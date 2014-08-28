import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StockGUI extends JPanel {
	
	JButton searchByProduct = new JButton("Search by Product");
	JButton viewListOfStock = new JButton("View list of stock");
	JLabel title;
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);
	public StockGUI()  {
		
		this.setLayout(new GridLayout(0,1));
		//this.setSize(400,400);
		title = new JLabel("Stock Viewer");	
		add(title);
		add(searchByProduct);
		add(viewListOfStock);
		
		
		
		searchByProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				searchByProduct.setBackground(colorButtonSelected);
				viewListOfStock.setBackground(colorButtons);
				MenuGUI.getInstance().setPanelAction(new SearchByProductStockGUI());
				//dispose();
			}
		});
		
		viewListOfStock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				viewListOfStock.setBackground(colorButtonSelected);
				searchByProduct.setBackground(colorButtons);
				MenuGUI.getInstance().setPanelAction(new AvailableStockLevelsGUI());
				//dispose();
			}
		});
		
		colorButton();
		setVisible(true);

		
	}
	public void colorButton(){
		searchByProduct.setBackground(colorButtons);
		searchByProduct.setFont(fontButtons);
		viewListOfStock.setBackground(colorButtons);
		viewListOfStock.setFont(fontButtons);
	}

}
