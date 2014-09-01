import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddSupplierGUI extends JPanel {
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel title;
	private JButton button;
	private JButton addButton;

	// Instance of supplier in aid of accessing removal method
	// And variable used to hold position in list of requested removal

	public AddSupplierGUI() {
		this.setLayout(new GridLayout(0, 1));
		textField2 = new JTextField();
		textField3 = new JTextField();
		title = new JLabel("Add a new supplier");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		label2 = new JLabel("Supplier name");
		label3 = new JLabel("Supplier contact number");
		addButton = new JButton("ADD");
		button = new JButton("Supplier Menu");

		this.add(title);
		this.add(label2);
		this.add(textField2);
		this.add(label3);
		this.add(textField3);
		this.add(addButton);
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);


		// Button pressed, checks if entries are valid and does not already
		// exist
		// then creates supplier and adds to list
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField2.getText();
				String phoneNumber = textField3.getText();
				if(RetailSystem.validatePhone(phoneNumber)){
					if (supplierValidation(name, phoneNumber)) {
						Supplier supplier = new Supplier(name, phoneNumber);
						supplier.addSupplierToList(supplier);
						JOptionPane.showMessageDialog(null,
								"Supplier Created and added to the list",
								"Success", JOptionPane.PLAIN_MESSAGE);
						supplier.saveSupplier();
						textField2.setText("");
						textField3.setText("");
					}
				}				
			}
		});

	}

	// Methods used to test that the input of data is valid and supplier does
	// NOT exist
	public boolean supplierValidation(String name, String phoneNumber) {
		boolean correct = true;
		if (name.isEmpty() || phoneNumber.isEmpty()) {
			correct = false;
			JOptionPane.showMessageDialog(null,
					"All fields must be filled out!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

		return correct;
	}
}
