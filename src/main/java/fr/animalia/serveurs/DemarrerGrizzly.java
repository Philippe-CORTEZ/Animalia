package fr.animalia.serveurs;

import lombok.extern.slf4j.Slf4j;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http2.Http2AddOn;
import org.glassfish.grizzly.http2.Http2Configuration;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Démarre un serveur Grizzly en écoute sur les ports et URI indiqués
 * @author Philippe CORTEZ
 * @version 1.00
 */
@Slf4j
public class DemarrerGrizzly
{
    /** URI où le serveur Grizzly écoutera */
    public static final String BASE_URI = "http://0.0.0.0:8080/animalia";
    /** Port HTTPS utilisé */
    public static final int TLS_PORT = 4443;


    /** Démarre le serveur est se met en écoute jusqu'a un CTRL-C */
    public static void main(String[] args) throws InterruptedException, IOException
    {
        log.info("Serveur REST démarrage sur l'URI : " + BASE_URI);
        final HttpServer serveur = demarrerServeur();

        ajoutTLSEtHttp2(serveur);

        // Le serveur s'eteindra a la fin du programme
        Runtime.getRuntime().addShutdownHook(new Thread(serveur::shutdownNow));

        log.info(String.format("%n Application démarrée.%n Pour arrêter l'application :  CTRL+C %n"));

        // Attente de CTRL+C
        Thread.currentThread().join();
        serveur.shutdown();
    }

    /**
     * Démarre un serveur HTTP Grizzly utilisant les ressources JAX-RS de cette application
     * @return Grizzly HTTP serveur.
     */
    public static HttpServer demarrerServeur()
    {
        Logger logger = Logger.getLogger(DemarrerGrizzly.class.getName());
        logger.setLevel(Level.FINE);

        final ResourceConfig rc = new ResourceConfig()
                .packages(true, "fr.animalia")
                .register(new LoggingFeature(logger, Level.INFO, LoggingFeature.Verbosity.PAYLOAD_TEXT, null));

        // Creer et demarre une instance grizzly
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Ajoute une connexion en HTTPS et le protocole HTTP2
     * @param httpServeur le serveur http
     */
    public static HttpServer ajoutTLSEtHttp2(HttpServer httpServeur) throws IOException
    {
        NetworkListener listener = new NetworkListener("TLS", NetworkListener.DEFAULT_NETWORK_HOST, TLS_PORT);
        listener.setSecure(true);

        // Ajout d'un certificat pour authentifier le serveur, se trouvant dans src/main/resources/ssl
        // Ce certificat est normalement par defaut auto signe et genere par maven (voir pom.xml)
        SSLContextConfigurator sslContextConfigurator = new SSLContextConfigurator();
        sslContextConfigurator.setKeyStoreBytes(Objects.requireNonNull(DemarrerGrizzly.class.getResourceAsStream("/ssl/cert.jks")).readAllBytes());
        sslContextConfigurator.setKeyStorePass("storepass");

        listener.setSSLEngineConfig(new SSLEngineConfigurator(sslContextConfigurator, false, false, false));

        // Creer une configuration par defaut de HTTP2
        Http2Configuration configuration = Http2Configuration.builder().build();
        Http2AddOn http2Addon = new Http2AddOn(configuration);

        // Enregistre l'Addon
        listener.registerAddOn(http2Addon);
        httpServeur.addListener(listener);

        return httpServeur;
    }

}
