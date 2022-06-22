package fr.animalia.modeles;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
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
@Builder

@Getter

@Entity
@Table(name = "INFORMATION_SEJOUR", uniqueConstraints = {@UniqueConstraint(name = "PKK_INFO_SEJOUR", columnNames = {"NUM_PUCE_ANIMAL", "ID_REFUGE", "DATE_DEBUT_SEJOUR"}) })

@JsonTypeName("information_sejour")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = InformationSejour.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InformationSejour implements Entite
{
    /** ID automatiquement généré par la base de données */
    @Builder.Default
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;

    /** L'animal qui passe le séjour */
    @ManyToOne
    @JoinColumn(name = "NUM_PUCE_ANIMAL")
    private Animal pensionnaire;

    /** Le refuge où l'animal a passé son séjour */
    @ManyToOne
    @JoinColumn(name = "ID_REFUGE")
    private Refuge refuge;

    /** Date d'arrivée au refuge */
    @Column(name = "DATE_DEBUT_SEJOUR")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebutSejour;

    /** Date de départ du refuge */
    @Setter
    @Column(name = "DATE_FIN_SEJOUR")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinSejour;

    /** Les raisons du départ du refuge */
    @Setter
    @Column(name = "MOTIF_FIN_SEJOUR")
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
