package fr.animalia.controleurs;


import fr.animalia.modeles.Soin;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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



    public void validerSaisie()
    {
        // a faire
    }

}
