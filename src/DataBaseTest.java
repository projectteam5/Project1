import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataBaseTest {

	// TEST FOR LOADING FILE

	private ArrayList<Supplier> supplierStatus = RetailSystem.getInstance()
			.getSuppliers();
	private ArrayList<Product> productStatus = RetailSystem.getInstance()
			.getProducts();
	private static ArrayList<User> usersTest;
	private static ArrayList<Customer> customersTest;
	private static ArrayList<Supplier> suppliersTest;
	private static ArrayList<Product> productsTest;
	private static ArrayList<Order> ordersTest;
	private static ArrayList<Stock> stocksTest;
	private static Date date = new Date();

	/*
	 * it is necessary to initialize a supplier arrayList for the product
	 * loading it is necessary to initialize a product arrayList for the order
	 * loading
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Reader reader = new StringReader("supplier1;Supplier1 Name;083776655;true\n"
				+ "supplier2;Supplier2 Name;083775839;true\n");
		ArrayList<Supplier> suppliers = DataBase.loadSuppliers(reader);
		RetailSystem.getInstance().setSuppliers(suppliers);
		Reader reader1 = new StringReader(
				"product1;ProductPC1;1000;200;supplier1;true\n"
						+ "product2;ProductPC2;2000;400;supplier1;true\n"
						+ "product3;ProductPC3;1500;300;supplier2;true\n");
		ArrayList<Product> products = DataBase.loadProducts(reader1);
		RetailSystem.getInstance().setProducts(products);
		User user1 = new User("1111","Cris","passw10","Attendant");
		usersTest = new ArrayList<User>();
		usersTest.add(user1);
		Customer customer1 = new Customer("customer1","Scott Scott","address 1 Dublin","089665544");
		customersTest = new ArrayList<Customer>();
		customersTest.add(customer1); 
		Supplier supplier1 = new Supplier("supplier1","Supplier1 Name","083776655");
		suppliersTest = new ArrayList<Supplier>();
		suppliersTest.add(supplier1);//product1;ProductPC1;1000;200;supplier1
		Product product1 = new Product("product1","ProductPC1",1000, 200, supplier1);
		productsTest = new ArrayList<Product>();
		productsTest.add(product1);
		Order order1 = new Order("order10", date, RetailSystem.getInstance().getProducts().get(0),10,date,date,false );
		ordersTest = new ArrayList<Order>();
		ordersTest.add(order1);
		Stock stock1 = new Stock(10, product1);
		stocksTest = new ArrayList<Stock>();
		stocksTest.add(stock1);
		
	}

	/*
	 * at the end of the test phase the arraylists previously initialized are
	 * reset with their original values
	 */
	@After
	public void tearDown() throws Exception {
		RetailSystem.getInstance().setSuppliers(supplierStatus);
		RetailSystem.getInstance().setProducts(productStatus);
	}

	// TEST FOR USERS
	// Loads user correctly
	@Test
	public void testLoadUsersOK2Users() throws IOException {
		Reader reader = new StringReader("1111;Scott Scott;passw1;Manager;true\n"
				+ "1112;Daniel Daniel;passw2;Attendant;true\n");
		ArrayList<User> list = DataBase.loadUsers(reader);
		assertEquals(2, list.size());
	}

	// Loads file empty
	@Test
	public void testLoadUsersEmpty() throws IOException {
		Reader reader = new StringReader("");
		ArrayList<User> list = DataBase.loadUsers(reader);
		assertEquals(0, list.size());
	}

	// Skips lines with corrupted data
	@Test
	public void testLoadUsersCorrupted() throws IOException {
		Reader reader = new StringReader("1111;Scott Scott;pa");
		ArrayList<User> list = DataBase.loadUsers(reader);
		assertEquals(0, list.size());
	}

	// TEST FOR CUSTOMERS
	// Loads customer correctly
	@Test
	public void testLoadCustomersOK2Customers() throws IOException {
		Reader reader = new StringReader(
				"customer1;Scott Scott;address 1 Dublin;089665544;true\n"
						+ "customer2;Henry Bo;address 2 Cork;089334455;true\n");
		ArrayList<Customer> list = DataBase.loadCustomers(reader);
		assertEquals(2, list.size());
	}

	// Loads file empty
	@Test
	public void testLoadCustomersEmpty() throws IOException {
		Reader reader = new StringReader("");
		ArrayList<Customer> list = DataBase.loadCustomers(reader);
		assertEquals(0, list.size());
	}

	// Skips lines with corrupted data
	@Test
	public void testLoadCustomersCorrupted() throws IOException {
		Reader reader = new StringReader("customer1;Scott Scott;address");
		ArrayList<Customer> list = DataBase.loadCustomers(reader);
		assertEquals(0, list.size());
	}

	// TEST FOR SUPPLIERS
	// Loads suppliers correctly
	@Test
	public void testLoadSuppliersOK2Suppliers() throws IOException {
		Reader reader = new StringReader("supplier1;Supplier1 Name;083776655;true\n"
				+ "supplier2;Supplier2 Name;083775839;true\n");
		ArrayList<Supplier> list = DataBase.loadSuppliers(reader);
		assertEquals(2, list.size());
	}

	// Loads file empty
	@Test
	public void testLoadSupplierEmpty() throws IOException {
		Reader reader = new StringReader("");
		ArrayList<Supplier> list = DataBase.loadSuppliers(reader);
		assertEquals(0, list.size());
	}

	// Skips lines with corrupted data
	@Test
	public void testLoadSupplierCorrupted() throws IOException {
		Reader reader = new StringReader("supplier1;Supplier1 Nam");
		ArrayList<Supplier> list = DataBase.loadSuppliers(reader);
		assertEquals(0, list.size());
	}

	// TEST FOR PRODUCTS
	// Loads products correctly
	@Test
	public void testLoadProductsOK3Products() throws IOException {
		Reader reader = new StringReader(
				"product1;ProductPC1;1000;200;supplier1;true\n"
						+ "product2;ProductPC2;2000;400;supplier1;true\n"
						+ "product3;ProductPC3;1500;300;supplier2;true\n");
		ArrayList<Product> list = DataBase.loadProducts(reader);
		assertEquals(3, list.size());
	}

	// Loads file empty
	@Test
	public void testLoadProductEmpty() throws IOException {
		Reader reader = new StringReader("");
		ArrayList<Product> list = DataBase.loadProducts(reader);
		assertEquals(0, list.size());
	}

	// Skips lines with corrupted data
	@Test
	public void testLoadProductCorrupted() throws IOException {
		Reader reader = new StringReader("product1;ProductPC1;1000;2");
		ArrayList<Product> list = DataBase.loadProducts(reader);
		assertEquals(0, list.size());
	}

	// TEST FOR ORDERS
	// Loads orders correctly
	@Test
	public void testLoadOrderOK2Orders() throws IOException, ParseException {
		//System.err.println(DateFormat.getDateInstance(DateFormat.DEFAULT).format(new Date()));
		Reader reader = new StringReader(
				"order1;14-Aug-2014;product1;10;14-Aug-2014;;false;true\n"
						+ "order1;14-Aug-2014;product2;9;14-Aug-2014;14-Aug-2014;false;true\n");
		ArrayList<Order> list = DataBase.loadOrders(reader);
		assertEquals(2, list.size());
	}

	// Loads file empty
	@Test
	public void testLoadOrderEmpty() throws IOException, ParseException {
		Reader reader = new StringReader("");
		ArrayList<Order> list = DataBase.loadOrders(reader);
		assertEquals(0, list.size());
	}

	// Skips lines with corrupted data
	@Test
	public void testLoadOrderCorrupted() throws IOException, ParseException {
		Reader reader = new StringReader(
				"order1;14-Aug-2014;product1;10;14-Aug-2014;;false;true\n"
						+ "order1;14-Aug-2014");
		ArrayList<Order> list = DataBase.loadOrders(reader);
		assertEquals(1, list.size());
	}

	// TEST FOR STOCKS
	// Loads stocks correctly
	@Test
	public void testLoadStocksOK2Stocks() throws IOException, ParseException {
		Reader reader = new StringReader("product1;20;true\n" + "product2;15;true\n");
		ArrayList<Stock> list = DataBase.loadStocks(reader);
		assertEquals(2, list.size());
	}

	// Loads file empty
	@Test
	public void testLoadStocksEmpty() throws IOException, ParseException {
		Reader reader = new StringReader("");
		ArrayList<Stock> list = DataBase.loadStocks(reader);
		assertEquals(0, list.size());
	}

	// Skips lines with corrupted data
	@Test
	public void testLoadStocksCorrupteds() throws IOException, ParseException {
		Reader reader = new StringReader("product1;20;true\n" + "product2\n"
				+ "product3;9;true\n");
		ArrayList<Stock> list = DataBase.loadStocks(reader);
		assertEquals(2, list.size());
	}
	
	//TEST FOR WRITING DATA METHODS
	//Writes a user
	@Test
	public void testwriteUsers() throws IOException, ParseException {
		Writer userFile = new StringWriter();
		DataBase.writeUsers(usersTest,userFile);
		assertEquals("1111;Cris;passw10;Attendant;true",userFile.toString().trim());
	}
	
	//Writes a customer 
	public void testwriteCustomers() throws IOException, ParseException {
		Writer userFile = new StringWriter();
		DataBase.writeCustomers(customersTest,userFile);
		assertEquals("customer1;Scott Scott;address 1 Dublin;089665544",userFile.toString().trim());
	}
	
	//Writes a supplier
	public void testwriteSuppliers() throws IOException, ParseException {
		Writer userFile = new StringWriter();
		DataBase.writeSuppliers(suppliersTest,userFile);
		assertEquals("supplier1;Supplier1 Name;083776655;true",userFile.toString().trim());
	}
	
	//Writes a product
	public void testwriteSProductss() throws IOException, ParseException {
		Writer userFile = new StringWriter();
		DataBase.writeProducts(productsTest,userFile);
		assertEquals("product1;ProductPC1;1000;200;supplier1;true",userFile.toString().trim());
	}
	
	//Writes a order
	@Test
	public void testwriteOrders() throws IOException, ParseException {
		String datestring =DateFormat.getDateInstance().format(date);
		Writer userFile = new StringWriter();
		DataBase.writeOrders(ordersTest,userFile);
		assertEquals("order10;"+datestring+";product1;10;"+datestring+";"+datestring+";false"+";true",userFile.toString().trim());
	}
	
	//Writes a stock
	@Test
	public void testwriteStocks() throws IOException, ParseException {
		Writer userFile = new StringWriter();
		DataBase.writeStocks(stocksTest,userFile);
		assertEquals("product1;10;true",userFile.toString().trim());
	}

}
