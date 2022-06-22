package fr.animalia.bdds.daos;

import fr.animalia.modeles.Soin;

import java.util.List;

/**
 * La DAO qui gère les objets de type Soin
 * @author Philippe CORTEZ
 */
public class SoinDAO extends DAOGenerique<Soin>
{
    /**
     * Recherche les soins comprit dans une fourchette de prix
     * @param prixBas prix le plus bas (borne début)
     * @param prixHaut prix le plus haut (borne de fin)
     * @return la liste des soins dans la fourchette de prix
     */
    public List<Soin> rechercherParPrix(float prixBas, float prixHaut)
    {
        return entityManager.createQuery("select soin from Soin soin where soin.prix between :prixBas and :prixHaut", Soin.class)
                .setParameter("prixBas", prixBas)
                .setParameter("prixHaut", prixHaut)
                .getResultList();
    }

    /**
     * Recherche les soins correspondants à un prix exact
     * @param prix le prix exact
     * @return la liste des soins qui coute ce prix
     */
    public List<Soin> rechercherParPrix(float prix)
    {
        return rechercherParPrix(prix, prix);
    }

}
