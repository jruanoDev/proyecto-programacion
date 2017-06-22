package com.proyectogestioncitas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.proyectogestioncitas.app.App;
import com.proyectogestioncitas.model.interfaces.IAppointmentDAO;
import com.proyectogestioncitas.model.pojo.Appointment;

public class AppointmentDAO implements IAppointmentDAO {

	Connection connection = App.getConnection();
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	String sql = "";
	int rows = 0;
	ResultSet resultSet = null;
	boolean success;	
	Appointment appoint = null;
	List<Appointment> appointmentsList = new ArrayList<>();
	List<Appointment> clientAppointmentsList = new ArrayList<>();
	
	@Override
	public boolean createNewAppointment(Appointment appointment, String id, String name) {
		boolean success = false;
		
		sql = "INSERT INTO dates(day, hour, center, client_name, client_id) VALUES(?,?,?,?,?);";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, appointment.getDay());
			preparedStatement.setString(2, appointment.getTime());
			preparedStatement.setString(3, appointment.getAssociatedCenter());
			preparedStatement.setString(4, name);
			preparedStatement.setString(5, id);
			
			rows = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error while creating new Appointment", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
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
	public boolean updateAppointment(Appointment appointment, String checkDay, String checkHour) {
		boolean success = false;
		System.out.println(checkDay + checkHour);
		sql = "UPDATE dates SET day=?, hour=?, center=? WHERE day=? and hour=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, appointment.getDay());
			preparedStatement.setString(2, appointment.getTime());
			preparedStatement.setString(3, appointment.getAssociatedCenter());
			preparedStatement.setString(4, checkDay);
			preparedStatement.setString(5, checkHour);
			rows = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Error al realizar la consulta al intentar actualizar la cita.");
			e.printStackTrace();
		}
		if(rows != 0)
			success = true;
		
		return success;
	}
	
	@Override
	public List<Appointment> getAppointmentsForClient(String clientId){
		sql = " SELECT day, hour, center FROM dates WHERE client_id=?;";
		List<Appointment> clientAppointments = new ArrayList<>();
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			resultSet = preparedStatement.executeQuery();
			//System.out.println(resultSet.toString());
			
			while(resultSet.next()){
				appoint = new Appointment(resultSet.getString("day"), resultSet.getString("hour"), resultSet.getString("center"));
				
				clientAppointments.add(appoint);
			}
		} catch (SQLException e) {
			System.err.println("Error en la consulta al intentar obtener las citas del cliente.");
		}
		
		return clientAppointments;
	}

	@Override
	public boolean deleteAppointmentByID(Appointment appointment) {
		success = false;
		
		sql = "DELETE FROM dates WHERE day=? and hour=? and center=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, appointment.getDay());
			preparedStatement.setString(2, appointment.getTime());
			preparedStatement.setString(3, appointment.getAssociatedCenter());
			rows = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error trying to delete an appointment", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
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
