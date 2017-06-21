package com.proyectogestioncitas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.proyectogestioncitas.controler.ClientFrameTableModel;
import com.proyectogestioncitas.controler.Controller;
import com.proyectogestioncitas.model.dao.ClientDAO;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;

public class ClientFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Name;
	private JTextField textField_Surnames;
	private JTextField textField_Birthdate;
	private JTextField textField_id;
	private JTextField textField_assCenter;
	private JTextField textField_email;
	private JTable table_applyFor;
	private JLabel lblNotSelectedYet;
	private JButton btnSendApply;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame frame = new ClientFrame();
					new Controller(frame, new ClientDAO());
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
	public ClientFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblClientFrame = new JLabel("CLIENT FRAME");
		
		JLabel lblClientInformation = new JLabel("CLIENT INFORMATION:");
		
		JPanel panelClientInformation = new JPanel();
		panelClientInformation.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lblApplyForAn = new JLabel("APPLY FOR AN APPOINTMENT:");
		
		JPanel panelApplyFor = new JPanel();
		panelApplyFor.setBorder(BorderFactory.createLineBorder(Color.black));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblClientInformation))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblApplyForAn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelClientInformation, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelApplyFor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(308, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(216, Short.MAX_VALUE)
					.addComponent(lblClientFrame)
					.addGap(191))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClientFrame)
					.addGap(39)
					.addComponent(lblClientInformation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelClientInformation, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(lblApplyForAn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelApplyFor, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		
		JLabel lblAvailableAppointmentsToday = new JLabel("Appointments available today:");
		
		table_applyFor = new JTable(new ClientFrameTableModel());
		
		JLabel lblyourAppointmentWill = new JLabel("*Your appointment will be today at:");
		
		lblNotSelectedYet = new JLabel("Not selected yet");
		
		btnSendApply = new JButton("Send apply");
		btnSendApply.setEnabled(false);
		GroupLayout gl_panelApplyFor = new GroupLayout(panelApplyFor);
		gl_panelApplyFor.setHorizontalGroup(
			gl_panelApplyFor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelApplyFor.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelApplyFor.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAvailableAppointmentsToday)
						.addComponent(table_applyFor, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelApplyFor.createSequentialGroup()
							.addComponent(lblyourAppointmentWill)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNotSelectedYet)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panelApplyFor.createSequentialGroup()
					.addContainerGap(337, Short.MAX_VALUE)
					.addComponent(btnSendApply)
					.addContainerGap())
		);
		gl_panelApplyFor.setVerticalGroup(
			gl_panelApplyFor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelApplyFor.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAvailableAppointmentsToday)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table_applyFor, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panelApplyFor.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblyourAppointmentWill)
						.addComponent(lblNotSelectedYet))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnSendApply)
					.addContainerGap())
		);
		panelApplyFor.setLayout(gl_panelApplyFor);
		
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblSurnames = new JLabel("Surnames:");
		
		JLabel lblBirthDate = new JLabel("Birth date:");
		
		JLabel lblId = new JLabel("ID:");
		
		JLabel lblAssociatedCenter = new JLabel("Associated center:");
		
		JLabel lblEmail = new JLabel("E-mail:");
		
		textField_Name = new JTextField();
		textField_Name.setEditable(false);
		textField_Name.setColumns(10);
		
		textField_Surnames = new JTextField();
		textField_Surnames.setEditable(false);
		textField_Surnames.setColumns(10);
		
		textField_Birthdate = new JTextField();
		textField_Birthdate.setEditable(false);
		textField_Birthdate.setColumns(10);
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		
		textField_assCenter = new JTextField();
		textField_assCenter.setEditable(false);
		textField_assCenter.setColumns(10);
		
		textField_email = new JTextField();
		textField_email.setEditable(false);
		textField_email.setColumns(10);
		GroupLayout gl_panelClientInformation = new GroupLayout(panelClientInformation);
		gl_panelClientInformation.setHorizontalGroup(
			gl_panelClientInformation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelClientInformation.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelClientInformation.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEmail)
						.addComponent(lblName)
						.addComponent(lblAssociatedCenter)
						.addComponent(lblSurnames)
						.addComponent(lblBirthDate)
						.addComponent(lblId))
					.addGap(18)
					.addGroup(gl_panelClientInformation.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_email)
						.addComponent(textField_assCenter)
						.addComponent(textField_id)
						.addComponent(textField_Birthdate)
						.addComponent(textField_Surnames)
						.addComponent(textField_Name, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
					.addContainerGap(294, Short.MAX_VALUE))
		);
		gl_panelClientInformation.setVerticalGroup(
			gl_panelClientInformation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelClientInformation.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelClientInformation.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelClientInformation.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSurnames)
						.addComponent(textField_Surnames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelClientInformation.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBirthDate)
						.addComponent(textField_Birthdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelClientInformation.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelClientInformation.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssociatedCenter)
						.addComponent(textField_assCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelClientInformation.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textField_email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		panelClientInformation.setLayout(gl_panelClientInformation);
		contentPane.setLayout(gl_contentPane);
	}

	public JTextField getTextField_Name() {
		return textField_Name;
	}

	public JTextField getTextField_Surnames() {
		return textField_Surnames;
	}

	public JTextField getTextField_Birthdate() {
		return textField_Birthdate;
	}

	public JTextField getTextField_id() {
		return textField_id;
	}

	public JTextField getTextField_assCenter() {
		return textField_assCenter;
	}

	public JTextField getTextField_email() {
		return textField_email;
	}

	public JTable getTable_applyFor() {
		return table_applyFor;
	}

	public JLabel getLblNotSelectedYet() {
		return lblNotSelectedYet;
	}

	public JButton getBtnSendApply() {
		return btnSendApply;
	}
}
