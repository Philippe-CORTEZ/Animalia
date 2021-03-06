package fr.animalia.modeles;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Décrit les informations d'une adoption entre une personne et l'animal
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Getter

@Entity
@Table(name = "INFORMATION_ADOPTION", uniqueConstraints = {@UniqueConstraint(name = "PKK_INFO_ADOPTION", columnNames = {"NUM_PUCE_ANIMAL", "NUM_MAITRE"}) })

@JsonTypeName("information_adoption")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = InformationAdoption.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InformationAdoption implements Entite
{
    /** ID automatiquement généré par la base de données */
    @Builder.Default
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;

    /** La maître adoptant */
    @ManyToOne
    @JoinColumn(name = "NUM_MAITRE")
    private Personne maitre;

    /** L'animal adopté */
    @ManyToOne
    @JoinColumn(name = "NUM_PUCE_ANIMAL")
    @JsonProperty("num_puce_animal")
    @JsonIdentityReference(alwaysAsId = true)
    private Animal animal;

    /** La date de l'adoption */
    @Column(name = "DATE_ADOPTION")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAdoption;

    /** La date du retour d'adoption (si l'adoption s'est mal passée) */
    @Setter
    @Column(name = "DATE_RETOUR")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRetour;

    /** Indique si l'adoption s'est terminée par un retour */
    @Builder.Default
    @Setter
    @Column(name = "RETOUR_ADOPTION")
    private boolean retourAdoption = false;

    /** Cotisation versée pour cette adoption */
    private float cotisation;

    /** Don libre versé pour cette adoption */
    @Builder.Default
    private float don = 0;



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


    /** Redéfinition de toString */
    @Override
    public String toString()
    {
        return "InformationAdoption{" +
                "id=" + id +
                ", maitre_id=" + maitre.getId() +
                ", animal_id=" + animal.getId() +
                ", dateAdoption=" + dateAdoption +
                ", dateRetour=" + dateRetour +
                ", retourAdoption=" + retourAdoption +
                ", cotisation=" + cotisation +
                ", don=" + don +
                '}';
    }

}
