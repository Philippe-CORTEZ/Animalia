package fr.animalia.serveurs.ressourcesrest;

import fr.animalia.bdds.daos.RefugeDAO;
import fr.animalia.modeles.Refuge;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * Gère les ressources de la classe Refuge
 * @author Philippe CORTEZ
 */
@Path("refuges")
public class RefugeRessource extends RessourceGenerique<Refuge>
{
    /** Utilise une RefugeDAO en type déclaré pour les actions propres à cette ressource */
    private final RefugeDAO refugeDAO;


    /** Utilise la DAO de Refuge pour les méthodes de la classe mère */
    public RefugeRessource()
    {
        this.dao = new RefugeDAO();
        this.refugeDAO = new RefugeDAO();
    }


    /**
     * Récupère les refuges par adresse
     * @param adresse l'adresse a recherche
     * @return les refuges à cette adresse
     */
    @GET
    @Path("adresses/{localisation: [a-zA-Z0-9_ -]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Refuge> recupererParAdresse(@PathParam("localisation") String adresse)
    {
        return refugeDAO.rechercherParAdresse(adresse);
    }

}
