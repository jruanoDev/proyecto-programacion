package com.proyectogestioncitas.controler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.proyectogestioncitas.model.dao.AppointmentDAO;
import com.proyectogestioncitas.model.pojo.Appointment;

public class MedicalCenterTableModel extends AbstractTableModel implements TableModelListener, ListSelectionListener{

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return super.getColumnName(column);
	}



	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}



	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * MODIFICAR CÓDIGO
	 * @param appDao
	 * @return
	 
	public Object[][] addCentersToTableData(AppointmentDAO appDao){
		//public Object[][] addAppointmentsToTableData(AppointmentDAO appDao, Client client){
			//List<Appointment> appList = appDao.getAppointmentsForClient(client);
			
			List<Appointment> appList = new ArrayList<>();
			appList.add(new Appointment(LocalDate.now(), LocalDate.now(), 1, "Antonio"));
			appList.add(new Appointment(LocalDate.now(), LocalDate.now(), 2, "Maria Jose"));
			
			int rows = appList.size();
			int columns = columnNames.length;
			
			Object dataTable[][] = new Object[rows][columns];
			
			for(int i = 0; i < rows ; i++){
				Appointment appointment = appList.get(i);
				dataTable[i] = new Object[]{appointment.getDay(), appointment.getTime(), appointment.getAssociatedCenter(), appointment.getDoctorName()};
			}
			
			return dataTable; 
		}
		*/

}
