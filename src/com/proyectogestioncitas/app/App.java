package com.proyectogestioncitas.app;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.proyectogestioncitas.controler.Controller;
import com.proyectogestioncitas.model.DataBaseController;
import com.proyectogestioncitas.model.TimeController;
import com.proyectogestioncitas.model.XMLFile;
import com.proyectogestioncitas.view.DataBaseConfigFrame;
import com.proyectogestioncitas.view.LoginFrame;

public class App {
	// Al iniciar la app primero comprobamos el archivo XML, lo almacenaremos en una carpeta llamada config
	// donde estarÃ¡ el archivo de configuraciÃ³n, si no existe lo creamos nosotros de nuevo
	
	private XMLFile xmlFile = null;
	private static Connection dbConnection = null;
	private DataBaseConfigFrame dbConfigFrame = new DataBaseConfigFrame();
	private Controller dbConfigController = null;
	private DataBaseController dbController = null;
	private static LoginFrame loginFrame = null;
	
	public static void main(String[] args) {
		new App();
	}
	
	public App() {
		
		xmlFile = new XMLFile(new File("config/dbConfig.xml"));
		dbConfigController = new Controller(dbConfigFrame);
		
		
		if(xmlFile.checkXMLFile()) {
			dbConnection = xmlFile.getConnectionWithXML(dbConfigFrame);
			
			if(dbConnection != null) {
				dbController = new DataBaseController(dbConnection);
				dbController.checkDatabaseTables();
				
				dbController.checkAdminOnDB();
				dbController.checkMedicalCenters();
				
				loginFrame = new LoginFrame();
				new Controller(loginFrame, dbConnection);
				loginFrame.setVisible(true);
				
				try {
					Statement statement = dbConnection.createStatement();
					ResultSet timeResultSet = statement.executeQuery("SELECT * FROM currentday;");
					
					String setTime = "INSERT INTO currentday VALUES(?);";
					PreparedStatement preparedStatement = dbConnection.prepareStatement(setTime);
					
					preparedStatement.setString(1, TimeController.getCurrentTime().toString());
					
					if(!timeResultSet.next()) {
						preparedStatement.execute();
						dbController.createDatesSctructure();
					}

					ResultSet updateRSet = statement.executeQuery("SELECT * FROM currentday;");
					updateRSet.next();

					String currentDay = updateRSet.getString("day");
					
					if(!currentDay.equals(TimeController.getCurrentTime().toString())) {
						
						int deletedRows = statement.executeUpdate("DELETE FROM currentday;");
						preparedStatement.execute();
						
						dbController = new DataBaseController(dbConnection);
						dbController.createDatesSctructure();
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			}

		} else {
			if(xmlFile.createConfigXMLFile())
				xmlFile.getConnectionWithXML(dbConfigFrame);
			
		}		
			
	}
	
	public static Connection getConnection() {
		return dbConnection;
	}
	
	public static void closeLoginFrame() {
		loginFrame.dispose();
	}
}
