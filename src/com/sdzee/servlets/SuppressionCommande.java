package com.sdzee.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Client;
import com.sdzee.dao.CommandeDao;
import com.sdzee.dao.DAOFactory;
import com.sdzee.form.FormUtil;

@WebServlet( "/suppressionCommandes" )
public class SuppressionCommande extends HttpServlet {

    private static final String PARAM_ID_COMMANDE = "idCommande";
    private static final String SESSION_COMMANDE  = "listCommande";
    private static final String VUE               = "/listeCommandes";
    private static final String CONF_FACTORY_DAO  = "dao_factory";

    private CommandeDao         commandeDao;

    @Override
    public void init() throws ServletException {

        this.commandeDao = ( (DAOFactory) this.getServletContext().getAttribute( CONF_FACTORY_DAO ) ).getCommandeDao();
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Map<String, Client> listCommandeMap = (Map<String, Client>) session.getAttribute( SESSION_COMMANDE );

        String valueParam = FormUtil.getParam( request, PARAM_ID_COMMANDE );
        if ( valueParam != null && !listCommandeMap.isEmpty() ) {
            Long idCommande = Long.parseLong( valueParam );
            commandeDao.supprimerCommandeParID( idCommande );
            listCommandeMap.remove( idCommande );
            session.setAttribute( SESSION_COMMANDE, listCommandeMap );

        }
        session.setAttribute( SESSION_COMMANDE, listCommandeMap );

        response.sendRedirect( request.getContextPath() + VUE );

    }

}
