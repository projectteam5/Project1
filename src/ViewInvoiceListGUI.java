import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class ViewInvoiceListGUI extends JPanel{
	private JLabel title;
	private JPanel showInvoicePanel;
	private JScrollPane scrollPaneInvoices;
	private Vector<Invoice> vet;
	private JTable table;
	private Vector<Invoice> vet2;

	public ViewInvoiceListGUI() {
		title = new JLabel("Invoice List. Total number of Invoices: "+RetailSystem.getInstance().getInvoices().size());
		title.setFont(new Font("Arial", Font.BOLD, 20));
		this.setLayout(new GridLayout(0,1));
		vet = new Vector<Invoice>(RetailSystem.getInstance().getInvoices());
		TableModel dataModel = new InvoiceTable(vet); 
		table = new JTable(dataModel);
		scrollPaneInvoices = new JScrollPane(table);
		this.add(scrollPaneInvoices);
	}

}
