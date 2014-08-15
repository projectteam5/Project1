import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserGUI extends JFrame {

	public UserGUI() {
		// declaration and initialization of panel, container, layout setting
		// and buttons
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));
		JButton addUser = new JButton("Add User");
		JButton editUser = new JButton("Edit User");
		JButton deleteUser = new JButton("Delete User");
		JButton showUser = new JButton("Show User");
		JButton saveUser = new JButton("Save");
		JButton menuUser = new JButton("Menu");

		// adding all the components
		panel.add(addUser);
		panel.add(editUser);
		panel.add(showUser);
		panel.add(deleteUser);
		panel.add(saveUser);
		panel.add(menuUser);

		/*
		 * Add button: it opens a new window where it's possible to insert user
		 * data and if the validation is correct the new user is created and
		 * inserted in the ArrayList
		 */
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addUserButton();
			}
		});

		/*
		 * Edit button: it asks to insert the userID of the user that has to be
		 * modified and if the validation of the userID is correct, another
		 * window is opened displaying the existing data for that user. If the
		 * validation is not satisfied, an error message is shown
		 */
		editUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editUserButton();
			}
		});

		/*
		 * Delete button: it asks to insert the userID of the user that has to
		 * be deleted. A validation on the existence of the inserted userID is
		 * performed. In addition it is not possible to delete the current
		 * userID. If the validation is not satisfied, an error message is shown
		 */
		showUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showUserButton();
			}
		});

		/*
		 * Delete button: it asks to insert the userID of the user that has to
		 * be deleted. A validation on the existence of the inserted userID is
		 * performed. In addition it is not possible to delete the current
		 * userID. If the validation is not satisfied, an error message is shown
		 */
		deleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = deleteUserButton();
				if (returnValue == 1) {
					JOptionPane.showMessageDialog(null,
							"You cannot delete this ID!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"User correctly removed", "Correctly Done",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		saveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveUserButton();
			}
		});
		
		menuUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveUserButton();
			}
		});
		

		this.setVisible(true);

	}

	public static boolean existingID(String id) {
		for (User user : RetailSystem.getInstance().getUsers()) {
			if (user.getUserID().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static boolean notLoginID(String id) {
		if (RetailSystem.getInstance().getCurrentUserID().equals(id)) {
			return false;
		}
		return true;
	}

	public void addUserButton() {
		AddUserGUI addUserGui = new AddUserGUI();
	}

	public void editUserButton() {
			EditUserGUI gui = new EditUserGUI();
			this.setVisible(false);
	}

	public void showUserButton() {
		ShowUserGUI showUserGui = new ShowUserGUI();
	}

	public int deleteUserButton() {
		String inputValue = JOptionPane
				.showInputDialog("Please insert a UserID");
		if (existingID(inputValue) && notLoginID(inputValue)) {
			User userRemove = null;
			for (User user : RetailSystem.getInstance().getUsers()) {
				if (user.getUserID().equals(inputValue)) {
					userRemove = user;
				}
			}
			RetailSystem.getInstance().getUsers().remove(userRemove);
			return 0;
		} else {
			return 1;
		}

	}

	public void SaveUserButton() {
		try {
			FileWriter userFile;
			userFile = new FileWriter("users.txt");
			DataBase.writeUsers(RetailSystem.getInstance().getUsers(), userFile);
			userFile.close();// close the user file
			JOptionPane.showMessageDialog(null, "Data have been saved!", "Notification", JOptionPane.INFORMATION_MESSAGE );
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
	
	public void MenuUserButton() {
		MenuGUI menuGui = new MenuGUI();
		this.setVisible(false);
	}
}
