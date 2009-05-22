package org.codepanda.database;

import java.sql.SQLException;
import java.util.ArrayList;

import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.user.User;

public interface DatabaseManagerFacade {
	// user
	public int newUser(final User user);
	public int getUser(String userName, final User user);
	public int editUser(String userName, final User user);	
	public int delUser(final User user);					
	public int checkExistUser(String userName);				
	public int loginUser(String userName, String password);
	
	// contact
	public int newContact(String userName,final PersonalContact contact);
	public int getContactData(String userName,final ArrayList<ContactOperations> contactList);
	//这里有名字的小错误，我帮你改了
	//public int editUser(final User user, final ContactData contact);
	public int editContact(final User user, final PersonalContact contact);		
	public int deleteContact(final User user, final PersonalContact contact);	
	// label
	public int newLabel(String labelname, String username);						
	public int editCommonlabel(String labelname);								
	public int delCommonlabel(String labelname);								
	// operation
	public int open(String dbName) throws SQLException;
	public int close() throws SQLException;
}

