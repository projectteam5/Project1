//GUI done. Method working//test commit
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddProductGUI extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldCost;
	private JTextField textFieldMarkup;
	private ArrayList<String> names = new ArrayList<String>();
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private double cost;
	private double markup;
	private Supplier supplierPicked;

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;

	public AddProductGUI() {
		this.setLayout(new GridLayout(0, 1));
		label2 = new JLabel("Product Name");
		textFieldName = new JTextField();
		label3 = new JLabel("Product Cost");
		textFieldCost = new JTextField();
		label4 = new JLabel("Product Markup");
		textFieldMarkup = new JTextField();

		compileSupplierNames();
		JLabel label5 = new JLabel("Product Supplier ID");
		JButton submitButton = new JButton("Submit");

		this.add(label2);
		this.add(textFieldName);
		this.add(label3);
		this.add(textFieldCost);
		this.add(label4);
		this.add(textFieldMarkup);
		this.add(label5);
		this.add(supplierDropDown);
		this.add(submitButton);
		this.setVisible(true);

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo0) {

				boolean duplicateProductID = false;
				boolean duplicateProductName = false;
				boolean correctInfo = true;
				String productName = textFieldName.getText();
				String name = textFieldName.getText();
				if (!productName.equals("") && productName != null) {
					try {
						cost = Double.parseDouble(textFieldCost.getText());
						markup = Double.parseDouble(textFieldMarkup.getText());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null,
								"Please enter in correct format");
						correctInfo = false;
					}

					String supplierChoice = supplierDropDown.getSelectedItem()
							.toString();
					for (Supplier supplier : RetailSystem.getInstance()
							.getSuppliers()) {
						if (supplierChoice.contains(supplier.getName())) {
							supplierPicked = supplier;
						}
					}

					// Check to see if productID is already in system
					for (Product product : RetailSystem.getInstance()
							.getProducts()) {
						if (product.getProductID()
								.equalsIgnoreCase(productName)) {
							duplicateProductName = true;

						}
					}
					if (duplicateProductName == true) {
						JOptionPane.showMessageDialog(null,
								"Product in system with same name");

					}

					// If product is not already in system, add to system
					if ((correctInfo == true) && (!duplicateProductName)) {
						try {
							JOptionPane.showMessageDialog(null,
									"Adding product");
							Product product = new Product(name, cost, markup,supplierPicked);
							RetailSystem.getInstance().getProducts().add(product);
							saveProduct();
							cleanFileds();
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,
									"Error processing request");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Please fill all the fields");
				}
			}
		});

	}

	public void compileSupplierNames() {
		for (Supplier supplier : RetailSystem.getInstance().getSuppliers()) {
			if (supplier.isActive()) {
				supplierDropDown.addItem(supplier.getName());

			}
		}
	}


	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	public JTextField getTextFieldCost() {
		return textFieldCost;
	}

	public void setTextFieldCost(JTextField textFieldCost) {
		this.textFieldCost = textFieldCost;
	}

	public JTextField getTextFieldMarkup() {
		return textFieldMarkup;
	}

	public void setTextFieldMarkup(JTextField textFieldMarkup) {
		this.textFieldMarkup = textFieldMarkup;
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}


	public static void saveProduct() {
		try {
			FileWriter productFile;
			productFile = new FileWriter("products.txt");
			DataBase.writeProducts(RetailSystem.getInstance().getProducts(),
					productFile);
			productFile.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public void cleanFileds(){
		textFieldName.setText("");
		textFieldCost.setText("");
		textFieldMarkup.setText("");
	}

}
