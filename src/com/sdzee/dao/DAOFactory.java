package com.sdzee.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DAOFactory {
    private static final String PROPERTIES_FILE_PATH       = "/com/sdzee/dao/dao.properties";

    private static final String PROPERTY_URL_DATABASE      = "url";
    private static final String PROPERTY_USER_DATABASE     = "nomutilisateur";
    private static final String PROPERTY_PASSWORD_DATABASE = "motdepasse";
    private static final String PROPERTY_DRIVER_DATABASE   = "driver";

    private BoneCP              connectionPool;

    private DAOFactory( BoneCP cp ) {
        this.connectionPool = cp;

    }

    public static DAOFactory getInstance() {

        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        BoneCP connectionPool = null;

        // récupération des proprietes de connexion database
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream( PROPERTIES_FILE_PATH );

        if ( stream == null ) {

            throw new DAOConfigurationException( "Le fichier de properties n'a pas été trouvé" );

        }
        Properties prop = new Properties();
        try {
            prop.load( stream );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Le fichier de properties n'a pas pu etre correctement lu", e );
        }

        url = prop.getProperty( PROPERTY_URL_DATABASE );
        nomUtilisateur = prop.getProperty( PROPERTY_USER_DATABASE );
        motDePasse = prop.getProperty( PROPERTY_PASSWORD_DATABASE );
        driver = prop.getProperty( PROPERTY_DRIVER_DATABASE );

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "impossible de trouver le driver de connexion à la base de donnée",
                    e );
        }
        try {
            BoneCPConfig confBoneCp = new BoneCPConfig();
            confBoneCp.setJdbcUrl( url );
            confBoneCp.setUsername( nomUtilisateur );
            confBoneCp.setPassword( motDePasse );

            confBoneCp.setMinConnectionsPerPartition( 5 );
            confBoneCp.setMaxConnectionsPerPartition( 10 );
            confBoneCp.setPartitionCount( 2 );

            connectionPool = new BoneCP( confBoneCp );
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DAOConfigurationException( "Impossible de créer le pool de connexion", e );
        }

        return new DAOFactory( connectionPool );
    }

    public Connection getConnection() throws SQLException {

        return connectionPool.getConnection();
    }

    public ClientDao getClientDao() {
        return new ClientDaoImpl( this );
    }

    public CommandeDao getCommandeDao() {
        return new CommandeDaoImpl( this );
    }

}
