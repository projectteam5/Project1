package data;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import model.User;

import org.junit.Test;

public class DataBaseTest {

	//TEST FOR LOADING FILE
	@Test
	public void testLoadUsersOK2Users() throws IOException {
		Reader reader = new StringReader("1111;Scott Scott;passw1;Manager\n"
				+ "1112;Daniel Daniel;passw2;Attendant\n");
		ArrayList<User> list = DataBase.loadUsers(reader);
		assertEquals(2, list.size());
	}
	
	@Test
	public void testLoadUsersEmpty() throws IOException {
		Reader reader = new StringReader("");
		ArrayList<User> list = DataBase.loadUsers(reader);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testLoadUsersCorrupted() throws IOException {
		Reader reader = new StringReader("1111;Scott Scott;pa");
		ArrayList<User> list = DataBase.loadUsers(reader);
		assertEquals(0, list.size());
	}

}
