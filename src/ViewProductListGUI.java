//GUI "done" but needs more work
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


public class ViewProductListGUI extends JFrame{

	public ViewProductListGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		//panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		
		JScrollPane scrollPaneProducts = new JScrollPane();
		JLabel title = new JLabel("Product List");
		
		JPanel centralPanel = new JPanel(new GridLayout(4,1));
		centralPanel.setSize(100,100);
		for(Product product: RetailSystem.getInstance().getProducts()){
			JLabel label = new JLabel(product.getProductID()+" | "+product.getName()+" | "+product.getCost()+" | "+product.getMarkup()+" | "+product.getSupplier().getName());
			//label.setFont(new Font("Times New Roman",20,20));
			label.setSize(10,10);
			centralPanel.add(label);
		}
			
			
		scrollPaneProducts.setViewportView(centralPanel);// puts the scroll pane on the central panel with the data
			
		panel.add(scrollPaneProducts,BorderLayout.CENTER);// puts the scrollpane which now contains the central panel into the center
		container.add(panel);
		this.setVisible(true);
			
		}
		
}


