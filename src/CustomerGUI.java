import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomerGUI extends JFrame {
	
	private JButton viewCustomer;

	public CustomerGUI() {
		// declaration and initialization of panel, container, layout setting
		// and buttons
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));
		JButton addCustomer = new JButton("Add Customer");
		JButton editCustomer = new JButton("Edit Customer");
		JButton deleteCustomer = new JButton("Delete Customer");
		JButton viewCustomer = new JButton("View Customer");
		JButton mainMenu = new JButton("Main Menu");

		// adding components
		panel.add(addCustomer);
		panel.add(editCustomer);
		panel.add(deleteCustomer);
		panel.add(viewCustomer);
		panel.add(mainMenu);
		

		//Adds customer in a new GUI
		addCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCustomerButton();
				closeCustomerGUI();
			}
		});
		
		editCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editCustomerButton();
				closeCustomerGUI();
			}
		});
		
		
		deleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerDeleteGUI CustomerDeleteGui = new CustomerDeleteGUI();
				closeCustomerGUI();
			}
		});

	
		
		viewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerViewGUI CustomerViewGui = new CustomerViewGUI();
				closeCustomerGUI();
			}
		});

		
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuGUI menuGui = new MenuGUI();
				closeCustomerGUI();
			}
		});

		this.setVisible(true);
	}

	

	public void addCustomerButton() {
		CustomerAddGUI customerAddGUI = new CustomerAddGUI();
		this.setVisible(false);
	}
	
	public void editCustomerButton() {
		CustomerEditGUI customerEditGUI = new CustomerEditGUI();
		this.setVisible(false);
	}

	
	
	
	public static void saveCustomer() {
		try {
			FileWriter customerFile;
			customerFile = new FileWriter("customers.txt");
			DataBase.writeCustomers(RetailSystem.getInstance().getCustomers(), customerFile);
			customerFile.close();// close the customer file
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public void closeCustomerGUI(){
		this.setVisible(false);
			dispose();
	}
	
	

}

