import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class OrderTest {
	
	private static Supplier supplier;
	
	private static Product product;
	
	private ArrayList<Order> orders;
	
	private static Order order;
	
	private static Date date1;

	private static Date date2;

	private static Date date3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		order = new Order(date1, product, 10, date2, date3, false);
		
	}

	@Before	//called before every test method
	public void setUp() throws Exception {
		
		//setUp the test
		
		supplier = new Supplier("SUP001", "Supplier");
		
		product = new Product("PRD001", "Product", 100, 25, supplier, false);
		
		orders = new ArrayList<Order>();
		
		date1 = new Date();
		
		date2 = new Date();
		
		date3 = new Date();
		
		order = new Order(date1, product, 10, date2, date3, false);
		
		
	}

	@After	//called after every test method
	public void tearDown() throws Exception {
		
		//tearDown the test
		
		orders = null;
		
	}
	
	@Test
	public void testArrayList() {
		
		//although orders created, we have not added any objects to the list
		
		assertEquals("list should have 0 elements", 0, orders.size());
		
	}
	
	
	@SuppressWarnings("unused")
	@Test (expected = IndexOutOfBoundsException.class)
	public void testForException() {
		
		//because we have orders at null (no objects at element 0), nothing to get - exception thrown
		
		Order o = orders.get(0);
		
	}
	
	@Test
	public void testAddOrderToList() {
		
		assertEquals("list should have 0 elements", 0, orders.size());
		
		orders.add(order);
		
		assertEquals("list should have 1 element", 1, orders.size());
		
		
	}

	@Test
	public void testRemoveOrderFromList() {
		
		orders.add(order);
		
		assertEquals("list should have 1 element", 1, orders.size());
		
		orders.remove(0);
		
		assertEquals("list should have 0 elements", 0, orders.size());
		
	}

	@Test
	public void testReceivedOrder() {
		
		boolean test = true;
		
		boolean result;
		
		result = order.receivedOrder(date2, date3);
		
		assertEquals(result, test);
		
	}

}
