package com.proyectogestioncitas.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSplitPane;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textField_REmail;
	private JTextField textField_RName;
	private JTextField textField_RID;
	private JTextField textField_RPassword;
	private JTextField textField_RRepeat;
	private JTextField textField_RBirthDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setResizable(false);
		inicialize();
	}
	
	public void inicialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.1);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
		);
		
		JPanel leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		
		JLabel lblLogin = new JLabel("LOGIN");
		
		JLabel lblUsername = new JLabel("Username:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		
		JButton btnAccept = new JButton("Accept");
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPassword)
					.addContainerGap(145, Short.MAX_VALUE))
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addGap(89)
					.addComponent(lblLogin)
					.addContainerGap(100, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_leftPanel.createSequentialGroup()
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAccept))
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_leftPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_leftPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblUsername))
							.addGroup(gl_leftPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(textFieldPassword, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))))
					.addContainerGap(63, GroupLayout.PREFERRED_SIZE))
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addComponent(lblLogin)
					.addGap(37)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPassword)
					.addGap(3)
					.addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAccept)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		leftPanel.setLayout(gl_leftPanel);
		
		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		
		JLabel lblRegistry = new JLabel("REGISTRY");
		
		JLabel lblREmail = new JLabel("E-mail:");
		
		textField_REmail = new JTextField();
		textField_REmail.setColumns(10);
		
		JLabel lblRName = new JLabel("Name:");
		
		textField_RName = new JTextField();
		textField_RName.setColumns(10);
		
		JLabel lblRID = new JLabel("ID number:");
		
		textField_RID = new JTextField();
		textField_RID.setColumns(10);
		
		JLabel lblRPassword = new JLabel("Password:");
		
		textField_RPassword = new JTextField();
		textField_RPassword.setColumns(10);
		
		JLabel lblRRepeat = new JLabel("Repeat password:");
		
		textField_RRepeat = new JTextField();
		textField_RRepeat.setText("");
		textField_RRepeat.setColumns(10);
		
		JLabel lblRBirthDate = new JLabel("Birth date:");
		
		textField_RBirthDate = new JTextField();
		textField_RBirthDate.setColumns(10);
		
		JButton btnRSend = new JButton("Send");
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addGap(103)
							.addComponent(lblRegistry))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblREmail))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_REmail, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRName))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_RName, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRID))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_RID, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRPassword))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_RPassword, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRRepeat))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_RRepeat, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRBirthDate))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_RBirthDate, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addContainerGap(226, Short.MAX_VALUE)
							.addComponent(btnRSend)))
					.addContainerGap())
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addComponent(lblRegistry)
					.addGap(18)
					.addComponent(lblREmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_REmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_RName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRID)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_RID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_RPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRRepeat)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_RRepeat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRBirthDate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_RBirthDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRSend)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		rightPanel.setLayout(gl_rightPanel);
		contentPane.setLayout(gl_contentPane);
	}
}
