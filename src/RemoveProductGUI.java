//GUI done. Method working//test commit
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class RemoveProductGUI extends JPanel{
	private JComboBox<String> productDropDown = new JComboBox<String>();
	private JPanel panel;
	private JButton buttonDeleteProduct; 

	public RemoveProductGUI() {
		this.setLayout(new GridLayout(0,1));	
		compileProductNames();
		
		buttonDeleteProduct = new JButton("Delete");
		
		this.add(productDropDown);
		this.add(buttonDeleteProduct);
		this.setVisible(true);
		
		//When a user presses the delete button, the system will check which product they are trying to delete
		buttonDeleteProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				String name = productDropDown.getSelectedItem().toString();
				boolean found = false;
				for(Product product: RetailSystem.getInstance().getProducts()){
					if(name.equalsIgnoreCase(product.getName())){
						found = true;
						product.setActive(false);
						JOptionPane.showMessageDialog(null, "Product "+product.getName()+" has been removed from the system");
						saveProduct();
						repopulateComboBox();
						break;
					}
				}
				if(!found){
					JOptionPane.showMessageDialog(null, "No Product With This ID in System!");
				}
			}
		});
		
	}
	

	
	public void repopulateComboBox(){
		this.remove(productDropDown);
		this.remove(buttonDeleteProduct);
		compileProductNames();
		productDropDown = new JComboBox();
		compileProductNames();
		this.add(productDropDown);
		this.add(buttonDeleteProduct);
		revalidate();
		repaint();
	}
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			if(product.isActive()){
				productDropDown.addItem(product.getName());

			}
		}
	}
	
	public static void saveProduct(){
	       try {
	           FileWriter productFile;
	           productFile = new FileWriter("products.txt");
	           DataBase.writeProducts(RetailSystem.getInstance().getProducts(), productFile);
	           productFile.close();
	       } catch (Exception exception) {
	           exception.printStackTrace();
	       }
	   }


}
