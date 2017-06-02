package com.proyectogestioncitas.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class DataBaseConfigFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_DbUrl;
	private JTextField textField_DbName;
	private JTextField textField_DbPassword;
	private JLabel lblDbPassword;
	private JLabel lblDbUsername;
	private JLabel lblDbHostUrl;
	private JLabel lblDatabaseConfiguration;
	private JTextArea textArea_Db;
	private JButton btnDbValidate;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataBaseConfigFrame frame = new DataBaseConfigFrame();
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
	public DataBaseConfigFrame() {
		setResizable(false);
		inicialize();
	}
	
	public void inicialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblDatabaseConfiguration = new JLabel("DATABASE CONFIGURATION");
		
		lblDbHostUrl = new JLabel("Host URL:");
		
		textField_DbUrl = new JTextField();
		textField_DbUrl.setColumns(10);
		
		lblDbUsername = new JLabel("DB Name:");
		
		textField_DbName = new JTextField();
		textField_DbName.setColumns(10);
		
		lblDbPassword = new JLabel("DB Password:");
		
		textField_DbPassword = new JTextField();
		textField_DbPassword.setColumns(10);
		
		btnDbValidate = new JButton("Validate");
		
		textArea_Db = new JTextArea();
		textArea_Db.setEditable(false);
		textArea_Db.setText("Example:\n" + "  Host URL:    sql.remotedatabasedomain.com \n" + 
							"  Database name:    sql123456 \n" + "  Database password:    sqlpasswd123 \n" + 
							"  Database port:    3306 \n");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDbPassword)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblDbUsername)
									.addComponent(lblDbHostUrl)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_DbPassword, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_DbUrl, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(textField_DbName, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDatabaseConfiguration)))
						.addComponent(textArea_Db, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDbValidate, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatabaseConfiguration)
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDbHostUrl)
						.addComponent(textField_DbUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDbUsername)
						.addComponent(textField_DbName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDbPassword)
						.addComponent(textField_DbPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(btnDbValidate)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea_Db, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(84, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	//Getters and setters
	
	public JTextField getTextField_DbUrl() {
		return textField_DbUrl;
	}

	public JTextField getTextField_DbName() {
		return textField_DbName;
	}

	public JTextField getTextField_DbPassword() {
		return textField_DbPassword;
	}

	public JButton getBtnDbValidate() {
		return btnDbValidate;
	}

}
