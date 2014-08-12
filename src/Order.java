import java.util.Date;
import java.util.ArrayList;

public class Order {
	private static ArrayList<Order> orders;
	
	private String orderID;
	private Date orderDate;
	private Product product;
	//private Supplier supplier; //reasoning given in assumptions
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
			Product product, int quantity, Date expectedDeliveryDate, Date dateReceived, boolean received) {
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.product = product;
		this.quantity = quantity;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.dateReceived = dateReceived;
		this.received = received;
	}
	
	public void addOrderToList(Order order) {
		orders.add(order);
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

	public static ArrayList<Order> getOrders() {
		return orders;
	}

	public static void setOrders(ArrayList<Order> orders) {
		Order.orders = orders;
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