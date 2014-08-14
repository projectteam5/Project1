import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ListSuppliersGUI extends JFrame{
	private ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
	private JComboBox comboList;
	private JLabel label;
	
	public ListSuppliersGUI() {
		
		// Suppliers in database are pulled into this array list and added to an un-editable combo box
		// All supplier information is available for view in the drop down menu.
		// One supplier is highlighted in main box and others can be selected from the drop down list
		setTitle("LIST OF SUPPLIERS");
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		suppliers = RetailSystem.getInstance().getSuppliers();
		comboList = new JComboBox<String>();
		comboList.setEditable(false);
		
		comboList.addItem("Select Supplier from drop down menu");
		for(Supplier s: suppliers){
			comboList.addItem(s.getName()+"-"+s.getSupplierID()+"-"+s.getPhoneNumber());
		}
		
		label = new JLabel("Showing list of suppliers");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		panel.add(comboList);
		Container cp = getContentPane();
		cp.add(panel);
		setVisible(true);
	}
}
