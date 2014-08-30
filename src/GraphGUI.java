import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GraphGUI extends JPanel implements ActionListener {
	
	JLabel labelTitle;
	JButton viewOrderGraph;
	JButton viewSalesGraph;
	JButton viewStockGraph;
	
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);

	public GraphGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitle = new JLabel("Graph Menu");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		
		viewOrderGraph = new JButton("Products by Orders");
		viewSalesGraph = new JButton("Sales Current/Predicted");
		viewStockGraph = new JButton("Current Stock Levels");
		
		colorButton();
		
		this.add(labelTitle);
		this.add(viewOrderGraph);
		this.add(viewSalesGraph);
		this.add(viewStockGraph);
		
		viewSalesGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuGUI.getInstance().setPanelAction(new GraphPrediction());
				colorButton();
				viewSalesGraph.setBackground(colorButtonSelected);
			}
		});
		
		viewOrderGraph.addActionListener(this);
		
		viewStockGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuGUI.getInstance().setPanelAction(new GraphOfStock());
				colorButton();
				viewStockGraph.setBackground(colorButtonSelected);
			}
		});
		
		
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == viewOrderGraph) {
			try {
				
				MenuGUI.getInstance().setPanelAction(new GraphOfTopProductsFromOrders("",""));
				colorButton();
				viewOrderGraph.setBackground(colorButtonSelected);
				
				
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach createOrderGUI");
			}
		}
		
	}
	
	public void colorButton() {
		
		viewOrderGraph.setBackground(colorButtons);
		viewOrderGraph.setFont(fontButtons);
		viewStockGraph.setBackground(colorButtons);
		viewStockGraph.setFont(fontButtons);
		
	}
		
}