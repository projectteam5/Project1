import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class SearchByProductStockGUI extends JPanel {
	private JComboBox<String> productNameDropDown = new JComboBox<String>();
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JButton search;
	private JButton stockMenu;
	private JPanel stockResults;
	private ArrayList<JLabel> results;
	private JLabel result;
	private JLabel productName = new JLabel("Please choose a Product Name from the list below");
	private JLabel supplier = new JLabel("Please choose a Supplier");
	private JLabel or = new JLabel("--OR--");
	private JRadioButton searchProductName = new JRadioButton("Search by Product Name",true);
	private JRadioButton searchBySupplier = new JRadioButton("Search by Supplier",false);
	private String orders;
	public SearchByProductStockGUI() {
		//this.setSize(400,400);
		//this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.setTitle("Search Stock by Product");
		//panel = new JPanel();
		//Container container = getContentPane();
		//container.add(panel);
		//panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		results = new ArrayList<JLabel>();
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
			SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
			boolean orderFound = false;
			
			
				for(Stock s:RetailSystem.getInstance().getStocks()){
						orders+="\n";
					if(searchProductName.isSelected()){
						
						if(s.getProduct().getName().equalsIgnoreCase(productNameDropDown.getSelectedItem().toString())){
							
							for(Order o: RetailSystem.getInstance().getOrders()){
								if(o.getProduct().getName().equalsIgnoreCase(s.getProduct().getName())&&o.isActive()&&!o.isReceived()){
									result = new JLabel("Product: "+s.getProduct().getName()+"\nUnits: "+s.getUnits()+"\nOrder of "+o.getQuantity()+" units expected on "+dt.format(o.getExpectedDeliveryDate()));
									results.add(result);
									for(JLabel r: results){
										r.setSize(30, 30);
										stockResults.add(r,BorderLayout.CENTER);
										}
										MenuGUI.getInstance().setPanelAction(stockResults);
									orderFound = true;
								}
							}
							if(!orderFound){
									result= new JLabel(s.getProduct().getName()+"    "+s.getUnits());
									results.add(result);
									for(JLabel r: results){
									stockResults.add(r);
									}
									MenuGUI.getInstance().setPanelAction(stockResults);
							}
							
							
						}
						
						
						
					}else{
						
						for(Supplier supplier:RetailSystem.getInstance().getSuppliers()){
							
							if(supplier.getName().equalsIgnoreCase(supplierDropDown.getSelectedItem().toString())){
								JTextArea details = new JTextArea();
								JScrollPane scrollPane = new JScrollPane();
								String supplierName = supplier.getName()+"\n";
								SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
								String stocksList = "-------------------------------------------------------\n";
								for(Stock stock: RetailSystem.getInstance().getStocks()){
									
									if(stock.getProduct().getSupplier().getName().equalsIgnoreCase(supplier.getName())){
										stocksList+="Product: "+stock.getProduct().getName()+"||Units: "+stock.getUnits();
										for(Order o: RetailSystem.getInstance().getOrders()){
												if(o.getProduct().getProductID().equalsIgnoreCase(stock.getProduct().getProductID())){
													stocksList +=" An order of "+o.getQuantity()+" units is expected "+dateFormat.format(o.getExpectedDeliveryDate())+"\n";
												}else{
													stocksList+="\n";
												}
										}
										details.setText(supplierName+stocksList);
										scrollPane.setViewportView(details);
										stockResults.add(scrollPane);
										MenuGUI.getInstance().setPanelAction(stockResults);
									}
									
								}
								
							}
							
							
							
						}
						
						
						
							}
							
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
}
