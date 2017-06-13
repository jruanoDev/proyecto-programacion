package com.proyectogestioncitas.model.interfaces;

import java.util.List;

import com.proyectogestioncitas.model.pojo.Client;

public interface IClientDAO {
	boolean createNewClient(Client client);
	Client getClientByID(String id);
	List<Client> getAllClients();
	boolean deleteClientByID(String id);
	boolean updateClient(Client client);
}
