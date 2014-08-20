

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerAddGUI extends JFrame {
	
	JButton button1 = new JButton("Add Customer");
	
	
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JButton customerMenuButton;

	public CustomerAddGUI() {
		setSize(400, 200); // set frames size in pixels
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		textField1 = new JTextField();
		JLabel label1 = new JLabel("Customer ID");
		textField2 = new JTextField();
		JLabel label2 = new JLabel("Customer Name");
		textField3 = new JTextField();
		JLabel label3 = new JLabel("Customer Address");
		textField4 = new JTextField();
		JLabel label4 = new JLabel("Customer Phone Number");
		customerMenuButton = new JButton("Customer Menu");
		
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(0,1));
		jpanel.add(label1);
		jpanel.add(textField1);
		jpanel.add(label2);
		jpanel.add(textField2);
		jpanel.add(label3);
		jpanel.add(textField3);
		jpanel.add(label4);
		jpanel.add(textField4);
		jpanel.add(customerMenuButton);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerID = textField1.getText();
				String name = textField2.getText();
				String address = textField3.getText();
				String phoneNumber = textField4.getText();
				if(customerValidation(customerID, name, address, phoneNumber)){
					Customer customer = new Customer(customerID, name, address, phoneNumber);
					RetailSystem.getInstance().getCustomers().add(customer);
					saveCustomer();
					JOptionPane.showMessageDialog(null, "Customer Created and added to system", "Success", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});
		
		customerMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGUI customerGui = new CustomerGUI();
				closeCustomerAddGUI();
				
			}
		});
		
		jpanel.add(button1);
		Container cp = getContentPane();
		cp.add(jpanel);
		setVisible(true);
	}
	
	public static void saveCustomer() {
		try {
			FileWriter customerFile;
			customerFile = new FileWriter("customers.txt");
			DataBase.writeCustomers(RetailSystem.getInstance().getCustomers(), customerFile);
			customerFile.close();// close the customer file
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	
	public boolean customerValidation(String customerID, String name, String address, String phoneNumber){
		boolean correct = true;
		if (customerID.isEmpty() || name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()){
			correct=false;
			JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else {
			for(Customer customer :RetailSystem.getInstance().getCustomers()){
				if (customer.getCustomerID().equals(customerID)){
					correct = false;
					JOptionPane.showMessageDialog(null, "Customer ID already exists!", "Warning", JOptionPane.INFORMATION_MESSAGE);	
				}
				
			}
		}
		return correct;
	}
	
	public void closeCustomerAddGUI(){
		this.setVisible(false);
	}
	
}
