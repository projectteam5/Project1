import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ViewAllOrdersGUI extends JPanel {
	
	private JLabel printLabel;
	private JLabel counterLabel;
	
	private JLabel printLabel2;
	private JLabel labelTitle2;
	private JLabel labelTitle3;
	private JLabel labelTitle4;
	private JLabel labelTitleMain;

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

		for(Order order: RetailSystem.getInstance().getOrders()) {
			
			if(order.isReceived()==false && order.isActive()==true) {
				//the order is open
				
				totalOpenCost += order.getProduct().getCost() * order.getQuantity();
				
					printLabel2 = new JLabel(order.getOrderID()
						+" | "+DateFormat.getDateInstance().format(order.getOrderDate())
						+" | "+order.getProduct().getName()
						+" | "+order.getQuantity()
						+" | "+DateFormat.getDateInstance().format(order.getExpectedDeliveryDate())
						+" | "+DateFormat.getDateInstance().format(order.getDateReceived())
						+" | "+order.isReceived()
						+" | "+calcOrderCost(order.getProduct().getCost(), order.getQuantity()));
				this.add(printLabel2);
			}
			
		}
		
		labelTitle4 = new JLabel("Total Open Order Cost: " +"€"+ totalOpenCost);
		labelTitle4.setFont(new Font("Arial", Font.ITALIC, 12));
		this.add(labelTitle4);
		
		labelTitle2 = new JLabel("Received Orders");
		labelTitle2.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(labelTitle2);
		
		double totalReceivedCost = 0;
		
		for(Order order: RetailSystem.getInstance().getOrders()) {
			
			if(order.isReceived()==true && order.isActive()==true) {
				//the order is received
				
				totalReceivedCost += order.getProduct().getCost() * order.getQuantity();
				
				printLabel = new JLabel(order.getOrderID()
					+" | "+DateFormat.getDateInstance().format(order.getOrderDate())
					+" | "+order.getProduct().getName()
					+" | "+order.getQuantity()
					+" | "+DateFormat.getDateInstance().format(order.getExpectedDeliveryDate())
					+" | "+DateFormat.getDateInstance().format(order.getDateReceived())
					+" | "+order.isReceived()
					+" | "+calcOrderCost(order.getProduct().getCost(), order.getQuantity()));
			this.add(printLabel);
			
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
	
	public String calcOrderCost(double cost, int quantity) {
		double orderCost = 0;
		orderCost = cost * quantity;
		String s = "€"+orderCost;
		return s;
	}
	
}