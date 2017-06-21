package com.proyectogestioncitas.controler;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.proyectogestioncitas.model.DataBaseController;
import com.proyectogestioncitas.model.dao.AppointmentDAO;
import com.proyectogestioncitas.model.pojo.Appointment;
import com.proyectogestioncitas.model.pojo.Client;
import com.proyectogestioncitas.view.AdministrationFrame;

@SuppressWarnings("serial")
public class AppointmentTableModel extends AbstractTableModel implements TableModelListener, ListSelectionListener{

	//new Appointment(day, time, associatedCenter, doctorName)	
	private static String[] columnNames = {
			"Day",
			"Hour",
			"Associated center"
	};
	
	private static Object[][] tableData = new AppointmentTableModel().addAppointmentsToTableData(new AppointmentDAO(), Controller.getClientIdFromController());
	
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
	
	public Object[][] addAppointmentsToTableData(AppointmentDAO appDao, String clientId) {
		//clientId = Controller.getClientWithRowParams().getId();		
		Client client = DataBaseController.getCurrentLoguedClient();
		//System.out.println("HAS ENTRADO DONDE QUERIAS");
		List<Appointment> appList = appDao.getAppointmentsForClient(client.getId());

		int rows = appList.size();
		int columns = columnNames.length;
		
		Object tableData[][] = new Object[rows][columns];
		
		for(int i = 0; i < rows ; i++){
			Appointment appointment = appList.get(i);
			//tableData[i] = new Object[]{appointment.getDay(), appointment.getTime(), appointment.getAssociatedCenter()};
			tableData[i] = new Object[]{null, null, null};
		}
		//AdministrationFrame.getTableCCAAppointment().repaint();
		return tableData; 
	}

	public Object[][] addClientAppointmentsToTableData(AppointmentDAO appDAO, String id) {
		List<Appointment> appList = appDAO.getAppointmentsForClient(id);

		for (Appointment appointment : appList) {
			System.out.println(appointment.toString());
		}
		
		int rows = appList.size();
		int columns = getColumnCount();
		
		Object tableData[][] = new Object[rows][columns];
		
		JTable jtable = AdministrationFrame.getTableCCAAppointment();
		DefaultTableModel dtm = new DefaultTableModel(rows, columns);
		jtable.setModel(dtm);
		
		for(int i = 0; i < rows ; i++){
			Appointment appointment = appList.get(i);
			tableData[i] = new Object[]{appointment.getDay(), appointment.getTime(), appointment.getAssociatedCenter()};
			dtm.removeRow(0);
			dtm.addRow(new Object[]{appointment.getDay(), appointment.getTime(), appointment.getAssociatedCenter()});
			
		}
		
		return tableData;
	}

}
