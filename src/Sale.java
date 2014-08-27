import java.util.ArrayList;
import java.util.Date;


public class Sale {
	
	private ArrayList<LineItem> lineItems;
	private Date saleDate;

	public Sale() {
		this.lineItems = new ArrayList<LineItem>();
		this.saleDate = new Date();
	}
	
	public Sale(Date date) {
		this.lineItems = new ArrayList<LineItem>();
		this.saleDate = date;
	}
	
	public void addLineItem(LineItem lineItem){
		lineItems.add(lineItem);
	}
	
	public void removeLineItem(LineItem lineItem){
		lineItems.remove(lineItem);
	}

	public ArrayList<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(ArrayList<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	

}
