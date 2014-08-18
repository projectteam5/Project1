//GUI done. Method working
//waly
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


public class ViewProductListGUI extends JFrame{
	private JButton buttonMenu;

	public ViewProductListGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		this.setTitle("View All Products");
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		
		JScrollPane scrollPaneProducts = new JScrollPane();
		JLabel title = new JLabel("Product List. Amount of products: "+RetailSystem.getInstance().getProducts().size());
		buttonMenu = new JButton("Menu");
		
		JPanel centralPanel = new JPanel(new GridLayout(0,1));
		centralPanel.setSize(100,100);
		for(Product product: RetailSystem.getInstance().getProducts()){
			if((product.isActive())&&(product.getSupplier().isActive())){
				JLabel label = new JLabel("Product ID"+"|"+"Product Name"+"|"+"Product Cost"+"|"+"Product Markup"+"|"+"Supplier Name");
				JLabel label1 = new JLabel(product.getProductID()+" | "+product.getName()+" | "+product.getCost()+" | "+product.getMarkup()+" | "+product.getSupplier().getName());
				label.setSize(10,10);
				centralPanel.add(label);
				centralPanel.add(label1);
			}

		}
			
		buttonMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ago0){
				ProductMenuGUI productMenuGUI = new ProductMenuGUI();
				closeViewProductListGUI();	
			}
		});
			
		scrollPaneProducts.setViewportView(centralPanel);
			
		panel.add(scrollPaneProducts,BorderLayout.CENTER);
		panel.add(title,BorderLayout.NORTH);
		container.add(panel);
		panel.add(buttonMenu,BorderLayout.SOUTH);
		this.setVisible(true);
			
		}
	
	public void closeViewProductListGUI(){
		this.setVisible(false);
	}
		
}


