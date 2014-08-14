//GUI done. Need to add method to delete product when button pressed
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class RemoveProductGUI extends JFrame{
	private JComboBox<String> productDropDown = new JComboBox<String>();

	public RemoveProductGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));	
		
		//Call method to populate combobox
		compileProductNames();
		//Add delete button
		JButton buttonDelete = new JButton("Delete");
		//Add to panel
		panel.add(productDropDown);
		panel.add(buttonDelete);
		this.setVisible(true);
		
		//When a user presses the delete button, the system will check which product they are trying to delete
/*		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
				
			}
		*/
	}
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			productDropDown.addItem(product.getName()
			+ " | " + product.getProductID());
		}
	}
	


}
