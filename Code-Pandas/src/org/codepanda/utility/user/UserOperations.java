/**
 * 
 */
package org.codepanda.utility.user;

/**
 * @author hszcg
 * @version 4.17.01
 */
public interface UserOperations {
	/**
	 * @return
	 */
	public String getUserName();
	
	/**
	 * @param inputPassword
	 * @return
	 */
	public int varifyUserPassword(String inputPassword);
	
	/**
	 * @param initializeString
	 * @return
	 */
	public int initializeUserInformation( String initializeString );
	
	/**
	 * @param editString
	 * @return
	 */
	public int editUserInformation( String editString );
}
