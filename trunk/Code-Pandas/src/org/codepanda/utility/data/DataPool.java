/**
 * 
 */
package org.codepanda.utility.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.codepanda.database.Database;
import org.codepanda.database.DatabaseManager;
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
 * Singleton Pattern
 *
 */
public class DataPool {
	private DataPool(){
		//TODO: Initialize all data except for dataPoolInstance
		db=new Database();
		try {
			db.open("test");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static DataPool dataPoolInstance = new DataPool();
	
	/*
	 * Database part
	 */
	private DatabaseManager db;
	
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
	
	//1��Key��Ӧ���Value��HashMap
	private HashMultimap<String, ContactOperations> allContactNameMultimap;

	public void createNewUser(User newUser) {
		// TODO Auto-generated method stub
		// ʣ�µ��������Utility�����ݿ���Ϸ���checkExistUser()���͸������ݿ� createNewUserData()	
		// ���Utility����DataPool�����ؽ������
		currentUser = newUser;
		if(!DataPool.getInstance().db.checkExistUser(currentUser.getUserName()))
		{
			DataPool.getInstance().db.newUser(newUser);
		}
		else
		{
			System.out.println("Already Exist  Same User!!!");
		}
		
	}
}
