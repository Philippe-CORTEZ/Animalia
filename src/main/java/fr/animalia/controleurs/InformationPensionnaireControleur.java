package fr.animalia.controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Contrôleur qui gère le menu personnel de chaque pensionnaire préalablement sélectionné
 * @author Philippe CORTEZ
 */
public class InformationPensionnaireControleur implements Controleur
{
    /**
     * Association FXML avec attributs
     */
    @FXML
    private Label infoNumPuce = new Label();

    @FXML
    private Label infoNom = new Label();

    @FXML
    private Label infoEspece = new Label();

    @FXML
    private Label infoRace = new Label();

    @FXML
    private Label infoSexe = new Label();

    @FXML
    private Label infoDateNaissance = new Label();

    @FXML
    private Label infoRefuge = new Label();

    @FXML
    private Label infoDescriptif = new Label();

    @FXML
    private Label infoPathologies = new Label();

    @FXML
    private Label infoSoins = new Label();

    @FXML
    private Label infoSOS = new Label();



    /** Initialise les valeurs de certains widgets */
    @FXML
    public void initialize()
    {
        // Initialise les informations de la fiche personnelle de l'animal
        FichePersonnelleControleur.initialiserFichePersonnelle(infoNumPuce, infoNom, infoEspece, infoRace, infoSexe,
                infoDateNaissance, infoRefuge, infoSOS, infoDescriptif);

        // Initialise la section Suivi medical
       infoPathologies.setText(String.valueOf(DonneeIHM.getAnimalSelectionne().getPathologies()));
       infoSoins.setText(String.valueOf(DonneeIHM.getAnimalSelectionne().getSoins()));
    }


    /** Affiche la popup pour adopter l'animal couramment sélectionné */
    public void afficherAdoption()
    {
        creerPopup("/vues/Adoption.fxml", "Adoption");
    }

}