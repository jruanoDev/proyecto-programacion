package com.proyectogestioncitas.model.interfaces;

import java.util.List;

import com.proyectogestioncitas.model.pojo.MedicalCenter;

public interface IMedicalCenterDAO {
	boolean createNewCenter(MedicalCenter center);
	MedicalCenter getMedicalCenterByID(String id);
	List<MedicalCenter> getAllMedicalCenters();
	boolean deleteCenterByID(String id);
	boolean updateCenter(MedicalCenter center);
}
