package com.proyectogestioncitas.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import com.proyectogestioncitas.model.Conexion;
import com.proyectogestioncitas.model.DataBaseController;
import com.proyectogestioncitas.model.XMLFile;
import com.proyectogestioncitas.view.AdministrationFrame;
import com.proyectogestioncitas.view.CheckTableErrorDialog;
import com.proyectogestioncitas.view.CreateAdminFrame;
import com.proyectogestioncitas.view.CreateCenterDialog;
import com.proyectogestioncitas.view.DataBaseConfigFrame;
import com.proyectogestioncitas.view.LoginFrame;
import com.proyectogestioncitas.model.pojo.Client;

public class Controller implements ActionListener {

	private Connection dbConnection;
	private CreateAdminFrame createAdminFrame;
	private DataBaseConfigFrame dbConfigFrame;
	private LoginFrame loginFrame;
	private CheckTableErrorDialog chkTableDialog;
	private AdministrationFrame adminFrame;
	private CreateCenterDialog cCenterDialog;
	
	public Controller(DataBaseConfigFrame dbConfigFrame) {
		this.dbConfigFrame = dbConfigFrame;
		actionListenerConfigFrame(this);
		
	}
	
	public Controller(CheckTableErrorDialog chkTableDialog, Connection dbConnection) {
		this.chkTableDialog = chkTableDialog;
		this.dbConnection = dbConnection;
		actionListenerTableErrorDialog(this);
	}
	
	public Controller(CreateAdminFrame createAdminFrame, Connection dbConnection) {
		this.createAdminFrame = createAdminFrame;
		this.dbConnection = dbConnection;
		actionListenerCreateAdminFrame(this);
	}

	public Controller(AdministrationFrame adminFrame){
		this.adminFrame = adminFrame;
		actionListenerAdministrationFrame(this);
	}
	
	public Controller(CreateCenterDialog cCenterDialog) {
		this.cCenterDialog = cCenterDialog;
		actionListenerCenterDialog(this);
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
		
		if(e.getActionCommand().equals("Create")) {
			String login = createAdminFrame.getTextField_CALogin().getText();
			String password = new String(createAdminFrame.getPasswordField_CAPassword().getPassword());
			String repPassword = new String(createAdminFrame.getPasswordField_CARepeat().getPassword());
			
			if(login.length() > 20 || password.length() > 20 || repPassword.length() > 20) {
				JOptionPane.showMessageDialog(null, "Los datos introducidos deben ser de menos de 20 caracteres.",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				if(password.equals(repPassword)) {
					DataBaseController dbController = new DataBaseController(dbConnection);
					
					if(dbController.checkLogins(login)) {
						dbController.createNewAdmin(login, password);
						createAdminFrame.dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "El login introducido ya existe", "Error",
								JOptionPane.ERROR_MESSAGE);
						
					}
						
					
				} else {
					JOptionPane.showMessageDialog(null, "Las contraseñas deben coincidir.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}

	}
	
	public void actionListenerConfigFrame(ActionListener escuchador){
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
		chkTableDialog.getCancelButton().addActionListener(escuchador);
		chkTableDialog.getRepairTableButton().addActionListener(escuchador);
		
	}
	
	public void actionListenerCreateAdminFrame(ActionListener escuchador) {
		createAdminFrame.getBtnCreate().addActionListener(escuchador);
		createAdminFrame.getTextField_CALogin().addActionListener(escuchador);
		createAdminFrame.getPasswordField_CAPassword().addActionListener(escuchador);
		createAdminFrame.getPasswordField_CARepeat().addActionListener(escuchador);
		
	}
	
	public void actionListenerCenterDialog(ActionListener escuchador) {
		cCenterDialog.getBtnCreate().addActionListener(escuchador);
		cCenterDialog.getBtnCancel().addActionListener(escuchador);
		
	}
	
	public void actionListenerAdministrationFrame(ActionListener escuchador){
		setTextCCAdministrationFrame();
	}
	
	
	public void setTextCCAdministrationFrame(){
		System.out.println("Has entrado");
		JTable adminCCTable = adminFrame.getTableCCClient();
	    adminCCTable.setModel(new ClientTableModel());
		adminCCTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//adminCCTable.getSelectionModel().addListSelectionListener(e -> {

				System.out.println("Ahora has entrado al selection listener");
				int selectedRow = adminCCTable.getSelectedRow();
				System.out.println(selectedRow);
				/*
					Object name = adminCCTable.getValueAt(selectedRow, 0);
					Object surnames = adminCCTable.getValueAt(selectedRow, 1);
					Object id = adminCCTable.getValueAt(selectedRow, 1);
					Object birthDate = adminCCTable.getValueAt(selectedRow, 1);
					
					adminFrame.getTextField_CCBirthDate().setText(birthDate.toString());
					adminFrame.getTextField_CCName().setText(name.toString());
					adminFrame.getTextField_CCSurname().setText(surnames.toString());
					adminFrame.getTextField_CCdni().setText(id.toString());
*/
		//});
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        adminCCTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
	}
	

}
