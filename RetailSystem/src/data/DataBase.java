package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import model.User;

public class DataBase {
	//method for loading users in the Array List
	public static ArrayList<User> loadUsers(Reader reader) throws IOException {
		ArrayList<User> users = new ArrayList<User>();
		BufferedReader bufReader = new BufferedReader(reader);
		String line = null;
		int count = 0;
		while ((line = bufReader.readLine()) != null) {
			String[] parts = line.split(";");
			count++;
			if (parts.length == 4 ){
				String userID = parts[0];
				String name = parts[1];
				String password = parts[2];
				String type = parts[3];
				User user = new User(userID, name, password, type);
				users.add(user);
			}
			else{
				System.err.println("Skipping corrupted user at line "+ count);
			}
			
		}
		return users;
	}
	
	public static void writeUsers (ArrayList<User> users, Writer writer) throws IOException{
			BufferedWriter out = new BufferedWriter(writer);
			for(User user : users){
				 out.write(user.getUserID()+";"+user.getName()+";"+user.getPassword()+";"+user.getType()+"\n");
			}

	}
	

}
