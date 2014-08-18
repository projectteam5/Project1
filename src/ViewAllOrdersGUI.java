import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.text.DateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ViewAllOrdersGUI extends JFrame{

	private JPanel panel;
	private JPanel detailsPanel;
	private Container container;
	private JScrollPane scrollPane;
	private JLabel orderDetailsLabel;
	private JLabel printLabel;
	private JLabel counterLabel;

	public ViewAllOrdersGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 200);
		
		panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setLayout(new BorderLayout());
		
		detailsPanel = new JPanel(new GridLayout(0,1));
		detailsPanel.setSize(100,100);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(detailsPanel);
		orderDetailsLabel = new JLabel("Order Details");
		
		panel.add(detailsPanel);
		
		panel.add(orderDetailsLabel,BorderLayout.NORTH);
		panel.add(scrollPane,BorderLayout.CENTER);
		scrollPane.setViewportView(detailsPanel);
		
		counterLabel = new JLabel("Number of Orders in System: " + RetailSystem.getInstance().getOrders().size());
		panel.add(counterLabel, BorderLayout.SOUTH);
		
		setVisible(true);
		
		for(Order order: RetailSystem.getInstance().getOrders()) {
			
			printLabel = new JLabel(order.getOrderID()
					+" | "+DateFormat.getDateInstance().format(order.getOrderDate())
					+" | "+order.getProduct().getProductID()
					+" | "+order.getQuantity()
					+" | "+DateFormat.getDateInstance().format(order.getExpectedDeliveryDate())
					+" | "+DateFormat.getDateInstance().format(order.getDateReceived())
					+" | "+order.isReceived());
			
			printLabel.setSize(10,10);
			detailsPanel.add(printLabel);
		}
	}
}