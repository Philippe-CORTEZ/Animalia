package fr.animalia.serveurs.ressourcesrest;

import fr.animalia.bdds.daos.AnimalDAO;
import fr.animalia.modeles.Animal;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;


/**
 * Gère les ressources de la classe Animal
 * @author Philippe CORTEZ
 */
@Path("animaux")
public class AnimalRessource extends RessourceGenerique<Animal>
{
    /** Utilise une Animal en type déclaré pour les actions propres à cette ressource */
    private final AnimalDAO animalDAO;


    /** Utilise la DAO Animal pour les méthodes de la classe mère */
    public AnimalRessource()
    {
        this.dao = new AnimalDAO();
        this.animalDAO = new AnimalDAO();
    }


    /**
     * Récupère les animaux d'une même espèce
     * @param espece l'espèce en question
     * @return la liste des animaux de cette espèce
     */
    @GET
    @Path("especes/{typeEspece: [a-zA-Z]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> recupererParEspece(@PathParam("typeEspece") String espece)
    {
        return animalDAO.rechercherParEspece(espece);
    }

    /**
     * Récupère les animaux notés "SOS"
     * @return la liste des animaux en "SOS"
     */
    @GET
    @Path("sos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> recupererParSOS()
    {
        return animalDAO.rechercherParSOS();
    }

}
