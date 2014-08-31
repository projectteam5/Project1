import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
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

public class CustomerEditGUI extends JPanel {

	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JComboBox customerDropDown;
	private JButton doneButton;
	private JButton editButton;
	private JLabel labelName;
	private JLabel labelAddress;
	private JLabel labelPhone;
	private JLabel labelTitleMain;
	private JLabel labelTitle;
	private JLabel mainTitle;
	private String customerID;
	private String selectedCustomerIDPrev;
	private Customer customer;
	private int controlVariable;

	public CustomerEditGUI() {

		controlVariable = 0;
		selectedCustomerIDPrev = null;

		this.setLayout(new GridLayout(0, 1));

		// declaration of the labels and initialisation of labels and text field
		mainTitle = new JLabel("Edit Customer");
		mainTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitle = new JLabel("Please pick the customer you want to edit"
				+ " from the customer list below");
		customerDropDown = new JComboBox();
		Customer.customerListComplete(customerDropDown);
		editButton = new JButton("Edit Customer");
		doneButton = new JButton("Commit Changes");
		nameField = new JTextField("");
		addressField = new JTextField("");
		phoneField = new JTextField("");
		labelName = new JLabel("Name");
		labelAddress = new JLabel("Address");
		labelPhone = new JLabel("Phone Number");

		// adding all the components
		this.add(mainTitle);
		this.add(labelTitle);
		this.add(customerDropDown);
		this.add(editButton);
		this.add(labelName);
		this.add(nameField);
		this.add(labelAddress);
		this.add(addressField);
		this.add(labelPhone);
		this.add(phoneField);

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Cris: using the static method in Customer class
				customerID = RetailSystem.returnIDfromCombobox(customerDropDown
						.getSelectedItem().toString());
				if (controlVariable == 0
						|| !customerID.equals(selectedCustomerIDPrev)) {
					populateFields();
					selectedCustomerIDPrev = customerID;
				}

			}

		});

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = editCustomer();
				if (returnValue == 0) {
					JOptionPane.showMessageDialog(null, "Customer Edited!",
							"Update", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"All fields must be filled!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

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
			customerDropDown.removeAllItems();
			Customer.customerListComplete(customerDropDown);
			returnValue = 0;
		}
		return returnValue;
	}

	public void populateFields() {
		controlVariable = 1;
		customer = Customer.retrieveCustomer(customerID);
		if (customer != null) {
			nameField.setText(customer.getName());
			addressField.setText(customer.getAddress());
			phoneField.setText(customer.getPhoneNumber());
			this.add(doneButton);
			revalidate();
			repaint();
		}

	}

}
