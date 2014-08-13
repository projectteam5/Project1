
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerGUI extends JFrame {

	public CustomerGUI() {
		setSize(400, 400); // set frames size in pixels
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JTextField textField1 = new JTextField();
		JLabel label1 = new JLabel("Name");
		JTextField textField2 = new JTextField();
		JLabel label2 = new JLabel("Customer ID");
		JTextField textField3 = new JTextField();
		JLabel label3 = new JLabel("Phone Number");
		

		JButton but1 = new JButton("Add Customer");
		but1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Done");
				System.out.println(e.getActionCommand());

			}
		});
		
		JButton but2 = new JButton("Edit");
		but2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Done");
				System.out.println(e.getActionCommand());

			}
		});

		JButton but3 = new JButton("Delete");
		but3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Done");
				System.out.println(e.getActionCommand());

			}
		});

		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(0,1));
		jpanel.add(label1);
		jpanel.add(textField1);
		jpanel.add(label2);
		jpanel.add(textField2);
		jpanel.add(label3);
		jpanel.add(textField3);

		jpanel.add(but1);
		jpanel.add(but2);
		jpanel.add(but3);
		Container cp = getContentPane();
		cp.add(jpanel);
		setVisible(true);

	}

	//public static void main(String[] args) {
		//CustomerGUI gui = new CustomerGUI();

	//}

}
