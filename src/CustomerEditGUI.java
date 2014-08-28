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
	private String customerID;
	private String selectedCustomerIDPrev;
	private Customer customer;
	private int controlVariable;

	public CustomerEditGUI() {

		controlVariable = 0;
		selectedCustomerIDPrev = null;

		this.setLayout(new GridLayout(0, 1));

		// declaration of the labels and initialization of labels and text field
		labelTitle = new JLabel("Please pick the customer you want to edit"
				+ " from the customer list below");
		customerDropDown = new JComboBox();
		Customer.customerListComplete(customerDropDown);
		editButton = new JButton("Edit Customer");
		doneButton = new JButton("Commit Changes");

		// adding all the components
		this.add(labelTitle);
		this.add(customerDropDown);
		this.add(editButton);

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Cris: using the static method in Customer class
				customerID = Customer.returnIDfromCombobox(customerDropDown
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
					JOptionPane.showMessageDialog(null, "Customer Edited",
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
			returnValue = 0;
		}
		return returnValue;
	}

	public void populateFields() {
		if (controlVariable != 0) {
			this.remove(labelName);
			this.remove(nameField);
			this.remove(labelAddress);
			this.remove(addressField);
			this.remove(labelPhone);
			this.remove(phoneField);
			this.remove(customerDropDown);
			this.remove(editButton);
			this.remove(doneButton);

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

			this.remove(customerDropDown);
			this.remove(editButton);
			this.remove(labelTitle);
			this.add(labelName);
			this.add(nameField);
			this.add(labelAddress);
			this.add(addressField);
			this.add(labelPhone);
			this.add(phoneField);
			this.add(doneButton);

			revalidate();
			repaint();
		}

	}

}
