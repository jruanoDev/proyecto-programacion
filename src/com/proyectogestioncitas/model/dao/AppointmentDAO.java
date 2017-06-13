package com.proyectogestioncitas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyectogestioncitas.app.App;
import com.proyectogestioncitas.model.Conexion;
import com.proyectogestioncitas.model.interfaces.IAppointmentDAO;
import com.proyectogestioncitas.model.pojo.Appointment;

public class AppointmentDAO implements IAppointmentDAO {

	Connection connection = Conexion.getInstanceConnection("urlexample", "userexample", "passwordexample");
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	String sql = "";
	int rows = 0;
	ResultSet resultSet = null;
	boolean success;	
	Appointment appoint;
	List<Appointment> appointmentsList = new ArrayList<>();
	
	@Override
	public boolean createNewAppointment(Appointment appointment) {
		boolean success = false;
		//Appointment a = new Appointment(day, time, associatedCenter, doctorName) FALTA RELACION CON CLIENTE
		//NECESARIO MODIFICAR LA SENTENCIA Y AÑADIR SET STRING
		sql = "INSERT INTO appointments(day, time, associatedCenter, doctorName) VALUES(?,?,?,?);";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setString(1, appointment.getDay());
			//preparedStatement.setString(2, appointment.getTime());
			preparedStatement.setInt(3, appointment.getAssociatedCenter());
			preparedStatement.setString(4, appointment.getDoctorName());
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error en la consulta al intentar crear una nueva cita.");
		}
		
		if(rows != 0)
			success = true;
		
		return success;
	}

	@Override
	public List<Appointment> getAllAppointments() {
		sql = "SELECT day, time, associatedCenter, doctorName FROM appointments;";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				//appoint.setDay(resultSet.getString(1));
				//appoint.setTime(resultSet.getString(2));
				appoint.setAssociatedCenter(resultSet.getInt(3));
				appoint.setDoctorName(resultSet.getString(4));
				
				appointmentsList.add(appoint);
			}
		} catch (SQLException e) {
			System.err.println("Error en la consulta al intentar obtener todas las citas.");
		}
		
		return appointmentsList;
	}

	@Override
	public boolean updateAppointment(Appointment appointment) {
		boolean success = false;
		/**
		 * IMPORTANTE Observar el posible cambio en sentencia.
		 */
		sql = "UPDATE appointments SET day=?, time=?, associatedCenter=?, doctorName=? WHERE day=? AND time=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setString(1, appointment.getDay());
			//preparedStatement.setString(2, appointment.getTime());
			preparedStatement.setInt(3, appointment.getAssociatedCenter());
			preparedStatement.setString(4, appointment.getDoctorName());
			//preparedStatement.setString(5, appointment.getDay());
			//preparedStatement.setString(6, appointment.getTime());
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error al realizar la consulta al intentar actualizar la cita.");
		}
		if(rows != 0)
			success = true;
		
		return success;
	}

}
