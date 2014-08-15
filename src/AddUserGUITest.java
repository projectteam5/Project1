import static org.junit.Assert.assertTrue;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


public class AddUserGUITest {

	private ArrayList<User> userStatus = RetailSystem.getInstance()
			.getUsers();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Reader reader = new StringReader("1111;Scott Scott;passw1;Manager\n"
				+ "1112;Daniel Daniel;passw2;Attendant\n");
		ArrayList<User> users = DataBase.loadUsers(reader);
		RetailSystem.getInstance().setUsers(users);
	}

	@After
	public void tearDown() throws Exception {
		RetailSystem.getInstance().setUsers(userStatus);
	}

	@Test
	public void testValidateUserNewUser() {
		assertTrue(AddUserGUI.validateUser("1113", "name", "password", "Manager"));
	}
	@Test
	public void testValidateUserExistingUser() {
		assertTrue(!AddUserGUI.validateUser("1111", "name", "password", "Manager"));
	}

}
