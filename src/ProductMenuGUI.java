import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ProductMenuGUI extends JFrame{

	public ProductMenuGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));
		JButton buttonAddProduct = new JButton("Add Product to System");
		JButton buttonRemoveProduct = new JButton("Remove Product From System");
		JButton buttonViewProduct = new JButton("View Single Product");
		JButton buttonViewProductList = new JButton("View All Products in System");
		JButton buttonEditProduct = new JButton("Edit Product");
		
		if(RetailSystem.getInstance().getCurrentUserType().equalsIgnoreCase("Manager")){
			panel.add(buttonAddProduct);
			panel.add(buttonRemoveProduct);
			panel.add(buttonViewProduct);
			panel.add(buttonViewProductList);
			panel.add(buttonEditProduct);
			
		}else{
			JOptionPane.showMessageDialog(null, "No access at attendant level!");
		}
		
		buttonAddProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				AddProductGUI addProductGUI = new AddProductGUI();
			}
		});
		
		buttonRemoveProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				RemoveProductGUI removeProductGUI = new RemoveProductGUI();
			}
		});
		
		buttonViewProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				ViewProductGUI viewProductGUI = new ViewProductGUI();
			}
		});
		
		buttonViewProductList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				ViewProductListGUI viewProductListGUI = new ViewProductListGUI();
			}
		});
		
		buttonEditProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				EditProductGUI editProductGUI = new EditProductGUI();
			}
		});
		
		
		this.setVisible(true);
		
		
	}

}
