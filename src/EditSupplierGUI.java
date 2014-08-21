import java.awt.Container;
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


public class EditSupplierGUI extends JFrame {
	private JTextField supplierNameField;
	private JTextField supplierNumberField;
	
	private static Supplier chosenEditSupplier;
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JButton buttonCommitEditProduct;
	private JButton buttonMenu;
	private JPanel panel;
	private JLabel title = new JLabel("Please chose a supplier from the list below");
	private JLabel supplierName = new JLabel("Name");
	private JLabel supplierNumber = new JLabel("Number");
	private JButton menuButton;
	private boolean productChosen = false;
	private Supplier supplierAccess = null;

	public EditSupplierGUI() {
		setTitle("EDIT SUPPLIER");
		setSize(RetailSystem.getInstance().getWidth(), RetailSystem.getInstance().getHeight());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		supplierNameField= new JTextField();
		supplierNumberField= new JTextField();
		supplierName = new JLabel("Supplier Name");
		supplierNumber = new JLabel("Supplier Number");
		JButton buttonEditSupplier = new JButton("Confirm supplier to edit");
		buttonMenu = new JButton("Supplier Menu");
		buttonCommitEditProduct = new JButton("Save Changes");
		compileSupplierIDs();
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));
		panel.add(title);
		panel.add(supplierDropDown);
		panel.add(buttonEditSupplier);
		panel.add(supplierName);
		panel.add(supplierNameField);
		panel.add(supplierNumber);
		panel.add(supplierNumberField);
		panel.add(buttonCommitEditProduct);
		panel.add(buttonMenu);
		Container cp = getContentPane();
		cp.add(panel);
		setVisible(true);	
		
		buttonEditSupplier.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				String ID = supplierDropDown.getSelectedItem().toString();
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
				String ID = supplierDropDown.getSelectedItem().toString();
				Supplier supplierToEdit=findSupplier(ID);
				String name = supplierNameField.getText();
				String number = supplierNumberField.getText();
				validateAndUpdateSupplier(supplierToEdit,name,number);
				supplierAccess.saveUser();
			}
		});
		
		buttonMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ago0){
				SupplierMenuGUI supplierMenuGUI = new SupplierMenuGUI();
				closeEditSupplierGUI();	
			}
		});
	
	}
	public void validateAndUpdateSupplier(Supplier supplierToEdit, String name,String number) {
		if ( name.isEmpty() ||  number.isEmpty()){
			JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else{
			supplierToEdit.setName(name);
			supplierToEdit.setPhoneNumber(number);	
			JOptionPane.showMessageDialog(null, "Supplier Updated!", "Success", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public Supplier findSupplier(String ID) {
		Supplier supplierToEdit = null;
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			if(ID.equalsIgnoreCase(supplier.getSupplierID())){
				supplierToEdit = supplier;
			}
		}
		return supplierToEdit;
	}
	
	public void compileSupplierIDs(){
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
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
	
	public void closeEditSupplierGUI(){
		this.setVisible(false);
	}
}
