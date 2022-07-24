package com.sridhar.project.course.courseapiapp.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Service
public class ConnectionService {
    /**
     * This method is used to connect with the database using JDBC Driver (Cleaner method)
     * @return Connection
     * @throws SQLException
     */
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:harperdb:Server=https://course-api-sridhar.harperdbcloud.com;User=root;Password=root;UseSSL=true;");
    }

    /**
     * This method is also used to connect with the database using JDBC Drive but using the Properties class
     * @return Connection
     * @throws SQLException
     */

    public Connection createConnectionUsingProps() throws SQLException{
        Properties connectionProps = new Properties();
        connectionProps.setProperty("Server", "https://course-api-sridhar.harperdbcloud.com");
        connectionProps.setProperty("User", "root");
        connectionProps.setProperty("Password", "root");

        return DriverManager.getConnection("jdbc:harperdb:", connectionProps);
    }
}
