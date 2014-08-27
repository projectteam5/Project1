//test commit
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ProductMenuGUI extends JPanel{
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);
	
	private JButton buttonAddProduct;
	private JButton buttonRemoveProduct;
	private JButton buttonViewProduct;
	private JButton buttonViewProductList;
	private JButton buttonEditProduct;

	public ProductMenuGUI() {
		this.setLayout(new GridLayout(0,1));
		buttonAddProduct = new JButton("Add Product");
		buttonRemoveProduct = new JButton("Remove Product");
		buttonViewProduct = new JButton("View Product");
		buttonViewProductList = new JButton("View All Products");
		buttonEditProduct = new JButton("Edit Product");
		
		colourButton();
		
		if(RetailSystem.getInstance().getCurrentUserType().equalsIgnoreCase("Manager")){
			this.add(buttonAddProduct);
			this.add(buttonRemoveProduct);
			this.add(buttonViewProduct);
			this.add(buttonViewProductList);
			this.add(buttonEditProduct);
			
		}else{
			JOptionPane.showMessageDialog(null, "No access at attendant level!");
		}
		
		buttonAddProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				colourButton();
				buttonAddProduct.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new AddProductGUI());
			}
		});

		buttonRemoveProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				colourButton();
				buttonRemoveProduct.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new RemoveProductGUI());

			}
		});
		
		buttonViewProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				colourButton();
				buttonViewProduct.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new ViewProductGUI());
			}
		});
		
		buttonViewProductList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				colourButton();
				buttonViewProductList.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new ViewProductListGUI());
			}
		});
		
		buttonEditProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent argo0){
				colourButton();
				buttonEditProduct.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new EditProductGUI());
			}
		});
	
		this.setVisible(true);
	

		
	}
	public void closeProductMenuGUI(){
		this.setVisible(false);
	}
	
	public void colourButton(){
		buttonAddProduct.setBackground(colorButtons);
		buttonRemoveProduct.setFont(fontButtons);
		buttonRemoveProduct.setBackground(colorButtons);
		buttonRemoveProduct.setFont(fontButtons);
		buttonViewProduct.setBackground(colorButtons);
		buttonViewProduct.setFont(fontButtons);
		buttonViewProductList.setBackground(colorButtons);
		buttonViewProductList.setFont(fontButtons);
		buttonEditProduct.setBackground(colorButtons);
		buttonEditProduct.setFont(fontButtons);
	}


}
