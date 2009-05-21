/**
 * 
 */
package org.codepanda.utility.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.codepanda.database.DatabaseMagager;
import org.codepanda.database.DatabaseManagerFacade;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.group.ContactGroup;
import org.codepanda.utility.group.GroupType;
import org.codepanda.utility.label.CommonLabel;
import org.codepanda.utility.user.User;

import com.google.common.collect.HashMultimap;

/**
 * @author hszcg
 * @version 4.17.01
 * 
 *          Singleton Pattern
 * 
 */
public class DataPool {
	private DataPool() {
		// TODO Initialize all data except for dataPoolInstance
		currentUser = new User();
		try {
			db = new DatabaseMagager("test");
			db.open("test");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		allContactISNMap = new HashMap<Integer, ContactOperations>();
		allContactGroupMap = new HashMap<String, ContactGroup>();
		allCommonLabelDataMap = new HashMap<String, ContactGroup>();
		allRelationLabelList = new ArrayList<String>();

		currentUser = new User();

		// 1个Key对应多个Value的HashMap
		allContactNameMultimap = HashMultimap.create();

		User user = new User();

		user.setUserName("leilei");
		user.setPassword("leilei");

		System.out.println("DataPoolInit");
		PersonalContact contact1 = new PersonalContact();
		PersonalContact contact2 = new PersonalContact();
		PersonalContact contact3 = new PersonalContact();
		contact1.setContactName("汤则1");
		contact2.setContactName("汤则2");
		contact3.setContactName("汤则3");
		db.newUser(user);
		db.newContact("leilei", contact1);
		// db.newContact("leilei", contact2);
		// db.newContact("leilei", contact3);
		/*
		 * try { db.close(); } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		System.out.println("close database!");
	}

	private static DataPool dataPoolInstance = new DataPool();

	/*
	 * Database part
	 */
	private DatabaseManagerFacade db;

	/**
	 * @return
	 */
	public static DataPool getInstance() {
		return dataPoolInstance;
	}

	private HashMap<Integer, ContactOperations> allContactISNMap;
	private HashMap<String, ContactGroup> allContactGroupMap;
	private HashMap<String, ContactGroup> allCommonLabelDataMap;
	private ArrayList<String> allRelationLabelList;
	private User currentUser;

	// 1个Key对应多个Value的HashMap
	private HashMultimap<String, Integer> allContactNameMultimap;

	public int createNewUser(User newUser) {
		// TODO Auto-generated method stub
		// 剩下的事情就是Utility向数据库检查合法性checkExistUser()，和更新数据库 createNewUserData()
		// 最后Utility更新DataPool并返回结果即可
		currentUser = newUser;
		if (DataPool.getInstance().db.checkExistUser(currentUser.getUserName()) != 1) {
			System.out.println("newUser-begin");
			DataPool.getInstance().db.newUser(newUser);
			System.out.println("newUser-end");
		} else {
			System.out.println("Already Exist  Same User!!!");
		}

		return 0;
	}

	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public int loginUser(String userName, String password) {

		// TODO 用常量来作为返回类型

		if (DataPool.getInstance().db.checkExistUser(userName) == 0) {
			System.out.println("Login User Name Not Exist");
			return -1;
		}

		if (DataPool.getInstance().db.loginUser(userName, password) == 0) {
			System.out.println("Login User Password Invaild");
			return -2;
		}

		// TODO 把当前用户的联系人读入DataPool
		DataPool.getInstance().db.getUser(userName, currentUser);

		ArrayList<ContactOperations> allContactList = new ArrayList<ContactOperations>();
		this.db.getContactData(userName, allContactList);

		for (ContactOperations t : allContactList) {
			Integer iSN = t.getISN();

			allContactISNMap.put(iSN, t);

			allContactNameMultimap.put(t.getContactName(), iSN);

			for (String groupName : t.getGroupList()) {
				if (allContactGroupMap.containsKey(groupName)) {
					allContactGroupMap.get(groupName).addGroupMember(iSN);
				} else {
					ContactGroup newContactGroup = new ContactGroup(
							GroupType.NORMAL_GROUP, groupName);
					newContactGroup.addGroupMember(iSN);
					allContactGroupMap.put(groupName, newContactGroup);
				}
			}

			for (String commonLabelName : t.getCommonLabelList()) {
				if (allCommonLabelDataMap.containsKey(commonLabelName)) {
					allCommonLabelDataMap.get(commonLabelName).addGroupMember(
							iSN);
				} else {
					ContactGroup newContactGroup = new ContactGroup(
							GroupType.LABEL_GROUP, commonLabelName);
					newContactGroup.addGroupMember(iSN);
					allCommonLabelDataMap.put(commonLabelName, newContactGroup);
				}
			}

			// allRelationLabelList
			// TODO 内置的RelationLabelList需要设置一下 读configuration里面的xml
		}

		System.out.println(allContactNameMultimap.toString());

		return 0;
	}

	public int deleteUser(User user) {
		// TODO 删除用户时假定这个用户名已经存在，需要验证密码
		// 检查密码是否正确，return -2;
		if (DataPool.getInstance().db.delUser(user) == 0) {
			System.out.println("Wrong  User Password ");
			return -2;
		}
		// 同时需要更新所有的用户列表
		return 0;
	}

	public int editUser(String userName, User user) {
		// 修改成功
		if (DataPool.getInstance().db.editUser(userName, user) == -1) {
			System.out.println("Edit User Failed!!!!");
			return -2;
		}
		return 0;
	}

	public int newContact(PersonalContact contactData) {
		// 如果失败，返回-2,成功返回0
		if (DataPool.getInstance().db.newContact(currentUser.getUserName(),
				contactData) == -2) {
			return -2;
		}
		return 0;
	}

	public int editContact(PersonalContact contactData) {
		// 如果失败，返回-2，成功返回0
		if (DataPool.getInstance().db.editContact(currentUser, contactData) == -2) {
			return -2;
		}
		return 0;
	}

	public int deleteContact(PersonalContact contactData) {
		// 如果失败，返回-2，成功返回0
		if (DataPool.getInstance().db.deleteContact(currentUser, contactData) == -2) {
			return -2;
		}
		return 0;
	}

	/**
	 * @return
	 */
	public final HashMap<String, ContactGroup> getAllContactGroupMap() {
		return allContactGroupMap;
	}

	/**
	 * @return
	 */
	public final ArrayList<String> getAllRelationLabelList() {
		return allRelationLabelList;
	}

	/**
	 * @return
	 */
	public final HashMap<String, ContactGroup> getAllCommonLabelDataMap() {
		return allCommonLabelDataMap;
	}

	/**
	 * @return
	 */
	public final HashMap<Integer, ContactOperations> getAllContactISNMap() {
		return allContactISNMap;
	}

	public int newCommonLabel(CommonLabel commonLabel) {
		// 添加普通标签失败，返回-2,成功返回0
		if (DataPool.getInstance().db.newLabel(commonLabel.getLabelName(),
				currentUser.getUserName()) == -2) {
			return -2;
		}
		return 0;
	}
}
