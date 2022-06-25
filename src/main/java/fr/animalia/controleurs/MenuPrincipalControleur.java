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

public class MenuPrincipalControleur
{
    /** Association FXML avec attributs */
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
                    .nom(txtNom.getText())
                    .espece(txtEspece.getText())
                    .race(txtRace.getText())
                    .sexe(choixSexe.getValue())
                    .dateNaissance(choixDateNaissance.getValue())
                    .sos(choixSOS.isSelected())
                    .description(txtDescription.getText())
                    .build();

            // Ajout ses premieres informations de sejour
            InformationSejour informationSejourBase = InformationSejour.builder()
                    .dateDebutSejour(LocalDate.now().plusDays(1))
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
        }

        else
        {
            labelMessage.setText("Champs requis manquant");
        }
    }

    /** Vérifie si la saisie est correcte */
    public boolean verifierSaisie()
    {
        // Verifie si tous les champs requis sont bien remplis sinon renvoie faux
        if(txtNumPuce.getText().isEmpty()) return false;
        if(txtNom.getText().isEmpty()) return false;
        if(txtEspece.getText().isEmpty()) return false;
        if(choixSexe.getValue() == null) return false;
        if(choixDateNaissance.getValue() == null) return false;
        if(choixRefuge.getValue() == null) return false;

        return true;
    }

}
