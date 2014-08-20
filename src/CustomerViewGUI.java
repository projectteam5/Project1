import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomerViewGUI extends JFrame {
	
	private JPanel panel;
	private JComboBox customersDropDown;
	private JLabel labelTitle;
	private JLabel labelID;
	private JLabel labelName;
	private JLabel labelAddress;
	private JLabel labelPhone;
	private JButton viewButton;
	private JButton customerMenuButton;
	private int returnValue;
	private String selectedCustomerID;
	private String selectedCustomerIDPrev;

	public CustomerViewGUI() {
		// declaration and initialization of panel, container, layout setting
		// and buttons
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 1));
			
		labelTitle = new JLabel("Please pick the customer from the list below");
		customersDropDown = new JComboBox();
		compileCustomerNames ();
		viewButton = new JButton("View Customer");
		customerMenuButton = new JButton("Customer Menu");
		
		panel.add(labelTitle);
		panel.add(customersDropDown);
		panel.add(viewButton);
		panel.add(customerMenuButton);
		
		returnValue = 2;
		
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedCustomerString = customersDropDown.getSelectedItem().toString();
				String[] selectedCustomerArray = selectedCustomerString.split(";");
				String selectedCustomerIDString = selectedCustomerArray[0];
				String[] selectedCustomerIDArray = selectedCustomerIDString.split(":");
				selectedCustomerID = selectedCustomerIDArray[1].trim();
				if(returnValue == 2 || !selectedCustomerID.equals(selectedCustomerIDPrev)){
					returnValue = viewCustomerButton();
					selectedCustomerIDPrev = selectedCustomerID;
				}
			}
		});
		
		setVisible(true);
		
		customerMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGUI customerGui = new CustomerGUI();
				closeCustomerViewGUI();
				
			}
		});

		setVisible(true);
	}
	
	public static String[] customerList(){
		String[] customerList = new String[ RetailSystem.getInstance().getCustomers().size()];
		int i = 0;
		for (Customer customer : RetailSystem.getInstance().getCustomers()){
			customerList[i] = "ID: " + customer.getCustomerID()+" ; Name: "+customer.getName();
			i++;
		}
		return customerList;
	}
	
	public int viewCustomerButton(){
		//retrieve the customer object in the user ArrayList
		if(returnValue != 2){
			panel.remove(labelName);
			panel.remove(labelID);
			panel.remove(labelAddress);
			panel.remove(labelPhone);
		}
		Customer selectedCustomer = null;
		for(Customer customer : RetailSystem.getInstance().getCustomers()){
			if(customer.getCustomerID().equals(selectedCustomerID)){
				selectedCustomer = customer;
			}
		}
		if(selectedCustomer != null){
			labelName = new JLabel("Customer Name: "+ selectedCustomer.getName());
			labelID = new JLabel("Customer ID: "+ selectedCustomer.getCustomerID());
			labelAddress = new JLabel("Customer Address: "+ selectedCustomer.getAddress());
			labelPhone = new JLabel("Customer Phone Number: "+ selectedCustomer.getPhoneNumber());
			panel.add(labelName);
			panel.add(labelID);
			panel.add(labelAddress);
			panel.add(labelPhone);
			panel.revalidate();
			panel.repaint();
			return 0;
		}
		else{
			return 1;
		}
		
	}
	
	public void compileCustomerNames () {
		for (Customer customer: RetailSystem.getInstance().getCustomers()) {
			String string = "ID: "+ customer.getCustomerID()+ "; Name: " + customer.getName();
			customersDropDown.addItem(string);
		}
	}
	
	public void closeCustomerViewGUI(){
		this.setVisible(false);
		dispose();
	}

}
