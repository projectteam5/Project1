import java.text.DateFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class OrdersTable extends AbstractTableModel {
	
	private Vector<Order> vet = null;
	
	private String[] ColName = { "Order ID", "Order Date", "Product", "Quantity", "Expected Delivery Date", "Date Received", "Order Cost", "Received", "Active" };

	public OrdersTable(Vector<Order> vet) {
		
		this.vet = vet;
		
	}
	
	public int getRowCount() {
		return vet.size();
	}
	
	public int getColumnCount() {
		
		return ColName.length;
	}
	
	public String getValueAt(int row, int col) {
		Order order = (Order) vet.elementAt(row);
		String val = null;
		// it returns the value for each column
		switch (col) {
		case 0:
			val = order.getOrderID();
			break;
		case 1:
			val = String.valueOf(DateFormat.getDateInstance().format(order.getOrderDate()));
			break;
		case 2:
			val = order.getProduct().getName();
			break;
		case 3:
			val = String.valueOf(order.getQuantity());
			break;
		case 4:
			val = String.valueOf(DateFormat.getDateInstance().format(order.getExpectedDeliveryDate()));
			break;
		case 5:
			if(!order.isReceived()) {
				
				val = "";
				
			} else {
				
				val = String.valueOf(DateFormat.getDateInstance().format(order.getDateReceived()));
				
			}
			break;
		case 6:
			if(order.isReceived()||!order.isReceived()&&order.isActive()) {
				
				val = String.valueOf(Order.calculateOrderCost(order.getProduct().getCost(), order.getQuantity()));
				
			} else {
				
				val = "";
				
			}
			break;
		case 7:
			 if(order.isReceived())
				 val = "Y";
			 else
				 val = "N"; 
			 break;
		case 8:
			 if(order.isActive())
				 val = "Y";
			 else
				 val = "N"; 
			 break;
		default: 
			val = "";
		}
		return val;
	}
	
	// Column name
		public String getColumnName(int col) {
			return ColName[col];
		}

		// return if a cell is editable
		public boolean isCellEditable(int row, int col) {
			return false;
		}

}