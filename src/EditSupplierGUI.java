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


public class EditSupplierGUI extends JFrame {
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;

	public EditSupplierGUI() {
		setTitle("EDIT SUPPLIER");
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		textField1= new JTextField();
		textField2= new JTextField();
		textField3= new JTextField();
		JLabel label1 = new JLabel("Supplier ID");
		JLabel label2 = new JLabel("Supplier name");
		JLabel label3 = new JLabel("Supplier contact number");
		
		// Takes in the information and sends as arguments to a method which edits details
		// if supplier exists and returns true to signal edit update.
		// will return false if fields are blank and supplier does not exist
		JButton updateButton = new JButton("Confirm Supplier Details");
		updateButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				String supplierID = textField1.getText();
				String name = textField2.getText() ;
				String phoneNumber = textField3.getText();
				if(validateSupplierUpdate(supplierID,name,phoneNumber)){
					JOptionPane.showMessageDialog(null, "Supplier details have been updated", "Success", JOptionPane.PLAIN_MESSAGE);			
				}
				else{
					JOptionPane.showMessageDialog(null, "Please make sure supplier exists and all fields are filled", "Success", JOptionPane.PLAIN_MESSAGE);			
				}			
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(textField1);
		panel.add(label1);
		panel.add(textField2);
		panel.add(label2);
		panel.add(textField3);
		panel.add(label3);
		panel.add(updateButton);
		Container cp = getContentPane();
		cp.add(panel);
		setVisible(true);		
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
