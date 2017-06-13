package com.proyectogestioncitas.model.interfaces;

import java.util.List;

import com.proyectogestioncitas.model.pojo.Administrator;
import com.proyectogestioncitas.model.pojo.Appointment;
import com.proyectogestioncitas.model.pojo.Client;
import com.proyectogestioncitas.model.pojo.Doctor;

public interface IAppointmentDAO {
	/**
	 * Necesitamos una relacion entre la cita y el cliente para poder obtener todas las citas dependiendo del cliente
	 * 
	 * ¡IMPORTANTE! Revisar AppointmentDAO para un correcto código en las sentencias y métodos implementados.
	 * 
	 */
	boolean createNewAppointment(Appointment appointment);
	//Appointment getAppointmentByID(int id);
	List<Appointment> getAllAppointments();
	//boolean deleteAppointmentByID(int id);
	boolean updateAppointment(Appointment appointment);
}
