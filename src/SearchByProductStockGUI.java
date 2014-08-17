import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SearchByProductStockGUI extends JFrame {

	public SearchByProductStockGUI() {
		this.setSize(400,400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Search Stock by Product");
		
		
		Container c = this.getContentPane();
		
		JPanel searchByProductPanel = new JPanel();
		searchByProductPanel.setLayout(new FlowLayout());
		JLabel lblProductID = new JLabel("Search by product id");
		JLabel lblProductName = new JLabel("Search by product name");
		final JTextField productID = new JTextField();
		productID.setPreferredSize(new Dimension(200, 24));
		final JTextField productName = new JTextField();
		productName.setPreferredSize(new Dimension(200, 24));	
		JButton search = new JButton("Search");
		final JLabel productDetails = new JLabel();
		
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
				for(Stock s:RetailSystem.getInstance().getStocks()){
					
					if(productID.getText().toString().equalsIgnoreCase(s.getProduct().getProductID())||productName.getText().toString().equalsIgnoreCase(s.getProduct().getName())){
						productDetails.setText(s.getProduct().getProductID()+" : "+s.getUnits()+"units available.");
						for(Order o:RetailSystem.getInstance().getOrders()){
							if(s.getProduct().getProductID().equalsIgnoreCase(o.getProduct().getProductID())){
								productDetails.setText(s.getProduct().getProductID()+" : "+s.getUnits()+"units available.\n An order of "+o.getQuantity()+" units is expected on "+dt.format(o.getExpectedDeliveryDate()));
							}
						}
					}
					
				}
				
			}
				
			});
	
		
		
		
		
		
		
		
		
		
		
		JButton stockMenu = new JButton("Return to menu");
		
		
		
		
		
		
		
		
		
		
		
		
		
		searchByProductPanel.add(lblProductID);
		searchByProductPanel.add(productID);
		searchByProductPanel.add(lblProductName);
		searchByProductPanel.add(productName);
		searchByProductPanel.add(search);
		searchByProductPanel.add(stockMenu);
		searchByProductPanel.add(productDetails);
		if(productDetails.getText().isEmpty()){
			productDetails.setText("No data for this product");
		}
		repaint();
		c.add(searchByProductPanel);
		setVisible(true);
	}

}
