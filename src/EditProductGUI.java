//GUI done. Method working//test commit//
import java.awt.Container;
import java.awt.Font;
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


public class EditProductGUI extends JPanel{
	private JComboBox<String> productDropDown = new JComboBox<String>();
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private static Product chosenEditProduct;
	private JTextField textFieldName = new JTextField("");
	private JTextField textFieldCost = new JTextField("");
	private JTextField textFieldMarkup = new JTextField("");
	private JButton buttonCommitEditProduct;
	private JButton buttonEditProduct;
	private JLabel title = new JLabel("Please chose a product from the list below");
	private JLabel productName = new JLabel("Name");
	private JLabel productCost = new JLabel("Cost");
	private JLabel productMarkup = new JLabel("Markup");
	private JLabel supplierName = new JLabel("Supplier");
	private JLabel mainTitle;
	private boolean productChosen = false;
	private boolean empty = true;
	//
	Supplier supplierPicked = null;
	boolean correctInfo;
	boolean duplicateProductName = false;
	double cost = 0;
	double markup = 0;


	public EditProductGUI() {
		this.setLayout(new GridLayout(0,1));
		
		compileProductNames();
		compileSupplierNames();

		mainTitle = new JLabel("Edit Product");
		mainTitle.setFont(new Font("Arial", Font.BOLD, 20));
		buttonEditProduct = new JButton("Edit");
		buttonCommitEditProduct = new JButton("Save changes");

		this.add(mainTitle);
		this.add(title);
		this.add(productDropDown);
		this.add(buttonEditProduct);
		this.add(productName);
		this.add(textFieldName);
		this.add(productCost);
		this.add(textFieldCost);
		this.add(productMarkup);
		this.add(textFieldMarkup);
		this.add(supplierName);
		this.add(supplierDropDown);
		this.add(buttonCommitEditProduct);
		this.setVisible(true);
		
		buttonEditProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				String name = productDropDown.getSelectedItem().toString();
				boolean found = false;
				for(Product product: RetailSystem.getInstance().getProducts()){
					if(name.equalsIgnoreCase(product.getName())){
						//If found in the list of products, fill in form with details
						found = true;
						productChosen = true;
						chosenEditProduct = product;
						populateFields();
						break;
					}
				}
				if(!found){
					JOptionPane.showMessageDialog(null, "No Product With This ID in System!");
				}
			}
			
		});
		
		//Checks details and submits edited product to system
		//Check all fields are filled out and filled out correctly
		
		buttonCommitEditProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				correctInfo = true;
				if(productChosen){
					//Makes sure fields are not empty
					nullCheck();
					if(!empty){
						//Try to parse doubles
						parseDoubles();
						if(correctInfo){
							//Find the supplier
							//Check product name duplication
							findSupplier();
							checkProduct();
							editProduct();
						}
					}
				}
			}
		});
						

	}
	
	public void nullCheck(){
		if(textFieldName.getText().equals("") || textFieldCost.getText().equals("") || textFieldMarkup.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please fill out all fields");
			empty = true;
		}else{
			empty = false;
		}
	}
	
	//Try to parse doubles
	public void parseDoubles(){
		try{
			cost = Double.parseDouble(textFieldCost.getText());
			markup = Double.parseDouble(textFieldMarkup.getText());
			if(cost<=0 || markup <0){
				JOptionPane.showMessageDialog(null,
						"Please insert positive values");
				correctInfo = false;
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please enter numbers in the correct format");
			correctInfo = false;
		}
	}
	
	//Find supplier
	public void findSupplier(){
		String supplierChoice = supplierDropDown.getSelectedItem().toString();
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			if(supplierChoice.contains(supplier.getName())){
				supplierPicked = supplier;
			}
		}
	}
	
	//Check product name duplication
	public void checkProduct(){
		String productNameChoice = textFieldName.getText();
		if(!productNameChoice.equalsIgnoreCase(chosenEditProduct.getName())){
			for(Product product: RetailSystem.getInstance().getProducts()){
				if(product.getName().equalsIgnoreCase(productNameChoice)){
					duplicateProductName = true;
				
				}								
			}
		}
		if(duplicateProductName == true){
			JOptionPane.showMessageDialog(null, "Product in system with same name");
		}
	}
	
	//Edit the product
	public void editProduct(){
		if((correctInfo) && (!duplicateProductName)){
			chosenEditProduct.setName(textFieldName.getText());
			chosenEditProduct.setCost(cost);
			chosenEditProduct.setMarkup(markup);
			chosenEditProduct.setSupplier(supplierPicked);
			int index = productDropDown.getSelectedIndex();
			productDropDown.removeAllItems();
			compileProductNames();
			productDropDown.setSelectedIndex(index);
			JOptionPane.showMessageDialog(null, "Product has been edited");
			Product.saveProduct();
		}else{
			JOptionPane.showMessageDialog(null, "error");
		}
	}
	
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			if(product.isActive()){
				productDropDown.addItem(product.getName());

			}
		}
	}
	
	public void compileSupplierNames(){
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			if(supplier.isActive()){
				supplierDropDown.addItem(supplier.getName());
			}
		}
	}
	
	
	public void populateFields(){
		this.remove(productName);
		this.remove(textFieldName);
		this.remove(productCost);
		this.remove(textFieldCost);
		this.remove(productMarkup);
		this.remove(textFieldMarkup);
		this.remove(supplierName);
		this.remove(supplierDropDown);
		this.remove(buttonCommitEditProduct);
		supplierDropDown.setSelectedItem(chosenEditProduct.getSupplier().getName());
		textFieldName = new JTextField(chosenEditProduct.getName());
		textFieldCost = new JTextField(String.valueOf(chosenEditProduct.getCost()));
		textFieldMarkup = new JTextField(String.valueOf(chosenEditProduct.getMarkup()));
		this.add(productName);
		this.add(textFieldName);
		this.add(productCost);
		this.add(textFieldCost);
		this.add(productMarkup);
		this.add(textFieldMarkup);
		this.add(supplierName);
		this.add(supplierDropDown);
		this.add(buttonCommitEditProduct);
		revalidate();
		repaint();
	}
	
	

	public static Product getChosenEditProduct() {
		return chosenEditProduct;
	}

	public void setChosenEditProduct(Product chosenEditProduct) {
		this.chosenEditProduct = chosenEditProduct;
	}


}
