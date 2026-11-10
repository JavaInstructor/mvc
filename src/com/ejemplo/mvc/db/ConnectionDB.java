package com.ejemplo.mvc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	private static final String URL = "jdbc:mysql://localhost:3306/personas";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "root";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException error) {
			throw new RuntimeException(
					"Falta el driver mysql-connector-j en el classpath"
					, error);
		}
	}
	
	private ConnectionDB() {
		
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, PASSWORD);
	}

}
