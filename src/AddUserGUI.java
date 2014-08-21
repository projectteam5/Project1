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
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JButton doneButton;
	private JButton userMenuButton;

	public AddUserGUI() {
		// declaration and initialization of panel, container and layout setting
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(RetailSystem.getInstance().getWidth(), RetailSystem.getInstance().getHeight());
		this.setTitle("Create User");
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
		label1 = new JLabel("User ID");
		label2 = new JLabel("Name");
		label3 = new JLabel("Password");
		label4 = new JLabel("Type");
		doneButton = new JButton("Add");
		userMenuButton  = new JButton("User Menu");
		
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
		panel.add(userMenuButton);

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
		
		userMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserGUI userGui = new UserGUI();
				closeGUI();
				
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
	
	public void closeGUI(){
		this.setVisible(false);
	}

}
