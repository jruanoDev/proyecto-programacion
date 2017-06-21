package com.proyectogestioncitas.view;


//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class CreateAdminFrame extends JDialog {

	private JPanel contentPane;
	private JTextField textField_CALogin;
	private JPasswordField passwordField_CAPassword;
	private JPasswordField passwordField_CARepeat;
	private JButton btnCreate;
	private JLabel lblCARepeat;
	private JLabel lblCALogin;
	private JButton btnExit;

	/**
	 * Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAdminFrame frame = new CreateAdminFrame();
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
	public CreateAdminFrame() {
		setAutoRequestFocus(false);
		inicialize();
	}
	
	public void inicialize(){
		setBounds(100, 100, 408, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		
		JLabel lblCreatingAdminUser = new JLabel("CREATING ADMIN USER");
		
		lblCALogin = new JLabel("Login:");
		
		JLabel lblCAPassword = new JLabel("Password:");
		
		textField_CALogin = new JTextField();
		textField_CALogin.setColumns(10);
		
		btnCreate = new JButton("Create");
		
		lblCARepeat = new JLabel("Repeat password:");
		
		passwordField_CAPassword = new JPasswordField();
		
		passwordField_CARepeat = new JPasswordField();
		
		btnExit = new JButton("Exit");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(241, Short.MAX_VALUE)
					.addComponent(btnCreate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExit)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCARepeat)
						.addComponent(lblCAPassword)
						.addComponent(lblCALogin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(passwordField_CARepeat)
						.addComponent(passwordField_CAPassword)
						.addComponent(textField_CALogin, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
					.addContainerGap(99, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(119)
					.addComponent(lblCreatingAdminUser)
					.addContainerGap(147, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblCreatingAdminUser)
					.addGap(60)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_CALogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCALogin))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCAPassword)
						.addComponent(passwordField_CAPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCARepeat)
						.addComponent(passwordField_CARepeat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExit)
						.addComponent(btnCreate))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public JButton getBtnExit() {
		return btnExit;
	}

	//Getters and setters
	public JTextField getTextField_CALogin() {
		return textField_CALogin;
	}

	public JPasswordField getPasswordField_CAPassword() {
		return passwordField_CAPassword;
	}

	public JPasswordField getPasswordField_CARepeat() {
		return passwordField_CARepeat;
	}

	public JButton getBtnCreate() {
		return btnCreate;
	}
}
