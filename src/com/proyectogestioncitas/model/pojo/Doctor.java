package com.proyectogestioncitas.model.pojo;

import java.time.LocalDate;

public class Doctor extends Person {
	private int associatedCenter;

	public Doctor(String name, String surnames, String id, String birthDate, int associatedCenter) {
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
