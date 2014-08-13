import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SupplierGUI extends JFrame{

	public SupplierGUI() {
		setTitle("SUPPLIER GUI");
		setSize(400,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JButton editButton = new JButton("Edit Supplier Details");
		JButton viewListButton = new JButton("List Suppliers");
		JButton modifyListButton = new JButton("Add/Delete Suppliers");
		JButton productPerSupplier = new JButton("ViewSupplierOfProduct");
		
		// Launches the edit supplier frame
		editButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				EditSupplierGUI editSupplier =new EditSupplierGUI();
			}
		});
		
		// Launches the add/delete supplier from list frame
		modifyListButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				AddOrDeleteSupplierGUI addOrDeleteSupplierGUI = new AddOrDeleteSupplierGUI();
			}
		});
		
		// Launches the list suppliers frame
		viewListButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				ListSuppliersGUI listSupplierGUI = new ListSuppliersGUI();		
			}
		});
		
		// Launches the the supplier of a particular product frame
		productPerSupplier.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				SupplierOfProductGUI supplierOfProductGUI = new SupplierOfProductGUI();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(editButton);
		panel.add(modifyListButton);
		panel.add(productPerSupplier);
		panel.add(viewListButton);
		Container cp = getContentPane();
		cp.add(panel);
		setVisible(true);
	}
}
