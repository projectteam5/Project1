import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


public class ProductTest {
	private ArrayList<Supplier> supplierStatus = RetailSystem.getInstance().getSuppliers();
	private ArrayList<Product> productStatus = RetailSystem.getInstance().getProducts();
	private static Supplier supplier;
	private static Product product1;
	private static Product product2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//create a Suppliers ArrayList for test
		Reader reader = new StringReader("supplier1;Supplier1 Name;083776655;true\n"+ "supplier2;Supplier2 Name;083775839;true\n");
		ArrayList<Supplier> suppliers = DataBase.loadSuppliers(reader);
		RetailSystem.getInstance().setSuppliers(suppliers);
		//create a Products ArrayList for test
		Reader reader1 = new StringReader("product1;ProductPC1;1000;200;supplier1;true\n"+ "product2;ProductPC2;2000;400;supplier1;true\n"+ "product3;ProductPC3;1500;300;supplier2;true\n");
		ArrayList<Product> products = DataBase.loadProducts(reader1);
		RetailSystem.getInstance().setProducts(products);

		supplier = new Supplier("HHHHHHH", "Supplier Store Place", "087326892");
		product1 = new Product("5783JKDS", "Computer", 500.66, 200.00, supplier);
		product2 = new Product("5783JKDS", "Another Computer", 7923.00, 100.00, supplier);


	}

	@After
	public void tearDown() throws Exception {
		RetailSystem.getInstance().setSuppliers(supplierStatus);
		RetailSystem.getInstance().setProducts(productStatus);
	}
	
	@Test
	public void addProductToListTest() {
		String string = Product.addProductToList(product1);
		assertEquals("5783JKDS", string);
	}

}
