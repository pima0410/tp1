package com.sdzee.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Client;
import com.sdzee.beans.Commande;
import com.sdzee.dao.ClientDao;
import com.sdzee.dao.DAOFactory;
import com.sdzee.form.CreationCommandeForm;

public class CreationCommande extends HttpServlet {

    private static final String ATT_COMMANDE          = "commande";
    private static final String ATT_ERRREUR           = "erreur";
    private static final String SESSION_CLIENT        = "listClient";
    private static final String SESSION_COMMANDE      = "listCommande";

    private static final String CONF_DAO_FACTORY      = "dao_factory";

    private static final String VUE_AFFICHER_COMMANDE = "/WEB-INF/afficherCommande.jsp";
    private static final String VUE_FORM_COMMANDE     = "/WEB-INF/creationCommande.jsp";

    private ClientDao           daoClient;

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        /*
         * request.setAttribute( ATT_COMMANDE, commande ); request.setAttribute(
         * ATT_MESSAGE, message ); request.setAttribute( ATT_ERRREUR, isError );
         */
        // request.setAttribute( ATT_CLIENT, client );

        this.getServletContext().getRequestDispatcher( VUE_FORM_COMMANDE ).forward( request, response );

    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        CreationCommandeForm form = new CreationCommandeForm( daoClient );
        Commande commande = form.creerCommande( request );
        Map<Long, Client> listClient = new HashMap<Long, Client>();
        Map<String, Commande> listCommande = new HashMap<String, Commande>();

        if ( form.getMapErreur().isEmpty() ) {

            HttpSession session = request.getSession();
            listClient = (Map<Long, Client>) session.getAttribute( SESSION_CLIENT );
            if ( listClient == null ) {
                listClient = new HashMap<Long, Client>();
            }

            listClient.put( commande.getClient().getId(), commande.getClient() );
            session.setAttribute( SESSION_CLIENT, listClient );

            listCommande = (Map<String, Commande>) session.getAttribute( SESSION_COMMANDE );
            if ( listCommande == null ) {
                listCommande = new HashMap<String, Commande>();
            }

            listCommande.put( commande.getDate(), commande );

            session.setAttribute( SESSION_COMMANDE, listCommande );

        }

        request.setAttribute( ATT_COMMANDE, commande );

        request.setAttribute( ATT_ERRREUR, form.getMapErreur() );

        if ( form.getMapErreur().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( VUE_AFFICHER_COMMANDE ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_FORM_COMMANDE ).forward( request, response );
        }

    }

    @Override
    public void init() throws ServletException {

        daoClient = ( (DAOFactory) this.getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getClientDao();

    }

}
