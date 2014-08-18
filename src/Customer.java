import java.io.FileWriter;

//package RetailSystem;

public class Customer {

	private String customerID;//change as customerID and change below as well
	private String name;
	private String address;
	private String phoneNumber;
	//comment
	public Customer(String customerID, String name, String address, String phoneNumber) {
		this.customerID = customerID;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public void display(){
		System.out.println("Customer ID is "+ customerID +
				", Name is "+name+", Address is "+address+", Phone Number is "+phoneNumber);
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


	public static String[] customerListComplete() {
		String[] customerList = new String[RetailSystem.getInstance().getCustomers()
				.size()];
		int i = 0;
		for (Customer customer : RetailSystem.getInstance().getCustomers()) {
			customerList[i] = "ID: " + customer.getCustomerID() + " ; Name: "
					+ customer.getName();
			i++;
		}
		return customerList;
	}
	
	

}
