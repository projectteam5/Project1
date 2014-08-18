import java.awt.Container;
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


public class DeleteSupplierGUI extends JFrame{
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JComboBox<String> supplierDropDownAfterDelete = new JComboBox<String>();
	private JButton buttonMenu;
	private JButton deleteButton;
	private int i;
	// Instance of supplier in aid of accessing removal method
	private Supplier removeSupplier = new Supplier("","","");
	JPanel panel1;
	
	public DeleteSupplierGUI() {
		setTitle("DELETE SUPPLIER LIST GUI");
		setSize(400,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		panel1 = new JPanel();
		compileSupplierNames();
		deleteButton = new JButton("Delete");
		buttonMenu = new JButton("Supplier Menu");
		panel1.add(supplierDropDown);
		panel1.add(deleteButton);
		panel1.add(buttonMenu);
		Container container = getContentPane();
		container.add(panel1);
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel1.setLayout(new GridLayout(0,1));	
		setVisible(true);
		
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				String name = supplierDropDown.getSelectedItem().toString();
				// Getting the supplier from the list and assigning to new object then
				// Using another object to access supplier class method to delete from list
				Supplier supplierToRemove = findAndReturnSupplierFromList(name);			
				supplierToRemove.setActive(false);
				//removeSupplier.removeSupplierFromList(supplierToRemove);
				JOptionPane.showMessageDialog(null, "Supplier is not active anymore", "Success", JOptionPane.PLAIN_MESSAGE);
				removeSupplier.saveUser();
				compileSupplierNamesAfterDelete();
				refresh();
			}
		});
		
		buttonMenu.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				SupplierMenuGUI supplierMenuGUI = new SupplierMenuGUI();
				closeRemoveSupplierGUI();
			}
		});	
	}
	
	public void refresh(){
		panel1.remove(supplierDropDown);
		panel1.remove(deleteButton);
		panel1.remove(buttonMenu);
		panel1.add(supplierDropDownAfterDelete);
		panel1.add(deleteButton);
		panel1.add(buttonMenu);
		panel1.revalidate();
		revalidate();
		repaint();
	}
	
	// Takes the name selected from drop-down menu, finds it on the list
	// And returns the supplier
	private Supplier findAndReturnSupplierFromList(String name) {
		Supplier supplierToDelete = null;
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			if(supplier.isActive()){
				if(name.equalsIgnoreCase(supplier.getName())){
					supplierToDelete = supplier;						
					break;
				}
			}
		}	
		return supplierToDelete;
	}
	
	// Used to fill up the combo box with suppliers from the list
	public void compileSupplierNames(){
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			if(supplier.isActive()){
				supplierDropDown.addItem(supplier.getName());
			}
		}
	}
	
	public void compileSupplierNamesAfterDelete(){
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			if(supplier.isActive()){
				supplierDropDownAfterDelete.addItem(supplier.getName());
			}
		}
	}
	
	public void closeRemoveSupplierGUI(){
		this.setVisible(false);
	}
}