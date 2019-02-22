package com.sdzee.form;

import static com.sdzee.form.FormUtil.getParam;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.Client;
import com.sdzee.dao.ClientDao;

public class CreationClientForm {
    private static final String CHAMP_NOM       = "nom";
    private static final String CHAMP_PRENOM    = "prenom";
    private static final String CHAMP_ADRESSE   = "adresse";
    private static final String CHAMP_TELEPHONE = "telephone";
    private static final String CHAMP_EMAIL     = "email";

    private Map<String, String> mapErreur       = new HashMap<String, String>();
    private ClientDao           dao;

    public CreationClientForm( ClientDao dao ) {

        this.dao = dao;
    }

    public Client creerClient( HttpServletRequest request ) {

        Client client = new Client();

        String nomClient = getParam( request, CHAMP_NOM );
        String prenomClient = getParam( request, CHAMP_PRENOM );
        String adresseClient = getParam( request, CHAMP_ADRESSE );
        String telephoneClient = getParam( request, CHAMP_TELEPHONE );
        String emailClient = getParam( request, CHAMP_EMAIL );

        try {
            verifNom( nomClient );
        } catch ( Exception e ) {
            mapErreur.put( CHAMP_NOM, e.getMessage() );
        }

        try {
            verifPrenom( prenomClient );
        } catch ( Exception e ) {
            mapErreur.put( CHAMP_PRENOM, e.getMessage() );
        }

        try {
            verifAdresse( adresseClient );
        } catch ( Exception e ) {
            mapErreur.put( CHAMP_ADRESSE, e.getMessage() );
        }

        try {
            verifTelephone( telephoneClient );
        } catch ( Exception e ) {
            mapErreur.put( CHAMP_TELEPHONE, e.getMessage() );
        }

        try {
            verifEmail( emailClient );
        } catch ( Exception e ) {
            mapErreur.put( CHAMP_EMAIL, e.getMessage() );
        }

        client.setNom( nomClient );
        client.setPrenom( prenomClient );
        client.setAdresse( adresseClient );
        client.setTelephone( telephoneClient );
        client.setEmail( emailClient );

        if ( mapErreur.isEmpty() ) {
            dao.creerClient( client );
        }

        return client;

    }

    private static void verifNom( String nom ) throws Exception {
        if ( nom == null ) {
            throw new Exception( "Merci de renseigner un Nom" );

        } else if ( nom.length() < 2 ) {
            throw new Exception( "Le nom doit contenir au moins 2 caracteres" );
        }

    }

    private static void verifPrenom( String prenom ) throws Exception {
        if ( prenom != null && prenom.length() < 2 ) {
            throw new Exception( "Le prénom doit contenir au moins 2 caracteres" );

        }

    }

    private static void verifAdresse( String adresse ) throws Exception {
        if ( adresse == null ) {
            throw new Exception( "Merci de renseigner un Nom" );

        } else if ( adresse.length() < 10 ) {
            throw new Exception( "Le nom doit contenir au moins 10 caracteres" );
        }

    }

    private static void verifTelephone( String tel ) throws Exception {
        if ( tel == null ) {
            throw new Exception( "Merci de renseigner un Numéro de téléphone" );

        } else if ( !tel.matches( "^\\d+$" ) ) {
            throw new Exception( "Le numéro de telephone doit contenir des caracteres numériques " );
        } else if ( tel.length() < 4 || tel.length() > 10 ) {
            throw new Exception( "Le numéro de telephone doit contenir entre 4 et 10 caracteres" );
        }
    }

    private static void verifEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Le format de l'adresse mail est incorect" );

        }

    }

    public Map<String, String> getMapErreur() {
        return mapErreur;
    }
}
