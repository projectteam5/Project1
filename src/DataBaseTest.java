import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class DataBaseTest {

	private ArrayList<Supplier> supplierStatus = RetailSystem.getInstance()
			.getSuppliers();
	private ArrayList<Product> productStatus = RetailSystem.getInstance()
			.getProducts();

	// TEST FOR LOADING FILE
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Reader reader = new StringReader("supplier1;Supplier1 Name;083776655\n"
				+ "supplier2;Supplier2 Name;083775839\n");
		ArrayList<Supplier> suppliers = DataBase.loadSuppliers(reader);
		RetailSystem.getInstance().setSuppliers(suppliers);
		Reader reader1 = new StringReader(
				"product1;ProductPC1;1000;200;supplier1\n"
						+ "product2;ProductPC2;2000;400;supplier1\n"
						+ "product3;ProductPC3;1500;300;supplier2\n");
		ArrayList<Product> products = DataBase.loadProducts(reader1);
		RetailSystem.getInstance().setProducts(products);
	}

	// Test for Users
	@Test
	public void testLoadUsersOK2Users() throws IOException {
		Reader reader = new StringReader("1111;Scott Scott;passw1;Manager\n"
				+ "1112;Daniel Daniel;passw2;Attendant\n");
		ArrayList<User> list = DataBase.loadUsers(reader);
		assertEquals(2, list.size());
	}

	@Test
	public void testLoadUsersEmpty() throws IOException {
		Reader reader = new StringReader("");
		ArrayList<User> list = DataBase.loadUsers(reader);
		assertEquals(0, list.size());
	}

	@Test
	public void testLoadUsersCorrupted() throws IOException {
		Reader reader = new StringReader("1111;Scott Scott;pa");
		ArrayList<User> list = DataBase.loadUsers(reader);
		assertEquals(0, list.size());
	}

	// Test for Suppliers
	@Test
	public void testLoadSuppliersOK2Suppliers() throws IOException {
		Reader reader = new StringReader("supplier1;Supplier1 Name;083776655\n"
				+ "supplier2;Supplier2 Name;083775839\n");
		ArrayList<Supplier> list = DataBase.loadSuppliers(reader);
		assertEquals(2, list.size());
	}

	@Test
	public void testLoadSupplierEmpty() throws IOException {
		Reader reader = new StringReader("");
		ArrayList<Supplier> list = DataBase.loadSuppliers(reader);
		assertEquals(0, list.size());
	}

	@Test
	public void testLoadSupplierCorrupted() throws IOException {
		Reader reader = new StringReader("supplier1;Supplier1 Nam");
		ArrayList<Supplier> list = DataBase.loadSuppliers(reader);
		assertEquals(0, list.size());
	}

	// Test for Products
	@Test
	public void testLoadProductsOK3Products() throws IOException {
		Reader reader = new StringReader(
				"product1;ProductPC1;1000;200;supplier1\n"
						+ "product2;ProductPC2;2000;400;supplier1\n"
						+ "product3;ProductPC3;1500;300;supplier2\n");
		ArrayList<Product> list = DataBase.loadProducts(reader);
		assertEquals(3, list.size());
	}

	@Test
	public void testLoadProductEmpty() throws IOException {
		Reader reader = new StringReader("");
		ArrayList<Product> list = DataBase.loadProducts(reader);
		assertEquals(0, list.size());
	}

	@Test
	public void testLoadProductCorrupted() throws IOException {
		Reader reader = new StringReader("product1;ProductPC1;1000;2");
		ArrayList<Product> list = DataBase.loadProducts(reader);
		assertEquals(0, list.size());
	}

	// Test for Orders
	@Test
	public void testLoadOrderOK2Orders() throws IOException, ParseException {
		Reader reader = new StringReader(
				"order1;10/08/2014;product1;10;20/08/2014;;false\n"
				+ "order1;01/08/2014;product2;9;08/08/2014;08/08/2014;false\n");
		ArrayList<Order> list = DataBase.loadOrders(reader);
		assertEquals(2, list.size());
	}

	@Test
	public void tearDown() {
		RetailSystem.getInstance().setSuppliers(supplierStatus);
		RetailSystem.getInstance().setProducts(productStatus);
	}

}
