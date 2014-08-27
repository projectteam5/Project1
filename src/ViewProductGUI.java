//GUI working. Method working//test commit
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class ViewProductGUI extends JPanel{
	private JComboBox<String> productDropDown = new JComboBox<String>();
	//private JButton buttonMenu;
	private JLabel label;
	private JLabel label1;
	private JButton buttonViewProduct;

	public ViewProductGUI() {
/*		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		this.setTitle("View Product");
		JPanel panel = new JPanel();
		Container container = getContentPane();*/
		this.setLayout(new GridLayout(0,1));	
		compileProductNames();
		
		buttonViewProduct = new JButton("View Product Details");
		//buttonMenu = new JButton("Menu");
		label = new JLabel();
		label1 = new JLabel();



		
		
		buttonViewProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				String name = productDropDown.getSelectedItem().toString();
				boolean found = false;
				for(Product product: RetailSystem.getInstance().getProducts()){
					if(name.equalsIgnoreCase(product.getName())){
						found = true;
						label.setText("Product ID"+"|"+"Product Name"+"|"+"Product Cost"+"|"+"Product Markup"+"|"+"Supplier Name");
						label1.setText(product.getProductID()+" | "+product.getName()+" | "+product.getCost()+" | "+product.getMarkup()+" | "+product.getSupplier().getName());
						break;
					}
				}
				if(!found){
					JOptionPane.showMessageDialog(null, "No Product With This ID in System!");
				}
			}
		});
		
/*		buttonMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ago0){
				ProductMenuGUI productMenuGUI = new ProductMenuGUI();
				closeViewProductGUI();	
			}
		});
		*/
		//panel.add(title,BorderLayout.NORTH);
		this.add(productDropDown);
		this.add(buttonViewProduct);
		this.add(label);
		this.add(label1);
		//container.add(panel);
		//this.add(buttonMenu);
		this.setVisible(true);
		
	}
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			if(product.isActive()){
				productDropDown.addItem(product.getName());

			}
		}
	}
	
	public void closeViewProductGUI(){
		this.setVisible(false);
	}
	

}
