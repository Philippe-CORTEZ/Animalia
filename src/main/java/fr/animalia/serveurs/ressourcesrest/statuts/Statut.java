package fr.animalia.serveurs.ressourcesrest.statuts;

import jakarta.ws.rs.NameBinding;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Permet de choisir le code de retour des réponses HTTP
 * @author Philippe CORTEZ
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Statut
{
    int CREATED = 201;
    int ACCEPTED = 202;
    int NO_CONTENT = 204;
    int RESET_CONTENT = 205;
    int PARTIAL_CONTENT = 206;

    int value();
}
