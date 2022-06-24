package fr.animalia.controleurs;

/**
 * Contrôleur utiliser par l'interface d'authentification
 * @author Philippe CORTEZ
 */
public class AuthentificationControleur implements Controleur
{
    /** Lorsque qu'un clic sur le bouton "connexion est fait", se connecte à un compte personnel */
    public void connexion()
    {
        // Pour l'instant se contente de charger la fenetre suivante
        // Utilisateur par defaut : un gestionnaire
        changerFXML("vues/MenuPrincipal.fxml");
    }

}
