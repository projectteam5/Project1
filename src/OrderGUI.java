import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class OrderGUI extends JFrame implements ActionListener {
	private JPanel panel;
	private Container container;
	private JButton createOrder;
	private JButton editOrder;
	private JButton viewOrder;
	
	public OrderGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		setTitle(this.getClass().toString());
		
		panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(25,25));
		
		createOrder = new JButton("Create Order");
		editOrder = new JButton("Edit Order");
		viewOrder = new JButton("View Order");
		panel.add(createOrder);
		panel.add(editOrder);
		panel.add(viewOrder);
		
		createOrder.addActionListener(this);
		editOrder.addActionListener(this);
		viewOrder.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == createOrder) {
			try {
				CreateOrderGUI createOrder = new CreateOrderGUI();
				this.setVisible(false);
			} catch(Exception e) {
				System.out.println("cannot reach createOrderGUI");
			}
		}
		
		if(target == editOrder) {
			try {
				EditOrderGUI editOrder = new EditOrderGUI();
				this.setVisible(false);
			} catch(Exception e) {
				System.out.println("cannot reach editOrderGUI");
			}
		}
		
		if(target == viewOrder) {
			try {
				ViewOrderGUI viewOrder = new ViewOrderGUI();
				this.setVisible(true);
			} catch(Exception e) {
				System.out.println("cannot reach viewOrderGUI");
			}
		}
	}
}