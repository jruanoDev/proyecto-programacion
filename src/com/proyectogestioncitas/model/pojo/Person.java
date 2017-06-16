package com.proyectogestioncitas.model.pojo;


public class Person {
	private String name;
	private String surnames;
	private String id; //dni
	private String birthDate;
	
	public Person(String name, String surnames, String id, String birthDate) {
		this.name = name;
		this.surnames = surnames;
		this.id = id;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", surnames=" + surnames + ", id=" + id + ", birthDate=" + birthDate + "]";
	}
	
	
}
