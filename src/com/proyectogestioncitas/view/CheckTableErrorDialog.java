package com.proyectogestioncitas.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

@SuppressWarnings("serial")
public class CheckTableErrorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton repairTableButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			CheckTableErrorDialog dialog = new CheckTableErrorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public CheckTableErrorDialog() {
		setBounds(100, 100, 340, 259);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		JTextPane txtpnEl = new JTextPane();
		txtpnEl.setText("Las tablas contenidas en la base de datos no son correctas, por favor, revise manualmente estas tablas o pulse el botón reparar para que se haga automáticamente.");
		
		JLabel lblCuidado = new JLabel("CUIDADO");
		lblCuidado.setForeground(Color.RED);
		
		JTextPane txtpnSiPulsaEl = new JTextPane();
		txtpnSiPulsaEl.setText("Si pulsa el botón reparar significará que las tablas existentes serán borradas y se crearán unas nuevas sin ningún dato.");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnEl, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(13)
							.addComponent(lblCuidado)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtpnSiPulsaEl, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnEl, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtpnSiPulsaEl, GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(33)
							.addComponent(lblCuidado)
							.addContainerGap())))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				repairTableButton = new JButton("Repair tables");
				repairTableButton.setActionCommand("OK");
				buttonPane.add(repairTableButton);
				getRootPane().setDefaultButton(repairTableButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JButton getRepairTableButton() {
		return repairTableButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}
