import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class ViewProductGUI extends JFrame{
	private JComboBox<String> productDropDown = new JComboBox<String>();

	public ViewProductGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));	
		
		//Call method to populate combobox
		compileProductNames();
		//Add button to view all product information
		JButton buttonViewProduct = new JButton("View Product Details");
		//Add to panel
		panel.add(productDropDown);
		panel.add(buttonViewProduct);
		this.setVisible(true);
		
	}
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			productDropDown.addItem(product.getName()
			+ " | " + product.getProductID());
		}
	}

}
