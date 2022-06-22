package fr.animalia.serveurs.ressourcesrest;

import fr.animalia.bdds.daos.SoinDAO;
import fr.animalia.modeles.Soin;
import jakarta.ws.rs.Path;

/**
 * Gère les ressources de la classe Soin
 * @author Philippe CORTEZ
 */
@Path("soins")
public class SoinRessource extends RessourceGenerique<Soin>
{
    /** Utilise la DAO de Soin pour les méthodes de la classe mère */
    public SoinRessource()
    {
        this.dao = new SoinDAO();
    }

}
