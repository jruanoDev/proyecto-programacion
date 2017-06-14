package com.proyectogestioncitas.controler;

import java.time.LocalDate;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;import javax.swing.table.TableModel;

import com.proyectogestioncitas.model.pojo.Client;
import com.proyectogestioncitas.view.AdministrationFrame;

@SuppressWarnings("serial")
public class ClientTableModel extends AbstractTableModel implements TableModelListener {

	private static String[] columnNames = {
			"Name",
			"Surnames",
			"ID",
			"BirthDate",
			"E-mail",
			"Password",
			"Associated center"
	};
	
	private static Object[][] tableData = {
			{"ExampleName", "ExampleSurnames", "idExample", LocalDate.now(),
				"email@example.com", "examplePwd", new Integer(1)},
			{"ExampleName", "ExampleSurnames", "idExample", LocalDate.now(),
					"email@example.com", "examplePwd", new Integer(1)}	
	};
	
	public ClientTableModel(){
		addTableModelListener(this);
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
		//new AdministrationFrame().
		return client;
	}
	
	

}
