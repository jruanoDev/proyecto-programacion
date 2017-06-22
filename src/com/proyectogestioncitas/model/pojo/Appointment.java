package com.proyectogestioncitas.model.pojo;


public class Appointment {
	private String day;
	private String time;
	private String associatedCenter;
	
	public Appointment(String day, String time, String associatedCenter) {
		super();
		this.day = day;
		this.time = time;
		this.associatedCenter = associatedCenter;
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

	@Override
	public String toString() {
		return "Appointment [day=" + day + ", time=" + time + ", associatedCenter=" + associatedCenter + "]";
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
	
}
