
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddSupplierGUI extends JFrame{
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	// Instance of supplier in aid of accessing removal method
	// And variable used to hold position in list of requested removal

	public AddSupplierGUI() {
		setTitle("ADD SUPPLIER LIST GUI");
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		textField1= new JTextField();
		textField2= new JTextField();
		textField3= new JTextField();
		JLabel label1 = new JLabel("Supplier ID");
		JLabel label2 = new JLabel("Supplier name");
		JLabel label3 = new JLabel("Supplier contact number");		
		JButton addButton = new JButton("ADD");
			
		// Button pressed, checks if entries are valid and does not already exist
		// then creates supplier and adds to list
		addButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				String supplierID = textField1.getText();
				String name = textField2.getText() ;
				String phoneNumber = textField3.getText();
				if(supplierValidation(supplierID,name,phoneNumber)){
					Supplier supplier = new Supplier(supplierID,name,phoneNumber);
					supplier.addSupplierToList(supplier);
					JOptionPane.showMessageDialog(null, "Supplier Created and added to the list", "Success", JOptionPane.PLAIN_MESSAGE);
					supplier.saveUser();
				}
			}
		});
		// Layout of panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(textField1);
		panel.add(label1);
		panel.add(textField2);
		panel.add(label2);
		panel.add(textField3);
		panel.add(label3);
		panel.add(addButton);
		Container cp = getContentPane();
		cp.add(panel);
		setVisible(true);
	}
	
	// Methods used to test that the input of data is valid and supplier does NOT exist
	public boolean supplierValidation(String supplierID, String name, String phoneNumber){
		boolean correct = true;
		if (supplierID.isEmpty() || name.isEmpty() ||  phoneNumber.isEmpty()){
			correct=false;
			JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else {
			for(Supplier supplier :RetailSystem.getInstance().getSuppliers()){
				if (supplier.getSupplierID().equals(supplierID)){
					correct = false;
					JOptionPane.showMessageDialog(null, "Supplier ID already exists!", "Warning", JOptionPane.INFORMATION_MESSAGE);	
				}			
			}
		}
		return correct;
	}
}
