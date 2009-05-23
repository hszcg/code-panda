package org.codepanda.utility.data;

import java.util.HashSet;

public class PhoneMeConstants {
	private HashSet<String> allRelationLabelName;
	private HashSet<String> allGroupList;

	private static PhoneMeConstants instance = new PhoneMeConstants();

	/**
	 * 
	 */
	private PhoneMeConstants() {
		// TODO 通过XML来初始化
		allRelationLabelName = new HashSet<String>(10);
		allRelationLabelName.add("老师");
		allRelationLabelName.add("学生");
		allRelationLabelName.add("父母");
		allRelationLabelName.add("领导");
		allRelationLabelName.add("部下");
		allRelationLabelName.add("家属");
		allRelationLabelName.add("兄弟姐妹");
		
		allGroupList = new HashSet<String>(10);
		allGroupList.add("好友");
		allGroupList.add("家人");
		allGroupList.add("同事");
		allGroupList.add("同学");
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
}
