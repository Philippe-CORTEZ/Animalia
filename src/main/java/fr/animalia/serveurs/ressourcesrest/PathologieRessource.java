package fr.animalia.serveurs.ressourcesrest;

import fr.animalia.bdds.daos.PathologieDAO;
import fr.animalia.modeles.Pathologie;
import jakarta.ws.rs.Path;

/**
 * Gère les ressources de la classe Pathologie
 * @author Philippe CORTEZ
 */
@Path("pathologies")
public class PathologieRessource extends RessourceGenerique<Pathologie>
{
    /** Utilise la DAO de Pathologie pour les méthodes de la classe mère */
    public PathologieRessource()
    {
        this.dao = new PathologieDAO();
    }

}
