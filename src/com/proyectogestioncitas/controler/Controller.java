package com.proyectogestioncitas.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.proyectogestioncitas.model.Conexion;
import com.proyectogestioncitas.model.DataBaseController;
import com.proyectogestioncitas.model.XMLFile;
import com.proyectogestioncitas.model.dao.AppointmentDAO;
import com.proyectogestioncitas.model.dao.ClientDAO;
import com.proyectogestioncitas.model.dao.MedicalCenterDAO;
import com.proyectogestioncitas.view.AdministrationFrame;
import com.proyectogestioncitas.view.CheckTableErrorDialog;
import com.proyectogestioncitas.view.CreateAdminFrame;
import com.proyectogestioncitas.view.CreateCenterDialog;
import com.proyectogestioncitas.view.DataBaseConfigFrame;
import com.proyectogestioncitas.view.LoginFrame;
import com.proyectogestioncitas.model.pojo.Appointment;
import com.proyectogestioncitas.model.pojo.Client;

public class Controller implements ActionListener {

	private Connection dbConnection;
	private CreateAdminFrame createAdminFrame;
	private DataBaseConfigFrame dbConfigFrame;
	private LoginFrame loginFrame;
	private CheckTableErrorDialog chkTableDialog;
	private AdministrationFrame adminFrame;
	private CreateCenterDialog cCenterDialog;
	private Client client;
	private ClientDAO clientDao;
	private AppointmentDAO appDao;
	private MedicalCenterDAO centerDao;
	
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

	public Controller(AdministrationFrame adminFrame, ClientDAO clientDao, AppointmentDAO appDao, MedicalCenterDAO centerDao){
		this.adminFrame = adminFrame;
		this.clientDao = clientDao;
		this.appDao = appDao;
		this.centerDao = centerDao;
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
		System.out.println("Has entrado en el action performed.");
		switch (e.getActionCommand()) {
		//Client
		case "Add client":
			getActionAddClientBtn();
			break;
		case "Delete client":
			getActionDeleteClientBtn();
			break;
		case "Update client":
			getActionUpdateClientBtn();
			break;
		case "Save client":
			getActionSaveClientBtn();
			break;
		//App
		case "Add app":
			getActionAddAppBtn();
			break;
		case "Delete app":
			getActionDeleteAppBtn();
			break;
		case "Update app":
			getActionUpdateAppBtn();
			break;
		case "Save app":
			getActionSaveAppBtn();
			break;

		default:
			break;
		}
		
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
		//Client table
		adminFrame.getBtnCCAddNew().addActionListener(escuchador);
		adminFrame.getBtnCCUpdate().addActionListener(escuchador);
		adminFrame.getBtnCCDelete().addActionListener(escuchador);
		adminFrame.getBtnCCSave().addActionListener(escuchador);
		
		//Appointment table
		adminFrame.getBtnCCAAddNew().addActionListener(escuchador);
		adminFrame.getBtnCCAUpdate().addActionListener(escuchador);
		adminFrame.getBtnCCADelete().addActionListener(escuchador);
		adminFrame.getBtnCCASave().addActionListener(escuchador);
		
		adminFrame.getTableCCClient().getSelectionModel().addListSelectionListener(e -> {
			setTextCCAdministrationFrame();
			/**
			 * new AppointmentTableModel().addAppointmetsToTableData(new AppointmentDAO(), this.returnsClientWithRowParams());
			 * 
			 * Para poder obtener en la tabla de citas las citas de un cliente en concreto.
			 * Necesitamos relacion entre cliente y cita a parte de pasarle al table model esa 
			 * relacion(dni) para poder mostrar sus citas.
			 */
			
		});
		
		adminFrame.getTableCCAAppointment().getSelectionModel().addListSelectionListener(e -> {
			setTextCCAppAdministrationFrame();
		});
		
		adminFrame.getTableMedicalCenter().getSelectionModel().addListSelectionListener(e -> {
			setTextMCenterAdministrationFrame();
		});
		
	}
	
	
	public void setTextCCAdministrationFrame(){
		/**
		 * Una vez solucionado la RELACION ENTRE CLIENTE Y CITA descomentar codigo.
		 */
		try{
			System.out.println("Ahora has entrado al selection listener de la tabla cliente.");
			
			int selectedRow = adminFrame.getTableCCClient().getSelectedRow();
			System.out.println(selectedRow);
			
			//Client("name", "surname", "id", LocalDate.now(), "email", "pass", 1))
			Object name = adminFrame.getTableCCClient().getValueAt(selectedRow, 0);
			Object surnames = adminFrame.getTableCCClient().getValueAt(selectedRow, 1);
			Object id = adminFrame.getTableCCClient().getValueAt(selectedRow, 2);
			Object birthDate = adminFrame.getTableCCClient().getValueAt(selectedRow, 3);
			Object email = adminFrame.getTableCCClient().getValueAt(selectedRow, 4);
			Object password = adminFrame.getTableCCClient().getValueAt(selectedRow, 5);
			Object assCenter = adminFrame.getTableCCClient().getValueAt(selectedRow, 6);
			
			adminFrame.getTextField_CCBirthDate().setText(birthDate.toString());
			adminFrame.getTextField_CCName().setText(name.toString());
			adminFrame.getTextField_CCSurname().setText(surnames.toString());
			adminFrame.getTextField_CCdni().setText(id.toString());
			adminFrame.getTextField_CCEmail().setText(email.toString());
			adminFrame.getTextField_CCPassword().setText(password.toString());
			adminFrame.getTextField_CCAssCenter().setText(assCenter.toString());
			
			//Client client = new Client(name.toString(), surnames.toString(), id.toString(), birthDate.toString(), 
			//email.toString(), password.toString());
			//returnsClientWithRowParams(client);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
	
	public void setTextCCAppAdministrationFrame(){
		System.out.println("Has entrado a setTextCCAppAdminFrame");
		try{
			System.out.println("Ahora has entrado al selection listener de la tabla appointment");
			
			int selectedRow = adminFrame.getTableCCAAppointment().getSelectedRow();
			System.out.println(selectedRow);
				
			//new Appointment(day, time, associatedCenter, doctorName)
			Object day = adminFrame.getTableCCAAppointment().getValueAt(selectedRow, 0);
			Object hour = adminFrame.getTableCCAAppointment().getValueAt(selectedRow, 1);
			Object assCenter = adminFrame.getTableCCAAppointment().getValueAt(selectedRow, 2);
			Object doctorsName = adminFrame.getTableCCAAppointment().getValueAt(selectedRow, 3);
			
			adminFrame.getTextCCAField_Date().setText(day.toString());
			adminFrame.getTextCCAField_Hour().setText(hour.toString());
			adminFrame.getTextField_CCAAssCenter().setText(assCenter.toString());
			adminFrame.getTextField_CCADoctorName().setText(doctorsName.toString());
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void setTextMCenterAdministrationFrame(){
		System.out.println("Has entrado a setTextMCenterAdministrationFrame()");
		try{
			System.out.println("Ahora has entrado al selection listener de la tabla medical center");
			
			int selectedRow = adminFrame.getTableMedicalCenter().getSelectedRow();
			System.out.println(selectedRow);
				
			//new MedicalCenter()
			Object day = adminFrame.getTableCCAAppointment().getValueAt(selectedRow, 0);
			Object hour = adminFrame.getTableCCAAppointment().getValueAt(selectedRow, 1);
			
			adminFrame.getTextCCAField_Date().setText(day.toString());
			adminFrame.getTextCCAField_Hour().setText(hour.toString());
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//Client table btn
	private void getActionAddClientBtn(){
		System.out.println("Has entrado a getActionAddClientBtn()");
		//TextField
		adminFrame.getTextField_CCBirthDate().setEditable(true);
		adminFrame.getTextField_CCBirthDate().setText("");
		adminFrame.getTextField_CCdni().setEditable(true);
		adminFrame.getTextField_CCdni().setText("");
		adminFrame.getTextField_CCEmail().setEditable(true);
		adminFrame.getTextField_CCEmail().setText("");
		adminFrame.getTextField_CCName().setEditable(true);
		adminFrame.getTextField_CCName().setText("");
		adminFrame.getTextField_CCPassword().setEditable(true);
		adminFrame.getTextField_CCPassword().setText("");
		adminFrame.getTextField_CCSurname().setEditable(true);
		adminFrame.getTextField_CCSurname().setText("");
		adminFrame.getTextField_CCAssCenter().setEditable(true);
		adminFrame.getTextField_CCAssCenter().setText("");
		
		System.out.println(adminFrame.getTextField_CCdni().isEditable()+" holi");
		//Other btns
		setCCBtnConfiguration(false);

	}
	private void getActionDeleteClientBtn(){
		client = new Client(adminFrame.getTextField_CCName().getText(), 
									adminFrame.getTextField_CCSurname().getText(), 
									adminFrame.getTextField_CCdni().getText(),
									adminFrame.getTextField_CCBirthDate().getText(),
									adminFrame.getTextField_CCEmail().getText(),
									adminFrame.getTextField_CCPassword().getText(),
									adminFrame.getTextField_CCAAssCenter().getText());
		System.out.println(client);
		//clientDao.deleteClientByID(client.getId());
		JOptionPane.showConfirmDialog(null, "The user with ID: '" + client.getId() + "' was deleted.", 
				"An user was deleted", JOptionPane.DEFAULT_OPTION);
	}
	private void getActionUpdateClientBtn(){
		//TextField
		adminFrame.getTextField_CCBirthDate().setEditable(true);
		adminFrame.getTextField_CCEmail().setEditable(true);
		adminFrame.getTextField_CCName().setEditable(true);
		adminFrame.getTextField_CCPassword().setEditable(true);
		adminFrame.getTextField_CCSurname().setEditable(true);
		adminFrame.getTextField_CCAssCenter().setEditable(true);
		
		//Other btns
		setCCBtnConfiguration(false);
	}
	
	private void getActionSaveClientBtn(){
		if(adminFrame.getTextField_CCdni().isEditable()){
			client = new Client(adminFrame.getTextField_CCName().getText(), 
					adminFrame.getTextField_CCSurname().getText(), 
					adminFrame.getTextField_CCdni().getText(),
					adminFrame.getTextField_CCBirthDate().getText(),
					adminFrame.getTextField_CCEmail().getText(),
					adminFrame.getTextField_CCPassword().getText(),
					adminFrame.getTextField_CCAAssCenter().getText());

			//clientDao.createNewClient(client);
			JOptionPane.showConfirmDialog(null, "The user with ID: '" + client.getId() + "' was created.", 
					"An user was created", JOptionPane.DEFAULT_OPTION);
		}else{
			client = new Client(adminFrame.getTextField_CCName().getText(), 
					adminFrame.getTextField_CCSurname().getText(), 
					adminFrame.getTextField_CCdni().getText(),
					adminFrame.getTextField_CCBirthDate().getText(),
					adminFrame.getTextField_CCEmail().getText(),
					adminFrame.getTextField_CCPassword().getText(),
					adminFrame.getTextField_CCAAssCenter().getText());

			//clientDao.updateClient(client);
			JOptionPane.showConfirmDialog(null, "The user with ID: '" + client.getId() + "' was updated.", 
					"An user was updated", JOptionPane.DEFAULT_OPTION);
		}
		setCCBtnConfiguration(true);
	}
	
	//Appointment table btn
	private void getActionAddAppBtn(){
		
	}
	private void getActionDeleteAppBtn(){
		
	}
	private void getActionUpdateAppBtn(){
		
	}
	private void getActionSaveAppBtn(){
		
	}
	
	private void setCCBtnConfiguration(Boolean booleano){
		adminFrame.getBtnCCAAddNew().setEnabled(booleano);
		adminFrame.getBtnCCAddNew().setEnabled(booleano);
		adminFrame.getBtnCCADelete().setEnabled(booleano);
		adminFrame.getBtnCCSave().setEnabled(!booleano);
		adminFrame.getBtnCCAUpdate().setEnabled(booleano);
		adminFrame.getBtnCCDelete().setEnabled(booleano);
		adminFrame.getBtnCCAUpdate().setEnabled(booleano);
		adminFrame.getBtnCCUpdate().setEnabled(booleano);
	}
	/**
	 * NO BORRAAAAAAAAR
	 * public Client returnsClientWithRowParams(Client client){
	 * 		return client;
	 * }
	 */

}
