package com.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDbConnection {

	private static Connection connection;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.16.208:1521:xe";
		String user = "javaus";
		String password = "mphasis";
		connection = DriverManager.getConnection(url, user, password);
		
		return connection;
		
	}
}
// Singleton Java class