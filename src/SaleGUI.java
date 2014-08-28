import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class SaleGUI extends JPanel {

	private JLabel productLabel;
	private JLabel customerLabel;
	private JLabel labelTitle;
	private JLabel quantityLabel;
	private JLabel runningTotalLabel = new JLabel("Running Total:");

	private JComboBox<String> productDropDown = new JComboBox<String>();
	private JComboBox<String> customerDropDown = new JComboBox<String>();

	private JTextField quantityField = new JTextField("", 10);
	private JTextField runningTotalField = new JTextField("");

	private JButton buttonAddProduct;
	private JButton buttonRefreshList;
	private JButton buttonConfirmSale;

	private Vector<LineItem> vet;
	private JTable table;
	private JScrollPane scrollPaneLineItems;

	private Sale sale;

	private double runningTotal;
	private double runningCost;
	private double deductionCost;
	private int amount;
	private Customer customerPicked;
	private TableModel dataModel;

	public SaleGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Product.compileProductNames(productDropDown);
		Customer.customerListComplete(customerDropDown);// Combobox for Customer
		quantityField.setText("0");
		labelTitle = new JLabel("New Sale");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitle.setAlignmentX(CENTER_ALIGNMENT);
		customerLabel = new JLabel(
				"Please choose a customer from the list below");
		customerLabel.setAlignmentX(CENTER_ALIGNMENT);
		productLabel = new JLabel("Please choose products from the list below");
		productLabel.setAlignmentX(CENTER_ALIGNMENT);
		quantityLabel = new JLabel("Please enter an amount");
		quantityLabel.setAlignmentX(CENTER_ALIGNMENT);
		buttonAddProduct = new JButton("Add Product");
		buttonAddProduct.setAlignmentX(CENTER_ALIGNMENT);
		buttonRefreshList = new JButton("Refresh List");
		buttonRefreshList.setAlignmentX(CENTER_ALIGNMENT);
		buttonConfirmSale = new JButton("Confirm Sale");
		buttonConfirmSale.setAlignmentX(CENTER_ALIGNMENT);
		runningTotalLabel.setAlignmentX(CENTER_ALIGNMENT);

		this.add(labelTitle);
		this.add(customerLabel);
		this.add(customerDropDown);
		this.add(productLabel);
		this.add(productDropDown);
		this.add(quantityLabel);
		this.add(quantityField);
		this.add(buttonAddProduct);

		vet = new Vector<LineItem>();
		dataModel = new LineItemTable(vet);
		table = new JTable(dataModel);
		scrollPaneLineItems = new JScrollPane(table);
		this.add(scrollPaneLineItems);
		this.add(runningTotalLabel);
		this.add(runningTotalField);
		this.add(buttonRefreshList);
		this.add(buttonConfirmSale);

		// Add running total
		buttonAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = productDropDown.getSelectedItem().toString();
				String amountString = quantityField.getText();
				if (amountString != null) {
					try {
						amount = Integer.parseInt(amountString);
						if (amount <= 0) {
							error();
						}
					} catch (NumberFormatException e) {
						error();
					}
					// Find product from product name
					Product product = Product.findProductWithName(name);
					if (product != null) {
						addProduct(product);
					}
				}
			}
		});

		buttonRefreshList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeProduct();
			}
		});

		buttonConfirmSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmSale();
			}
		});

	}
	
	public void addProduct(Product product){
		LineItem lineItem = new LineItem(product, amount);
		vet.add(lineItem);
		runningTotal = runningTotal + lineItem.getTotalCost();
		runningTotalField.setText(String.valueOf(runningTotal));
		repopulate();
	}
	
	public void removeProduct(){
		Vector<LineItem> rem = new Vector<LineItem>();
		for (LineItem lineItem : vet) {
			if (lineItem.isRemoved()) {
				rem.add(lineItem);
				runningTotal = runningTotal - lineItem.getTotalCost();
			}
		}
		vet.removeAll(rem);
		runningTotalField.setText(String.valueOf(runningTotal));
		repopulate();
	}
	
	public void confirmSale(){
		// Get customer
		String customerID = RetailSystem.returnIDfromCombobox(customerDropDown.getSelectedItem().toString());
		customerPicked = Customer.retrieveCustomer(customerID);
		//Create Sale
		sale = new Sale();
		// for(LineItem lineItem: vet){
		for (int i = 0; i < vet.size(); i++) {
			sale.addLineItem(vet.get(i));
		}
		// Create invoice //Issue here
		Invoice invoice = new Invoice(sale.getSaleDate(),
				customerPicked, runningTotal, sale);
		RetailSystem.getInstance().getInvoices().add(invoice);
		Invoice.saveInvoice();
		JOptionPane.showMessageDialog(null,
				"Invoice saved and printed", "Print",
				JOptionPane.PLAIN_MESSAGE);
		clear();
	}
	

	public void repopulate() {
		table.revalidate();
		table.repaint();
	}

	public void clear() {
		customerDropDown.removeAllItems();
		Customer.customerListComplete(customerDropDown);

		productDropDown.removeAllItems();
		Product.compileProductNames(productDropDown);

		quantityField.setText("0");
		runningTotalField.setText("");
		vet.clear();
		repopulate();
	}

	private void error() {
		JOptionPane.showMessageDialog(null,
				"The amount has to be a positive integer", "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}