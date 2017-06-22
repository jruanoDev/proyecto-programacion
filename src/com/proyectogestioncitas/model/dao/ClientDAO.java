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
import com.proyectogestioncitas.model.interfaces.IClientDAO;
import com.proyectogestioncitas.model.pojo.Client;

public class ClientDAO implements IClientDAO {
	
	Connection connection = App.getConnection();
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
		
		sql = "INSERT INTO clients(name, surname, id, birth_date, email, password, associated_centre) VALUES(?,?,?,?,?,?,?);";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getSurnames());
			preparedStatement.setString(3, client.getId());
			preparedStatement.setString(4, client.getBirthDate());
			preparedStatement.setString(5, client.getEmail());
			preparedStatement.setString(6, client.getPassword());
			preparedStatement.setString(7, MedicalCenterDAO.getMedicalCenterId());
			rows = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al crear nuevo usuario.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			
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
				client.setAssociatedCenter(resultSet.getString(7));
			}
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la sentencia de obtener un cliente por id.");
		}
		
		return client;
	}

	@Override
	public List<Client> getAllClients() {
		sql = "SELECT name, surname, id, birth_date, email, password, associated_centre FROM clients;";
		connection = App.getConnection();
		/**
		 * "email VARCHAR(20) NOT NULL UNIQUE," + 
			"name VARCHAR(15) NOT NULL," + 
			"surname VARCHAR(20) NOT NULL," + 
			"id VARCHAR(9) NOT NULL UNIQUE," + 
			"password VARCHAR(20) NOT NULL," + 
			"birth_date DATE NOT NULL," + 
			"associated_centre VARCHAR(10) NOT NULL,
		 */
		try {
			Statement updateStatement = connection.createStatement();

			resultSet = updateStatement.executeQuery(sql);
			
			while(resultSet.next()){	
				
				client = new Client(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("id"),
						resultSet.getString("birth_date"), resultSet.getString("email"), resultSet.getString("password"),
							resultSet.getString("associated_centre"));
				
				clientsList.add(client);
				
			}
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la sentencia de obtener todos los clients.");
		}
		
		return clientsList;
	}

	@Override
	public boolean deleteClientByID(String id) {
		boolean success = false;
		
		sql = "DELETE FROM clients WHERE id=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			rows = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar usuario", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(rows != 0)
			success = true;
		return success;
	}

	@Override
	public boolean updateClient(Client client) {
		boolean success = false;
		
		sql = "UPDATE clients SET name=?, surname=?, birth_date=?, email=?, password=?, associated_centre=? WHERE id=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getSurnames());
			preparedStatement.setString(3, client.getBirthDate());
			preparedStatement.setString(4, client.getEmail());
			preparedStatement.setString(5, client.getPassword());
			preparedStatement.setString(6, client.getAssociatedCenter());
			preparedStatement.setString(7, client.getId());
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rows != 0)
			success = true;
		return success;
	}

}
