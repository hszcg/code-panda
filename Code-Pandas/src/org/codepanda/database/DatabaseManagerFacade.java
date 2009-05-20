package org.codepanda.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.user.User;

public interface DatabaseManagerFacade {
	// user
	public int newUser(User user);
	public int getUser(String userName, User user);
	public int editUser(String userName, User user);
	public int delUser(User user);
	public int checkExistUser(String userName);
	public int loginUser(String userName, String password);
	
	// contact
	public int newContact(String userName, ContactData contact);
	public int getContactData(String userName, ArrayList<ContactData> contactList);
	public int editUser(User user, ContactData contact);
	public int deleteContact(User user, ContactData contact);
	// operation
	public int open(String dbName) throws SQLException;
	public int close() throws SQLException;
}

