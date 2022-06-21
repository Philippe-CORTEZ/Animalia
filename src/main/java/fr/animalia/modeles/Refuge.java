package fr.animalia.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.Objects;

/**
 * Un refuge accueil des animaux, en attendant qu'ils soient adoptés
 * Un refuge se distingue par son nom ET son adresse
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Getter

@Entity
public class Refuge
{
    /** Le nom du refuge */
    @Id
    private String nom;

    /** L'adresse du refuge */
    private String adresse;

    /** Description du refuge */
    @Setter
    private String description;

    /** Les animaux séjournant dans le refuge ainsi que leurs informations */
    @OneToMany(mappedBy = "refuge")
    private List<InformationSejour> informationSejours;



    /** Redéfinition de equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Refuge)) return false;

        Refuge refuge = (Refuge) o;

        if (!Objects.equals(nom, refuge.nom)) return false;
        return Objects.equals(adresse, refuge.adresse);
    }

    /** Redéfinition de hashCode */
    @Override
    public int hashCode()
    {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        return result;
    }

    /** Redéfinition de toString */
    @Override
    public String toString()
    {
        return "Refuge{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
