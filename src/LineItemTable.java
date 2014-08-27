//test commit
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class LineItemTable extends AbstractTableModel {

	private Vector<LineItem> vet = null;
	// Vector with columns names
	private String[] ColName = { "Product Name",
			"Amount", "Total Cost", "Remove"};

	public LineItemTable(Vector<LineItem> vet) {
		this.vet = vet;
	}

	// Columns number
	public int getColumnCount() {
		return ColName.length;
	}

	// Rows number
	public int getRowCount() {
		return vet.size();
	}
	

	// return the value of each cell
	public Object getValueAt(int row, int col) {
		LineItem lineItem = (LineItem) vet.elementAt(row);
		Object val = null;
		// it returns the value for each column
		switch (col) {
		case 0:
			val = lineItem.getProduct().getName();
			break;
		case 1:
			val = String.valueOf(lineItem.getQuantity());
			break;
		case 2:
			val = String.valueOf(lineItem.getTotalCost());
			break;
		case 3:
			val = lineItem.isRemoved();
			break;
		}
		return val;
	}

	// Column name
	public String getColumnName(int col) {
		return ColName[col];
	}

	// return if a cell is editable
	public boolean isCellEditable(int row, int col) {
		if(col == 3){
			return true;
		}
		return false;
	}
	

}