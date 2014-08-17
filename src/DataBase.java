

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataBase {

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
				for (Supplier supplier_1 : RetailSystem.getInstance().getSuppliers()) {
					if (supplier_1.getSupplierID().equals(supplierID)) {
						supplier = supplier_1;
						break; // I need to exit the for
					}
				}
				if (supplier != null) {
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

	// method for loading orders in the Array List
	public static ArrayList<Order> loadOrders(Reader reader) throws IOException, ParseException {
		ArrayList<Order> orders = new ArrayList<Order>();
		BufferedReader bufReader = new BufferedReader(reader);
		String line = null;
		int count = 0;
		while ((line = bufReader.readLine()) != null) {
			String[] parts = line.split(";");
			count++;
			if (parts.length == 7) {
				String orderID = parts[0];
				Date orderDate = DateFormat.getDateInstance().parse(parts[1]); // transform as date
				String productID = parts[2];
				//String supplierID = parts[3];
				int quantity = Integer.parseInt(parts[3]);// transform as int																// int
				Date expectedDeliveryDate = DateFormat.getDateInstance().parse(parts[4]);// transform as date
				Date dateReceived = null;
				if (!parts[5].isEmpty()){
					dateReceived = DateFormat.getDateInstance().parse(parts[5]);// transform as date
				}
				boolean received = Boolean.parseBoolean(parts[6]);// transform as boolean
				Product product = null;
				// before to construct the object order it is necessary retrieve
				// the object product
				for (Product product_1 : RetailSystem.getInstance().getProducts()) {
					if (product_1.getProductID().equals(productID)) {
						product = product_1;
						break; // I need to exit the for
					}
				}
				if (product!= null) {
					Order order = new Order(orderID, orderDate, product, quantity, expectedDeliveryDate,
							dateReceived, received);
					orders.add(order);
				} else {
					System.err
							.println("Skipping order with not valid  product at line "
									+ count);
				}

			} else {
				System.err.println("Skipping corrupted order at line " + count);
			}

		}
		return orders;
	}
	
	// method for loading stocks in the Array List
		public static ArrayList<Stock> loadStocks(Reader reader) throws IOException, ParseException {
			ArrayList<Stock> stocks = new ArrayList<Stock>();
			BufferedReader bufReader = new BufferedReader(reader);
			String line = null;
			int count = 0;
			while ((line = bufReader.readLine()) != null) {
				String[] parts = line.split(";");
				count++;
				if (parts.length == 2) {
					String productID = parts[0];
					int quantity = Integer.parseInt(parts[1]);// transform as int																// int
					Product product = null;
					// before to construct the object order it is necessary retrieve
					// the object product
					for (Product product_1 : RetailSystem.getInstance().getProducts()) {
						if (product_1.getProductID().equals(productID)) {
							product = product_1;
							break; // I need to exit the for
						}
					}
					if (product!= null) {
						Stock stock = new Stock(quantity, product);
						stocks.add(stock);
					} else {
						System.err
								.println("Skipping order with not valid  product at line "
										+ count);
					}

				} else {
					System.err.println("Skipping corrupted stock at line " + count);
				}

			}
			return stocks;
		}

	// ---- METHODS FOR SAVE DATA AT THE END OF THE SESSION
	// method for saving users at the end of the session
	public static void writeUsers(ArrayList<User> users, Writer writer)
			throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		for (User user : users) {
			out.write(user.getUserID() + ";" + user.getName() + ";"
					+ user.getPassword() + ";" + user.getType());
			out.newLine();
		}
		out.close();
	}
	

	// method for saving customers at the end of the session
	public static void writeCustomers(ArrayList<Customer> customers,
			Writer writer) throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		for (Customer customer : customers) {
			out.write(customer.getCustomerID() + ";" + customer.getName() + ";"
					+ customer.getAddress() + ";" + customer.getPhoneNumber());
			out.newLine();
		}
		out.close();
	}

	// method for saving suppliers at the end of the session
	public static void writeSuppliers(ArrayList<Supplier> suppliers,
			Writer writer) throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		for (Supplier supplier : suppliers) {
			out.write(supplier.getSupplierID() + ";" + supplier.getName() + ";"
					+ supplier.getPhoneNumber());
			out.newLine();
		}
		out.close();

	}

	// method for saving products at the end of the session
	public static void writeProducts(ArrayList<Product> products, Writer writer)
			throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		for (Product product : products) {
			out.write(product.getProductID() + ";" + product.getName() + ";"
					+ product.getCost() + ";" + product.getMarkup() + ";"
					+ product.getSupplier().getSupplierID());
			out.newLine();
		}
		out.close();

	}
	
	// method for saving orders at the end of the session
	public static void writeOrders(ArrayList<Order> orders, Writer writer)
			throws IOException {
		//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		BufferedWriter out = new BufferedWriter(writer);
		for (Order order : orders) {
			out.write(order.getOrderID() + ";" + DateFormat.getDateInstance().format(order.getOrderDate()) + ";"
					+ order.getProduct().getProductID() + ";" 
					+ order.getQuantity() + ";" 
					+ DateFormat.getDateInstance().format(order.getExpectedDeliveryDate()) + ";"
					+ DateFormat.getDateInstance().format(order.getDateReceived())  + ";" 
					+ order.isReceived());
			out.newLine();
		}
		out.close();

	}
	
	// method for saving stock at the end of the session
	public static void writeStocks(ArrayList<Stock> stocks, Writer writer)
			throws IOException {
		BufferedWriter out = new BufferedWriter(writer);
		for (Stock stock : stocks) {
			out.write(stock.getProduct().getProductID() + ";" + stock.getUnits());
			out.newLine();
		}
		out.close();

	}
	

}
