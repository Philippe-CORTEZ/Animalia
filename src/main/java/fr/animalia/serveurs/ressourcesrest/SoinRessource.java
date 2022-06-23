package fr.animalia.serveurs.ressourcesrest;

import fr.animalia.bdds.daos.SoinDAO;
import fr.animalia.modeles.Soin;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * Gère les ressources de la classe Soin
 * @author Philippe CORTEZ
 */
@Path("soins")
public class SoinRessource extends RessourceGenerique<Soin>
{
    /** Utilise une SoinDAO en type déclaré pour les actions propres à cette ressource */
    private final SoinDAO soinDAO;


    /** Utilise la DAO de Soin pour les méthodes de la classe mère */
    public SoinRessource()
    {
        this.dao = new SoinDAO();
        this.soinDAO = new SoinDAO();
    }


    /**
     * Récupère les soins comprit dans une fourchette de prix
     * @param prixBas prix le plus bas (borne début)
     * @param prixHaut prix le plus haut (borne de fin)
     * @return la liste des soins dans la fourchette de prix
     */
    @GET
    @Path("prix")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Soin> recupererParPrix(@QueryParam("prixBas") float prixBas, @QueryParam("prixHaut") float prixHaut)
    {
        return soinDAO.rechercherParPrix(prixBas, prixHaut);
    }
    
}
