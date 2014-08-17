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
		this.setTitle("Product Menu");
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
		JButton buttonMainMenu = new JButton("Main Menu");
		
		if(RetailSystem.getInstance().getCurrentUserType().equalsIgnoreCase("Manager")){
			panel.add(buttonAddProduct);
			panel.add(buttonRemoveProduct);
			panel.add(buttonViewProduct);
			panel.add(buttonViewProductList);
			panel.add(buttonEditProduct);
			panel.add(buttonMainMenu);
			
		}else{
			JOptionPane.showMessageDialog(null, "No access at attendant level!");
		}
		
		buttonAddProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				AddProductGUI addProductGUI = new AddProductGUI();
				closeProductMenuGUI();
			}
		});
		
		buttonRemoveProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				RemoveProductGUI removeProductGUI = new RemoveProductGUI();
				closeProductMenuGUI();
			}
		});
		
		buttonViewProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				ViewProductGUI viewProductGUI = new ViewProductGUI();
				closeProductMenuGUI();
			}
		});
		
		buttonViewProductList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				ViewProductListGUI viewProductListGUI = new ViewProductListGUI();
				closeProductMenuGUI();
			}
		});
		
		buttonEditProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				EditProductGUI editProductGUI = new EditProductGUI();
				closeProductMenuGUI();
			}
		});
		
		buttonMainMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				MenuGUI menuGUI = new MenuGUI();
				closeProductMenuGUI();
			}
		});
		
		
		this.setVisible(true);
	

		
	}
	public void closeProductMenuGUI(){
		this.setVisible(false);
	}


}
