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
public class ViewAllOrdersSubMenu extends JPanel implements ActionListener {
	
	JLabel labelTitle;
	JButton viewOrders;
	
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);

	public ViewAllOrdersSubMenu() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitle = new JLabel("Order Viewer");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		
		viewOrders = new JButton("View Orders");
		
		colorButton();
		
		this.add(labelTitle);
		this.add(viewOrders);
		
		viewOrders.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == viewOrders) {
			try {
				
				MenuGUI.getInstance().setPanelAction(new ViewAllOrdersGUI());
				colorButton();
				viewOrders.setBackground(colorButtonSelected);
				
				
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach ViewAllOrdersGUI");
			}
		}
		
	}
	
	public void colorButton() {
		
		viewOrders.setBackground(colorButtons);
		viewOrders.setFont(fontButtons);
		
	}

}
