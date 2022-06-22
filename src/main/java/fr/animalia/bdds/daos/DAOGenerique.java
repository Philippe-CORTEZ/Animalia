package fr.animalia.bdds.daos;

import fr.animalia.modeles.Entite;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * La DAO générique décrit les opérations usuelles qu'ont toutes les DAOs
 * @param <E> le type générique des entités
 * @author Philippe CORTEZ
 */
public abstract class DAOGenerique<E extends Entite> implements DAO<E>
{
    /** Le nom de l'unité de persistance (décrit dans persistence.xml) */
    // Cette facon de faire est correcte que si toutes les DAOs heritees de cette classe travaillent sur la meme unite de persistance
    private static final String NOM_UNITEE_PERSISTANCE = "Animalia";


    /** L'entity manager pour gérer les actions avec la base de données */
    protected final EntityManager entityManager;

    /** Le gestionnaire de transaction */
    protected final EntityTransaction transaction;

    /** Sert à stocker le type réel de la classe (évite de le spécifié dans les requêtes de recherches) */
    protected final Class<E> type;



    /** Constructeur de la DAOGenerique */
    protected DAOGenerique()
    {
        // Definit l'entity manager et la transaction
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(NOM_UNITEE_PERSISTANCE);
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();

        // Recupere le type reel de la classe heritant de DAOGenerique
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.type = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }


    /**
     * Persiste l'entité en base de données et la renvoie
     * Equivalent au persist de l'entity manager
     * @param entite l'entité à persister
     * @return un objet de type E
     */
    @Override
    public E persister(E entite)
    {
        // Insertion de l'entite en base de donnees
        transaction.begin();
        entityManager.persist(entite);
        transaction.commit();

        // Mise a jour de l'entitee de base par rapport a l'insertion faite (pour les id auto generes)
        entityManager.refresh(entite);
        return entite;
    }

    /**
     * Renvoie une entité dont l'id sont spécifiée
     * Equivalent au find de l'entity manager
     * @param id l'identifiant l'entité à récupérer
     * @return un objet de type E
     */
    @Override
    public E rechercher(long id)
    {
        return entityManager.find(type, id);
    }

    /**
     * Rafraichit l'entité spécifiée avec les données de la base de données
     * Equivalent au refresh de l'entity manager
     * @param entite l'entité à rafraîchir
     * @return un objet de type E
     */
    @Override
    public E rafraichir(E entite)
    {
        // rafraichit l'entite grace a base de donnees et renvoie l'instance
        entityManager.refresh(entite);
        return entite;
    }

    /**
     * Supprime une entité en base de données
     * @param entite l'entité à supprimer
     */
    @Override
    public void supprimer(E entite)
    {
        transaction.begin();
        entityManager.remove(entite);
        transaction.commit();
    }

    /**
     * Met à jour l'entité en base de données
     * Execute un merge dans une transaction, ce qui à pour conséquence
     * Que toute modification sera capté et traité par l'entity manager
     * @param entite l'entité qui sera mise à jour en base de données
     */
    @Override
    public void miseAJour(E entite)
    {
        // A note qu'a la base merge sert a demander de gerer une entite detachee
        // Elle peut aussi servir dans cette configuration a capter le moindre changement
        // Pour mettre directement a jour la base de donnees
        transaction.begin();
        entityManager.merge(entite);
        transaction.commit();
    }

    /**
     * Demande a l'entity manager de gérer l'entité donnée
     * Equivalent au merge de l'entity manager
     * Le nom est laissé tel quel
     * @param entite l'entité a géré
     * @return une entité ressemblante, mais qui elle sera géré par l'entity manager
     */
    @Override
    public E merge(E entite)
    {
        return entityManager.merge(entite);
    }

    /**
     * Récupère toutes les entités d'une relation en particulier
     * @return une liste d'entités
     */
    @Override
    public List<E> rechercherTout()
    {
        // Utilisation des criteria query pour rendre la methode generique (sans avoir a passer de parametre)
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(type);
        Root<E> rootEntry = cq.from(type);
        CriteriaQuery<E> all = cq.select(rootEntry);

        TypedQuery<E> allQuery = entityManager.createQuery(all);

        return  allQuery.getResultList();
    }

    /**
     * Récupère toutes les entités d'un type particulier avec le nom voulu
     * Ceci est possible, car toutes les entités possible de récupérer possèdent bien un champ nom
     * @param nom le nom recherché
     * @return une liste d'entités
     */
    public List<E> rechercherParNom(String nom)
    {
        // Met le nom en uppercase pour normaliser pour eviter les problemes de sensibilite a la casse
        nom = nom.toUpperCase();

        // S'inspire de la methode rechercherTout mais en utilisant une clause where
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(type);
        Root<E> rootEntry = cq.from(type);
        CriteriaQuery<E> all = cq.select(rootEntry).where(cb.like(rootEntry.get("nom"), nom));

        TypedQuery<E> nameQuery = entityManager.createQuery(all);

        return  nameQuery.getResultList();
    }

}
