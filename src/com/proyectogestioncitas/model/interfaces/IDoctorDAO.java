package com.proyectogestioncitas.model.interfaces;

import java.util.List;

import com.proyectogestioncitas.model.pojo.Client;
import com.proyectogestioncitas.model.pojo.Doctor;

public interface IDoctorDAO {
	boolean createNewDoctor(Doctor doctor);
	Doctor getDoctorByID(int id);
	List<Doctor> getAllDoctors();
	boolean deleteDoctorByID(int id);
	boolean updateDoctor(Doctor doctor);
}
