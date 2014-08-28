import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ShowUserGUI extends JPanel {

	private JComboBox usersDropDown;
	private JLabel labelTitleMain;
	private JLabel labelTitle;
	private JLabel labelName;
	private JLabel labelID;
	private JLabel labelPassword;
	private JLabel labelType;
	private JButton showButton;
	private JButton userMenuButton;
	private int returnValue;
	private String selectedUserID;
	private String selectedUserIDPrev;

	public ShowUserGUI() {

		this.setLayout(new GridLayout(0, 1));

		labelTitleMain = new JLabel("View User");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitle = new JLabel(
				"Please pick the user you want to see from the user list below");
		usersDropDown = new JComboBox();
		User.userListComplete(usersDropDown);
		showButton = new JButton("Show User");
		labelName = new JLabel("Name of the selected user:");
		labelID = new JLabel("ID of the selected user:");
		labelPassword = new JLabel("Password of the selected user:");
		labelType = new JLabel("Type of the selected user:");

		this.add(labelTitleMain);
		this.add(labelTitle);
		this.add(usersDropDown);
		this.add(showButton);
		this.add(labelName);
		this.add(labelID);
		this.add(labelPassword);
		this.add(labelType);
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);

		returnValue = 2;

		showButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedUserID = RetailSystem
						.returnIDfromCombobox(usersDropDown.getSelectedItem()
								.toString());
				if (returnValue == 2
						|| !selectedUserID.equals(selectedUserIDPrev)) {
					returnValue = showUserButton();
					selectedUserIDPrev = selectedUserID;
				}
			}
		});

	}

	public int showUserButton() {

		User selectedUser = User.retrieveUser(selectedUserID);
		if (selectedUser != null) {
			labelName.setText("Name of the selected user: "
					+ selectedUser.getName());
			labelID.setText("ID of the selected user: "
					+ selectedUser.getUserID());
			labelPassword.setText("Password of the selected user: "
					+ selectedUser.getPassword());
			labelType.setText("Type of the selected user: "
					+ selectedUser.getType());

			this.revalidate();
			this.repaint();
			return 0;
		} else {
			return 1;
		}

	}

}
