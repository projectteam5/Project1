import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ListSuppliersGUI extends JPanel {

	private JLabel title;
	private JScrollPane scrollPaneSuppliers;
	private Vector<Supplier> vet;
	private JTable table;

	public ListSuppliersGUI() {

		/*
		 * scrollPaneSuppliers = new JScrollPane(); title = new
		 * JLabel("Supplier List. Total number of suppliers: "
		 * +RetailSystem.getInstance().getSuppliers().size()); title.setFont(new
		 * Font("Arial", Font.BOLD, 20)); showSupplierPanel = new JPanel();
		 * showSupplierPanel.setLayout(new GridLayout(0,1)); for(Supplier
		 * supplier: RetailSystem.getInstance().getSuppliers()){
		 * if(supplier.isActive()){ JLabel label1 = new
		 * JLabel(supplier.getSupplierID
		 * ()+" | "+supplier.getName()+" | "+supplier.getPhoneNumber());
		 * label1.setSize(10,10); showSupplierPanel.add(label1); } }
		 * 
		 * this.add(title); this.setLayout(new GridLayout(0, 1));
		 * scrollPaneSuppliers.setViewportView(showSupplierPanel);
		 * this.add(scrollPaneSuppliers); setVisible(true);
		 */
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		title = new JLabel("Supplier List. Total number of suppliers: "
				+ RetailSystem.getInstance().getSuppliers().size());
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		vet = new Vector<Supplier>(RetailSystem.getInstance().getSuppliers());
		TableModel dataModel = new SupplierTable(vet);
		table = new JTable(dataModel);
		scrollPaneSuppliers = new JScrollPane(table);
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		this.add(title);
		this.add(labelEmpty1);
		this.add(scrollPaneSuppliers);
		this.add(labelEmpty);

	}

}
