package org.codepanda.userinterface.xml;

import org.codepanda.utility.user.User;

/**
 * @author hszcg
 * 
 */
public class MyXMLMaker {
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String getLoginUserXML(String userName, String password) {
		String loginUserXML = addTag("LoginUser", addTag("UserName", userName)
				+ addTag("UserPassword", password));
		return addTag("com", loginUserXML);
	}

	/**
	 * @param myUser
	 * @return
	 */
	public static String getNewUserXML(User myUser) {
		StringBuffer newUserXML = new StringBuffer();
		newUserXML.append(addTag("UserName", myUser.getUserName()));
		newUserXML.append(addTag("UserPassword", myUser.getPassword()));

		// TODO Add Other User Info

		return addTag("com", addTag("NewUser", newUserXML.toString()));
	}

	/**
	 * @param String
	 *            tag
	 * @param String
	 *            value
	 * @return String <tag>value</tag>
	 */
	public static String addTag(String tag, String value) {
		String result = '<' + tag + '>' + value + "</" + tag + '>';
		return result;
	}
}
