import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;


public class SaleGUI extends JPanel {
	private JLabel productLabel;
	private JComboBox<String> productDropDown = new JComboBox<String>();
	private JLabel quantityLabel;
	private JTextField quantityField = new JTextField("", 10);
	private JButton buttonAddProduct;
	private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
	private JLabel productListings = new JLabel("");
	
	private Vector<LineItem> vet;
	private JTable table;
	private JPanel showLineItemsPanel;
	private JScrollPane scrollPaneLineItems;
	private JButton buttonRefreshList;
	private JButton buttonConfirmSale;
	private Sale sale;
	private JLabel runningTotalLabel = new JLabel("Running Total:");
	private JTextField runningTotalField = new JTextField("");
	private double runningTotal;
	private double runningCost;
	private double deductionCost;

	public SaleGUI() {
		this.setLayout(new GridLayout(0,1));
		compileProductNames();
		quantityField.setText("0");
		productLabel = new JLabel("Please choose products from the list below");
		quantityLabel = new JLabel("Please enter an amount");
		buttonAddProduct = new JButton("Add Product");
		buttonRefreshList = new JButton("Refresh List");
		buttonConfirmSale = new JButton("Confirm Sale");
		
		scrollPaneLineItems = new JScrollPane(table);
		this.add(productLabel);
		this.add(productDropDown);
		this.add(quantityLabel);
		this.add(quantityField);
		this.add(buttonAddProduct);
		
		vet = new Vector<LineItem>();
		TableModel dataModel = new LineItemTable(vet); 
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
				int amount = Integer.parseInt(quantityField.getText());
				//Testing print
				System.out.println("Product: "+name+"\nAmount:"+amount);
				//Find product from product name
				for(Product product: RetailSystem.getInstance().getProducts()){
					if(name.equalsIgnoreCase(product.getName())){
						//Add lineItem to array of lineItems
						Product addingProduct = product;
						LineItem lineItem = new LineItem(product, amount);
						vet.add(lineItem);
						runningCost = lineItem.getTotalCost();
						runningTotalCalc();
						repopulate();
						
					}
				}
				

			}
			
		});
		
		buttonRefreshList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(LineItem lineItem: vet){
					if(lineItem.isRemoved()){
						vet.remove(lineItem);
						deductionCost = lineItem.getTotalCost();
						removalCostCalc();
						repopulate();
					}
				}
			}
		});
		
		buttonConfirmSale.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				boolean valid = false;
				sale = new Sale();
				int i = 0;
				for(LineItem lineItem: vet){
					sale.addLineItem(lineItem);
					System.out.println("LineItem: "+sale.getLineItems().get(i).getProduct().getName());
				}
				//Create invoice
				//Invoice invoice = new Invoice("HJKD789", sale.getSaleDate(), Customer customer, )
			}
		});
		
		
	}
	
	public void repopulate(){
		this.remove(productLabel);
		this.remove(productDropDown);
		this.remove(quantityLabel);
		this.remove(quantityField);
		this.remove(buttonAddProduct);
		this.remove(scrollPaneLineItems);
		this.remove(runningTotalLabel);
		this.remove(runningTotalField);
		this.remove(buttonRefreshList);
		this.remove(buttonConfirmSale);
		
		this.add(productLabel);
		this.add(productDropDown);
		this.add(quantityLabel);
		this.add(quantityField);
		this.add(buttonAddProduct);
		this.add(scrollPaneLineItems);
		this.add(runningTotalLabel);
		this.add(runningTotalField);
		this.add(buttonRefreshList);
		this.add(buttonConfirmSale);
		revalidate();
		repaint();
		
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


}
