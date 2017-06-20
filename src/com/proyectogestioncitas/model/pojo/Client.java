package com.proyectogestioncitas.model.pojo;

import java.time.LocalDate;

public class Client extends Person{
	private String email;
	private String password;
	private String associatedCenter;
	
	public Client(String name, String surnames, String id, String birthDate, String email, String password,
			String associatedCenter) {
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

	public String getAssociatedCenter() {
		return associatedCenter;
	}

	public void setAssociatedCenter(String associatedCenter) {
		this.associatedCenter = associatedCenter;
	}

	@Override
	public String toString() {
		return "Client [email=" + email + ", password=" + password + ", associatedCenter=" + associatedCenter + "]";
	}
	
	
	
	
}
