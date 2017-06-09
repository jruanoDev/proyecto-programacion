package com.proyectogestioncitas.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;

import javax.swing.JOptionPane;

import com.proyectogestioncitas.model.Conexion;
import com.proyectogestioncitas.model.DataBaseController;
import com.proyectogestioncitas.model.XMLFile;
import com.proyectogestioncitas.view.CheckTableErrorDialog;
import com.proyectogestioncitas.view.CreateAdminFrame;
import com.proyectogestioncitas.view.DataBaseConfigFrame;
import com.proyectogestioncitas.view.LoginFrame;

public class Controller implements ActionListener {

	private Connection dbConnection;
	private CreateAdminFrame createAdminFrame;
	private DataBaseConfigFrame dbConfigFrame;
	private LoginFrame loginFrame;
	private CheckTableErrorDialog chkTableDialog;
	
	public Controller(DataBaseConfigFrame dbConfigFrame) {
		super();
		this.dbConfigFrame = dbConfigFrame;
		actionListenerConfigFrame(this);
		
	}
	
	
	public Controller(CheckTableErrorDialog chkTableDialog, Connection dbConnection) {
		this.chkTableDialog = chkTableDialog;
		this.dbConnection = dbConnection;
		actionListenerTableErrorDialog(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		/*getCreateAdminFrameAction(e);
		getDBConfigFrameAction(e);
		getLoginFrameAction(e);*/
		
		if(e.getActionCommand().equals("Validate")) {
			String dbUrl = dbConfigFrame.getTextField_DbUrl().getText();
			String dbUser = dbConfigFrame.getTextField_DbName().getText();
			String dbPassword = dbConfigFrame.getTextField_DbPassword().getText();
			
			XMLFile xmlFile = new XMLFile(new File("config/dbConfig.xml"));
			
			dbConnection = Conexion.getInstanceConnection(dbUrl, dbUser, dbPassword);
			
			if(dbConnection != null) {
				
				xmlFile.modifyXMLFile(dbUrl, dbUser, dbPassword);
				dbConfigFrame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos, por favor," + 
						"compruebe que los parámetros están introducidos\ncorrectamente y que el servidor está operativo.",
							"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getActionCommand().equals("OK")) {
			if(dbConnection != null) {
				DataBaseController dbController = new DataBaseController(dbConnection);
				chkTableDialog.dispose();
				dbController.createDataBaseStructure();
				
			}
		}
		
		if(e.getActionCommand().equals("Cancel")) {
			JOptionPane.showMessageDialog(null, "El programa se cerrará, por favor, compruebe la base de datos manualmente.",
						"Información", JOptionPane.INFORMATION_MESSAGE);
			
			System.exit(1);
		}

	}
	
	public void actionListenerConfigFrame(ActionListener escuchador){
		//Create AdminFrameComponents
		/*createAdminFrame.getBtnCreate().addActionListener(escuchador);
		createAdminFrame.getTextField_CALogin().addActionListener(escuchador);
		createAdminFrame.getPasswordField_CAPassword().addActionListener(escuchador);
		createAdminFrame.getPasswordField_CARepeat().addActionListener(escuchador);*/
		
		//Create DataBaseConfigComponents
		dbConfigFrame.getBtnDbValidate().addActionListener(escuchador);
		dbConfigFrame.getTextField_DbName().addActionListener(escuchador);
		dbConfigFrame.getTextField_DbPassword().addActionListener(escuchador);
		dbConfigFrame.getTextField_DbUrl().addActionListener(escuchador);
		
		//Create LoginFrameComponents
		/*loginFrame.getBtnLAccept().addActionListener(escuchador);
		loginFrame.getBtnRSend().addActionListener(escuchador);
		loginFrame.getTextField_LUsername().addActionListener(escuchador);
		loginFrame.getTextField_RBirthDate().addActionListener(escuchador);
		loginFrame.getTextField_REmail().addActionListener(escuchador);
		loginFrame.getTextField_RID().addActionListener(escuchador);
		loginFrame.getTextField_RName().addActionListener(escuchador);
		loginFrame.getPasswordField_LPassword().addActionListener(escuchador);
		loginFrame.getPasswordField_RPassword().addActionListener(escuchador);
		loginFrame.getPasswordField_RRepeat().addActionListener(escuchador);*/
		
		
		
	}
	
	public void actionListenerTableErrorDialog(ActionListener escuchador) {
		//Create CheckTableErrorDialog
				chkTableDialog.getCancelButton().addActionListener(escuchador);
				chkTableDialog.getRepairTableButton().addActionListener(escuchador);
	}
	
	/*public void getCreateAdminFrameAction(ActionEvent e){
		if(e.getActionCommand().equals("Create")){
			
		}
	}
	
	public void getDBConfigFrameAction(ActionEvent e){
		if(e.getActionCommand().equals("Validate")){
			
		}
	}
	
	public void getLoginFrameAction(ActionEvent e){
		if(e.getActionCommand().equals("Accept")){
			
		}
		if(e.getActionCommand().equals("Send")){
			
		}
	}*/

}
