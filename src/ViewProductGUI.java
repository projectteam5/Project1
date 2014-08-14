//GUI working. Method working
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class ViewProductGUI extends JFrame{
	private JComboBox<String> productDropDown = new JComboBox<String>();

	public ViewProductGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 200);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		panel.setLayout(new GridLayout(0,1));	
		
		//Call method to populate combobox
		compileProductNames();
		//Add button to view all product information
		JButton buttonViewProduct = new JButton("View Product Details");
		JLabel title = new JLabel("Product Details");
		final JLabel label = new JLabel();
		final JLabel label1 = new JLabel();



		
		
		buttonViewProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				String name = productDropDown.getSelectedItem().toString();
				boolean found = false;
				for(Product product: RetailSystem.getInstance().getProducts()){
					if(name.equalsIgnoreCase(product.getName())){
						//Display the information for that product
						found = true;
						label.setText(product.getProductID()+" | "+product.getName()+" | "+product.getCost()+" | "+product.getMarkup()+" | "+product.getSupplier().getName());
						break;
					}
				}
				if(!found){
					JOptionPane.showMessageDialog(null, "No Product With This ID in System!");
				}
			}
		});
		
		panel.add(title,BorderLayout.NORTH);
		panel.add(productDropDown);
		panel.add(buttonViewProduct);
		panel.add(label);
		panel.add(label1);
		container.add(panel);
		this.setVisible(true);
		
	}
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			productDropDown.addItem(product.getName());
		}
	}
	

}
