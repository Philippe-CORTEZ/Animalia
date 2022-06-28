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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur qui gère l'adoption d'un animal
 * @author Philippe CORTEZ
 */
public class AdoptionControleur
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
        infoNumPuce.setText(String.valueOf(DonneeIHM.getAnimalSelectionne().getId()));
        infoNom.setText(DonneeIHM.getAnimalSelectionne().getNom());
        infoEspece.setText(DonneeIHM.getAnimalSelectionne().getEspece());
        infoRace.setText(DonneeIHM.getAnimalSelectionne().getRace());
        infoSexe.setText(String.valueOf(DonneeIHM.getAnimalSelectionne().getSexe()));
        infoDateNaissance.setText(String.valueOf(DonneeIHM.getAnimalSelectionne().getDateNaissance()));
        infoRefuge.setText(DonneeIHM.getAnimalSelectionne().getInformationSejours().get(DonneeIHM.getAnimalSelectionne().getInformationSejours().size() - 1).getRefuge().getNom());

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
        if(txtNumMaitre.getText().isEmpty() || txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty()
                || choixDateNaissance.getValue() == null || txtAdresse.getText().isEmpty())
        {
            labelMessage.setText("Champs requis manquant");
            return false;
        }

        // Si le champs don est vide alors le met a 0
        if(txtDon.getText().isEmpty()) txtDon.setText("0");

        return true;
    }


    public void validerSaisie()
    {
        // Reinitialise le message d'erreur
        labelMessage.setText("");
        labelMessage.setTextFill(Color.RED);

        // La saisie est correcte (tous les champs sont remplis)
        if(verfierSaisie())
        {
            // Si l'adoptant est deja enregistre
            Personne maitre = ClientREST.getWebRessource().path("personnes/" + txtNumMaitre.getText()).request().get(Personne.class);

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
        }
    }

}
