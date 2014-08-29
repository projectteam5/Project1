import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class ViewOrderGUI extends JPanel implements ActionListener {
	private JButton viewOrderButton;
	private JLabel printLabel;
	private JComboBox<String> orderList;
	private JLabel counterLabel;
	private JLabel labelTitleMain;
	
	private JLabel orderID;
	private JLabel orderDate;
	private JLabel product;
	private JLabel quantity;
	private JLabel deliveryDate;
	private JLabel receivedDate;
	private JLabel isReceived;
	private JLabel orderCost;
	
	public ViewOrderGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitleMain = new JLabel("View an Order");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		
		orderList = new JComboBox<String>();
		
		viewOrderButton = new JButton("View Order Details");
		
		printLabel = new JLabel();
		
		orderID = new JLabel();
		orderDate = new JLabel();
		product = new JLabel();
		quantity = new JLabel();
		deliveryDate = new JLabel();
		receivedDate = new JLabel();
		isReceived = new JLabel();
		orderCost = new JLabel();
		
		this.add(labelTitleMain);
		this.add(orderList);
		this.add(viewOrderButton);
		
		this.add(orderID);
		this.add(orderDate);
		this.add(product);
		this.add(quantity);
		this.add(deliveryDate);
		this.add(receivedDate);
		this.add(isReceived);
		this.add(orderCost);
		
		this.add(printLabel);
		
		counterLabel = new JLabel("Total Orders in System: " + RetailSystem.getInstance().getOrders().size());
		this.add(counterLabel, BorderLayout.SOUTH);
		
		viewOrderButton.addActionListener(this);
		
		setVisible(true);
		
		for(Order order: RetailSystem.getInstance().getOrders()){
				orderList.addItem(order.getOrderID());
		}
		
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == viewOrderButton) {
			
			String orderID = orderList.getSelectedItem().toString();
			boolean orderFound = false;

			for(Order order: RetailSystem.getInstance().getOrders()){
				if(orderID.equalsIgnoreCase(order.getOrderID())){

					orderFound = true;
					
					this.orderID.setText("Order ID: " + order.getOrderID());
					this.orderDate.setText("Order Date: "+DateFormat.getDateInstance().format(order.getOrderDate()));
					this.product.setText("Product Name: " + order.getProduct().getName());
					this.quantity.setText("Quantity: "+ order.getQuantity());
					this.deliveryDate.setText("Expected Delivery Date: "+ DateFormat.getDateInstance().format(order.getExpectedDeliveryDate()));
					this.receivedDate.setText("Date Received: "+ DateFormat.getDateInstance().format(order.getDateReceived()));
					this.isReceived.setText("Is Received?: "+ order.isReceived());
					this.orderCost.setText("Order Cost: "+ Order.calculateOrderCost(order.getProduct().getCost(), order.getQuantity()));
					
					break;
					
				}
			}
			
			if(!orderFound){
				
				JOptionPane.showMessageDialog(this, "No Order With This ID in System!");
			}
		}
		
	}
	
}