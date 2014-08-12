package view;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.RetailSystem;

public class MenuGUI extends JFrame {

	public MenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(3,2));
		//if it is a manager i can see Manage Customer, Manage Product
		//Manage Supplier, Manage Order
		if (RetailSystem.getInstance().getCurrentUserType().equalsIgnoreCase("Manager")){
			JButton buttonCustomer = new JButton("Manage Customer");
			panel.add(buttonCustomer);
			JButton buttonProduct = new JButton("Manage Product");
			panel.add(buttonProduct);
			JButton buttonSupplier = new JButton("Manage Supplier");
			panel.add(buttonSupplier);
			JButton buttonOrder = new JButton("Manage Order");
			panel.add(buttonOrder);
			JButton buttonAvailability = new JButton("View the availability");
			panel.add(buttonAvailability);
			JButton buttonViewOrder = new JButton("View Order");
			panel.add(buttonViewOrder);
			
		}
		else{
			JButton buttonCustomer = new JButton("Manage Customer");
			panel.add(buttonCustomer);
			JButton buttonAvailability = new JButton("View Availability");
			panel.add(buttonAvailability);
			JButton buttonViewOrder = new JButton("View Order");
			panel.add(buttonViewOrder);
		}
		
		this.setVisible(true);
	}

}
