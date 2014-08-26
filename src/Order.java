import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;

public class Order {

	private String orderID;
	private Date orderDate;
	private Product product;
	private int quantity;
	private Date expectedDeliveryDate;
	private Date dateReceived;
	private boolean received;
	
	private boolean active;
	
	public Order(Date orderDate, 
			Product product, int quantity, 
			Date expectedDeliveryDate) throws ParseException  {
		
		this.orderID = "Order"+(RetailSystem.getInstance().getOrders().size()+1);
		this.orderDate = orderDate;
		this.product = product;
		this.quantity = quantity;
		this.expectedDeliveryDate = expectedDeliveryDate;
		
	}
	
	public Order(Date orderDate, 
			Product product, int quantity, 
			Date expectedDeliveryDate, Date dateReceived, boolean received) throws ParseException  {

		this.orderID = "Order"+(RetailSystem.getInstance().getOrders().size()+1);
		this.orderDate = orderDate;
		this.product = product;
		this.quantity = quantity;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.dateReceived = dateReceived;
		this.received = received;
		this.active = true;
	}
	
	public Order(Date orderDate, 
			Product product, int quantity, 
			Date expectedDeliveryDate, Date dateReceived, boolean received, boolean active) throws ParseException  {

		this.orderID = "Order"+(RetailSystem.getInstance().getOrders().size()+1);
		this.orderDate = orderDate;
		this.product = product;
		this.quantity = quantity;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.dateReceived = dateReceived;
		this.received = received;
		this.active = active;
	}
	
	public Order(String orderID, Date orderDate, 
			Product product, int quantity, 
			Date expectedDeliveryDate, Date dateReceived, boolean received, boolean active) throws ParseException  {

		this.orderID = orderID;
		this.orderDate = orderDate;
		this.product = product;
		this.quantity = quantity;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.dateReceived = dateReceived;
		this.received = received;
		this.active = active;
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
	
	public boolean receivedOrder(Date expectedDeliveryDate, Date dateReceived) {
		received = false;
		if(dateReceived.equals(expectedDeliveryDate)) {
			received=true;
		}
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
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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