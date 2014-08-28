
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class AvailableStockLevelsGUI extends JPanel {
	 private JLabel title;
	 private JScrollPane scrollPane;
	 private Vector<Stock> v;
	 private JTable table;
	
	public AvailableStockLevelsGUI() {
		title = new JLabel("Stock List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		this.setLayout(new GridLayout(0,1));
		v = new Vector<Stock>(RetailSystem.getInstance().getStocks());
		TableModel dataModel = new StockTable(v); 
		table = new JTable(dataModel);
		scrollPane = new JScrollPane(table);
		this.add(scrollPane);
	}
	

}
