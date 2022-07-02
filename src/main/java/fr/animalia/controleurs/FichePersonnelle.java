package fr.animalia.controleurs;


import javafx.scene.control.Label;

/**
 * Factorise le code qui affiche dynamiquement la fiche personnelle d'un pensionnaire
 * Entre l'adoption et les informations en intégralité
 * @author Philippe CORTEZ
 */
public interface FichePersonnelle
{
    /**
     * Initialise les données de la fiche personnelle d'un pensionnaire
     * @param infoNumPuce information sur le numéro de puce
     * @param infoNom information sur le nom
     * @param infoEspece information sur l'espèce
     * @param infoRace information sur la race
     * @param infoSexe information sur le sexe
     * @param infoDateNaissance information sur la date de naissance
     * @param infoRefuge information sur le refuge actuel
     * @param infoDescriptif information sur la description du pensionnaire
     * @param infoSOS information si le pensionnaire est dans le dispositif SOS
     */
    default void initialiserFichePersonnelle(Label infoNumPuce, Label infoNom, Label infoEspece, Label infoRace,
                                                   Label infoSexe, Label infoDateNaissance, Label infoRefuge, Label infoSOS, Label infoDescriptif)
    {
        // Initialise les informations de la fiche personnelle de l'animal
        infoNumPuce.setText(String.valueOf(DonneeIHM.getAnimalSelectionne().getId()));
        infoNom.setText(DonneeIHM.getAnimalSelectionne().getNom());
        infoEspece.setText(DonneeIHM.getAnimalSelectionne().getEspece());
        infoRace.setText(DonneeIHM.getAnimalSelectionne().getRace());
        infoSexe.setText(String.valueOf(DonneeIHM.getAnimalSelectionne().getSexe()));
        infoDateNaissance.setText(String.valueOf(DonneeIHM.getAnimalSelectionne().getDateNaissance()));
        infoRefuge.setText(DonneeIHM.getAnimalSelectionne().getInformationSejours().get(DonneeIHM.getAnimalSelectionne().getInformationSejours().size() - 1).getRefuge().getNom());
        infoDescriptif.setText(DonneeIHM.getAnimalSelectionne().getDescription());

        if(DonneeIHM.getAnimalSelectionne().isSos()) infoSOS.setText("Dispositif SOS");
    }

    /**
     * Initialise les données de la fiche personnelle d'un pensionnaire (hors description)
     * @param infoNumPuce information sur le numéro de puce
     * @param infoNom information sur le nom
     * @param infoEspece information sur l'espèce
     * @param infoRace information sur la race
     * @param infoSexe information sur le sexe
     * @param infoDateNaissance information sur la date de naissance
     * @param infoRefuge information sur le refuge actuel
     */
    default void initialiserFichePersonnelle(Label infoNumPuce, Label infoNom, Label infoEspece, Label infoRace,
                                                   Label infoSexe, Label infoDateNaissance, Label infoRefuge, Label infoSOS)
    {
        initialiserFichePersonnelle(infoNumPuce, infoNom, infoEspece, infoRace, infoSexe, infoDateNaissance, infoRefuge, infoSOS, new Label(""));
    }

}
