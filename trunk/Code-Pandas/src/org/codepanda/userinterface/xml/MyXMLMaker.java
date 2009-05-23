package org.codepanda.userinterface.xml;

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
