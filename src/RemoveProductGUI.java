//GUI done. Method working
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
	private JButton buttonMenu;
	private JPanel panel;
	private JButton buttonDeleteProduct; 

	public RemoveProductGUI() {
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setSize(400, 200);
		//this.setTitle("Remove Product");
		//panel = new JPanel();
		//Container container = getContentPane();
		//container.add(panel);
		//panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0,1));	
		compileProductNames();
		
		buttonDeleteProduct = new JButton("Delete");
		buttonMenu = new JButton("Menu");
		
		this.add(productDropDown);
		this.add(buttonDeleteProduct);
		this.add(buttonMenu);
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
		
		buttonMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ago0){
				ProductMenuGUI productMenuGUI = new ProductMenuGUI();
				closeRemoveProductGUI();	
			}
		});
	}
	

	
	public void repopulateComboBox(){
		this.remove(productDropDown);
		this.remove(buttonDeleteProduct);
		this.remove(buttonMenu);
		compileProductNames();
		productDropDown = new JComboBox();
		compileProductNames();
		this.add(productDropDown);
		this.add(buttonDeleteProduct);
		this.add(buttonMenu);
		revalidate();
		repaint();
	}
	
	public void closeRemoveProductGUI(){
		this.setVisible(false);
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
