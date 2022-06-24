package fr.animalia.controleurs;

import fr.animalia.clients.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * Mutualise les comportements communs entre les contrôleurs
 * @author Philippe CORTEZ
 */
public interface Controleur
{
    /**
     * Charge un fichier FXML et l'affiche, remplace le FXML courant dans la stage principale
     * @param nomFichierFXML le chemin vers un fichier FXML
     */
    default void changerFXML(String nomFichierFXML)
    {
        Logger log = LoggerFactory.getLogger(Controleur.class);

        try
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getClassLoader().getResource(nomFichierFXML)));
            Scene scene = new Scene(root);

            App.getStage().setScene(scene);
            App.getStage().sizeToScene();
        }
        catch (IOException e)
        {
            log.error("Impossible de charger le fichier spécifié", e);
        }
    }

    /**
     * Charge un fichier FXML et l'affiche dans une fenêtre popup
     * @param nomFichierFXML le chemin vers un fichier FXML
     * @param titre le titre que portera la popup
     * @param nonDecoree si vrai la fenêtre est non décorée (sans les boutons croix rouge, minimise et maximise)
     */
    default void creerPopup(String nomFichierFXML, String titre, boolean nonDecoree)
    {
        Logger log = LoggerFactory.getLogger(Controleur.class);

        try
        {
            // C'est une nouvelle fenetre, ainsi une stage est cree
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(nomFichierFXML)));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            // Si vrai supprime les boutons croix rouge, minimise et maximise
            if(nonDecoree)
            {
                stage.initStyle(StageStyle.UNDECORATED);
            }

            stage.setTitle(titre);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            log.error("Impossible de charger le fichier spécifié", e);
        }
    }

    /**
     * Charge un fichier FXML et l'affiche dans une fenêtre popup, avec décoration
     * @param nomFichierFXML le chemin vers un fichier FXML
     * @param titre le titre que portera la popup
     */
    default void creerPopup(String nomFichierFXML, String titre)
    {
        creerPopup(nomFichierFXML, titre, false);
    }

    /**
     * Charge un fichier FXML et l'affiche dans une fenêtre popup, avec décoration et titre par défaut
     * @param nomFichierFXML le chemin vers un fichier FXML
     */
    default void creerPopup(String nomFichierFXML)
    {
        creerPopup(nomFichierFXML, "Animalia");
    }

}
