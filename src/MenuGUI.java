

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MenuGUI extends JFrame {

	
	
	public MenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(4,2));
		JButton buttonUser = new JButton("Manage Users");
		JButton buttonCustomer = new JButton("Manage Customers");
		JButton buttonProduct = new JButton("Manage Products");
		JButton buttonSupplier = new JButton("Manage Suppliers");
		JButton buttonOrder = new JButton("Manage Orders");
		JButton buttonAvailability = new JButton("View Stock");
		JButton buttonViewOrder = new JButton("View Order");
		//if it is a manager i can see Manage Customer, Manage Product
		//Manage Supplier, Manage Order
		if (RetailSystem.getInstance().getCurrentUserType().equalsIgnoreCase("Manager")){
			panel.add(buttonCustomer);
			panel.add(buttonProduct);
			panel.add(buttonSupplier);
			panel.add(buttonOrder);
			panel.add(buttonAvailability);
			panel.add(buttonViewOrder);
			panel.add(buttonUser);
			
		}
		else{
			panel.add(buttonCustomer);
			panel.add(buttonAvailability);
			panel.add(buttonViewOrder);
		}
		
		//Define the panel for the Customer managment
		
		buttonCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGUI customerGui = new CustomerGUI();
			}
		});
		
		buttonUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserGUI userGui = new UserGUI();
			}
		});
				
		this.setVisible(true);
		

	}

}
