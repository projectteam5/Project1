import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReactivateUser extends JPanel {
	private JLabel title;
	private JLabel userLabel;
	private JComboBox<String> userDropDown = new JComboBox<String>();
	private JButton buttonReactivate;
	private User pickedUser;
	// fixing the layout
	JLabel labelEmpty = new JLabel(" ");
	JLabel labelEmpty1 = new JLabel(" ");
	JLabel labelEmpty2 = new JLabel(" ");
	JLabel labelEmpty3 = new JLabel(" ");
	JLabel labelEmpty4 = new JLabel(" ");

	public ReactivateUser() {
		this.setLayout(new GridLayout(0, 1));
		title = new JLabel("Reactivating Users");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		userLabel = new JLabel("Please choose a user from the list below");
		buttonReactivate = new JButton("Reactivate");
		User.userListCompleteInactive(userDropDown);

		this.add(title);
		this.add(labelEmpty);
		this.add(userLabel);
		this.add(userDropDown);
		this.add(buttonReactivate);
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		this.add(labelEmpty4);

		buttonReactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userDropDown.getItemCount()>0){
					System.out.println(userDropDown.getComponentCount());
					reactivate();
				}
			}
		});
	}

	public void reactivate(){
		String id = RetailSystem.returnIDfromCombobox(userDropDown.getSelectedItem().toString());
		User user = User.retrieveUser(id);
		if(user != null){
			reactivateUser(user);
			reloadDropDown();
		}
	}
	public void reloadDropDown() {
		userDropDown.removeAllItems();
		User.userListCompleteInactive(userDropDown);
		userDropDown.revalidate();
		userDropDown.repaint();
	}

	public void reactivateUser(User user) {
		user.setActive(true);
		User.saveUser();
		JOptionPane.showMessageDialog(null, "Product has been set to active");
	}

}
