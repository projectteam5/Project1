import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


public class RetailSystemTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidatePhoneOK() {
		assertTrue(RetailSystem.validatePhone("0897653689"));
	}
	@Test
	public void testValidatePhoneOK1() {
		assertTrue(RetailSystem.validatePhone("123"));
	}
	@Test
	public void testValidatePhoneOK2() {
		assertTrue(RetailSystem.validatePhone("35389498765"));
	}

}
