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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditUserGUI extends JFrame {

	private JTextField NameField;
	private JTextField PasswordField;
	private JComboBox typeDropDown;
	private String userID;
	private User user;
	
	
	public EditUserGUI(String userInput) {

		//initialize the user variable to display the current values of the user
		userID = userInput;
		user = retrieveUser(userID);

		// declaration and initialization of panel, container and layout setting
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));

		// declaration of the labels and initialization of labels and text field
		NameField = new JTextField(user.getName());
		PasswordField = new JTextField(user.getPassword());
		JLabel label1 = new JLabel("Name");
		JLabel label2 = new JLabel("Password");
		JLabel label3 = new JLabel("Type");
		typeDropDown = new JComboBox(RetailSystem.getInstance().getUserTypeList());
		typeDropDown.setSelectedItem(user.getType());
		JButton doneButton = new JButton("Commit");

		// adding all the components
		panel.add(label1);
		panel.add(NameField);
		panel.add(label2);
		panel.add(PasswordField);
		panel.add(label3);
		panel.add(typeDropDown);
		panel.add(doneButton);
		Container container = getContentPane();
		container.add(panel);

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = editUser();
				if(returnValue == 0){
					JOptionPane.showMessageDialog(null,
							"User correctly updated", "Update",
							JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null,
							"All fields must be filled!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		this.setVisible(true);
	}
	
	public User retrieveUser(String id){
		User user = null;
		for(User userRS : RetailSystem.getInstance().getUsers()){
			if(userRS.getUserID().equals(userID)){
				user = userRS;
			}
		}
		return user;
	}
	
	public int editUser(){
		int returnValue = 1;
		String name = NameField.getText();
		String password = PasswordField.getText();
		String type = typeDropDown.getSelectedItem().toString();
		if (!name.isEmpty() && !password.isEmpty() && !type.isEmpty()) {
			for (User userRS : RetailSystem.getInstance().getUsers()) {
				if (userRS.getUserID().equals(userID)) {
					userRS.setName(name);
					userRS.setPassword(password);
					userRS.setType(type);
					returnValue = 0;
					this.setVisible(false);
				}
			}
		}
		return returnValue;
	}
}
