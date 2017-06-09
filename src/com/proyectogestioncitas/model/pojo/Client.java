package com.proyectogestioncitas.model.pojo;

import java.time.LocalDate;

public class Client extends Person{
	private String email;
	private String password;
	private int associatedCenter;
	
	public Client(String name, String surnames, String id, LocalDate birthDate, String email, String password,
			int associatedCenter) {
		super(name, surnames, id, birthDate);
		this.email = email;
		this.password = password;
		this.associatedCenter = associatedCenter;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAssociatedCenter() {
		return associatedCenter;
	}

	public void setAssociatedCenter(int associatedCenter) {
		this.associatedCenter = associatedCenter;
	}
	
	
	
}
