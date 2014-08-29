import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ViewAllOrdersByDateGUI extends JPanel implements ActionListener {
	
	private JLabel labelTitleMain;
	private JTextField searchTextField;
	private JButton searchButton;
	private JLabel formatDataLabel;
	private JLabel formatHeadingLabel;
	private double totalOrderCost;
	private JLabel labelTotalOrderCost;

	public ViewAllOrdersByDateGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitleMain = new JLabel("Search for an Order");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		
		searchTextField = new JTextField();
		searchTextField.setText(DateFormat.getDateInstance().format(new Date()));
		
		searchButton = new JButton("Search");
		
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
		
		formatDataLabel = new JLabel();
		
		labelTotalOrderCost = new JLabel();
		
		labelTotalOrderCost.setFont(new Font("Arial", Font.ITALIC, 12));
		
		this.add( labelTitleMain );
		this.add( searchTextField );
		this.add( searchButton );
			
		searchButton.addActionListener(this);
		
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object target = e.getSource();
		
		if( target == searchButton ) {
			
			remove(formatHeadingLabel);
			
			remove(formatDataLabel);
			
			remove(labelTotalOrderCost);
			
			totalOrderCost = 0;
			
			for( Order order: RetailSystem.getInstance().getOrders() ) {
				
				if( Order.searchOrderByDate( order.getOrderDate(), searchTextField.getText() ) ) {
					
					totalOrderCost += order.getProduct().getCost() * order.getQuantity();
					
					formatDataLabel = new JLabel();
					
					formatDataLabel.setText("<html><table>"
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
					
					labelTotalOrderCost.setText("Total Order Cost: €" + totalOrderCost);
					
					this.add( formatHeadingLabel );
				
					this.add( formatDataLabel );
					
					this.add( labelTotalOrderCost );
					
					break;
				
				}
				
				repaint();
				
				revalidate();
				
			}
			
		}
		
	}

}