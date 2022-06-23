package fr.animalia.serveurs.ressourcesrest;

import fr.animalia.bdds.daos.PersonneDAO;
import fr.animalia.modeles.Personne;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * Gère les ressources de la classe Personne
 * @author Philippe CORTEZ
 */
@Path("personnes")
public class PersonneRessource extends RessourceGenerique<Personne>
{
    /** Utilise une PersonneDAO en type déclaré pour les actions propres à cette ressource */
    private final PersonneDAO personneDAO;


    /** Utilise la DAO de Personne pour les méthodes de la classe mère */
    public PersonneRessource()
    {
        this.dao = new PersonneDAO();
        this.personneDAO = new PersonneDAO();
    }


    /**
     * Récupère une personne par son nom ET prénom
     * @param nom le nom de la personne
     * @param prenom le prénom de la personne
     * @return les personnes avec ce nom et prénom
     */
    @GET
    @Path("{nom: [a-zA-Z]+}/{prenom: [a-zA-Z]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> recupererParNomPrenom(@PathParam("nom") String nom, @PathParam("prenom") String prenom)
    {
        return personneDAO.rechercherParNomPrenom(nom, prenom);
    }

}
