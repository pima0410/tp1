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
import com.sdzee.dao.ClientDao;
import com.sdzee.dao.DAOFactory;

public class PrechargementFilter extends HttpFilter {

    private static final String CONF_DAO_FACTORY    = "dao_factory";
    private static final String SESSION_LIST_CLIENT = "listClient";
    private ClientDao           clientDao;

    @Override
    public void init( FilterConfig config ) throws ServletException {
        // TODO Auto-generated method stub
        DAOFactory factory = (DAOFactory) config.getServletContext().getAttribute( CONF_DAO_FACTORY );
        clientDao = factory.getClientDao();
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        List<Client> clients = clientDao.listerClient();
        Map<String, Client> listClient = new HashMap<String, Client>();
        for ( Client client : clients ) {
            listClient.put( client.getNom(), client );
        }

        HttpSession session = req.getSession();

        session.setAttribute( SESSION_LIST_CLIENT, listClient );

        chain.doFilter( request, response );

    }

    // Filtre pour r�cup�rer la liste des clients et commandes en session

}
