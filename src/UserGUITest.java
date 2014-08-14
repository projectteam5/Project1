import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


public class UserGUITest {

	private ArrayList<User> userStatus = RetailSystem.getInstance()
			.getUsers();
	private String userIDCurrentLogin = RetailSystem.getInstance().getCurrentUserID();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Reader reader = new StringReader("1111;Scott Scott;passw1;Manager\n"
				+ "1112;Daniel Daniel;passw2;Attendant\n");
		ArrayList<User> users = DataBase.loadUsers(reader);
		RetailSystem.getInstance().setUsers(users);
		RetailSystem.getInstance().setCurrentUserID("1111");
	}

	@After
	public void tearDown() throws Exception {
		RetailSystem.getInstance().setUsers(userStatus);
		RetailSystem.getInstance().setCurrentUserID(userIDCurrentLogin);
	}

	@Test
	public void testExistingIDUserFound() {
		assertTrue(UserGUI.existingID("1111"));
	}
	@Test
	public void testExistingIDUserNotFound() {
		assertFalse(UserGUI.existingID("1234"));
	}

	@Test
	public void testNotLoginIDLoggedOk() {
		assertFalse(UserGUI.notLoginID("1111"));
	}
	
	@Test
	public void testNotLoginIDNotLogged() {
		assertTrue(UserGUI.notLoginID("1112"));
	}
	
}
