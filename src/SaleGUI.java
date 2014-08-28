import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	
	//private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
	//private JLabel productListings = new JLabel("");
	
	private Vector<LineItem> vet;
	private JTable table;
	//private JPanel showLineItemsPanel;
	private JScrollPane scrollPaneLineItems;
	
	private Sale sale;
	
	
	private double runningTotal;
	private double runningCost;
	private double deductionCost;
	private Customer customerPicked;
	private TableModel dataModel;

	public SaleGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		compileProductNames();
		//Customer can have same name :)
		//compileCustomerNames(); //Check to make sure if customers can have same names. If so use IDs to populate instead
		Customer.customerListComplete(customerDropDown);//Combobox for Customer
		quantityField.setText("0");
		labelTitle = new JLabel("New Sale");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitle.setAlignmentX(CENTER_ALIGNMENT);
		customerLabel = new JLabel("Please choose a customer from the list below");
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
		
		
		//Add running total
		buttonAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = productDropDown.getSelectedItem().toString();
				
				int amount = 0;
				try {
					amount = Integer.parseInt(quantityField.getText());
					if(amount<=0){
						 error();
					}
				} catch (NumberFormatException e) {
					 error();
				}
				//Testing print
				//System.out.println("Product: "+name+"\nAmount:"+amount);
				//Find product from product name
				Product product = findProductWithName(name);
			//	for(Product product: RetailSystem.getInstance().getProducts()){
					//if(name.equalsIgnoreCase(product.getName())){
				if(product != null && amount!=0){
						//Add lineItem to array of lineItems
					//Product addingProduct = product;
					LineItem lineItem = new LineItem(product, amount);
					vet.add(lineItem);
					runningCost = lineItem.getTotalCost();
					runningTotalCalc();
					repopulate();
						
				}
				//}
			}
			
		});
		
		buttonRefreshList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<LineItem> rem = new Vector<LineItem>();
				for(LineItem lineItem: vet){
					if(lineItem.isRemoved()){
						rem.add(lineItem);
						deductionCost = lineItem.getTotalCost();
						removalCostCalc();
					}
				}
				vet.removeAll(rem);
				repopulate();
			}
		});

		
		buttonConfirmSale.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				boolean valid = false;
				sale = new Sale();
				//for(LineItem lineItem: vet){
				for(int i=0; i<vet.size(); i++){
					sale.addLineItem(vet.get(i));
					System.out.println("LineItem: "+sale.getLineItems().get(i).getProduct().getName());
				}
				
				//Get customer
				String selectedCustomerString = customerDropDown.getSelectedItem()
						.toString();
				String[] selectedCustomerArray = selectedCustomerString.split(";");
				String selectedCustomerIDString = selectedCustomerArray[0];
				String[] selectedCustomerIDArray = selectedCustomerIDString.split(":");
				String customerID = selectedCustomerIDArray[1].trim();
				//String customerChoice = customerDropDown.getSelectedItem().toString();
				for(Customer customer: RetailSystem.getInstance().getCustomers()){
					//if(customerChoice.contains(customer.getName()))
					if(customer.getCustomerID().equals(customerID)){
						customerPicked = customer;
					}
				}
				//Create invoice //Issue here
				Invoice invoice = new Invoice(sale.getSaleDate(), customerPicked, runningTotal, sale);
				RetailSystem.getInstance().getInvoices().add(invoice);
				Invoice.saveInvoice();
				JOptionPane.showMessageDialog(null,
						"Invoice saved and printed", "Print",
						JOptionPane.PLAIN_MESSAGE);
				clear();
				//System.out.println("Invoice ID: "+invoice.getInvoiceID()+"\nSale Date: "+invoice.getSale().getSaleDate()+"\nCustomer: "+invoice.getCustomer().getName()+"\nTotal: "+invoice.getTotalInvoice());
			}
		});
		
		
	}
	
	public void repopulate(){
		table.revalidate();
		table.repaint();		
	}
	
	public void runningTotalCalc(){
		runningTotal = runningTotal + runningCost;
		runningTotalField.setText(String.valueOf(runningTotal));
		
	}
	
	public void removalCostCalc(){
		runningTotal = runningTotal - deductionCost;
		runningTotalField.setText(String.valueOf(runningTotal));
	}

	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			if(product.isActive()){
				productDropDown.addItem(product.getName());

			}
		}
	}
	public static Product findProductWithName(String name) {
		Product product = null;
		for (Product product_1 : RetailSystem.getInstance().getProducts()) {
			if (product_1.getName().equals(name)) {
				product = product_1;
				break; // I need to exit the for
			}
		}
		return product;
	}
	
	public void clear(){
		customerDropDown.removeAllItems();
		Customer.customerListComplete(customerDropDown);
		
		productDropDown.removeAllItems();
		compileProductNames();
		
		quantityField.setText("0");
		runningTotalField.setText("");
		vet.clear();
		repopulate();
		
	}
	private void error(){
		JOptionPane.showMessageDialog(null,
				"The amount has to be a positive integer", "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	/*public void compileCustomerNames(){
		for(Customer customer: RetailSystem.getInstance().getCustomers()){
			if(customer.isActive()){
				customerDropDown.addItem(customer.getName());
			}
		}
	}*/


}
