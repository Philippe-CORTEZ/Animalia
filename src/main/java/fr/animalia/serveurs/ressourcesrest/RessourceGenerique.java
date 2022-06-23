package fr.animalia.serveurs.ressourcesrest;

import fr.animalia.bdds.daos.DAO;
import fr.animalia.modeles.Entite;
import fr.animalia.serveurs.ressourcesrest.statuts.Statut;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * Classe générique qui factorise le plus de comportement possible entre les différents types de ressource REST
 * Cette classe à besoin d'un DAO que les classes filles doivent spécifier
 * @author Philippe CORTEZ
 * @param <E> type générique
 */
public abstract class RessourceGenerique<E extends Entite> implements Ressource<E>
{
    /**
     * Le type réel de cette DAO doit être défini dans les classes filles
     * Si cette DAO est de type reel pathologie, il ne sera possible que de travailler qu'avec des pathologies
     * Cependant type déclaré oblige, cette DAO se limite aux opérations de bases de l'interface DAO
     */
    protected DAO<E> dao;


    /**
     * Recherche et récupère une ressource par son id unique
     * @param id l'id de la ressource
     * @return une ressource avec le media type adéquat (JSON)
     */
    @Override
    @GET
    @Path("{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public E recupererRessourceParId(@PathParam("id") long id)
    {
        return dao.rechercher(id);
    }

    /**
     * Recherche et récupère un ensemble de ressources
     * @return une liste de ressources avec le media type adéquat (JSON)
     */
    @Override
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<E> recupererToutesRessources()
    {
        return dao.rechercherTout();
    }

    /**
     * Recherche et récupère une ressource par son nom
     * @param nom le nom de la ressource
     * @return une ressource avec le media type adéquat (JSON)
     */
    @Override
    @GET
    @Path("{nom: [a-zA-Z]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<E> recupererRessourceParNom(@PathParam("nom") String nom)
    {
        return dao.rechercherParNom(nom);
    }

    /**
     * Créer une ressource et l'insère en base de données
     * @param ressource une ressource avec le media type adéquat
     * @return La ressource insérée
     */
    @Override
    @POST
    @Statut(Statut.CREATED)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public E insererRessource(E ressource)
    {
        // Besoin de merge avant de persist car si la ressource a des attributs qui sont des entites deja existantes en bdd
        // Il faut que l'entity manager en prenne connaissance (pour eviter une erreur de type violation cle primaire)
        ressource = dao.merge(ressource);
        return dao.persister(ressource);
    }

    /**
     * Supprime une ressource par son id en base de données
     * @param id l'id de la ressource à supprimer
     */
    @Override
    @DELETE
    @Statut(Statut.NO_CONTENT)
    @Path("{id: [0-9]+}")
    public void supprimerRessourceParId(@PathParam("id") long id)
    {
        E ressource = dao.rechercher(id);
        if(ressource != null)
            dao.supprimer(ressource);
    }

    /**
     * Met à jour l'entièreté d'une ressource spécifiée
     * @param ressource une ressource avec le media type adéquat
     * @param id l'id de la ressource à mettre à jour
     * @return la ressource à jour avec le media type adéquat
     */
    @Override
    @PUT
    @Path("{id: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public E miseAJourRessource(E ressource, @PathParam("id") long id)
    {
        // Recherche si la ressource specifie existe bien
        E rsc = dao.rechercher(id);
        if(rsc != null)
        {
            // Dans le cas ou elle existe, mise a jour complete et retourne la ressource a jour
            dao.miseAJour(ressource);
            return ressource;
        }

        // Cas ou la ressource a mettre a jour n'existait pas, renvoie juste null
        return null;
    }

}
