//GUI done. Method working//test commit
//waly
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;


public class ViewProductListGUI extends JPanel{
	private JLabel title;
	private JPanel showProductPanel;
	private JScrollPane scrollPaneProducts;
	private Vector<Product> vet;
	private Vector<Product> vet2;
	private JTable table;
	

	public ViewProductListGUI() {
		title = new JLabel("Product List. Amount of products: "+RetailSystem.getInstance().getProducts().size());
		title.setFont(new Font("Arial", Font.BOLD, 20));
		this.setLayout(new GridLayout(0,1));
		vet = new Vector<Product>(RetailSystem.getInstance().getProducts());
		TableModel dataModel = new ProductTable(vet); 
		table = new JTable(dataModel);
		scrollPaneProducts = new JScrollPane(table);
		this.add(scrollPaneProducts);

	}
		

}



