package com.sdzee.form;

import static com.sdzee.form.FormUtil.getParam;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sdzee.beans.Client;
import com.sdzee.beans.Commande;
import com.sdzee.dao.ClientDao;

public class CreationCommandeForm {

    private static final String CHAMP_DATE                 = "date";
    private static final String CHAMP_MONTANT              = "montant";
    private static final String CHAMP_MODE_PAIEMENT        = "modePaiement";
    private static final String CHAMP_STATUT_PAIEMENT      = "statutPaiement";
    private static final String CHAMP_MODE_LIVRAISON       = "modeLivraison";
    private static final String CHAMP_STATUT_LIVRAISON     = "statutlivraison";

    private static final String ANCIEN_CLIENT              = "ancienClient";

    private static final String SESSION_CLIENT             = "listClient";

    private static final String CHAMP_CHOIX_NOUVEAU_CLIENT = "choixNouveauClient";
    private static final String CHAMP_ID_ANCIEN_CLIENT     = "nomAncienClient";

    private static final String DATE_FORMAT                = "dd/MM/yyyy HH:mm:ss";

    private Map<String, String> mapErreur                  = new HashMap<String, String>();
    private ClientDao           clientDao;

    public CreationCommandeForm( ClientDao clientDao ) {

        this.clientDao = clientDao;
    }

    public Commande creerCommande( HttpServletRequest request ) {
        Client client = null;
        CreationClientForm formClient = new CreationClientForm( clientDao );

        String choixNouveauClient = getParam( request, CHAMP_CHOIX_NOUVEAU_CLIENT );
        if ( choixNouveauClient != null && choixNouveauClient.equals( ANCIEN_CLIENT ) ) {

            String idAncienClient = getParam( request, CHAMP_ID_ANCIEN_CLIENT );
            if ( idAncienClient != null ) {
                Long idClient = Long.parseLong( idAncienClient );
                HttpSession session = request.getSession();
                Map<Long, Client> listClientsMap = (Map<Long, Client>) session.getAttribute( SESSION_CLIENT );
                client = listClientsMap.get( idClient );
            }

        } else {
            client = formClient.creerClient( request );
            mapErreur = formClient.getMapErreur();
        }

        Commande commande = new Commande();

        String date;

        String montant = getParam( request, CHAMP_MONTANT );
        double montantValeur = -1;
        String modePaiement = getParam( request, CHAMP_MODE_PAIEMENT );
        String statutPaiement = getParam( request, CHAMP_STATUT_PAIEMENT );
        String modeLivraison = getParam( request, CHAMP_MODE_LIVRAISON );
        String statutLivraison = getParam( request, CHAMP_STATUT_LIVRAISON );

        // On ne récupere pas la date
        DateTime dt = new DateTime();

        DateTimeFormatter fmt = DateTimeFormat.forPattern( DATE_FORMAT );
        date = fmt.print( dt );

        try {
            montantValeur = verifMontant( montant );
        } catch ( Exception e ) {
            mapErreur.put( CHAMP_MONTANT, e.getMessage() );
        }

        try {
            verifModePaiement( modePaiement );
        } catch ( Exception e ) {
            mapErreur.put( CHAMP_MODE_PAIEMENT, e.getMessage() );
        }

        try {
            verifStatutPaiement( statutPaiement );
        } catch ( Exception e ) {
            mapErreur.put( CHAMP_STATUT_PAIEMENT, e.getMessage() );
        }

        try {
            verifModeLivraison( modeLivraison );
        } catch ( Exception e ) {
            mapErreur.put( CHAMP_MODE_LIVRAISON, e.getMessage() );
        }

        try {
            verifStatutLivraison( statutLivraison );
        } catch ( Exception e ) {
            mapErreur.put( CHAMP_STATUT_LIVRAISON, e.getMessage() );
        }

        commande.setDate( date );
        commande.setModeLivraison( modeLivraison );
        commande.setModePaiement( modePaiement );
        commande.setMontant( montant );
        commande.setStatutLivraison( statutLivraison );
        commande.setStatutPaiement( statutPaiement );
        commande.setClient( client );

        return commande;
    }

    private double verifMontant( String montant ) throws Exception {
        double temp;

        if ( montant != null ) {
            try {
                temp = Double.parseDouble( montant );
                if ( temp < 0 ) {
                    temp = -1;
                    throw new Exception( "Le montant doit etre positif" );
                }
            } catch ( NumberFormatException e ) {
                temp = -1;
                throw new Exception( "Le montant doit etre numérique" );
            }

        } else {
            temp = -1;
            throw new Exception( "Merci de saisir un montant" );
        }

        return temp;

    }

    private void verifModePaiement( String mode ) throws Exception {
        if ( mode == null ) {
            throw new Exception( "Merci de renseigner un mode de paiement" );
        } else if ( mode.length() < 2 ) {
            throw new Exception( "Le mode de paiement doit contenir au moins 2 caracteres" );
        }

    }

    private void verifModeLivraison( String mode ) throws Exception {
        if ( mode == null ) {
            throw new Exception( "Merci de renseigner un mode de livraison" );
        } else if ( mode.length() < 2 ) {
            throw new Exception( "Le mode de livraison doit contenir au moins 2 caracteres" );
        }

    }

    private void verifStatutPaiement( String statut ) throws Exception {
        if ( statut != null && statut.length() < 2 ) {
            throw new Exception( "Le statut de paiement doit contenir au moins 2 caracteres" );
        }
    }

    private void verifStatutLivraison( String statut ) throws Exception {
        if ( statut != null && statut.length() < 2 ) {
            throw new Exception( "Le statut de livraison doit contenir au moins 2 caracteres" );
        }
    }

    public Map<String, String> getMapErreur() {
        return mapErreur;
    }

}
