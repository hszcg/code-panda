package org.codepanda.database;

import java.sql.SQLException;

import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.user.User;

public interface DatabaseManager {
	
	public void newUser(User user);
	public User getUser(String username);
	public User editUser(String username, User user);
	public int delUser(User user);
	public boolean checkExistUser(String userName);
	
	public void newContact(User user, ContactData contact);
	public ContactData getContactData(User user, ContactData contact);
	public ContactData editUser(User user, ContactData contact);
	public void deleteContact(User user, ContactData contact);
	
	public void open(String db_name) throws SQLException;
	public void close() throws SQLException;
}
