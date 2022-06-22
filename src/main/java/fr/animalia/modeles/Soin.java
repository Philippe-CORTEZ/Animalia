package fr.animalia.modeles;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

/**
 * Représente les soins qu'a pu avoir un animal
 * Un soin se distingue par son nom, un soin a un certain coût
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Getter

@Entity

@JsonTypeName("soin")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nom", scope = Soin.class)
public class Soin
{
    /** Le nom du soin */
    @Id
    private String nom;

    /** Une description du soin */
    private String description;

    /** Le coût du soin */
    @Setter
    private float prix;


    /** Redéfinition de equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Soin)) return false;

        Soin soin = (Soin) o;

        return Objects.equals(nom, soin.nom);
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
        return "Soin{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }

}
