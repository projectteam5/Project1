//GUI done. Method working
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class AddProductGUI extends JFrame{
	private JTextField textFieldProductID;
	private JTextField textFieldName;
	private JTextField textFieldCost;
	private JTextField textFieldMarkup;
	private ArrayList<String> names = new ArrayList<String>();
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private double cost;
	private double markup;
	private Supplier supplierPicked;
	

	public AddProductGUI() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));	
		
		//Add input text fields to add user to the system
		JLabel label1 = new JLabel("Product ID");
		textFieldProductID = new JTextField();
		JLabel label2 = new JLabel("Product Name");
		textFieldName = new JTextField();
		JLabel label3 = new JLabel("Product Cost");
		textFieldCost = new JTextField();
		JLabel label4 = new JLabel("Product Markup");
		textFieldMarkup = new JTextField();
		

		compileSupplierNames();
		JLabel label5 = new JLabel("Product Supplier ID");
		//Submit button
		JButton submitButton = new JButton("Submit");
		
		
		panel.add(label1);
		panel.add(textFieldProductID);
		panel.add(label2);
		panel.add(textFieldName);
		panel.add(label3);
		panel.add(textFieldCost);
		panel.add(label4);
		panel.add(textFieldMarkup);
		panel.add(label5);
		panel.add(supplierDropDown);	
		panel.add(submitButton);
		this.setVisible(true);
		
		
		
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				
			boolean duplicateProductID = false;
			boolean correctInfo = true;
			String productID = textFieldProductID.getText();
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
			
			
			//If product is not already in system, add to system
			if ((!duplicateProductID) && (correctInfo==true)){
				try{
					JOptionPane.showMessageDialog(null, "Adding product");
					Product product = new Product(productID, name, cost, markup, supplierPicked);
					RetailSystem.getInstance().getProducts().add(product);
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Error processing request");
				}
			
		}
			
		}
	});
	}
		
	
	
	
	public void compileSupplierNames(){
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			supplierDropDown.addItem(supplier.getName());
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

}
