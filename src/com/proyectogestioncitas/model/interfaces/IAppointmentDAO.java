package com.proyectogestioncitas.model.interfaces;

import java.util.List;

import com.proyectogestioncitas.model.pojo.Appointment;
import com.proyectogestioncitas.model.pojo.Client;

public interface IAppointmentDAO {
	/**
	 * Necesitamos una relacion entre la cita y el cliente para poder obtener todas las citas dependiendo del cliente
	 * 
	 * ¡IMPORTANTE! Revisar AppointmentDAO para un correcto código en las sentencias y métodos implementados.
	 * 
	 * ----------------DESCOMENTAR ÚLTIMO METODO TRAS RELACION ENTRE CLIENTE Y CITA
	 */
	boolean createNewAppointment(Appointment appointment);
	List<Appointment> getAppointmentsForClient(String clientId);
	List<Appointment> getAllAppointments();
	boolean deleteAppointmentByID(Appointment appointment);
	boolean updateAppointment(Appointment appointment);
	List<Appointment> getUnusedAppointments();
	boolean fillAnAppointment(Appointment appointment, String clientId, String clientName);
	
	//List<Appointment> getAppointmentsForClient(Client client);
	
}
