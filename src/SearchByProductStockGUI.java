import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;


public class SearchByProductStockGUI extends JPanel {
	private JComboBox<String> productNameDropDown = new JComboBox<String>();
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JButton search;
	private JButton stockMenu;
	private JPanel stockResults;
	private JLabel result;
	private JLabel productName = new JLabel("Please choose a Product Name from the list below");
	private JLabel supplier = new JLabel("Please choose a Supplier");
	private JLabel or = new JLabel("--OR--");
	private JRadioButton searchProductName = new JRadioButton("Search by Product Name",true);
	private JRadioButton searchBySupplier = new JRadioButton("Search by Supplier",false);
	private String orders;
	private JLabel title;
	public SearchByProductStockGUI() {
		stockResults = new JPanel();
		stockResults.setLayout(new BorderLayout());
		this.setLayout(new GridLayout(0,1));
		compileSuppliers();
		compileProductNames();
		search = new JButton("Search");
		
		if(searchProductName.isSelected()){
				supplierDropDown.setEnabled(false);
		}else if(searchBySupplier.isSelected()){
			productNameDropDown.setEnabled(false);
		}
		
		final JTextField data = new JTextField();
		data.setEditable(false);
		data.setBackground(Color.LIGHT_GRAY);
		data.setSize(100,100);
		data.setVisible(false);
		
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			if(searchBySupplier.isSelected()){	
				searchStockBySupplier();
			}else{
				searchForProductStock();
			}
			}
		});
		
		searchBySupplier.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				searchProductName.setSelected(false);
				productNameDropDown.setEnabled(false);
				supplierDropDown.setEnabled(true);
				
			}
		});
		
		searchProductName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				searchBySupplier.setSelected(false);
				supplierDropDown.setEnabled(false);
				productNameDropDown.setEnabled(true);
				
			}
		});
		
		add(searchProductName);
		add(productName);
		add(productNameDropDown);
		add(or);
		add(searchBySupplier);
		add(supplier);
		add(supplierDropDown);
		add(search);
		//add(stockMenu);
		add(data);
		
		this.setVisible(true);
		
		
	
	}
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			productNameDropDown.addItem(product.getName());
		}
	}
	public void compileSuppliers(){
		ArrayList<Supplier>suppliers = new ArrayList<Supplier>();
		for(Supplier supplier: RetailSystem.getInstance().getSuppliers()){
			for(Stock s: RetailSystem.getInstance().getStocks()){
				if(s.getProduct().getSupplier().equals(supplier)&&!suppliers.contains(supplier)){
					suppliers.add(supplier);
				}
			}
		}
		for(Supplier supp: suppliers){
			supplierDropDown.addItem(supp.getName());
		}
	}
	public void searchForProductStock(){
		JPanel productResult = new JPanel();
		JTextArea results = new JTextArea();
		boolean foundStock = false;
		for(Stock s:RetailSystem.getInstance().getStocks()){
			
			if(s.getProduct().getName().equalsIgnoreCase(productNameDropDown.getSelectedItem().toString())){
					results.setText(s.getProduct().getName()
						+"|("+s.getUnits()+" available)\n");
					foundStock = true;
				for(Order o:RetailSystem.getInstance().getOrders()){
					if(s.getProduct().getProductID().equalsIgnoreCase(o.getProduct().getProductID())&&o.isReceived()==false&&o.isActive()==true){
						results.setText(results.getText()+" Order of"+o.getQuantity()+" units expected on "+o.getExpectedDeliveryDate()+"\n");
					}
				}
			}
			
			
		}
	if(!foundStock){
		JOptionPane.showMessageDialog(null, "No stock available for this product");
	}else{
	productResult.setLayout(new GridLayout(0,1));
	productResult.add(results);
	productResult.setVisible(true);
	MenuGUI.getInstance().setPanelAction(productResult);
	}
	}
	public void searchStockBySupplier(){
		JPanel productResult = new JPanel();
		JTextArea results = new JTextArea();
		boolean foundStock = false;
		JScrollPane scroll = new JScrollPane();
		JLabel supplierName = new JLabel(supplierDropDown.getSelectedItem().toString());
			for(Stock stock: RetailSystem.getInstance().getStocks()){
				
				if(stock.getProduct().getSupplier().getName().equalsIgnoreCase(supplierDropDown.getSelectedItem().toString())){
					results.setText(results.getText()+stock.getProduct().getName()+
							"|(Units: "+stock.getUnits()+")\n"
							);
					foundStock = true;
					for(Order o:RetailSystem.getInstance().getOrders()){
						if(stock.getProduct().getProductID().equalsIgnoreCase(o.getProduct().getProductID())){
							results.setText(results.getText()+"Order of "+o.getQuantity()+" units expected on "+o.getExpectedDeliveryDate()+"\n");
						}
					}
					
					
				}
				
			}
			
			if(!foundStock){
				JOptionPane.showMessageDialog(null, "No stock available for this suppier");
			}else{
			scroll.setViewportView(results);
			productResult.setLayout(new GridLayout(0,1));
			productResult.add(supplierName);
			productResult.add(scroll);
			productResult.setVisible(true);
			MenuGUI.getInstance().setPanelAction(productResult);
			}
	}
	
}
