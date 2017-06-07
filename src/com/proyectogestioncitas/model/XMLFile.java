package com.proyectogestioncitas.model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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

import com.proyectogestioncitas.app.App;
import com.proyectogestioncitas.view.DataBaseConfigFrame;

public class XMLFile {

	private File xmlFile;
	private String dbUrl = "";
	private String dbUser = "";
	private String dbPassword = "";
	private Connection connection = null;
	
	public XMLFile(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	public boolean checkXMLFile() {
		boolean check = false;
		
		if(xmlFile.exists()) 
			check = true;
			
		return check;
	}
	
	public Connection getConnectionWithXML(DataBaseConfigFrame dbConfigFrame) {
		
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
				
				connection = Conexion.getInstanceConnection(dbUrl, dbUser, dbPassword);
				
				if(connection != null) {
					connection = Conexion.getInstanceConnection(dbUrl, dbUser, dbPassword);
				} else {
					JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos, por favor," + 
							"compruebe que los parámetros están introducidos\ncorrectamente y que el servidor está operativo.",
								"Error", JOptionPane.ERROR_MESSAGE);
					dbConfigFrame.setVisible(true);
					//new Controller(dbConfigFrame);
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
		
		return connection;
	
	}
	
	public boolean createConfigXMLFile() {
		boolean valid = false;
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
			
			transformerCreate.setOutputProperty(OutputKeys.INDENT, "yes");
			transformerCreate.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource sourceCreate = new DOMSource(docCreate);
			StreamResult resultCreate = new StreamResult(new File("config/dbConfig.xml"));
			
			transformerCreate.transform(sourceCreate, resultCreate);
			
				
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
		
		valid = true;
		return valid;
				
	}
	
	public void modifyXMLFile(String dbUrl, String dbUser, String dbPassword) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			NodeList nList = doc.getElementsByTagName("dbConfig");
			
			Node nNode = nList.item(0);
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				
				element.getElementsByTagName("dbUrl").item(0).setTextContent(dbUrl);
				element.getElementsByTagName("dbUser").item(0).setTextContent(dbUser);
				element.getElementsByTagName("dbPassword").item(0).setTextContent(dbPassword);
					
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("config/dbConfig.xml"));
				transformer.transform(source, result);
				
			}
			//PROVISIONAL
			new App();

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
	
		
	}

	
}
