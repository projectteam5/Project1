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


public class SupplierDetailsGUI extends JPanel{
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JLabel title;
	private JLabel instruction;
	private JLabel showLabel,showLabel1,showLabel2;
	private JButton button;
	private static Supplier getSupplier=null;

	public SupplierDetailsGUI() {
		title = new JLabel("Supplier Details");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		instruction = new JLabel("Choose a supplier from the drop down menu");
		showLabel = new JLabel("");
		showLabel1 = new JLabel("");
		showLabel2 = new JLabel("");
		button = new JButton("Show supplier details");
		Supplier.supplierListComplete(supplierDropDown);
		//compileSupplierIDs();
		
		// Takes a supplier iD from drop down menu. Loops through the supplier list to obtain specific object
		// and then gets their details. The array list is obtained from method in supplier class.
		button.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				String ID = RetailSystem.returnIDfromCombobox(supplierDropDown.getSelectedItem().toString());
				for(Supplier supplier: getSupplier.getSupplierList()){
					if(supplier.getSupplierID().equals(ID)){
						String tempName= supplier.getName();
						String tempNumber = supplier.getPhoneNumber();
						showLabel.setText("Supplier ID: "+ID);
						showLabel1.setText("Supplier Name: "+tempName);
						showLabel2.setText("Supplier Contact No: "+tempNumber);
					}
				}
			}
		});
		
		
		this.setLayout(new GridLayout(0,1));
		this.add(title);
		this.add(instruction);
		this.add(supplierDropDown);
		this.add(button);	
		this.add(showLabel);
		this.add(showLabel1);
		this.add(showLabel2);
		setVisible(true);
	}
	
	public void compileSupplierIDs(){
		for(Supplier supplier: Supplier.getSupplierList()){
			supplierDropDown.addItem(supplier.getSupplierID());
		}
	}
}
