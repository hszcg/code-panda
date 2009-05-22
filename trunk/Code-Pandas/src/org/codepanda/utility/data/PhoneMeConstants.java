package org.codepanda.utility.data;

import java.util.HashSet;

public class PhoneMeConstants {
	private HashSet<String> allRelationLabelName;

	private static PhoneMeConstants instance = new PhoneMeConstants();

	/**
	 * 
	 */
	private PhoneMeConstants() {
		// TODO ͨ��XML����ʼ��
		allRelationLabelName = new HashSet<String>();
		allRelationLabelName.add("��ʦ");
		allRelationLabelName.add("ѧ��");
		allRelationLabelName.add("��ĸ");
		allRelationLabelName.add("�쵼");
		allRelationLabelName.add("����");
		allRelationLabelName.add("����");
		allRelationLabelName.add("�ֵܽ���");
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
}
