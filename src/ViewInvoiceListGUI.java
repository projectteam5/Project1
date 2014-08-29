import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class ViewInvoiceListGUI extends JPanel{
	private JLabel title;
	private JScrollPane scrollPaneInvoices;
	private Vector<Invoice> vet;
	private JTable table;
	
	public ViewInvoiceListGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		title = new JLabel("Invoice List. Total number of Invoices: "+RetailSystem.getInstance().getInvoices().size());
		title.setFont(new Font("Arial", Font.BOLD, 20));

		vet = new Vector<Invoice>(RetailSystem.getInstance().getInvoices());
		TableModel dataModel = new InvoiceTable(vet); 
		table = new JTable(dataModel);
		scrollPaneInvoices = new JScrollPane(table);

		//fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(title);
		this.add(labelEmpty1);

		this.add(scrollPaneInvoices);
	}

}
