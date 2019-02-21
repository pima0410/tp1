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
import com.sdzee.dao.ClientDao;
import com.sdzee.dao.DAOFactory;
import com.sdzee.form.CreationClientForm;

public class CreationClient extends HttpServlet {

    private static final String ATT_CLIENT          = "client";

    private static final String ATT_ERRREUR         = "erreur";
    private static final String SESSION_CLIENT      = "listClient";

    private static final String VUE_AFFICHER_CLIENT = "/WEB-INF/afficherClient.jsp";
    private static final String VUE_FORM_CLIENT     = "/WEB-INF/creationClient.jsp";

    private static final String CONF_DAO_FACTORY    = "dao_factory";

    private Map<String, Client> listClient          = new HashMap<String, Client>();

    private ClientDao           dao;

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_FORM_CLIENT ).forward( request, response );

    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // Modification test git
        CreationClientForm form = new CreationClientForm( dao );
        Client client = form.creerClient( request );
        HttpSession session = request.getSession();

        listClient = (Map<String, Client>) session.getAttribute( SESSION_CLIENT );
        if ( listClient == null ) {
            listClient = new HashMap<String, Client>();
        }

        if ( form.getMapErreur().isEmpty() ) {
            listClient.put( client.getNom(), client );

        }
        session.setAttribute( SESSION_CLIENT, listClient );
        request.setAttribute( ATT_CLIENT, client );
        request.setAttribute( ATT_ERRREUR, form.getMapErreur() );

        if ( form.getMapErreur().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( VUE_AFFICHER_CLIENT ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_FORM_CLIENT ).forward( request, response );
        }

    }

    @Override
    public void init() throws ServletException {

        dao = ( (DAOFactory) this.getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getClientDao();

    }

}
