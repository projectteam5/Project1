//GUI done. Method working
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
		//Title not showing
		JLabel title = new JLabel("Product List. Amount of products: "+RetailSystem.getInstance().getProducts().size());
		
		JPanel centralPanel = new JPanel(new GridLayout(0,1));
		centralPanel.setSize(100,100);
		for(Product product: RetailSystem.getInstance().getProducts()){
			JLabel label = new JLabel(product.getProductID()+" | "+product.getName()+" | "+product.getCost()+" | "+product.getMarkup()+" | "+product.getSupplier().getName());
			label.setSize(10,10);
			centralPanel.add(label);
		}
			
			
		scrollPaneProducts.setViewportView(centralPanel);
			
		panel.add(scrollPaneProducts,BorderLayout.CENTER);
		panel.add(title,BorderLayout.NORTH);
		container.add(panel);
		this.setVisible(true);
			
		}
		
}


