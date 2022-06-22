package fr.animalia.serveurs.ressourcesrest.statuts;


import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;

/**
 * Si une réponse HTTP renvoie OK 200, elle se retrouve potentiellement filtré
 * Si la méthode est annotée avec @Statut pour renvoyer une réponse plus appropriée
 * @author Philippe CORTEZ
 */
@Provider
public class StatutFiltre implements ContainerResponseFilter
{
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext)
    {
        if (containerResponseContext.getStatus() == Response.Status.OK.getStatusCode())
        {
            for (Annotation annotation : containerResponseContext.getEntityAnnotations())
            {
                if (annotation instanceof Statut)
                {
                    containerResponseContext.setStatus(((Statut) annotation).value());
                    break;
                }
            }
        }
    }

}
