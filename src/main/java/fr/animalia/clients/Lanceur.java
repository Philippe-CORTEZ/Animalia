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


// TODO : Sectionner le bloc d'attribut FXML
// TODO : Refactor code contrôleur
// TODO : Refactor FXML
// TODO : Refactor code ClientREST
// TODO : Transformer classe FicherPerso en interface
