package com.proyectogestioncitas.model.pojo;

public class MedicalCenter {
	private String centerId;
	private String Location;
	private String centerName;
	private String postalCode;
	private String phoneNumber;
	
	public MedicalCenter(String centerId, String location, String centerName, String postalCode, String phoneNumber) {
		super();
		this.centerId = centerId;
		Location = location;
		this.centerName = centerName;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
	}
	
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
