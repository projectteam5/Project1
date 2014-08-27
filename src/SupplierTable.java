import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class SupplierTable extends AbstractTableModel {

	private Vector<Supplier> vet = null;
	// Vector with columns names
	private String[] ColName = { "Supplier ID", "Supplier Name",
			"Supplier Phone Number", "Active" };

	public SupplierTable(Vector<Supplier> vet) {
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
	public String getValueAt(int row, int col) {
		Supplier supplier = (Supplier) vet.elementAt(row);
		String val = null;
		// it returns the value for each column
		switch (col) {
		case 0:
			val = supplier.getSupplierID();
			break;
		case 1:
			val = supplier.getName();
			break;
		case 2:
			val = supplier.getPhoneNumber();
			break;
		 case 3: 
			 if(supplier.isActive())
				 val = "Y";
			 else
				 val = "N"; break;
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
