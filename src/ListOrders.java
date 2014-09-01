import java.awt.Font;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class ListOrders extends JPanel {
	
	private JLabel title;
	private JScrollPane scrollPaneOrders;
	private Vector<Order> vet;
	private JTable table;

	public ListOrders() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		title = new JLabel("Order List");
		this.add(title);
		title.setFont(new Font("Arial", Font.BOLD, 20));
		vet = new Vector<Order>(RetailSystem.getInstance().getOrders());
		TableModel dataModel = new OrdersTable(vet); 
		table = new JTable(dataModel);
		scrollPaneOrders = new JScrollPane(table);
		this.add(scrollPaneOrders);
		
	}

}
