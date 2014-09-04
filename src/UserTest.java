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
		Reader reader = new StringReader("User1;Scott Scott;passw1;Manager;true\n"
				+ "User2;Daniel Daniel;passw2;Attendant;true\n");
		ArrayList<User> users = DataBase.loadUsers(reader);
		RetailSystem.getInstance().setUsers(users);
	}

	@After
	public void tearDown() throws Exception {
		RetailSystem.getInstance().setUsers(userStatus);
	}

	@Test
	public void testExistingUserTrue() {
		assertTrue(User.existingUser("User1"));
	}
	
	@Test
	public void testExistingUserFalse() {
		assertFalse(User.existingUser("2222"));
	}

	@Test
	public void testRetrieveUserFound() {
		User user1 = new User("Scott Scott","passw1","Manager");
		RetailSystem.getInstance().getUsers().add(user1);
		assertEquals(user1.getUserID(), User.retrieveUser("User3").getUserID());
	}
	
	@Test
	public void testRetrieveUserNotFound() {
		assertEquals(null, User.retrieveUser("2222"));
	}
	
	@Test
	public void testValidateUserNewUser() {
		assertTrue(User.validateUser( "name", "password", "Manager"));
	}
	
	@Test
	public void testValidateUserEmpty() {
		assertFalse(User.validateUser("", "", "Manager"));
	}
	
	@Test
	public void testValidateUserNull() {
		assertFalse(User.validateUser(null, "", "Manager"));
	}

}
