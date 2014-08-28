import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomerGUI extends JPanel {

	JButton addCustomer;
	JButton editCustomer;
	JButton deleteCustomer;
	JButton showCustomer;
	JLabel labelTitle;

	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);

	public CustomerGUI() {
		// declaration and initialization of buttons and labels
		this.setLayout(new GridLayout(0, 1));
		labelTitle = new JLabel("Customer Menu");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		addCustomer = new JButton("Add Customer");
		editCustomer = new JButton("Edit Customer");
		deleteCustomer = new JButton("Remove Customer");
		showCustomer = new JButton("Show Customer");
		colorButton();

		// adding all the components
		this.add(labelTitle);
		this.add(addCustomer);
		this.add(editCustomer);
		this.add(showCustomer);
		this.add(deleteCustomer);
		//fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);

		/*
		 * Add button: it opens a new window where it's possible to insert user
		 * data and if the validation is correct the new user is created and
		 * inserted in the ArrayList
		 */
		addCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				addCustomer.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new CustomerAddGUI());
			}
		});

		/*
		 * Edit button: it opens a new window where it's possible to select the
		 * user from a dropdown list. After a user is selected it is possible to
		 * push the button "Edit User" and automatically will be displayed
		 * fields with the user data in it. It is possible to modify the data
		 * and save it with the "Commit" button.
		 */
		editCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				editCustomer.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new CustomerEditGUI());
			}
		});

		/*
		 * Show button: it opens a new window where it's possible to select the
		 * user from a dropdown list. Pressing the "Show User" button the data
		 * related to that user will be displayed in a read-only way
		 */
		showCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				showCustomer.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new CustomerViewGUI());
			}
		});

		/*
		 * Delete button: it opens a new window where it's possible to select
		 * the customer from a dropdown list. After a customer is selected it is
		 * possible to push the button "Delete Customer" and automatically the customer
		 * will be removed
		 */
		deleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				deleteCustomer.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new CustomerDeleteGUI());
			}
		});

	
	}

	public void colorButton(){
		addCustomer.setBackground(colorButtons);
		addCustomer.setFont(fontButtons);
		editCustomer.setBackground(colorButtons);
		editCustomer.setFont(fontButtons);
		showCustomer.setBackground(colorButtons);
		showCustomer.setFont(fontButtons);
		deleteCustomer.setBackground(colorButtons);
		deleteCustomer.setFont(fontButtons);
	}

}
