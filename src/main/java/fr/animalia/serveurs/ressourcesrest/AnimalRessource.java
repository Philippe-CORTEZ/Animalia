package fr.animalia.serveurs.ressourcesrest;

import fr.animalia.bdds.daos.AnimalDAO;
import fr.animalia.modeles.Animal;
import jakarta.ws.rs.Path;


/**
 * Gère les ressources de la classe Animal
 * @author Philippe CORTEZ
 */
@Path("animaux")
public class AnimalRessource extends RessourceGenerique<Animal>
{
    /** Utilise la DAO Animal pour les méthodes de la classe mère */
    public AnimalRessource()
    {
        this.dao = new AnimalDAO();
    }

}
