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

public class App {
	// Al iniciar la app primero comprobamos el archivo XML, lo almacenaremos en una carpeta llamada config
	// donde estarÃ¡ el archivo de configuraciÃ³n, si no existe lo creamos nosotros de nuevo
	
	private XMLFile xmlFile = null;
	private static Connection dbConnection = null;
	private DataBaseConfigFrame dbConfigFrame = new DataBaseConfigFrame();
	private Controller dbConfigController = null;
	private DataBaseController dbController = null;
	
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
				dbController = new DataBaseController(dbConnection);
				dbController.checkDatabaseTables();
				
				dbController.checkAdminOnDB();
				dbController.checkMedicalCenters();
				
			}
			

		} else {
			if(xmlFile.createConfigXMLFile())
				xmlFile.getConnectionWithXML(dbConfigFrame);
			
		}
		
		//VAMOS A DEJAR ESTO PARA LUEGO, PRIMERO CREAMOS UN ADMINISTRADOR Y CONFIGURAMOS CENTROS
		
		/*try {
			Statement statement = dbConnection.createStatement();
			ResultSet timeResultSet = statement.executeQuery("SELECT * FROM currentday;");
			
			String setTime = "INSERT INTO currentday VALUES(?);";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(setTime);
			
			preparedStatement.setString(1, TimeController.getCurrentTime().toString());
			
			if(!timeResultSet.next())
				preparedStatement.execute();
			
			while(timeResultSet.next()) {
				String currentDay = timeResultSet.getString("day");
				
				if(!currentDay.equals(TimeController.getCurrentTime().toString())) {
					// HACER UN BORRADO DE LAS FILAS DE LA TABLA CURRENT DAY
					// Y INSERTAR UNA NUEVA CON LA FECHA ACTUAL, DE ESTA MANERA PODEMOS
					// ACTUALIZAR LA FECHA Y COMPROBAR SI LAS CITAS SON VÃ�LIDAS, MIRAR
					// SI LO HACEMOS DIRECTAMENTE AQUÃ� (NO RECOMENDABLE), O EN DATABASECONTROLLER
					// O EN TIMECONTROLLER
					
					int deletedRows = statement.executeUpdate("DELETE FROM currentday;");
					preparedStatement.execute();
					
					dbController = new DataBaseController(dbConnection);
					
					
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//if(TimeController.getCurrentTime().equals(obj));
		
	}
	
}
