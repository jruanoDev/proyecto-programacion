package com.proyectogestioncitas.controler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;import javax.swing.table.TableModel;

import com.proyectogestioncitas.model.dao.ClientDAO;
import com.proyectogestioncitas.model.pojo.Client;
import com.proyectogestioncitas.view.AdministrationFrame;
import com.proyectogestioncitas.model.pojo.Client;

@SuppressWarnings("serial")
public class ClientTableModel extends AbstractTableModel implements TableModelListener, ListSelectionListener{

	
	private static String[] columnNames = {
			"Name",
			"Surnames",
			"ID",
			"BirthDate",
			"E-mail",
			"Password",
			"Associated center"
	};
	
	private static Object[][] tableData = new ClientTableModel().addClientsToTableData(new ClientDAO());
	
	public ClientTableModel(){
		//addTableModelListener(this);
		addClientsToTableData(new ClientDAO());
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
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		tableData[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		return tableData[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}

	/**
	 * We use this method to change values directly in the JTable saving the 
	 * changes.
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		getClientInformation(e);
		
		
	}
	
	
	
	public Object getClientInformation(TableModelEvent e){
		int row = e.getFirstRow();
		ClientTableModel model = (ClientTableModel) e.getSource();
		String name = (String) model.getValueAt(row, 0);
		String surnames = (String) model.getValueAt(row, 1);
		String id = (String) model.getValueAt(row, 2);
		LocalDate birthDate = (LocalDate) model.getValueAt(row, 3);
		String email = (String) model.getValueAt(row, 4);
		String password = (String) model.getValueAt(row, 5);
		int associatedCenter = (int) model.getValueAt(row, 6);
		Client client = new Client(name, surnames, id, birthDate, email, password, associatedCenter);
		new Controller(new AdministrationFrame()).setTextCCAdministrationFrame(name, surnames, id, birthDate.toString());
		return client;
	}
	
	public Object[][] addClientsToTableData(ClientDAO clientDAO){
		
		//List<Client> clientList= clientDAO.getAllClients();		
		
		List<Client> clientList = new ArrayList<>();
		clientList.add(new Client("asdf", "asdf", "", LocalDate.now(), "", "", 1));
		clientList.add(new Client("fasd", "qwer", "", LocalDate.now(), "", "", 1));
		clientList.add(new Client("oscar", "caca", "", LocalDate.now(), "", "", 1));
		
		int rowCount = clientList.size();
		int columnCount = columnNames.length;
		Object dataTable[][] = new Object[rowCount][columnCount];
		System.out.println(rowCount + "," + columnCount);
		for(int i=0; i<rowCount; i++){
			Client client = clientList.get(i);
			 dataTable[i] = new Object[]{
					client.getName(), client.getSurnames(), client.getId(), client.getBirthDate(),
						client.getEmail(), client.getPassword(), client.getAssociatedCenter()};				
			System.out.println(dataTable.toString());
		}
		
		System.out.println(dataTable.toString());
		return dataTable;
	}
	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		System.out.println(e.getFirstIndex() + e.getLastIndex());
		
		
	}
	
}
