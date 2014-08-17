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
	private JButton buttonMenu;

	public ViewProductGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		this.setTitle("View Product");
		JPanel panel = new JPanel();
		Container container = getContentPane();
		panel.setLayout(new GridLayout(0,1));	
		compileProductNames();
		
		JButton buttonViewProduct = new JButton("View Product Details");
		//JLabel title = new JLabel("Product Details");
		buttonMenu = new JButton("Menu");
		final JLabel label = new JLabel();
		final JLabel label1 = new JLabel();



		
		
		buttonViewProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				String name = productDropDown.getSelectedItem().toString();
				boolean found = false;
				for(Product product: RetailSystem.getInstance().getProducts()){
					if(name.equalsIgnoreCase(product.getName())){
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
		
		buttonMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ago0){
				ProductMenuGUI productMenuGUI = new ProductMenuGUI();
				closeViewProductGUI();	
			}
		});
		
		//panel.add(title,BorderLayout.NORTH);
		panel.add(productDropDown);
		panel.add(buttonViewProduct);
		panel.add(label);
		panel.add(label1);
		container.add(panel);
		panel.add(buttonMenu);
		this.setVisible(true);
		
	}
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			productDropDown.addItem(product.getName());
		}
	}
	
	public void closeViewProductGUI(){
		this.setVisible(false);
	}
	

}
