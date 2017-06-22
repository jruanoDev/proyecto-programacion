package com.proyectogestioncitas.controler;

import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.proyectogestioncitas.model.dao.ClientDAO;
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
		addTableModelListener(this);
		//new Controller(new AdministrationFrame());
		//setJTableClientConfiguration();
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
				
		
	}
	
	public Object[][] addClientsToTableData(ClientDAO clientDAO){
		
		List<Client> clientList= clientDAO.getAllClients();
		
		int rowCount = clientList.size();
		int columnCount = columnNames.length;
		Object dataTable[][] = new Object[rowCount][columnCount];
		
		for(int i=0; i<rowCount; i++){
			Client client = clientList.get(i);
			dataTable[i] = new Object[]{client.getName(), client.getSurnames(), client.getId(), client.getBirthDate(),
						client.getEmail(), client.getPassword(), client.getAssociatedCenter()};				
			
		}
		
		return dataTable;
		
	}
	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		//System.out.println(e.getFirstIndex() + e.getLastIndex());		
	}
	
	public static String getClientId(){
		
		return null;
	}
	/**
	public void setJTableClientConfiguration(){
		
	}
	*/
	
}
