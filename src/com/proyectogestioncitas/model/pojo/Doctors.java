package com.proyectogestioncitas.model.pojo;

import java.time.LocalDate;

public class Doctors extends Person {
	private int associatedCenter;

	public Doctors(String name, String surnames, String id, LocalDate birthDate, int associatedCenter) {
		super(name, surnames, id, birthDate);
		this.associatedCenter = associatedCenter;
	}

	public int getAssociatedCenter() {
		return associatedCenter;
	}

	public void setAssociatedCenter(int associatedCenter) {
		this.associatedCenter = associatedCenter;
	}
	
	
}
