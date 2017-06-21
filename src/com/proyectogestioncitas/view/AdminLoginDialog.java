package com.proyectogestioncitas.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class AdminLoginDialog extends JDialog {
	private JTextField textFieldAdminUser;
	private JPasswordField textFieldAdminPassword;
	private JButton btnLogIn;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			AdminLoginDialog dialog = new AdminLoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public AdminLoginDialog() {
		setBounds(100, 100, 416, 213);
		
		JLabel lblAdministratorLogin = new JLabel("Administrator Login");
		
		JLabel lblUser = new JLabel("User");
		
		JLabel lblPassword = new JLabel("Password");
		
		textFieldAdminUser = new JTextField();
		textFieldAdminUser.setColumns(10);
		
		btnLogIn = new JButton("Log In");
		
		textFieldAdminPassword = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(135)
							.addComponent(lblAdministratorLogin))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUser)
								.addComponent(lblPassword))
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldAdminPassword)
								.addComponent(textFieldAdminUser, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
								.addComponent(btnLogIn))))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministratorLogin)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUser)
						.addComponent(textFieldAdminUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textFieldAdminPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(btnLogIn)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}

	public JTextField getTextFieldAdminUser() {
		return textFieldAdminUser;
	}

	public JPasswordField getTextFieldAdminPassword() {
		return textFieldAdminPassword;
	}

	public JButton getBtnLogIn() {
		return btnLogIn;
	}
}
