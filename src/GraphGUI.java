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
	JButton viewProductSalesGraph;
	JButton viewStockLevels;

	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);

	public GraphGUI() {

		this.setLayout(new GridLayout(0, 1));

		labelTitle = new JLabel("Graph Menu");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));

		viewProductOrderGraph = new JButton("Top Product Orders");
		viewSalesAndPredictionsGraph = new JButton("Sales & Predictions");
		viewProductSalesGraph = new JButton("Top Product Sales");
		viewStockLevels = new JButton("Stock Levels");

		colorButton();

		this.add(labelTitle);
		this.add(viewProductOrderGraph);
		this.add(viewProductSalesGraph);
		this.add(viewSalesAndPredictionsGraph);
		this.add(viewStockLevels);
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
	
		this.add(labelEmpty);
		this.add(labelEmpty1);

		viewProductSalesGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuGUI.getInstance().setPanelAction(
						new GraphOfTopSales("", ""));
				colorButton();
				viewProductSalesGraph.setBackground(colorButtonSelected);
			}
		});

		viewSalesAndPredictionsGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuGUI.getInstance().setPanelAction(new GraphPrediction());
				colorButton();
				viewSalesAndPredictionsGraph.setBackground(colorButtonSelected);
			}
		});
		viewStockLevels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuGUI.getInstance().setPanelAction(new GraphOfStock());
				colorButton();
				viewStockLevels.setBackground(colorButtonSelected);
			}
		});

		viewProductOrderGraph.addActionListener(this);

	}

	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();

		if (target == viewProductOrderGraph) {
			try {

				MenuGUI.getInstance().setPanelAction(
						new GraphOfTopProductsFromOrders("", ""));
				colorButton();
				viewProductOrderGraph.setBackground(colorButtonSelected);

			} catch (Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this,
						"cannot reach createOrderGUI");
			}
		}

	}

	public void colorButton() {

		viewProductOrderGraph.setBackground(colorButtons);
		viewProductOrderGraph.setFont(fontButtons);
		viewProductSalesGraph.setBackground(colorButtons);
		viewProductSalesGraph.setFont(fontButtons);
		viewSalesAndPredictionsGraph.setBackground(colorButtons);
		viewSalesAndPredictionsGraph.setFont(fontButtons);
		viewStockLevels.setBackground(colorButtons);
		viewStockLevels.setFont(fontButtons);
	}

}