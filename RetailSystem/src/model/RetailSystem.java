package model;

import java.io.FileReader;
import java.util.ArrayList;

import view.LoginGUI;
import data.DataBase;

public class RetailSystem {

	private static RetailSystem instance;
	private ArrayList<User> users;
	private String currentUserType;
	
	public RetailSystem() {	}
	
	// we want a unique instance that every class can refer to
	// hence, we create the object only once and we save it in a static variable
	public static RetailSystem getInstance() {
		if (instance == null) {
			instance = new RetailSystem();
		}
		return instance;
	}

	public static void main(String[] args) {
		FileReader userFile;
		RetailSystem retailSystem = getInstance();
		try {
			userFile = new FileReader(args[0]);
			retailSystem.setUsers(DataBase.loadUsers(userFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LoginGUI login = new LoginGUI();

	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public String getCurrentUserType() {
		return currentUserType;
	}

	public void setCurrentUserType(String currentUserType) {
		this.currentUserType = currentUserType;
	}

	public static void setInstance(RetailSystem instance) {
		RetailSystem.instance = instance;
	}

}
