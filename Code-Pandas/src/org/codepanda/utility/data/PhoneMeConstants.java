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
		// TODO ͨ��XML����ʼ��
		
		// ��ϵ��ǩ
		allRelationLabelName = new HashSet<String>(10);
		allRelationLabelName.add("��ʦ");
		allRelationLabelName.add("ѧ��");
		allRelationLabelName.add("��ĸ");
		allRelationLabelName.add("�쵼");
		allRelationLabelName.add("����");
		allRelationLabelName.add("����");
		allRelationLabelName.add("�ֵܽ���");

		// ����
		allGroupList = new HashSet<String>(10);
		allGroupList.add("����");
		allGroupList.add("����");
		allGroupList.add("ͬ��");
		allGroupList.add("ͬѧ");

		// �ֶ�����
		contactSectionList = new HashMap<ContactSectionType, String>(15);
		contactSectionList.put(ContactSectionType.CONTACT_NAME, "����");
		contactSectionList.put(ContactSectionType.PHONE_NUMBER, "��ϵ�绰");
		contactSectionList.put(ContactSectionType.EMAIL_ADDR, "��������");
		contactSectionList.put(ContactSectionType.HOME_ADDR, "��ͥסַ");
		contactSectionList.put(ContactSectionType.WORK_OFFICE, "������λ");
		contactSectionList.put(ContactSectionType.IM, "��ʱ��ϵ��ʽ");
		contactSectionList.put(ContactSectionType.BIRTHDAY, "��ϵ������");
		contactSectionList.put(ContactSectionType.WEB_URL, "Web��ַ");
		contactSectionList.put(ContactSectionType.COMMON_LABEL, "��ͨ��ǩ");
		contactSectionList.put(ContactSectionType.GROUP_LABEL, "��������");
		contactSectionList.put(ContactSectionType.RELATION_LABEL, "��ϵ��ǩ");
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
