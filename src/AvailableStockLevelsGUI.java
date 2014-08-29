import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class AvailableStockLevelsGUI extends JPanel {

	private JLabel title;
	private JScrollPane scrollPane;

	private JTable table;
	private String[][] vector;
	private String orderDate;

	public AvailableStockLevelsGUI() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		title = new JLabel("Stock List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		buildVector();
		TableModel dataModel = new StockTable(vector);

		table = new JTable(dataModel);
		scrollPane = new JScrollPane(table);
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		this.add(title);
		this.add(labelEmpty1);
		this.add(scrollPane);
		this.add(labelEmpty);
	}

	public void buildVector() {
		vector = new String[RetailSystem.getInstance().getStocks().size()][3];
		for (int i = 0; i < RetailSystem.getInstance().getStocks().size(); i++) {
			orderDate = "";
			vector[i][0] = RetailSystem.getInstance().getStocks().get(i)
					.getProduct().getName();
			vector[i][1] = RetailSystem.getInstance().getStocks().get(i)
					.getUnits()
					+ "";
			checkOrder(RetailSystem.getInstance().getStocks().get(i)
					.getProduct());
			vector[i][2] = orderDate;
		}
	}

	public boolean checkOrder(Product product) {
		boolean b = false;
		for (Order order : RetailSystem.getInstance().getOrders()) {
			if (order.isActive()
					&& !order.isReceived()
					&& order.getProduct().getProductID()
							.equals(product.getProductID())) {
				b = true;
				orderDate = DateFormat.getDateInstance().format(
						order.getExpectedDeliveryDate());
			}
		}
		return b;

	}

}
