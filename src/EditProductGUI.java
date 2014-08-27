//GUI done. Method working
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
	private JButton buttonMenu;
	private JPanel panel;
	private JLabel title = new JLabel("Please chose a product from the list below");
	private JLabel productName = new JLabel("Name");
	private JLabel productCost = new JLabel("Cost");
	private JLabel productMarkup = new JLabel("Markup");
	private JLabel supplierName = new JLabel("Supplier");
	private boolean productChosen = false;


	public EditProductGUI() {
/*		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		this.setTitle("Edit Product");
		panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));*/
		this.setLayout(new GridLayout(0,1));
		compileProductNames();
		compileSupplierNames();

		JButton buttonEditProduct = new JButton("Edit");
		buttonCommitEditProduct = new JButton("Submit Edit");
		buttonMenu = new JButton("Menu");

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
		this.add(buttonMenu);
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
					String name = null;
					double cost = 0;
					double markup = 0;
					try{
						name = textFieldName.getText();
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
					if(correctInfo){
						chosenEditProduct.setName(name);
						chosenEditProduct.setCost(cost);
						chosenEditProduct.setMarkup(markup);
						chosenEditProduct.setSupplier(supplierPicked);
						JOptionPane.showMessageDialog(null, "Product has been edited");
						saveProduct();
						
					}
				}else{
					JOptionPane.showMessageDialog(null, "You cannot edit without chosing a product");

				}
				
			
		}
		});
		buttonMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ago0){
				ProductMenuGUI productMenuGUI = new ProductMenuGUI();
				closeEditProductGUI();	
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
		this.remove(buttonMenu);
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
		this.add(buttonMenu);
		revalidate();
		repaint();
	}

	public static Product getChosenEditProduct() {
		return chosenEditProduct;
	}

	public void setChosenEditProduct(Product chosenEditProduct) {
		this.chosenEditProduct = chosenEditProduct;
	}

	public void closeEditProductGUI(){
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
