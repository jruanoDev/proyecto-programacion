package com.proyectogestioncitas.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.JTable;

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
import com.proyectogestioncitas.model.pojo.Client;
import com.proyectogestioncitas.model.pojo.MedicalCenter;

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
	
	public Controller(CreateCenterDialog cCenterDialog, Connection dbConnection) {
		this.cCenterDialog = cCenterDialog;
		this.dbConnection = dbConnection;
		actionListenerCenterDialog(this);
	}
	
	public Controller(LoginFrame loginFrame, Connection dbConnection) {
		this.loginFrame = loginFrame;
		this.dbConnection = dbConnection;
		actionListenerLoginFrame(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*getCreateAdminFrameAction(e);
		getDBConfigFrameAction(e);
		getLoginFrameAction(e);*/
		//System.out.println("Has entrado en el action performed.");
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

		//Center
		case "Add center":
			getActionAddCenterBtn();
			break;
		case "Delete center":
			getActionDeleteCenterBtn();
			break;
		case "Update center":
			getActionUpdateCenterBtn();
			break;
		case "Save center":
			getActionSaveCenterBtn();
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
		
		if(e.getActionCommand().equals("Exit")) {
			System.exit(1);
		}
		
		if(e.getActionCommand().equals("Create center")) {
			String id = cCenterDialog.getTextFieldID().getText();
			String name = cCenterDialog.getTextFieldName().getText();
			String address = cCenterDialog.getTextFieldAddress().getText();
			String pCode = cCenterDialog.getTextFieldPCode().getText();
			String phoneNumber = cCenterDialog.getTextFieldPNumber().getText();
						
			if(id != "" || name != "" || address != "" || pCode != "" || phoneNumber != "") {
				DataBaseController dbController = new DataBaseController(dbConnection);
				dbController.createNewCenter(id, name, address, pCode, phoneNumber);
				
				cCenterDialog.dispose();
				
			} else {
				JOptionPane.showMessageDialog(null, "Los campos no pueden estar vac�os.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if(e.getActionCommand().equals("Accept")) {
			DataBaseController dbController = new DataBaseController(dbConnection);
			
			String login = loginFrame.getTextField_LUsername().getText();
			
			@SuppressWarnings("deprecation")
			String password = loginFrame.getPasswordField_LPassword().getText();
						
			if(dbController.logUser(login, password)) {
				System.out.println("Abrir aqui el frame de cliente.");
			}
		}
		
		if(e.getActionCommand().equals("Send")) {
			String email = loginFrame.getTextField_REmail().getText();
			String name = loginFrame.getTextField_RName().getText();
			String surname = loginFrame.getTextField_RSurnames().getText();
			String id = loginFrame.getTextField_RID().getText();
			String password = loginFrame.getPasswordField_RPassword().getText();
			String repPassword = loginFrame.getPasswordField_RRepeat().getText();
			String birthDate = loginFrame.getTextField_RBirthDate().getText();
			
			DataBaseController dbController = new DataBaseController(dbConnection);
			
			String emailRegExp = "[a-zA-Z0-9_-]*@[a-zA-Z0-9_-]*.[a-z]{1,3}+";
			
			if(email != "" && name != "" && surname != "" && id != "" && password != "" && repPassword != "" && birthDate != "") {
				if(password.equals(repPassword)) {
					if(email.matches(emailRegExp)) {
						dbController.registerUser(emailRegExp, name, surname, id, repPassword, birthDate);
						
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Las contrase�as no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Los campos no son correctos, no pueden estar vac�os.", "Null",
						JOptionPane.ERROR_MESSAGE);
			}
			
		}

	}
	
	public void actionListenerConfigFrame(ActionListener escuchador){
		dbConfigFrame.getBtnDbValidate().addActionListener(escuchador);
		dbConfigFrame.getTextField_DbName().addActionListener(escuchador);
		dbConfigFrame.getTextField_DbPassword().addActionListener(escuchador);
		dbConfigFrame.getTextField_DbUrl().addActionListener(escuchador);
		
	}
	
	public void actionListenerLoginFrame(ActionListener escuchador) {
		loginFrame.getBtnLAccept().addActionListener(escuchador);
		loginFrame.getBtnRSend().addActionListener(escuchador);
		loginFrame.getTextField_LUsername().addActionListener(escuchador);
		loginFrame.getTextField_RBirthDate().addActionListener(escuchador);
		loginFrame.getTextField_REmail().addActionListener(escuchador);
		loginFrame.getTextField_RID().addActionListener(escuchador);
		loginFrame.getTextField_RName().addActionListener(escuchador);
		loginFrame.getPasswordField_LPassword().addActionListener(escuchador);
		loginFrame.getPasswordField_RPassword().addActionListener(escuchador);
		loginFrame.getPasswordField_RRepeat().addActionListener(escuchador);
		loginFrame.getBtnAdminLogin().addActionListener(escuchador);
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
		createAdminFrame.getBtnExit().addActionListener(escuchador);
		
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
		
		//Medical center table
		adminFrame.getBtnMCAddNew().addActionListener(escuchador);
		adminFrame.getBtnMCDelete().addActionListener(escuchador);
		adminFrame.getBtnMCSave().addActionListener(escuchador);
		adminFrame.getBtnMCUpdate().addActionListener(escuchador);
		
		
		adminFrame.getTableCCClient().getSelectionModel().addListSelectionListener(e -> {
			adminFrame.getBtnCCUpdate().setEnabled(true);
			adminFrame.getBtnCCDelete().setEnabled(true);
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
			adminFrame.getBtnCCAUpdate().setEnabled(true);
			adminFrame.getBtnCCADelete().setEnabled(true);
			setTextCCAppAdministrationFrame();
		});
		
		adminFrame.getTableMedicalCenter().getSelectionModel().addListSelectionListener(e -> {
			adminFrame.getBtnMCUpdate().setEnabled(true);
			adminFrame.getBtnMCDelete().setEnabled(true);
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
				
			Object id = adminFrame.getTableMedicalCenter().getValueAt(selectedRow, 0);
			Object location = adminFrame.getTableMedicalCenter().getValueAt(selectedRow, 1);
			Object name = adminFrame.getTableMedicalCenter().getValueAt(selectedRow, 2);
			Object cp = adminFrame.getTableMedicalCenter().getValueAt(selectedRow, 3);
			Object phone = adminFrame.getTableMedicalCenter().getValueAt(selectedRow, 4);
			
			adminFrame.getTextField_MCCenterID().setText(id.toString());
			adminFrame.getTextField_MCLocation().setText(location.toString());
			adminFrame.getTextField_MCCenterName().setText(name.toString());
			adminFrame.getTextField_MCPostalCode().setText(cp.toString());
			adminFrame.getTextField_MCPhone().setText(phone.toString());
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//Client table btn
	private void getActionAddClientBtn(){
		System.out.println("Has entrado a getActionAddClientBtn()");
		//TextField
		setCCTextFields(true, "all");
		//Other btns
		setCCBtnConfiguration(false);
		//JTable
		adminFrame.getTableCCClient().setEnabled(false);

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
		//JTable
		adminFrame.getTableCCClient().setEnabled(false);
		
		//TextField
		setCCTextFields(true, "update");
		
		//Other btns
		setCCBtnConfiguration(false);
	}
	
	private void getActionSaveClientBtn(){
		//System.out.println("Editable? "+adminFrame.getTextField_CCdni().isEditable());
		if(areCCTextFieldsCorrectlyWrited()){
			JOptionPane.showMessageDialog(null, "You need to fill all the fields to create or update a client.", 
					"Cannot create or update a invalid user.", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
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
			
		} else {
			
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
		setCCTextFields(false, "all");
		setCCBtnConfiguration(true);
		adminFrame.getTableCCClient().setEnabled(true);
		adminFrame.getBtnCCUpdate().setEnabled(false);
		adminFrame.getBtnCCDelete().setEnabled(false);
	}
	
	//Appointment table btn
	private void getActionAddAppBtn(){
		
	}
	private void getActionDeleteAppBtn(){
		
	}
	private void getActionUpdateAppBtn(){
		
	}
	private void getActionSaveAppBtn(){
		adminFrame.getBtnCCAUpdate().setEnabled(false);
		adminFrame.getBtnCCADelete().setEnabled(false);
	}
	
	//Medical center table btn actions
	private void getActionAddCenterBtn(){
		System.out.println("Click en add center");
		adminFrame.getTableMedicalCenter().setEnabled(false);
		setMCTextFields(true, "all");
		setMCBtnConfiguration(false);
	}
	
	private void getActionDeleteCenterBtn(){
		String id = adminFrame.getTextField_MCCenterID().getText();		
		//centerDao.deleteCenterByID(id);
		
		JOptionPane.showMessageDialog(null, "The center with ID:'" + id + "' was deleted.", 
				"Deleted center", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void getActionUpdateCenterBtn(){
		System.out.println("Click en update center");
		adminFrame.getTableMedicalCenter().setEnabled(false);
		setMCTextFields(true, "update");
		setMCBtnConfiguration(false);
	}
	
	private void getActionSaveCenterBtn(){
		adminFrame.getBtnMCUpdate().setEnabled(false);
		adminFrame.getBtnMCDelete().setEnabled(false);
		if(adminFrame.getTextField_MCCenterID().getText().equals("") || adminFrame.getTextField_MCCenterName().getText().equals("")
				|| adminFrame.getTextField_MCLocation().getText().equals("") || adminFrame.getTextField_MCPhone().getText().equals("")
				|| adminFrame.getTextField_MCPostalCode().getText().equals("")){
			JOptionPane.showMessageDialog(null, "You cannot create or update a center without all the fields correctly filled.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(adminFrame.getTextField_MCCenterID().isEditable()){
			MedicalCenter center = new MedicalCenter(adminFrame.getTextField_MCCenterID().getText(), 
					adminFrame.getTextField_MCLocation().getText(), 
					adminFrame.getTextField_MCCenterName().getText(), 
					adminFrame.getTextField_MCPostalCode().getText(), 
					adminFrame.getTextField_MCPhone().getText());
			
			//centerDao.createNewCenter(center);
			System.out.println(center);
			
			JOptionPane.showMessageDialog(null, "A center was added.", 
					"Created center", JOptionPane.INFORMATION_MESSAGE);
		}else{
			MedicalCenter center = new MedicalCenter(adminFrame.getTextField_MCCenterID().getText(), 
					adminFrame.getTextField_MCLocation().getText(), 
					adminFrame.getTextField_MCCenterName().getText(), 
					adminFrame.getTextField_MCPostalCode().getText(), 
					adminFrame.getTextField_MCPhone().getText());
			
			//centerDao.updateCenter(center);
			System.out.println(center);
			
			JOptionPane.showMessageDialog(null, "A center was updated.", 
					"Updated center", JOptionPane.INFORMATION_MESSAGE);
		}
		//code
		
		adminFrame.getTableMedicalCenter().setEnabled(true);
		setMCTextFields(false, "save");
		setMCBtnConfiguration(true);
		
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
	
	private void setCCTextFields(Boolean booleano, String status){
		if(status.equals("all")){
			adminFrame.getTextField_CCdni().setEditable(booleano);	
			
			adminFrame.getTextField_CCdni().setText("");
			adminFrame.getTextField_CCAssCenter().setText("");
			adminFrame.getTextField_CCSurname().setText("");
			adminFrame.getTextField_CCPassword().setText("");
			adminFrame.getTextField_CCName().setText("");
			adminFrame.getTextField_CCEmail().setText("");
			adminFrame.getTextField_CCBirthDate().setText("");
		}
		adminFrame.getTextField_CCBirthDate().setEditable(booleano);			
		adminFrame.getTextField_CCEmail().setEditable(booleano);			
		adminFrame.getTextField_CCName().setEditable(booleano);			
		adminFrame.getTextField_CCPassword().setEditable(booleano);			
		adminFrame.getTextField_CCSurname().setEditable(booleano);			
		adminFrame.getTextField_CCAssCenter().setEditable(booleano);
		
	}
	
	private boolean areCCTextFieldsCorrectlyWrited(){
		boolean success = false;
		if(adminFrame.getTextField_CCdni().getText().equals("") || adminFrame.getTextField_CCBirthDate().getText().equals("") || 
				adminFrame.getTextField_CCEmail().getText().equals("") || adminFrame.getTextField_CCName().getText().equals("") || 
				adminFrame.getTextField_CCPassword().getText().equals("") || adminFrame.getTextField_CCSurname().getText().equals("") || 
				adminFrame.getTextField_CCAssCenter().getText().equals(""))
			success = true;
		return success;
	}
	
	private void setMCTextFields(Boolean booleano, String status){
		if(status.equals("all")){
			adminFrame.getTextField_MCCenterID().setEditable(booleano);
			adminFrame.getTextField_MCCenterID().setText("");
			adminFrame.getTextField_MCCenterName().setText("");
			adminFrame.getTextField_MCLocation().setText("");
			adminFrame.getTextField_MCPhone().setText("");
			adminFrame.getTextField_MCPostalCode().setText("");
		}
		if(status.equals("save")){
			adminFrame.getTextField_MCCenterID().setEditable(booleano);
		}
		
		adminFrame.getTextField_MCCenterName().setEditable(booleano);		
		adminFrame.getTextField_MCLocation().setEditable(booleano);		
		adminFrame.getTextField_MCPhone().setEditable(booleano);		
		adminFrame.getTextField_MCPostalCode().setEditable(booleano);
		
	}
	
	private void setMCBtnConfiguration(Boolean booleano){
		adminFrame.getBtnMCAddNew().setEnabled(booleano);
		adminFrame.getBtnMCDelete().setEnabled(booleano);
		adminFrame.getBtnMCUpdate().setEnabled(booleano);
		adminFrame.getBtnMCSave().setEnabled(!booleano);
		
	}

	/**
	 * NO BORRAAAAAAAAR
	 * public Client returnsClientWithRowParams(Client client){
	 * 		return client;
	 * }
	 */

}
