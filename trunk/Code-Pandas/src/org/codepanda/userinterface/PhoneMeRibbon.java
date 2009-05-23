package org.codepanda.userinterface;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.DeleteContactMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.contact.ContactOperations;
import org.jvnet.flamingo.common.JCommandButton;
import org.jvnet.flamingo.common.icon.ImageWrapperResizableIcon;
import org.jvnet.flamingo.ribbon.*;

public class PhoneMeRibbon {
	private PhoneMeFrame mainFrame;
	private RibbonTask basicTask;
	private RibbonTask advancedTask;

	public PhoneMeRibbon(PhoneMeFrame mainFrame) {
		this.mainFrame = mainFrame;

		JRibbonBand userManagerBand;
		try {
			userManagerBand = this.getUserManagerBand();
			JRibbonBand contactManagerBand = this.getContactManagerBand();
			JRibbonBand commonLabelManagerBand = this
					.getCommonLabelManagerBand();
			basicTask = new RibbonTask("基本功能", userManagerBand,
					contactManagerBand, commonLabelManagerBand);
			basicTask.setKeyTip("B");

			JRibbonBand contactExchangeBand = this.getContactExchangeBand();
			JRibbonBand contactSyncBand = this.getContactSyncBand();
			JRibbonBand otherFunctionBand = this.getOtherFunctionBand();
			advancedTask = new RibbonTask("高级功能", contactExchangeBand,
					contactSyncBand, otherFunctionBand);
			advancedTask.setKeyTip("A");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ExpandActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(mainFrame, "Expand button clicked");
		}
	}

	private JRibbonBand getUserManagerBand() throws IOException {
		JRibbonBand userManagerBand = new JRibbonBand("用户管理",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user network.png")),
						new Dimension(32, 32)), new ExpandActionListener());

		JCommandButton newUserButton;

		newUserButton = new JCommandButton("新建用户", ImageWrapperResizableIcon
				.getIcon(ImageIO.read(this.getClass().getResource(
						"/icon/plateIcon/user.png")), new Dimension(32, 32)));

		newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("新建用户");

				PhoneMeCreateNewUserDialog dialog = new PhoneMeCreateNewUserDialog(
						mainFrame, null);
				dialog.setVisible(true);
			}
		});

		userManagerBand.addCommandButton(newUserButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteUserButton = new JCommandButton("删除用户",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user root.png")),
						new Dimension(32, 32)));

		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("删除用户");
				// TODO delete current user
				
				PhoneMeCorfirmDialog deleteUserDialog = 
					new PhoneMeCorfirmDialog(mainFrame);
				deleteUserDialog.setVisible(true);
			}
		});

		userManagerBand.addCommandButton(deleteUserButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editUserButton = new JCommandButton("修改用户信息",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user network.png")),
						new Dimension(32, 32)));

		editUserButton.addActionListener(new ActionListener() {
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
		});

		userManagerBand.addCommandButton(editUserButton,
				RibbonElementPriority.MEDIUM);

		// userManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(userManagerBand));

		return userManagerBand;
	}

	private JRibbonBand getContactManagerBand() throws IOException {
		JRibbonBand contactManagerBand = new JRibbonBand("联系人管理",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)), new ExpandActionListener());

		JCommandButton newContactButton = new JCommandButton("新建联系人",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook.png")),
						new Dimension(32, 32)));

		newContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("新建联系人");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab(
						"New Contact",
						new ContactInfoPanel(mainFrame, null, true,
								ContactInfoPanel.CONTACT_INFO_PANEL));
			}
		});

		contactManagerBand.addCommandButton(newContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteContactButton = new JCommandButton("删除联系人",
				ImageWrapperResizableIcon.getIcon(ImageIO
						.read(this.getClass().getResource(
								"/icon/plateIcon/addressbook manager.png")),
						new Dimension(32, 32)));

		deleteContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("删除联系人");
				Component currentTab = mainFrame.getMyPhoneMeMajorPanel()
						.getCurrentTab();
				if (currentTab instanceof ContactInfoPanel) {
					ContactOperations p = ((ContactInfoPanel) currentTab)
							.getMyContact();
					if (p != null) {
						// 非新建联系人
						String xml = MyXMLMaker.addTag("ISN", p.getISN()
								.toString());
						xml = MyXMLMaker.addTag("DeleteContact", xml);
						xml = MyXMLMaker.addTag("com", xml);

						System.out.println("DELETE_CONTACT\n" + xml);

						CommandVisitor deleteContactCommandVisitor = new CommandVisitor(
								CommandType.DELETE_CONTACT, xml);
						DeleteContactMessageHandler deleteContactMessageHandler = new DeleteContactMessageHandler();
						deleteContactMessageHandler
								.executeCommand(deleteContactCommandVisitor);
						mainFrame.getMyPhoneMeMajorPanel().closeTab(currentTab);
					}
				}
			}
		});

		contactManagerBand.addCommandButton(deleteContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editContactButton = new JCommandButton("修改联系人信息",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 1.png")),
						new Dimension(32, 32)));

		editContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("修改联系人");
				Component currentTab = mainFrame.getMyPhoneMeMajorPanel()
						.getCurrentTab();
				if (currentTab instanceof ContactInfoPanel) {
					((ContactInfoPanel) currentTab).setEditable(true);
				}
			}
		});

		contactManagerBand.addCommandButton(editContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton statContactButton = new JCommandButton("联系人统计",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)));

		statContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人统计");
			}
		});

		contactManagerBand.addCommandButton(statContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton searchContactButton = new JCommandButton("联系人搜索",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/search.png")),
						new Dimension(32, 32)));

		searchContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人搜索");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab("Search",
						new SearchPanel(mainFrame));
			}
		});

		contactManagerBand.addCommandButton(searchContactButton,
				RibbonElementPriority.MEDIUM);

		// contactManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(contactManagerBand));

		return contactManagerBand;
	}

	private JRibbonBand getCommonLabelManagerBand() throws IOException {
		JRibbonBand commonLabelManagerBand = new JRibbonBand("普通标签管理",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/clipboard.png")),
						new Dimension(32, 32)), new ExpandActionListener());

		JCommandButton newCommonLabelButton = new JCommandButton("新建普通标签",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/new document.png")),
						new Dimension(32, 32)));

		newCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("新建普通标签");
			}
		});

		commonLabelManagerBand.addCommandButton(newCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteCommonLabelButton = new JCommandButton("删除普通标签",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/trash full.png")),
						new Dimension(32, 32)));

		deleteCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("删除普通标签");
			}
		});

		commonLabelManagerBand.addCommandButton(deleteCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editCommonLabelButton = new JCommandButton("修改普通标签",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/clipboard.png")),
						new Dimension(32, 32)));

		editCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("修改普通标签");
			}
		});

		commonLabelManagerBand.addCommandButton(editCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		return commonLabelManagerBand;
	}

	JRibbonBand getContactExchangeBand() throws IOException {
		JRibbonBand contactExchangeBand = new JRibbonBand("数据导入导出",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/harddrive floppy.png")),
						new Dimension(32, 32)), new ExpandActionListener());

		JCommandButton importContactButton = new JCommandButton("联系人导入",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/folder downloads.png")),
						new Dimension(32, 32)));

		importContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人导入");
			}
		});

		contactExchangeBand.addCommandButton(importContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton exportContactButton = new JCommandButton("联系人导出",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/save to floppy.png")),
						new Dimension(32, 32)));

		exportContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人导出");
			}
		});

		contactExchangeBand.addCommandButton(exportContactButton,
				RibbonElementPriority.MEDIUM);

		return contactExchangeBand;
	}

	JRibbonBand getContactSyncBand() throws IOException {
		JRibbonBand contactSyncBand = new JRibbonBand("联系人同步",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/regional settings.png")),
						new Dimension(32, 32)), new ExpandActionListener());

		JCommandButton googleContactButton = new JCommandButton(
				"与Google Contact同步", ImageWrapperResizableIcon.getIcon(ImageIO
						.read(this.getClass().getResource(
								"/icon/plateIcon/regional settings.png")),
						new Dimension(32, 32)));

		googleContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("与Google Contact同步");
			}
		});

		contactSyncBand.addCommandButton(googleContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton bluetoothContactButton = new JCommandButton("与手机蓝牙同步",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/computer network.png")),
						new Dimension(32, 32)));

		bluetoothContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("与手机蓝牙同步");
			}
		});

		contactSyncBand.addCommandButton(bluetoothContactButton,
				RibbonElementPriority.MEDIUM);

		return contactSyncBand;
	}

	JRibbonBand getOtherFunctionBand() throws IOException {
		JRibbonBand otherFunctionBand = new JRibbonBand("其它高级功能",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/tool.png")),
						new Dimension(32, 32)), new ExpandActionListener());

		JCommandButton remindBirthdayButton = new JCommandButton("生日提醒",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/calendar.png")),
						new Dimension(32, 32)));

		remindBirthdayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("生日提醒");
			}
		});

		otherFunctionBand.addCommandButton(remindBirthdayButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton arrangeContactButton = new JCommandButton("联系人整理",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/folder user.png")),
						new Dimension(32, 32)));

		arrangeContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人整理");
			}
		});

		otherFunctionBand.addCommandButton(arrangeContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton relationNetButton = new JCommandButton("人立方",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/windows.png")),
						new Dimension(32, 32)));

		relationNetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("人立方");
			}
		});

		otherFunctionBand.addCommandButton(relationNetButton,
				RibbonElementPriority.MEDIUM);

		return otherFunctionBand;
	}

	public RibbonTask getBasicTask() {
		return basicTask;
	}

	public RibbonTask getAdvancedTask() {
		return advancedTask;
	}

	public void setMainFrame(PhoneMeFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public PhoneMeFrame getMainFrame() {
		return mainFrame;
	}
}
