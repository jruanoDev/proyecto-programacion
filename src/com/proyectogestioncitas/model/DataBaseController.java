package com.proyectogestioncitas.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.proyectogestioncitas.controler.Controller;
import com.proyectogestioncitas.view.CheckTableErrorDialog;
import com.proyectogestioncitas.view.StatusBarDialog;

public class DataBaseController {
	private Statement statement = null;
	private Connection dbConnection = null;
	private CheckTableErrorDialog chkTableDialog = null;
	
	public DataBaseController(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public void checkDatabaseTables() {
		try {
			Statement statement = dbConnection.createStatement();
			int checkClients = statement.executeUpdate("SHOW TABLES LIKE 'clients'");
			int checkAdmins = statement.executeUpdate("SHOW TABLES LIKE 'admins'");
			int checkCenters = statement.executeUpdate("SHOW TABLES LIKE 'centers'");
			int checkMedicians = statement.executeUpdate("SHOW TABLES LIKE 'medicians'");
			int checkDates = statement.executeUpdate("SHOW TABLES LIKE 'dates'");
			int checkCurrentDay = statement.executeUpdate("SHOW TABLES LIKE 'currentday'");
			
			if(checkClients == 0 || checkAdmins == 0 || checkCenters == 0 || checkMedicians == 0 || checkDates == 0
					|| checkCurrentDay == 0) {
				
				chkTableDialog = new CheckTableErrorDialog();
				new Controller(chkTableDialog, dbConnection);
				chkTableDialog.setVisible(true);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void createDataBaseStructure() {
		StatusBarDialog statusBarDialog = new StatusBarDialog();
		statusBarDialog.setVisible(true);
		
		try {
			statement = dbConnection.createStatement();
			
			int deleteClients = statement.executeUpdate("DROP TABLE IF EXISTS clients");
			int deleteAdmins = statement.executeUpdate("DROP TABLE IF EXISTS admins");
			int deleteMedicians = statement.executeUpdate("DROP TABLE IF EXISTS medicians");
			int deleteDates = statement.executeUpdate("DROP TABLE IF EXISTS dates");
			int deleteCurrentDay = statement.executeUpdate("DROP TABLE IF EXISTS currentday");
			int deleteCenters = statement.executeUpdate("DROP TABLE IF EXISTS centers");
			
			statusBarDialog.setDbLoadingBarValue(10);
			
			String createAdmins = "CREATE TABLE admins (" + 
								"id MEDIUMINT NOT NULL AUTO_INCREMENT," +
								"login VARCHAR(20) NOT NULL UNIQUE," + 
								"password VARCHAR(20) NOT NULL," + 
								"PRIMARY KEY (id)" + 
								");";
			
			int createAdminsCheck = statement.executeUpdate(createAdmins);
			
			statusBarDialog.setDbLoadingBarValue(20);
			
			String createCenters = "CREATE TABLE centers (" + 
								"id VARCHAR(10) NOT NULL UNIQUE," + 
								"address VARCHAR(20) NOT NULL UNIQUE," + 
								"name VARCHAR(25) NOT NULL UNIQUE," + 
								"postal_code NUMERIC(5) NOT NULL," + 
								"phone_number NUMERIC(9) NOT NULL," + 
								"PRIMARY KEY (id)" + 
								");";
			
			int createCentersCheck = statement.executeUpdate(createCenters);
			
			statusBarDialog.setDbLoadingBarValue(30);
			
			String createClients = "CREATE TABLE clients (" + 
								"email VARCHAR(20) NOT NULL UNIQUE," + 
								"name VARCHAR(15) NOT NULL," + 
								"surname VARCHAR(20) NOT NULL," + 
								"id VARCHAR(9) NOT NULL UNIQUE," + 
								"password VARCHAR(20) NOT NULL," + 
								"birth_date DATE NOT NULL," + 
								"associated_centre VARCHAR(10) NOT NULL," + 
								"CONSTRAINT fk_centers FOREIGN KEY clients(associated_centre) REFERENCES centers(id)" + 
								"ON DELETE CASCADE," + 
								"PRIMARY KEY (id)" + 
								");";
			
			int createClientsCheck = statement.executeUpdate(createClients);
			
			statusBarDialog.setDbLoadingBarValue(40);
			
			String createMedicians = "CREATE TABLE medicians (" + 
									"associated_centre VARCHAR(10) NOT NULL," + 
									"name VARCHAR(15) NOT NULL," + 
									"surname VARCHAR(20) NOT NULL," + 
									"id VARCHAR(9) NOT NULL UNIQUE," +
									"birthdate DATE NOT NULL," + 
									"CONSTRAINT fk_medicians FOREIGN KEY medicians(associated_centre)" + 
									"REFERENCES centers(id) ON DELETE CASCADE," + 
									"PRIMARY KEY (id)" + 
									");";
			
			int createMediciansCheck = statement.executeUpdate(createMedicians);
			
			statusBarDialog.setDbLoadingBarValue(50);
			
			String createDates = "CREATE TABLE dates (" + 
								"day DATE NOT NULL," + 
								"hour TIME NOT NULL UNIQUE," + 
								"center VARCHAR(10) NOT NULL UNIQUE," + 
								"id_date MEDIUMINT NOT NULL UNIQUE AUTO_INCREMENT," + 
								"CONSTRAINT fk_dates FOREIGN KEY dates(center) REFERENCES centers(id) ON " + 
								"DELETE CASCADE," + 
								"PRIMARY KEY (id_date)" + 
								");";
			
			int createDatesCheck = statement.executeUpdate(createDates);
			
			statusBarDialog.setDbLoadingBarValue(60);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
