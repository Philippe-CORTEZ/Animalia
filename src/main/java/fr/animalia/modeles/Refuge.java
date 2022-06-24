package fr.animalia.modeles;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Un refuge accueil des animaux, en attendant qu'ils soient adoptés
 * Un refuge se distingue par son nom
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Getter

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "PKK_Refuge", columnNames = {"nom"}) })

@JsonTypeName("refuge")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Refuge.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Refuge implements Entite
{
    /** ID automatiquement généré par la base de données */
    @Builder.Default
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;

    /** Le nom du refuge */
    private String nom;

    /** L'adresse du refuge */
    private String adresse;

    /** Description du refuge */
    @Setter
    private String description;

    /** Les animaux séjournant dans le refuge ainsi que leurs informations */
    @Builder.Default
    @OneToMany(mappedBy = "refuge")
    @JsonProperty("id_info_sejour")
    @JsonIdentityReference(alwaysAsId = true)
    private List<InformationSejour> informationSejours = new ArrayList<>();



    /** Redéfinition de equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Refuge)) return false;

        Refuge refuge = (Refuge) o;

        return Objects.equals(nom, refuge.nom);
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
        return nom;
    }

}
