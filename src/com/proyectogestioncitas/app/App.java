package com.proyectogestioncitas.app;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.proyectogestioncitas.controler.Controller;
import com.proyectogestioncitas.model.DataBaseController;
import com.proyectogestioncitas.model.TimeController;
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
		
		try {
			Statement statement = dbConnection.createStatement();
			ResultSet timeResultSet = statement.executeQuery("SELECT * FROM currentday");
			
			while(timeResultSet.next()) {
				String currentDay = timeResultSet.getString("day");
				
				if(!currentDay.equals(TimeController.getCurrentTime().toString())) {
					// HACER UN BORRADO DE LAS FILAS DE LA TABLA CURRENT DAY
					// Y INSERTAR UNA NUEVA CON LA FECHA ACTUAL, DE ESTA MANERA PODEMOS
					// ACTUALIZAR LA FECHA Y COMPROBAR SI LAS CITAS SON VÁLIDAS, MIRAR
					// SI LO HACEMOS DIRECTAMENTE AQUÍ (NO RECOMENDABLE), O EN DATABASECONTROLLER
					// O EN TIMECONTROLLER
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//if(TimeController.getCurrentTime().equals(obj));
		
	}
	
}
