package fr.animalia.modeles;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Décrit les informations d'une adoption entre une personne et l'animal
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Getter
public class InformationAdoption
{
    /** ID automatiquement généré par la base de données */
    private long id;

    /** La maître adoptant */
    private Personne maitre;

    /** L'animal adopté */
    private Animal animal;

    /** La date de l'adoption */
    private LocalDate dateAdoption;

    /** La date du retour d'adoption (si l'adoption s'est mal passée) */
    @Setter
    private LocalDate dateRetour;

    /** Indique si l'adoption s'est terminée par un retour */
    @Setter
    private boolean retourAdoption;

    /** Cotisation versée pour cette adoption */
    private float cotisation;

    /** Don libre versé pour cette adoption */
    private float don;



    /** Redéfinition de equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof InformationAdoption)) return false;

        InformationAdoption that = (InformationAdoption) o;

        if (!Objects.equals(maitre, that.maitre)) return false;
        return Objects.equals(animal, that.animal);
    }

    /** Redéfinition de hashCode */
    @Override
    public int hashCode()
    {
        int result = maitre != null ? maitre.hashCode() : 0;
        result = 31 * result + (animal != null ? animal.hashCode() : 0);
        return result;
    }

}
