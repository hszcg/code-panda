package org.codepanda.userinterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jvnet.flamingo.common.JCommandButton.CommandButtonKind;
import org.jvnet.flamingo.common.icon.ImageWrapperResizableIcon;
import org.jvnet.flamingo.ribbon.*;

public class PhoneMeMenu extends RibbonApplicationMenu {
	PhoneMeFrame mainFrame;
	RibbonApplicationMenuEntryPrimary userManager;
	RibbonApplicationMenuEntryPrimary contactManager;
	RibbonApplicationMenuEntryPrimary contactExchange;
	RibbonApplicationMenuEntryPrimary amEntryExit;

	public PhoneMeMenu(PhoneMeFrame mainFrame) throws IOException {
		super();
		this.mainFrame = mainFrame;

		userManager = new RibbonApplicationMenuEntryPrimary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user network.png")),
						new Dimension(32, 32)), "�û�����", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("�û�����");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

		RibbonApplicationMenuEntrySecondary newUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user.png")),
						new Dimension(32, 32)), "�½��û�", null,
				CommandButtonKind.ACTION_ONLY);
		newUser.setDescriptionText("�½��û��˻���" + "�������½����˻�ʹ��PhoneMe��������й���");

		RibbonApplicationMenuEntrySecondary deleteUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user root.png")),
						new Dimension(32, 32)), "ɾ���û�", null,
				CommandButtonKind.ACTION_ONLY);
		deleteUser.setDescriptionText("���û��б���ɾ��ĳ���û�����Ҫ����������֤");

		RibbonApplicationMenuEntrySecondary editUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user network.png")),
						new Dimension(32, 32)), "�޸��û���Ϣ", null,
				CommandButtonKind.ACTION_ONLY);
		editUser.setDescriptionText("�޸ĵ�ǰ�û��˻��ĸ�����Ϣ");

		userManager.addSecondaryMenuGroup("�û���Ϣ����������ӡ�ɾ�����޸��û��˻�", newUser,
				deleteUser, editUser);

		contactManager = new RibbonApplicationMenuEntryPrimary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)), "��ϵ�˹���", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("��ϵ�˹���");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

		RibbonApplicationMenuEntrySecondary newContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook.png")),
						new Dimension(32, 32)), "�½���ϵ��", null,
				CommandButtonKind.ACTION_ONLY);
		newContact.setDescriptionText("�½���ϵ�ˣ�����д����ϵ�˵������Ϣ");

		RibbonApplicationMenuEntrySecondary deleteContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO
						.read(this.getClass().getResource(
								"/icon/plateIcon/addressbook manager.png")),
						new Dimension(32, 32)), "ɾ����ϵ��", null,
				CommandButtonKind.ACTION_ONLY);
		deleteContact.setDescriptionText("����ϵ���б���ɾ��һ��������ϵ��");

		RibbonApplicationMenuEntrySecondary editContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 1.png")),
						new Dimension(32, 32)), "�޸���ϵ����Ϣ", null,
				CommandButtonKind.ACTION_ONLY);
		editContact.setDescriptionText("�޸ĵ�ǰ�û��˻��µ�ĳ����ϵ����Ϣ");

		RibbonApplicationMenuEntrySecondary arrangeContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)), "��ϵ��ͳ��", null,
				CommandButtonKind.ACTION_ONLY);
		arrangeContact.setDescriptionText("ͳ�Ƶ�ǰ�û��˻��µ���ϵ����Ϣ");

		RibbonApplicationMenuEntrySecondary searchContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/search.png")),
						new Dimension(32, 32)), "��ϵ������", null,
				CommandButtonKind.ACTION_ONLY);
		searchContact.setDescriptionText("�û���������������" + "ȷ�Ϻ�᷵�ط�����������ϵ���б�");

		contactManager.addSecondaryMenuGroup("��ϵ�˹���" + "������ӡ�ɾ�����޸ġ�ͳ�ƺ�������ϵ��",
				newContact, deleteContact, editContact, arrangeContact,
				searchContact);

		contactExchange = new RibbonApplicationMenuEntryPrimary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/harddrive floppy.png")),
						new Dimension(32, 32)), "���ݵ��뵼��", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("���ݵ��뵼��");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

		RibbonApplicationMenuEntrySecondary importContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/folder downloads.png")),
						new Dimension(32, 32)), "��ϵ�˵���", null,
				CommandButtonKind.ACTION_ONLY);
		importContact.setDescriptionText("���ļ��е�����ϵ�����ݣ�"
				+ "����֧�ִ�.xls��.csv��ʽ������ϵ�˵���ǰ�û��˻���");

		RibbonApplicationMenuEntrySecondary exportContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/save to floppy.png")),
						new Dimension(32, 32)), "��ϵ�˵���", null,
				CommandButtonKind.ACTION_ONLY);
		exportContact.setDescriptionText("����ǰ�û��˻��µ���ϵ����Ϣ�������ļ���"
				+ "����֧�ֵ�����.xls��.csv��ʽ���ļ���");

		contactExchange.addSecondaryMenuGroup("��ϵ�����ݵ��뵼����"
				+ "����֧��.xls��.csv��ʽ�ļ�", importContact, exportContact);

		amEntryExit = new RibbonApplicationMenuEntryPrimary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/stop.png")),
						new Dimension(32, 32)), "Exit", new ActionListener() {
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

	public void setMainFrame(PhoneMeFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public PhoneMeFrame getMainFrame() {
		return mainFrame;
	}
}
