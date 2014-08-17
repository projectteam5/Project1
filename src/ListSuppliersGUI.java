import java.awt.BorderLayout;
import java.awt.Container;
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

public class ListSuppliersGUI extends JFrame{
	
	private JButton button;
	
	public ListSuppliersGUI() {
		
		setTitle("LIST OF SUPPLIERS");
		setSize(400,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel label = new JLabel("Showing list of suppliers");
		JPanel panel = new JPanel();
		button = new JButton("Supplier Menu");
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		
		JScrollPane scrollPaneSuppliers = new JScrollPane();
		JLabel title = new JLabel("Supplier List. Total number of suppliers: "+RetailSystem.getInstance().getSuppliers().size());
			
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ago0){
				SupplierMenuGUI supplierMenuGUI = new SupplierMenuGUI();
				closeViewProductListGUI();	
			}
		});
		
		JPanel showSupplierPanel = new JPanel();
		showSupplierPanel.setLayout(new GridLayout(0,1));	
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			JLabel label1 = new JLabel(supplier.getSupplierID()+" | "+supplier.getName()+" | "+supplier.getPhoneNumber());
			label.setSize(10,10);
			showSupplierPanel.add(label1);
		}
		
		scrollPaneSuppliers.setViewportView(showSupplierPanel);
		panel.add(scrollPaneSuppliers,BorderLayout.CENTER);
		panel.add(title,BorderLayout.NORTH);
		panel.add(button,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public void closeViewProductListGUI(){
		setVisible(false);
	}
}
