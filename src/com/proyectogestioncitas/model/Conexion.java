package com.proyectogestioncitas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Connection conexion = null;
	
	// El constructor está modificado para estos parámetros recogidos del XML en App.java
	private Conexion(String dbUrl, String dbUser, String dbPassword) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//joseoski.heliohost.org
			conexion = DriverManager.getConnection("jdbc:mysql://" + dbUrl + "?useSSL=false", dbUser, dbPassword);
			
			/* conexion = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8177637", 
					"sql8177637", "li94WcskFU"); */
			
		} catch (SQLException e) {
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getInstanceConnection(String dbUrl, String dbUser, String dbPassword) {
		if(conexion == null)
			new Conexion(dbUrl, dbUser, dbPassword);
		
		return conexion;
	}
	
}
