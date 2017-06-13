package com.proyectogestioncitas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyectogestioncitas.model.Conexion;
import com.proyectogestioncitas.model.interfaces.IDoctorDAO;
import com.proyectogestioncitas.model.pojo.Doctor;

public class DoctorDAO implements IDoctorDAO{
	
	Connection connection = Conexion.getInstanceConnection("urlexample", "userexample", "passwordexample");
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	String sql = "";
	int rows = 0;
	ResultSet resultSet = null;
	boolean success;	
	Doctor doctor;
	List<Doctor> doctorsList = new ArrayList<>();
	

	@Override
	public boolean createNewDoctor(Doctor doctor) {
		success = false;
		
		sql = "INSERT INTO doctors (name, surname, id, birthdate, associatedCenter) VALUES(?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, doctor.getName());
			preparedStatement.setString(2, doctor.getSurnames());
			preparedStatement.setString(3, doctor.getId());
			preparedStatement.setString(4, doctor.getBirthDate().toString());
			preparedStatement.setInt(5, doctor.getAssociatedCenter());
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la consulta de crear nuevo Doctor.");
		}		
		
		if(rows != 0)
			success = true;
		return success;
	}

	@Override
	public Doctor getDoctorByID(String id) {		
		sql = "SELECT * FROM doctors WHERE id=?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				doctor.setName(resultSet.getString(1));
				doctor.setSurnames(resultSet.getString(2));
				doctor.setId(resultSet.getString(3));
				//doctor.setBirthDate(resultSet.getString(4));
				doctor.setAssociatedCenter(resultSet.getInt(5));
			}
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la consulta de obtener un Doctor.");
		}
		
		return doctor;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		sql = "SELECT * FROM doctors";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				doctor.setName(resultSet.getString(1));
				doctor.setSurnames(resultSet.getString(2));
				doctor.setId(resultSet.getString(3));
				//doctor.setBirthDate(resultSet.getString(4));
				doctor.setAssociatedCenter(resultSet.getInt(5));
				doctorsList.add(doctor);
			}
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la consulta de obtener todos los Doctor.");
		}
		
		return doctorsList;
	}

	@Override
	public boolean deleteDoctorByID(String id) {
		success = false;
		
		sql = "DELETE FROM doctors WHERE id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la consulta de eliminar un Doctor.");
		}
		
		if(rows != 0)
			success = true;
		return success;
	}

	@Override
	public boolean updateDoctor(Doctor doctor) {
		success = false;
		
		sql = "UPDATE doctors SET name=?, surnames=?, id=?, birthdate=?, associatedCenter=? WHERE id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, doctor.getName());
			preparedStatement.setString(2, doctor.getSurnames());
			preparedStatement.setString(3, doctor.getId());
			//preparedStatement.setString(4, doctor.getBirthDate());
			preparedStatement.setInt(5, doctor.getAssociatedCenter());
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la consulta de actualizar un Doctor.");
		}
		if(rows != 0)
			success = true;
		
		return success;
	}

}
