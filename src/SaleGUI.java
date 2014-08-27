import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
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
	
	

	public SaleGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		compileProductNames();
		quantityField.setText("0");
		productLabel = new JLabel("Please choose products from the list below");
		productLabel.setAlignmentX(CENTER_ALIGNMENT);
		quantityLabel = new JLabel("Please enter an amount");
		quantityLabel.setAlignmentX(CENTER_ALIGNMENT);
		buttonAddProduct = new JButton("Add Product");
		buttonAddProduct.setAlignmentX(CENTER_ALIGNMENT);
		buttonRefreshList = new JButton("Refresh List");
		buttonRefreshList.setAlignmentX(CENTER_ALIGNMENT);
		
		//scrollPaneLineItems = new JScrollPane(table);
		this.add(productLabel);
		this.add(productDropDown);
		this.add(quantityLabel);
		this.add(quantityField);
		this.add(buttonAddProduct);
		
		vet = new Vector<LineItem>();
		TableModel dataModel = new LineItemTable(vet); 
		table = new JTable(dataModel);
		//table.setSize(200, 70);
		scrollPaneLineItems = new JScrollPane(table);
		this.add(scrollPaneLineItems);
		this.add(buttonRefreshList);
		
		
		
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
						Sale sale = new Sale();
						LineItem lineItem = new LineItem(product, amount);
						sale.addLineItem(lineItem);
						vet.add(lineItem);
						repopulate();
						
					}
				}
				

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
		this.remove(buttonRefreshList);
		
		this.add(productLabel);
		this.add(productDropDown);
		this.add(quantityLabel);
		this.add(quantityField);
		this.add(buttonAddProduct);
		this.add(scrollPaneLineItems);
		this.add(buttonRefreshList);
		revalidate();
		repaint();
		
	}
	

	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			if(product.isActive()){
				productDropDown.addItem(product.getName());

			}
		}
	}


}
