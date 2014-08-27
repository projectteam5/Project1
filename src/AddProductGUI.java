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


public class AddProductGUI extends JPanel{
	private JTextField textFieldProductID;
	private JTextField textFieldName;
	private JTextField textFieldCost;
	private JTextField textFieldMarkup;
	private ArrayList<String> names = new ArrayList<String>();
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private double cost;
	private double markup;
	private Supplier supplierPicked;
	//private JButton buttonMenu;
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	

	public AddProductGUI() {
		
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setSize(400, 400);
		//this.setTitle("Add Product");
		//JPanel panel = new JPanel();
		//Container container = getContentPane();
		//container.add(panel);
		//panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0,1));	
		
		label1 = new JLabel("Product ID");
		textFieldProductID = new JTextField();
		label2 = new JLabel("Product Name");
		textFieldName = new JTextField();
		label3 = new JLabel("Product Cost");
		textFieldCost = new JTextField();
		label4 = new JLabel("Product Markup");
		textFieldMarkup = new JTextField();
		

		compileSupplierNames();
		JLabel label5 = new JLabel("Product Supplier ID");
		JButton submitButton = new JButton("Submit");
		//buttonMenu = new JButton("Menu");
		
		
		this.add(label1);
		this.add(textFieldProductID);
		this.add(label2);
		this.add(textFieldName);
		this.add(label3);
		this.add(textFieldCost);
		this.add(label4);
		this.add(textFieldMarkup);
		this.add(label5);
		this.add(supplierDropDown);	
		this.add(submitButton);
		//this.add(buttonMenu);
		this.setVisible(true);
		
		
		
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				
			boolean duplicateProductID = false;
			boolean duplicateProductName = false;
			boolean correctInfo = true;
			String productID = textFieldProductID.getText();
			String productName = textFieldName.getText();
			String name = textFieldName.getText();
			try{
			cost = Double.parseDouble(textFieldCost.getText());
			markup = Double.parseDouble(textFieldMarkup.getText());
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Please enter in correct format");
				correctInfo = false;
			}
			
			
			//Changes supplier choice to supplier object
			String supplierChoice = supplierDropDown.getSelectedItem().toString();
			for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
				if(supplierChoice.contains(supplier.getName())){
					supplierPicked = supplier;
				}
			}
			
			//check to see if product ID is already in system
			for(Product product: RetailSystem.getInstance().getProducts()){
				if(product.getProductID().equalsIgnoreCase(productID)){
					duplicateProductID = true;
				
				}
			}
			if(duplicateProductID == true){
				JOptionPane.showMessageDialog(null, "Product in system with same ID");

			}
			
			//Check to see if product NAME is already in system
			for(Product product: RetailSystem.getInstance().getProducts()){
				if(product.getProductID().equalsIgnoreCase(productName)){
					duplicateProductName = true;
				
				}
			}
			if(duplicateProductName == true){
				JOptionPane.showMessageDialog(null, "Product in system with same name");

			}
			
			
			//If product is not already in system, add to system
			if ((!duplicateProductID) && (correctInfo==true) && (!duplicateProductName)){
				try{
					JOptionPane.showMessageDialog(null, "Adding product");
					Product product = new Product(productID, name, cost, markup, supplierPicked);
					RetailSystem.getInstance().getProducts().add(product);
					saveProduct();
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Error processing request");
				}
			
		}
			
		}
	});
		
/*		buttonMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ago0){
				ProductMenuGUI productMenuGUI = new ProductMenuGUI();
				closeAddProductGUI();	
			}
		});*/
	}
		
	
	
	
	public void compileSupplierNames(){
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			if(supplier.isActive()){
				supplierDropDown.addItem(supplier.getName());

			}
		}
	}
	
	public JTextField getTextFieldProductID() {
		return textFieldProductID;
	}
	public void setTextFieldProductID(JTextField textFieldProductID) {
		this.textFieldProductID = textFieldProductID;
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
	
	public void closeAddProductGUI(){
		this.setVisible(false);
	}
	
	public static void saveProduct(){
	       try {
	           FileWriter productFile;
	           productFile = new FileWriter("products.txt");
	           DataBase.writeProducts(RetailSystem.getInstance().getProducts(), productFile);
	           productFile.close();
	       } catch (Exception exception) {
	           exception.printStackTrace();
	       }
	   }

}
