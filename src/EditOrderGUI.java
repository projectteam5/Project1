import java.awt.*;
import java.awt.event.ActionEvent;	//class
import java.awt.event.ActionListener;	//interface

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EditOrderGUI extends JFrame implements ActionListener {
	private JPanel panel;
	private Container container;

	public EditOrderGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		setTitle(this.getClass().toString());
		
		panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(25,25));
		
		setVisible(true);
	}
	
	public void actionEvent(ActionEvent event) {
		Object target = new Object();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}