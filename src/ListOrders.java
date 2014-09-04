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
	private JLabel subTitle;
	private JScrollPane scrollPaneOrders;
	private Vector<Order> vet;
	private JTable table;

	public ListOrders() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		title = new JLabel("Order List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		subTitle = new JLabel("Total amount of orders: " + RetailSystem.getInstance().getOrders().size());
		subTitle.setAlignmentX(CENTER_ALIGNMENT);
		
		vet = new Vector<Order>(RetailSystem.getInstance().getOrders());
		
		JLabel emptyLabel = new JLabel(" ");
		JLabel emptyLabel2 = new JLabel(" ");
		
		this.add(title);
		this.add(emptyLabel);
		this.add(subTitle);
		this.add(emptyLabel2);
		
		TableModel dataModel = new OrdersTable(vet); 
		table = new JTable(dataModel);
		scrollPaneOrders = new JScrollPane(table);
		this.add(scrollPaneOrders);
		
		table.revalidate();
		
		table.repaint();
		
	}

}
