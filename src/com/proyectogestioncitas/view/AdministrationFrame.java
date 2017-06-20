package com.proyectogestioncitas.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.proyectogestioncitas.controler.AppointmentTableModel;
import com.proyectogestioncitas.controler.ClientTableModel;
import com.proyectogestioncitas.controler.Controller;
import com.proyectogestioncitas.controler.MedicalCenterTableModel;
import com.proyectogestioncitas.model.dao.AppointmentDAO;
import com.proyectogestioncitas.model.dao.ClientDAO;
import com.proyectogestioncitas.model.dao.MedicalCenterDAO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JSeparator;


@SuppressWarnings("serial")
public class AdministrationFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_MPLog;
	private JTextField textField_MPLastLog;
	private JTable medicalTableCRUD;
	private JTextField textField_MCCenterID;
	private JTextField textField_MCLocation;
	private JTextField textField_MCCenterName;
	private JTextField textField_MCPostalCode;
	private JTextField textField_MCPhone;
	private static JTable tableCCClient;
	private JTextField textField_CCdni;
	private JTextField textField_CCName;
	private JTable tableCCAAppointment;
	private JTextField textField_CCBirthDate;
	private JTextField textField_CCSurname;
	private JTextField textCCAField_Date;
	private JTextField textCCAField_Hour;
	private JButton btnCCAAddNew;
	private JButton btnCCADelete;
	private JButton btnCCAUpdate;
	private JButton btnCCUpdate;
	private JButton btnCCDelete;
	private JButton btnCCAddNew;
	private JTextField textField_CCEmail;
	private JTextField textField_CCPassword;
	private JTextField textField_CCAssCenter;
	private JTextField textField_CCAAssCenter;
	private JTextField textField_CCADoctorName;
	private JButton btnCCSave;
	private JButton btnCCASave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrationFrame frame = new AdministrationFrame();
					new Controller(frame, new ClientDAO(), new AppointmentDAO(), new MedicalCenterDAO(), tableCCClient);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdministrationFrame() {
		inicialize();
	}
	
	public void inicialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 986, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
		);
		
		JPanel MainPanel = new JPanel();
		tabbedPane.addTab("Main panel", null, MainPanel, null);
		
		JLabel lblMPUserInformation = new JLabel("User information");
		
		JLabel lblMPLoguedAs = new JLabel("Logued as:");
		
		JLabel lblMPLastLog = new JLabel("Last log:");
		
		textField_MPLog = new JTextField();
		textField_MPLog.setEditable(false);
		textField_MPLog.setColumns(10);
		
		textField_MPLastLog = new JTextField();
		textField_MPLastLog.setEditable(false);
		textField_MPLastLog.setColumns(10);
		GroupLayout gl_MainPanel = new GroupLayout(MainPanel);
		gl_MainPanel.setHorizontalGroup(
			gl_MainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMPLastLog)
						.addComponent(lblMPLoguedAs)
						.addComponent(lblMPUserInformation))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_MPLastLog)
						.addComponent(textField_MPLog, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
					.addContainerGap(295, Short.MAX_VALUE))
		);
		gl_MainPanel.setVerticalGroup(
			gl_MainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainPanel.createSequentialGroup()
					.addGap(38)
					.addComponent(lblMPUserInformation)
					.addGap(18)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMPLoguedAs)
						.addComponent(textField_MPLog, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMPLastLog)
						.addComponent(textField_MPLastLog, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(178, Short.MAX_VALUE))
		);
		MainPanel.setLayout(gl_MainPanel);
		
		ClientTableModel ccTableModel = new ClientTableModel();
		
		AppointmentTableModel appTableModel = new AppointmentTableModel();
		
		MedicalCenterTableModel medicalTM = new MedicalCenterTableModel();
		
		JPanel ClientConfiguration = new JPanel();
		tabbedPane.addTab("Client configuration", null, ClientConfiguration, null);
		tableCCClient = new JTable(ccTableModel);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblCCid = new JLabel("ID:");
		
		textField_CCdni = new JTextField();
		textField_CCdni.setEditable(false);
		textField_CCdni.setColumns(10);
		
		JLabel lblCCName = new JLabel("Name:");
		
		textField_CCName = new JTextField();
		textField_CCName.setEditable(false);
		textField_CCName.setColumns(10);
		
		JLabel lblCCBirthdate = new JLabel("Birth date:");
		tableCCAAppointment = new JTable(appTableModel);
		
		textField_CCBirthDate = new JTextField();
		textField_CCBirthDate.setEditable(false);
		textField_CCBirthDate.setColumns(10);
		
		JLabel lblCCSurname = new JLabel("Surname:");
		
		textField_CCSurname = new JTextField();
		textField_CCSurname.setEditable(false);
		textField_CCSurname.setColumns(10);
		
		JLabel lblCCADate = new JLabel("Date:");
		
		textCCAField_Date = new JTextField();
		textCCAField_Date.setEditable(false);
		textCCAField_Date.setColumns(10);
		
		JLabel lblCCAHour = new JLabel("Hour:");
		
		textCCAField_Hour = new JTextField();
		textCCAField_Hour.setEditable(false);
		textCCAField_Hour.setColumns(10);
		
		JLabel lblClientInformation = new JLabel("CLIENT INFORMATION");
		
		JLabel lblAppointmentInformation = new JLabel("APPOINTMENT INFORMATION");
		
		btnCCAddNew = new JButton("Add client");
		
		btnCCDelete = new JButton("Delete client");
		
		btnCCUpdate = new JButton("Update client");
		btnCCUpdate.setEnabled(false);
		
		btnCCAUpdate = new JButton("Update app");
		btnCCAUpdate.setEnabled(false);
		
		btnCCADelete = new JButton("Delete app");
		
		btnCCAAddNew = new JButton("Add app");
		
		JLabel lblCCEmail = new JLabel("E-mail:");
		
		textField_CCEmail = new JTextField();
		textField_CCEmail.setEditable(false);
		textField_CCEmail.setColumns(10);
		
		JLabel lblCCPassword = new JLabel("Password:");
		
		textField_CCPassword = new JTextField();
		textField_CCPassword.setEditable(false);
		textField_CCPassword.setColumns(10);
		
		JLabel lblCCAssCenter = new JLabel("Ass. Center:");
		
		textField_CCAssCenter = new JTextField();
		textField_CCAssCenter.setEditable(false);
		textField_CCAssCenter.setColumns(10);
		
		JLabel lblCCAAssCenter = new JLabel("Ass. center");
		
		JLabel lblCCADoctorsName = new JLabel("Doctor's name:");
		
		textField_CCAAssCenter = new JTextField();
		textField_CCAAssCenter.setEditable(false);
		textField_CCAAssCenter.setColumns(10);
		
		textField_CCADoctorName = new JTextField();
		textField_CCADoctorName.setEditable(false);
		textField_CCADoctorName.setColumns(10);
		
		btnCCSave = new JButton("Save client");
		btnCCSave.setEnabled(false);
		
		btnCCASave = new JButton("Save app");
		btnCCASave.setEnabled(false);
		GroupLayout gl_ClientConfiguration = new GroupLayout(ClientConfiguration);
		gl_ClientConfiguration.setHorizontalGroup(
			gl_ClientConfiguration.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_ClientConfiguration.createSequentialGroup()
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ClientConfiguration.createSequentialGroup()
							.addContainerGap()
							.addComponent(tableCCClient, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tableCCAAppointment, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_ClientConfiguration.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_ClientConfiguration.createSequentialGroup()
									.addComponent(btnCCAddNew)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCCDelete)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCCUpdate)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnCCSave))
								.addGroup(gl_ClientConfiguration.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCCid)
										.addComponent(lblCCName)
										.addComponent(lblCCBirthdate)
										.addGroup(gl_ClientConfiguration.createSequentialGroup()
											.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblCCPassword)
												.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
													.addComponent(lblCCEmail)
													.addComponent(lblCCAssCenter)
													.addComponent(lblCCSurname)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_CCEmail, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
												.addComponent(textField_CCPassword, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
												.addComponent(textField_CCBirthDate, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_CCName, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
												.addComponent(textField_CCSurname, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_CCAssCenter, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_CCdni, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))))))
							.addGap(86)
							.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_ClientConfiguration.createSequentialGroup()
									.addGap(23)
									.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_ClientConfiguration.createSequentialGroup()
											.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
												.addComponent(lblCCADate)
												.addComponent(lblCCAHour))
											.addGap(53)
											.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textCCAField_Hour)
												.addComponent(textCCAField_Date, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)))
										.addComponent(lblCCADoctorsName)
										.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.TRAILING)
											.addComponent(textField_CCADoctorName, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
											.addGroup(Alignment.LEADING, gl_ClientConfiguration.createSequentialGroup()
												.addComponent(lblCCAAssCenter)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textField_CCAAssCenter, 253, 253, 253)))))
								.addGroup(gl_ClientConfiguration.createSequentialGroup()
									.addComponent(btnCCAAddNew)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnCCASave, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnCCADelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(18)
									.addComponent(btnCCAUpdate)))
							.addPreferredGap(ComponentPlacement.RELATED, 356, Short.MAX_VALUE))
						.addGroup(gl_ClientConfiguration.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1321, Short.MAX_VALUE))
						.addGroup(gl_ClientConfiguration.createSequentialGroup()
							.addGap(179)
							.addComponent(lblClientInformation)
							.addGap(368)
							.addComponent(lblAppointmentInformation)))
					.addContainerGap())
		);
		gl_ClientConfiguration.setVerticalGroup(
			gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ClientConfiguration.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(tableCCClient, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
						.addComponent(tableCCAAppointment, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClientInformation)
						.addComponent(lblAppointmentInformation))
					.addGap(9)
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCCid)
						.addComponent(textField_CCdni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCCName)
						.addComponent(textField_CCName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCCADate)
						.addComponent(textCCAField_Date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCCBirthdate)
						.addComponent(textField_CCBirthDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCCAHour)
						.addComponent(textCCAField_Hour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_CCSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCCSurname)
						.addComponent(lblCCAAssCenter)
						.addComponent(textField_CCAAssCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ClientConfiguration.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_CCEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCCEmail))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCCPassword)
								.addComponent(textField_CCPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCCAssCenter)
								.addComponent(textField_CCAssCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_ClientConfiguration.createSequentialGroup()
							.addGap(24)
							.addComponent(lblCCADoctorsName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_CCADoctorName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCCAAddNew)
						.addComponent(btnCCADelete)
						.addComponent(btnCCAUpdate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCCAddNew)
						.addComponent(btnCCDelete)
						.addComponent(btnCCUpdate)
						.addComponent(btnCCSave)
						.addComponent(btnCCASave))
					.addContainerGap())
		);
		ClientConfiguration.setLayout(gl_ClientConfiguration);
		
		JPanel MedicalCenterConfig = new JPanel();
		tabbedPane.addTab("Medical center configuration", null, MedicalCenterConfig, null);
		medicalTableCRUD = new JTable(medicalTM);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblMCCenterId = new JLabel("Center ID:");
		
		textField_MCCenterID = new JTextField();
		textField_MCCenterID.setEditable(false);
		textField_MCCenterID.setColumns(10);
		
		JLabel lblMCLocate = new JLabel("Location:");
		
		textField_MCLocation = new JTextField();
		textField_MCLocation.setEditable(false);
		textField_MCLocation.setColumns(10);
		
		JLabel lblMCCenterName = new JLabel("Center name:");
		
		textField_MCCenterName = new JTextField();
		textField_MCCenterName.setEditable(false);
		textField_MCCenterName.setColumns(10);
		
		JLabel lblMCPostalCode = new JLabel("Postal code:");
		
		textField_MCPostalCode = new JTextField();
		textField_MCPostalCode.setEditable(false);
		textField_MCPostalCode.setColumns(10);
		
		JLabel lblMCPhoneNumber = new JLabel("Phone number:");
		
		textField_MCPhone = new JTextField();
		textField_MCPhone.setEditable(false);
		textField_MCPhone.setColumns(10);
		
		JButton btnMCAddNew = new JButton("Add new");
		
		JButton btnMCUpdate = new JButton("Update");
		btnMCUpdate.setEnabled(false);
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnMCSave = new JButton("Save");
		btnMCSave.setEnabled(false);
		GroupLayout gl_MedicalCenterConfig = new GroupLayout(MedicalCenterConfig);
		gl_MedicalCenterConfig.setHorizontalGroup(
			gl_MedicalCenterConfig.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MedicalCenterConfig.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_MedicalCenterConfig.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
						.addComponent(medicalTableCRUD, GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
						.addGroup(gl_MedicalCenterConfig.createSequentialGroup()
							.addGroup(gl_MedicalCenterConfig.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_MedicalCenterConfig.createSequentialGroup()
									.addComponent(lblMCPostalCode)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_MCPostalCode))
								.addGroup(gl_MedicalCenterConfig.createSequentialGroup()
									.addComponent(lblMCCenterName)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_MCCenterName))
								.addGroup(gl_MedicalCenterConfig.createSequentialGroup()
									.addComponent(lblMCLocate)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_MCLocation))
								.addGroup(gl_MedicalCenterConfig.createSequentialGroup()
									.addComponent(lblMCCenterId)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_MCCenterID, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_MedicalCenterConfig.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_MedicalCenterConfig.createSequentialGroup()
									.addComponent(lblMCPhoneNumber)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_MCPhone, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_MedicalCenterConfig.createSequentialGroup()
									.addComponent(btnMCAddNew)
									.addGap(69)
									.addComponent(btnDelete)
									.addGap(69)
									.addComponent(btnMCUpdate)
									.addGap(66)
									.addComponent(btnMCSave)
									.addGap(107)))))
					.addContainerGap())
		);
		gl_MedicalCenterConfig.setVerticalGroup(
			gl_MedicalCenterConfig.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MedicalCenterConfig.createSequentialGroup()
					.addContainerGap()
					.addComponent(medicalTableCRUD, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_MedicalCenterConfig.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMCCenterId)
						.addComponent(textField_MCCenterID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMCPhoneNumber)
						.addComponent(textField_MCPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_MedicalCenterConfig.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_MedicalCenterConfig.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_MedicalCenterConfig.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMCLocate)
								.addComponent(textField_MCLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_MedicalCenterConfig.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMCCenterName)
								.addComponent(textField_MCCenterName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_MedicalCenterConfig.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMCPostalCode)
								.addComponent(textField_MCPostalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_MedicalCenterConfig.createSequentialGroup()
							.addGap(66)
							.addGroup(gl_MedicalCenterConfig.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnMCUpdate)
								.addComponent(btnDelete)
								.addComponent(btnMCAddNew)
								.addComponent(btnMCSave))))
					.addContainerGap(397, Short.MAX_VALUE))
		);
		MedicalCenterConfig.setLayout(gl_MedicalCenterConfig);
		contentPane.setLayout(gl_contentPane);
	}

	public JButton getBtnCCSave() {
		return btnCCSave;
	}

	public JButton getBtnCCASave() {
		return btnCCASave;
	}

	public JButton getBtnCCAAddNew() {
		return btnCCAAddNew;
	}

	public JButton getBtnCCADelete() {
		return btnCCADelete;
	}

	public JButton getBtnCCAUpdate() {
		return btnCCAUpdate;
	}

	public JButton getBtnCCUpdate() {
		return btnCCUpdate;
	}

	public JButton getBtnCCDelete() {
		return btnCCDelete;
	}

	public JButton getBtnCCAddNew() {
		return btnCCAddNew;
	}

	public JTextField getTextField_MPLog() {
		return textField_MPLog;
	}

	public JTextField getTextField_MPLastLog() {
		return textField_MPLastLog;
	}

	public JTextField getTextField_MCCenterID() {
		return textField_MCCenterID;
	}

	public JTextField getTextField_MCLocation() {
		return textField_MCLocation;
	}

	public JTextField getTextField_MCCenterName() {
		return textField_MCCenterName;
	}

	public JTextField getTextField_MCPostalCode() {
		return textField_MCPostalCode;
	}

	public JTextField getTextField_MCPhone() {
		return textField_MCPhone;
	}

	public JTextField getTextField_CCPassword() {
		return textField_CCPassword;
	}

	public JTextField getTextField_CCAssCenter() {
		return textField_CCAssCenter;
	}

	public JTextField getTextField_CCAAssCenter() {
		return textField_CCAAssCenter;
	}

	public JTextField getTextField_CCADoctorName() {
		return textField_CCADoctorName;
	}

	public JTextField getTextField_CCEmail() {
		return textField_CCEmail;
	}

	public JTable getTableCCClient() {
		return tableCCClient;
	}
	
	public JTable getTableMedicalCenter() {
		return medicalTableCRUD;
	}

	public JTextField getTextField_CCdni() {
		return textField_CCdni;
	}

	public JTextField getTextField_CCName() {
		return textField_CCName;
	}

	public JTable getTableCCAAppointment() {
		return tableCCAAppointment;
	}

	public JTextField getTextField_CCBirthDate() {
		return textField_CCBirthDate;
	}

	public JTextField getTextField_CCSurname() {
		return textField_CCSurname;
	}

	public JTextField getTextCCAField_Date() {
		return textCCAField_Date;
	}

	public JTextField getTextCCAField_Hour() {
		return textCCAField_Hour;
	}
}