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
	private UserOperations currentUser;
	
	//1个Key对应多个Value的HashMap
	private HashMultimap<String, ContactOperations> allContactNameMultimap;
}
