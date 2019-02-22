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
import com.sdzee.dao.ClientDao;
import com.sdzee.dao.DAOFactory;
import com.sdzee.form.FormUtil;

/**
 * Servlet implementation class SuppressionClient
 */

public class SuppressionClient extends HttpServlet {
    private static final long   serialVersionUID = 1L;

    private static final String PARAM_ID_CLIENT  = "nomClient";
    private static final String SESSION_CLIENT   = "listClient";
    private static final String SESSION_COMMANDE = "listCommande";

    private static final String CONF_FACTORY_DAO = "dao_factory";

    private static final String VUE              = "/listeClients";

    private ClientDao           daoClient;

    @Override
    public void init() throws ServletException {
        this.daoClient = ( (DAOFactory) this.getServletContext().getAttribute( CONF_FACTORY_DAO ) ).getClientDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Map<Long, Client> listClient = (Map<Long, Client>) session.getAttribute( SESSION_CLIENT );
        Map<String, Commande> listCommandeMap = (Map<String, Commande>) session.getAttribute( SESSION_COMMANDE );

        String valueParam = FormUtil.getParam( request, PARAM_ID_CLIENT );
        if ( valueParam != null && !listClient.isEmpty() ) {
            Long idClient = Long.parseLong( valueParam );
            daoClient.supprimerClientParID( idClient );

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
