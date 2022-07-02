package fr.animalia.controleurs;

import fr.animalia.clients.ClientREST;
import fr.animalia.modeles.Animal;
import fr.animalia.modeles.EnumSexe;
import fr.animalia.modeles.InformationSejour;
import fr.animalia.modeles.Refuge;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.List;

/**
 * Contrôleur qui s'occupe de l'enregistrement d'un pensionnaire (formulaire dans le menu principal)
 * @author Philippe CORTEZ
 */
public class EnregistrementPensionnaireControleur implements Controleur
{
    /** Informations pour le formulaire d'enregistrement d'un pensionnaire */
    @FXML
    private TextField txtNumPuce = new TextField();

    @FXML
    private TextField txtNom = new TextField();

    @FXML
    private TextField txtEspece = new TextField();

    @FXML
    private TextField txtRace = new TextField();

    @FXML
    private ComboBox<EnumSexe> choixSexe = new ComboBox<>();

    @FXML
    private DatePicker choixDateNaissance = new DatePicker();

    @FXML
    private CheckBox choixSOS = new CheckBox();

    @FXML
    private ComboBox<Refuge> choixRefuge = new ComboBox<>();

    @FXML
    private TextArea txtDescription = new TextArea();

    @FXML
    private TextField txtPrix = new TextField();


    /** Label pour le retour visuel de l'utilisateur */
    @FXML
    private Label labelMessage = new Label();



    /** Initialise les valeurs de certains widgets */
    @FXML
    public void initialize()
    {
        // Valeur pour le sexe de l'animal
        choixSexe.setItems(FXCollections.observableArrayList(EnumSexe.MALE, EnumSexe.FEMELLE));

        // Liste des refuges
        List<Refuge> refuges = ClientREST.getWebRessource().path("refuges/all").request().get(new GenericType<>(){});
        choixRefuge.setItems(FXCollections.observableArrayList(refuges));
    }


    /** Valide la saisie et enregistre l'animal en base de données */
    public void validerSaisie()
    {
        // Reinitialise le message d'erreur
        labelMessage.setText("");
        labelMessage.setTextFill(Color.RED);

        // Verifie si la saisie est correct auquel cas affiche un message d'erreur
        if(verifierSaisie())
        {
            // Sinon creer l'animal avec les donnees saisie
            Animal animal = Animal.builder()
                    .id(Long.parseLong(txtNumPuce.getText()))
                    .nom(traiterSaisieTexte(txtNom.getText()))
                    .espece(traiterSaisieTexte(txtEspece.getText()))
                    .race(traiterSaisieTexte(txtRace.getText()))
                    .sexe(choixSexe.getValue())
                    .dateNaissance(choixDateNaissance.getValue())
                    .sos(choixSOS.isSelected())
                    .description(txtDescription.getText())
                    .prix(Float.parseFloat(txtPrix.getText()))
                    .build();

            // Ajout ses premieres informations de sejour
            InformationSejour informationSejourBase = InformationSejour.builder()
                    .dateDebutSejour(LocalDate.now())
                    .pensionnaire(animal)
                    .refuge(choixRefuge.getValue())
                    .refugeActuel(true)
                    .build();

            animal.getInformationSejours().add(informationSejourBase);

            // Persiste l'animal et ses informations de sejour (dans son premier refuge)
            ClientREST.getWebRessource().path("animaux").request().post(Entity.entity(animal, MediaType.APPLICATION_JSON), Animal.class);

            // Affiche un message de reussite
            labelMessage.setTextFill(Color.GREEN);
            labelMessage.setText("Enregistrement réussit");

            // Reinitialise tous les champs pour une saisie future
            reinitialiserChamps();
        }
    }

    /**
     * Vérifie si la saisie est correcte
     * @return vrai si la saisie est correcte, faux sinon
     */
    private boolean verifierSaisie()
    {
        // Verifie si tous les champs requis sont bien remplis sinon renvoie faux
        if(txtNumPuce.getText().isEmpty() || txtNom.getText().isEmpty() || txtEspece.getText().isEmpty() ||
                choixSexe.getValue() == null || choixDateNaissance.getValue() == null
                || choixRefuge.getValue() == null || txtPrix.getText().isEmpty())
        {
            labelMessage.setText("Champs requis manquant");
            return false;
        }


        // Verifie si le prix est bien un flottant
        if(!traiterSaisieNombre(txtPrix.getText()))
        {
            labelMessage.setText("Saisir un nombre (champs prix)");
            return false;
        }

        // Verifie si le numero de puce est correcte
        // Si c'est bien un nombre et s'il ne provoquera pas une erreur de violation de cle primaire
        if(!traiterSaisieNombre(txtNumPuce.getText()))
        {
            labelMessage.setText("Saisir un numéro (champs numéro puce)");
            return false;
        }

        long numPuce = Long.parseLong(txtNumPuce.getText());
        if(ClientREST.getWebRessource().path("animaux/" + numPuce).request().get(Animal.class) != null)
        {
            labelMessage.setText("Numéro de puce déjà existant");
            return false;
        }

        return true;
    }

    /** Réinitialise tous les champs de saisie pour une prochaine saisie */
    private void reinitialiserChamps()
    {
        txtNumPuce.setText("");
        txtNom.setText("");
        txtEspece.setText("");
        txtRace.setText("");
        choixSexe.getSelectionModel().clearSelection();
        choixDateNaissance.getEditor().clear();
        choixSOS.setSelected(false);
        choixRefuge.getSelectionModel().clearSelection();
        txtDescription.setText("");
        txtPrix.setText("");
    }

}
