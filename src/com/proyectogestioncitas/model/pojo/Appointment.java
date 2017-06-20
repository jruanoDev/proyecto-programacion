package com.proyectogestioncitas.model.pojo;

import java.time.LocalDate;

public class Appointment {
	private LocalDate day;
	private LocalDate time;
	private int associatedCenter;
	private String doctorName;
	//id
	
	public Appointment(LocalDate day, LocalDate time, int associatedCenter, String doctorName) {
		super();
		this.day = day;
		this.time = time;
		this.associatedCenter = associatedCenter;
		this.doctorName = doctorName;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	public int getAssociatedCenter() {
		return associatedCenter;
	}

	public void setAssociatedCenter(int associatedCenter) {
		this.associatedCenter = associatedCenter;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	
}
