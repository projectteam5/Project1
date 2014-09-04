import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserGUI extends JPanel {

	JButton addUser;
	JButton editUser;
	JButton deleteUser;
	JButton showUser;
	JButton reactivateUser;
	JLabel labelTitle;


	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);

	public UserGUI() {
		// declaration and initialization of buttons and labels
		this.setLayout(new GridLayout(0,1));
		labelTitle = new JLabel("User Menu");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitle.setAlignmentY(TOP_ALIGNMENT);
		
		addUser = new JButton("Add User");
		editUser = new JButton("Edit User");
		deleteUser = new JButton("Deactive User");
		showUser = new JButton("View User");
		reactivateUser = new JButton("Reactivate User");
		colorButton();

		// adding all the components
		this.add(labelTitle);
		this.add(addUser);
		this.add(editUser);
		this.add(deleteUser);
		this.add(reactivateUser);
		this.add(showUser);
		//fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		this.add(labelEmpty);
		//this.add(labelEmpty1);

		/*
		 * Add button: it opens a new window where it's possible to insert user
		 * data and if the validation is correct the new user is created and
		 * inserted in the ArrayList
		 */
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				addUser.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new AddUserGUI());
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
				colorButton();
				editUser.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new EditUserGUI());
			}
		});

		/*
		 * Show button: it opens a new window where it's possible to select the
		 * user from a dropdown list. Pressing the "Show User" button the data
		 * related to that user will be displayed in a read-only way
		 */
		showUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				showUser.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new ShowUserGUI());
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
				colorButton();
				deleteUser.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new DeleteUserGUI());
			}
		});
		
		/*
		 * Reactivate button: it opens a new window where it's possible to select
		 * the user from a dropdown list. After a user is selected it is
		 * possible to push the button "Reactivate User" and automatically the user
		 * will be reactivated
		 */
		reactivateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				reactivateUser.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new ReactivateUser());
			}
		});

	
	}

	public void colorButton(){
		addUser.setBackground(colorButtons);
		addUser.setFont(fontButtons);
		editUser.setBackground(colorButtons);
		editUser.setFont(fontButtons);
		showUser.setBackground(colorButtons);
		showUser.setFont(fontButtons);
		deleteUser.setBackground(colorButtons);
		deleteUser.setFont(fontButtons);
		reactivateUser.setBackground(colorButtons);
		reactivateUser.setFont(fontButtons);
	}

}
