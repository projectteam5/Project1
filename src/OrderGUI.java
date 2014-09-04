import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class OrderGUI extends JPanel implements ActionListener {
	
	private JLabel labelTitle;
	private JButton createOrder;
	private JButton editOrder;
	private JButton viewOrder;
	private JButton removeOrder;
	private JButton viewAllOrders;
	
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);
	
	public OrderGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitle = new JLabel("Order Menu");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		
		createOrder = new JButton("Create an Order");
		editOrder = new JButton("Edit an Order");
		viewOrder = new JButton("View an Order");
		removeOrder = new JButton("Remove an Order");
		viewAllOrders = new JButton("View All Orders");
		
		colorButton();
		
		this.add(labelTitle);
		this.add(createOrder);
		this.add(editOrder);
		this.add(removeOrder);
		this.add(viewOrder);
		this.add(viewAllOrders);
		//fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		this.add(labelEmpty);

		
		createOrder.addActionListener(this);
		editOrder.addActionListener(this);
		viewOrder.addActionListener(this);
		removeOrder.addActionListener(this);
		viewAllOrders.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == createOrder) {
			try {
				
				colorButton();
				createOrder.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new CreateOrderGUI());
				
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach createOrderGUI");
			}
		}
		
		if(target == editOrder) {
			try {
				
				colorButton();
				editOrder.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new EditOrderGUI());

			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach EditOrderGUI");
			}
		}
		
		if(target == viewOrder) {
			try {
				
				colorButton();
				viewOrder.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new ViewOrderGUI());

			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach viewOrderGUI");
			}
		}
		
		if(target == removeOrder) {
			try {
				
				colorButton();
				removeOrder.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new RemoveOrderGUI());
				
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach viewOrderGUI");
			}
		}
		
		if(target == viewAllOrders) {
			try {
				
				colorButton();
				viewAllOrders.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new ListOrders());
				
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach ViewAllOrdersGUI");
			}
		}
		
	}
	
	public void colorButton() {
		
		createOrder.setBackground(colorButtons);
		createOrder.setFont(fontButtons);
		editOrder.setBackground(colorButtons);
		editOrder.setFont(fontButtons);
		viewOrder.setBackground(colorButtons);
		viewOrder.setFont(fontButtons);
		removeOrder.setBackground(colorButtons);
		removeOrder.setFont(fontButtons);
		viewAllOrders.setBackground(colorButtons);
		viewAllOrders.setFont(fontButtons);
		
	}
	
}