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
	JButton viewProductOrderGraph;
	JButton viewSalesAndPredictionsGraph;
	
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);

	public GraphGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitle = new JLabel("Graph Menu");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		
		viewProductOrderGraph = new JButton("Top Product Orders");
		viewSalesAndPredictionsGraph = new JButton("Sales & Predictions");
		
		colorButton();
		
		this.add(labelTitle);
		this.add(viewProductOrderGraph);
		this.add(viewSalesAndPredictionsGraph);
		
		viewSalesAndPredictionsGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuGUI.getInstance().setPanelAction(new GraphPrediction());
				colorButton();
				viewSalesAndPredictionsGraph.setBackground(colorButtonSelected);
			}
		});
		
		viewProductOrderGraph.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == viewProductOrderGraph) {
			try {
				
				MenuGUI.getInstance().setPanelAction(new GraphOfTopProductsFromOrders("",""));
				colorButton();
				viewProductOrderGraph.setBackground(colorButtonSelected);
				
				
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach createOrderGUI");
			}
		}
		
	}
	
	public void colorButton() {
		
		viewProductOrderGraph.setBackground(colorButtons);
		viewProductOrderGraph.setFont(fontButtons);
		viewSalesAndPredictionsGraph.setBackground(colorButtons);
		viewSalesAndPredictionsGraph.setFont(fontButtons);
		
	}
		
}