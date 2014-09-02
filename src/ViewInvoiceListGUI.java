import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ViewInvoiceListGUI extends JPanel {
	private JLabel title;
	private JLabel instruction;
	private JScrollPane scrollPaneInvoices;
	private Vector<Invoice> vet;
	private JTable table;
	private JComboBox month;
	private JComboBox year;
	private JButton showInvoice;

	public ViewInvoiceListGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		title = new JLabel("Invoice List");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		instruction = new JLabel("Please select a month and an year");
		instruction.setAlignmentX(CENTER_ALIGNMENT);
		month = new JComboBox<String>(RetailSystem.getInstance().getMonths());
		year = new JComboBox<String>(RetailSystem.getInstance().getYears());
		showInvoice = new JButton("Show Invoice");
		showInvoice.setAlignmentX(CENTER_ALIGNMENT);
		vet = new Vector<Invoice>();
		buildVector();

		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");

		this.add(title);
		this.add(labelEmpty);
		this.add(instruction);
		this.add(labelEmpty1);
		this.add(month);
		this.add(labelEmpty);
		this.add(year);
		this.add(labelEmpty1);
		this.add(showInvoice);
		this.add(labelEmpty);
		TableModel dataModel = new InvoiceTable(vet);
		table = new JTable(dataModel);
		scrollPaneInvoices = new JScrollPane(table);
		this.add(scrollPaneInvoices);
		table.revalidate();
		table.repaint();

		showInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showInvoice();
			}
		});
	}

	public void buildVector() {
		vet.clear();
		String monthSelected = month.getSelectedItem().toString();
		int yearSelected = Integer.parseInt(year.getSelectedItem().toString());
		// Build the related table
		if (!monthSelected.isEmpty() && monthSelected != null
				&& yearSelected != 0) {

			for (Invoice invoice : RetailSystem.getInstance().getInvoices()) {
				if (invoice.getInvoiceDate().getYear() == (yearSelected - 1900)) {
					if (monthSelected.equals("January")
							&& invoice.getInvoiceDate().getMonth() == 0) {
						vet.add(invoice);
					} else if (monthSelected.equals("February")
							&& invoice.getInvoiceDate().getMonth() == 1) {
						vet.add(invoice);
					} else if (monthSelected.equals("March")
							&& invoice.getInvoiceDate().getMonth() == 2) {
						vet.add(invoice);
					} else if (monthSelected.equals("April")
							&& invoice.getInvoiceDate().getMonth() == 3) {
						vet.add(invoice);
					} else if (monthSelected.equals("May")
							&& invoice.getInvoiceDate().getMonth() == 4) {
						vet.add(invoice);
					} else if (monthSelected.equals("June")
							&& invoice.getInvoiceDate().getMonth() == 5) {
						vet.add(invoice);
					} else if (monthSelected.equals("July")
							&& invoice.getInvoiceDate().getMonth() == 6) {
						vet.add(invoice);
					} else if (monthSelected.equals("August")
							&& invoice.getInvoiceDate().getMonth() == 7) {
						vet.add(invoice);
					} else if (monthSelected.equals("September")
							&& invoice.getInvoiceDate().getMonth() == 8) {
						vet.add(invoice);
					} else if (monthSelected.equals("October")
							&& invoice.getInvoiceDate().getMonth() == 9) {
						vet.add(invoice);
					} else if (monthSelected.equals("November")
							&& invoice.getInvoiceDate().getMonth() == 10) {
						vet.add(invoice);
					} else if (monthSelected.equals("December")
							&& invoice.getInvoiceDate().getMonth() == 11) {
						vet.add(invoice);
					}
				}
			}

		}

	}

	public void showInvoice() {
		buildVector();
		table.revalidate();
		table.repaint();
	}

}
