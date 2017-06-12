package com.proyectogestioncitas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyectogestioncitas.model.Conexion;
import com.proyectogestioncitas.model.interfaces.IAdministratorDAO;
import com.proyectogestioncitas.model.pojo.Administrator;

public class AdministratorDAO implements IAdministratorDAO {
	
	List<Administrator> adminList = new ArrayList<>();
	/**
	 * Cambiar parámetros connection.
	 */
	Connection connection = Conexion.getInstanceConnection("urlexample", "userexample", "passwordexample");
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	Administrator administrator;
	String sql = "";
	int rows = 0;
	ResultSet resultSet = null;

	@Override
	public boolean createNewAdministrator(Administrator administrator) {
		boolean success = false;
		/**
		 * Cambiar sentencia SQL.
		 */
		sql = "INSERT INTO administrators (id, login, password) VALUES(?,?,?);";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, administrator.getId());
			preparedStatement.setString(2, administrator.getLogin());
			preparedStatement.setString(3, administrator.getPassword());
			preparedStatement.executeQuery();
			rows = preparedStatement.getUpdateCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rows != 0)
			success = true;
		return success;
	}

	@Override
	public Administrator getAdministratorByID() {
		// TODO Auto-generated method stub
		return new Administrator(id, login, password);
	}

	@Override
	public List<Administrator> getAllAdministrators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAdministratorByID(int id) {
		boolean success = false;
		//TO-DO
		return success;
	}

	@Override
	public boolean updateAdministrator(Administrator administrator) {
		boolean success = false;
		//TO-DO
		return success;
	}

}
