package com.proyectogestioncitas.model.interfaces;

import java.util.List;

import com.proyectogestioncitas.model.pojo.Client;

public interface IClientDAO {
	void createNewClient(Client client);
	Client getClientByID(int id);
	List<Client> getAllClients();
	void deleteClientByID(int id);
	void updateClient(Client client);
}
