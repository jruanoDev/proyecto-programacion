package com.proyectogestioncitas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.imageio.spi.RegisterableService;
import javax.swing.JOptionPane;

import com.proyectogestioncitas.controler.Controller;
import com.proyectogestioncitas.model.dao.MedicalCenterDAO;
import com.proyectogestioncitas.model.pojo.MedicalCenter;
import com.proyectogestioncitas.view.CheckTableErrorDialog;
import com.proyectogestioncitas.view.CreateAdminFrame;
import com.proyectogestioncitas.view.CreateCenterDialog;

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
			int checkDates = statement.executeUpdate("SHOW TABLES LIKE 'dates'");
			int checkCurrentDay = statement.executeUpdate("SHOW TABLES LIKE 'currentday'");
			
			if(checkClients == 0 || checkAdmins == 0 || checkCenters == 0 || checkDates == 0 || checkCurrentDay == 0) {
				
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
			
			String createDates = "CREATE TABLE dates (" + 
								"day MEDIUMINT NOT NULL," + 
								"hour VARCHAR(5) NOT NULL UNIQUE," + 
								"center VARCHAR(10) NOT NULL," + 
								"id_date MEDIUMINT NOT NULL UNIQUE AUTO_INCREMENT," +
								"client_name VARCHAR(15) DEFAULT \"\"," + 
								"client_id VARCHAR(9) DEFAULT \"\"," + 
								"CONSTRAINT fk_dates FOREIGN KEY dates(center) REFERENCES centers(id) ON " + 
								"DELETE CASCADE," + 
								"PRIMARY KEY (id_date)" + 
								");";
			
			int createDatesCheck = statement.executeUpdate(createDates);
			
			String createCurrentDay = "CREATE TABLE currentday (" + 
									"day VARCHAR(10) NOT NULL" + 
									");";
			
			int createCurrentDayCheck = statement.executeUpdate(createCurrentDay);
			
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
				new Controller(newAdmin, dbConnection);
				newAdmin.setVisible(true);
				
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
				CreateCenterDialog cCenterDialog = new CreateCenterDialog();
				new Controller(cCenterDialog, dbConnection);
				cCenterDialog.setVisible(true);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean logUser(String login, String password) {
		boolean check = false;
		String loginSql = "SELECT id, password FROM clients WHERE id = ?;";
		
		try {
			PreparedStatement loginStatement = dbConnection.prepareStatement(loginSql);
			loginStatement.setString(1, login);
			
			ResultSet loginRSet = loginStatement.executeQuery();
			
			while(loginRSet.next()) {
				String dbLogin = loginRSet.getString("id");
				String dbPassword = loginRSet.getString("password");
				
				if(!dbLogin.equals("") || !dbPassword.equals("")) {
					if(login.equals(dbLogin) && password.equals(dbPassword))
						check = true;
				} else {
					JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El usuario/contraseña no son correctos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return check;
	}
	
	public boolean registerUser(String email, String name, String surname, String id, String password, String birthDate) {
		boolean check = false;
		
		String registerSql = "INSERT INTO clients VALUES (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement registerStatement = dbConnection.prepareStatement(registerSql);
			
			registerStatement.setString(1, email);
			registerStatement.setString(2, name);
			registerStatement.setString(3, surname);
			registerStatement.setString(4, id);
			registerStatement.setString(5, password);
			registerStatement.setString(6, birthDate);
			registerStatement.setString(7, MedicalCenterDAO.getMedicalCenterId()); //OBTENER EL CENTRO DE LA BBDD
			
			check = registerStatement.execute();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error en el registro, compruebe los datos.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		return check;
	}
	
	public boolean checkUserEmail(String email) {
		boolean check = false;
		
		String emailCheck = "SELECT email FROM clients WHERE email=?";
		
		try {
			PreparedStatement checkStatement = dbConnection.prepareStatement(emailCheck);
			checkStatement.setString(1, email);
			
			ResultSet emailRSet = checkStatement.executeQuery();
			
			while(emailRSet.next()) {
				if(!emailRSet.getString("email").equals(email))
					check = true;
					
			}
			
			if(!emailRSet.next())
				check = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	
	public boolean checkUserID(String id) {
		boolean check = false;
		
		String idCheck = "SELECT id FROM clients WHERE id=?";

		try {
			PreparedStatement checkIDStatement = dbConnection.prepareStatement(idCheck);
			checkIDStatement.setString(1, id);
			
			ResultSet idRSet = checkIDStatement.executeQuery();
			
			if(!idRSet.next())
				check = true;
			
			while(idRSet.next()) {
				if(!idRSet.getString("id").equals(id))
					check = true;
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	
	public boolean logAdmin(String login, String password) {
		boolean check = false;
		String adminLogin = "SELECT login, password FROM admins WHERE login=?";
		
		try {
			PreparedStatement adminLoginStatement = dbConnection.prepareStatement(adminLogin);
			adminLoginStatement.setString(1, login);
			
			ResultSet adminLoginRSet = adminLoginStatement.executeQuery();
			
			if(!adminLoginRSet.next())
				JOptionPane.showMessageDialog(null, "Usuario/Contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
			
			String dbLogin = adminLoginRSet.getString("login");
			String dbPassword = adminLoginRSet.getString("password");
			
			if(login.equals(dbLogin) && password.equals(dbPassword))
				check = true;
				
		} catch (SQLException e) {
			// Error de consulta si no hay nada, ya está controlado en el controller y con el condicional
		}
		
		return check;
		
	}
	
	public void createDatesSctructure() {
		LocalDate currentDay = TimeController.getCurrentTime();
		
		try {
			Statement deleteStatement = dbConnection.createStatement();
			int deletedRows = deleteStatement.executeUpdate("DELETE FROM dates;");
			
			String createDates = "INSERT INTO dates(day, hour, center) VALUES(?,?,?);";
			
			PreparedStatement cDates = dbConnection.prepareStatement(createDates);
			cDates.setInt(1, currentDay.getDayOfMonth());
			cDates.setString(2, "11:00");
			cDates.setString(3, MedicalCenterDAO.getMedicalCenterId());
			
			cDates.executeUpdate();
			
			cDates.setInt(1, currentDay.getDayOfMonth());
			cDates.setString(2, "12:00");
			cDates.setString(3, MedicalCenterDAO.getMedicalCenterId());
			
			cDates.executeUpdate();
			
			cDates.setInt(1, currentDay.getDayOfMonth());
			cDates.setString(2, "16:00");
			cDates.setString(3, MedicalCenterDAO.getMedicalCenterId());
			
			cDates.executeUpdate();
			
			cDates.setInt(1, currentDay.getDayOfMonth());
			cDates.setString(2, "18:00");
			cDates.setString(3, MedicalCenterDAO.getMedicalCenterId());
			
			cDates.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
