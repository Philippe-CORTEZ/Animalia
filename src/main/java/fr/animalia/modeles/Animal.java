package fr.animalia.modeles;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente les éléments de bases que possède un animal
 * Un animal se distingue par son numéro de puce électronique (implanté à son arrivée)
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Getter

@Entity

@JsonTypeName("animal")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Animal.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Animal implements Entite
{
    /** Le numéro de puce électronique */
    @Id
    @Column(name = "NUM_PUCE")
    private long id;

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

    /** Le coût de base pour adopter l'animal (hors frais médical) */
    @Setter
    private float prix;

    /** Les pathologies dont est atteint l'animal */
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "PATHOLOGIES_ANIMAUX", joinColumns = {@JoinColumn(name = "NUM_PUCE_ANIMAL")}, inverseJoinColumns = {@JoinColumn(name = "ID_PATHOLOGIE")})
    @JsonProperty("id_pathologie")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Pathologie> pathologies = new ArrayList<>();

    /** Les soins qu'a reçus l'animal */
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "SOINS_ANIMAUX", joinColumns = {@JoinColumn(name = "NUM_PUCE_ANIMAL")}, inverseJoinColumns = {@JoinColumn(name = "ID_SOIN")})
    @JsonProperty("id_soin")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Soin> soins = new ArrayList<>();

    /** Les informations d'adoptions de l'animal (maître, date, ...) */
    @Builder.Default
    @OneToMany(mappedBy = "animal")
    private List<InformationAdoption> informationAdoptions = new ArrayList<>();

    /** Les informations de ses refuges (ainsi que lui actuel) */
    @Builder.Default
    @OneToMany(mappedBy = "pensionnaire", cascade = CascadeType.PERSIST)
    private List<InformationSejour> informationSejours = new ArrayList<>();



    /** Redéfinition de equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        return id == animal.id;
    }

    /** Redéfinition de hashCode */
    @Override
    public int hashCode()
    {
        return (int) (id ^ (id >>> 32));
    }

    /** Redéfinition de toString */
    @Override
    public String toString()
    {
        return id + " - " + nom;
    }

}
