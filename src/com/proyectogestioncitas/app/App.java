package com.proyectogestioncitas.app;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class App {
	
	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	
	// Al iniciar la app primero comprobamos el archivo XML, lo almacenaremos en una carpeta llamada config
	// donde estará el archivo de configuración, si no existe lo creamos nosotros de nuevo
	
	public static void main(String[] args) {
		new App();
	}
	
	public App() {
		File xmlConfigFile = new File("config/dbConfig.xml");
		checkXMLFile(xmlConfigFile);
	}
	
	public void checkXMLFile(File xmlFile) {
		
		if(xmlFile.exists()) {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			
			try {
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);
				
				NodeList nList = doc.getElementsByTagName("user");
				
				Node nNode = nList.item(0);
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
					
					dbUrl = element.getElementsByTagName("dburl").item(0).getTextContent();
					dbUser = element.getElementsByTagName("dbuser").item(0).getTextContent();
					dbPassword = element.getElementsByTagName("dbpassword").item(0).getTextContent();
					
					// Si todos los datos están correctos abriremos la conexión a la base de datos con todos
					// estos datos recogidos del XML
					
					if(dbUrl == "none" || dbUser == "none" || dbPassword == "none") {
						// Aquí abriremos la pantalla de configuración de la base de datos
						
					}
					
					
					
				}
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
