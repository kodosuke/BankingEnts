package org.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
		
	public static Connection connect() throws ClassNotFoundException, SQLException {
		
		Connection connection = null;
		
        String URL = "jdbc:mysql://127.0.0.1:3306/aliens";
        String username = "root";
        String password = "@8Yh3V2m#";

        if (connection == null || connection.isClosed()) {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);
        }

        return connection;
		
	}
}
