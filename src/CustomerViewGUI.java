import java.awt.Container;
import java.awt.Font;
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

public class CustomerViewGUI extends JPanel {
	
	private JComboBox customersDropDown;
	private JLabel labelTitleMain;
	private JLabel labelTitle;
	private JLabel labelID;
	private JLabel labelName;
	private JLabel labelAddress;
	private JLabel labelPhone;
	private JButton viewButton;
	private int returnValue;
	private String selectedCustomerID;
	private String selectedCustomerIDPrev;

	public CustomerViewGUI() {
		
		this.setLayout(new GridLayout(0, 1));
	
		labelTitle = new JLabel(
				"Please pick the customer you want to view from the customer list below");
		customersDropDown = new JComboBox();
		Customer.customerListComplete(customersDropDown);
		viewButton = new JButton("View Customer");
		
		
		this.add(labelTitle);
		this.add(customersDropDown);
		this.add(viewButton);

		
		returnValue = 2;
		
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Cris: using the static method in Customer class
				selectedCustomerID = RetailSystem.returnIDfromCombobox(customersDropDown.getSelectedItem().toString());
				if(returnValue == 2 || !selectedCustomerID.equals(selectedCustomerIDPrev)){
					returnValue = viewCustomerButton();
					selectedCustomerIDPrev = selectedCustomerID;
				}
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
			this.remove(labelName);
			this.remove(labelID);
			this.remove(labelAddress);
			this.remove(labelPhone);
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
			this.add(labelName);
			this.add(labelID);
			this.add(labelAddress);
			this.add(labelPhone);
			this.revalidate();
			this.repaint();
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
	
	

}
