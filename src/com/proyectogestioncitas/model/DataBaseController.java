package com.proyectogestioncitas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.proyectogestioncitas.controler.Controller;
import com.proyectogestioncitas.view.CheckTableErrorDialog;
import com.proyectogestioncitas.view.CreateAdminFrame;

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
		
		try {
			statement = dbConnection.createStatement();
			
			int deleteClients = statement.executeUpdate("DROP TABLE IF EXISTS clients");
			int deleteAdmins = statement.executeUpdate("DROP TABLE IF EXISTS admins");
			int deleteMedicians = statement.executeUpdate("DROP TABLE IF EXISTS medicians");
			int deleteDates = statement.executeUpdate("DROP TABLE IF EXISTS dates");
			int deleteCurrentDay = statement.executeUpdate("DROP TABLE IF EXISTS currentday");
			int deleteCenters = statement.executeUpdate("DROP TABLE IF EXISTS centers");
						
			
			String createAdmins = "CREATE TABLE admins (" + 
								"id MEDIUMINT NOT NULL AUTO_INCREMENT," +
								"login VARCHAR(20) NOT NULL UNIQUE," + 
								"password VARCHAR(20) NOT NULL," + 
								"PRIMARY KEY (id)" + 
								");";
			
			int createAdminsCheck = statement.executeUpdate(createAdmins);
			
			
			String createCenters = "CREATE TABLE centers (" + 
								"id VARCHAR(10) NOT NULL UNIQUE," + 
								"address VARCHAR(20) NOT NULL UNIQUE," + 
								"name VARCHAR(25) NOT NULL UNIQUE," + 
								"postal_code NUMERIC(5) NOT NULL," + 
								"phone_number NUMERIC(9) NOT NULL," + 
								"PRIMARY KEY (id)" + 
								");";
			
			int createCentersCheck = statement.executeUpdate(createCenters);
			
			
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
			
			/* FALTAN POR CREAR LA TABLA DE LAS CITAS CONCEDIDAS, QUE SE LIMPIARÁ EN CASO DE
			 * QUE EL DÍA HAYA CAMBIADO Y SE AÑADIRAN NUEVOS CAMPOS CON LOS HORARIOS ESCOGIDOS.
			 */
			
			
			String createCurrentDay = "CREATE TABLE currentday (" + 
									"day VARCHAR(10) NOT NULL" + 
									");";
			
			int createCurrentDayCheck = statement.executeUpdate(createCurrentDay);
			
			/*String setTime = "INSERT INTO currentday VALUES(?);";
			PreparedStatement statementTime = dbConnection.prepareStatement(setTime);
			LocalDate currentDate = TimeController.getCurrentTime();
			
			statementTime.setString(1, currentDate.toString());
			statementTime.executeUpdate();*/
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkAdminOnDB() {
		boolean check = false;
		
		try {
			statement = dbConnection.createStatement();
			ResultSet adminResultSet = statement.executeQuery("SELECT * FROM admins;");
			
			if(!adminResultSet.next()) {
				CreateAdminFrame newAdmin = new CreateAdminFrame();
				newAdmin.setVisible(true);
				new Controller(newAdmin, dbConnection);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
		
	}
	
	public void createNewAdmin(String login, String password) {
		String newAdminSentence = "INSERT INTO admins(login, password) VALUES (?, ?);";
		
		try {
			PreparedStatement caPreparedStatement = dbConnection.prepareStatement(newAdminSentence);
			caPreparedStatement.setString(1, login);
			caPreparedStatement.setString(2, password);
			
			caPreparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean checkLogins(String login) {
		boolean check = false;
		
		try {
			statement = dbConnection.createStatement();
			ResultSet logins = statement.executeQuery("SELECT login FROM admins;");
			
			if(!logins.next()) 
				check = true;
			
			while(logins.next()) {
				String dbLogin = logins.getString("login");
				System.out.println(dbLogin);
				
				if(dbLogin == login) {
					break;
					
				} else {
					check = true;
					break;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
		
	}
	
	public void checkMedicalCenters() {
		
		try {
			statement = dbConnection.createStatement();
			ResultSet medicalCenters = statement.executeQuery("SELECT * FROM centers;");
			
			if(!medicalCenters.next()) {
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
