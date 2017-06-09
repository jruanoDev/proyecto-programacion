package com.proyectogestioncitas.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class StatusBarDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblLoadingDatabase;
	private JProgressBar dbLoadingBar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StatusBarDialog dialog = new StatusBarDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StatusBarDialog() {
		setBounds(100, 100, 450, 183);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblLoadingDatabase = new JLabel("Estamos preparando la aplicación para el primer uso");
		
		dbLoadingBar = new JProgressBar();
		dbLoadingBar.setStringPainted(true);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(dbLoadingBar, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLoadingDatabase))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblLoadingDatabase)
					.addGap(34)
					.addComponent(dbLoadingBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	public JProgressBar getDbLoadingBar() {
		return dbLoadingBar;
	}
	
	public void setDbLoadingBarValue(int value) {
		dbLoadingBar.setValue(value);
	}
}
