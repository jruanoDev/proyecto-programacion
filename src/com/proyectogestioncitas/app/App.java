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
	
	private String dbUrl = "";
	private String dbUser = "";
	private String dbPassword = "";
	private Connection dbConnection = null;
	
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
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			NodeList nList = doc.getElementsByTagName("dbConfig");
			
			Node nNode = nList.item(0);
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				
				dbUrl = element.getElementsByTagName("dbUrl").item(0).getTextContent();
				dbUser = element.getElementsByTagName("dbUser").item(0).getTextContent();
				dbPassword = element.getElementsByTagName("dbPassword").item(0).getTextContent();
				
				// Si todos los datos están correctos abriremos la conexión a la base de datos con todos
				// estos datos recogidos del XML
				
				if(dbUrl.equals("none") || dbUser.equals("none") || dbPassword.equals("none")) {
					System.out.println("Hemos entrado");
					// Aquí abriremos la pantalla de configuración de la base de datos
					// Recogemos los datos de la pantalla de configuracion y los guardamos en el XML
					
					element.getElementsByTagName("dbUrl").item(0).setTextContent("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8177637");
					element.getElementsByTagName("dbUser").item(0).setTextContent("sql8177637");
					element.getElementsByTagName("dbPassword").item(0).setTextContent("li94WcskFU");
					
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = null;
					try {
						transformer = transformerFactory.newTransformer();
					} catch (TransformerConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File("config/dbConfig.xml"));
					transformer.transform(source, result);
				}
				
				// Establecemos la conexion con la base de datos
				dbConnection = Conexion.getInstanceConnection(dbUrl, dbUser, dbPassword);
				
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
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return dbConnection;
	
	}
	
	private void createConfigXMLFile() {
		File configFolder = new File("config");
		File configFile = new File("config/dbConfig.xml");
		System.out.println("Hemos entrado en crear");
		
		if(!configFolder.exists())
			System.out.println(configFolder.mkdir());
			
		if(configFile.exists())
			configFile.delete();
			
		DocumentBuilderFactory dbFactoryCreate = DocumentBuilderFactory.newInstance();
		try {
			System.out.println("Hemos entrado en creacion del archivo");
			DocumentBuilder dBuilderCreate = dbFactoryCreate.newDocumentBuilder();
			Document docCreate = dBuilderCreate.newDocument();
			
			Element rootElement = docCreate.createElement("dbConfig");
			docCreate.appendChild(rootElement);
			
			Element dbUrlElement = docCreate.createElement("dbUrl");
			dbUrlElement.appendChild(docCreate.createTextNode("none"));
			rootElement.appendChild(dbUrlElement);
			
			Element dbUserElement = docCreate.createElement("dbUser");
			dbUserElement.appendChild(docCreate.createTextNode("none"));
			rootElement.appendChild(dbUserElement);
			
			Element dbPasswordElement = docCreate.createElement("dbPassword");
			dbPasswordElement.appendChild(docCreate.createTextNode("none"));
			rootElement.appendChild(dbPasswordElement);
			
			// Escribimos el archivo
			TransformerFactory tFactoryCreate = TransformerFactory.newInstance();
			Transformer transformerCreate = tFactoryCreate.newTransformer();
			DOMSource sourceCreate = new DOMSource(docCreate);
			StreamResult resultCreate = new StreamResult(new File("config/dbConfig.xml"));
			
			transformerCreate.transform(sourceCreate, resultCreate);
			
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
