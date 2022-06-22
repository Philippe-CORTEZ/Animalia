package fr.animalia.serveurs.ressourcesrest;

import java.util.List;

/**
 * Toutes ressources REST implante à minima les méthodes de cette interface
 * @author Philippe CORTEZ
 * @param <R> type générique pour les ressources
 */
public interface Ressource<R>
{
    /**
     * Recherche et récupère une ressource par son id unique
     * @param id l'id de la ressource
     * @return une ressource avec le media type adéquat (JSON)
     */
    R recupererRessourceParId(long id);

    /**
     * Recherche et récupère un ensemble de ressources
     * @return une liste de ressources avec le media type adéquat (JSON)
     */
    List<R> recupererToutesRessources();

    /**
     * Recherche et récupère une ressource par son nom
     * @param nom le nom de la ressource
     * @return une ressource avec le media type adéquat (JSON)
     */
    List<R> recupererRessourceParNom(String nom);

    /**
     * Créer une ressource et l'insère en base de données
     * @param ressource une ressource avec le media type adéquat
     * @return La ressource insérée
     */
    R insererRessource(R ressource);

    /**
     * Supprime une ressource par son id en base de données
     * @param id l'id de la ressource à supprimer
     */
    void supprimerRessourceParId(long id);

    /**
     * Met à jour l'entièreté d'une ressource spécifiée
     * @param ressource une ressource avec le media type adéquat
     * @return la ressource à jour avec le media type adéquat
     */
    R miseAJourRessource(R ressource);

}
