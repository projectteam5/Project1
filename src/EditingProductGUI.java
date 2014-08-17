//This is to be deleted!!!!
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

//GUI done. Method working
public class EditingProductGUI extends JFrame{
	private JTextField textFieldProductID;
	private JTextField textFieldName;
	private JTextField textFieldCost;
	private JTextField textFieldMarkup;
	private ArrayList<String> names = new ArrayList<String>();
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private Product product = EditProductGUI.getChosenEditProduct();

	public EditingProductGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));
		compileSupplierNames();
		
		JLabel label1 = new JLabel("Product ID");
		textFieldProductID = new JTextField(product.getProductID());
		JLabel label2 = new JLabel("Product Name");
		textFieldName = new JTextField(product.getName());
		JLabel label3 = new JLabel("Product Cost");
		textFieldCost = new JTextField(String.valueOf(product.getCost()));
		JLabel label4 = new JLabel("Product Markup");
		textFieldMarkup = new JTextField(String.valueOf(product.getMarkup()));
		JLabel label5 = new JLabel("Supplier");
		
		JButton buttonSubmitEditProduct = new JButton("Submit Edit");
		
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
		panel.add(buttonSubmitEditProduct);
		
		this.setVisible(true);
		
		buttonSubmitEditProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				Supplier supplierPicked = null;
				boolean correctInfo = true;
				String productID = null;
				String name = null;
				double cost = 0;
				double markup = 0;
				try{
					productID = textFieldProductID.getText();
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
					product.setProductID(productID);
					product.setName(name);
					product.setCost(cost);
					product.setMarkup(markup);
					product.setSupplier(supplierPicked);
					JOptionPane.showMessageDialog(null, "Product has been edited");
				}
				
			}
		});
			
	}
	
	public void compileSupplierNames(){
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			supplierDropDown.addItem(supplier.getName());
		}
	}

}
