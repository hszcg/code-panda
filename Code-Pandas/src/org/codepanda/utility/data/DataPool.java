/**
 * 
 */
package org.codepanda.utility.data;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.codepanda.database.DatabaseMagager;
import org.codepanda.database.DatabaseManagerFacade;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.group.ContactGroup;
import org.codepanda.utility.group.GroupType;
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
	private static int currentLowBound = Integer.MIN_VALUE+1;
	private HashMap<Integer, ContactOperations> allContactISNMap;
	private HashMap<String, ContactGroup> allContactGroupMap;
	private HashMap<String, ContactGroup> allCommonLabelDataMap;
	private ArrayList<String> allRelationLabelList;
	private User currentUser;

	// 1��Key��Ӧ���Value��HashMap
	private HashMultimap<String, Integer> allContactNameMultimap;

	private DataPool() {
		// TODO Initialize all data except for dataPoolInstance
		setCurrentUser(new User());
		try {
			setDb(new DatabaseMagager("test"));
			getDb().open("test");

			// // my test of file lock
			// // File theFile = new File("test.script");
			// theFile = new File("test.script");
			// // RandomAccessFile raf=new RandomAccessFile(theFile,"rw");
			// raf=new RandomAccessFile(theFile,"rw");
			// // System.in.read(); //��ס������ʵ�����в���Ҫ
			// // FileChannel fc = raf.getChannel();
			// fc = raf.getChannel();
			// // FileLock fl = fc.tryLock();
			// fl = fc.tryLock();
			// // fl.release();
			// // raf.close();
			// // end of my file lock

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		allContactISNMap = new HashMap<Integer, ContactOperations>();
		allContactGroupMap = new HashMap<String, ContactGroup>();
		allCommonLabelDataMap = new HashMap<String, ContactGroup>();
		allRelationLabelList = new ArrayList<String>();

		setCurrentUser(new User());

		// 1��Key��Ӧ���Value��HashMap
		allContactNameMultimap = HashMultimap.create();

		System.out.println("Finish initing of DataPool!");
	}

	private static DataPool dataPoolInstance = new DataPool();

	/*
	 * Database part
	 */
	private DatabaseManagerFacade db;

	// add for locking file
	// public RandomAccessFile raf;
	// File theFile;
	// FileChannel fc;
	// public FileLock fl;

	/**
	 * @return
	 */
	public static DataPool getInstance() {
		return dataPoolInstance;
	}

	public int createNewUser(User newUser) {
		// TODO Auto-generated method stub
		// ʣ�µ��������Utility�����ݿ���Ϸ���checkExistUser()���͸������ݿ� createNewUserData()
		// ���Utility����DataPool�����ؽ������
		System.out.println(newUser.getUserName());
		setCurrentUser(newUser);
		if (DataPool.getInstance().getDb().checkExistUser(
				getCurrentUser().getUserName()) != 1) {
			System.out.println("newUser-begin");
			DataPool.getInstance().getDb().newUser(newUser);
			// System.out.println("createNewUser"+newUser.getUserName());
			System.out.println("newUser-end");
		} else {

			System.out.println("Already Exist  Same User!!!");
			return -2;
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

		if (DataPool.getInstance().getDb().checkExistUser(userName) == 0) {
			System.out.println("Login User Name Not Exist");
			return -1;
		}

		if (DataPool.getInstance().getDb().loginUser(userName, password) == 0) {
			System.out.println("Login User Password Invaild");
			return -2;
		}
		
		// ���ط�����Ϣ
		for (String str : PhoneMeConstants.getInstance().getAllGroupList()) {
			System.out.println("ADD GROUP\n" + str);
			ContactGroup newContactGroup = new ContactGroup(
					GroupType.NORMAL_GROUP, str);
			allContactGroupMap.put(str, newContactGroup);
		}

		// TODO �ѵ�ǰ�û�����ϵ�˶���DataPool
		DataPool.getInstance().getDb().getUser(userName, getCurrentUser());
		
		ArrayList<ContactOperations> allContactList = new ArrayList<ContactOperations>();
		this.getDb().getContactData(userName, allContactList);

		for (ContactOperations t : allContactList) {
			Integer iSN = t.getISN();

			allContactISNMap.put(iSN, t);

			allContactNameMultimap.put(t.getContactName(), iSN);

			// TODO ����group����Ϣ
			// �����ϵ�˵�����
			for (String groupName : t.getGroupList()) {
				if (allContactGroupMap.containsKey(groupName)) {
					allContactGroupMap.get(groupName).addGroupMember(iSN);
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

		// System.out.println(allContactNameMultimap.toString());

		// ��ʼ��currentLowBound
		while (allContactISNMap.containsKey(currentLowBound)) {
			currentLowBound++;
		}

		return 0;
	}

	public int deleteUser(String userName) {
		// TODO ɾ���û�ʱ�ٶ�����û����Ѿ����ڣ���Ҫ��֤����
		// ��������Ƿ���ȷ��return -2;
		if (DataPool.getInstance().getDb().delUser(userName) == 0) {
			System.out.println("Wrong  User Password ");
			return -2;
		}
		// ͬʱ��Ҫ�������е��û��б�
		return 0;
	}

	public int editUser(String userName, User user) {
		// �޸ĳɹ�
		if (DataPool.getInstance().getDb().editUser(userName, user) == -1) {
			System.out.println("Edit User Failed!!!!");
			return -2;
		}
		return 0;
	}

	// ���ص�����ϵ�˶�Ӧ��ISN
	public int newContact(PersonalContact contactData) {
		// ���ʧ�ܣ�����-2,�ɹ�����0
		// if
		// (DataPool.getInstance().getDb().newContact(getCurrentUser().getUserName(),
		// contactData) == -2) {
		int temp = currentLowBound;
		contactData.setISN(temp);
		System.out.println("Name_____"+contactData.getContactName());
		System.out.println("ISN----" + contactData.getISN());
		DataPool.getInstance().getDb().newContact(
				getCurrentUser().getUserName(), contactData);
		int j = 0;
		System.out.println("ISNtjk----" + contactData.getISN());
		for (j = currentLowBound + 1; j < Integer.MAX_VALUE; j++) {
			if (allContactISNMap.containsKey(j))
				j++;
			else
				break;
		}
		currentLowBound = j;
		System.out.println("User----" + currentUser.getUserName());
		System.out.println("Contact----");
		System.out.println("ContactName---" + contactData.getContactName());
		for (int i = 0; i < contactData.getPhoneNumberList().size(); i++)
			System.out.println("ContactTelephone----"
					+ contactData.getPhoneNumberList().get(i));
		// ����ά����HashMap�����޸�
		allContactISNMap.put(temp, contactData);
		allContactNameMultimap.put(contactData.getContactName(), temp);
		for (String groupName : contactData.getGroupList()) {
			if (allContactGroupMap.containsKey(groupName)) {
				allContactGroupMap.get(groupName).addGroupMember(temp);
			} else {
				ContactGroup newContactGroup = new ContactGroup(
						GroupType.NORMAL_GROUP, groupName);
				newContactGroup.addGroupMember(temp);
				allContactGroupMap.put(groupName, newContactGroup);
			}
		}

		for (String commonLabelName : contactData.getCommonLabelList()) {
			if (allCommonLabelDataMap.containsKey(commonLabelName)) {
				allCommonLabelDataMap.get(commonLabelName).addGroupMember(temp);
			} else {
				ContactGroup newContactGroup = new ContactGroup(
						GroupType.LABEL_GROUP, commonLabelName);
				newContactGroup.addGroupMember(temp);
				allCommonLabelDataMap.put(commonLabelName, newContactGroup);
			}
		}

		return temp;
	}

	public int editContact(PersonalContact contactData) {
		// ���ʧ�ܣ�����-2���ɹ�����0
		if (DataPool.getInstance().getDb().editContact(
				getCurrentUser().getUserName(), contactData) == -2) {
			return -2;
		}
		// ����ά����HashMap�����޸�
		// allContactISNMap.remove(contactData.getISN());
		allContactISNMap.put(contactData.getISN(), contactData);
		allContactNameMultimap.put(contactData.getContactName(), contactData
				.getISN());
		for (String groupName : contactData.getGroupList()) {
			if (allContactGroupMap.containsKey(groupName)) {
				allContactGroupMap.get(groupName).addGroupMember(
						contactData.getISN());
			} else {
				ContactGroup newContactGroup = new ContactGroup(
						GroupType.NORMAL_GROUP, groupName);
				newContactGroup.addGroupMember(contactData.getISN());
				allContactGroupMap.put(groupName, newContactGroup);
			}
		}

		for (String commonLabelName : contactData.getCommonLabelList()) {
			if (allCommonLabelDataMap.containsKey(commonLabelName)) {
				allCommonLabelDataMap.get(commonLabelName).addGroupMember(
						contactData.getISN());
			} else {
				ContactGroup newContactGroup = new ContactGroup(
						GroupType.LABEL_GROUP, commonLabelName);
				newContactGroup.addGroupMember(contactData.getISN());
				allCommonLabelDataMap.put(commonLabelName, newContactGroup);
			}
		}

		return 0;
	}

	// ���ص�ǰ��currentLowBound
	public int deleteContact(int ISN) {
		// ���ʧ�ܣ�����-2���ɹ�����0
		if (DataPool.getInstance().getDb().deleteContact(
				currentUser.getUserName(), ISN) == -2)
			return -2;
		if (ISN < currentLowBound) {
			currentLowBound = ISN;
		}
		// �����е�hashMap����ά��
		//System.out.println()
		if(allContactISNMap.isEmpty())
		{
			System.out.println("Empty!!!!");
		}
		if(allContactISNMap==null)
		{
			System.out.println("Null!!!!");
		}
		if(allContactISNMap.get(ISN)!=null)
		{
			System.out.println("LLLLLL");
			System.out.println(allContactISNMap.get(ISN).getContactName());
		}
		System.out.println(ISN);
		allContactNameMultimap.remove(allContactISNMap.get(ISN).getContactName(),ISN);
	//	System.out.println("Here----"+this.getAllContactISNMap().get(ISN).getContactName());
	//	allContactNameMultimap.remove(this.getAllContactISNMap().get(ISN)
		//		.getContactName(), ISN);
		for (String groupName : getAllContactISNMap().get(ISN).getGroupList()) {
			if (allContactGroupMap.containsKey(groupName)) {
				allContactGroupMap.get(groupName).deleteGroupMember(ISN);
			}
		}

		for (String groupName : getAllContactISNMap().get(ISN).getGroupList()) {
			if (allCommonLabelDataMap.containsKey(groupName)) {
				allCommonLabelDataMap.get(groupName).deleteGroupMember(ISN);
			}
		}
		allContactISNMap.remove(ISN);
		return currentLowBound;

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

	public int deleteCommonLabel(String paramStr)
	{
		String temp[]=paramStr.split("--");
		//0��Ӧ��Ӧ���ǿ�
		for(int i=1;i<temp.length;i++)
		{
		
			//TODO ��Ҫ�������ݿ�ĺ�����������ӿڼ���
			if(this.getAllCommonLabelDataMap().containsKey(temp[i]))
			{
				
				ContactGroup contactGroup=this.getAllCommonLabelDataMap().get(temp[i]);
				this.getAllCommonLabelDataMap().remove(temp[i]);
				for(Integer inte : contactGroup.getGroupMembers())
				{
					if(this.getAllContactISNMap().containsKey(inte))
					{
						ArrayList<String> commandList=this.getAllContactISNMap().get(inte).getCommonLabelList();
						commandList.remove(temp[i]);
						this.getAllContactISNMap().get(inte).setCommonLabelList(commandList);
					}
				}
			
			}
			
			
		}
		return 0;
	}
	public int editCommandLabel(String paramStr)
	{
		String temp[]=paramStr.split("--");
		System.out.println("Temp___1__"+temp[1]);
		System.out.println("Temp___2__"+temp[2]);
		if(this.getAllCommonLabelDataMap().containsKey(temp[1]))
		{
			//��Ҫ�������ݿ⺯����ʵ���������޸�
			
			ContactGroup contactGroup=this.getAllCommonLabelDataMap().get(temp[1]);
			this.getAllCommonLabelDataMap().remove(temp[1]);
			this.getAllCommonLabelDataMap().put(temp[2], contactGroup);
			ArrayList<String> newCommonLabel=new ArrayList<String>();
			newCommonLabel.add(temp[2]);
				for(Integer inte : contactGroup.getGroupMembers())
				{
					if(this.getAllContactISNMap().containsKey(inte))
						this.getAllContactISNMap().get(inte).setCommonLabelList(newCommonLabel);
				}
			
			
		}
		return 0;
		
	}
	public void setDb(DatabaseManagerFacade db) {
		this.db = db;
	}

	public DatabaseManagerFacade getDb() {
		return db;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public final User getCurrentUser() {
		return currentUser;
	}
}
