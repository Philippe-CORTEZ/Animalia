package fr.animalia.clients;

import jakarta.ws.rs.client.ClientBuilder;
import javafx.application.Application;

public class Lanceur
{
    public static void main(String[] args)
    {
        // Creation et configuration du client REST
        ClientREST.setWebRessource(ClientBuilder.newClient().target("http://127.0.0.1:8080/animalia"));

        // Lancement de l'application JavaFX
        Application.launch(App.class);
    }
}
