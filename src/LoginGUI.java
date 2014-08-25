import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginGUI {

	private JFrame frame;
	private JPanel panel;
	private JTextField textFieldUser;
	private JPasswordField textFieldPassword;

	public LoginGUI() {
		// declaration and initialization of panel, container and layout setting
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setTitle("Login");
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Container container = frame.getContentPane();
		container.add(panel);
		panel.setLayout(new GridLayout(0, 1));

		// declaration of the labels and initialization of labels and text field
		JLabel label_1 = new JLabel("Please insert your identification number");
		textFieldUser = new JTextField();
		JLabel label_2 = new JLabel("Please insert your password");
		textFieldPassword = new JPasswordField();

		// declaration and initialization of button login
		JButton login = new JButton("Login");

		// adding all the components
		panel.add(label_1);
		panel.add(textFieldUser);
		panel.add(label_2);
		panel.add(textFieldPassword);
		panel.add(login);

		// insert an ActionListener in order to manage the access to the system
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = readingDataAndValidation();
				if (returnValue == 1) {
					JOptionPane.showMessageDialog(null,
							"Please fill all the boxes.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else if (returnValue == 2) {
					JOptionPane.showMessageDialog(null, "Login Failed!!!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		frame.setVisible(true);

	}

	// acquisition of the data, validation and opening of the menu gui
	public int readingDataAndValidation() {
		String id = textFieldUser.getText();
		String password = new String(textFieldPassword.getPassword());
		int returnValue = loginValidation(id, password);
		if (!RetailSystem.getInstance().getCurrentUserType().isEmpty()
				&& returnValue == 0) {
			MenuGUI.getInstance();
			frame.setVisible(false);
			return returnValue;
		} else {
			return returnValue;
		}
	}

	// Validation on id and password
	public static int loginValidation(String id, String password) {
		if (id.isEmpty() || password.isEmpty()) {
			return 1;
		} else {
			for (User user : RetailSystem.getInstance().getUsers()) {
				if (user.getUserID().equals(id)
						&& user.getPassword().equals(password) && user.isActive()) {
					RetailSystem.getInstance().setCurrentUserType(
							user.getType());
					RetailSystem.getInstance().setCurrentUserID(user.getUserID());
					return 0;
				}
			}
			return 2;
		}
	}

}
