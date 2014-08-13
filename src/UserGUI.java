import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserGUI extends JFrame {

	public UserGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));
		JButton AddUser = new JButton("Add User");
		JButton EditUser = new JButton("Edit User");
		JButton DeleteUser = new JButton("Delete User");

		panel.add(AddUser);
		panel.add(EditUser);
		panel.add(DeleteUser);

		//Add button: it opens a new window where it's possible to insert 
		//user data and if the validation is correct the new user is created
		//and inserted in the ArrayList
		AddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUserGUI addUserGui = new AddUserGUI();
			}
		});
		
		
		EditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inputValue = JOptionPane.showInputDialog("Please insert a UserID"); 
				if(existingID(inputValue)){
					EditUserGUI gui = new EditUserGUI(inputValue);
				}
			}
		});

		this.setVisible(true);

	}
	
	public boolean existingID (String id){
		for(User user : RetailSystem.getInstance().getUsers()){
			if(user.getUserID().equals(id)){
				return true;
			}
		}
		return false;
	}

}
