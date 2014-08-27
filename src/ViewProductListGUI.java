//GUI done. Method working
//waly
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;


public class ViewProductListGUI extends JPanel{
	private JLabel title;
	private JPanel showProductPanel;
	private JScrollPane scrollPaneProducts;
	private Vector<Product> vet;
	private Vector<Product> vet2;
	private JTable table;
	

	public ViewProductListGUI() {
/*		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		this.setTitle("View All Products");
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));*/
		//this.setLayout(new BorderLayout());
		
		
		title = new JLabel("Product List. Amount of products: "+RetailSystem.getInstance().getProducts().size());
		title.setFont(new Font("Arial", Font.BOLD, 20));
		this.setLayout(new GridLayout(0,1));
		vet = new Vector<Product>(RetailSystem.getInstance().getProducts());
		TableModel dataModel = new ProductTable(vet); 
		table = new JTable(dataModel);
		scrollPaneProducts = new JScrollPane(table);
		this.add(scrollPaneProducts);
/*		JScrollPane scrollPaneProducts = new JScrollPane();
		JLabel title = new JLabel("Product List. Amount of products: "+RetailSystem.getInstance().getProducts().size());
		buttonMenu = new JButton("Menu");
		
		JPanel centralPanel = new JPanel(new GridLayout(0,1));
		centralPanel.setSize(100,100);

		for(Product product: RetailSystem.getInstance().getProducts()){
			if((product.isActive())&&(product.getSupplier().isActive())){
				JLabel label1 = new JLabel(product.getProductID()+" | "+product.getName()+" | "+product.getCost()+" | "+product.getMarkup()+" | "+product.getSupplier().getName());
				label1.setSize(10,10);
				//centralPanel.add(label);
				centralPanel.add(label1);
			}

		}	
		
			
/*		buttonMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ago0){
				ProductMenuGUI productMenuGUI = new ProductMenuGUI();
				closeViewProductListGUI();	
			}
		});*/
			
/*		scrollPaneProducts.setViewportView(centralPanel);
			
		panel.add(scrollPaneProducts,BorderLayout.CENTER);
		panel.add(title,BorderLayout.NORTH);
		container.add(panel);
		panel.add(buttonMenu,BorderLayout.SOUTH);
		this.setVisible(true);
			
		}
	
	public void closeViewProductListGUI(){
		this.setVisible(false);
	}*/
	}
		

}



