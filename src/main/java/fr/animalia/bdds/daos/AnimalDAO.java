package fr.animalia.bdds.daos;

import fr.animalia.modeles.Animal;

import java.util.List;

/**
 * La DAO qui gère les objets de type Animal
 * @author Philippe CORTEZ
 */
public class AnimalDAO extends DAOGenerique<Animal>
{
    /**
     * Recherche les animaux d'une même espèce
     * @param espece l'espèce en question
     * @return la liste des animaux de cette espèce
     */
    public List<Animal> rechercherParEspece(String espece)
    {
        // Evite la casse
        espece = espece.toUpperCase();

        return entityManager.createQuery("select animal from Animal animal where animal.espece = :espece", Animal.class)
                .setParameter("espece", espece)
                .getResultList();
    }

    /**
     * Recherche les animaux notés "SOS"
     * @return la liste des animaux en "SOS"
     */
    public List<Animal> rechercherParSOS()
    {
        return entityManager.createQuery("select animal from Animal animal where animal.sos = true", Animal.class)
                .getResultList();
    }

}
