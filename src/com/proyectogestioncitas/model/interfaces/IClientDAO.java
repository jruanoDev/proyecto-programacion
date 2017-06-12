package com.proyectogestioncitas.model.interfaces;

import java.util.List;

import com.proyectogestioncitas.model.pojo.Client;

public interface IClientDAO {
	boolean createNewClient(Client client);
	Client getClientByID(int id);
	List<Client> getAllClients();
	boolean deleteClientByID(int id);
	boolean updateClient(Client client);
}
