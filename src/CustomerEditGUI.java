import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CustomerEditGUI extends JFrame {

	private JPanel panel;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JComboBox customerDropDown;
	private JButton doneButton;
	private JButton editButton;
	private JLabel labelName;
	private JLabel labelAddress;
	private JLabel labelPhone;
	private JLabel labelTitle;
	private String customerID;
	private String selectedCustomerIDPrev;
	private Customer customer;
	private int controlVariable;
	private JButton customerMenuButton;

	public CustomerEditGUI() {

		controlVariable = 0;
		selectedCustomerIDPrev = null;

		// declaration and initialization of panel, container and layout setting
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 200);
		this.setTitle("Edit Customer");
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));

		// declaration of the labels and initialization of labels and text field
		labelTitle = new JLabel(
				"Please pick the customer you want to edit from the customer list below");
		customerDropDown = new JComboBox();
		Customer.customerListComplete(customerDropDown);
		editButton = new JButton("Edit Customer");
		doneButton = new JButton("Commit Changes");
		customerMenuButton  = new JButton("Customer Menu");
		// adding all the components
		panel.add(labelTitle);
		panel.add(customerDropDown);
		panel.add(editButton);
		panel.add(customerMenuButton);

		Container container = getContentPane();
		container.add(panel);

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedCustomerString = customerDropDown.getSelectedItem()
						.toString();
				String[] selectedCustomerArray = selectedCustomerString.split(";");
				String selectedCustomerIDString = selectedCustomerArray[0];
				String[] selectedCustomerIDArray = selectedCustomerIDString.split(":");
				customerID = selectedCustomerIDArray[1].trim();
				if (controlVariable == 0 || !customerID.equals(selectedCustomerIDPrev)) {
					populateFields();
					selectedCustomerIDPrev = customerID;
				}
			}
		});

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = editCustomer();
				if (returnValue == 0) {
					JOptionPane.showMessageDialog(null,
							"Customer Edited", "Update",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"All fields must be filled!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		
		customerMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGUI customerGui = new CustomerGUI();
				closeCustomerEditGUI();
				
			}
		});

		this.setVisible(true);
	}

	public int editCustomer() {
		int returnValue = 1;
		String name = nameField.getText();
		String address = addressField.getText();
		String phone = phoneField.getText();
		if (name != null && address != null && phone != null) {

			customer.setName(name);
			customer.setAddress(address);
			customer.setPhoneNumber(phone);
			Customer.saveCustomer();
			returnValue = 0;
		}
		return returnValue;
	}

	public void populateFields() {
		if (controlVariable != 0) {
			panel.remove(labelAddress);
			panel.remove(addressField);
			panel.remove(labelPhone);
			panel.remove(phoneField);
			panel.remove(doneButton);
		}
		controlVariable = 1;
		customer = Customer.retrieveCustomer(customerID);
		if (customer != null) {
			nameField = new JTextField(customer.getName());
			addressField = new JTextField(customer.getAddress());
			phoneField = new JTextField(customer.getPhoneNumber());
			labelName = new JLabel("Name");
			labelAddress = new JLabel("Address");
			labelPhone = new JLabel("Phone Number");
			
			panel.add(labelName);
			panel.add(nameField);
			panel.add(labelAddress);
			panel.add(addressField);
			panel.add(labelPhone);
			panel.add(phoneField);
			panel.add(doneButton);
			
			revalidate();
			repaint();
		}

	}
	public void closeCustomerEditGUI(){
		this.setVisible(false);
		dispose();
	}
}
