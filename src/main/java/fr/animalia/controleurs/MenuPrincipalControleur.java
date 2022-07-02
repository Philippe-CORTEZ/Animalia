package fr.animalia.controleurs;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


/**
 * Contrôleur qui gère le menu principal de l'application
 * @author Philippe CORTEZ
 */
public class MenuPrincipalControleur implements Controleur
{
    /** Panes pour switcher en les différentes vues (enregistrement, liste pensionnaires) */
    @FXML
    private AnchorPane paneEnregistrement = new AnchorPane();

    @FXML
    private AnchorPane paneConsultation = new AnchorPane();



    /** Affiche les pensionnaires */
    public void afficherConsultation()
    {
        paneConsultation.toFront();
        paneEnregistrement.toBack();
    }

    /** Affiche le formulaire d'enregistrement d'un pensionnaire */
    public void afficherEnregistrement()
    {
        paneEnregistrement.toFront();
        paneConsultation.toBack();
    }

}
