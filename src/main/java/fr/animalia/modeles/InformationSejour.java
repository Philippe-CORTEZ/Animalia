package fr.animalia.modeles;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Décrit les informations d'un séjour d'un animal dans un refuge
 * Notamment sa date d'arrivée, de départ
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Getter
public class InformationSejour
{
    /** ID automatiquement généré par la base de données */
    private long id;

    /** L'animal qui passe le séjour */
    private Animal pensionnaire;

    /** Le refuge où l'animal a passé son séjour */
    private Refuge refuge;

    /** Date d'arrivée au refuge */
    private LocalDate dateDebutSejour;

    /** Date de départ du refuge */
    @Setter
    private LocalDate dateFinSejour;

    /** Les raisons du départ du refuge */
    @Setter
    private String motifFinDeSejour;



    /** Redéfinition de equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof InformationSejour)) return false;

        InformationSejour that = (InformationSejour) o;

        if (!Objects.equals(pensionnaire, that.pensionnaire)) return false;
        if (!Objects.equals(refuge, that.refuge)) return false;
        return Objects.equals(dateDebutSejour, that.dateDebutSejour);
    }

    /** Redéfinition de hashCode */
    @Override
    public int hashCode()
    {
        int result = pensionnaire != null ? pensionnaire.hashCode() : 0;
        result = 31 * result + (refuge != null ? refuge.hashCode() : 0);
        result = 31 * result + (dateDebutSejour != null ? dateDebutSejour.hashCode() : 0);
        return result;
    }

}
