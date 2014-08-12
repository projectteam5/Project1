

import java.io.FileReader;
import java.util.ArrayList;

public class RetailSystem {

	//attribute for the RetailSystem class
	private static RetailSystem instance; //we want only one instance of this class
	private ArrayList<User> users;//array list of users that can access the system
	//private ArrayList<Customer> customers;//array list of customers
	//private ArrayList<Supplier> suppliers;//array list of suppliers
	//private ArrayList<Product> products;//array list of products
	//private ArrayList<Order> orders;//array list of orders
	
	private String currentUserType = "";//it store which type of user logged on
	
	public RetailSystem() {	}
	
	// we want a unique instance that every class can refer to
	// hence, we create the object only once and we save it in a static variable
	public static RetailSystem getInstance() {
		if (instance == null) {
			//the first time we try to access the instance 
			//if it doesn't exist it is created
			instance = new RetailSystem(); 
		}
		return instance;
	}

	//main function that drives all the application
	public static void main(String[] args) {
		//load of all the data 
		FileReader userFile;
		RetailSystem retailSystem = getInstance();
		try {
			userFile = new FileReader("users.txt");
			retailSystem.setUsers(DataBase.loadUsers(userFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//login gui for authentication
		LoginGUI login = new LoginGUI();

	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	/*public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public ArrayList<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(ArrayList<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}*/

	public String getCurrentUserType() {
		return currentUserType;
	}

	public void setCurrentUserType(String currentUserType) {
		this.currentUserType = currentUserType;
	}

	public static void setInstance(RetailSystem instance) {
		RetailSystem.instance = instance;
	}

}
