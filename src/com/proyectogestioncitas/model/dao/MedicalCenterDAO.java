package com.proyectogestioncitas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyectogestioncitas.model.Conexion;
import com.proyectogestioncitas.model.interfaces.IMedicalCenterDAO;
import com.proyectogestioncitas.model.pojo.Doctor;
import com.proyectogestioncitas.model.pojo.MedicalCenter;

public class MedicalCenterDAO implements IMedicalCenterDAO {

	Connection connection = Conexion.getInstanceConnection("urlexample", "userexample", "passwordexample");
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	String sql = "";
	int rows = 0;
	ResultSet resultSet = null;
	boolean success;	
	MedicalCenter center;
	List<MedicalCenter> centersList = new ArrayList<>();
	
	@Override
	public boolean createNewCenter(MedicalCenter center) {
		success = false;
		
		//new MedicalCenter(centerId, location, centerName, postalCode, phoneNumber);
		sql = "INSERT INTO centers(centerId, location, centerName, postalCode, phoneNumber) VALUES(?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, center.getCenterId());
			preparedStatement.setString(2, center.getLocation());
			preparedStatement.setString(3, center.getCenterName());
			preparedStatement.setString(4, center.getPostalCode());
			preparedStatement.setString(5, center.getPhoneNumber());
			rows = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rows != 0)
			success = true;
		return success;
	}

	@Override
	public MedicalCenter getMedicalCenterByID(String id) {
		sql = "SELECT centerId, location, centerName, postalCode, phoneNumber FROM centers WHERE centerId = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				center.setCenterId(resultSet.getString(1));
				center.setLocation(resultSet.getString(2));
				center.setCenterName(resultSet.getString(3));
				center.setPostalCode(resultSet.getString(4));
				center.setPhoneNumber(resultSet.getString(5));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error a la hora de obtener un centro por id.");
		}
		return center;
	}

	@Override
	public List<MedicalCenter> getAllMedicalCenters() {
		sql = "SELECT centerId, location, centerName, postalCode, phoneNumber FROM center";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				center.setCenterId(resultSet.getString(1));
				center.setLocation(resultSet.getString(2));
				center.setCenterName(resultSet.getString(3));
				center.setPostalCode(resultSet.getString(4));
				center.setPhoneNumber(resultSet.getString(5));
				centersList.add(center);
			}
		} catch (SQLException e) {
			System.out.println("Error a la hora de obtener la lista de todos los centros.");
		}
		
		
		return centersList;
	}

	@Override
	public boolean deleteCenterByID(String id) {
		success = false;

		sql = "DELETE FROM centers WHERE id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			rows = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error a la hora de eliminar un centro por id.");
		}
		
		if(rows != 0)
			success = true;
		return success;
	}

	@Override
	public boolean updateCenter(MedicalCenter center) {
		success = false;

		sql = "UPDATE centers SET centerId=?, location=?, centerName=?, postalCode=?, phoneNumber=? WHERE id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, center.getCenterId());
			preparedStatement.setString(2, center.getLocation());
			preparedStatement.setString(3, center.getCenterName());
			preparedStatement.setString(4, center.getPostalCode());
			preparedStatement.setString(5, center.getPhoneNumber());
			preparedStatement.setString(6, center.getCenterId());
			rows = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error a la hora de actualizar un centro.");
		}
		
		if(rows != 0)
			success = true;
		return success;
	}

}
