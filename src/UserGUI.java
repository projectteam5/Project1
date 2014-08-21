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
		setSize(RetailSystem.getInstance().getWidthMain(), RetailSystem.getInstance().getHeightMain());
		this.setTitle("User Menu");
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));
		JButton addUser = new JButton("Add User");
		JButton editUser = new JButton("Edit User");
		JButton deleteUser = new JButton("Remove User");
		JButton showUser = new JButton("Show User");
		JButton menuUser = new JButton("Menu");

		// adding all the components
		panel.add(addUser);
		panel.add(editUser);
		panel.add(showUser);
		panel.add(deleteUser);
		panel.add(menuUser);

		/*
		 * Add button: it opens a new window where it's possible to insert user
		 * data and if the validation is correct the new user is created and
		 * inserted in the ArrayList
		 */
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUserGUI addUserGui = new AddUserGUI();
				closeUserGUI();
			}
		});

		/*
		 * Edit button: it opens a new window where it's possible to select the
		 * user from a dropdown list. After a user is selected it is possible to
		 * push the button "Edit User" and automatically will be displayed
		 * fields with the user data in it. It is possible to modify the data
		 * and save it with the "Commit" button.
		 */
		editUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditUserGUI gui = new EditUserGUI();
				closeUserGUI();
			}
		});

		/*
		 * Show button: it opens a new window where it's possible to select the
		 * user from a dropdown list. Pressing the "Show User" button the data
		 * related to that user will be displayed in a read-only way
		 */
		showUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowUserGUI showUserGui = new ShowUserGUI();
				closeUserGUI();
			}
		});

		/*
		 * Delete button: it opens a new window where it's possible to select
		 * the user from a dropdown list. After a user is selected it is
		 * possible to push the button "Delete User" and automatically the user
		 * will be removed
		 */
		deleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteUserGUI deleteUserGui = new DeleteUserGUI();
				closeUserGUI();
			}
		});

		/*
		 * Menu button: it closes the User menu and open a General Menu
		 */
		menuUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuGUI menuGui = new MenuGUI();
				closeUserGUI();
			}
		});

		this.setVisible(true);
	}

	public void closeUserGUI() {
		this.setVisible(false);
	}

}
