package com.proyectogestioncitas.model.interfaces;

import java.util.List;

import com.proyectogestioncitas.model.pojo.Administrator;

public interface IAdministratorDAO {
	boolean createNewAdministrator(Administrator administrator);
	Administrator getAdministratorByID(int id);
	List<Administrator> getAllAdministrators();
	boolean deleteAdministratorByID(int id);
	boolean updateAdministrator(Administrator administrator);
}
