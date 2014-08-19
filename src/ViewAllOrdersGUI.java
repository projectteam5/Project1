import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ViewAllOrdersGUI extends JFrame implements ActionListener {

	private JPanel panel;
	private JPanel detailsPanel;
	private Container container;
	private JScrollPane scrollPane;
	private JLabel orderDetailsLabel;
	private JLabel printLabel;
	private JLabel counterLabel;
	
	private JButton returnToMainMenu;
	private JLabel printLabel2;
	private JLabel labelTitle;
	private JLabel labelTitle2;
	private JLabel labelTitle3;
	private JLabel printLabel3;
	private JLabel labelTitle4;

	public ViewAllOrdersGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		setTitle("View Active Orders");
		
		panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setLayout(new BorderLayout());
		
		detailsPanel = new JPanel(new GridLayout(0,1));
		detailsPanel.setSize(100,100);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(detailsPanel);
		orderDetailsLabel = new JLabel("Order Details");
		
		returnToMainMenu = new JButton("Main Menu");
		
		panel.add(detailsPanel);
		
		panel.add(orderDetailsLabel,BorderLayout.NORTH);
		panel.add(scrollPane,BorderLayout.CENTER);
		scrollPane.setViewportView(detailsPanel);
		
		detailsPanel.add(returnToMainMenu);
		
		returnToMainMenu.addActionListener(this);
		
		int count=0;
		for(Order order : RetailSystem.getInstance().getOrders()) {
			if(order.isActive()) {
				count++;
			}
		}
		counterLabel = new JLabel("Active Orders in System: " + count);
		panel.add(counterLabel, BorderLayout.SOUTH);
		
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
	
	
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == returnToMainMenu) {
			try {
				MenuGUI returnToMainMenu = new MenuGUI();
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