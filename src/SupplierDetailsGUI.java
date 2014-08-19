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


public class SupplierDetailsGUI extends JFrame{
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JLabel label;
	private JLabel showLabel,showLabel1,showLabel2;
	private JButton button;
	private JButton menuButton;
	private static Supplier getSupplier=null;

	public SupplierDetailsGUI() {
		setTitle("Supplier Details");
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		label = new JLabel("Choose a supplier from the drop down menu");
		showLabel = new JLabel("");
		showLabel1 = new JLabel("");
		showLabel2 = new JLabel("");
		button = new JButton("Show supplier details");
		menuButton = new JButton("Supplier Menu");
		compileSupplierIDs();
		
		// Takes a supplier iD from drop down menu. Loops through the supplier list to obtain specific object
		// and then gets their details. The array list is obtained from method in supplier class.
		button.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				String ID = supplierDropDown.getSelectedItem().toString();
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
		
		menuButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				SupplierMenuGUI supplierMenuGUI = new SupplierMenuGUI();
				closeSupplierMenuGUI();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		panel.add(supplierDropDown);
		panel.add(button);	
		panel.add(showLabel);
		panel.add(showLabel1);
		panel.add(showLabel2);
		panel.add(menuButton);
		Container cp = getContentPane();
		cp.add(panel);
		setVisible(true);
	}
	
	public void compileSupplierIDs(){
		for(Supplier supplier: Supplier.getSupplierList()){
			supplierDropDown.addItem(supplier.getSupplierID());
		}
	}
	
	public void closeSupplierMenuGUI(){
		setVisible(false);
	}
}
