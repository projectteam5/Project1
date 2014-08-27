import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SaleGUI extends JPanel {
	private JLabel productLabel;
	private JComboBox<String> productDropDown = new JComboBox<String>();
	private JLabel quantityLabel;
	private JTextField quantityField = new JTextField("", 10);
	
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);
	

	public SaleGUI() {
		this.setLayout(new GridLayout(0,1));
		compileProductNames();
		quantityField.setText("0");
		productLabel = new JLabel("Please choose products from the list below");
		quantityLabel = new JLabel("Please enter an amount");
		
		
		
		
		
		this.add(productLabel);
		this.add(productDropDown);
		this.add(quantityLabel);
		this.add(quantityField);
	}
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			if(product.isActive()){
				productDropDown.addItem(product.getName());

			}
		}
	}

}
