package com.sdzee.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sdzee.dao.DAOFactory;

public class InitialisationDaoFactory implements ServletContextListener {
    private static final String CONF_DAO_FACTORY = "dao_factory";

    @Override
    public void contextDestroyed( ServletContextEvent sce ) {
        // TODO Auto-generated method stub
        ServletContextListener.super.contextDestroyed( sce );
    }

    @Override
    public void contextInitialized( ServletContextEvent sce ) {
        sce.getServletContext().setAttribute( CONF_DAO_FACTORY, DAOFactory.getInstance() );

    }

}
