import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ViewAllOrdersGUI extends JPanel {
	
	private JLabel counterLabel;
	private JLabel labelTitle2;
	private JLabel labelTitle3;
	private JLabel labelTitle4;
	private JLabel labelTitleMain;
	private JLabel formatDataLabel;
	private JLabel formatHeadingLabel;
	private JLabel formatHeadingLabel2;
	private JLabel formatDataLabel2;

	public ViewAllOrdersGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitleMain = new JLabel("Open 	Orders");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(labelTitleMain);
		
		int count=0;
		for(Order order : RetailSystem.getInstance().getOrders()) {
			if(order.isActive()) {
				count++;
			}
		}
		
		double totalOpenCost = 0;
		
		formatHeadingLabel = new JLabel(
				"<html><table>"
				+ "<tr>"
				+ "<td>Order ID</td>"
				+ "<td>Order Date</td>"
				+ "<td>Product Name</td>"
				+ "<td>Quantity</td>"
				+ "<td>Expected Delivery Date</td>"
				+ "<td>Date Received</td>"
				+ "<td>Is Received</td>"
				+ "<td>Order Cost</td>"
				+ "</tr>"
				+ "</table></html>");
		this.add( formatHeadingLabel );

		for(Order order: RetailSystem.getInstance().getOrders()) {
			
			if(order.isReceived()==false && order.isActive()==true) {
				//the order is open
				
				totalOpenCost += order.getProduct().getCost() * order.getQuantity();
				
				formatDataLabel = new JLabel("<html><table>"
						+ "<tr>"
						+ "<td>"+ order.getOrderID() +"</td>"
						+ "<td>"+DateFormat.getDateInstance().format(order.getOrderDate())+"</td>"
						+ "<td>"+order.getProduct().getName()+"</td>"
						+ "<td>"+order.getQuantity()+"</td>"
						+ "<td>"+DateFormat.getDateInstance().format(order.getExpectedDeliveryDate())+"</td>"
						+ "<td>"+DateFormat.getDateInstance().format(order.getDateReceived())+"</td>"
						+ "<td>"+order.isReceived()+"</td>"
						+ "<td>"+Order.calculateOrderCost(order.getProduct().getCost(), order.getQuantity())+"</td>"
						+ "</tr>"
						+ "</table></html>");
				this.add( formatDataLabel );
				
			}
			
		}
		
		labelTitle4 = new JLabel("Total Open Order Cost: " +"€"+ totalOpenCost);
		labelTitle4.setFont(new Font("Arial", Font.ITALIC, 12));
		this.add(labelTitle4);
		
		labelTitle2 = new JLabel("Received Orders");
		labelTitle2.setFont(new Font("Arial", Font.BOLD, 20));
		this.add( labelTitle2 );
		
		double totalReceivedCost = 0;
		
		formatHeadingLabel2 = new JLabel(
				"<html><table>"
				+ "<tr>"
				+ "<td>Order ID</td>"
				+ "<td>Order Date</td>"
				+ "<td>Product Name</td>"
				+ "<td>Quantity</td>"
				+ "<td>Expected Delivery Date</td>"
				+ "<td>Date Received</td>"
				+ "<td>Is Received</td>"
				+ "<td>Order Cost</td>"
				+ "</tr>"
				+ "</table></html>");
		this.add( formatHeadingLabel2 );
		
		for(Order order: RetailSystem.getInstance().getOrders()) {
			
			if(order.isReceived()==true && order.isActive()==true) {
				//the order is received
				
				totalReceivedCost += order.getProduct().getCost() * order.getQuantity();
				
				formatDataLabel2 = new JLabel("<html><table>"
						+ "<tr>"
						+ "<td>"+ order.getOrderID() +"</td>"
						+ "<td>"+DateFormat.getDateInstance().format(order.getOrderDate())+"</td>"
						+ "<td>"+order.getProduct().getName()+"</td>"
						+ "<td>"+order.getQuantity()+"</td>"
						+ "<td>"+DateFormat.getDateInstance().format(order.getExpectedDeliveryDate())+"</td>"
						+ "<td>"+DateFormat.getDateInstance().format(order.getDateReceived())+"</td>"
						+ "<td>"+order.isReceived()+"</td>"
						+ "<td>"+Order.calculateOrderCost(order.getProduct().getCost(), order.getQuantity())+"</td>"
						+ "</tr>"
						+ "</table></html>");
				this.add( formatDataLabel2 );
			
			}
		}
		
		labelTitle3 = new JLabel("Total Receved Order Cost: " +"€"+ totalReceivedCost);
		labelTitle3.setFont(new Font("Arial", Font.ITALIC, 12));
		this.add(labelTitle3);
		
		counterLabel = new JLabel("Active Orders in System: " + count);
		counterLabel.setFont(new Font("Arial", Font.BOLD, 15));
		this.add(counterLabel);
		
		this.setVisible(true);
		
	}
	
}