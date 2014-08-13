

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

	JPanel jpanel;
	Container container;
	
	public MenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(3,2));
		JButton buttonCustomer = new JButton("Manage Customer");
		JButton buttonProduct = new JButton("Manage Product");
		JButton buttonSupplier = new JButton("Manage Supplier");
		JButton buttonOrder = new JButton("Manage Order");
		JButton buttonAvailability = new JButton("View the availability");
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
			
		}
		else{
			panel.add(buttonCustomer);
			panel.add(buttonAvailability);
			panel.add(buttonViewOrder);
		}
		
		//Define the panel for the Customer managment
		
		JTextField textField1 = new JTextField();
		JLabel label1 = new JLabel("Name");
		JTextField textField2 = new JTextField();
		JLabel label2 = new JLabel("Customer ID");
		JTextField textField3 = new JTextField();
		JLabel label3 = new JLabel("Phone Number");
		JButton but1 = new JButton("Add Customer");
		JButton but2 = new JButton("Edit");
		JButton but3 = new JButton("Delete");

		jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(0,1));
		jpanel.add(label1);
		jpanel.add(textField1);
		jpanel.add(label2);
		jpanel.add(textField2);
		jpanel.add(label3);
		jpanel.add(textField3);
		jpanel.add(but1);
		jpanel.add(but2);
		jpanel.add(but3);
		
		buttonCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGUI customerGui = new CustomerGUI();
			}
		});
				
		this.setVisible(true);
		

	}

}
