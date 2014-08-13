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
	
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JComboBox typeDropDown;

	public AddUserGUI() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));

		textField1 = new JTextField();
		JLabel label1 = new JLabel("User ID");
		textField2 = new JTextField();
		JLabel label2 = new JLabel("Name");
		textField3 = new JTextField();
		JLabel label3 = new JLabel("Password");
		typeDropDown = new JComboBox(RetailSystem.getInstance().getUserTypeList());
		JLabel label4 = new JLabel("Type");
		JButton doneButton = new JButton("Add");
		
		panel.add(label1);
		panel.add(textField1);
		panel.add(label2);
		panel.add(textField2);
		panel.add(label3);
		panel.add(textField3);
		panel.add(label4);
		panel.add(typeDropDown);
		panel.add(doneButton);

		// Define the panel for the Customer managment

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userID = textField1.getText();
				String name = textField2.getText();
				String password = textField3.getText();
				String type = typeDropDown.getSelectedItem().toString();
				if(validateUser(userID, name, password, type)){
					User user = new User(userID, name, password, type);
					RetailSystem.getInstance().getUsers().add(user);
					JOptionPane.showMessageDialog(null, "User correctly inserted", "Inserted", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});

		this.setVisible(true);

	}
	
	public boolean validateUser(String userID, String name, String password, String type){
		boolean userOk = true;
		for(User user : RetailSystem.getInstance().getUsers()){
			if(user.getUserID().equals(userID)){
				userOk = false;
				JOptionPane.showMessageDialog(null, "ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return userOk;
		
	}

}
