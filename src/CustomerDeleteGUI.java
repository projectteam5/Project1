import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomerDeleteGUI extends JFrame {

	private JPanel panel;
	private JComboBox customersDropDown;
	private JLabel labelTitle;
	private JButton deleteButton;
	private JButton customerMenuButton;
	private String selectedCustomerID;
	private Customer customerRemove;
	
	public CustomerDeleteGUI() {
		// declaration and initialization of panel, container, layout setting
		// and buttons
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));

		labelTitle = new JLabel(
				"Please pick the customer you wish to delete from the list below");
		customersDropDown = new JComboBox();
		buildCustomersDropDown();
		deleteButton = new JButton("Delete Customer");
		customerMenuButton = new JButton("Customer Menu");

		panel.add(labelTitle);
		panel.add(customersDropDown);
		panel.add(deleteButton);
		panel.add(customerMenuButton);

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteCustomerButton();
			}

		});
		
		customerMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGUI customerGui = new CustomerGUI();
				closeCustomerDeleteGUI();
				
			}
		});

		setVisible(true);

	}

	public void deleteCustomerButton() {
		customerRemove = null;
		String selectedCustomerString = customersDropDown.getSelectedItem().toString();
		String[] selectedCustomerArray = selectedCustomerString.split(";");
		String selectedCustomerIDString = selectedCustomerArray[0];
		String[] selectedCustomerIDArray = selectedCustomerIDString.split(":");
		selectedCustomerID = selectedCustomerIDArray[1].trim();
		customerRemove = Customer.retrieveCustomer(selectedCustomerID);
		if (customerRemove != null) {
			customerRemove.setActive(false);
			JOptionPane.showMessageDialog(null, "Customer removed",
					"Customer Deleted", JOptionPane.INFORMATION_MESSAGE);
			Customer.saveCustomer();
			addAndRefresh();
		}

	}

	public void addAndRefresh() {
		panel.remove(labelTitle);
		panel.remove(customersDropDown);
		panel.remove(deleteButton);
		panel.remove(customerMenuButton);
		panel.add(labelTitle);
		customersDropDown = new JComboBox();
		buildCustomersDropDown();
		panel.add(customersDropDown);
		panel.add(deleteButton);
		panel.add(customerMenuButton);
		panel.revalidate();
		revalidate();
		repaint();
	}
	
	public void buildCustomersDropDown(){
		for (Customer customer : RetailSystem.getInstance().getCustomers()){
			if(!customer.getCustomerID().equals(RetailSystem.getInstance()) && customer.isActive()){
				String string = "ID: " + customer.getCustomerID() + " ; Name: "
						+ customer.getName();
				customersDropDown.addItem(string);
			}
		}
	}
	
	public void closeCustomerDeleteGUI(){
		this.setVisible(false);
		dispose();
	}

}
