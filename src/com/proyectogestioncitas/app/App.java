package com.proyectogestioncitas.app;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.proyectogestioncitas.model.Conexion;

public class App {
	
	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	private Connection dbConnection;
	
	// Al iniciar la app primero comprobamos el archivo XML, lo almacenaremos en una carpeta llamada config
	// donde estará el archivo de configuración, si no existe lo creamos nosotros de nuevo
	
	public static void main(String[] args) {
		new App();
	}
	
	public App() {
		File xmlConfigFile = new File("config/dbConfig.xml");
		if(checkXMLFile(xmlConfigFile))
			dbConnection = getConnectionWithXML(xmlConfigFile);
		else
			createConfigXMLFile();
	}
	
	public boolean checkXMLFile(File xmlFile) {
		boolean check = false;
		
		if(xmlFile.exists()) 
			check = true;
			
		return check;
	}
	
	private Connection getConnectionWithXML(File xmlFile) {
		Connection dbConection = null;
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			NodeList nList = doc.getElementsByTagName("dbConfig");
			
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
					// Recogemos los datos de la pantalla de configuracion y los guardamos en el XML
					
					element.getElementsByTagName("dburl").item(0).setTextContent("datos");
					element.getElementsByTagName("dbuser").item(0).setTextContent("datos");
					element.getElementsByTagName("dbpassword").item(0).setTextContent("datos");
				}
				
				// Establecemos la conexion con la base de datos
				dbConection = Conexion.getInstanceConnection(dbUrl, dbUser, dbPassword);
				
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
	
		return dbConection;
	
	}
	
	private void createConfigXMLFile() {
		File configFolder = new File("/config");
		File configFile = new File("/config/dbConfig.xml");
		
		if(!configFolder.exists()) {
			configFolder.mkdir();
			
			if(configFile.exists())
				configFile.delete();
				
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.newDocument();
				
				Element rootElement = doc.createElement("dbConfig");
				doc.appendChild(rootElement);
				
				Element dbUrlElement = doc.createElement("dbUrl");
				dbUrlElement.appendChild(doc.createTextNode("none"));
				rootElement.appendChild(dbUrlElement);
				
				Element dbUserElement = doc.createElement("dbUser");
				dbUserElement.appendChild(doc.createTextNode("none"));
				rootElement.appendChild(dbUserElement);
				
				Element dbPasswordElement = doc.createElement("dbPassword");
				dbPasswordElement.appendChild(doc.createTextNode("none"));
				rootElement.appendChild(dbPasswordElement);
				
				// Escribimos el archivo
				
				TransformerFactory tFactory = TransformerFactory.newInstance();
				Transformer transformer = tFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("/config/dbConfig.xml"));
				
				transformer.transform(source, result);
				
				getConnectionWithXML(new File("/config/dbConfig.xml"));
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
	
	}
}
