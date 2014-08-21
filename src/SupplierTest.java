import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
	
	@BeforeClass // Executed once before start of all tests, used for intensive
				//	activities such as connecting to a database
	public static void setUpBeforeClass() throws Exception {
		//create a Suppliers ArrayList for test
		Reader reader = new StringReader("1; Name1;083776655;true\n"+ "2;Name2;083775839;true\n");
		ArrayList<Supplier> suppliers = DataBase.loadSuppliers(reader);
		RetailSystem.getInstance().setSuppliers(suppliers);

		supplier = new Supplier("3","Name3","55");
		supplier1 = new Supplier("4","Name4","39");
		
	}
	
	@After
	public void tearDown() throws Exception {
		RetailSystem.getInstance().setSuppliers(supplierStatus);
	}
	
	@Test
	// Two newly created suppliers should be added to the list in the 3rd and 4th
	// position. 
	public void testAddSupplierToList() {
		// Two suppliers exist on list on list
		// Adding one more should equal three
		supplier.addSupplierToList(supplier);
		assertEquals(supplier, RetailSystem.getInstance().getSuppliers().get(0));
		supplier.addSupplierToList(supplier1);
		assertEquals(supplier1, RetailSystem.getInstance().getSuppliers().get(1));
	}

	@Test
	// The two suppliers created in setup before class are removed so that
	// the arrayList should be empty after removal
	public void testRemoveSupplierFromList() {
		Supplier removeSupplier1 = RetailSystem.getInstance().getSuppliers().get(0);
		Supplier removeSupplier = RetailSystem.getInstance().getSuppliers().get(1); 
		supplier1.removeSupplierFromList(removeSupplier);
		supplier1.removeSupplierFromList(removeSupplier1);
		assertEquals(0, RetailSystem.getInstance().getSuppliers().size());
	}

	@Test
	public void testGetSupplierList() {
		// this test tests that the returned list is equal to what it should of returned
		// It tests that the objects are also the same.
		ArrayList<Supplier> getList = supplier.getSupplierList();
		assertEquals(RetailSystem.getInstance().getSuppliers(),getList);		
	}
	
	// Unable to test at present, beyond scope of my current knowledge. I do not know how to 
	// get the contents of a file and compare with an expected file
	
	@Ignore
	
	public void testSaveUser() {
		supplier.addSupplierToList(supplier);
		supplier1.addSupplierToList(supplier1);
		//BufferedWriter out = new BufferedWriter(arg0);
		supplier1.saveUser();// I know its save user but i'm calling it in supplier class so it saves supplier
		Reader getReader = new StringReader("1; Name1;083776655;true\n"+ "2;Name2;083775839;true\n"+
				"3; Name3;55;true\n"+ "4;Name4;39;true\n");
		//Reader actualReader 
		
	}

}
