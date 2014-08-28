import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class InvoiceTable extends AbstractTableModel {

	private Vector<Invoice> vet = null;
	// Vector with columns names
	private String[] ColName = { "Date", "Invoice ID", "Customer Name", "Customer ID", "Total Cost", "Items", "Paid", "Active"};

	public InvoiceTable(Vector<Invoice> vet) {
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
		Invoice invoice = (Invoice) vet.elementAt(row);
		String val = null;
		// it returns the value for each column
		switch (col) {
		case 0:
			val = String.valueOf(invoice.getInvoiceDate());
			break;
		case 1:
			val = invoice.getInvoiceID();
			break;
		case 2:
			val = invoice.getCustomer().getName();
			break;
		case 3:
			val = invoice.getCustomer().getCustomerID();
			break;
		case 4:
			val = String.valueOf(invoice.getTotalInvoice());
			break;
		case 5:
			val = String.valueOf(invoice.getSale().getLineItems().size());
			break;
		case 6:
			 if(invoice.isPaid())
				 val = "Y";
			 else
				 val = "N"; break;
		case 7: 
			 if(invoice.isActive())
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
