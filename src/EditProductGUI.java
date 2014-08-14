//GUI done. Method working
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class EditProductGUI extends JFrame{
	private JComboBox<String> productDropDown = new JComboBox<String>();
	private static Product chosenEditProduct;

	public EditProductGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));
		compileProductNames();
		
		JButton buttonEditProduct = new JButton("Edit Product");
		
		panel.add(productDropDown);
		panel.add(buttonEditProduct);
		this.setVisible(true);
		
		buttonEditProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				String name = productDropDown.getSelectedItem().toString();
				boolean found = false;
				for(Product product: RetailSystem.getInstance().getProducts()){
					if(name.equalsIgnoreCase(product.getName())){
						//If found in the list of products, open new window
						found = true;
						chosenEditProduct = product;
						EditingProductGUI editingProductGUI = new EditingProductGUI();
						break;
					}
				}
				if(!found){
					JOptionPane.showMessageDialog(null, "No Product With This ID in System!");
				}
			}
		});
		
	}
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			productDropDown.addItem(product.getName());
		}
	}

	public static Product getChosenEditProduct() {
		return chosenEditProduct;
	}

	public void setChosenEditProduct(Product chosenEditProduct) {
		this.chosenEditProduct = chosenEditProduct;
	}

}
