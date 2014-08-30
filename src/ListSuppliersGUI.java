import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ListSuppliersGUI extends JPanel{
	
	private JLabel title;
	private JPanel showSupplierPanel;
	private JScrollPane scrollPaneSuppliers;
	private Vector<Supplier> vet;
	private JTable table;
	private Vector<Supplier> vet2;
	
	public ListSuppliersGUI() {
		
		/*scrollPaneSuppliers = new JScrollPane();
		title = new JLabel("Supplier List. Total number of suppliers: "+RetailSystem.getInstance().getSuppliers().size());
		title.setFont(new Font("Arial", Font.BOLD, 20));	
		showSupplierPanel = new JPanel();
		showSupplierPanel.setLayout(new GridLayout(0,1));	
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			if(supplier.isActive()){
			JLabel label1 = new JLabel(supplier.getSupplierID()+" | "+supplier.getName()+" | "+supplier.getPhoneNumber());
			label1.setSize(10,10);
			showSupplierPanel.add(label1);
			}
		}
		
		this.add(title);
		this.setLayout(new GridLayout(0, 1));
		scrollPaneSuppliers.setViewportView(showSupplierPanel);
		this.add(scrollPaneSuppliers);
		setVisible(true);*/
		
		title = new JLabel("Supplier List. Total number of suppliers: "+RetailSystem.getInstance().getSuppliers().size());
		title.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(title);
		this.setLayout(new GridLayout(0,1));
		vet = new Vector<Supplier>(RetailSystem.getInstance().getSuppliers());
		TableModel dataModel = new SupplierTable(vet); 
		table = new JTable(dataModel);
		scrollPaneSuppliers = new JScrollPane(table);
		this.add(scrollPaneSuppliers);
		
	}
	
	
}
