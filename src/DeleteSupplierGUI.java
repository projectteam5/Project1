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


public class DeleteSupplierGUI extends JFrame{
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JButton buttonMenu;
	private JButton deleteButton;
	// Instance of supplier in aid of accessing removal method
	private Supplier removeSupplier = new Supplier("","","");
	
	public DeleteSupplierGUI() {
		setTitle("DELETE SUPPLIER LIST GUI");
		setSize(400,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel panel1 = new JPanel();
		compileProductNames();
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
				removeSupplier.removeSupplierFromList(supplierToRemove);
				JOptionPane.showMessageDialog(null, "Supplier deleted from the list", "Success", JOptionPane.PLAIN_MESSAGE);
				removeSupplier.saveUser();
			}
		});
		
		buttonMenu.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				SupplierMenuGUI supplierMenuGUI = new SupplierMenuGUI();
				closeRemoveProductGUI();
			}
		});
		
	}
	
	// Takes the name selected from drop-down menu, finds it on the list
	// And returns the supplier
	private Supplier findAndReturnSupplierFromList(String name) {
		Supplier supplierToDelete = null;
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			if(name.equalsIgnoreCase(supplier.getName())){
				supplierToDelete = supplier;						
				break;
			}
		}	
		return supplierToDelete;
	}
	
	// Used to fill up the combo box with suppliers from the list
	public void compileProductNames(){
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			supplierDropDown.addItem(supplier.getName());
		}
	}
	
	public void closeRemoveProductGUI(){
		this.setVisible(false);
	}
}
