package org.codepanda.database;

import java.sql.SQLException;
import java.util.Vector;

import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.user.User;

public interface DatabaseManager {
	// user
	public int newUser(User user);
	public int getUser(String username, User user);
	public int editUser(String username, User user);
	public int delUser(User user);
	public int checkExistUser(String userName);
	// contact
	public int newContact(User user, ContactData contact);
	public int getContactData(String userName, Vector<ContactData> contactList);
	public int editUser(User user, ContactData contact);
	public int deleteContact(User user, ContactData contact);
	// operation
	public int open(String db_name) throws SQLException;
	public int close() throws SQLException;
}

