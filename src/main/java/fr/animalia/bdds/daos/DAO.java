package fr.animalia.bdds.daos;

import java.util.List;

/**
 * Une classe qui implante DAO possèdent à minima les opérations CRUD de bases défini plus bas
 * @author Philippe CORTEZ
 * @param <E> type générique
 */
public interface DAO<E>
{
    /**
     * Persiste l'entité en base de données et la renvoie
     * Equivalent au persist de l'entity manager
     * @param entite l'entité à persister
     * @return un objet de type E
     */
    E persister(E entite);

    /**
     * Renvoie une entité dont l'id est spécifiée
     * Equivalent au find de l'entity manager
     * @param id l'identifiant l'entité à récupérer
     * @return un objet de type E
     */
    E rechercher(long id);

    /**
     * Rafraichit l'entité spécifiée avec les données de la base de données
     * Equivalent au refresh de l'entity manager
     * @param entite l'entité à rafraîchir
     * @return un objet de type E
     */
    E rafraichir(E entite);

    /**
     * Supprime une entité de la base de données
     * Equivalent au remove de l'entity manager
     * @param entite l'entité à supprimer
     */
    void supprimer(E entite);

    /**
     * Met à jour l'entité en base de données
     * Execute un merge dans une transaction, ce qui à pour conséquence
     * Que toute modification sera capté et traité par l'entity manager
     * @param entite l'entité qui sera mise à jour en base de données
     */
    void miseAJour(E entite);

    /**
     * Demande a l'entity manager de gérer l'entité donnée
     * Equivalent au merge de l'entity manager
     * Le nom est laissé tel quel
     * @param entite l'entité a géré
     * @return une entité ressemblante, mais qui elle sera géré par l'entity manager
     */
    E merge(E entite);

    /**
     * Récupère toutes les entités d'un type en particulier
     * @return une liste d'entités
     */
    List<E> rechercherTout();

    /**
     * Récupère toutes les entités d'un type particulier avec le nom voulu
     * Ceci est possible, car toutes les entités possible de récupérer possèdent bien un champ nom
     * @param nom le nom recherché
     * @return une liste d'entités
     */
    public List<E> rechercherParNom(String nom);

}
