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

@SuppressWarnings("serial")
public class AppointmentTableModel extends AbstractTableModel implements TableModelListener, ListSelectionListener{

	//new Appointment(day, time, associatedCenter, doctorName)	
	private static String[] columnNames = {
			"Day",
			"Hour",
			"Associated center",
			"Doctor name"
	};
	
	private static Object[][] tableData = new AppointmentTableModel().addAppointmentsToTableData(new AppointmentDAO());
	//new ClientTableModel().addClientsToTableData(new ClientDAO());
	
	public AppointmentTableModel() {
		addTableModelListener(this);
	}
	
	
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		tableData[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}



	@Override
	public int getRowCount() {
		
		return tableData.length;
	}

	@Override
	public int getColumnCount() {
		
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	
		return tableData[rowIndex][columnIndex];
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		System.out.println(e.getFirstIndex() + e.getLastIndex());
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Object[][] addAppointmentsToTableData(AppointmentDAO appDao){
	//public Object[][] addAppointmentsToTableData(AppointmentDAO appDao, Client client){
		//List<Appointment> appList = appDao.getAppointmentsForClient(client);
		
		List<Appointment> appList = new ArrayList<>();
		appList.add(new Appointment("day", "hour", "1", "Antonio"));
		appList.add(new Appointment("dia", "hora", "2", "Maria Jose"));
		
		int rows = appList.size();
		int columns = columnNames.length;
		
		Object dataTable[][] = new Object[rows][columns];
		
		for(int i = 0; i < rows ; i++){
			Appointment appointment = appList.get(i);
			dataTable[i] = new Object[]{appointment.getDay(), appointment.getTime(), appointment.getAssociatedCenter(), appointment.getDoctorName()};
		}
		
		return dataTable; 
	}

}
