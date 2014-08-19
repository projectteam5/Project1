import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.*;

public class ViewOrderGUI extends JFrame implements ActionListener {
	private JPanel panel;
	private Container container;
	private JButton viewOrderButton;
	private JLabel orderDetailsLabel ;
	private JLabel printLabel;
	private JComboBox<String> orderList;
	
	private JButton returnToMainMenu;
	private JLabel counterLabel;

	public ViewOrderGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 200);
		setTitle("View All Orders");
		
		panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setLayout(new GridLayout(0,1));	
		
		orderList = new JComboBox<String>();
		viewOrderButton = new JButton("View Order Details");
		orderDetailsLabel = new JLabel("Order Details");
		printLabel = new JLabel();
		
		returnToMainMenu = new JButton("Main Menu");

		panel.add(orderDetailsLabel,BorderLayout.NORTH);
		panel.add(orderList);
		panel.add(viewOrderButton);
		panel.add(printLabel);
		container.add(panel);
		
		counterLabel = new JLabel("Total Orders in System: " + RetailSystem.getInstance().getOrders().size());
		panel.add(counterLabel, BorderLayout.SOUTH);
		
		panel.add(returnToMainMenu);
		
		viewOrderButton.addActionListener(this);
		
		returnToMainMenu.addActionListener(this);
		
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
		
		if(target == returnToMainMenu) {
			try {
				OrderGUI returnToMainMenu = new OrderGUI();
				this.setVisible(false);
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach OrderGUI");
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