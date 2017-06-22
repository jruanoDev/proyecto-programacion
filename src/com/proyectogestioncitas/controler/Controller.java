package com.proyectogestioncitas.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;

import com.proyectogestioncitas.app.App;
import com.proyectogestioncitas.model.ApplyForAnAppointmentConfiguration;
import com.proyectogestioncitas.model.Conexion;
import com.proyectogestioncitas.model.DataBaseController;
import com.proyectogestioncitas.model.XMLFile;
import com.proyectogestioncitas.model.dao.AppointmentDAO;
import com.proyectogestioncitas.model.dao.ClientDAO;
import com.proyectogestioncitas.model.dao.MedicalCenterDAO;
import com.proyectogestioncitas.view.AdminLoginDialog;
import com.proyectogestioncitas.view.AdministrationFrame;
import com.proyectogestioncitas.view.CheckTableErrorDialog;
import com.proyectogestioncitas.view.ClientFrame;
import com.proyectogestioncitas.view.CreateAdminFrame;
import com.proyectogestioncitas.view.CreateCenterDialog;
import com.proyectogestioncitas.view.DataBaseConfigFrame;
import com.proyectogestioncitas.view.LoginFrame;
import com.proyectogestioncitas.model.pojo.Appointment;
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
	private ClientFrame clientFrame;
	private static Client client;
	private ClientDAO clientDao;
	private AppointmentDAO appDao;
	private MedicalCenterDAO centerDao;
	private String btnStatus = "";
	private AppointmentTableModel appTableModel;
	public static String clientId;

	private JTable tableCCClient;

	private AdminLoginDialog adminLoginDialog;
	
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

	public Controller(AdministrationFrame adminFrame, ClientDAO clientDao, MedicalCenterDAO centerDao, AppointmentDAO appDAO) {
		this.appDao = appDAO;
		this.adminFrame = adminFrame;
		this.clientDao = clientDao;
		this.centerDao = centerDao;
		this.appTableModel = appTableModel;
		
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
	
	public Controller(AdminLoginDialog adminLoginDialog, Connection dbConnection) {
		this.adminLoginDialog = adminLoginDialog;
		this.dbConnection = dbConnection;
		actionListenerAdminLogin(this);
	}
	
	public Controller(ClientFrame clientFrame, ClientDAO clientDao){
		this.clientFrame = clientFrame;
		this.clientDao = clientDao;
		actionListenerClientFrame(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*getCreateAdminFrameAction(e);
		getDBConfigFrameAction(e);
		getLoginFrameAction(e);*/
		//System.out.println("Has entrado en el action performed.");
		if(e.getActionCommand().equals("Send apply")){
			getActionSendApplyClient();
		}
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
			//Cancel btns
		case "Cancel Client/App action":
			getActionCancelClientBtn();
			break;
		case "Cancel Medical center action":
			getActionCancelCenterBtn();
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
				JOptionPane.showMessageDialog(null, "Error en la conexiÃ³n a la base de datos, por favor," + 
						"compruebe que los parÃ¡metros estÃ¡n introducidos\ncorrectamente y que el servidor estÃ¡ operativo.",
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
			JOptionPane.showMessageDialog(null, "El programa se cerrarÃ¡, por favor, compruebe la base de datos manualmente.",
						"InformaciÃ³n", JOptionPane.INFORMATION_MESSAGE);
			
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
					JOptionPane.showMessageDialog(null, "Las contraseÃ±as deben coincidir.", "Error",
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
				MedicalCenter mCenter = new MedicalCenter(id, address, name, pCode, phoneNumber);
				MedicalCenterDAO mCenterDao = new MedicalCenterDAO();
				mCenterDao.createNewCenter(mCenter);
				
				cCenterDialog.dispose();
				
			} else {
				JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if(e.getActionCommand().equals("Accept")) {
			DataBaseController dbController = new DataBaseController(dbConnection);
			
			String login = loginFrame.getTextField_LUsername().getText();
			
			@SuppressWarnings("deprecation")
			String password = loginFrame.getPasswordField_LPassword().getText();
						
			if(dbController.logUser(login, password)) {
				ClientFrame cFrame = new ClientFrame();
				new Controller(cFrame, clientDao);
				cFrame.setVisible(true);
				
				App.closeLoginFrame();
				
				XMLFile xmlFile = new XMLFile(new File("config/dbConfig.xml"));
				xmlFile.writeUserID(login);
				
				dbController.writeUserDataOnGUI(cFrame);
				
			} else {
				JOptionPane.showMessageDialog(null, "Usuario/Contraseña incorrectos", "Error inicio sesión", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getActionCommand().equals("Send")) {
			System.out.println("Send");
			String email = loginFrame.getTextField_REmail().getText();
			String name = loginFrame.getTextField_RName().getText();
			String surname = loginFrame.getTextField_RSurnames().getText();
			String id = loginFrame.getTextField_RID().getText();
			String password = loginFrame.getPasswordField_RPassword().getText();
			String repPassword = loginFrame.getPasswordField_RRepeat().getText();
			String birthDate = loginFrame.getTextField_RBirthDate().getText();
			
			DataBaseController dbController = new DataBaseController(dbConnection);
			
			dbController.checkUserEmail(email);
			
			String emailRegExp = "\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b";
			String birthDateRegExp = "[0-9]{4}-[0-12]{2}-[0-31]{2}"; //\\d{4}-\\d{2}-\\d{2}
			
			
			if(email.equals("") || name.equals("") || surname.equals("") || id.equals("") || password.equals("") ||
					repPassword.equals("") || birthDate.equals("")) {				
				JOptionPane.showMessageDialog(null, "Los campos no son correctos, no pueden estar vacíos.", "Error",
						JOptionPane.ERROR_MESSAGE);			
			} else{
				if(password.equals(repPassword)) {
					if(email.matches(emailRegExp)) {
						if(birthDate.matches(birthDateRegExp)){
							if(dbController.checkUserEmail(email)) {
								if(dbController.checkUserID(id)) {
									dbController.registerUser(email, name, surname, id, repPassword, birthDate);
									//Hacer login
									dbController = new DataBaseController(dbConnection);
									
									String loginR = loginFrame.getTextField_RID().getText();
									@SuppressWarnings("deprecation")
									String passwordR = loginFrame.getPasswordField_RPassword().getText();
												
									if(dbController.logUser(loginR, passwordR)) {
										ClientFrame cFrame = new ClientFrame();
										new Controller(cFrame, clientDao);
										cFrame.setVisible(true);
										
										App.closeLoginFrame();
										
										XMLFile xmlFile = new XMLFile(new File("config/dbConfig.xml"));
										xmlFile.writeUserID(loginR);
										
										dbController.writeUserDataOnGUI(cFrame);
										
									} else {
										JOptionPane.showMessageDialog(null, "Usuario/Contraseña incorrectos", "Error inicio sesión", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "El ID introducido ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
								}
								
							} else {
								JOptionPane.showMessageDialog(null, "El email introducido ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(null, "Lo introducido no es una fecha válido. \n" + "Formato: 1990-01-01", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Lo introducido no es un e-mail válido.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		if(e.getActionCommand().equals("Admin Login")) {
			AdminLoginDialog aLoginDialog = new AdminLoginDialog();
			new Controller(aLoginDialog, App.getConnection());
			aLoginDialog.setVisible(true);
		}
		
		if(e.getActionCommand().equals("Log In")) {
			String login = adminLoginDialog.getTextFieldAdminUser().getText();
			@SuppressWarnings("deprecation")
			String password = adminLoginDialog.getTextFieldAdminPassword().getText();
			
			DataBaseController dbController = new DataBaseController(dbConnection);
			
			if(dbController.logAdmin(login, password)) {
				adminLoginDialog.dispose();
				App.closeLoginFrame();
				AdministrationFrame adminFrame = new AdministrationFrame();
				new Controller(adminFrame, new ClientDAO(), new MedicalCenterDAO(), new AppointmentDAO());
				adminFrame.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null, "The administrator user is not in the Database or the password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
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
	
	
	public void actionListenerClientFrame(ActionListener escuchador){
		clientFrame.getBtnSendApply().addActionListener(escuchador);
		clientFrame.getTable_applyFor().getSelectionModel().addListSelectionListener(e -> {
			getHourFromClientJTable();
			clientFrame.getBtnSendApply().setEnabled(true);
		});
	}
	
	public void actionListenerAdministrationFrame(ActionListener escuchador){
		//Client table
		adminFrame.getBtnCCAddNew().addActionListener(escuchador);
		adminFrame.getBtnCCUpdate().addActionListener(escuchador);
		adminFrame.getBtnCCDelete().addActionListener(escuchador);
		adminFrame.getBtnCCSave().addActionListener(escuchador);
		
		adminFrame.getBtnCCancelAction().addActionListener(escuchador);
		
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
		adminFrame.getBtnMCCancelMedicalCenter().addActionListener(escuchador);
		
		
		adminFrame.getTableCCClient().getSelectionModel().addListSelectionListener(e -> {
			adminFrame.getBtnCCUpdate().setEnabled(true);
			adminFrame.getBtnCCDelete().setEnabled(true);
			setTextCCAdministrationFrame();
			clientId = adminFrame.getTextField_CCdni().getText();
			System.out.println(clientId);
			adminFrame.getAppTableModel().addClientAppointmentsToTableData(appDao, clientId);
			
			
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
	
	public void actionListenerAdminLogin(ActionListener escuchador) {
		adminLoginDialog.getBtnLogIn().addActionListener(escuchador);
	}
	
	public void getHourFromClientJTable(){
		try{			
			int selectedRow = clientFrame.getTable_applyFor().getSelectedRow();
	
			Object day = clientFrame.getTable_applyFor().getValueAt(selectedRow, 0);
			Object hour = clientFrame.getTable_applyFor().getValueAt(selectedRow, 1);
			Object center = clientFrame.getTable_applyFor().getValueAt(selectedRow, 2);
			
			clientFrame.getLblNotSelectedYet().setText("Day: " + day.toString() + ", hour: " + hour.toString() + 
					", center: " + center.toString() + "." );		
			
			//Client client = new Client(name.toString(), surnames.toString(), id.toString(), birthDate.toString(), 
			//email.toString(), password.toString());
			//returnsClientWithRowParams(client);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void setTextCCAdministrationFrame(){
		/**
		 * Una vez solucionado la RELACION ENTRE CLIENTE Y CITA descomentar codigo.
		 */
		try{			
			int selectedRow = adminFrame.getTableCCClient().getSelectedRow();
			
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
			
			Client client = new Client(name.toString(), surnames.toString(), id.toString(), birthDate.toString(),
			email.toString(), password.toString(), assCenter.toString());
			setClientWithRowParams(client);
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void setTextCCAppAdministrationFrame(){
		System.out.println("Has entrado a setTextCCAppAdminFrame");
		try{
			System.out.println("Ahora has entrado al selection listener de la tabla appointment");
			
			int selectedRow = adminFrame.getTableCCAAppointment().getSelectedRow();
				
			//new Appointment(day, time, associatedCenter, doctorName)
			Object day = adminFrame.getTableCCAAppointment().getValueAt(selectedRow, 0);
			Object hour = adminFrame.getTableCCAAppointment().getValueAt(selectedRow, 1);
			Object assCenter = adminFrame.getTableCCAAppointment().getValueAt(selectedRow, 2);
			
			
			adminFrame.getTextCCAField_Date().setText(day.toString());
			adminFrame.getTextCCAField_Hour().setText(hour.toString());
			adminFrame.getTextField_CCAAssCenter().setText(assCenter.toString());
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void setTextMCenterAdministrationFrame(){
		System.out.println("Has entrado a setTextMCenterAdministrationFrame()");
		try{
			System.out.println("Ahora has entrado al selection listener de la tabla medical center");
			
			int selectedRow = adminFrame.getTableMedicalCenter().getSelectedRow();
				
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
		adminFrame.getBtnCCancelAction().setEnabled(true);
		
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

		clientDao.deleteClientByID(client.getId());
		JOptionPane.showConfirmDialog(null, "The user with ID: '" + client.getId() + "' was deleted.", 
				"An user was deleted", JOptionPane.DEFAULT_OPTION);
		setCCTextFields(false, "all");
	}
	private void getActionUpdateClientBtn(){
		adminFrame.getBtnCCancelAction().setEnabled(true);
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

			clientDao.createNewClient(client);
			JOptionPane.showConfirmDialog(null, "The user with ID: '" + client.getId() + "' was created.", 
					"An user was created", JOptionPane.DEFAULT_OPTION);
			
		} else {
			
			client = new Client(adminFrame.getTextField_CCName().getText(), 
					adminFrame.getTextField_CCSurname().getText(), 
					adminFrame.getTextField_CCdni().getText(),
					adminFrame.getTextField_CCBirthDate().getText(),
					adminFrame.getTextField_CCEmail().getText(),
					adminFrame.getTextField_CCPassword().getText(),
					MedicalCenterDAO.getMedicalCenterId());

			clientDao.updateClient(client);
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
		adminFrame.getBtnCCancelAction().setEnabled(true);	
		//TextField
		setCCATextFields(true, "add");
		//Other btns
		setCCABtnConfiguration(false);
		//JTable
		adminFrame.getTableCCAAppointment().setEnabled(false);
		//Save help
		btnStatus = "add";

	}
	private void getActionDeleteAppBtn(){
		Appointment app = new Appointment(adminFrame.getTextCCAField_Date().getText(), 
				adminFrame.getTextCCAField_Hour().getText(), 
				adminFrame.getTextField_CCAAssCenter().getText());
		
		//appDao.deleteAppointmentByID(app);
		
		JOptionPane.showMessageDialog(null, "An appointment was deleted.", "Appointment deleted", JOptionPane.INFORMATION_MESSAGE);
		setCCATextFields(false, "add");
		setCBtnConfiguration(true);
		return;
	}
	private void getActionUpdateAppBtn(){
		//Cancel btn
		adminFrame.getBtnCCancelAction().setEnabled(true);
		//TextFields
		setCCATextFields(true, "update");
		//Other Btns
		setCCABtnConfiguration(false);
		//JTable
		adminFrame.getTableCCAAppointment().setEnabled(false);
		//Save help
		btnStatus = "update";
	}
	private void getActionSaveAppBtn(){
		if(adminFrame.getTextCCAField_Date().getText().equals("") || 
					adminFrame.getTextCCAField_Hour().getText().equals("") || 
					adminFrame.getTextField_CCAAssCenter().getText().equals("")){
			JOptionPane.showMessageDialog(null, "Cannot create/update an appointment with fields not filled.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(btnStatus.equals("add")){
			Appointment app = new Appointment(adminFrame.getTextCCAField_Date().getText(), 
					adminFrame.getTextCCAField_Hour().getText(), 
					adminFrame.getTextField_CCAAssCenter().getText());
			
			appDao.createNewAppointment(app);
			JOptionPane.showMessageDialog(null, "An appointment was created.", "Appointment created.", JOptionPane.INFORMATION_MESSAGE);
			setCCATextFields(false, "add");
			setCBtnConfiguration(true);
		}
		if(btnStatus.equals("update")){
			Appointment app = new Appointment(adminFrame.getTextCCAField_Date().getText(), 
					adminFrame.getTextCCAField_Hour().getText(), 
					adminFrame.getTextField_CCAAssCenter().getText());
			//appDao.updateAppointment(app);
			JOptionPane.showMessageDialog(null, "An appointment was updated.", "Appointment updated.", JOptionPane.INFORMATION_MESSAGE);
			setCCATextFields(false, "add");
			setCBtnConfiguration(true);
		}
		/**
		 * 		
		
		 */
	}
	
	private void setCCATextFields(Boolean booleano, String status){
		if(status.equals("add")){			
			adminFrame.getTextCCAField_Date().setText("");			
			adminFrame.getTextCCAField_Hour().setText("");			
			adminFrame.getTextField_CCAAssCenter().setText("");	
			
		}
		adminFrame.getTextCCAField_Date().setEditable(booleano);
		adminFrame.getTextCCAField_Hour().setEditable(booleano);
		adminFrame.getTextField_CCAAssCenter().setEditable(booleano);
	}
	
	private void setCCABtnConfiguration(Boolean booleano){
		adminFrame.getBtnCCAAddNew().setEnabled(booleano);
		adminFrame.getBtnCCADelete().setEnabled(booleano);
		adminFrame.getBtnCCASave().setEnabled(!booleano);
		adminFrame.getBtnCCAUpdate().setEnabled(booleano);
	}
	
	//Medical center table btn actions
	private void getActionAddCenterBtn(){
		adminFrame.getBtnMCCancelMedicalCenter().setEnabled(true);
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
		
		setMCTextFields(false, "all");
		adminFrame.getBtnMCUpdate().setEnabled(false);
		adminFrame.getBtnMCDelete().setEnabled(false);
	}
	
	private void getActionUpdateCenterBtn(){
		adminFrame.getBtnMCCancelMedicalCenter().setEnabled(true);
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
		adminFrame.getTableCCAAppointment().setEnabled(true);
		setMCTextFields(false, "save");
		setMCBtnConfiguration(true);
		
	}
	//Cancel buttons
	private void getActionCancelClientBtn(){
		adminFrame.getTableCCAAppointment().setEnabled(true);
		adminFrame.getTableCCClient().setEnabled(true);
		adminFrame.getBtnCCUpdate().setEnabled(false);
		adminFrame.getBtnCCDelete().setEnabled(false);
		adminFrame.getBtnCCAUpdate().setEnabled(false);
		adminFrame.getBtnCCADelete().setEnabled(false);
		setCCTextFields(false, "all");
		setCBtnConfiguration(true);	
		setCCATextFields(false, "add");
		adminFrame.getBtnCCancelAction().setEnabled(false);
	}
	
	private void getActionCancelCenterBtn(){
		adminFrame.getTableMedicalCenter().setEnabled(true);
		adminFrame.getBtnMCUpdate().setEnabled(false);
		adminFrame.getBtnMCDelete().setEnabled(false);
		adminFrame.getBtnMCAddNew().setEnabled(true);
		adminFrame.getBtnMCSave().setEnabled(false);
		setMCTextFields(false, "all");
		
		adminFrame.getBtnMCCancelMedicalCenter().setEnabled(false);
		
	}
	
	private void setCCBtnConfiguration(Boolean booleano){
		adminFrame.getBtnCCAAddNew().setEnabled(booleano);
		adminFrame.getBtnCCAddNew().setEnabled(booleano);
		adminFrame.getBtnCCADelete().setEnabled(booleano);
		adminFrame.getBtnCCSave().setEnabled(!booleano);
		adminFrame.getBtnCCASave().setEnabled(booleano);
		adminFrame.getBtnCCAUpdate().setEnabled(booleano);
		adminFrame.getBtnCCDelete().setEnabled(booleano);
		adminFrame.getBtnCCAUpdate().setEnabled(booleano);
		adminFrame.getBtnCCUpdate().setEnabled(booleano);
	}
	
	private void setCBtnConfiguration(Boolean booleano){
		adminFrame.getBtnCCAAddNew().setEnabled(booleano);
		adminFrame.getBtnCCAddNew().setEnabled(booleano);
		adminFrame.getBtnCCADelete().setEnabled(!booleano);
		adminFrame.getBtnCCSave().setEnabled(!booleano);
		adminFrame.getBtnCCASave().setEnabled(!booleano);
		adminFrame.getBtnCCAUpdate().setEnabled(!booleano);
		adminFrame.getBtnCCDelete().setEnabled(!booleano);
		adminFrame.getBtnCCAUpdate().setEnabled(!booleano);
		adminFrame.getBtnCCUpdate().setEnabled(!booleano);
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
		adminFrame.getTextField_CCAssCenter().setEditable(false);
		
	}
	
	private boolean areCCTextFieldsCorrectlyWrited(){
		boolean success = false;
		if(adminFrame.getTextField_CCdni().getText().equals("") || adminFrame.getTextField_CCBirthDate().getText().equals("") || 
				adminFrame.getTextField_CCEmail().getText().equals("") || adminFrame.getTextField_CCName().getText().equals("") || 
				adminFrame.getTextField_CCPassword().getText().equals("") || adminFrame.getTextField_CCSurname().getText().equals(""))
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
	/** TERMINAR CODIGO */
	private void getActionSendApplyClient(){
		int selectedRow = clientFrame.getTable_applyFor().getSelectedRow();

		Object day = clientFrame.getTable_applyFor().getValueAt(selectedRow, 0);
		Object hour = clientFrame.getTable_applyFor().getValueAt(selectedRow, 1);
		Object associatedCenter2 = clientFrame.getTable_applyFor().getValueAt(selectedRow, 2);
		
		String name = clientFrame.getTextField_Name().getText();
		String surname = clientFrame.getTextField_Surnames().getText();
		String birthDate = clientFrame.getTextField_Birthdate().getText();
		String id = clientFrame.getTextField_id().getText();
		String associatedCenter = clientFrame.getTextField_assCenter().getText();
		String email = clientFrame.getTextField_email().getText();
		
		if(new AppointmentDAO().fillAnAppointment(new Appointment(day.toString(), hour.toString(), associatedCenter2.toString()), id, name)){
			ApplyForAnAppointmentConfiguration apply = new ApplyForAnAppointmentConfiguration(name, surname, birthDate, id, associatedCenter, email, 
					day.toString(), hour.toString(), associatedCenter2.toString());
			apply.downloadPdfToDesktop();
			apply.sendEmailToClient();
		}else{
			JOptionPane.showMessageDialog(null, "The appointment was not filled in the dates table.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setClientWithRowParams(Client client){
		this.client = client;
	}
	
	public static Client getClientWithRowParams(){
		return client;
	}
	
	public static String getClientIdFromController() {
		return clientId;
	}

}
