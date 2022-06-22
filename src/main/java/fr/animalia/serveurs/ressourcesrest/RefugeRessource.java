package fr.animalia.serveurs.ressourcesrest;

import fr.animalia.bdds.daos.RefugeDAO;
import fr.animalia.modeles.Refuge;
import jakarta.ws.rs.Path;

/**
 * Gère les ressources de la classe Refuge
 * @author Philippe CORTEZ
 */
@Path("refuges")
public class RefugeRessource extends RessourceGenerique<Refuge>
{
    /** Utilise la DAO de Refuge pour les méthodes de la classe mère */
    public RefugeRessource()
    {
        this.dao = new RefugeDAO();
    }

}
