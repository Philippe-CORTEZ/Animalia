package fr.animalia.modeles;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Une pathologie décrit une maladie que peut avoir un animal
 * Une pathologie se distingue par le nom
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Getter
public class Pathologie
{
    /** Le nom de la pathologie */
    private String nom;

    /** La description de la pathologie */
    private String description;



    /** Redéfinition de equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Pathologie)) return false;

        Pathologie that = (Pathologie) o;

        return Objects.equals(nom, that.nom);
    }

    /** Redéfinition de hashCode */
    @Override
    public int hashCode()
    {
        return nom != null ? nom.hashCode() : 0;
    }

    /** Redéfinition de toString */
    @Override
    public String toString()
    {
        return "Pathologie{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
