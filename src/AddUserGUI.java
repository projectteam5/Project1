import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddUserGUI extends JPanel {

	private JTextField textUserID;
	private JTextField textUserName;
	private JTextField textUserPass;
	private JComboBox typeDropDown;
	private JLabel labelTitle;
	private JLabel labelUserID;
	private JLabel labelUserName;
	private JLabel labelUserPass;
	private JLabel labelUserType;
	private JButton doneButton;
	private String userID;

	public AddUserGUI() {
		// declaration and initialization of panel, container and layout setting

		this.setLayout(new GridLayout(0,1));

		// declaration of the labels and initialization of labels and text field
		textUserName = new JTextField();
		textUserPass = new JTextField();
		typeDropDown = new JComboBox(RetailSystem.getInstance()
				.getUserTypeList());
		labelTitle = new JLabel("Add user");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelUserName = new JLabel("Name");
		labelUserPass = new JLabel("Password");
		labelUserType = new JLabel("Type");
		doneButton = new JButton("Add");
		

		// adding all the components
		this.add(labelTitle);
		this.add(labelUserName);
		this.add(textUserName);
		this.add(labelUserPass);
		this.add(textUserPass);
		this.add(labelUserType);
		this.add(typeDropDown);
		this.add(doneButton);
		
		//fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);

		// Define the panel for the User management

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = addUser();
				if (returnValue == 0) {
					JOptionPane.showMessageDialog(null,
							"User correctly inserted with ID: "+userID, "Inserted",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"ID already exists or missing fields", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

	}

	public int addUser() {
		String name = textUserName.getText();
		String password = textUserPass.getText();
		String type = typeDropDown.getSelectedItem().toString();
		if (validateUser(name, password, type)) {
			User user = new User(name, password, type);
			userID = user.getUserID();
			RetailSystem.getInstance().getUsers().add(user);
			//clean the textField
			textUserName.setText("");
			textUserPass.setText("");
			User.saveUser();
			return 0;
		} else {
			return 1;
		}
	}

	public static boolean validateUser(String name, String password, String type) {
		boolean userOk = false;
		if (name != null && password != null && type != null && !name.isEmpty()
				&& !password.isEmpty() && !type.isEmpty()) {
			userOk = true;
		}
		return userOk;
	}

}
