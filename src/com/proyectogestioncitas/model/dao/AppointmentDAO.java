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
import com.proyectogestioncitas.model.pojo.Client;

public class AppointmentDAO implements IAppointmentDAO {

	Connection connection = App.getConnection();
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	String sql = "";
	int rows = 0;
	ResultSet resultSet = null;
	boolean success;	
	Appointment appoint = new Appointment("", "", "");
	List<Appointment> appointmentsList = new ArrayList<>();
	List<Appointment> clientAppointmentsList = new ArrayList<>();
	
	@Override
	public boolean createNewAppointment(Appointment appointment) {
		boolean success = false;
		//Appointment a = new Appointment(day, time, associatedCenter, doctorName) FALTA RELACION CON CLIENTE
		//NECESARIO MODIFICAR LA SENTENCIA Y AÑADIR SET STRING
		sql = "INSERT INTO appointments(day, time, associatedCenter) VALUES(?,?,?,?);";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setString(1, appointment.getDay());
			//preparedStatement.setString(2, appointment.getTime());
			preparedStatement.setString(3, appointment.getAssociatedCenter());
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
		sql = "SELECT day, time, associatedCenter FROM dates WHERE client_id=;";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				//appoint.setDay(resultSet.getString(1));
				//appoint.setTime(resultSet.getString(2));
				appoint.setAssociatedCenter(resultSet.getString(3));
				
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
		sql = "UPDATE appointments SET day=?, time=?, associatedCenter=? WHERE day=? AND time=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setString(1, appointment.getDay());
			//preparedStatement.setString(2, appointment.getTime());
			preparedStatement.setString(3, appointment.getAssociatedCenter());
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
	
	@Override
	public List<Appointment> getAppointmentsForClient(String clientId){
		sql = " SELECT day, hour, center FROM dates WHERE client_id=?;";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet.toString());
			
			while(resultSet.next()){
				appoint.setDay(resultSet.getString("day"));
				appoint.setTime(resultSet.getString("hour"));
				appoint.setAssociatedCenter(resultSet.getString("center"));
				//appoint.setDoctorName(resultSet.getString(4));
				//appoint.setId(resultSet.getString(5));
				
				appointmentsList.add(appoint);
			}
		} catch (SQLException e) {
			System.err.println("Error en la consulta al intentar obtener las citas del cliente.");
		}
		
		return appointmentsList;
	}

	@Override
	public boolean deleteAppointmentByID(Appointment appointment) {
		success = false;
		
		sql = "DELETE FROM appointment WHERE date=?, hour=?, associatedCenter=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, appointment.getDay());
			preparedStatement.setString(2, appointment.getTime());
			preparedStatement.setString(3, appointment.getAssociatedCenter());
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error trying to delete an appointment.");
		}
		if(rows != 0)
			success = true;
		return success;
	}

	@Override
	public List<Appointment> getUnusedAppointments() {
		List<Appointment> unusedAppointments = new ArrayList<>();
		
		try {
			statement = connection.createStatement();
			ResultSet uASet = statement.executeQuery("SELECT day, hour, center FROM dates WHERE client_id=\"\"");
			
			while(uASet.next()) {
				Appointment appointment = new Appointment(uASet.getString("day"), uASet.getString("hour"), uASet.getString("center"));
				unusedAppointments.add(appointment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unusedAppointments;
	}
}
