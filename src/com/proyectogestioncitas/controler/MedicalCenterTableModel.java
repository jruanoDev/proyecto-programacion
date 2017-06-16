package com.proyectogestioncitas.controler;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.proyectogestioncitas.model.dao.AppointmentDAO;
import com.proyectogestioncitas.model.dao.MedicalCenterDAO;
import com.proyectogestioncitas.model.pojo.MedicalCenter;

public class MedicalCenterTableModel extends AbstractTableModel implements TableModelListener, ListSelectionListener{

	//new MedicalCenter(centerId, location, centerName, postalCode, phoneNumber)
	private static String columnNames[] = {
			"Center ID",
			"Location",
			"Center name",
			"Postal code",
			"Phone number"
	};
	
	private static Object[][] tableData = new MedicalCenterTableModel().addCentersToTableData(new MedicalCenterDAO());
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return tableData.length;
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
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return tableData[rowIndex][columnIndex];
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
	 */
	public Object[][] addCentersToTableData(MedicalCenterDAO centerDAO){
			
			//List<MedicalCenter> centerList = centerDAO.getAllMedicalCenters();
			
			//new MedicalCenter(centerId, location, centerName, postalCode, phoneNumber)
			List<MedicalCenter> centerList = new ArrayList<>();
			centerList.add(new MedicalCenter("IdExample", "Locatio", "NombreCentro", "CodigoPostal", "PhoneNumber"));
			centerList.add(new MedicalCenter("id", "loc", "centerName", "cp", "phoneNumber"));
			
			int rows = centerList.size();
			int columns = columnNames.length;
			
			Object dataTable[][] = new Object[rows][columns];
			
			for(int i = 0; i < rows ; i++){
				MedicalCenter center = centerList.get(i);
				dataTable[i] = new Object[]{center.getCenterId(), center.getLocation(), center.getCenterName(),
						center.getPostalCode(), center.getPhoneNumber()};
			}
			
			return dataTable; 
		}
		

}
