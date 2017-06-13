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
	boolean success;

	@Override
	public boolean createNewAdministrator(Administrator administrator) {
		success = false;
		/**
		 * Cambiar sentencia SQL.
		 */
		sql = "INSERT INTO administrators (id, login, password) VALUES(?,?,?);";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, administrator.getId());
			preparedStatement.setString(2, administrator.getLogin());
			preparedStatement.setString(3, administrator.getPassword());
			preparedStatement.executeUpdate();
			rows = preparedStatement.getUpdateCount();
		} catch (SQLException e) {
			System.err.println("Error al crear el administrador");
		}
		if(rows != 0)
			success = true;
		return success;
	}

	@Override
	public Administrator getAdministratorByID(int id) {
		sql = "SELECT id, login, password FROM administrators WHERE ID=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				administrator.setId(resultSet.getInt(1));
				administrator.setLogin(resultSet.getString(2));
				administrator.setPassword(resultSet.getString(3));;
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener el administrador");
		}
		
		return administrator;
	}

	@Override
	public List<Administrator> getAllAdministrators() {
		sql = "SELECT * FROM administrators";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				administrator.setId(resultSet.getInt(1));
				administrator.setLogin(resultSet.getString(2));
				administrator.setPassword(resultSet.getString(3));;
				adminList.add(administrator);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al obtener la lista de administrador");
		}
		
		return adminList;
	}

	@Override
	public boolean deleteAdministratorByID(int id) {
		success = false;
		
		sql = "DELETE FROM administrators WHERE ID=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al eliminar el administrador");
		}
		
		if(rows != 0)
			success = true;
		return success;
		
	}

	@Override
	public boolean updateAdministrator(Administrator administrator) {
		success = false;
		/**
		 * UPDATE table_name
		 * SET column1=value, column2=value2,...
		 * WHERE some_column=some_value
		 */
		sql = "UPDATE administrators SET login=?, password=? WHERE id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, administrator.getLogin());
			preparedStatement.setString(2, administrator.getPassword());
			preparedStatement.setInt(3, administrator.getId());
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al actualizar el administrador");
		}
		if(rows != 0)
			success = true;
		return success;
	}

}
