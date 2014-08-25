import java.io.FileWriter;

import javax.swing.JComboBox;

//package RetailSystem;

public class Customer {

	private String customerID;// change as customerID and change below as well
	private String name;
	private String address;
	private String phoneNumber;
	private boolean active;

	// comment
	public Customer(String name, String address,
			String phoneNumber) {
		this.customerID = "Customer"+(RetailSystem.getInstance().getCustomers().size()+1);
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.active = true;
	}

	public Customer(String customerID, String name, String address,
			String phoneNumber, boolean active2) {
		this.customerID = customerID;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.active = active2;
	}

	public void display() {
		System.out.println("Customer ID is " + customerID + ", Name is " + name
				+ ", Address is " + address + ", Phone Number is "
				+ phoneNumber);
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static void customerListComplete(JComboBox dropdown) {
		for (Customer customer : RetailSystem.getInstance().getCustomers()) {
			if (customer.isActive()) {
				String string = "ID: " + customer.getCustomerID() + " ; Name: "
						+ customer.getName();
				dropdown.addItem(string);
			}
		}

	}

	public static Customer retrieveCustomer(String id) {
		Customer customerRet = null;
		for (Customer customer : RetailSystem.getInstance().getCustomers()) {
			if (customer.getCustomerID().equals(id)) {
				customerRet = customer;
			}
		}
		return customerRet;
	}

	public static void saveCustomer() {
		try {
			FileWriter customerFile;
			customerFile = new FileWriter("customers.txt");
			DataBase.writeCustomers(RetailSystem.getInstance().getCustomers(),
					customerFile);
			customerFile.close();// close the user file
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
