import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;



public class ViewInvoiceGUI extends JPanel{
	private TableModel dataModel;
	private JLabel invoiceLabel;
	private JComboBox<String> invoiceDropDown = new JComboBox<String>();
	private JButton buttonViewInvoice;
	private JLabel invoiceDetails;
	private String selectedInvoiceID;
	private Invoice chosenInvoice;
	//For showing invoice details
	private JLabel invoiceInfoLabel1;
	private JLabel invoiceInfoLabel2;
	private Vector<LineItem> vet;
	private JTable table;
	private JScrollPane scrollPaneLineItems;
	private boolean populated = false;


	public ViewInvoiceGUI() {
		invoiceLabel = new JLabel("Please choose an invoice from the list below");
		buttonViewInvoice = new JButton("View Invoice");
		invoiceDetails = new JLabel("Invoice Details:");
		invoiceInfoLabel1 = new JLabel("");
		invoiceInfoLabel2 = new JLabel("");
		
		vet = new Vector<LineItem>();
		dataModel = new LineItemTable(vet);
		table = new JTable(dataModel);
		scrollPaneLineItems = new JScrollPane(table);
		table.removeColumn(table.getColumnModel().getColumn(3));
		
		Invoice.invoiceListComplete(invoiceDropDown);
		this.add(invoiceLabel);
		this.add(invoiceDropDown);
		this.add(buttonViewInvoice);
		this.add(invoiceDetails);
		this.add(invoiceInfoLabel1);
		this.add(invoiceInfoLabel2);
		this.add(scrollPaneLineItems);
		
		buttonViewInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedInvoiceID = RetailSystem.returnIDfromCombobox(invoiceDropDown.getSelectedItem().toString());
				System.out.println("selectedInvoiceID: "+selectedInvoiceID);
				//Find invoice item
				for(Invoice invoice: RetailSystem.getInstance().getInvoices()){
					if(selectedInvoiceID.equalsIgnoreCase(invoice.getInvoiceID())){
						chosenInvoice = invoice;
					}
				}
				//Show information from invoice in a label
				invoiceInfoLabel1.setText("Invoice Date: "+chosenInvoice.getInvoiceDate()+" | Invoice ID: "+chosenInvoice.getInvoiceID());
				invoiceInfoLabel2.setText("Customer Name: "+chosenInvoice.getCustomer().getName()+" | Customer ID: "+chosenInvoice.getCustomer().getCustomerID()+" | Total Cost: €"+chosenInvoice.getTotalInvoice());
				//Show information from Invoice.Sale.LineItems in another just below
				if(!populated){
					showLineItemInformation(chosenInvoice);
				}
			}
		});
	}
	
	
	public void showLineItemInformation(Invoice invoice){
		for(LineItem lineItem: invoice.getSale().getLineItems()){
			vet.add(lineItem);
			repopulate();
			populated = true;
		}
	}
	
	public void repopulate() {
		table.revalidate();
		table.repaint();
	}


}
