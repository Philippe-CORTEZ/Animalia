package fr.animalia.modeles;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
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

@Entity

@JsonTypeName("animal")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numPuce", scope = Animal.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Animal
{
    /** Le numéro de puce électronique */
    @Id
    @Column(name = "NUM_PUCE")
    private UUID numPuce;

    /** Le nom de l'animal */
    private String nom;

    /** La date de naissance de l'animal */
    @Column(name = "DATE_NAISSANCE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;

    /** Le sexe de l'animal (male ou femelle) */
    @Enumerated(EnumType.STRING)
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

    /** Les pathologies dont est atteint l'animal */
    @ManyToMany
    @JoinTable(name = "PATHOLOGIES_ANIMAUX", joinColumns = {@JoinColumn(name = "NUM_PUCE_ANIMAL")}, inverseJoinColumns = {@JoinColumn(name = "NOM_PATHOLOGIE")})
    @JsonProperty("nom_pathologie")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Pathologie> pathologies;

    /** Les soins qu'a reçus l'animal */
    @ManyToMany
    @JoinTable(name = "SOINS_ANIMAUX", joinColumns = {@JoinColumn(name = "NUM_PUCE_ANIMAL")}, inverseJoinColumns = {@JoinColumn(name = "NOM_SOINS")})
    @JsonProperty("nom_soin")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Soin> soins;

    /** Les informations d'adoptions de l'animal (maître, date, ...) */
    @OneToMany(mappedBy = "animal")
    @JsonProperty("id_info_adoption")
    @JsonIdentityReference(alwaysAsId = true)
    private List<InformationAdoption> informationAdoptions;



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
