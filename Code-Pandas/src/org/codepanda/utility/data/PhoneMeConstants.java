package org.codepanda.utility.data;

import java.util.HashMap;
import java.util.HashSet;

public class PhoneMeConstants {
	private HashSet<String> allRelationLabelName;
	private HashSet<String> allGroupList;
	private HashMap<ContactSectionType, String> contactSectionList;
	
	public static final int HEAD_IMAGE_HEIGHT = 115;
	public static int HEAD_IMAGE_WIDTH = 130;

	private static PhoneMeConstants instance = new PhoneMeConstants();

	/**
	 * 
	 */
	private PhoneMeConstants() {
		// TODO 通过XML来初始化
		
		// 关系标签
		allRelationLabelName = new HashSet<String>(10);
		allRelationLabelName.add("老师");
		allRelationLabelName.add("学生");
		allRelationLabelName.add("父母");
		allRelationLabelName.add("领导");
		allRelationLabelName.add("部下");
		allRelationLabelName.add("家属");
		allRelationLabelName.add("兄弟姐妹");

		// 分组
		allGroupList = new HashSet<String>(10);
		allGroupList.add("好友");
		allGroupList.add("家人");
		allGroupList.add("同事");
		allGroupList.add("同学");

		// 字段名称
		contactSectionList = new HashMap<ContactSectionType, String>(15);
		contactSectionList.put(ContactSectionType.CONTACT_NAME, "姓名");
		contactSectionList.put(ContactSectionType.PHONE_NUMBER, "联系电话");
		contactSectionList.put(ContactSectionType.EMAIL_ADDR, "电子邮箱");
		contactSectionList.put(ContactSectionType.HOME_ADDR, "家庭住址");
		contactSectionList.put(ContactSectionType.WORK_OFFICE, "工作单位");
		contactSectionList.put(ContactSectionType.IM, "即时联系方式");
		contactSectionList.put(ContactSectionType.BIRTHDAY, "联系人生日");
		contactSectionList.put(ContactSectionType.WEB_URL, "Web地址");
		contactSectionList.put(ContactSectionType.COMMON_LABEL, "普通标签");
		contactSectionList.put(ContactSectionType.GROUP_LABEL, "所属分组");
		contactSectionList.put(ContactSectionType.RELATION_LABEL, "关系标签");
	}

	/**
	 * @return
	 */
	public static PhoneMeConstants getInstance() {
		return instance;
	}

	/**
	 * @return the allRelationLabelName
	 */
	public final HashSet<String> getAllRelationLabelName() {
		return allRelationLabelName;
	}

	/**
	 * @return the allGroupList
	 */
	public final HashSet<String> getAllGroupList() {
		return allGroupList;
	}

	/**
	 * @return the contactSectionList
	 */
	public final HashMap<ContactSectionType, String> getContactSectionList() {
		return contactSectionList;
	}
}
