import java.util.ArrayList;
import java.util.Date;


public class Invoice {
	private String invoiceID;
	private Date invoiceDate;
	private Customer customer;
	private Sale sale;
	private double totalInvoice;
	private boolean paid;
	private boolean active;

	public Invoice(String invoiceID, Date date, Customer customer, double totalInvoice, Sale sale) {
		this.invoiceID = invoiceID;
		this.invoiceDate = date;
		this.customer = customer;
		this.sale = sale;
		this.totalInvoice = totalInvoice;
		this.paid = true;
		this.active = true;
	}
	
	public Invoice(String invoiceID, Date date, Customer customer, double totalInvoice, boolean paid, boolean active) {
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
	
}
