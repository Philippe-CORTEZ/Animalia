package fr.animalia.clients;

import jakarta.ws.rs.client.WebTarget;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe utilisée pour manipuler un client REST
 * @author Philippe CORTEZ
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientREST
{
    @Getter
    @Setter
    private static WebTarget webRessource;
}
