import static org.junit.Assert.assertEquals;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


public class LoginGUITest {

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
	public void testLoginValidationUserFound() {
		assertEquals(0, LoginGUI.loginValidation("1111", "passw1"));
	}
	
	@Test
	public void testLoginValidationUserEmpty() {
		assertEquals(1, LoginGUI.loginValidation("", ""));
	}
	
	@Test
	public void testLoginValidationUserIncomplete1() {
		assertEquals(1, LoginGUI.loginValidation("1111", ""));
	}
	
	@Test
	public void testLoginValidationUserIncomplete2() {
		assertEquals(1, LoginGUI.loginValidation("", "passw1"));
	}
	
	@Test
	public void testLoginValidationUserNotFound() {
		assertEquals(2, LoginGUI.loginValidation("1234", "passw1"));
	}
	


}
