import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class StockTable extends AbstractTableModel {
	private Vector<Stock> v = null;
	private Vector<Order> v2 = null;
	private String[] colNames = {"Product","Units","Orders expected"};
	
	public StockTable(Vector<Stock> v,Vector<Order> v2) {
		this.v = v;
		this.v2 = v2;
	}
	
	public int getColumnCount(){
		return colNames.length;
	}
	public int getRowCount(){
		return v.size();
	}
	public String getValueAt(int row, int col){
		Stock stock = (Stock)v.elementAt(row);
		Order order = (Order)v2.elementAt(row);
		String val = null;
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		switch(col){
		case 0: 
			val = stock.getProduct().getName();
			break;
		case 1: 
			val = stock.getUnits()+"";
			break;
		case 2:
			if(stock.getProduct().getProductID().equalsIgnoreCase(order.getProduct().getProductID())){
				val = dt.format(order.getExpectedDeliveryDate());
			}else{
				val = "";
			}
		
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
