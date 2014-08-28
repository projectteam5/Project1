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
import javax.swing.border.EmptyBorder;

public class DeleteUserGUI extends JPanel {

	private JComboBox usersDropDown;
	private JLabel labelTitleMain;
	private JLabel labelTitle;
	private JButton deleteButton;
	private JButton userMenuButton;
	private String selectedUserID;
	private User userRemove;

	public DeleteUserGUI() {
		// declaration and initialization of panel, container, layout setting
		// and buttons
		
		this.setLayout(new GridLayout(0, 1));

		labelTitleMain = new JLabel("Remove a user");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitle = new JLabel(
				"Please pick the user you want to remove from the user list below");
		usersDropDown = new JComboBox();
		buildUsersDropDown();
		deleteButton = new JButton("Remove User");

		this.add(labelTitle);
		this.add(usersDropDown);
		this.add(deleteButton);

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteUserButton();
			}

		});
		
	}

	public void deleteUserButton() {
		userRemove = null;
		selectedUserID = Customer.returnIDfromCombobox(usersDropDown.getSelectedItem().toString());
		userRemove = User.retrieveUser(selectedUserID);
		if (userRemove != null) {
			userRemove.setActive(false);
			JOptionPane.showMessageDialog(null, "User correctly removed",
					"Correctly Done", JOptionPane.INFORMATION_MESSAGE);
			User.saveUser();
			addAndRefresh();
		}

	}

	public void addAndRefresh() {
		usersDropDown.removeAllItems();
		buildUsersDropDown();
	}
	
	public void 
	buildUsersDropDown(){
		for (User user : RetailSystem.getInstance().getUsers()){
			if(!user.getUserID().equals(RetailSystem.getInstance().getCurrentUserID()) && user.isActive()){
				String string = "ID: " + user.getUserID() + " ; Name: "
						+ user.getName();
				usersDropDown.addItem(string);
			}
		}
	}

}
