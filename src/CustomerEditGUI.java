
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CustomerEditGUI extends JFrame {

	private String customerID;
	private JTextField NameField;
	private JTextField AddressField;
	private JTextField PhoneField;
	private Customer customer;
	private JButton customerMenuButton;
	private JComboBox customersDropDown;
	private JLabel labelTitle;
	private JButton editButton;
	
	public CustomerEditGUI() {


		// declaration and initialization of panel, container and layout setting
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));
		
		labelTitle = new JLabel(
				"Please pick the customer you wish to edit from the list below");
		customersDropDown = new JComboBox();
		buildCustomersDropDown();
		editButton = new JButton("Edit Customer");
		customerMenuButton = new JButton("Customer Menu");
		
		panel.add(labelTitle);
		panel.add(customersDropDown);
		panel.add(editButton);
		panel.add(customerMenuButton);

		/* declaration of the labels and initialization of labels and text field
		NameField = new JTextField(customer.getName());
		AddressField = new JTextField(customer.getAddress());
		PhoneField = new JTextField(customer.getPhoneNumber());
		JLabel label1 = new JLabel("Name");
		JLabel label2 = new JLabel("Address");
		JLabel label3 = new JLabel("Phone Number");
		JButton doneButton = new JButton("Done");
		customerMenuButton = new JButton("Customer Menu");

		// adding all the components
		panel.add(label1);
		panel.add(NameField);
		panel.add(label2);
		panel.add(AddressField);
		panel.add(label3);
		panel.add(PhoneField);
		panel.add(doneButton);
		panel.add(customerMenuButton);
		Container container = getContentPane();
		container.add(panel);

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = editCustomer();
				if (returnValue == 0) {
					saveCustomer();
					JOptionPane.showMessageDialog(null,
							"Customer correctly updated", "Update",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"All fields must be filled!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});*/

		this.setVisible(true);
		
		customerMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGUI customerGui = new CustomerGUI();
				closeGUI();
				
			}
		});

		setVisible(true);
	}

	public Customer retrieveCustomer(String id) {
		Customer customer = null;
		for (Customer customerRS : RetailSystem.getInstance().getCustomers()) {
			if (customerRS.getCustomerID().equals(customerID)) {
				customer = customerRS;
			}
		}
		return customer;
	}

	public int editCustomer() {
		int returnValue = 1;
		String name = NameField.getText();
		String address = AddressField.getText();
		String phoneNumber = PhoneField.getText();
		if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty()) {
			for (Customer customerRS : RetailSystem.getInstance()
					.getCustomers()) {
				if (customerRS.getCustomerID().equals(customerID)) {
					customerRS.setName(name);
					customerRS.setAddress(address);
					customerRS.setPhoneNumber(phoneNumber);
					returnValue = 0;
					this.setVisible(false);
				}
			}
		}
		return returnValue;
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
	
	public void closeGUI(){
		this.setVisible(false);
	}
}
