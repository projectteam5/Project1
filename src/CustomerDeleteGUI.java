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
import javax.swing.border.EmptyBorder;

public class CustomerDeleteGUI extends JPanel {

	private JComboBox customersDropDown;
	private JLabel labelTitleMain;
	private JLabel labelTitle;
	private JButton deleteButton;
	private String selectedCustomerID;
	private Customer customerRemove;
	
	JLabel labelEmpty = new JLabel(" ");
	JLabel labelEmpty1 = new JLabel(" ");
	JLabel labelEmpty2 = new JLabel(" ");
	JLabel labelEmpty3 = new JLabel(" ");
	JLabel labelEmpty4 = new JLabel(" ");

	public CustomerDeleteGUI() {
//setting layout
		this.setLayout(new GridLayout(0, 1));

		labelTitleMain = new JLabel("Delete Customer");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitle = new JLabel("Please pick the customer you want"
				+ " to delete from the list below");
		customersDropDown = new JComboBox();
		buildCustomersDropDown();
		deleteButton = new JButton("Delete Customer");

		this.add(labelTitleMain);
		this.add(labelTitle);
		this.add(customersDropDown);
		this.add(deleteButton);
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		this.add(labelEmpty4);

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteCustomerButton();
			}

		});

	}

	public void deleteCustomerButton() {
		customerRemove = null;
		// using the static method in Customer class
		selectedCustomerID = RetailSystem
				.returnIDfromCombobox(customersDropDown.getSelectedItem()
						.toString());
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
		this.removeAll();
		this.add(labelTitleMain);
		this.add(labelTitle);
		customersDropDown = new JComboBox();
		buildCustomersDropDown();
		this.add(customersDropDown);
		this.add(deleteButton);
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		this.add(labelEmpty4);
		this.revalidate();
		revalidate();
		repaint();
	}

	public void buildCustomersDropDown() {
		for (Customer customer : RetailSystem.getInstance().getCustomers()) {
			if (!customer.getCustomerID().equals(RetailSystem.getInstance())
					&& customer.isActive()) {
				String string = "ID: " + customer.getCustomerID() + " ; Name: "
						+ customer.getName();
				customersDropDown.addItem(string);
			}
		}
	}

}
