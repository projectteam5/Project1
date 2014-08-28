//GUI done. Method working//test commit//
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


public class EditProductGUI extends JPanel{
	private JComboBox<String> productDropDown = new JComboBox<String>();
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private static Product chosenEditProduct;
	private JTextField textFieldName = new JTextField("");
	private JTextField textFieldCost = new JTextField("");
	private JTextField textFieldMarkup = new JTextField("");
	private JButton buttonCommitEditProduct;
	private JButton buttonEditProduct;
	private JPanel panel;
	private JLabel title = new JLabel("Please chose a product from the list below");
	private JLabel productName = new JLabel("Name");
	private JLabel productCost = new JLabel("Cost");
	private JLabel productMarkup = new JLabel("Markup");
	private JLabel supplierName = new JLabel("Supplier");
	private boolean productChosen = false;


	public EditProductGUI() {
		this.setLayout(new GridLayout(0,1));
		compileProductNames();
		compileSupplierNames();

		buttonEditProduct = new JButton("Edit");
		buttonCommitEditProduct = new JButton("Submit Edit");

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
		buttonCommitEditProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				if(productChosen){
					Supplier supplierPicked = null;
					boolean correctInfo = true;
					boolean duplicateProductName = false;
					String productName = textFieldName.getText();
					double cost = 0;
					double markup = 0;
					try{
						cost = Double.parseDouble(textFieldCost.getText());
						markup = Double.parseDouble(textFieldMarkup.getText());
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Please enter in the correct format");
						correctInfo = false;
					}

					String supplierChoice = supplierDropDown.getSelectedItem().toString();
					for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
						if(supplierChoice.contains(supplier.getName())){
							supplierPicked = supplier;
						}
					
					}
					
					if(!productName.equalsIgnoreCase(chosenEditProduct.getName())){
						for(Product product: RetailSystem.getInstance().getProducts()){
							if(product.getName().equalsIgnoreCase(productName)){
								duplicateProductName = true;
							
							}
							
								
							}
						}
					if(duplicateProductName == true){
						JOptionPane.showMessageDialog(null, "Product in system with same name");
					}


					if((correctInfo) && (!duplicateProductName) &&(!textFieldName.equals(null))&& (cost!=0) && (markup!=0)){
						chosenEditProduct.setName(textFieldName.getText());
						chosenEditProduct.setCost(cost);
						chosenEditProduct.setMarkup(markup);
						chosenEditProduct.setSupplier(supplierPicked);
						JOptionPane.showMessageDialog(null, "Product has been edited");
						saveProduct();
						populateFields2();
						
					}else{
						JOptionPane.showMessageDialog(null, "Please fill out all fields");
					}
				}else{
					JOptionPane.showMessageDialog(null, "You cannot edit without chosing a product");

				}
				
			
		}
		});
	
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
	
	public void populateFields2(){
		this.remove(productDropDown);
		this.remove(buttonEditProduct);
		this.remove(productName);
		this.remove(textFieldName);
		this.remove(productCost);
		this.remove(textFieldCost);
		this.remove(productMarkup);
		this.remove(textFieldMarkup);
		this.remove(supplierName);
		this.remove(supplierDropDown);
		this.remove(buttonCommitEditProduct);
		productDropDown = new JComboBox();
		compileProductNames();
		productDropDown.setSelectedIndex(0);
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
		revalidate();
		repaint();
		
	}

	public static Product getChosenEditProduct() {
		return chosenEditProduct;
	}

	public void setChosenEditProduct(Product chosenEditProduct) {
		this.chosenEditProduct = chosenEditProduct;
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
