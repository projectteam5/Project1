import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InvoiceMenuGUI extends JPanel{
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);
	private JButton buttonViewInvoice;
	private JButton buttonViewAllInvoices;
	private JLabel labelTitle;

	public InvoiceMenuGUI() {
		this.setLayout(new GridLayout(0,1));
		labelTitle = new JLabel("Invoice Menu");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		buttonViewInvoice = new JButton("View Invoice");
		buttonViewAllInvoices = new JButton("View All Invoices");
		
		colourButton();
		
		this.add(labelTitle);
		this.add(buttonViewInvoice);
		this.add(buttonViewAllInvoices);
		//fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		
		buttonViewInvoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				colourButton();
				buttonViewInvoice.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new ViewInvoiceGUI());
			}
		});
		
		buttonViewAllInvoices.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				colourButton();
				buttonViewAllInvoices.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new ViewInvoiceListGUI());
			}
		});
		
		this.setVisible(true);
	}

	
	public void colourButton(){
		buttonViewInvoice.setBackground(colorButtons);
		buttonViewInvoice.setFont(fontButtons);
		buttonViewAllInvoices.setBackground(colorButtons);
		buttonViewAllInvoices.setFont(fontButtons);

	}

}
