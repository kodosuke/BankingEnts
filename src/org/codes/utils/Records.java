package org.codes.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Records {

    static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, IOException, SQLException {

        Properties props = new Properties();
        InputStream inputStream = new FileInputStream("connection.properties");
        props.load(inputStream);

        String URL = props.getProperty("URL");
        String username = props.getProperty("USER");
        String password = props.getProperty("PASS");

        if (connection == null || connection.isClosed()) {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);
        }

        return connection;
    }
}
