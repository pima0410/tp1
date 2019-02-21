package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Client;
import com.sdzee.beans.Commande;

/**
 * Servlet implementation class SuppressionClient
 */

public class SuppressionClient extends HttpServlet {
    private static final long   serialVersionUID = 1L;

    private static final String PARAM_NOM_CLIENT = "nomClient";
    private static final String SESSION_CLIENT   = "listClient";
    private static final String SESSION_COMMANDE = "listCommande";

    private static final String VUE              = "/listeClients";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Map<String, Client> listClient = (Map<String, Client>) session.getAttribute( SESSION_CLIENT );
        Map<String, Commande> listCommandeMap = (Map<String, Commande>) session.getAttribute( SESSION_COMMANDE );

        String valueParam = request.getParameter( PARAM_NOM_CLIENT );
        if ( valueParam != null && valueParam.trim().length() > 0 && !listClient.isEmpty() ) {
            listClient.remove( valueParam );
            session.setAttribute( SESSION_CLIENT, listClient );

            if ( !listCommandeMap.isEmpty() ) {
                List<String> listeDateToDelete = new ArrayList<String>();

                for ( Entry<String, Commande> commande : listCommandeMap.entrySet() ) {
                    if ( commande.getValue().getClient().getNom().equals( valueParam ) ) {
                        listeDateToDelete.add( commande.getValue().getDate() );
                    }

                }
                for ( String dateString : listeDateToDelete ) {
                    listCommandeMap.remove( dateString );
                }
                session.setAttribute( SESSION_COMMANDE, listCommandeMap );
            }

        }

        response.sendRedirect( request.getContextPath() + VUE );

        // TODO Auto-generated method stub

    }

}
