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
import org.codepanda.utility.group.ContactGroup;
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
			ContactData contact1 = new ContactData();
			ContactData contact2 = new ContactData();
			ContactData contact3 = new ContactData();
			contact1.contactName = "����1";
			contact2.contactName = "����2";
			contact3.contactName = "����3";
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
	private HashMap<String, CommonLabel> allCommonLabelDataMap;
	private ArrayList<RelationLabel> allRelationDataList;
	private User currentUser;

	// 1��Key��Ӧ���Value��HashMap
	private HashMultimap<String, ContactOperations> allContactNameMultimap;

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

		return 0;
	}
}
