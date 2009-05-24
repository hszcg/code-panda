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
						new Dimension(32, 32)), "用户管理", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("用户管理");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

		//
		RibbonApplicationMenuEntrySecondary newUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user.png")),
						new Dimension(32, 32)), "新建用户", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("新建用户");

						PhoneMeCreateNewUserDialog dialog = new PhoneMeCreateNewUserDialog(
								mainFrame, null);
						dialog.setVisible(true);
					}
				},
				CommandButtonKind.ACTION_ONLY);
		newUser.setDescriptionText("新建用户账户，" + "可以以新建的账户使用PhoneMe软件的所有功能");

		//
		RibbonApplicationMenuEntrySecondary deleteUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user root.png")),
						new Dimension(32, 32)), "删除用户", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("删除用户");
						// TODO delete current user

						PhoneMeCorfirmDialog deleteUserDialog = new PhoneMeCorfirmDialog(
								mainFrame);
						deleteUserDialog.setVisible(true);
					}
				},
				CommandButtonKind.ACTION_ONLY);
		deleteUser.setDescriptionText("从用户列表中删除某个用户，需要进行密码认证");

		//
		RibbonApplicationMenuEntrySecondary editUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user network.png")),
						new Dimension(32, 32)), "修改用户信息", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("修改用户信息");

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
		editUser.setDescriptionText("修改当前用户账户的个人信息");

		userManager.addSecondaryMenuGroup("用户信息管理，包括添加、删除和修改用户账户", newUser,
				deleteUser, editUser);

		//
		contactManager = new RibbonApplicationMenuEntryPrimary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)), "联系人管理", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("联系人管理");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

		RibbonApplicationMenuEntrySecondary newContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook.png")),
						new Dimension(32, 32)), "新建联系人", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("新建联系人");
						mainFrame.getMyPhoneMeMajorPanel().addNewTab(
								"新建联系人",
								new ContactInfoPanel(mainFrame, null, true,
										ContactInfoPanel.CONTACT_INFO_PANEL));
					}
				},
				CommandButtonKind.ACTION_ONLY);
		newContact.setDescriptionText("新建联系人，并填写该联系人的相关信息");

		RibbonApplicationMenuEntrySecondary deleteContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO
						.read(this.getClass().getResource(
								"/icon/plateIcon/addressbook manager.png")),
						new Dimension(32, 32)), "删除联系人", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("修改联系人");
						Component currentTab = mainFrame.getMyPhoneMeMajorPanel()
								.getCurrentTab();
						if (currentTab instanceof ContactInfoPanel) {
							((ContactInfoPanel) currentTab).setEditable(true);
						}
					}
				},
				CommandButtonKind.ACTION_ONLY);
		deleteContact.setDescriptionText("从联系人列表中删除一个或多个联系人");

		RibbonApplicationMenuEntrySecondary editContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 1.png")),
						new Dimension(32, 32)), "修改联系人信息", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("修改联系人");
						Component currentTab = mainFrame.getMyPhoneMeMajorPanel()
								.getCurrentTab();
						if (currentTab instanceof ContactInfoPanel) {
							((ContactInfoPanel) currentTab).setEditable(true);
						}
					}
				},
				CommandButtonKind.ACTION_ONLY);
		editContact.setDescriptionText("修改当前用户账户下的某个联系人信息");

		//
		RibbonApplicationMenuEntrySecondary arrangeContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)), "联系人统计", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("联系人统计");
						mainFrame.getMyPhoneMeMajorPanel().addNewTab("联系人统计",
								new StatContactPanel(mainFrame));
					}
				},
				CommandButtonKind.ACTION_ONLY);
		arrangeContact.setDescriptionText("统计当前用户账户下的联系人信息");

		RibbonApplicationMenuEntrySecondary searchContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/search.png")),
						new Dimension(32, 32)), "联系人搜索", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("联系人搜索");
						mainFrame.getMyPhoneMeMajorPanel().addNewTab("高级搜索",
								new SearchPanel(mainFrame));
					}
				},
				CommandButtonKind.ACTION_ONLY);
		searchContact.setDescriptionText("用户输入搜索条件，" + "确认后会返回符合条件的联系人列表");

		contactManager.addSecondaryMenuGroup("联系人管理，" + "包括添加、删除、修改、统计和搜索联系人",
				newContact, deleteContact, editContact, arrangeContact,
				searchContact);

		contactExchange = new RibbonApplicationMenuEntryPrimary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/harddrive floppy.png")),
						new Dimension(32, 32)), "数据导入导出", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("数据导入导出");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

		RibbonApplicationMenuEntrySecondary importContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/folder downloads.png")),
						new Dimension(32, 32)), "联系人导入", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("联系人导入");

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
		importContact.setDescriptionText("从文件中导入联系人数据，"
				+ "可以支持从.xls和.csv格式导入联系人到当前用户账户下");

		RibbonApplicationMenuEntrySecondary exportContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/save to floppy.png")),
						new Dimension(32, 32)), "联系人导出", new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("联系人导出");

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
		exportContact.setDescriptionText("将当前用户账户下的联系人信息导出至文件中"
				+ "可以支持导出到.xls或.csv格式的文件中");

		contactExchange.addSecondaryMenuGroup("联系人数据导入导出，"
				+ "可以支持.xls和.csv格式文件", importContact, exportContact);

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
