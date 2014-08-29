import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	
	public static void orderMore(ArrayList<LineItem> items ) {
		
		
		Order newOrder = null;
		for(LineItem item: items){
		for( Stock s : RetailSystem.getInstance().getStocks() ) {
			
			if( s.getUnits() < 5 && s.getProduct() == item.getProduct() ) {
				
				try {
					
					//	quantity in this Order could be final int REORDER_LEVEL = 5 defined in Stock?
					// then, add on an amount we think brings the Stock level back to final int MIN_LEVEL = 'whatever' ?
					// and, if the resulting Order would pass our MAX_LEVEL of Stock that 'over-level' amount is taken away from the Order quantity?
					
					/*
					 * 
					 * if ( s.getUnits <= REORDER_LEVEL ) --> orderMore;
					 * 
					 * newOrder.quantity = ( s.getUnits + (MIN_LEVEL - s.getUnits) ) + a quantity the user can specify
					 * 
					 * if ( userSpecifiedQuantity > MAX_LEVEL ) { newOrder.quantity = (userSpecifiedQuantity - MAX_LEVEL) / MAX_LEVEL * 100 }
					 * 
					 * ??????????????????????????????????????????????????????????????
					 * 
					 * */
					
					newOrder = new Order( new Date(), item.getProduct(), s.getUnits()+10, new Date() );
					
				} catch ( ParseException e ) {
					
					e.printStackTrace();
					
				}
				
				RetailSystem.getInstance().getOrders().add( newOrder );
				
				
				
			}
			
		}
		}
		
		
	}
	
	public static boolean searchOrderByDate( Date orderDate, String date ) {
		
		boolean isAMatch = false;
		
		String oldStringDate = date;
		
		Date newDate = null;
		
		try {
			
			newDate = new SimpleDateFormat("dd-MMM-yyyy").parse(oldStringDate);
			
		} catch ( ParseException e ) {
			
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Date format must be in the form: dd-MMM-yyyy");
			
		}
			
		if( orderDate.equals(newDate) ) {
			
			isAMatch = true;
			
		}
		
		return isAMatch;
		
	}
	
	public static String calculateOrderCost( double cost, int quantity ) {
		
		double orderCost = 0;
		
		orderCost = cost * quantity;
		
		String s = "€"+orderCost;
		
		return s;
		
	}
	
	public static boolean checkForOrders( Product product ) {
		
		boolean cantOrder = false;
		
		for(Order o : RetailSystem.getInstance().getOrders()) {
			
			if( o.getProduct().getProductID().equalsIgnoreCase(product.getProductID()) && !o.isReceived() ) {
				
				cantOrder = true;
				
				break;
				
			}
			
		}
		
		return cantOrder;
		
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
	public Date autogenExpectedDate(Date orderDate){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderDate);
		cal.add(Calendar.DATE, 7);
		return cal.getTime();
	}
}