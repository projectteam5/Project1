import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class EditSupplierGUI extends JPanel {
	private JTextField supplierNameField;
	private JTextField supplierNumberField;
	
	private static Supplier chosenEditSupplier;
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JButton buttonCommitEditProduct;
	private JButton buttonMenu;
	private JLabel title;
	private JLabel instruction; 
	private JLabel supplierName; 
	private JLabel supplierNumber;
	private JButton buttonEditSupplier;
	private boolean productChosen = false;
	private Supplier supplierAccess = null;

	public EditSupplierGUI() {
		
		title= new JLabel("Edit Supplier");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		instruction = new JLabel("Please choose a supplier from the list below");
		supplierNumber = new JLabel("Number");
		supplierName = new JLabel("Name");
		supplierNameField= new JTextField();
		supplierNumberField= new JTextField();
		supplierName = new JLabel("Supplier Name");
		supplierNumber = new JLabel("Supplier Number");
		buttonEditSupplier = new JButton("Confirm supplier to edit");
		buttonCommitEditProduct = new JButton("Save Changes");
		//compileSupplierIDs();
		Supplier.supplierListComplete(supplierDropDown);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0,1));
		this.add(title);
		this.add(instruction);
		this.add(supplierDropDown);
		this.add(buttonEditSupplier);
		this.add(supplierName);
		this.add(supplierNameField);
		this.add(supplierNumber);
		this.add(supplierNumberField);
		this.add(buttonCommitEditProduct);
		setVisible(true);	
		
		buttonEditSupplier.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				String ID = RetailSystem.returnIDfromCombobox(supplierDropDown.getSelectedItem().toString());
				for(Supplier supplier: supplierAccess.getSupplierList()){
					if(supplier.getSupplierID().equals(ID)){
						String tempName= supplier.getName();
						String tempNumber = supplier.getPhoneNumber();
						supplierNameField.setText(tempName);
						supplierNumberField.setText(tempNumber);
					}
				}
			}
		});
		
		buttonCommitEditProduct.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				String ID =  RetailSystem.returnIDfromCombobox(supplierDropDown.getSelectedItem().toString());
				Supplier supplierToEdit=findSupplier(ID);
				String name = supplierNameField.getText();
				String number = supplierNumberField.getText();
				validateAndUpdateSupplier(supplierToEdit,name,number);
				int index = supplierDropDown.getSelectedIndex();
				supplierDropDown.removeAllItems();
				Supplier.supplierListComplete(supplierDropDown);
				supplierDropDown.setSelectedIndex(index);
				supplierAccess.saveSupplier();
			}
		});
	
	}
	public void validateAndUpdateSupplier(Supplier supplierToEdit, String name,String number) {
		if ( name.isEmpty() ||  number.isEmpty()){
			JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else{
			if(RetailSystem.validatePhone(number)){
			supplierToEdit.setName(name);
			supplierToEdit.setPhoneNumber(number);	
			JOptionPane.showMessageDialog(null, "Supplier Updated!", "Success", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public Supplier findSupplier(String ID) {
		Supplier supplierToEdit = null;
		for(Supplier supplier: supplierAccess.getSupplierList()){
			if(ID.equalsIgnoreCase(supplier.getSupplierID())){
				supplierToEdit = supplier;
			}
		}
		return supplierToEdit;
	}
	
	public void compileSupplierIDs(){
		for(Supplier supplier: supplierAccess.getSupplierList()){
			supplierDropDown.addItem(supplier.getSupplierID());
		}
	}
	// Method that updates user details
	public boolean validateSupplierUpdate(String supplierID,String name,String phoneNumber){
		boolean correct=false;
		if (supplierID.isEmpty() || name.isEmpty() ||  phoneNumber.isEmpty()){
			JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else {
			for(Supplier supplier:RetailSystem.getInstance().getSuppliers()){
				if(supplier.getSupplierID().equals(supplierID)||supplier.getName().equals(name)||supplier.getPhoneNumber().equals(phoneNumber)){
					supplier.setSupplierID(supplierID);
					supplier.setName(name);
					supplier.setPhoneNumber(phoneNumber);
					correct=true;
				}
			}
		}
		return correct;
	}
	
}
