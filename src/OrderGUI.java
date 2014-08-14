import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

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
		setSize(800, 600);
		setTitle(this.getClass().toString());
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);	//null sets the frame to centre
		
		panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(10,25));
		
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
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "cannot reach createOrderGUI");
			}
		}
		
		if(target == editOrder) {
			try {
				EditOrderGUI editOrder = new EditOrderGUI();
				this.setVisible(false);
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "cannot reach EditOrderGUI");
			}
		}
		
		if(target == viewOrder) {
			try {
				ViewOrderGUI viewOrder = new ViewOrderGUI();
				this.setVisible(false);
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "cannot reach viewOrderGUI");
			}
		}
	}
}