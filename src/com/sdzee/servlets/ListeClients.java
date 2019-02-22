package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.dao.ClientDao;
import com.sdzee.dao.DAOFactory;

/**
 * Servlet implementation class ListeClients
 */
@WebServlet( name = "ListeClients", urlPatterns = "/listeClients" )
public class ListeClients extends HttpServlet {
    private static final long   serialVersionUID = 1L;
    private ClientDao           dao;

    private static final String CONF_DAO_FACTORY = "dao_factory";

    private static final String VUE              = "/WEB-INF/listerClients.jsp";

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        /*
         * Client client = new Client(); client.setNom( "parra" );
         * client.setPrenom( "pierre" ); client.setAdresse(
         * "18 Allée de l'oseraie 94260 Fresnes" ); client.setEmail(
         * "pierre.parra@gmail.com" ); client.setTelephone( "0665559157" );
         * DAOFactory dao = (DAOFactory)
         * request.getServletContext().getAttribute( "dao_factory" );
         * dao.getClientDao().creerClient( client ); //
         * DAOFactory.getInstance().getClientDao().creerClient( client );
         * 
         * System.out.println( client.getId() );
         */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public void init() throws ServletException {

        dao = ( (DAOFactory) this.getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getClientDao();

    }

}
