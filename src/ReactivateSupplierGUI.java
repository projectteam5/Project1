import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class ReactivateSupplierGUI extends JPanel{
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JComboBox<String> supplierDropDownAfterReactivation = new JComboBox<String>();
	private JButton reactivateButton;
	private JLabel title;
	private JLabel instruction;
	private Supplier supplierToReactivate = null;

	public ReactivateSupplierGUI() {
		title = new JLabel("Reactivate a supplier");
		reactivateButton = new JButton("Reactivate");
		instruction = new JLabel("Please choose a supplier to reactivate from drop down menu");
		compileSupplierNames();
		
		this.add(title);
		this.add(instruction);
		this.add(supplierDropDown);
		this.add(reactivateButton);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0,1));	
		this.setVisible(true);
		
		reactivateButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				String name = supplierDropDown.getSelectedItem().toString();
				// Getting the supplier from the list and assigning to new object then
				// Using another object to access supplier class method to delete from list
				Supplier supplierToReactivate = findAndReturnSupplierFromList(name);			
				supplierToReactivate.setActive(true);
				JOptionPane.showMessageDialog(null, "Supplier is now active", "Success", JOptionPane.PLAIN_MESSAGE);
				supplierToReactivate.saveSupplier();
				compileSupplierNamesAfterReactivation();
				refresh();
			}
		});	
	}
	
	// Takes the name selected from drop-down menu, finds it on the list
		// And returns the supplier
		private Supplier findAndReturnSupplierFromList(String name) {
			Supplier supplierToReactivate = null;
			for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
					if(name.equalsIgnoreCase(supplier.getName())){
						supplierToReactivate = supplier;						
						break;
					}
			}	
			return supplierToReactivate;
		}
	
	// Used to fill up the combo box with suppliers from the list
		public void compileSupplierNames(){
			for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
				if(supplier.isActive()==false){
					supplierDropDown.addItem(supplier.getName());
				}
			}
		}
		
	public void compileSupplierNamesAfterReactivation(){
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			if(supplier.isActive()==false){
				supplierDropDownAfterReactivation.addItem(supplier.getName());
			}
		}
	}
	
	public void refresh(){
		this.remove(supplierDropDown);
		this.remove(reactivateButton);
		this.add(supplierDropDownAfterReactivation);
		this.add(reactivateButton);
		this.revalidate();
		revalidate();
		repaint();
	}

}
