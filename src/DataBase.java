

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
//
public class DataBase {
	//comment

	// ---- METHODS FOR UPLOAD DATA AT THE BEGINNING OF THE SESSION
	// method for loading users in the Array List
	public static ArrayList<User> loadUsers(Reader reader) throws IOException {
		ArrayList<User> users = new ArrayList<User>();
		BufferedReader bufReader = new BufferedReader(reader);
		String line = null;//test
		int count = 0;
		while ((line = bufReader.readLine()) != null) {
			String[] parts = line.split(";");
			count++;
			if (parts.length == 4) {
				String userID = parts[0];
				String name = parts[1];
				String password = parts[2];
				String type = parts[3];
				User user = new User(userID, name, password, type);
				users.add(user);
			} else {
				System.err.println("Skipping corrupted user at line " + count);
			}

		}
		return users;
	}
/*
	// method for loading customers in the Array List
	public static ArrayList<Customer> loadCustomers(Reader reader)
			throws IOException {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		BufferedReader bufReader = new BufferedReader(reader);
		String line = null;
		int count = 0;
		while ((line = bufReader.readLine()) != null) {
			String[] parts = line.split(";");
			count++;
			if (parts.length == 4) {
				String customerID = parts[0];
				String name = parts[1];
				String address = parts[2];
				String phoneNumber = parts[3];
				Customer customer = new Customer(customerID, name, address,
						phoneNumber);
				customers.add(customer);
			} else {
				System.err.println("Skipping corrupted customer at line "
						+ count);
			}

		}
		return customers;
	}

	// method for loading suppliers in the Array List
	public static ArrayList<Supplier> loadSuppliers(Reader reader)
			throws IOException {
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		BufferedReader bufReader = new BufferedReader(reader);
		String line = null;
		int count = 0;
		while ((line = bufReader.readLine()) != null) {
			String[] parts = line.split(";");
			count++;
			if (parts.length == 3) {
				String supplierID = parts[0];
				String name = parts[1];
				String phoneNumber = parts[2];
				Supplier supplier = new Supplier(supplierID, name, phoneNumber);
				suppliers.add(supplier);
			} else {
				System.err.println("Skipping corrupted supplier at line "
						+ count);
			}

		}
		return suppliers;
	}

	// method for loading products in the Array List
	public static ArrayList<Product> loadProducts(Reader reader)
			throws IOException {
		ArrayList<Product> products = new ArrayList<Product>();
		BufferedReader bufReader = new BufferedReader(reader);
		String line = null;
		int count = 0;
		while ((line = bufReader.readLine()) != null) {
			String[] parts = line.split(";");
			count++;
			if (parts.length == 5) {
				String productID = parts[0];
				String name = parts[1];
				double cost = Double.parseDouble(parts[2]); // double
				double markup = Double.parseDouble(parts[3]); // double
				String supplierID = parts[4]; // with the id i need to find the
				Supplier supplier = null;
				for (Supplier supplier_1 : RetailSystem.getInstance()
						.getSuppliers()) {
					if (supplier_1.getSupplierID().equals(supplierID)) {
						supplier = supplier_1;
						break; // I need to exit the for
					}
				}
				if (!supplier.isEmpty()) {
					Product product = new Product(productID, name, cost,
							markup, supplier);
					products.add(product);
				} else {
					System.err
							.println("Skipping product with not valid supplier at line "
									+ count);
				}

			} else {
				System.err.println("Skipping corrupted product at line "
						+ count);
			}

		}
		return products;
	}

/*	// method for loading orders in the Array List
	public static ArrayList<Order> loadOrders(Reader reader) throws IOException {
		ArrayList<Order> orders = new ArrayList<Order>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		BufferedReader bufReader = new BufferedReader(reader);
		String line = null;
		int count = 0;
		while ((line = bufReader.readLine()) != null) {
			String[] parts = line.split(";");
			count++;
			if (parts.length == 8) {
				String orderID = parts[0];
				Date orderDateString = df.parse(parts[1]); // transform as date
				String productID = parts[2];
				String supplierID = parts[3];
				int quantity = Integer.parseInteger(parts[4]);// transform as
																// int
				Date expectedDeliveryDate = df.parse(parts[5]);// transform as
																// date
				Date dateReceived = df.parse(parts[6]);// transform as date
				boolean received = Boolean.parseBoolean(parts[7]);// transform
																	// as
																	// boolean
				Supplier supplier = null;
				Product product = null;
				// before to construct the object order it is necessary retrieve
				// the
				// objects supplier and product
				for (Supplier supplier_1 : RetailSystem.getInstance()
						.getSuppliers()) {
					if (supplier_1.getSupplierID().equals(supplierID)) {
						supplier = supplier_1;
						break; // I need to exit the for
					}
				}
				for (Product product_1 : RetailSystem.getInstance()
						.getProducts()) {
					if (product_1.getSupplierID().equals(productID)) {
						product = product_1;
						break; // I need to exit the for
					}
				}
				if (!supplier.isEmpty() && !product.isEmpty()) {
					Order order = new Order(orderID, orderDate, product,
							supplier, quantity, expectedDeliveryDate,
							dateReceived, received);
					orders.add(order);
				} else {
					System.err
							.println("Skipping order with not valid supplier or product at line "
									+ count);
				}

			} else {
				System.err.println("Skipping corrupted order at line " + count);
			}

		}
		return orders;
	}*/

	// ---- METHODS FOR SAVE DATA AT THE END OF THE SESSION
	// method for saving users at the end of the session
	public static void writeUsers(ArrayList<User> users, Writer writer)
			throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		for (User user : users) {
			out.write(user.getUserID() + ";" + user.getName() + ";"
					+ user.getPassword() + ";" + user.getType() + "\n");
		}

	}
	/*

	// method for saving customers at the end of the session
	public static void writeCustomers(ArrayList<Customer> customer,
			Writer writer) throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		for (Customer customer : customers) {
			out.write(customer.getCustomerID() + ";" + customer.getName() + ";"
					+ customer.getAddress() + ";" + customer.getPhoneNumber()
					+ "\n");
		}

	}

	// method for saving suppliers at the end of the session
	public static void writeSuppliers(ArrayList<Supplier> suppliers,
			Writer writer) throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		for (Supplier supplier : suppliers) {
			out.write(supplier.getSupplierID() + ";" + supplier.getName() + ";"
					+ supplier.getPhoneNumber() + "\n");
		}

	}

	// method for saving products at the end of the session
	public static void writeProducts(ArrayList<Product> products, Writer writer)
			throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		for (Product product : products) {
			out.write(product.getProductID() + ";" + product.getName() + ";"
					+ product.getCost() + ";" + product.getMarkup() + ";"
					+ product.getSupplier().getSupplierID() + "\n");
		}

	}*/
	
/*	// method for saving orders at the end of the session
	public static void writeOrders(ArrayList<Order> orders, Writer writer)
			throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		for (Order order : orders) {
			out.write(order.getOrderID() + ";" + order.getOrderDate() + ";"
					+ order.getProduct().getProductID() + ";" 
					+ order.getSupplier().getSupplierID() + ";"
					+ order.getQuantity() + ";" + order.getExpectedDeliveryDate() + ";"
					+ order.getDateReceived()  + ";" + order.getReceived() + "\n");
		}

	}*/
	

}
