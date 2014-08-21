import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SupplierMenuGUI extends JFrame{

	public SupplierMenuGUI() {
		setTitle("SUPPLIER GUI");
		setSize(RetailSystem.getInstance().getWidthMain(), RetailSystem.getInstance().getHeightMain());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JButton editButton = new JButton("Edit Supplier");
		JButton viewListButton = new JButton("List Suppliers");
		JButton deleteButton = new JButton("Delete Supplier");
		JButton addButton = new JButton("Add Supplier");
		JButton singleSupplierDetails = new JButton("Supplier details");
		JButton buttonMainMenu = new JButton("Main Menu");
		
		// Launches the edit supplier frame
		editButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				EditSupplierGUI editSupplier =new EditSupplierGUI();
				closeSupplierMenuGUI();
			}
		});
		
		addButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				AddSupplierGUI addSupplierGUI = new AddSupplierGUI();
				closeSupplierMenuGUI();
			}
		});
		
		
		// Launches the add/delete supplier from list frame
		deleteButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				DeleteSupplierGUI deleteSupplierGUI = new DeleteSupplierGUI();
				closeSupplierMenuGUI();
			}
		});
		
		// Launches the list suppliers frame
		viewListButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				ListSuppliersGUI listSupplierGUI = new ListSuppliersGUI();	
				closeSupplierMenuGUI();
			}
		});
		
		// Launches the the supplier of a particular product frame
		singleSupplierDetails.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				SupplierDetailsGUI supplierDetailsGUI = new SupplierDetailsGUI();
				closeSupplierMenuGUI();
			}
		});
		
		// Launches the main menu again
		buttonMainMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				MenuGUI menuGUI = new MenuGUI();
				closeSupplierMenuGUI();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(addButton);		
		panel.add(deleteButton);
		panel.add(editButton);
		panel.add(singleSupplierDetails);
		panel.add(viewListButton);
		panel.add(buttonMainMenu);
		Container cp = getContentPane();
		cp.add(panel);
		setVisible(true);
	}
	
	public void closeSupplierMenuGUI(){
		this.setVisible(false);
	}
}
