package org.codepanda.userinterface;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.ExportContactMessageHandler;
import org.codepanda.userinterface.messagehandler.ImportContactMessageHandler;
import org.codepanda.userinterface.utility.ExtensionFileFilter;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.jvnet.flamingo.common.JCommandButton.CommandButtonKind;
import org.jvnet.flamingo.common.icon.ImageWrapperResizableIcon;
import org.jvnet.flamingo.ribbon.*;

public class PhoneMeMenu extends RibbonApplicationMenu {
	final private PhoneMeFrame mainFrame;
	final private RibbonApplicationMenuEntryPrimary userManager;
	final private RibbonApplicationMenuEntryPrimary contactManager;
	final private RibbonApplicationMenuEntryPrimary contactExchange;
	final private RibbonApplicationMenuEntryPrimary amEntryExit;

	public PhoneMeMenu(final PhoneMeFrame mainFrame) throws IOException {
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

		//
		RibbonApplicationMenuEntrySecondary newUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user.png")),
						new Dimension(32, 32)), "�½��û�", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("�½��û�");

						PhoneMeCreateNewUserDialog dialog = new PhoneMeCreateNewUserDialog(
								mainFrame, null);
						dialog.setVisible(true);
					}
				},
				CommandButtonKind.ACTION_ONLY);
		newUser.setDescriptionText("�½��û��˻���" + "�������½����˻�ʹ��PhoneMe��������й���");

		//
		RibbonApplicationMenuEntrySecondary deleteUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user root.png")),
						new Dimension(32, 32)), "ɾ���û�", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("ɾ���û�");
						// TODO delete current user

						PhoneMeCorfirmDialog deleteUserDialog = new PhoneMeCorfirmDialog(
								mainFrame);
						deleteUserDialog.setVisible(true);
					}
				},
				CommandButtonKind.ACTION_ONLY);
		deleteUser.setDescriptionText("���û��б���ɾ��ĳ���û�����Ҫ����������֤");

		//
		RibbonApplicationMenuEntrySecondary editUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user network.png")),
						new Dimension(32, 32)), "�޸��û���Ϣ", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("�޸��û���Ϣ");

						JDialog dialog = new PhoneMeEditUserPanel(mainFrame)
								.getDialog();
						dialog.pack();
						Dimension screenSize = Toolkit.getDefaultToolkit()
								.getScreenSize();
						dialog.setLocation((screenSize.width - dialog.getWidth()) / 2,
								(screenSize.height - dialog.getHeight()) / 2);
						dialog.setResizable(false);
						dialog.setVisible(true);
					}
				},
				CommandButtonKind.ACTION_ONLY);
		editUser.setDescriptionText("�޸ĵ�ǰ�û��˻��ĸ�����Ϣ");

		userManager.addSecondaryMenuGroup("�û���Ϣ����������ӡ�ɾ�����޸��û��˻�", newUser,
				deleteUser, editUser);

		//
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
						new Dimension(32, 32)), "�½���ϵ��", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("�½���ϵ��");
						mainFrame.getMyPhoneMeMajorPanel().addNewTab(
								"�½���ϵ��",
								new ContactInfoPanel(mainFrame, null, true,
										ContactInfoPanel.CONTACT_INFO_PANEL));
					}
				},
				CommandButtonKind.ACTION_ONLY);
		newContact.setDescriptionText("�½���ϵ�ˣ�����д����ϵ�˵������Ϣ");

		RibbonApplicationMenuEntrySecondary deleteContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO
						.read(this.getClass().getResource(
								"/icon/plateIcon/addressbook manager.png")),
						new Dimension(32, 32)), "ɾ����ϵ��", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("�޸���ϵ��");
						Component currentTab = mainFrame.getMyPhoneMeMajorPanel()
								.getCurrentTab();
						if (currentTab instanceof ContactInfoPanel) {
							((ContactInfoPanel) currentTab).setEditable(true);
						}
					}
				},
				CommandButtonKind.ACTION_ONLY);
		deleteContact.setDescriptionText("����ϵ���б���ɾ��һ��������ϵ��");

		RibbonApplicationMenuEntrySecondary editContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 1.png")),
						new Dimension(32, 32)), "�޸���ϵ����Ϣ", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("�޸���ϵ��");
						Component currentTab = mainFrame.getMyPhoneMeMajorPanel()
								.getCurrentTab();
						if (currentTab instanceof ContactInfoPanel) {
							((ContactInfoPanel) currentTab).setEditable(true);
						}
					}
				},
				CommandButtonKind.ACTION_ONLY);
		editContact.setDescriptionText("�޸ĵ�ǰ�û��˻��µ�ĳ����ϵ����Ϣ");

		//
		RibbonApplicationMenuEntrySecondary arrangeContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)), "��ϵ��ͳ��", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("��ϵ��ͳ��");
						mainFrame.getMyPhoneMeMajorPanel().addNewTab("��ϵ��ͳ��",
								new StatContactPanel(mainFrame));
					}
				},
				CommandButtonKind.ACTION_ONLY);
		arrangeContact.setDescriptionText("ͳ�Ƶ�ǰ�û��˻��µ���ϵ����Ϣ");

		RibbonApplicationMenuEntrySecondary searchContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/search.png")),
						new Dimension(32, 32)), "��ϵ������", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("��ϵ������");
						mainFrame.getMyPhoneMeMajorPanel().addNewTab("�߼�����",
								new SearchPanel(mainFrame));
					}
				},
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
						new Dimension(32, 32)), "��ϵ�˵���", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("��ϵ�˵���");

						JFileChooser playerHeadChooser = new JFileChooser();
						playerHeadChooser.setDialogTitle("Contact Import Chooser");

						File dir = new File("./");
						String url = null;
						if (dir.isDirectory())
							playerHeadChooser.setCurrentDirectory(dir);

						FileFilter playerHeadFileFilter = new ExtensionFileFilter(
								"Support Files (*.csv, *.xls)", new String[] { ".csv",
										".xls" });
						playerHeadChooser.addChoosableFileFilter(playerHeadFileFilter);

						int result = playerHeadChooser.showOpenDialog(mainFrame);

						if (result == JFileChooser.CANCEL_OPTION)
							return;

						File selectedFile = playerHeadChooser.getSelectedFile();
						if (!selectedFile.isFile()) {
							return;
						}
						try {
							url = selectedFile.getAbsolutePath();
							System.out.println("IMPORT\n" + url);
						} catch (Exception error) {
							error.printStackTrace();
						}

						// TODO url == null
						StringBuffer tempMessage = new StringBuffer();
						if (url.toString().endsWith("csv"))
							tempMessage.append(MyXMLMaker.addTag("Type", "csv"));
						else
							tempMessage.append(MyXMLMaker.addTag("Type", "xls"));

						tempMessage.append(MyXMLMaker.addTag("Url", url.toString()));

						String xml = MyXMLMaker.addTag("ImportContact", tempMessage
								.toString());
						xml = MyXMLMaker.addTag("com", xml);

						System.out.println("IMPORT_CONTACT\n" + xml);

						CommandVisitor importContactCommandVisitor = new CommandVisitor(
								CommandType.IMPORT_CONTACT, xml);
						ImportContactMessageHandler importContactMessageHandler = new ImportContactMessageHandler();
						importContactMessageHandler
								.executeCommand(importContactCommandVisitor);

						mainFrame.updateTaskPane(0);
					}
				},
				CommandButtonKind.ACTION_ONLY);
		importContact.setDescriptionText("���ļ��е�����ϵ�����ݣ�"
				+ "����֧�ִ�.xls��.csv��ʽ������ϵ�˵���ǰ�û��˻���");

		RibbonApplicationMenuEntrySecondary exportContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/save to floppy.png")),
						new Dimension(32, 32)), "��ϵ�˵���", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("��ϵ�˵���");

						JFileChooser playerHeadChooser = new JFileChooser();
						playerHeadChooser.setDialogTitle("Contact Export Chooser");

						File dir = new File("./");
						String url = null;
						if (dir.isDirectory())
							playerHeadChooser.setCurrentDirectory(dir);

						FileFilter playerHeadFileFilter = new ExtensionFileFilter(
								"Support Files (*.csv, *.xls)", new String[] { ".csv",
										".xls" });
						playerHeadChooser.addChoosableFileFilter(playerHeadFileFilter);

						int result = playerHeadChooser.showOpenDialog(mainFrame);

						if (result == JFileChooser.CANCEL_OPTION)
							return;

						File selectedFile = playerHeadChooser.getSelectedFile();
						try {
							url = selectedFile.getAbsolutePath();
							System.out.println("EXPORT\n" + url);
						} catch (Exception error) {
							error.printStackTrace();
						}

						// TODO url == null
						StringBuffer tempMessage = new StringBuffer();
						if (url.toString().endsWith("csv"))
							tempMessage.append(MyXMLMaker.addTag("Type", "csv"));
						else
							tempMessage.append(MyXMLMaker.addTag("Type", "xls"));

						tempMessage.append(MyXMLMaker.addTag("Url", url.toString()));

						String xml = MyXMLMaker.addTag("ExportContact", tempMessage
								.toString());
						xml = MyXMLMaker.addTag("com", xml);

						System.out.println("EXPORT_CONTACT\n" + xml);

						CommandVisitor exportContactCommandVisitor = new CommandVisitor(
								CommandType.EXPORT_CONTACT, xml);
						ExportContactMessageHandler exportContactMessageHandler = new ExportContactMessageHandler();
						exportContactMessageHandler
								.executeCommand(exportContactCommandVisitor);

						// TODO After Export
					}
				},
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
						getMainFrame().exitProgram();
					}
				}, CommandButtonKind.ACTION_ONLY);
		addMenuEntry(userManager);
		addMenuEntry(contactManager);
		addMenuEntry(contactExchange);
		addMenuEntry(amEntryExit);
	}



	/**
	 * @return
	 */
	public final PhoneMeFrame getMainFrame() {
		return mainFrame;
	}
}
