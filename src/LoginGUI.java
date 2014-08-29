import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginGUI extends JFrame{

	private JFrame frame;
	private JPanel panel;
	private JTextField textFieldUser;
	private JPasswordField textFieldPassword;
	private JLabel label_1;
	private JLabel label_2;
	private JButton login;
	

	public LoginGUI() {
		// declaration and initialization of panel, container and layout setting
		//frame = new JFrame();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setTitle("Login");
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(120, 50, 120, 50));
		
		Container container = this.getContentPane();
		container.add(panel);
		
		//panel.setLayout(new GridLayout(0, 1));

		// declaration of the labels and initialization of labels and text field
		label_1 = new JLabel("Please insert your ID");
		label_1.setAlignmentX(CENTER_ALIGNMENT);
		textFieldUser = new JTextField();
		label_2 = new JLabel("Please insert your password");
		label_2.setAlignmentX(CENTER_ALIGNMENT);
		textFieldPassword = new JPasswordField();

		// declaration and initialization of button login
		login = new JButton("Login");
		login.setAlignmentX(CENTER_ALIGNMENT);
		// adding all the components
		panel.add(label_1);
		panel.add(textFieldUser);
		panel.add(label_2);
		panel.add(textFieldPassword);
		panel.add(login);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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

		this.setVisible(true);

	}

	// acquisition of the data, validation and opening of the menu gui
	public int readingDataAndValidation() {
		String id = textFieldUser.getText();
		String password = new String(textFieldPassword.getPassword());
		int returnValue = loginValidation(id, password);
		if (!RetailSystem.getInstance().getCurrentUserType().isEmpty()
				&& returnValue == 0) {
			MenuGUI.getInstance();
			this.setVisible(false);
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
