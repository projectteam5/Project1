import java.util.ArrayList;
import java.util.Date;


public class Invoice {
	
	private String invoiceID;
	private Date date;
	private Customer customer;
	private ArrayList<ProductInvoice> productsInvoice;
	private boolean completed;

	public Invoice(String invoiceID, Date date, Customer customer,boolean completed) {
		this.invoiceID = invoiceID;
		this.date = date;
		this.customer = customer;
		this.completed = completed;
		this.productsInvoice = new ArrayList<ProductInvoice>();
	}
	
	public Invoice(String invoiceID, Date date, Customer customer){
		this.invoiceID = invoiceID;
		this.date = date;
		this.customer = customer;
		this.completed = true;
		this.productsInvoice = new ArrayList<ProductInvoice>();
	}
	
	public void addProductsInvoice(Product product, int quantity){
		ProductInvoice productInvoice = new ProductInvoice(product, quantity);
		productsInvoice.add(productInvoice);
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

	public ArrayList<ProductInvoice> getProductsInvoice() {
		return productsInvoice;
	}

	public void setProductsInvoice(ArrayList<ProductInvoice> productsInvoice) {
		this.productsInvoice = productsInvoice;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	

}
