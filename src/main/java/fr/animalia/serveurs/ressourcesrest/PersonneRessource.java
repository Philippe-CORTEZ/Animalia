package fr.animalia.serveurs.ressourcesrest;

import fr.animalia.bdds.daos.PersonneDAO;
import fr.animalia.modeles.Personne;
import jakarta.ws.rs.Path;

/**
 * Gère les ressources de la classe Personne
 * @author Philippe CORTEZ
 */
@Path("personnes")
public class PersonneRessource extends RessourceGenerique<Personne>
{
    /** Utilise la DAO de Personne pour les méthodes de la classe mère */
    public PersonneRessource()
    {
        this.dao = new PersonneDAO();
    }

}
