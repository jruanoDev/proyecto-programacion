package com.proyectogestioncitas.model.dao;

import java.util.List;

import com.proyectogestioncitas.model.interfaces.IClientDAO;
import com.proyectogestioncitas.model.pojo.Client;

public class ClientDAO implements IClientDAO {

	@Override
	public boolean createNewClient(Client client) {
		boolean success = false;
		//TO-DO
		return success;
	}

	@Override
	public Client getClientByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteClientByID(int id) {
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
