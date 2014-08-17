import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StockGUI extends JFrame {

	/**
	 * 
	 */
	public StockGUI()  {
		this.setTitle("Stock Checker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,400);
		
		Container contentPane = this.getContentPane();
		
		JPanel optionsPanel = new JPanel();
		JPanel searchByProductPanel = new JPanel();
		JPanel stockListPanel = new JPanel();
		
		
		 JButton searchByProduct = new JButton("Search by Product");
		 JButton viewListOfStock = new JButton("View list of stock");
		 JButton menu = new JButton("Menu");
		
		optionsPanel.setLayout(new GridLayout(0,1));
		
		
		optionsPanel.add(searchByProduct);
		optionsPanel.add(viewListOfStock);
		optionsPanel.add(menu);
		
		
		//the search by product panel....
		
		searchByProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				new SearchByProductStockGUI();
				dispose();
			}
		});
		
		//if(optionsPanel.isVisible()==false){
		contentPane.add(optionsPanel);
		setVisible(true);
		//}
		
	}
	public static void main(String [] args){
		new StockGUI();
	}
	public void searchByProductGUI(){
		
		
		//end of search by product panel...
		
	}

}
