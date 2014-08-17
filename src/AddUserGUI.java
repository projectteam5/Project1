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


public class AddUserGUI extends JFrame {
	
	private JTextField textUserID;
	private JTextField textUserName;
	private JTextField textUserPass;
	private JComboBox typeDropDown;

	public AddUserGUI() {
		// declaration and initialization of panel, container and layout setting
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));

		// declaration of the labels and initialization of labels and text field
		textUserID = new JTextField();
		textUserName = new JTextField();
		textUserPass = new JTextField();
		typeDropDown = new JComboBox(RetailSystem.getInstance().getUserTypeList());
		JLabel label1 = new JLabel("User ID");
		JLabel label2 = new JLabel("Name");
		JLabel label3 = new JLabel("Password");
		JLabel label4 = new JLabel("Type");
		JButton doneButton = new JButton("Add");
		
		// adding all the components
		panel.add(label1);
		panel.add(textUserID);
		panel.add(label2);
		panel.add(textUserName);
		panel.add(label3);
		panel.add(textUserPass);
		panel.add(label4);
		panel.add(typeDropDown);
		panel.add(doneButton);

		// Define the panel for the Customer managment

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = addUser();
				if(returnValue == 0){
					JOptionPane.showMessageDialog(null, "User correctly inserted", "Inserted", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "ID already exists or missing fields", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});

		this.setVisible(true);

	}
	
	public int addUser(){
		String userID = textUserID.getText();
		String name = textUserName.getText();
		String password = textUserPass.getText();
		String type = typeDropDown.getSelectedItem().toString();
		if(validateUser(userID, name, password, type)){
			User user = new User(userID, name, password, type);
			RetailSystem.getInstance().getUsers().add(user);
			User.saveUser();
			this.setVisible(false);
			return 0;
		}
		else{
			return 1;
		}
	}
	
	public static boolean validateUser(String userID, String name, String password, String type){
		boolean userOk = false;
		if(userID != null && name != null && password != null && type != null && !userID.isEmpty() && !name.isEmpty() && !password.isEmpty() && !type.isEmpty()){
			if(!User.existingUser(userID)){
				userOk = true;
			}
		}
		return userOk;
	}

}
