package com.proyectogestioncitas.app;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.proyectogestioncitas.controler.Controller;
import com.proyectogestioncitas.model.DataBaseController;
import com.proyectogestioncitas.model.XMLFile;
import com.proyectogestioncitas.view.DataBaseConfigFrame;

public class App {
	// Al iniciar la app primero comprobamos el archivo XML, lo almacenaremos en una carpeta llamada config
	// donde estará el archivo de configuración, si no existe lo creamos nosotros de nuevo
	
	private XMLFile xmlFile = null;
	private Connection dbConnection = null;
	private DataBaseConfigFrame dbConfigFrame = new DataBaseConfigFrame();
	private Controller dbConfigController = null;
	
	public static void main(String[] args) {
		System.out.println("Hol");
		new App();
		
	}
	
	public App() {
		
		xmlFile = new XMLFile(new File("config/dbConfig.xml"));
		dbConfigController = new Controller(dbConfigFrame);
		
		
		if(xmlFile.checkXMLFile()) {
			xmlFile.getConnectionWithXML(dbConfigFrame);
			dbConnection = dbConfigController.getValidConnection();
			DataBaseController dbController = new DataBaseController(dbConnection);
			//dbController.deleteExistingDatabaseTables();
			

		} else {
			xmlFile.createConfigXMLFile();
		}
		
	}
	
	private void checkForDatabaseTables() {
		dbConnection = dbConfigController.getValidConnection();
		System.out.println("Accediendo al método");
		
		try {
			Statement checkStatement = dbConnection.createStatement();
			checkStatement.executeUpdate("DROP TABLE IF EXISTS users");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
