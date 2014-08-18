import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


public class UserTest {

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
	public void testExistingUserTrue() {
		assertTrue(User.existingUser("1111"));
	}
	
	@Test
	public void testExistingUserFalse() {
		assertFalse(User.existingUser("2222"));
	}

	@Test
	public void testRetrieveUserFound() {
		User user1 = new User("1111","Scott Scott","passw1","Manager");
		assertEquals(user1.getUserID(), User.retrieveUser("1111").getUserID());
	}
	
	@Test
	public void testRetrieveUserNotFound() {
		assertEquals(null, User.retrieveUser("2222"));
	}

	@Test
	public void testUserListComplete() {
		assertEquals(2, User.userListComplete().length);
	}

}
