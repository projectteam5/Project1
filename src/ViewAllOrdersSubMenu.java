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
	JButton viewOrdersByDate;
	
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);

	public ViewAllOrdersSubMenu() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitle = new JLabel("Order Viewer");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		
		viewOrders = new JButton("View Orders");
		viewOrdersByDate = new JButton("Orders By Date");
		
		colorButton();
		
		this.add(labelTitle);
		this.add(viewOrders);
		this.add(viewOrdersByDate);
		//fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		JLabel labelEmpty4 = new JLabel(" ");
		JLabel labelEmpty5 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		this.add(labelEmpty4);
		this.add(labelEmpty5);
		
		viewOrders.addActionListener(this);
		viewOrdersByDate.addActionListener(this);
		
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
		
		if(target == viewOrdersByDate) {
			try {
				
				MenuGUI.getInstance().setPanelAction(new ViewAllOrdersByDateGUI());
				colorButton();
				viewOrdersByDate.setBackground(colorButtonSelected);
				
				
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach ViewAllOrdersByDateGUI");
			}
		}
		
	}
	
	public void colorButton() {
		
		viewOrders.setBackground(colorButtons);
		viewOrders.setFont(fontButtons);
		viewOrdersByDate.setBackground(colorButtons);
		viewOrdersByDate.setFont(fontButtons);
		
	}

}
