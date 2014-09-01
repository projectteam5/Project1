import java.io.FileWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;

public class Invoice {
	private String invoiceID;
	private Date invoiceDate;
	private Customer customer;
	private Sale sale;
	private double totalInvoice;
	private boolean paid;
	private boolean active;

	public Invoice(Date date, Customer customer, double totalInvoice, Sale sale) {
		this.invoiceID = "Invoice"
				+ (RetailSystem.getInstance().getInvoices().size() + 1);
		this.invoiceDate = date;
		this.customer = customer;
		this.sale = sale;
		this.totalInvoice = totalInvoice;
		this.paid = true;
		this.active = true;
	}

	public Invoice(String invoiceID, Date date, Customer customer,
			double totalInvoice, boolean paid, boolean active) {
		this.invoiceID = invoiceID;
		this.invoiceDate = date;
		this.customer = customer;
		this.totalInvoice = totalInvoice;
		this.paid = paid;
		this.active = active;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public double getTotalInvoice() {
		return totalInvoice;
	}

	public void setTotalInvoice(double totalInvoice) {
		this.totalInvoice = totalInvoice;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static void saveInvoice() {
		try {
			FileWriter userFile;
			userFile = new FileWriter("invoices.txt");
			DataBase.writeInvoices(RetailSystem.getInstance().getInvoices(),
					userFile);
			userFile.close();// close the invoice file
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void invoiceListComplete(JComboBox dropdown) {
		for (Invoice invoice : RetailSystem.getInstance().getInvoices()) {
			if (invoice.isActive()) {
				// System.out.println(invoice.getInvoiceID()+invoice.getCustomer().getCustomerID());
				String string = "ID: " + invoice.getInvoiceID() + " ; Name: "
						+ invoice.getCustomer().getName();
				dropdown.addItem(string);
			}
		}
	}

	public static Invoice retrieveInvoiceWithID(String id) {
		Invoice invoice = null;
		for (Invoice invoice_1 : RetailSystem.getInstance().getInvoices()) {
			if (invoice_1.getInvoiceID().equals(id)) {
				invoice = invoice_1;
			}
		}
		return invoice;
	}

	public static String[][] productsSold(){
		String[][] matrix;
		int count = 0;
		int last = 0;
		for(Invoice invoice : RetailSystem.getInstance().getInvoices()){
			count = count + invoice.getSale().getLineItems().size();
		}
		matrix = new String[count][3];
		for(Invoice invoice : RetailSystem.getInstance().getInvoices()){
			for (int i=last ; i<(last+(invoice.getSale().getLineItems().size())); i++){
				matrix[i][0]= DateFormat.getDateInstance().format(invoice.getInvoiceDate());
				matrix[i][1]= invoice.getSale().getLineItems().get(i-last).getProduct().getProductID();
				matrix[i][2]= invoice.getSale().getLineItems().get(i-last).getQuantity()+"";
			}
			last = last+invoice.getSale().getLineItems().size();
		}
		
		return matrix;
	}
}
