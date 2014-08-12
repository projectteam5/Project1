//package RetailSystem;

public class Customer {

	private String customerId;//change as customerID and change below as well
	private String name;
	private String address;
	private String phoneNumber;
	//comment
	public Customer(String id, String name, String address, String phoneNumber) {
		this.CustomerId = CustomerId;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public void display(){
		System.out.println("Customer ID is "+ customerId +
				", Name is "+name+", Address is "+address+", Phone Number is "+phoneNumber);
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String CustomerId) {
		this.customerId = customerId;
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
	
	

}
