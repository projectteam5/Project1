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
import javax.swing.border.EmptyBorder;

public class DeleteUserGUI extends JFrame {

	private JPanel panel;
	private JComboBox usersDropDown;
	private JLabel labelTitle;
	private JButton deleteButton;
	private JButton userMenuButton;
	private String selectedUserID;
	private User userRemove;

	public DeleteUserGUI() {
		// declaration and initialization of panel, container, layout setting
		// and buttons
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		this.setTitle("Remove User");
		panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));

		labelTitle = new JLabel(
				"Please pick the user you want to remove from the user list below");
		usersDropDown = new JComboBox();
		buildUsersDropDown();
		deleteButton = new JButton("Remove User");
		userMenuButton = new JButton("User Menu");

		panel.add(labelTitle);
		panel.add(usersDropDown);
		panel.add(deleteButton);
		panel.add(userMenuButton);

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteUserButton();
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

	public void deleteUserButton() {
		userRemove = null;
		String selectedUserString = usersDropDown.getSelectedItem().toString();
		String[] selectedUserArray = selectedUserString.split(";");
		String selectedUserIDString = selectedUserArray[0];
		String[] selectedUserIDArray = selectedUserIDString.split(":");
		selectedUserID = selectedUserIDArray[1].trim();
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
		panel.remove(labelTitle);
		panel.remove(usersDropDown);
		panel.remove(deleteButton);
		panel.remove(userMenuButton);
		panel.add(labelTitle);
		usersDropDown = new JComboBox();
		buildUsersDropDown();
		panel.add(usersDropDown);
		panel.add(deleteButton);
		panel.add(userMenuButton);
		panel.revalidate();
		revalidate();
		repaint();
	}
	
	public void buildUsersDropDown(){
		for (User user : RetailSystem.getInstance().getUsers()){
			if(!user.getUserID().equals(RetailSystem.getInstance().getCurrentUserID()) && user.isActive()){
				String string = "ID: " + user.getUserID() + " ; Name: "
						+ user.getName();
				usersDropDown.addItem(string);
			}
		}
	}
	public void closeGUI(){
		this.setVisible(false);
	}

}
