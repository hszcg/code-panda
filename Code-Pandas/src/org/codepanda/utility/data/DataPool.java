/**
 * 
 */
package org.codepanda.utility.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.codepanda.database.DatabaseMagager;
import org.codepanda.database.DatabaseManagerFacade;
import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.group.ContactGroup;
import org.codepanda.utility.group.GroupType;
import org.codepanda.utility.label.CommonLabel;
import org.codepanda.utility.label.RelationLabel;
import org.codepanda.utility.user.User;
import org.codepanda.utility.user.UserOperations;

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
		db = new DatabaseMagager();
		try {
			db.open("test");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DataPoolInit");
		// for test
		try {
			PersonalContact contact1 = new PersonalContact();
			PersonalContact  contact2 = new PersonalContact();
			PersonalContact  contact3 = new PersonalContact();
			contact1.setContactName("����1");
			contact2.setContactName("����2");
			contact3.setContactName("����3");
			db.newContact("leilei", contact1);
			db.newContact("leilei", contact2);
			db.newContact("leilei", contact3);
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	// 1��Key��Ӧ���Value��HashMap
	private HashMultimap<String, Integer> allContactNameMultimap;

	public int createNewUser(User newUser) {
		// TODO Auto-generated method stub
		// ʣ�µ��������Utility�����ݿ���Ϸ���checkExistUser()���͸������ݿ� createNewUserData()
		// ���Utility����DataPool�����ؽ������
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

		// TODO �ó�������Ϊ��������

		if (DataPool.getInstance().db.checkExistUser(userName) == 0) {
			System.out.println("Login User Name Not Exist");
			return -1;
		}

		if (DataPool.getInstance().db.loginUser(userName, password) == 0) {
			System.out.println("Login User Password Invaild");
			return -2;
		}

		// TODO �ѵ�ǰ�û�����ϵ�˶���DataPool
		this.db.getUser(userName, currentUser);

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
			// TODO ���õ�RelationLabelList��Ҫ����һ�� ��configuration�����xml
		}

		return 0;
	}

	public int deleteUser(User user) {
		// TODO ɾ���û�ʱ�ٶ�����û����Ѿ����ڣ���Ҫ��֤����
		// ��������Ƿ���ȷ��return -2;
		if (DataPool.getInstance().db.delUser(user) == 0) {
			System.out.println("Wrong  User Password ");
			return -2;
		}
		// ͬʱ��Ҫ�������е��û��б�
		return 0;
	}

	public int editUser(String userName, User user) {
		// �޸ĳɹ�
		if (DataPool.getInstance().db.editUser(userName, user) == -1) {
			System.out.println("Edit User Failed!!!!");
			return -2;
		}
		return 0;
	}
	public int newContact(PersonalContact contactData)
	{
		//���ʧ�ܣ�����-1,�ɹ�����0
		if(DataPool.getInstance().db.newContact(currentUser.getUserName(), contactData)==-1)
		{
			return -1;
		}
		return 0;
	}
}
