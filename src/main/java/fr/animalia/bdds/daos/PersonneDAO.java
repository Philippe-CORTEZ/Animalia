package fr.animalia.bdds.daos;

import fr.animalia.modeles.Personne;
import java.util.List;

/**
 * La DAO qui gère les objets de type Personne
 * @author Philippe CORTEZ
 */
public class PersonneDAO extends DAOGenerique<Personne>
{
    /**
     * Recherche une personne par son nom ET prénom
     * @param nom le nom de la personne
     * @param prenom le prénom de la personne
     * @return les personnes avec ce nom et prénom
     */
    public List<Personne> rechercherParNomPrenom(String nom, String prenom)
    {
        // Evite les problemes de casse
        nom = nom.toUpperCase();
        prenom = prenom.toUpperCase();

        return entityManager.createQuery("select personne from Personne personne where personne.nom = :nom and personne.prenom = :prenom", Personne.class)
                .setParameter("nom", nom)
                .setParameter("prenom", prenom)
                .getResultList();
    }

}
