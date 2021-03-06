import java.io.FileWriter;

import javax.swing.JComboBox;



public class User {

	//User attributes
	private String userID;
	private String name;
	private String password;
	private String type;
	private boolean active;
	
		
	public User(String userID, String name, String password, String type, boolean active) {
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.type = type;
		this.active = active;
	}
	
	public User(String name, String password, String type) {
		this.userID = "User"+(RetailSystem.getInstance().getUsers().size()+1);
		this.name = name;
		this.password = password;
		this.type = type;
		this.active = true;
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
	
	
	
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
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
			userFile = new FileWriter("RetailSystem/users.txt");
			DataBase.writeUsers(RetailSystem.getInstance().getUsers(), userFile);
			userFile.close();// close the user file
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public static void userListComplete(JComboBox dropdown) {
		for (User user : RetailSystem.getInstance().getUsers()) {
			if(user.isActive()){
				String string = "ID: " + user.getUserID() + " ; Name: "
						+ user.getName(); 
				dropdown.addItem(string);
			}
		}
	}
	public static void userListCompleteInactive(JComboBox dropdown) {
		for (User user : RetailSystem.getInstance().getUsers()) {
			if(!user.isActive()){
				String string = "ID: " + user.getUserID() + " ; Name: "
						+ user.getName(); 
				dropdown.addItem(string);
			}
		}
	}
	
	public static boolean validateUser(String name, String password, String type) {
		boolean userOk = false;
		if (name != null && password != null && type != null && !name.isEmpty()
				&& !password.isEmpty() && !type.isEmpty()) {
			userOk = true;
		}
		return userOk;
	}
	


}
