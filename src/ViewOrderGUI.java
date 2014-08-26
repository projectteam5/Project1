import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class ViewOrderGUI extends JPanel implements ActionListener {
	private JButton viewOrderButton;
	private JLabel orderDetailsLabel ;
	private JLabel printLabel;
	private JComboBox<String> orderList;
	private JLabel counterLabel;
	private JLabel labelTitleMain;

	public ViewOrderGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitleMain = new JLabel("View an Order");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		
		orderList = new JComboBox<String>();
		viewOrderButton = new JButton("View Order Details");
		orderDetailsLabel = new JLabel("Order Details");
		printLabel = new JLabel();
		
		this.add(labelTitleMain);
		this.add(orderDetailsLabel,BorderLayout.NORTH);
		this.add(orderList);
		this.add(viewOrderButton);
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

					printLabel.setText(order.getOrderID()
							+" | "+DateFormat.getDateInstance().format(order.getOrderDate())
							+" | "+order.getProduct().getProductID()
							+" | "+order.getQuantity()
							+" | "+DateFormat.getDateInstance().format(order.getExpectedDeliveryDate())
							+" | "+DateFormat.getDateInstance().format(order.getDateReceived())
							+" | "+order.isReceived()
							+" | "+calcOrderCost(order.getProduct().getCost(), order.getQuantity())
							+" | "+order.isActive());

					break;
				}
			}
			
			if(!orderFound){
				
				JOptionPane.showMessageDialog(this, "No Order With This ID in System!");
			}
		}
		
	}
	
	public String calcOrderCost(double cost, int quantity) {
		double orderCost = 0;
		orderCost = cost * quantity;
		String s = "€"+orderCost;
		return s;
	}
	
}