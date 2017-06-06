package com.proyectogestioncitas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JSeparator;

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
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrationFrame frame = new AdministrationFrame();
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
		setBounds(100, 100, 626, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
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
		
		JPanel ClientConfiguration = new JPanel();
		tabbedPane.addTab("Client configuration", null, ClientConfiguration, null);
		
		table = new JTable();
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblDni = new JLabel("DNI:");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User name:");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Birth date:");
		
		table_1 = new JTable();
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		GroupLayout gl_ClientConfiguration = new GroupLayout(ClientConfiguration);
		gl_ClientConfiguration.setHorizontalGroup(
			gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ClientConfiguration.createSequentialGroup()
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ClientConfiguration.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 587, Short.MAX_VALUE))
						.addGroup(gl_ClientConfiguration.createSequentialGroup()
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
						.addGroup(gl_ClientConfiguration.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_ClientConfiguration.createSequentialGroup()
									.addComponent(lblUserName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ClientConfiguration.createSequentialGroup()
									.addComponent(lblDni)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ClientConfiguration.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_2)))
							.addPreferredGap(ComponentPlacement.RELATED, 355, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_ClientConfiguration.setVerticalGroup(
			gl_ClientConfiguration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ClientConfiguration.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.TRAILING)
						.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ClientConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		ClientConfiguration.setLayout(gl_ClientConfiguration);
		
		JPanel MedicalCenterCofig = new JPanel();
		tabbedPane.addTab("Medical center cofiguration", null, MedicalCenterCofig, null);
		
		medicalTableCRUD = new JTable();
		
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
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnMCSave = new JButton("Save");
		btnMCSave.setEnabled(false);
		GroupLayout gl_MedicalCenterCofig = new GroupLayout(MedicalCenterCofig);
		gl_MedicalCenterCofig.setHorizontalGroup(
			gl_MedicalCenterCofig.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MedicalCenterCofig.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
						.addComponent(medicalTableCRUD, GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
						.addGroup(gl_MedicalCenterCofig.createSequentialGroup()
							.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_MedicalCenterCofig.createSequentialGroup()
									.addComponent(lblMCPostalCode)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_MCPostalCode))
								.addGroup(gl_MedicalCenterCofig.createSequentialGroup()
									.addComponent(lblMCCenterName)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_MCCenterName))
								.addGroup(gl_MedicalCenterCofig.createSequentialGroup()
									.addComponent(lblMCLocate)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_MCLocation))
								.addGroup(gl_MedicalCenterCofig.createSequentialGroup()
									.addComponent(lblMCCenterId)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_MCCenterID, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnMCAddNew)
								.addComponent(lblMCPhoneNumber))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_MCPhone, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
								.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnMCSave)
									.addGroup(gl_MedicalCenterCofig.createSequentialGroup()
										.addComponent(btnDelete)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnMCUpdate))))))
					.addContainerGap())
		);
		gl_MedicalCenterCofig.setVerticalGroup(
			gl_MedicalCenterCofig.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MedicalCenterCofig.createSequentialGroup()
					.addContainerGap()
					.addComponent(medicalTableCRUD, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMCCenterId)
						.addComponent(textField_MCCenterID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMCPhoneNumber)
						.addComponent(textField_MCPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_MedicalCenterCofig.createSequentialGroup()
							.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMCLocate)
								.addComponent(textField_MCLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMCCenterName)
								.addComponent(textField_MCCenterName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18))
						.addGroup(gl_MedicalCenterCofig.createSequentialGroup()
							.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnMCAddNew)
								.addComponent(btnDelete)
								.addComponent(btnMCUpdate))
							.addGap(37)))
					.addGroup(gl_MedicalCenterCofig.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMCPostalCode)
						.addComponent(textField_MCPostalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnMCSave)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		MedicalCenterCofig.setLayout(gl_MedicalCenterCofig);
		contentPane.setLayout(gl_contentPane);
	}
}
