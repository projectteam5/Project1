import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class StockTable extends AbstractTableModel {
	private String[][] vector = null;
	private String[] colNames = {"Product","Units","Expected Order Date"};

	public StockTable(String[][] vector) {
		this.vector = vector;
	}
	public int getColumnCount(){
		return colNames.length;
	}
	public int getRowCount(){
		return vector.length;
	}
	
	public Object getValueAt(int row, int col){
		 return vector[row][col];

	}
	
	public String getColumnName(int col) {
		return colNames[col];
	}

	
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
