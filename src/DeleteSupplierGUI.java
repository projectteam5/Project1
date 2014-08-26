import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.Refreshable;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class DeleteSupplierGUI extends JPanel{
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JComboBox<String> supplierDropDownAfterDelete = new JComboBox<String>();
	private JButton deleteButton;
	private JLabel title;
	private JLabel instruction;
	// Instance of supplier in aid of accessing removal method
	private Supplier removeSupplier = new Supplier("","");
	
	public DeleteSupplierGUI() {
		
		title = new JLabel("Deactivate Supplier");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		instruction = new JLabel("Please choose a supplier from the list below");
		deleteButton = new JButton("Delete");
		compileSupplierNames();
		
		this.add(title);
		this.add(instruction);
		this.add(supplierDropDown);
		this.add(deleteButton);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0,1));	
		this.setVisible(true);
		
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				String name = supplierDropDown.getSelectedItem().toString();
				// Getting the supplier from the list and assigning to new object then
				// Using another object to access supplier class method to delete from list
				Supplier supplierToRemove = findAndReturnSupplierFromList(name);			
				supplierToRemove.setActive(false);
				JOptionPane.showMessageDialog(null, "Supplier is not active anymore", "Success", JOptionPane.PLAIN_MESSAGE);
				removeSupplier.saveSupplier();
				compileSupplierNamesAfterDelete();
				refresh();
			}
		});
	}
	
	public void refresh(){
		this.remove(supplierDropDown);
		this.remove(deleteButton);
		this.add(supplierDropDownAfterDelete);
		this.add(deleteButton);
		this.revalidate();
		revalidate();
		repaint();
	}
	
	// Takes the name selected from drop-down menu, finds it on the list
	// And returns the supplier
	private Supplier findAndReturnSupplierFromList(String name) {
		Supplier supplierToDelete = null;
		for(Supplier supplier: Supplier.getSupplierList()){
				if(name.equalsIgnoreCase(supplier.getName())){
					supplierToDelete = supplier;						
					break;
				}
		}	
		return supplierToDelete;
	}
	
	// Used to fill up the combo box with suppliers from the list
	public void compileSupplierNames(){
		for(Supplier supplier: Supplier.getSupplierList()){
				supplierDropDown.addItem(supplier.getName());
		}
	}
	
	public void compileSupplierNamesAfterDelete(){
		for(Supplier supplier: Supplier.getSupplierList()){
				supplierDropDownAfterDelete.addItem(supplier.getName());
		}
	}
}