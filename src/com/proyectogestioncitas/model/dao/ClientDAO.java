package com.proyectogestioncitas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyectogestioncitas.model.Conexion;
import com.proyectogestioncitas.model.interfaces.IClientDAO;
import com.proyectogestioncitas.model.pojo.Client;

public class ClientDAO implements IClientDAO {
	
	Connection connection = Conexion.getInstanceConnection("urlexample", "userexample", "passwordexample");
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	String sql = "";
	int rows = 0;
	ResultSet resultSet = null;
	boolean success;	
	Client client;
	List<Client> clientsList = new ArrayList<>();

	@Override
	public boolean createNewClient(Client client) {
		boolean success = false;
		
		sql = "INSERT INTO client(name, surnames, id, birthDate, email, password, associatedCenter) VALUES(?,?,?,?,?,?,?);";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getSurnames());
			preparedStatement.setString(3, client.getId());
			//preparedStatement.setString(4, client.getBirthDate());
			preparedStatement.setString(5, client.getEmail());
			preparedStatement.setString(6, client.getPassword());
			preparedStatement.setInt(7, client.getAssociatedCenter());
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la sentencia de crear un nuevo cliente.");
		}
		if(rows != 0)
			success = true;
		return success;
	}

	@Override
	public Client getClientByID(String id) {
		//Client client2 = new Client(name, surnames, id, birthDate, email, password, associatedCenter);
		sql = "SELECT FROM clients WHERE id=?;";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				client.setName(resultSet.getString(1));
				client.setSurnames(resultSet.getString(2));
				client.setId(resultSet.getString(3));
				//client.setBirthDate(resultSet.getString(4));
				client.setEmail(resultSet.getString(5));
				client.setPassword(resultSet.getString(6));
				client.setAssociatedCenter(resultSet.getInt(7));
			}
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la sentencia de obtener un cliente por id.");
		}
		
		return client;
	}

	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return clientsList;
	}

	@Override
	public boolean deleteClientByID(String id) {
		boolean success = false;
		//TO-DO
		return success;
	}

	@Override
	public boolean updateClient(Client client) {
		boolean success = false;
		//TO-DO
		return success;
	}

}
