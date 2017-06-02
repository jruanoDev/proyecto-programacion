package com.proyectogestioncitas.app;

import java.io.File;
import java.sql.Connection;

import com.proyectogestioncitas.model.XMLFile;

public class App {
	// Al iniciar la app primero comprobamos el archivo XML, lo almacenaremos en una carpeta llamada config
	// donde estará el archivo de configuración, si no existe lo creamos nosotros de nuevo
	
	XMLFile xmlFile = null;
	Connection dbConnection = null;
	
	public static void main(String[] args) {
		new App();
		
	}
	
	public App() {
		xmlFile = new XMLFile(new File("config/dbConfig.xml"));
		
		if(xmlFile.checkXMLFile())
			dbConnection = xmlFile.getConnectionWithXML();
		else
			xmlFile.createConfigXMLFile();
	}
	
	
	
	
	
	
}
