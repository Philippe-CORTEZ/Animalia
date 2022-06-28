package fr.animalia.controleurs;

import fr.animalia.modeles.Animal;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe qui stock les données nécessaires entre plusieurs fenêtres
 * @author Philippe CORTEZ
 */
public class DonneeIHM
{
    /** L'animal sélectionné par l'utilisateur */
    @Getter
    @Setter
    private static Animal animalSelectionne;
}
