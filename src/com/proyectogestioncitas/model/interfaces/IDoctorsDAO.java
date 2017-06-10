package com.proyectogestioncitas.model.interfaces;

import java.util.List;

import com.proyectogestioncitas.model.pojo.Client;
import com.proyectogestioncitas.model.pojo.Doctor;

public interface IDoctorsDAO {
	void createNewDoctor(Doctor doctor);
	Client getDoctorByID(int id);
	List<Doctor> getAllDoctors();
	void deleteDoctorByID(int id);
	void updateDoctor(Doctor doctor);
}
