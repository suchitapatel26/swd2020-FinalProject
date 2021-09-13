package com.team07.SQL;

import java.sql.*;

/**
 * Class SQLConnectionImplement inherits Class SQLConnectionInterface
 */
public class SQLConnectionImplement implements SQLConnectionInterface {

    /**
     * Database user name
     */
    private final static String databaseUserName = "engr_class007";
    /**
     * Database password
     */
    private final static String databasePassword = "swd_007";

    /**
     * Constructor
     */
    public SQLConnectionImplement() {
    }

    /**
     * Connect to database
     */
    public Connection connect() {
        Connection connection = null;
        try {
            // Get connection
            connection = DriverManager.getConnection(SQLConnectionInterface.MySQLURL, databaseUserName, databasePassword);

            if (connection != null) {
                System.out.println(SQLConnectionInterface.DB_PASS);
                return connection;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return connection;
    }


}
