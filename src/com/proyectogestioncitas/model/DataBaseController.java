package com.proyectogestioncitas.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseController {
	private Statement statement = null;
	private Connection dbConnection = null;
	
	public DataBaseController(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public boolean deleteExistingDatabaseTables() {
		try {
			statement = dbConnection.createStatement();
			int usersCheck = statement.executeUpdate("DROP TABLE IF EXISTS users");
			int adminCheck = statement.executeUpdate("DROP TABLE IF EXISTS admins");
			int centerCheck = statement.executeUpdate("DROP TABLE IF EXISTS centers");
			int datesCheck = statement.executeUpdate("DROP TABLE IF EXISTS dates");
			
			System.out.println(usersCheck);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

}
