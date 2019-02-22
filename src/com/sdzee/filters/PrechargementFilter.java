package com.sdzee.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Client;
import com.sdzee.beans.Commande;
import com.sdzee.dao.ClientDao;
import com.sdzee.dao.CommandeDao;
import com.sdzee.dao.DAOFactory;

public class PrechargementFilter extends HttpFilter {

    private static final String CONF_DAO_FACTORY      = "dao_factory";
    private static final String SESSION_LIST_CLIENT   = "listClient";
    private static final String SESSION_LIST_COMMANDE = "listCommande";
    private ClientDao           clientDao;
    private CommandeDao         commandeDao;

    @Override
    public void init( FilterConfig config ) throws ServletException {
        // TODO Auto-generated method stub
        DAOFactory factory = (DAOFactory) config.getServletContext().getAttribute( CONF_DAO_FACTORY );
        clientDao = factory.getClientDao();
        commandeDao = factory.getCommandeDao();
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if ( session.getAttribute( SESSION_LIST_CLIENT ) == null ) {

            List<Client> clients = clientDao.listerClient();
            Map<Long, Client> listClient = new HashMap<Long, Client>();
            for ( Client client : clients ) {
                listClient.put( client.getId(), client );
            }

            session.setAttribute( SESSION_LIST_CLIENT, listClient );
        }

        if ( session.getAttribute( SESSION_LIST_COMMANDE ) == null ) {
            List<Commande> commandes = commandeDao.listerCommande();
            Map<Long, Commande> listCommande = new HashMap<Long, Commande>();
            for ( Commande commande : commandes ) {
                listCommande.put( commande.getId(), commande );
            }
            session.setAttribute( SESSION_LIST_COMMANDE, listCommande );

        }

        chain.doFilter( request, response );

    }

    // Filtre pour récupérer la liste des clients et commandes en session

}
