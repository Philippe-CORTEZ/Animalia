package fr.animalia.modeles;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

/**
 * Représente les soins qu'a pu avoir un animal
 * Un soin se distingue par son nom, un soin a un certain coût
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Getter

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "PKK_Soin", columnNames = {"nom"}) })

@JsonTypeName("soin")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Soin.class)
public class Soin implements Entite
{
    /** ID automatiquement généré par la base de données */
    @Builder.Default
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;

    /** Le nom du soin */
    private String nom;

    /** Une description du soin */
    private String description;

    /** Le coût du soin */
    @Setter
    private float prix;

    /** Le pourcentage qui sera à charge de l'adoptant lors de l'adoption */
    @Setter
    @Column(name = "POURCENTAGE_CHARGE")
    private float pourcentageCharge;


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
