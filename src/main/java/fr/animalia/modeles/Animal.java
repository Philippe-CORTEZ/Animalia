package fr.animalia.modeles;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Représente les éléments de bases que possède un animal
 * Un animal se distingue par son numéro de puce électronique (implanté à son arrivée)
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Getter
public class Animal
{
    /** Le numéro de puce électronique */
    private UUID numPuce;

    /** Le nom de l'animal */
    private String nom;

    /** La date de naissance de l'animal */
    private LocalDate dateNaissance;

    /** Le sexe de l'animal (male ou femelle) */
    private EnumSexe sexe;

    /** L'espèce de l'animal (chien, chat, cheval, ...) */
    private String espece;

    /** La race de l'animal */
    private String race;

    /** Description de l'animal */
    @Setter
    private String description;

    /** SOS = animal à adopter en urgence, cotisation libre */
    @Setter
    private boolean sos;



    /** Redéfinition de equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        return Objects.equals(numPuce, animal.numPuce);
    }

    /** Redéfinition de hashCode */
    @Override
    public int hashCode()
    {
        return numPuce != null ? numPuce.hashCode() : 0;
    }

    /** Redéfinition de toString */
    @Override
    public String toString()
    {
        return "Animal{" +
                "numPuce=" + numPuce +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", sexe=" + sexe +
                ", espece='" + espece + '\'' +
                ", race='" + race + '\'' +
                ", description='" + description + '\'' +
                ", sos=" + sos +
                '}';
    }

}
