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


public class RemoveProductGUI extends JFrame{
	private JComboBox<String> productDropDown = new JComboBox<String>();
	private JButton buttonMenu;

	public RemoveProductGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		this.setTitle("Remove Product");
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));	
		compileProductNames();
		
		JButton buttonDeleteProduct = new JButton("Delete");
		buttonMenu = new JButton("Menu");
		
		panel.add(productDropDown);
		panel.add(buttonDeleteProduct);
		panel.add(buttonMenu);
		this.setVisible(true);
		
		//When a user presses the delete button, the system will check which product they are trying to delete
		buttonDeleteProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				String name = productDropDown.getSelectedItem().toString();
				boolean found = false;
				for(Product product: RetailSystem.getInstance().getProducts()){
					if(name.equalsIgnoreCase(product.getName())){
						found = true;
						RetailSystem.getInstance().getProducts().remove(product);
						JOptionPane.showMessageDialog(null, "Product "+product.getName()+" has been removed from the system");
						saveProduct();
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
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			productDropDown.addItem(product.getName());
		}
	}
	
	public void closeRemoveProductGUI(){
		this.setVisible(false);
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
