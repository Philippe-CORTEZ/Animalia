package fr.animalia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Objects;

public class App extends Application
{
    /** Stage pour changer le contenu */
    @Getter
    @Setter
    private static Stage stage = new Stage();

    /**
     * Initialise la stage pour l'application JavaFX
     * @param primaryStage la stage préparée par JavaFX
     * @throws IOException erreur levée lorsque le fichier fxml spécifié n'existe pas
     */
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        // Initialise la stage static avec elle preparee par JavaFX
        App.setStage(primaryStage);

        // Charge le fichier fxml et l'affiche
        Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getClassLoader().getResource("vues/EnregistrementPensionnaire.fxml")));
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Animalia");
        stage.show();
    }

}
