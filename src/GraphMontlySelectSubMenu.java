import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GraphMontlySelectSubMenu extends JPanel{
	private JLabel title;
	private JLabel instruction;
	private JButton select;
	public static JComboBox<String> combobox;
	
	public GraphMontlySelectSubMenu() {
		this.setLayout(new GridLayout(0, 1));
		title = new JLabel("Monthly Option Menu");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		instruction = new JLabel("Choose a month");
		select = new JButton("Show Graph");
		combobox = new JComboBox<String>();
		fillComboBox();
		
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String monthlyID = combobox.getSelectedItem().toString();
				System.out.println(monthlyID);
				if(monthlyID.equals("January")){
					MenuGUI.getInstance().setPanelAction(new GraphJanuary("", ""));	
				}
				else if(monthlyID.equals("February")){
					MenuGUI.getInstance().setPanelAction(new GraphFebruary("", ""));
				}
				else if(monthlyID.equals("March")){
					MenuGUI.getInstance().setPanelAction(new GraphMarch("", ""));
				}
				else if(monthlyID.equals("April")){
					MenuGUI.getInstance().setPanelAction(new GraphApril("", ""));
				}
				else if(monthlyID.equals("May")){
					MenuGUI.getInstance().setPanelAction(new GraphMay("", ""));
				}
				else if(monthlyID.equals("June")){
					MenuGUI.getInstance().setPanelAction(new GraphJune("", ""));
				}
				else if(monthlyID.equals("July")){
					MenuGUI.getInstance().setPanelAction(new GraphJuly("", ""));
				}
				else if(monthlyID.equals("August")){
					MenuGUI.getInstance().setPanelAction(new GraphAugust("", ""));
				}
				else if(monthlyID.equals("September")){
					MenuGUI.getInstance().setPanelAction(new GraphSeptember("", ""));
				}
				else if(monthlyID.equals("October")){
					MenuGUI.getInstance().setPanelAction(new GraphOctober("", ""));
				}
				else if(monthlyID.equals("November")){
					MenuGUI.getInstance().setPanelAction(new GraphNovember("", ""));
				}
				else if(monthlyID.equals("December")){
					MenuGUI.getInstance().setPanelAction(new GraphDecember("", ""));
				}
			}
		});
		
		
		this.add(title);
		this.add(instruction);
		this.add(combobox);
		this.add(select);
		
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		
		setVisible(true);
	}
	
	// 
	public void fillComboBox(){
		combobox.addItem("January");
		combobox.addItem("February");
		combobox.addItem("March");
		combobox.addItem("April");
		combobox.addItem("May");
		combobox.addItem("June");
		combobox.addItem("July");
		combobox.addItem("August");
		combobox.addItem("July");
		combobox.addItem("September");
		combobox.addItem("October");
		combobox.addItem("November");
		combobox.addItem("December");
	}

}
