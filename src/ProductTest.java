import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class ProductTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Supplier supplier = new Supplier("HHHHHHH", "Supplier Store Place", "087326892");
		Product product1 = new Product("5783JKDS", "Computer", 500.66, 200.00, supplier);
		Product product2 = new Product("5783JKDS", "Another Computer", 7923.00, 100.00, supplier);
		
	}

	@Test
	public String addProductToList() {
		String string = addProductToList(product1);
		assertEquals("Function to add Product to list has run", string);
	}

}
