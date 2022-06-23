package fr.animalia.bdds.daos;

import fr.animalia.modeles.Animal;
import fr.animalia.modeles.Refuge;
import java.util.List;

/**
 * La DAO qui gère les objets de type Refuge
 * @author Philippe CORTEZ
 */
public class RefugeDAO extends DAOGenerique<Refuge>
{
    /**
     * Recherche les refuges par adresse
     * @param adresse l'adresse a recherche
     * @return les refuges à cette adresse
     */
    public List<Refuge> rechercherParAdresse(String adresse)
    {
        // Evite la casse
        adresse = adresse.toUpperCase();

        return entityManager.createQuery("select refuge from Refuge refuge where refuge.adresse = :adresse", Refuge.class)
                .setParameter("adresse", adresse)
                .getResultList();
    }

    /**
     * Recherche les animaux d'un refuge en particulier
     * @param nomRefuge le nom du refuge (unique)
     * @return les animaux de ce refuge
     */
    public List<Animal> rechercherParRefuge(String nomRefuge)
    {
        // Evite la sensibilite a la casse
        nomRefuge = nomRefuge.toUpperCase();

        // Recupere les animaux qui sont dans le refuge dont les noms est donne et dont leur fin de sejour n'est pas acte
        return entityManager.createQuery("select animal from Animal animal where animal.id in " +
                        "(select info.pensionnaire.id from InformationSejour info where info.refuge.nom = :nomRefuge and info.refugeActuel = true)", Animal.class)
                .setParameter("nomRefuge", nomRefuge)
                .getResultList();
    }

}
