package fr.animalia.controleurs;


import fr.animalia.clients.ClientREST;
import fr.animalia.modeles.Animal;
import fr.animalia.modeles.InformationAdoption;
import fr.animalia.modeles.Personne;
import fr.animalia.modeles.Soin;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur qui gère l'adoption d'un animal
 * @author Philippe CORTEZ
 */
public class AdoptionControleur implements Controleur
{
    /** Association FXML avec attributs */
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
    private Label infoSOS = new Label();

    @FXML
    private Label infoCoutAnimal = new Label();

    @FXML
    private Label infoCoutSoin = new Label();

    @FXML
    private Label infoTotal = new Label();

    @FXML
    private TextField txtDon = new TextField();

    @FXML
    private TextField txtNumMaitre = new TextField();

    @FXML
    private TextField txtNom = new TextField();

    @FXML
    private TextField txtPrenom = new TextField();

    @FXML
    private DatePicker choixDateNaissance = new DatePicker();

    @FXML
    private TextField txtAdresse = new TextField();

    @FXML
    private Label labelMessage = new Label();



    /** Initialise les valeurs de certains widgets */
    @FXML
    public void initialize()
    {
        // Initialise les informations de la fiche personnelle de l'animal
        FichePersonnelleControleur.initialiserFichePersonnelle(infoNumPuce, infoNom, infoEspece, infoRace, infoSexe, infoDateNaissance, infoRefuge, infoSOS);

        // Initialise les informations des frais
        infoCoutAnimal.setText(DonneeIHM.getAnimalSelectionne().getPrix() + "€");

        List<Float> coutSoins = calculerCoutsSoin();
        infoCoutSoin.setText(coutSoins.get(0) + "€ dont à charge " + coutSoins.get(1) + "€");

        float coutTotal = DonneeIHM.getAnimalSelectionne().getPrix() + coutSoins.get(1);
        infoTotal.setText(coutTotal + "€");
    }

    /**
     * Calcule le coût de tous les soins effectué pour l'animal sélectionné
     * Avec et sans le pourcentage pris a charge
     * @return une liste de deux flottants, le 1er élément le cout total, le 2e le cout à charge de l'adoptant
     */
    private List<Float> calculerCoutsSoin()
    {
        List<Float> coutSoins = new ArrayList<>();

        float coutSoinTotal = 0;
        float coutSoinCharge = 0;

        // Parcours tous les soins de l'animal et ajoute le prix dans les valeurs avec et sans charge
        for(Soin soin : DonneeIHM.getAnimalSelectionne().getSoins())
        {
            coutSoinTotal += soin.getPrix();
            coutSoinCharge += (soin.getPrix() * soin.getPourcentageCharge());
        }

        coutSoins.add(coutSoinTotal);
        coutSoins.add(coutSoinCharge);

        return coutSoins;
    }


    /**
     * Vérifie si la saisie est correcte
     * @return vrai si la saisie est correcte, faux sinon
     */
    private boolean verfierSaisie()
    {
        // Si le champs don est vide alors le met a 0
        if(txtDon.getText().isEmpty()) txtDon.setText("0");

        // Sinon verifie que la saisie est bien un nombre
        else
        {
            if(!traiterSaisieNombre(txtDon.getText()))
            {
                labelMessage.setText("Saisir un nombre (champs don)");
                return false;
            }
        }


        // Deux types de saisie possible

        // 1: seul le numero de maitre est renseigne
        if(!txtNumMaitre.getText().isEmpty())
        {
            // Dans ce cas il faut encore verifie que la saisie est bien un nombre
            if(!traiterSaisieNombre(txtNumMaitre.getText()))
            {
                labelMessage.setText("Saisir un nombre (champs numéro maître)");
                return false;
            }
            return true;
        }

        // 2: le numero de maitre n'est pas renseigne (creation de la personne avec son nom, prenom, date et adresse)
        if(!txtNom.getText().isEmpty() || !txtPrenom.getText().isEmpty()
                || choixDateNaissance.getValue() != null || !txtAdresse.getText().isEmpty())
        {
            return true;
        }

        // Cas ou aucun des deux possibilites n'est correctement remplis
        labelMessage.setText("Champs requis manquant");
        return false;
    }


    public void validerSaisie()
    {
        // Reinitialise le message d'erreur
        labelMessage.setText("");
        labelMessage.setTextFill(Color.RED);

        // La saisie est correcte (tous les champs requis sont remplis)
        if(verfierSaisie())
        {
            Personne maitre;

            // Si l'adoptant n'a pas ete renseigner par le numero de maitre, elle est pas encore persiste
            // Il faut donc la creer puis la persister
            // Puis dans tous les cas recupere ensuite la personne pour avoir toute les informations
            if(txtNumMaitre.getText().isEmpty())
            {
                Personne personne = Personne.builder()
                        .nom(traiterSaisieTexte(txtNom.getText()))
                        .prenom(traiterSaisieTexte(txtPrenom.getText()))
                        .dateNaissance(choixDateNaissance.getValue())
                        .adresse(traiterSaisieTexte(txtAdresse.getText()))
                        .build();

                maitre = ClientREST.getWebRessource().path("personnes").request().post(Entity.entity(personne, MediaType.APPLICATION_JSON), Personne.class);
            }

            else
            {
                maitre = ClientREST.getWebRessource().path("personnes/" + txtNumMaitre.getText()).request().get(Personne.class);
            }

            // Creer les informations de l'adoption
            InformationAdoption informationAdoption = InformationAdoption.builder()
                    .dateAdoption(LocalDate.now())
                    .animal(DonneeIHM.getAnimalSelectionne())
                    .don(Float.parseFloat(txtDon.getText()))
                    .cotisation(Float.parseFloat(infoTotal.getText().substring(0, infoTotal.getText().length() - 1)))
                    .maitre(maitre)
                    .build();

            // Mise a jour des informations de l'animal (sur l'adoption)
            DonneeIHM.getAnimalSelectionne().getInformationAdoptions().add(informationAdoption);
            ClientREST.getWebRessource().path("animaux/" + DonneeIHM.getAnimalSelectionne().getId())
                    .request().put(Entity.entity(DonneeIHM.getAnimalSelectionne(), MediaType.APPLICATION_JSON), Animal.class);

            labelMessage.setTextFill(Color.GREEN);
            labelMessage.setText("Adoption réussite");

            // Ferme la fenetre une fois l'adoption reussite
            ((Stage)(labelMessage.getScene().getWindow())).close();
        }
    }

}
