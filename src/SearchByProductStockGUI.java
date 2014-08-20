import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class SearchByProductStockGUI extends JFrame {
	private JComboBox<String> productNameDropDown = new JComboBox<String>();
	private JComboBox<String> productIDDropDown = new JComboBox<String>();
	private JButton search;
	private JButton stockMenu;
	private JPanel panel;
	private JLabel productName = new JLabel("Please choose a Product Name from the list below");
	private JLabel productID = new JLabel("Please choose a ProductID ");
	private JLabel or = new JLabel("--OR--");
	private JRadioButton searchProductName = new JRadioButton("Search by Product Name",true);
	private JRadioButton searchByProductID = new JRadioButton("Search by Product ID",false);
	
	public SearchByProductStockGUI() {
		this.setSize(400,400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Search Stock by Product");
		panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));
		compileProductIDs();
		compileProductNames();
		search = new JButton("Search");
		
		if(searchProductName.isSelected()){
			productIDDropDown.setEnabled(false);
		}else if(searchByProductID.isSelected()){
			productNameDropDown.setEnabled(false);
		}
		
		final JTextField data = new JTextField();
		data.setEditable(false);
		data.setBackground(Color.LIGHT_GRAY);
		data.setSize(100,100);
		data.setVisible(false);
		
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
			boolean orderFound = false;
			
			
				for(Stock s:RetailSystem.getInstance().getStocks()){
						
					if(searchProductName.isSelected()){
						
						if(s.getProduct().getName().equalsIgnoreCase(productNameDropDown.getSelectedItem().toString())){
							
							for(Order o: RetailSystem.getInstance().getOrders()){
								if(o.getProduct().getName().equalsIgnoreCase(s.getProduct().getName())&&o.isActive()){
									JOptionPane.showMessageDialog(null, "Product: "+s.getProduct().getName()+"\nUnits: "+s.getUnits()+"\nOrder of "+o.getQuantity()+" units expected on "+dt.format(o.getExpectedDeliveryDate()));
									orderFound = true;
								}
							}
							if(!orderFound){
								JOptionPane.showMessageDialog(null, "Product: "+s.getProduct().getName()+"\nUnits: "+s.getUnits());
							}
							
							
						}
						
						
						
					}else{
						
						if(s.getProduct().getProductID().equalsIgnoreCase(productIDDropDown.getSelectedItem().toString())){
							
							for(Order o: RetailSystem.getInstance().getOrders()){
								
								if(o.getProduct().getProductID().equalsIgnoreCase(s.getProduct().getProductID())){
									JOptionPane.showMessageDialog(null, "Product: "+s.getProduct().getName()+"\nUnits: "+s.getUnits()+"\nOrder of "+o.getQuantity()+" units expected on "+dt.format(o.getExpectedDeliveryDate()));
									orderFound = true;
								}
							}
							
							if(!orderFound){
								JOptionPane.showMessageDialog(null, "Product: "+s.getProduct().getName()+"\nUnits: "+s.getUnits());
							}
						}
						
						
					}
					
				
				
				
				}
				
			}
				
			});
	
			stockMenu = new JButton("Return to menu");
		
		stockMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					new StockGUI();
					dispose();
			}
		});
		
		searchByProductID.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				searchProductName.setSelected(false);
				productNameDropDown.setEnabled(false);
				productIDDropDown.setEnabled(true);
				
			}
		});
		
		searchProductName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				searchByProductID.setSelected(false);
				productIDDropDown.setEnabled(false);
				productNameDropDown.setEnabled(true);
				
			}
		});
		
		panel.add(searchProductName);
		panel.add(productName);
		panel.add(productNameDropDown);
		panel.add(or);
		panel.add(searchByProductID);
		panel.add(productID);
		panel.add(productIDDropDown);
		panel.add(search);
		panel.add(stockMenu);
		panel.add(data);
		
		this.setVisible(true);
		
		
	
	}
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			productNameDropDown.addItem(product.getName());
		}
	}
	public void compileProductIDs(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			productIDDropDown.addItem(product.getProductID());
		}
	}
}
