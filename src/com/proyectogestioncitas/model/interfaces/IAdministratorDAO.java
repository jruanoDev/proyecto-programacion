package com.proyectogestioncitas.model.interfaces;

import java.util.List;

import com.proyectogestioncitas.model.pojo.Administrator;

public interface IAdministratorDAO {
	void createNewAdministrator(Administrator administrator);
	Administrator getAdministratorByID();
	List<Administrator> getAllAdministrators();
	void deleteAdministratorByID(int id);
	void updateAdministrator(Administrator administrator);
}
