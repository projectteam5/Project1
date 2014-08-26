import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ListSuppliersGUI extends JPanel{
	
	private JLabel title;
	private JPanel showSupplierPanel;
	private JScrollPane scrollPaneSuppliers;
	
	public ListSuppliersGUI() {
		
		scrollPaneSuppliers = new JScrollPane();
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
		setVisible(true);
	}
	
}
