import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class ProductTable extends AbstractTableModel {

	private Vector<Product> vet = null;
	// Vector with columns names
	private String[] ColName = { "Product ID", "Product Name",
			"Product Cost", "Product Markup", "Product Supplier", "Product Active" };

	public ProductTable(Vector<Product> vet) {
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
		Product product = (Product) vet.elementAt(row);
		String val = null;
		// it returns the value for each column
		switch (col) {
		case 0:
			val = product.getProductID();
			break;
		case 1:
			val = product.getName();
			break;
		case 2:
			val = String.valueOf(product.getCost());
			break;
		case 3:
			val = String.valueOf(product.getMarkup());
			break;
		case 4:
			val = product.getSupplier().getName();
			break;
		case 5: 
			 if(product.isActive())
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