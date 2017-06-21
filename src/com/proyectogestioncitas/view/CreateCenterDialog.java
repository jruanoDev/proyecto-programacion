package com.proyectogestioncitas.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateCenterDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldAddress;
	private JTextField textFieldPCode;
	private JTextField textFieldPNumber;
	private JButton btnCreate;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			CreateCenterDialog dialog = new CreateCenterDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public CreateCenterDialog() {
		setBounds(100, 100, 457, 310);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		JLabel lblNoSeHa = new JLabel("<html>No se ha detectado ningún centro médico <br>en su base de datos, para poder usar la aplicación, <br>introduzca al menos los datos de un centro.</html>");
		
		JLabel lblId = new JLabel("ID");
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		
		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		
		textFieldPCode = new JTextField();
		textFieldPCode.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone number");
		
		btnCancel = new JButton("Cancel");
		
		textFieldPNumber = new JTextField();
		textFieldPNumber.setColumns(10);
		
		btnCreate = new JButton("Create center");
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNoSeHa, GroupLayout.PREFERRED_SIZE, 779, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnCreate)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblPhoneNumber)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldPNumber))
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblId)
											.addComponent(lblName)
											.addComponent(lblAddress)
											.addComponent(lblPostalCode))
										.addGap(28)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
											.addComponent(textFieldPCode)
											.addComponent(textFieldID)
											.addComponent(textFieldAddress)
											.addComponent(textFieldName, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNoSeHa, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(textFieldAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostalCode)
						.addComponent(textFieldPCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhoneNumber)
						.addComponent(textFieldPNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreate)
						.addComponent(btnCancel)))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	public JButton getBtnCreate() {
		return btnCreate;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JTextField getTextFieldID() {
		return textFieldID;
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public JTextField getTextFieldAddress() {
		return textFieldAddress;
	}

	public JTextField getTextFieldPCode() {
		return textFieldPCode;
	}

	public JTextField getTextFieldPNumber() {
		return textFieldPNumber;
	}
}
