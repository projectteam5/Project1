import java.util.Date;
import java.util.ArrayList;

public class Order {
	//don't mind this, here just to test
	//private static ArrayList<Order> orders;
	
	private String orderID;
	private Date orderDate;
	private Product product;
	
	//mistake in the Class definition by myself the other day...
	//as said in Assumptions.docx, we have a 'contract' with a supplier to supply a certain product
	//that dosen't mean we can't get many of that certain product from them...
	//so, a product is always associated to a supplier, don't need to pass a Supplier, 
	//	they are inferred by the Product class itself
	//private Supplier supplier; //reasoning given in assumptions
	private int quantity;
	private Date expectedDeliveryDate;
	private Date dateReceived;
	private boolean received;

	public Order() {
		//
		//handy to have an empty object of Order...
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
			Date expectedDeliveryDate, Date dateReceived, boolean received) {
		//full-fat constructor
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.product = product;
		this.quantity = quantity;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.dateReceived = dateReceived;
		this.received = received;
	}
	
	public void addOrderToList(Order order) {
		//add to RetailSystem ArrayList<Order> orders
		//try-catch block statement needed to cover for exception...
		
		//get the list of Order in RetailSystem and assign that variable
		ArrayList<Order> rsOrderList = RetailSystem.getInstance().getOrders();
		//iterate through an Order array of rsOrderList
		for(Order o : rsOrderList) {
			//if what's in the list already does not match this, condition is TRUE
			if(!o.getOrderID().equals(order.getOrderID())) {
				rsOrderList.add(order);
			} else {
				//just to test
				System.out.println("orderList contains order already");
			}
		}
	}
	
	//same as above ^ but opposite
	//was thinking maybe just passing the orderID to this method?..instead of the object itself
	//if we are really ambitious we could offer the choice of search to a user...
	//i.e. by ID, by name, by date...
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
	/*
	public static ArrayList<Order> getOrders() {
		return orders;
	}

	public static void setOrders(ArrayList<Order> orders) {
		Order.orders = orders;
	}
	*/
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