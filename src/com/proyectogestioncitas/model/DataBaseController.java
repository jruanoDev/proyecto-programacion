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
			int checkUsers = statement.executeUpdate("SHOW TABLES LIKE 'users'");
			int checkAdmins = statement.executeUpdate("SHOW TABLES LIKE 'admins'");
			int checkCenters = statement.executeUpdate("SHOW TABLES LIKE 'centers'");
			int checkStaff = statement.executeUpdate("SHOW TABLES LIKE 'staff'");
			
			if(checkUsers == 0 || checkAdmins == 0 || checkCenters == 0 || checkStaff == 0) {
				chkTableDialog = new CheckTableErrorDialog();
				new Controller(chkTableDialog);
				chkTableDialog.setVisible(true);
				
				/* AÑADIR LA LÓGICA PARA EL DIALOGO DE LA COMPROBACIÓN
				 * Y ESPERAR AL HILO DE ESTE DIALOGO PARA CONTINUAR CON
				 * LAS OPERACIONES SOBRE LAS TABLAS*/
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public boolean deleteExistingDatabaseTables() {
		
		StatusBarDialog statusBarDialog = new StatusBarDialog();
		statusBarDialog.setVisible(true);
		
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
