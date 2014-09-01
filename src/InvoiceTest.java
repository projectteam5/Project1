import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


public class InvoiceTest {

	// TEST FOR LOADING FILE

	private ArrayList<Supplier> supplierStatus = RetailSystem.getInstance()
			.getSuppliers();
	private ArrayList<Product> productStatus = RetailSystem.getInstance()
			.getProducts();
	private ArrayList<Customer> customerStatus = RetailSystem.getInstance()
			.getCustomers();
	private ArrayList<User> usersStatus = RetailSystem.getInstance().getUsers();
	private ArrayList<Order> ordersStatus = RetailSystem.getInstance()
			.getOrders();
	private ArrayList<Sale> salesStatus = RetailSystem.getInstance().getSales();
	private ArrayList<Invoice> invoiceStatus = RetailSystem.getInstance().getInvoices();
	private static ArrayList<Invoice> invoicesTest;
	private static ArrayList<Sale> salesTest;
	private static Date date = new Date();

	/*
	 * it is necessary to initialize a supplier arrayList for the product
	 * loading it is necessary to initialize a product arrayList for the order
	 * loading
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// create a Suppliers ArrayList for test
		Reader reader = new StringReader(
				"supplier1;Supplier1 Name;083776655;true\n"
						+ "supplier2;Supplier2 Name;083775839;true\n");
		ArrayList<Supplier> suppliers = DataBase.loadSuppliers(reader);
		RetailSystem.getInstance().setSuppliers(suppliers);
		// create a Products ArrayList for test
		Reader reader1 = new StringReader(
				"product1;ProductPC1;1000;200;supplier1;true\n"
						+ "product2;ProductPC2;2000;400;supplier1;true\n"
						+ "product3;ProductPC3;1500;300;supplier2;true\n");
		ArrayList<Product> products = DataBase.loadProducts(reader1);
		RetailSystem.getInstance().setProducts(products);

		// create a Customers ArrayList for test
		Reader reader2 = new StringReader(
				"customer1;Scott Scott;address 1 Dublin;089665544;true\n"
						+ "customer2;Henry Bo;address 2 Cork;089334455;true\n");
		ArrayList<Customer> customers = DataBase.loadCustomers(reader2);
		RetailSystem.getInstance().setCustomers(customers);

		// create a User ArrayList for test
		Reader reader3 = new StringReader("");
		ArrayList<User> users = DataBase.loadUsers(reader3);
		RetailSystem.getInstance().setUsers(users);

		// create a Order ArrayList for test
		Reader reader4 = new StringReader("");
		ArrayList<Order> orders = DataBase.loadOrders(reader4);
		RetailSystem.getInstance().setOrders(orders);
		
		// create a Invoice ArrayList for test
		Reader reader5 = new StringReader("");
		ArrayList<Invoice> invoices = DataBase.loadInvoices(reader5);
		RetailSystem.getInstance().setInvoices(invoices);

		// create a Sale ArrayList for test
		ArrayList<Sale> sales = new ArrayList<Sale>();
		RetailSystem.getInstance().setSales(sales);

		Sale sale = new Sale();
		LineItem lineItem1 = new LineItem(RetailSystem.getInstance()
				.getProducts().get(0), 3);
		LineItem lineItem2 = new LineItem(RetailSystem.getInstance()
				.getProducts().get(1), 1);
		sale.addLineItem(lineItem1);
		sale.addLineItem(lineItem2);
		Invoice invoice1 = new Invoice(date, RetailSystem
				.getInstance().getCustomers().get(0), 3600, sale);
		invoicesTest = new ArrayList<Invoice>();
		invoicesTest.add(invoice1);
		RetailSystem.getInstance().setInvoices(invoicesTest);
		//System.err.println(invoicesTest.get(0).getSale().getLineItems().get(1).getQuantity());

	}

	/*
	 * at the end of the test phase the arraylists previously initialized are
	 * reset with their original values
	 */
	@After
	public void tearDown() throws Exception {
		RetailSystem.getInstance().setSuppliers(supplierStatus);
		RetailSystem.getInstance().setProducts(productStatus);
		RetailSystem.getInstance().setCustomers(customerStatus);
		RetailSystem.getInstance().setUsers(usersStatus);
		RetailSystem.getInstance().setOrders(ordersStatus);
		RetailSystem.getInstance().setSales(salesStatus);
		RetailSystem.getInstance().setInvoices(invoiceStatus);
	}


	@Test
	public void testRetrieveInvoiceWithID() {
		Invoice retrieveInvoice = Invoice.retrieveInvoiceWithID("Invoice1");
		System.err.println(retrieveInvoice.getInvoiceID());
		assertEquals(invoicesTest.get(0).getInvoiceID(), retrieveInvoice.getInvoiceID());
	}

	@Test
	public void testQuantityProductSoldMonthly() {
		assertEquals(Invoice.quantityProductSoldMonthly(RetailSystem.getInstance().getProducts().get(0), 8, 114),3);
	}
	
	@Test
	public void testQuantityProductSoldMonthly1() {
		assertEquals(Invoice.quantityProductSoldMonthly(RetailSystem.getInstance().getProducts().get(1), 8, 114),1);
	}

}
