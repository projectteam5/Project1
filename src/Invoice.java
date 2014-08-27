import java.util.ArrayList;
import java.util.Date;


public class Invoice {
	
	private String invoiceID;
	private Date date;
	private Customer customer;
	private ArrayList<LineItem> productsInvoice;
	private boolean completed;

	public Invoice(String invoiceID, Date date, Customer customer,boolean completed) {
		this.invoiceID = invoiceID;
		this.date = date;
		this.customer = customer;
		this.completed = completed;
		this.productsInvoice = new ArrayList<LineItem>();
	}
	
	public Invoice(String invoiceID, Date date, Customer customer){
		this.invoiceID = invoiceID;
		this.date = date;
		this.customer = customer;
		this.completed = true;
		this.productsInvoice = new ArrayList<LineItem>();
	}
	
	public void addProductsInvoice(Product product, int quantity){
		LineItem lineItem = new LineItem(product, quantity);
		productsInvoice.add(lineItem);
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<LineItem> getProductsInvoice() {
		return productsInvoice;
	}

	public void setProductsInvoice(ArrayList<LineItem> productsInvoice) {
		this.productsInvoice = productsInvoice;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	

}
