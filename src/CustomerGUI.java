import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomerGUI extends JFrame {

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
		JButton AddCustomer = new JButton("Add Customer");
		JButton EditCustomer = new JButton("Edit Customer");
		JButton DeleteCustomer = new JButton("Delete Customer");

		// adding components
		panel.add(AddCustomer);
		panel.add(EditCustomer);
		panel.add(DeleteCustomer);

		//Adds customer in a new GUI
		AddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCustomerButton();
			}
		});

		//Enter customer ID then edit if there is a corresponding Customer ID in the system
		EditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = editCustomerButton();
				if (returnValue == 1) {
					JOptionPane.showMessageDialog(null, "ID not found!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		DeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = deleteCustomerButton();
				if (returnValue == 1) {
					JOptionPane.showMessageDialog(null, "ID not found!",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Customer correctly removed", "Customer Deleted",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		this.setVisible(true);

	}

	public static boolean existingID(String id) {
		for (Customer customer : RetailSystem.getInstance().getCustomers()) {
			if (customer.getCustomerID().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static boolean notLoginID(String id) {
		if (RetailSystem.getInstance().getCurrentUserID().equals(id)) {
			return false;
		}
		return true;
	}

	public void addCustomerButton() {
		CustomerAddGUI addCustomerGUI = new CustomerAddGUI();
		this.setVisible(false);
	}

	public int editCustomerButton() {
		String inputValue = JOptionPane
				.showInputDialog("Please insert a UserID");
		if (existingID(inputValue)) { 
			System.out.println("Before open GUI");
			CustomerEditGUI gui = new CustomerEditGUI(inputValue);
			this.setVisible(false);
			return 0;
		} else {
			System.out.println("After open GUI");
			return 1;
		}
	}
	
	
	public int deleteCustomerButton() {
		String inputValue = JOptionPane
				.showInputDialog("Please insert a CustomerID");
		if (existingID(inputValue) && notLoginID(inputValue)) {
			Customer customerRemove = null;
			for (Customer customer : RetailSystem.getInstance().getCustomers()) {
				if (customer.getCustomerID().equals(inputValue)) {
					customerRemove = customer;
				}
			}
			RetailSystem.getInstance().getCustomers().remove(customerRemove);
			return 0;
		} else {
			return 1;
		}

	}

}

