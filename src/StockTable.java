import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class StockTable extends AbstractTableModel {
	private Vector<Stock> v = null;
	private String[] colNames = {"Product","Units"};
	
	
	public StockTable(Vector<Stock> v) {
		this.v = v;
	}
	public int getColumnCount(){
		return colNames.length;
	}
	public int getRowCount(){
		return v.size();
	}
	public String getValueAt(int row, int col){
		Stock stock = (Stock)v.elementAt(row);
		String val = null;
		switch(col){
		case 0: 
			val = stock.getProduct().getName();
			break;
		case 1: 
			val = stock.getUnits()+"";
			break;
		
		default: 
			val = "";
		
		
		}
		return val;
	}
	public String getColumnName(int col) {
		return colNames[col];
	}

	
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
