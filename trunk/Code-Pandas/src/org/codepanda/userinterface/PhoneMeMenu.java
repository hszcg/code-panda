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
						new Dimension(32, 32)), "用户管理", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("用户管理");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

		RibbonApplicationMenuEntrySecondary newUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user.png")),
						new Dimension(32, 32)), "新建用户", null,
				CommandButtonKind.ACTION_ONLY);
		newUser.setDescriptionText("新建用户账户，" + "可以以新建的账户使用PhoneMe软件的所有功能");

		RibbonApplicationMenuEntrySecondary deleteUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user root.png")),
						new Dimension(32, 32)), "删除用户", null,
				CommandButtonKind.ACTION_ONLY);
		deleteUser.setDescriptionText("从用户列表中删除某个用户，需要进行密码认证");

		RibbonApplicationMenuEntrySecondary editUser = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user network.png")),
						new Dimension(32, 32)), "修改用户信息", null,
				CommandButtonKind.ACTION_ONLY);
		editUser.setDescriptionText("修改当前用户账户的个人信息");

		userManager.addSecondaryMenuGroup("用户信息管理，包括添加、删除和修改用户账户", newUser,
				deleteUser, editUser);

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
						new Dimension(32, 32)), "新建联系人", null,
				CommandButtonKind.ACTION_ONLY);
		newContact.setDescriptionText("新建联系人，并填写该联系人的相关信息");

		RibbonApplicationMenuEntrySecondary deleteContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO
						.read(this.getClass().getResource(
								"/icon/plateIcon/addressbook manager.png")),
						new Dimension(32, 32)), "删除联系人", null,
				CommandButtonKind.ACTION_ONLY);
		deleteContact.setDescriptionText("从联系人列表中删除一个或多个联系人");

		RibbonApplicationMenuEntrySecondary editContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 1.png")),
						new Dimension(32, 32)), "修改联系人信息", null,
				CommandButtonKind.ACTION_ONLY);
		editContact.setDescriptionText("修改当前用户账户下的某个联系人信息");

		RibbonApplicationMenuEntrySecondary arrangeContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)), "联系人统计", null,
				CommandButtonKind.ACTION_ONLY);
		arrangeContact.setDescriptionText("统计当前用户账户下的联系人信息");

		RibbonApplicationMenuEntrySecondary searchContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/search.png")),
						new Dimension(32, 32)), "联系人搜索", null,
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
						new Dimension(32, 32)), "联系人导入", null,
				CommandButtonKind.ACTION_ONLY);
		importContact.setDescriptionText("从文件中导入联系人数据，"
				+ "可以支持从.xls和.csv格式导入联系人到当前用户账户下");

		RibbonApplicationMenuEntrySecondary exportContact = new RibbonApplicationMenuEntrySecondary(
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/save to floppy.png")),
						new Dimension(32, 32)), "联系人导出", null,
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
