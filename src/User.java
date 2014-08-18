import java.io.FileWriter;

import javax.swing.JOptionPane;



public class User {

	//User attributes
	private String userID;
	private String name;
	private String password;
	private String type;
	
		
	public User(String userID, String name, String password, String type) {
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.type = type;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	public static boolean existingUser(String id){
		boolean userOk = false;
		for(User user : RetailSystem.getInstance().getUsers()){
			if(user.getUserID().equals(id)){
				userOk = true;
			}
		}
		return userOk;
	}
	
	public static User retrieveUser(String id){
		User userRet = null;
		for(User user : RetailSystem.getInstance().getUsers()){
			if(user.getUserID().equals(id)){
				userRet = user;
			}
		}
		return userRet;
	}
	
	public static void saveUser(){
		try {
			FileWriter userFile;
			userFile = new FileWriter("users.txt");
			DataBase.writeUsers(RetailSystem.getInstance().getUsers(), userFile);
			userFile.close();// close the user file
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public static String[] userListComplete() {
		String[] userList = new String[RetailSystem.getInstance().getUsers()
				.size()];
		int i = 0;
		for (User user : RetailSystem.getInstance().getUsers()) {
			userList[i] = "ID: " + user.getUserID() + " ; Name: "
					+ user.getName();
			i++;
		}
		return userList;
	}

}
