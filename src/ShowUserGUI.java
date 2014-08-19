import java.awt.Container;
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

public class ShowUserGUI extends JFrame {

	private JPanel panel;
	private JComboBox usersDropDown;
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
		// declaration and initialization of panel, container, layout setting
		// and buttons
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		this.setTitle("Show User");
		panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));

		labelTitle = new JLabel(
				"Please pick the user you want to see from the user list below");
		usersDropDown = new JComboBox();
		User.userListComplete(usersDropDown);
		showButton = new JButton("Show User");
		userMenuButton  = new JButton("User Menu");

		panel.add(labelTitle);
		panel.add(usersDropDown);
		panel.add(showButton);
		panel.add(userMenuButton);

		returnValue = 2;

		showButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedUserString = usersDropDown.getSelectedItem()
						.toString();
				String[] selectedUserArray = selectedUserString.split(";");
				String selectedUserIDString = selectedUserArray[0];
				String[] selectedUserIDArray = selectedUserIDString.split(":");
				selectedUserID = selectedUserIDArray[1].trim();
				if (returnValue == 2
						|| !selectedUserID.equals(selectedUserIDPrev)) {
					returnValue = showUserButton();
					selectedUserIDPrev = selectedUserID;
				}
			}
		});
		
		userMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserGUI userGui = new UserGUI();
				closeGUI();
				
			}
		});

		setVisible(true);
	}

	public int showUserButton() {
		// retrieve the user object in the user ArrayList
		if (returnValue != 2) {
			panel.remove(labelName);
			panel.remove(labelID);
			panel.remove(labelPassword);
			panel.remove(labelType);
		}
		User selectedUser = User.retrieveUser(selectedUserID);
		if (selectedUser != null) {
			labelName = new JLabel("Name of the selected user: "
					+ selectedUser.getName());
			labelID = new JLabel("ID of the selected user: "
					+ selectedUser.getUserID());
			labelPassword = new JLabel("Password of the selected user: "
					+ selectedUser.getPassword());
			labelType = new JLabel("Type of the selected user: "
					+ selectedUser.getType());
			panel.add(labelName);
			panel.add(labelID);
			panel.add(labelPassword);
			panel.add(labelType);
			panel.revalidate();
			panel.repaint();
			return 0;
		} else {
			return 1;
		}

	}
	public void closeGUI(){
		this.setVisible(false);
	}

}
