package org.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseCon {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Connection connection = null;
		
        String URL = "jdbc:mysql://127.0.0.1:3306/avengers";
        String username = "root";
        String password = "@8Yh3V2m#";

        if (connection == null || connection.isClosed()) {
        	
//            Class.forName("com.mysql.jdbc.Driver");  //Deprecated
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);
        }

        return connection;
		
	}

}
