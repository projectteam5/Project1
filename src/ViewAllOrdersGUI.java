import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ViewAllOrdersGUI extends JPanel {
	
	private JPanel detailsPanel;
	private JScrollPane scrollPane;
	private JLabel orderDetailsLabel;
	private JLabel printLabel;
	private JLabel counterLabel;
	
	private JLabel printLabel2;
	private JLabel labelTitle;
	private JLabel labelTitle2;
	private JLabel labelTitle3;
	private JLabel labelTitle4;
	private JLabel labelTitleMain;

	public ViewAllOrdersGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitleMain = new JLabel("View all 	Orders");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		
		detailsPanel = new JPanel(new GridLayout(0,1));
		
		scrollPane = new JScrollPane(detailsPanel);
		scrollPane.setViewportView(detailsPanel);
		orderDetailsLabel = new JLabel("Order Details");
		
		this.add(labelTitleMain);
		this.add(detailsPanel);
		
		this.add(orderDetailsLabel,BorderLayout.NORTH);
		this.add(scrollPane,BorderLayout.CENTER);
		
		scrollPane.setViewportView(detailsPanel);
		
		int count=0;
		for(Order order : RetailSystem.getInstance().getOrders()) {
			if(order.isActive()) {
				count++;
			}
		}
		counterLabel = new JLabel("Active Orders in System: " + count);
		this.add(counterLabel, BorderLayout.SOUTH);
		
		setVisible(true);
		
		labelTitle = new JLabel("Open Orders");
		labelTitle.setSize(10,10);
		detailsPanel.add(labelTitle);
		
		double totalOpenCost = 0;

		for(Order order: RetailSystem.getInstance().getOrders()) {
			
			if(order.isReceived()==false && order.isActive()==true) {
				//the order is open
				
				totalOpenCost += order.getProduct().getCost() * order.getQuantity();
				
					printLabel2 = new JLabel(order.getOrderID()
						+" | "+DateFormat.getDateInstance().format(order.getOrderDate())
						+" | "+order.getProduct().getProductID()
						+" | "+order.getQuantity()
						+" | "+DateFormat.getDateInstance().format(order.getExpectedDeliveryDate())
						+" | "+DateFormat.getDateInstance().format(order.getDateReceived())
						+" | "+order.isReceived()
						+" | "+calcOrderCost(order.getProduct().getCost(), order.getQuantity()));
				printLabel2.setSize(10,10);
				detailsPanel.add(printLabel2);
			}
			
		}
		
		labelTitle4 = new JLabel("Total Open Order Cost: " +"€"+ totalOpenCost);
		labelTitle4.setSize(10,10);
		detailsPanel.add(labelTitle4);
		
		labelTitle2 = new JLabel("Received Orders");
		labelTitle2.setSize(10,10);
		detailsPanel.add(labelTitle2);
		
		double totalReceivedCost = 0;
		
		for(Order order: RetailSystem.getInstance().getOrders()) {
			
			if(order.isReceived()==true) {
				//the order is received
				
				totalReceivedCost += order.getProduct().getCost() * order.getQuantity();
				
				printLabel = new JLabel(order.getOrderID()
					+" | "+DateFormat.getDateInstance().format(order.getOrderDate())
					+" | "+order.getProduct().getProductID()
					+" | "+order.getQuantity()
					+" | "+DateFormat.getDateInstance().format(order.getExpectedDeliveryDate())
					+" | "+DateFormat.getDateInstance().format(order.getDateReceived())
					+" | "+order.isReceived()
					+" | "+calcOrderCost(order.getProduct().getCost(), order.getQuantity()));
			printLabel.setSize(10,10);
			detailsPanel.add(printLabel);
			
			}
		}
		
		labelTitle3 = new JLabel("Total Receved Order Cost: " +"€"+ totalReceivedCost);
		labelTitle3.setSize(10,10);
		detailsPanel.add(labelTitle3);
		
	}
	
	public String calcOrderCost(double cost, int quantity) {
		double orderCost = 0;
		orderCost = cost * quantity;
		String s = "€"+orderCost;
		return s;
	}
	
}