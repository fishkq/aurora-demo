package org.example;

import java.sql.*;
import java.util.Properties;

public class UsernameConnector {

    /**
     * The clusterInstanceHostPattern configuration property is required
     * when an IP address or custom domain is used to connect to a cluster
     * that provides topology information.
     * If you would instead like to connect without failover functionality,
     * set the enableClusterAwareFailover configuration property to false.
     *
     * properties.setProperty("clusterInstanceHostPattern", "?.[url]");
     */
//    private static final String CONNECTION_STRING = "jdbc:mysql:aws://127.0.0.1:3307";
    private static final String CONNECTION_STRING = "jdbc:mysql://127.0.0.1:3307";
    private static final String USER_NAME = "[username]";
    private static final String PASSWORD = "[password]";

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", USER_NAME);
        properties.setProperty("password", PASSWORD);
        // Try and make a connection:
        try (final Connection conn = DriverManager.getConnection(CONNECTION_STRING, properties);
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT CURRENT_DATE() FROM DUAL")) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }


}
