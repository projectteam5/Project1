import java.awt.Container;
import java.awt.Font;
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

public class EditUserGUI extends JPanel {

	private JTextField NameField;
	private JTextField PasswordField;
	private JComboBox typeDropDown;
	private JComboBox userDropDown;
	private JButton doneButton;
	private JButton editButton;
	private JLabel labelTitleMain;
	private JLabel labelTitle;
	private JLabel labelName;
	private JLabel labelPassword;
	private JLabel labelType;
	private String userID;
	private String selectedUserIDPrev;
	private User user;
	private int controlVariable;

	public EditUserGUI() {

		controlVariable = 0;
		selectedUserIDPrev = null;

		this.setLayout(new GridLayout(0, 1));

		// declaration of the labels and initialization of labels and text field
		labelTitleMain = new JLabel("Edit user");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitle = new JLabel(
				"Please pick the user you want to see from the user list below");
		userDropDown = new JComboBox();
		User.userListComplete(userDropDown);
		editButton = new JButton("Edit User");
		doneButton = new JButton("Commit");
		NameField = new JTextField("");
		PasswordField = new JTextField("");
		labelName = new JLabel("Name");
		labelPassword = new JLabel("Password");
		labelType = new JLabel("Type");
		typeDropDown = new JComboBox(RetailSystem.getInstance().getUserTypeList());
		
		

		// adding all the components
		this.add(labelTitleMain);
		this.add(labelTitle);
		this.add(userDropDown);
		this.add(editButton);
		this.add(labelName);
		this.add(NameField);
		this.add(labelPassword);
		this.add(PasswordField);
		this.add(labelType);
		this.add(typeDropDown);
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userID = RetailSystem.returnIDfromCombobox(userDropDown.getSelectedItem()
						.toString());
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
		}
		return returnValue;
	}

	public void populateFields() {
			controlVariable = 1;
		user = User.retrieveUser(userID);
		if (user != null) {
			NameField.setText(user.getName());
			PasswordField.setText(user.getPassword());
			typeDropDown.setSelectedItem(user.getType());
			this.add(doneButton);
			revalidate();
			repaint();
		}

	}
}
