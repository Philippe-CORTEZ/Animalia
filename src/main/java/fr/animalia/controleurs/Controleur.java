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

    /**
     * Formate la chaine pour qu'elle soit conforme avant persistance
     * @param texteATraiter la chaîne à traiter
     * @return la même chaîne mais traité
     */
    default String traiterSaisieTexte(String texteATraiter)
    {
        String res = texteATraiter;

        // Mise en majuscule
        res = res.toUpperCase();

        // Remplacement des espaces par des "-"
        res = res.replace(" ", "-");

        return res;
    }

    /**
     * Traite et détecte si une saisie sur un champ qui nécessite un nombre est correcte
     * @param texteATraiter la chaîne qui contient le dit nombre
     * @return vrai si la saisie est correcte (c'est un nombre), faux sinon
     */
    default boolean traiterSaisieNombre(String texteATraiter)
    {
        // Supprime prealablement les espaces
        texteATraiter = texteATraiter.replace(" ", "");


        // Si une erreur du type NumberFormatException est lever c'est que la chaine n'etait pas un nombre
        try
        {
           Double.parseDouble(texteATraiter);
        }
        catch (NumberFormatException erreur)
        {
            return false;
        }

        return true;
    }

}
