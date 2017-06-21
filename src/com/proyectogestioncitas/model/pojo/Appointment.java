package com.proyectogestioncitas.model.pojo;

import java.time.LocalDate;

public class Appointment {
	private String day;
	private String time;
	private String associatedCenter;
	private String doctorName;
	//id
	
	public Appointment(String day, String time, String associatedCenter, String doctorName) {
		super();
		this.day = day;
		this.time = time;
		this.associatedCenter = associatedCenter;
		this.doctorName = doctorName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAssociatedCenter() {
		return associatedCenter;
	}

	public void setAssociatedCenter(String associatedCenter) {
		this.associatedCenter = associatedCenter;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	
}
