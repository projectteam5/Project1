import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
//	NOTE!!!!!!!!!!!!
//	When running the unit tests, error messages are being returned and the test does not run
//	This applies to others unit tests that are working on other machines. Problem is unresolved.


// Aside from the usual getters/setters the supplier class is used to
// add and delete(unused presently) a supplier to the list, return the supplier
// list and save the new updated list to a database class.

public class SupplierTest {
	private ArrayList<Supplier> supplierStatus = RetailSystem.getInstance().getSuppliers();
	private static Supplier supplier, supplier1;
	private RetailSystem retailSystem=null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//create a Suppliers ArrayList for test
		Reader reader = new StringReader("1; Name1;083776655;true\n"+ "2;Name2;083775839;true\n");
		ArrayList<Supplier> suppliers = DataBase.loadSuppliers(reader);
		RetailSystem.getInstance().setSuppliers(suppliers);

		supplier = new Supplier("1","Name1","083776655");
		supplier1 = new Supplier("2","Name2","083775839");
		
	}
	
	@After
	public void tearDown() throws Exception {
		RetailSystem.getInstance().setSuppliers(supplierStatus);
	}
	
	@Test
	public void testAddSupplierToList() {
		// Two suppliers exist on list on list
		// Adding one more should equal three
		supplier.addSupplierToList(supplier);
		assertEquals(3, retailSystem.getSuppliers().size());	
	}

	@Test
	public void testRemoveSupplierFromList(Supplier supplier) {
		// There should now be two suppliers on the list
		// that is 3- (removed object)
		supplier1.removeSupplierFromList(supplier);
		assertEquals(2, retailSystem.getSuppliers().size());
	}

	@Test
	public ArrayList<Supplier> testGetSupplierList() {
		// this test tests that the returned list is equal to what it should of returned
		// It tests that the objects are also the same.
		ArrayList<Supplier> newSupplierList = supplier1.getSupplierList();
		assertEquals(newSupplierList.equals(0),supplier.getSupplierList().equals(0));
		assertEquals(newSupplierList.equals(1),supplier.getSupplierList().equals(1));
		return newSupplierList;
	}
	
	// Unable to test at present, beyond scope of my current knowledge. will research though
	/*
	@Test
	
	public void testSaveUser() {
		supplier1.saveUser();
		assertEquals();
	}*/

}
