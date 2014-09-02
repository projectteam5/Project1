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
import java.text.DateFormat;
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
	private String[][] vector;
	private String orderDate;
	private JTable table;
	
	public SearchByProductStockGUI() {
		stockResults = new JPanel();
		stockResults.setLayout(new BorderLayout());
		this.setLayout(new GridLayout(0,1));
		compileSuppliers();
		compileProductNames();
		search = new JButton("Search");
		title = new JLabel("View Stock");	
		title.setFont(new Font("Arial", Font.BOLD, 20));
		
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
		
		add(title);
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
		vector = new String[1][3];
		for(Stock s:RetailSystem.getInstance().getStocks()){
			if(s.getProduct().getName().equalsIgnoreCase(productNameDropDown.getSelectedItem().toString())){
				vector[0][0]= s.getProduct().getName();
				vector[0][1]= s.getUnits()+"";
					foundStock = true;
				for(Order o:RetailSystem.getInstance().getOrders()){
					if(s.getProduct().getProductID().equalsIgnoreCase(o.getProduct().getProductID())&&o.isReceived()==false&&o.isActive()==true){
						vector[0][2]=DateFormat.getDateInstance().format(o.getExpectedDeliveryDate());
					}
				}
			}
			
			
		}
	if(!foundStock){
		JOptionPane.showMessageDialog(null, "No stock available for this product");
	}else{
		table = new JTable();
		TableModel dataModel = new StockTable(vector);
		table = new JTable(dataModel);
		JScrollPane scrollPane = new JScrollPane(table);
		productResult.add(scrollPane);
	MenuGUI.getInstance().setPanelAction(productResult);
	}
	}
	public void searchStockBySupplier(){
		JPanel panel = new JPanel();
		table = new JTable();
		TableModel dataModel = new StockTable(vector);
		JScrollPane scrollPane;
		for(Supplier supplier:RetailSystem.getInstance().getSuppliers()){
			if(supplier.getName().equals(supplierDropDown.getSelectedItem())){
				buildVector(supplier);
				
				
			}
			dataModel = new StockTable(vector);
		}
			
		
		table = new JTable(dataModel);
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		MenuGUI.getInstance().setPanelAction(panel);
	}
	public void buildVector(Supplier supplier) {
		
		int counter = 0;
		for(int i=0;i<RetailSystem.getInstance().getProducts().size();i++){
			if(RetailSystem.getInstance().getProducts().get(i).getSupplier().getSupplierID().equalsIgnoreCase(supplier.getSupplierID())){
				counter++;
			}
			
		}
		int counter2=0;	
		System.out.println(counter);
		vector = new String[counter][3];
		for (int i = 0; i < RetailSystem.getInstance().getStocks().size() ; i++) {
			if(supplier.getSupplierID().equals(RetailSystem.getInstance().getStocks().get(i).getProduct().getSupplier().getSupplierID())){
				orderDate = "";
				vector[counter2][0] = RetailSystem.getInstance().getStocks().get(i)
					.getProduct().getName();
				vector[counter2][1] = RetailSystem.getInstance().getStocks().get(i)
					.getUnits()
					+ "";
				checkOrder(RetailSystem.getInstance().getStocks().get(i)
					.getProduct());
				vector[counter2][2] = orderDate;
				counter2++;
			}
			
				
			}
			
			
		}
			
	
	public boolean checkOrder(Product product) {
		boolean b = false;
		for (Order order : RetailSystem.getInstance().getOrders()) {
			if (order.isActive()
					&& !order.isReceived()
					&& order.getProduct().getProductID()
							.equals(product.getProductID())) {
				b = true;
				orderDate = DateFormat.getDateInstance().format(
						order.getExpectedDeliveryDate());
			}
		}
		return b;

	}
	
}
