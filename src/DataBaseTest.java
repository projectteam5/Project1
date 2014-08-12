
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataBaseTest {

	// TEST FOR LOADING FILE
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Reader reader = new StringReader("supplier1;Supplier1 Name;083776655\n"
				+ "supplier2;Supplier2 Name;083775839\n");
		ArrayList<Supplier> suppliers = DataBase.loadSuppliers(reader);
		
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
	/*
	// Test for Products I need to update the test because he need the supplier list to be validate
		@Test
		public void testLoadProductsOK2Products() throws IOException {
			Reader reader = new StringReader("product1;ProductPC1;1000;200;supplier1\n"
					+"product2;ProductPC2;2000;400;supplier1\n"
					+"product3;ProductPC3;1500;300;supplier2\n");
			ArrayList<Product> list = DataBase.loadProducts(reader);
			assertEquals(3, list.size());
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
		}*/

}
