package com.proyectogestioncitas.model.pojo;

import java.time.LocalDate;

public class Person {
	private String name;
	private String surnames;
	private String id; //dni
	private LocalDate birthDate;
	
	public Person(String name, String surnames, String id, LocalDate birthDate) {
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	
}
