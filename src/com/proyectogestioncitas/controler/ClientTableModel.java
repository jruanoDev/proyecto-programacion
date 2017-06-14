package com.proyectogestioncitas.controler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.proyectogestioncitas.model.dao.ClientDAO;
import com.proyectogestioncitas.model.pojo.Client;
import com.proyectogestioncitas.view.AdministrationFrame;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

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
		setJTableClientConfiguration();
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
		
		//List<Client> clientList= clientDAO.getAllClients();		
		
		List<Client> clientList = new ArrayList<>();
		clientList.add(new Client("asdf", "asdf", "", LocalDate.now(), "", "", 1));
		clientList.add(new Client("fasd", "qwer", "", LocalDate.now(), "", "", 1));
		clientList.add(new Client("oscar", "caca", "", LocalDate.now(), "", "", 1));
		
		int rowCount = clientList.size();
		int columnCount = columnNames.length;
		Object dataTable[][] = new Object[rowCount][columnCount];
		//System.out.println(rowCount + "," + columnCount);
		
		for(int i=0; i<rowCount; i++){
			Client client = clientList.get(i);
			dataTable[i] = new Object[]{client.getName(), client.getSurnames(), client.getId(), client.getBirthDate(),
						client.getEmail(), client.getPassword(), client.getAssociatedCenter()};				
			//System.out.println(dataTable.toString());
		}
		
		return dataTable;
	}
	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		System.out.println(e.getFirstIndex() + e.getLastIndex());		
	}
	
	public void setJTableClientConfiguration(){
		AdministrationFrame adminFrame = new AdministrationFrame();
		JTable adminCCTable = adminFrame.getTableCCClient();
		adminCCTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adminCCTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = adminCCTable.getSelectedRow();
				if(selectedRow >= 0){
					Object name = adminCCTable.getValueAt(selectedRow, 0);
					Object surnames = adminCCTable.getValueAt(selectedRow, 1);
					Object id = adminCCTable.getValueAt(selectedRow, 1);
					Object birthDate = adminCCTable.getValueAt(selectedRow, 1);
					
					adminFrame.getTextField_CCBirthDate().setText(birthDate.toString());
					adminFrame.getTextField_CCName().setText(name.toString());
					adminFrame.getTextField_CCSurname().setText(surnames.toString());
					adminFrame.getTextField_CCdni().setText(id.toString());
					
				}
				
			}
		});
	}
	
	
}
