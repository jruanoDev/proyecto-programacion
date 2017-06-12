package com.proyectogestioncitas.model.dao;

import java.util.List;

import com.proyectogestioncitas.model.interfaces.IDoctorDAO;
import com.proyectogestioncitas.model.pojo.Doctor;

public class DoctorDAO implements IDoctorDAO{

	@Override
	public boolean createNewDoctor(Doctor doctor) {
		boolean success = false;
		//TO-DO
		return success;
	}

	@Override
	public Doctor getDoctorByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDoctorByID(int id) {
		boolean success = false;
		//TO-DO
		return success;
	}

	@Override
	public boolean updateDoctor(Doctor doctor) {
		boolean success = false;
		//TO-DO
		return success;
	}

}
