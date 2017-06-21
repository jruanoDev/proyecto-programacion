package com.proyectogestioncitas.view;

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

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_LUsername;
	private JTextField textField_REmail;
	private JTextField textField_RName;
	private JTextField textField_RSurnames;
	private JTextField textField_RIDnumber;
	private JPasswordField passwordField_LPassword;
	private JPasswordField passwordField_RPassword;
	private JPasswordField passwordField_RRepeat;
	private JLabel lblLPassword;
	private JButton btnLAccept;
	private JLabel lblLUsername;
	private JLabel lblLogin;
	private JLabel lblRegistry;
	private JButton btnRSend;
	private JLabel lblRid;
	private JLabel lblRSurnames;
	private JLabel lblRName;
	private JLabel lblRPassword;
	private JLabel lblRRepeat;
	private JLabel lblREmail;
	private JTextField textField_RBirthdate;
	private JButton btnAdminLogin;

	/**
	 * Launch the application.
	 
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
	*/

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setResizable(false);
		inicialize();
	}
	
	public void inicialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.1);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
		);
		
		JPanel leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		
		lblLogin = new JLabel("LOGIN");
		
		lblLUsername = new JLabel("Username:");
		
		lblLPassword = new JLabel("Password:");
		
		textField_LUsername = new JTextField();
		textField_LUsername.setColumns(10);
		
		btnLAccept = new JButton("Accept");
		
		passwordField_LPassword = new JPasswordField();
		
		btnAdminLogin = new JButton("Admin Login");
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLPassword)
						.addComponent(btnLAccept)
						.addComponent(lblLUsername)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(passwordField_LPassword, Alignment.LEADING)
							.addComponent(textField_LUsername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
						.addComponent(lblLogin)
						.addComponent(btnAdminLogin))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogin)
					.addGap(25)
					.addComponent(lblLUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_LUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField_LPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(btnLAccept)
					.addPreferredGap(ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
					.addComponent(btnAdminLogin)
					.addContainerGap())
		);
		leftPanel.setLayout(gl_leftPanel);
		
		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		
		lblRegistry = new JLabel("REGISTRY");
		
		lblREmail = new JLabel("E-mail:");
		
		textField_REmail = new JTextField();
		textField_REmail.setColumns(10);
		
		lblRName = new JLabel("Name:");
		
		textField_RName = new JTextField();
		textField_RName.setColumns(10);
		
		lblRSurnames = new JLabel("Surnames:");
		
		textField_RSurnames = new JTextField();
		textField_RSurnames.setColumns(10);
		
		lblRid = new JLabel("ID number:");
		
		lblRPassword = new JLabel("Password:");
		
		lblRRepeat = new JLabel("Repeat password:");
		
		textField_RIDnumber = new JTextField();
		textField_RIDnumber.setColumns(10);
		
		btnRSend = new JButton("Send");
		
		passwordField_RPassword = new JPasswordField();
		
		passwordField_RRepeat = new JPasswordField();
		
		JLabel lblBirthDate = new JLabel("Birth date:");
		
		textField_RBirthdate = new JTextField();
		textField_RBirthdate.setColumns(10);
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(textField_RBirthdate, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(lblRid)
							.addContainerGap(193, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(lblRSurnames)
							.addContainerGap(194, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(lblRName)
							.addContainerGap(225, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(lblRPassword)
							.addContainerGap(195, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(lblRRepeat)
							.addContainerGap(140, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(lblREmail)
							.addContainerGap(223, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(lblRegistry)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(btnRSend)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(lblBirthDate)
							.addContainerGap(194, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField_RIDnumber, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
								.addComponent(textField_REmail, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
								.addComponent(textField_RName, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
								.addComponent(textField_RSurnames, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
								.addComponent(passwordField_RPassword, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
								.addComponent(passwordField_RRepeat, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRegistry)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblREmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_REmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblRName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_RName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRSurnames)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_RSurnames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRid)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_RIDnumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblRPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField_RPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRRepeat)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField_RRepeat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBirthDate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_RBirthdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(btnRSend)
					.addContainerGap())
		);
		rightPanel.setLayout(gl_rightPanel);
		contentPane.setLayout(gl_contentPane);
	}
	
	//Getters and setters
	
	public JTextField getTextField_RSurnames() {
		return textField_RSurnames;
	}

	public JButton getBtnAdminLogin() {
		return btnAdminLogin;
	}

	public JTextField getTextField_LUsername() {
		return textField_LUsername;
	}


	public JTextField getTextField_REmail() {
		return textField_REmail;
	}


	public JTextField getTextField_RName() {
		return textField_RName;
	}


	public JTextField getTextField_RID() {
		return textField_RIDnumber;
	}


	public JTextField getTextField_RBirthDate() {
		return textField_RBirthdate;
	}


	public JPasswordField getPasswordField_LPassword() {
		return passwordField_LPassword;
	}


	public JPasswordField getPasswordField_RPassword() {
		return passwordField_RPassword;
	}


	public JPasswordField getPasswordField_RRepeat() {
		return passwordField_RRepeat;
	}

	public JButton getBtnLAccept() {
		return btnLAccept;
	}


	public JButton getBtnRSend() {
		return btnRSend;
	}
}
