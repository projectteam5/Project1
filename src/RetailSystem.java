
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;

public class RetailSystem {

	// attribute for the RetailSystem class
	private static RetailSystem instance; // we want only one instance of this class
	private ArrayList<User> users;// array list of users that can access the system
	private ArrayList<Customer> customers;// array list of customers
	private ArrayList<Order> orders;// array list of orders
	private ArrayList<Supplier> suppliers;// array list of suppliers
	private ArrayList<Product> products;// array list of products
	private ArrayList<Stock> stocks;// array list of stock invoices
	private ArrayList<Invoice> invoices;// array list of invoices

	private String currentUserType = "";// it store which type of user logged on
	private String currentUserID = "";// it store which type of user logged on
	private String[] userTypeList = {"Manager", "Attendant"};//Array with the possible users types


	public RetailSystem() {
	}

	// we want a unique instance that every class can refer to
	// hence, we create the object only once and we save it in a static variable
	public static RetailSystem getInstance() {
		if (instance == null) {
			// the first time we try to access the instance
			// if it doesn't exist it is created
			instance = new RetailSystem();
		}
		return instance;
	}

	// main function that drives all the application
	public static void main(String[] args) {
		// load of all the data
		FileReader userFile;
		RetailSystem retailSystem = getInstance();
		// load users
		try {
			userFile = new FileReader("users.txt");
			retailSystem.setUsers(DataBase.loadUsers(userFile));
			userFile.close();// close the user file
		} catch (Exception e) {
			e.printStackTrace();
		}

		// load customers
		try {
			userFile = new FileReader("customers.txt");
			retailSystem.setCustomers(DataBase.loadCustomers(userFile));
			userFile.close();// close the user file
		} catch (Exception e) {
			e.printStackTrace();
		}
		// load suppliers
		try {
			userFile = new FileReader("suppliers.txt");
			retailSystem.setSuppliers(DataBase.loadSuppliers(userFile));
			userFile.close();// close the user file
		} catch (Exception e) {
			e.printStackTrace();
		}
		// load products
		try {
			userFile = new FileReader("products.txt");
			retailSystem.setProducts(DataBase.loadProducts(userFile));
			userFile.close();// close the user file
		} catch (Exception e) {
			e.printStackTrace();
		}
		// load orders
		try {
			userFile = new FileReader("orders.txt");
			retailSystem.setOrders(DataBase.loadOrders(userFile));
			userFile.close();// close the user file
		} catch (Exception e) {
			e.printStackTrace();
		}

		// load stocks
		try {
			userFile = new FileReader("stocks.txt");
			retailSystem.setStocks(DataBase.loadStocks(userFile));
			userFile.close();// close the user file
		} catch (Exception e) {
			e.printStackTrace();
		}

		// login gui for authentication
		LoginGUI login = new LoginGUI();

	}

	public String getCurrentUserID() {
		return currentUserID;
	}

	public void setCurrentUserID(String currentUserID) {
		this.currentUserID = currentUserID;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	
	 public ArrayList<Customer> getCustomers(){ 
		 return customers; 
	 }
	 
	 public void setCustomers(ArrayList<Customer> customers){ 
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
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}

	public String getCurrentUserType() {
		return currentUserType;
	}

	public void setCurrentUserType(String currentUserType) {
		this.currentUserType = currentUserType;
	}

	public String[] getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(String[] userTypeList) {
		this.userTypeList = userTypeList;
	}

	public ArrayList<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(ArrayList<Invoice> invoices) {
		this.invoices = invoices;
	}

	
	
}
