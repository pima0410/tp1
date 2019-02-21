package com.sdzee.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtils {

    public static void fermetureSilencieuse( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Impossible de fermer le resultSet" + e.getMessage() );
            }
        }
    }

    public static void fermetureSilencieuse( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Impossible de fermer le statement" + e.getMessage() );
            }
        }
    }

    public static void fermetureSilencieuse( Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                System.out.println( "Impossible de fermer la connection" + e.getMessage() );
            }
        }
    }

    public static void fermetureSilencieuse( Connection connection, Statement statement ) {
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connection );
    }

    public static void fermetureSilencieuse( Connection connection, Statement statement, ResultSet resultSet ) {
        fermetureSilencieuse( connection, statement );
        fermetureSilencieuse( resultSet );
    }

}
