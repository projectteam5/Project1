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
	private JLabel label;
	private JLabel label1;
	private JButton buttonViewProduct;

	public ViewProductGUI() {
		this.setLayout(new GridLayout(0,1));	
		compileProductNames();
		buttonViewProduct = new JButton("View Product Details");
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
		
		this.add(productDropDown);
		this.add(buttonViewProduct);
		this.add(label);
		this.add(label1);
		this.setVisible(true);
		
	}
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			if(product.isActive()){
				productDropDown.addItem(product.getName());

			}
		}
	}
	
	

}
