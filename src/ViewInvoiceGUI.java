import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ViewInvoiceGUI extends JPanel{
	private JLabel invoiceLabel;
	private JComboBox<String> invoiceDropDown = new JComboBox<String>();
	private JButton buttonViewInvoice;
	private JLabel invoiceDetails;
	private String selectedInvoiceID;
	private Invoice chosenInvoice;
	//For showing invoice details
	private JPanel showInvoicePanel;
	private JScrollPane scrollPaneInvoices;
	private Vector<Invoice> vet;
	private JTable table;
	private Vector<Invoice> vet2;
	//For showing lineItems
	private Vector<LineItem> vet3;
	private JTable table2;
	private JScrollPane scrollPaneLineItems;


	public ViewInvoiceGUI() {
		invoiceLabel = new JLabel("Please choose an invoice from the list below");
		buttonViewInvoice = new JButton("View Invoice");
		invoiceDetails = new JLabel("Invoice Details:");
		
		Invoice.invoiceListComplete(invoiceDropDown);
		this.add(invoiceLabel);
		this.add(invoiceDropDown);
		this.add(buttonViewInvoice);
		this.add(invoiceDetails);
		
		//this.setVisible(true);
		
		buttonViewInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedInvoiceID = RetailSystem.returnIDfromCombobox(invoiceDropDown.getSelectedItem().toString());
				System.out.println("selectedInvoiceID: "+selectedInvoiceID);
				//Find invoice item
				for(Invoice invoice: RetailSystem.getInstance().getInvoices()){
					if(selectedInvoiceID.equalsIgnoreCase(invoice.getInvoiceID())){
						chosenInvoice = invoice;
						System.out.println("1."+selectedInvoiceID+" 2."+invoice.getInvoiceID());
					}
				}
				//Show information from invoice in one table
				//Show information from Invoice.Sale.LineItems in another just below
			}
		});
	}


}
