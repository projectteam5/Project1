import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;



public class ViewInvoiceGUI extends JPanel{
	
	
	private JLabel invoiceLabel;
	private JLabel invoiceInfoLabel1;
	private JLabel invoiceInfoLabel2;
	private JLabel invoiceDetails;
	private JLabel labelTitle;
	
	private JComboBox<String> invoiceDropDown = new JComboBox<String>();
	private JButton buttonViewInvoice;
	
	private String selectedInvoiceID;
	private Invoice chosenInvoice;
	//For showing invoice details
	
	private Vector<LineItem> vet;
	private TableModel dataModel;
	private JTable table;
	private JScrollPane scrollPaneLineItems;
	//private boolean populated = false;
	//private JLabel title;


	public ViewInvoiceGUI() {

		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		labelTitle = new JLabel("Viewing Invoice");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitle.setAlignmentX(CENTER_ALIGNMENT);

		invoiceLabel = new JLabel("Please choose an invoice from the list below");
		invoiceLabel.setAlignmentX(CENTER_ALIGNMENT);
		buttonViewInvoice = new JButton("View Invoice");
		buttonViewInvoice.setAlignmentX(CENTER_ALIGNMENT);
		invoiceDetails = new JLabel("Invoice Details:");
		invoiceInfoLabel1 = new JLabel("");
		invoiceInfoLabel1.setAlignmentX(CENTER_ALIGNMENT);
		invoiceInfoLabel2 = new JLabel("");
		invoiceInfoLabel2.setAlignmentX(CENTER_ALIGNMENT);
		
		vet = new Vector<LineItem>();
		dataModel = new LineItemTable(vet);
		table = new JTable(dataModel);
		scrollPaneLineItems = new JScrollPane(table);
		table.removeColumn(table.getColumnModel().getColumn(3));
		
		Invoice.invoiceListComplete(invoiceDropDown);

		
		//fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		
		this.add(labelTitle);

		this.add(invoiceLabel);
		this.add(labelEmpty);
		this.add(invoiceDropDown);
		this.add(labelEmpty1);
		this.add(buttonViewInvoice);
		this.add(labelEmpty);
		//this.add(invoiceDetails);
		this.add(invoiceInfoLabel1);
		this.add(invoiceInfoLabel2);
		this.add(labelEmpty1);
		this.add(scrollPaneLineItems);
		
		buttonViewInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedInvoiceID = RetailSystem.returnIDfromCombobox(invoiceDropDown.getSelectedItem().toString());
				//Find invoice item
				chosenInvoice = Invoice.retrieveInvoiceWithID(selectedInvoiceID);
				//Show information from invoice in a label
				if(chosenInvoice!= null){
				invoiceInfoLabel1.setText("Invoice Date: "+chosenInvoice.getInvoiceDate()+" | Invoice ID: "+chosenInvoice.getInvoiceID());
				invoiceInfoLabel2.setText("Customer Name: "+chosenInvoice.getCustomer().getName()+" | Customer ID: "+chosenInvoice.getCustomer().getCustomerID()+" | Total Cost: €"+chosenInvoice.getTotalInvoice());
				//Show information from Invoice.Sale.LineItems in another just below
				showLineItemInformation(chosenInvoice);
				}
			}
		});
	}
	
	
	public void showLineItemInformation(Invoice invoice){
		vet.clear();
		for(LineItem lineItem: invoice.getSale().getLineItems()){
			vet.add(lineItem);
			repopulate();
		}
	}
	
	public void repopulate() {
		table.revalidate();
		table.repaint();
	}


}
