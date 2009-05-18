package org.codepanda.userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.codepanda.userinterface.utility.document_save_as;
import org.codepanda.userinterface.utility.system_log_out;
import org.codepanda.userinterface.utility.text_html;
import org.codepanda.userinterface.utility.x_office_document;
import org.jvnet.flamingo.common.JCommandButton.CommandButtonKind;
import org.jvnet.flamingo.ribbon.*;

public class PhoneMeMenu extends RibbonApplicationMenu {
	PhoneMeFrame mainFrame;
	RibbonApplicationMenuEntryPrimary userManager;
	RibbonApplicationMenuEntryPrimary contactManager;
	RibbonApplicationMenuEntryPrimary contactExchange;
	RibbonApplicationMenuEntryPrimary amEntryExit;
	
	public PhoneMeMenu(PhoneMeFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		
		userManager = new RibbonApplicationMenuEntryPrimary(
				new document_save_as(), "�û�����", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("�û�����");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
	

		RibbonApplicationMenuEntrySecondary newUser = new RibbonApplicationMenuEntrySecondary(
				new x_office_document(), "�½��û�", null,
				CommandButtonKind.ACTION_ONLY);
		newUser.setDescriptionText("�½��û��˻���" + "�������½����˻�ʹ��PhoneMe��������й���");

		RibbonApplicationMenuEntrySecondary deleteUser = new RibbonApplicationMenuEntrySecondary(
				new text_html(), "ɾ���û�", null, CommandButtonKind.ACTION_ONLY);
		deleteUser.setDescriptionText("���û��б���ɾ��ĳ���û�����Ҫ����������֤");

		RibbonApplicationMenuEntrySecondary editUser = new RibbonApplicationMenuEntrySecondary(
				new document_save_as(), "�޸��û���Ϣ", null,
				CommandButtonKind.ACTION_ONLY);
		editUser.setDescriptionText("�޸ĵ�ǰ�û��˻��ĸ�����Ϣ");

		userManager.addSecondaryMenuGroup("�û���Ϣ����������ӡ�ɾ�����޸��û��˻�", newUser,
				deleteUser, editUser);

		contactManager = new RibbonApplicationMenuEntryPrimary(
				new document_save_as(), "��ϵ�˹���", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("��ϵ�˹���");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

		RibbonApplicationMenuEntrySecondary newContact = 
			new RibbonApplicationMenuEntrySecondary(
				new x_office_document(), "�½���ϵ��", null,
				CommandButtonKind.ACTION_ONLY);
		newContact.setDescriptionText("�½���ϵ�ˣ�����д����ϵ�˵������Ϣ");
		RibbonApplicationMenuEntrySecondary deleteContact = 
			new RibbonApplicationMenuEntrySecondary(
				new text_html(), "ɾ����ϵ��", null, CommandButtonKind.ACTION_ONLY);
		deleteContact.setDescriptionText("����ϵ���б���ɾ��һ��������ϵ��");

		RibbonApplicationMenuEntrySecondary editContact = 
			new RibbonApplicationMenuEntrySecondary(
				new document_save_as(), "�޸���ϵ����Ϣ", null,
				CommandButtonKind.ACTION_ONLY);
		editContact.setDescriptionText("�޸ĵ�ǰ�û��˻��µ�ĳ����ϵ����Ϣ");

		RibbonApplicationMenuEntrySecondary arrangeContact = 
			new RibbonApplicationMenuEntrySecondary(
				new document_save_as(), "��ϵ��ͳ��", null,
				CommandButtonKind.ACTION_ONLY);
		arrangeContact.setDescriptionText("ͳ�Ƶ�ǰ�û��˻��µ���ϵ����Ϣ");

		RibbonApplicationMenuEntrySecondary searchContact = 
			new RibbonApplicationMenuEntrySecondary(
				new text_html(), "��ϵ������", null, CommandButtonKind.ACTION_ONLY);
		searchContact.setDescriptionText
		("�û���������������" + "ȷ�Ϻ�᷵�ط�����������ϵ���б�");

		contactManager.addSecondaryMenuGroup
		("��ϵ�˹���" + "������ӡ�ɾ�����޸ġ�ͳ�ƺ�������ϵ��",
				newContact, deleteContact, editContact, arrangeContact,
				searchContact);

		contactExchange = new RibbonApplicationMenuEntryPrimary(
				new document_save_as(), "���ݵ��뵼��", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("���ݵ��뵼��");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

		RibbonApplicationMenuEntrySecondary importContact = 
			new RibbonApplicationMenuEntrySecondary(
				new x_office_document(), "��ϵ�˵���", null,
				CommandButtonKind.ACTION_ONLY);
		importContact.setDescriptionText("���ļ��е�����ϵ�����ݣ�"
				+ "����֧�ִ�.xls��.csv��ʽ������ϵ�˵���ǰ�û��˻���");

		RibbonApplicationMenuEntrySecondary exportContact = 
			new RibbonApplicationMenuEntrySecondary(
				new text_html(), "��ϵ�˵���", null, 
				CommandButtonKind.ACTION_ONLY);
		exportContact.setDescriptionText
		("����ǰ�û��˻��µ���ϵ����Ϣ�������ļ���"
				+ "����֧�ֵ�����.xls��.csv��ʽ���ļ���");

		contactExchange.addSecondaryMenuGroup("��ϵ�����ݵ��뵼����"
				+ "����֧��.xls��.csv��ʽ�ļ�", importContact, exportContact);

		amEntryExit = new RibbonApplicationMenuEntryPrimary(
				new system_log_out(), "Exit", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				}, CommandButtonKind.ACTION_ONLY);
		addMenuEntry(userManager);
		addMenuEntry(contactManager);
		addMenuEntry(contactExchange);
		addMenuEntry(amEntryExit);
	}
	
	public void setMainFrame(PhoneMeFrame mainFrame){
		this.mainFrame = mainFrame;
	}
	
	public PhoneMeFrame getMainFrame(){
		return mainFrame;
	}
}
