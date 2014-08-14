//GUI done, needs methods to actually add product/check if product already exists in the system
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	

	public AddProductGUI() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));	
		
		//Add input text fields to add user to the system
		textFieldProductID = new JTextField();
		JLabel label1 = new JLabel("Product ID");
		textFieldName = new JTextField();
		JLabel label2 = new JLabel("Product Name");
		textFieldCost = new JTextField();
		JLabel label3 = new JLabel("Product Cost");
		textFieldMarkup = new JTextField();
		JLabel label4 = new JLabel("Product Markup");

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
				//when the user presses the button. checks:
				//supplierDropDown.getSelectedObjects();
				/*String productID = textFieldProductID.getText();
				String name = textFieldName.getText();
				//Supplier supplier = textFieldSupplier.getText();
				boolean fail = false;
				try{
					double cost = Double.parseDouble(textFieldCost.getText());
					double markup = Double.parseDouble(textFieldMarkup.getText());
					
				}catch(NumberFormatException formatError){
					JOptionPane.showMessageDialog(null, "Invalid Entry", "Error", JOptionPane.WARNING_MESSAGE);		
					fail = true;
				}
				
				if(fail!=true){
					Product product = new Product(productID, name, cost, markup, supplier);
				}*/
			}
		});
		
		
	}
	
	
	public void compileSupplierNames(){
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			supplierDropDown.addItem(supplier.getName()
			+ " | " + supplier.getSupplierID());
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
