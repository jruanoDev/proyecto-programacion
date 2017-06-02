package com.proyectogestioncitas.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.proyectogestioncitas.view.CreateAdminFrame;
import com.proyectogestioncitas.view.DataBaseConfigFrame;
import com.proyectogestioncitas.view.LoginFrame;

public class Controller implements ActionListener {

	private CreateAdminFrame createAdminFrame;
	private DataBaseConfigFrame dbConfigFrame;
	private LoginFrame loginFrame;
	
	public Controller(CreateAdminFrame createAdminFrame, DataBaseConfigFrame dbConfigFrame, LoginFrame loginFrame) {
		super();
		this.createAdminFrame = createAdminFrame;
		this.dbConfigFrame = dbConfigFrame;
		this.loginFrame = loginFrame;
		actionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getCreateAdminFrameAction(e);
		getDBConfigFrameAction(e);
		getLoginFrameAction(e);

	}
	
	public void actionListener(ActionListener escuchador){
		//Create AdminFrameComponents
		createAdminFrame.getBtnCreate().addActionListener(escuchador);
		createAdminFrame.getTextField_CALogin().addActionListener(escuchador);
		createAdminFrame.getPasswordField_CAPassword().addActionListener(escuchador);
		createAdminFrame.getPasswordField_CARepeat().addActionListener(escuchador);
		
		//Create DataBaseConfigComponents
		dbConfigFrame.getBtnDbValidate().addActionListener(escuchador);
		dbConfigFrame.getTextField_DbName().addActionListener(escuchador);
		dbConfigFrame.getTextField_DbPassword().addActionListener(escuchador);
		dbConfigFrame.getTextField_DbUrl().addActionListener(escuchador);
		
		//Create LoginFrameComponents
		loginFrame.getBtnLAccept().addActionListener(escuchador);
		loginFrame.getBtnRSend().addActionListener(escuchador);
		loginFrame.getTextField_LUsername().addActionListener(escuchador);
		loginFrame.getTextField_RBirthDate().addActionListener(escuchador);
		loginFrame.getTextField_REmail().addActionListener(escuchador);
		loginFrame.getTextField_RID().addActionListener(escuchador);
		loginFrame.getTextField_RName().addActionListener(escuchador);
		loginFrame.getPasswordField_LPassword().addActionListener(escuchador);
		loginFrame.getPasswordField_RPassword().addActionListener(escuchador);
		loginFrame.getPasswordField_RRepeat().addActionListener(escuchador);
		
	}
	
	public void getCreateAdminFrameAction(ActionEvent e){
		if(e.getActionCommand().equals("Create")){
			
		}
	}
	
	public void getDBConfigFrameAction(ActionEvent e){
		if(e.getActionCommand().equals("Validate")){
			
		}
	}
	
	public void getLoginFrameAction(ActionEvent e){
		if(e.getActionCommand().equals("Accept")){
			
		}
		if(e.getActionCommand().equals("Send")){
			
		}
	}

}
