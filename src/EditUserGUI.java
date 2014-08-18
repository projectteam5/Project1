import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditUserGUI extends JFrame {

	private JPanel panel;
	private JTextField NameField;
	private JTextField PasswordField;
	private JComboBox typeDropDown;
	private JComboBox userDropDown;
	private JButton doneButton;
	private JButton editButton;
	private JLabel labelName;
	private JLabel labelPassword;
	private JLabel labelType;
	private JLabel labelTitle;
	private String userID;
	private String selectedUserIDPrev;
	private User user;
	private int controlVariable;
	private JButton userMenuButton;

	public EditUserGUI() {

		controlVariable = 0;
		selectedUserIDPrev = null;

		// declaration and initialization of panel, container and layout setting
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));

		// declaration of the labels and initialization of labels and text field
		labelTitle = new JLabel(
				"Please pick the user you want to see from the user list below");
		userDropDown = new JComboBox();
		User.userListComplete(userDropDown);
		editButton = new JButton("Edit User");
		doneButton = new JButton("Commit");
		userMenuButton  = new JButton("User Menu");

		// adding all the components
		panel.add(labelTitle);
		panel.add(userDropDown);
		panel.add(editButton);
		panel.add(userMenuButton);

		Container container = getContentPane();
		container.add(panel);

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedUserString = userDropDown.getSelectedItem()
						.toString();
				String[] selectedUserArray = selectedUserString.split(";");
				String selectedUserIDString = selectedUserArray[0];
				String[] selectedUserIDArray = selectedUserIDString.split(":");
				userID = selectedUserIDArray[1].trim();
				if (controlVariable == 0 || !userID.equals(selectedUserIDPrev)) {
					populateFields();
					selectedUserIDPrev = userID;
				}
			}
		});

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = editUser();
				if (returnValue == 0) {
					JOptionPane.showMessageDialog(null,
							"User correctly updated", "Update",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"All fields must be filled!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		
		userMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserGUI userGui = new UserGUI();
				closeGUI();
				
			}
		});

		this.setVisible(true);
	}

	public int editUser() {
		int returnValue = 1;
		String name = NameField.getText();
		String password = PasswordField.getText();
		String type = typeDropDown.getSelectedItem().toString();
		if (name != null && password != null && type != null && name != null
				&& !password.isEmpty() && !type.isEmpty()) {
			user.setName(name);
			user.setPassword(password);
			user.setType(type);
			User.saveUser();
			returnValue = 0;
			this.setVisible(false);
		}
		return returnValue;
	}

	public void populateFields() {
		if (controlVariable != 0) {
			panel.remove(labelName);
			panel.remove(NameField);
			panel.remove(labelPassword);
			panel.remove(PasswordField);
			panel.remove(labelType);
			panel.remove(typeDropDown);
			panel.remove(doneButton);
		}
		controlVariable = 1;
		user = User.retrieveUser(userID);
		if (user != null) {
			NameField = new JTextField(user.getName());
			PasswordField = new JTextField(user.getPassword());
			labelName = new JLabel("Name");
			labelPassword = new JLabel("Password");
			labelType = new JLabel("Type");
			typeDropDown = new JComboBox(RetailSystem.getInstance()
					.getUserTypeList());
			typeDropDown.setSelectedItem(user.getType());
			panel.add(labelName);
			panel.add(NameField);
			panel.add(labelPassword);
			panel.add(PasswordField);
			panel.add(labelType);
			panel.add(typeDropDown);
			panel.add(doneButton);
			revalidate();
			repaint();
		}

	}
	public void closeGUI(){
		this.setVisible(false);
	}
}
