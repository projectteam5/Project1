import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class AvailableStockLevelsGUI extends JFrame {
	private Container contentPane;
	private JPanel panel;

	public AvailableStockLevelsGUI() {
		//the frames attributes
		this.setSize(600,400);
		//this.setResizable(false);
		this.setTitle("Available Stock Levels");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		contentPane= getContentPane();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();// so a scroller appears if there is lots of stock
		JLabel title = new JLabel("Available stock levels");
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");//to format the date object from the orders.
		panel.add(title,BorderLayout.NORTH);
		
		JPanel centralPanel = new JPanel(new GridLayout(RetailSystem.getInstance().getStocks().size(),0));
		centralPanel.setSize(50,50);
		for(Stock s:RetailSystem.getInstance().getStocks()){// grabs all available stock from stocks arraylist.
			JLabel lbl = new JLabel(s.getProduct().getName()+" : "+s.getUnits()+" units available\n");
			
			for(Order o: RetailSystem.getInstance().getOrders()){// gets the list of orders 
				if(o.getProduct().getProductID()==s.getProduct().getProductID()){// we should display when the stock is expected to arrive if its on order. 
					lbl = new JLabel(s.getProduct().getName()+" : "+s.getUnits()+" units available. Order of "+o.getQuantity()+" units expected:"+dt.format(o.getExpectedDeliveryDate()));
				}
				
			}
			
			
			//lbl.setFont(new Font("Times New Roman",20,20));
			lbl.setSize(10,10);
			centralPanel.add(lbl);
		}
		scrollPane.setViewportView(centralPanel);// puts the scroll pane on the central panel with the data
		
		
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new StockGUI();
				dispose();// gets rid of just this GUI
			}
		});
		panel.add(scrollPane,BorderLayout.CENTER);// puts the scrollpane which now contains the central panel into the center
		panel.add(ok,BorderLayout.SOUTH);// button on bottom
		contentPane.add(panel);
		
		this.setVisible(true);
	}

}
