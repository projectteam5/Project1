import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.text.DateFormatter;

public class Order {
	private Locale localDate;
	private SimpleDateFormat df;
	
	private String orderID;
	private Date orderDate;
	private Product product;
	private int quantity;
	private Date expectedDeliveryDate;
	private Date dateReceived;
	private boolean received;
	
	public Order() {

		this.orderID = null;
		this.orderDate = null;
		this.product = null;
		this.quantity = 0;
		this.expectedDeliveryDate =null;
		this.dateReceived = null;
		this.received = false;
	}
	
	public Order(String orderID, Date orderDate, 
			Product product, int quantity, 
			Date expectedDeliveryDate, Date dateReceived, boolean received) throws ParseException  {
		localDate = new Locale("en", "GB");
		df = new SimpleDateFormat("dd/MM/yyyy", localDate);
		
		this.orderID = orderID;
		String od = df.format(orderDate = new Date());
		try {
			this.orderDate = df.parse(od);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.product = product;
		this.quantity = quantity;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.dateReceived = dateReceived;
		this.received = received;
	}
	
	public void addOrderToList(Order order) {
		ArrayList<Order> rsOrderList = RetailSystem.getInstance().getOrders();
		for(Order o : rsOrderList) {
			if(!o.getOrderID().equals(order.getOrderID())) {
				rsOrderList.add(order);
			} else {
				System.out.println("orderList contains order already");
			}
		}
	}
	
	public void removeOrderFromList(Order order) {
		//remove from RetailSystem ArrayList<Order> orders
		ArrayList<Order> rsOrderList = RetailSystem.getInstance().getOrders();
		for(Order o : rsOrderList) {
			if(o.getOrderID().equals(order.getOrderID())) {
				rsOrderList.remove(order);
			} else {
				System.out.println("orderList does not contain order");
			}
		}
	}
	
	public void  viewAllOrderFromList() {
		ArrayList<Order> rsOrderList = RetailSystem.getInstance().getOrders();
		for(Order o : rsOrderList) {
			o.displayOrder();
		}
	}
	
	public boolean receivedOrder() {
		//if the expectedDeliveryDate == dateReceived
		//set received = TRUE
		//else, received = FALSE
		return received;
	}
	
	public Date checkOverdue() {
		return expectedDeliveryDate;
	}
	
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}
	
	public void displayOrder() {
		System.out.println(orderID 
				+ " | " + orderDate 
				+ " | " + product 
				+ " | " + quantity 
				+ " | " + expectedDeliveryDate 
				+ " | " + dateReceived 
				+ " | " + received);
	}
}