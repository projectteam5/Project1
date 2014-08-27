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
	JButton viewGraph;
	
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);

	public GraphGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitle = new JLabel("Graph Menu");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		
		viewGraph = new JButton("View Graph");
		
		colorButton();
		
		this.add(labelTitle);
		this.add(viewGraph);
		
		viewGraph.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == viewGraph) {
			try {
				
				MenuGUI.getInstance().setPanelAction(new Graph("",""));
				colorButton();
				viewGraph.setBackground(colorButtonSelected);
				
				
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach createOrderGUI");
			}
		}
		
	}
	
	public void colorButton() {
		
		viewGraph.setBackground(colorButtons);
		viewGraph.setFont(fontButtons);
		
	}
		
}