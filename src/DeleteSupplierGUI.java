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


public class DeleteSupplierGUI extends JFrame{
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	// Instance of supplier in aid of accessing removal method
	// And variable used to hold position in list of requested removal
	private Supplier removeSupplier = new Supplier("","","");
	private int k;
	private int l;

	public DeleteSupplierGUI() {
		setTitle("MODIFY SUPPLIER LIST GUI");
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		textField1= new JTextField();
		textField2= new JTextField();
		textField3= new JTextField();
		JLabel label1 = new JLabel("Supplier ID");
		JLabel label2 = new JLabel("Supplier name");
		JLabel label3 = new JLabel("Supplier contact number");		
		JButton delButton = new JButton("DELETE");
		
		// Button pressed, checks if entries are valid and does already exist
		// then deletes supplier and removes from list
		delButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				String supplierID = textField1.getText();
				String name = textField2.getText() ;
				String phoneNumber = textField3.getText();
				if(deleteSupplierValidation(supplierID,name,phoneNumber)){
					removeSupplier.removeSupplierFromList(RetailSystem.getInstance().getSuppliers().get(k));
					removeSupplier.saveUser();
				}
				else{
					JOptionPane.showMessageDialog(null, "Please make sure Supplier ID is correct and all fields are filled in", "Warning", JOptionPane.INFORMATION_MESSAGE);	
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
		panel.add(delButton);
		Container cp = getContentPane();
		cp.add(panel);
		setVisible(true);
	}
	
	// Method used to test that entry is valid and the supplier DOES exist
	public boolean deleteSupplierValidation(String supplierID, String name, String phoneNumber){
		boolean correct = false;
		// Used only for an arbitrary condition for else if
		int i=0; 
		
		if (supplierID.isEmpty() || name.isEmpty() ||  phoneNumber.isEmpty()){
			i=1;
			JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else if(i==0){
			for(Supplier supplier :RetailSystem.getInstance().getSuppliers()){
				if (supplier.getSupplierID().equals(supplierID)){			
					JOptionPane.showMessageDialog(null, "Supplier ID exists, processing removal!", "Warning", JOptionPane.INFORMATION_MESSAGE);	
					correct = true;
					k=RetailSystem.getInstance().getSuppliers().indexOf(supplier);
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "Supplier ID is not valid!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		return correct;
	}
}
