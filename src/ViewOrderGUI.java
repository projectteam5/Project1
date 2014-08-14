import java.awt.*;
import java.awt.event.ActionEvent;	//class
import java.awt.event.ActionListener;	//interface

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ViewOrderGUI extends JFrame implements ActionListener {
	private JPanel panel;
	private Container container;

	public ViewOrderGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle(this.getClass().toString());
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);	//null sets the frame to centre
		
		panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(10,25));

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
	}
}