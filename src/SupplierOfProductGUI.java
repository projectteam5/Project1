import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SupplierOfProductGUI extends JFrame{
	private JTextField textField;
	private JLabel label;
	private JButton button;
	private JButton menuButton;
	private Product productTest;

	public SupplierOfProductGUI() {
		setTitle("LIST OF SUPPLIERS");
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		textField = new JTextField();
		label = new JLabel("Enter Product ID");	
		button = new JButton("Confirm");
		menuButton = new JButton("Supplier Menu");
		
		// Action listener first checks field is filled in, then sends entry to function to find and show supplier
		// name or returns a message saying the product does not exist.
		button.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				String productID = 	textField.getText();
				if(productID.isEmpty()){
					JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(showProductSupplier(productID)){
				}
				else{
					JOptionPane.showMessageDialog(null, "Product does not exist!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		menuButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				SupplierMenuGUI supplierMenuGUI = new SupplierMenuGUI();
				closeSupplierMenuGUI();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(textField);
		panel.add(label);
		panel.add(button);
		panel.add(menuButton);
		Container cp = getContentPane();
		cp.add(panel);
		setVisible(true);
	}
	
	public boolean showProductSupplier(String productID){
		boolean correct = false;
		for(Product product: RetailSystem.getInstance().getProducts()){
			if(product.getProductID().equals(productID)){
				JOptionPane.showMessageDialog(null, "This product is supplied by: "+product.getSupplier().getName(), "Success", JOptionPane.PLAIN_MESSAGE);
				correct =true;
			}
		}
		return correct;
	}
	
	public void closeSupplierMenuGUI(){
		setVisible(false);
	}
}
