package com.proyectogestioncitas.model.pojo;

public class Administrator {
	private int id;
	private String login;
	private String Password;
	
	public Administrator(int id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		Password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
}
