import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


public class CustomerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReturnIDfromCombobox() {
		assertEquals("Customer1", Customer.returnIDfromCombobox("ID: Customer1 ; Name: CustomerName"));
	}

}
