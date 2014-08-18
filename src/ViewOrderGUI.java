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

	public ViewOrderGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 200);
		
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
							+" | "+order.isReceived());

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
}