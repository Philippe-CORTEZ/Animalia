package fr.animalia.controleurs;

import fr.animalia.clients.ClientREST;
import fr.animalia.modeles.Animal;
import jakarta.ws.rs.core.GenericType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Contrôleur qui s'occupe de liste les pensionnaires (liste dans le menu principal)
 * @author Philippe CORTEZ
 */
public class ListePensionnaireControleur implements Controleur
{
    /** Informations sur la liste des pensionnaires */
    @FXML
    private ListView<Animal> listViewPensionnaires = new ListView<>();



    /** Initialise les valeurs de certains widgets */
    @FXML
    public void initialize()
    {
        // initialise la liste des pensionnaires
        initialiserListePensionnaires();
    }


    /** Initialise la liste des pensionnaires et affecte un traitement à chaque cellules */
    private void initialiserListePensionnaires()
    {
        // Remplis la liste avec les pensionnaires existant
        rechargerListePensionnaires();

        // Ajout une action a chaque element de la listView (les pensionnaires)
        // Ici affiche sa fiche personnelle
        listViewPensionnaires.setOnMouseClicked(event ->
        {
            if(listViewPensionnaires.getSelectionModel().selectedItemProperty().getValue() != null)
            {
                // Recupere pour l'instant l'animal selectionne et reinitilaise la selection de la liste (pour selectionner un autre pensionnaire)
                DonneeIHM.setAnimalSelectionne(listViewPensionnaires.getSelectionModel().selectedItemProperty().getValue());
                int indexe = listViewPensionnaires.getSelectionModel().getSelectedIndex();
                listViewPensionnaires.getSelectionModel().clearSelection(indexe);

                // Affiche les informations pour le pensionnaire selectionne
                creerPopup("/vues/InformationPensionnaire.fxml", "Menu personnel");
            }
        });
    }


    /** Met à jour la liste des pensionnaires */
    public void rechargerListePensionnaires()
    {
        List<Animal> animaux = ClientREST.getWebRessource().path("animaux/all").request().get(new GenericType<>(){});
        listViewPensionnaires.setItems(FXCollections.observableArrayList(animaux));
    }
}
