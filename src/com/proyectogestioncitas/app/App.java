package com.proyectogestioncitas.app;

import java.io.File;
import java.sql.Connection;

import com.proyectogestioncitas.controler.Controller;
import com.proyectogestioncitas.model.DataBaseController;
import com.proyectogestioncitas.model.XMLFile;
import com.proyectogestioncitas.view.DataBaseConfigFrame;

public class App {
	// Al iniciar la app primero comprobamos el archivo XML, lo almacenaremos en una carpeta llamada config
	// donde estará el archivo de configuración, si no existe lo creamos nosotros de nuevo
	
	private XMLFile xmlFile = null;
	private static Connection dbConnection = null;
	private DataBaseConfigFrame dbConfigFrame = new DataBaseConfigFrame();
	private Controller dbConfigController = null;
	
	public static void main(String[] args) {
		new App();
		
		/*try {
			Statement statement = dbConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}*/
		
	}
	
	public App() {
		
		xmlFile = new XMLFile(new File("config/dbConfig.xml"));
		dbConfigController = new Controller(dbConfigFrame);
		
		
		if(xmlFile.checkXMLFile()) {
			dbConnection = xmlFile.getConnectionWithXML(dbConfigFrame);
			
			if(dbConnection != null) {
				DataBaseController dbController = new DataBaseController(dbConnection);
				dbController.checkDatabaseTables();
			}
			

		} else {
			if(xmlFile.createConfigXMLFile())
				xmlFile.getConnectionWithXML(dbConfigFrame);
			
			
		}
		
	}
	
}
