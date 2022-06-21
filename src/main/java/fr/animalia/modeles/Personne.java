package fr.animalia.modeles;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Une personne est reconnue par ses informations personnelles (nom, prénom, date naissance)
 * Une personne est enregistré lorsqu'elle adopte un animal du refuge, elle devient ainsi son maître
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Getter
public class Personne
{
    /** L'identifiant d'une personne en tant que maître*/
    private UUID numMaitre;

    /** Le nom de la personne */
    private String nom;

    /** Le prénom de la personne */
    private String prenom;

    /** Date de naissance de la personne */
    private LocalDate dateNaissance;

    /** L'adresse de la personne */
    @Setter
    private String adresse;



    /** Redéfinition de equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Personne)) return false;

        Personne personne = (Personne) o;

        return Objects.equals(numMaitre, personne.numMaitre);
    }

    /** Redéfinition de hashCode */
    @Override
    public int hashCode()
    {
        return numMaitre != null ? numMaitre.hashCode() : 0;
    }

    /** Redéfinition de toString */
    @Override
    public String toString()
    {
        return "Personne{" +
                "numMaitre=" + numMaitre +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", adresse='" + adresse + '\'' +
                '}';
    }

}
