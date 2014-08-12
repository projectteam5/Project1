package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.RetailSystem;
import model.User;

public class LoginGUI extends JFrame {

	private JPanel panel;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setBounds(84, 42, 226, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_1 = new JLabel("Please insert your password");
		label_1.setBounds(84, 73, 226, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Please insert your identification number");
		label_2.setBounds(84, 17, 226, 14);
		panel.add(label_2);

		textField_2 = new JTextField();
		textField_2.setBounds(84, 98, 226, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JButton button = new JButton("Submit");
		button.setBounds(152, 148, 89, 23);
		panel.add(button);

		// insert an ActionListener in order to manage the access to the system
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = textField_1.getText();
				String password = textField_2.getText();
				if (id.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Please fill all the boxes.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					loginValidation(id, password);
					if(!RetailSystem.getInstance().getCurrentUserType().isEmpty()){
						MenuGUI menu = new MenuGUI();
					}
				}

			}
		});

		
		
		this.setVisible(true);

	}

	// Validation on id and password
	public void loginValidation(String id, String password) {
		for (User user : RetailSystem.getInstance().getUsers()) {
			if (user.getUserID().equals(id) && user.getPassword().equals(password)) {
				RetailSystem.getInstance().setCurrentUserType(user.getType());
				return;
			}
		}
		JOptionPane.showMessageDialog(null,
				"Login Failed!!!", "Error",
				JOptionPane.ERROR_MESSAGE);

	}


	

}
