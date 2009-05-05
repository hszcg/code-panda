package org.codepanda.utility.user;

import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.contact.ContactOperations;

public class User implements UserOperations,ContactOperations {
	private ContactData userContactData;
	
	private String userName;
	private String password;

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int initializeUserInformation(String initializeString) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int varifyUserPassword(String inputPassword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void editContactInformation(String editString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initializeContactInformation(String initializeString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int editUserInformation(String editString) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
