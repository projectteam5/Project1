import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ViewInvoiceGUI extends JPanel{
	private JLabel invoiceLabel;
	private JComboBox<String> invoiceDropDown = new JComboBox<String>();
	private JButton buttonViewInvoice;
	private JLabel invoiceDetails;

	public ViewInvoiceGUI() {
		invoiceLabel = new JLabel("Please choose an invoice from the list below");
		buttonViewInvoice = new JButton("View Invoice");
		invoiceDetails = new JLabel("Invoice Details:");
		
		Invoice.invoiceListComplete(invoiceDropDown);
		this.add(invoiceLabel);
		this.add(invoiceDropDown);
		this.add(buttonViewInvoice);
		this.add(invoiceDetails);
		
		this.setVisible(true);
	}


}
