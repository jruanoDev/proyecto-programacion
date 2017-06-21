package com.proyectogestioncitas.controler;

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
public class ClientFrameTableModel  extends AbstractTableModel implements TableModelListener, ListSelectionListener {
	
	private static String[] columnNames = {
			"Day",
			"Hour",
			"Associated center"
	};
	
	private Object[][] tableData = addAppointmentsToTableData(new AppointmentDAO());
	//new ClientTableModel().addClientsToTableData(new ClientDAO());
	
	public ClientFrameTableModel() {
		addTableModelListener(this);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return tableData.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return tableData[arg0][arg1];
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		tableData[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//public Object[][] addHourToTableData(AppointmentDAO appDao){
	public Object[][] addAppointmentsToTableData(AppointmentDAO appDao){
		List<Appointment> appList = appDao.getUnusedAppointments();
		
		List<Appointment> updatedAppList = new ArrayList<>();
		
		int rows = appList.size();
		int columns = columnNames.length;
		
		Object dataTable[][] = new Object[rows][columns];
		
		for(int i = 0; i < rows ; i++){
			Appointment appointment = appList.get(i);
			dataTable[i] = new Object[]{appointment.getDay(), appointment.getTime(), appointment.getAssociatedCenter()};
		}
		
		return dataTable; 
	}

}
