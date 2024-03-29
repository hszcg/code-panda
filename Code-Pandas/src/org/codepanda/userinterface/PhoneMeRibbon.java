package org.codepanda.userinterface;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileFilter;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.application.googlecontactsyn.GContactOper;
import org.codepanda.userinterface.listener.StyleChangeActionListener;
import org.codepanda.userinterface.messagehandler.DeleteContactMessageHandler;
import org.codepanda.userinterface.messagehandler.ExportContactMessageHandler;
import org.codepanda.userinterface.messagehandler.ImportContactMessageHandler;
import org.codepanda.userinterface.test.RelationNetShow;
import org.codepanda.userinterface.utility.ExtensionFileFilter;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.ContactSectionType;
import org.codepanda.utility.data.DataPool;
import org.jvnet.flamingo.common.JCommandButton;
import org.jvnet.flamingo.common.JCommandToggleButton;
import org.jvnet.flamingo.common.StringValuePair;
import org.jvnet.flamingo.common.icon.ImageWrapperResizableIcon;
import org.jvnet.flamingo.ribbon.*;

import com.google.gdata.util.ServiceException;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class PhoneMeRibbon {
	private PhoneMeFrame mainFrame;
	private RibbonTask basicTask;
	private RibbonTask advancedTask;
	public HashMap<String, LookAndFeelInfo> allLookAndFeelMap;

	public PhoneMeRibbon(PhoneMeFrame mainFrame) {
		this.mainFrame = mainFrame;

		try {
			JRibbonBand userManagerBand = this.getUserManagerBand();
			JRibbonBand contactManagerBand = this.getContactManagerBand();
			JRibbonBand commonLabelManagerBand = this
					.getCommonLabelManagerBand();
			// userManagerBand.setTitle("User Manager");
			// contactManagerBand.setTitle("User Manager");
			// commonLabelManagerBand.setTitle("User Manager");
			basicTask = new RibbonTask("基本功能", userManagerBand,
					contactManagerBand, commonLabelManagerBand);
			basicTask.setKeyTip("B");

			JRibbonBand contactExchangeBand = this.getContactExchangeBand();
			JRibbonBand contactSyncBand = this.getContactSyncBand();
			JRibbonBand styleManagerBand = this.getStyleManagerBand();
			JRibbonBand otherFunctionBand = this.getOtherFunctionBand();
			// contactExchangeBand.setTitle("User Manager");
			// contactSyncBand.setTitle("User Manager");
			// otherFunctionBand.setTitle("User Manager");
			advancedTask = new RibbonTask("高级功能", contactExchangeBand,
					contactSyncBand, styleManagerBand, otherFunctionBand);
			advancedTask.setKeyTip("A");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// private class ExpandActionListener implements ActionListener {
	// public void actionPerformed(ActionEvent e) {
	// JOptionPane.showMessageDialog(mainFrame, "Expand button clicked");
	// }
	// }

	private JRibbonBand getUserManagerBand() throws IOException {
		JRibbonBand userManagerBand = new JRibbonBand("用户管理",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user network.png")),
						new Dimension(32, 32)), null);
		userManagerBand.startGroup();

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

				PhoneMeCorfirmDialog deleteUserDialog = new PhoneMeCorfirmDialog(
						mainFrame);
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
						new Dimension(32, 32)), null);

		contactManagerBand.startGroup();

		JCommandButton newContactButton = new JCommandButton("新建联系人",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook.png")),
						new Dimension(32, 32)));

		newContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("新建联系人");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab(
						"新建联系人",
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

						mainFrame.updateAllPanel(p.getISN());
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

		JCommandButton allContactButton = new JCommandButton("显示所有联系人",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/chat 2.png")),
						new Dimension(32, 32)));

		allContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("显示所有联系人");
				ArrayList<Integer> myISNList = new ArrayList<Integer>();
				myISNList.addAll(DataPool.getInstance().getAllContactISNMap()
						.keySet());
				mainFrame.getMyPhoneMeMajorPanel()
						.addNewTab(
								"All Contacts",
								new AllContactPanel(mainFrame, myISNList,
										ContactSectionType.PHONE_NUMBER)
										.getMainPanel());
			}
		});

		contactManagerBand.addCommandButton(allContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton statContactButton = new JCommandButton("联系人统计",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)));

		statContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人统计");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab("联系人统计",
						new StatContactPanel(mainFrame));
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
				mainFrame.getMyPhoneMeMajorPanel().addNewTab("高级搜索",
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
						new Dimension(32, 32)), null);

		commonLabelManagerBand.startGroup();

		JCommandButton newCommonLabelButton = new JCommandButton("普通标签管理",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/clipboard.png")),
						new Dimension(32, 32)));

		newCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("普通标签管理");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab(
						"Common Label Show",
						new CommonLabelShowPanel(mainFrame));
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
				Component currentTab = mainFrame.getMyPhoneMeMajorPanel()
						.getCurrentTab();
				if (currentTab instanceof CommonLabelShowPanel) {
					((CommonLabelShowPanel) currentTab)
							.deleteAllSelectedCommonLabel();
				}
			}
		});

		commonLabelManagerBand.addCommandButton(deleteCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editCommonLabelButton = new JCommandButton("修改普通标签",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/document rtf.png")),
						new Dimension(32, 32)));

		editCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("修改普通标签");
				Component currentTab = mainFrame.getMyPhoneMeMajorPanel()
						.getCurrentTab();
				if (currentTab instanceof CommonLabelShowPanel) {
					((CommonLabelShowPanel) currentTab).setEditable(true);
				}
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
						new Dimension(32, 32)), null);
		contactExchangeBand.startGroup();

		JCommandButton importContactButton = new JCommandButton("联系人导入",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/folder downloads.png")),
						new Dimension(32, 32)));

		importContactButton.addActionListener(new ActionListener() {
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

				mainFrame.updateAllPanel(0);
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
		});

		contactExchangeBand.addCommandButton(exportContactButton,
				RibbonElementPriority.MEDIUM);

		return contactExchangeBand;
	}

	JRibbonBand getContactSyncBand() throws IOException {
		JRibbonBand contactSyncBand = new JRibbonBand("联系人同步",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/regional settings.png")),
						new Dimension(32, 32)), null);
		contactSyncBand.startGroup();

		JCommandButton googleContactButton = new JCommandButton(
				"与Google Contact同步", ImageWrapperResizableIcon.getIcon(ImageIO
						.read(this.getClass().getResource(
								"/icon/plateIcon/regional settings.png")),
						new Dimension(32, 32)));

		googleContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("与Google Contact同步");
				final JDialog dialog = new JDialog(mainFrame,
						"Sync With Gmail", true);
				final JTextField userNameField = new JTextField(15);
				final JPasswordField userPasswordField = new JPasswordField(15);
				final JLabel errorLabel = new JLabel();
				JButton upload = new JButton("上传");
				upload.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (GContactOper.createContact(userNameField
									.getText(), String
									.valueOf(userPasswordField.getPassword())))
								dialog.dispose();
							else
								errorLabel.setText("登录失败");
						} catch (ServiceException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				JButton download = new JButton("下载");
				download.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (GContactOper.getAllContacts(userNameField
									.getText(), String
									.valueOf(userPasswordField.getPassword())))
								dialog.dispose();
							else
								errorLabel.setText("登录失败");
						} catch (ServiceException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						mainFrame.updateAllPanel(0);
					}
				});
				JButton cancel = new JButton("取消同步操作");
				cancel.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dialog.dispose();
					}

				});
				FormLayout layout = new FormLayout(
						"10dlu, pref, 20dlu, pref, 10dlu", // columns
						"10dlu, p, 10dlu, p, 10dlu, p, 10dlu, p, 10dlu, p, 10dlu"); // rows

				PanelBuilder builder = new PanelBuilder(layout);
				builder.setDefaultDialogBorder();
				CellConstraints cc = new CellConstraints();

				builder.addLabel("请输入gmail帐号", cc.xy(2, 2));
				builder.addLabel("用户名", cc.xy(2, 4));
				builder.add(userNameField, cc.xy(4, 4));

				builder.addLabel("密码", cc.xy(2, 6));
				builder.add(userPasswordField, cc.xy(4, 6));

				builder.add(upload, cc.xy(2, 8));
				builder.add(download, cc.xy(4, 8));
				builder.add(errorLabel, cc.xy(2, 10));
				builder.add(cancel, cc.xy(4, 10));
				// dialog.add(upload);
				dialog.add(builder.getPanel());
				dialog.pack();
				Dimension screenSize = Toolkit.getDefaultToolkit()
						.getScreenSize();
				dialog.setLocation((screenSize.width - dialog.getWidth()) / 2,
						(screenSize.height - dialog.getHeight()) / 2);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}
		});

		contactSyncBand.addCommandButton(googleContactButton,
				RibbonElementPriority.MEDIUM);

		/*
		 * JCommandButton bluetoothContactButton = new JCommandButton("与手机蓝牙同步",
		 * ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
		 * .getResource("/icon/plateIcon/computer network.png")), new
		 * Dimension(32, 32)));
		 * 
		 * bluetoothContactButton.addActionListener(new ActionListener() {
		 * public void actionPerformed(ActionEvent e) {
		 * System.out.println("与手机蓝牙同步"); } });
		 * 
		 * contactSyncBand.addCommandButton(bluetoothContactButton,
		 * RibbonElementPriority.MEDIUM);
		 */

		return contactSyncBand;
	}

	JRibbonBand getOtherFunctionBand() throws IOException {
		JRibbonBand otherFunctionBand = new JRibbonBand("其它功能",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/tool.png")),
						new Dimension(32, 32)), null);
		otherFunctionBand.startGroup();

		JCommandButton remindBirthdayButton = new JCommandButton("生日提醒",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/calendar.png")),
						new Dimension(32, 32)));

		remindBirthdayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("生日提醒");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab("生日提醒",
						new BirthdayRemindPanel(mainFrame));
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
				mainFrame.getMyPhoneMeMajorPanel().addNewTab("联系人整理选项",
						new PhoneMeArrangeContactPanel(mainFrame));
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
				mainFrame.getMyPhoneMeMajorPanel().addNewTab(
						"Relation Net Show", new RelationNetShow(mainFrame));
			}
		});

		otherFunctionBand.addCommandButton(relationNetButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton aboutUsButton = new JCommandButton("关于我们",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/Project.png")), new Dimension(32,
						32)));

		aboutUsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PhoneMeAboutUsDialog(mainFrame, "About PhoneMe", true);
			}
		});

		otherFunctionBand.addCommandButton(aboutUsButton,
				RibbonElementPriority.MEDIUM);

		return otherFunctionBand;
	}

	/**
	 * @return
	 * @throws IOException
	 */
	private JRibbonBand getStyleManagerBand() throws IOException {
		JRibbonBand styleManagerBand = new JRibbonBand("界面风格切换",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/Style.png")), new Dimension(32,
						32)));

		Map<RibbonElementPriority, Integer> transitionGalleryVisibleButtonCounts = new HashMap<RibbonElementPriority, Integer>();
		transitionGalleryVisibleButtonCounts.put(RibbonElementPriority.LOW, 2);
		transitionGalleryVisibleButtonCounts.put(RibbonElementPriority.MEDIUM,
				4);
		transitionGalleryVisibleButtonCounts.put(RibbonElementPriority.TOP, 6);

		List<StringValuePair<List<JCommandToggleButton>>> transitionGalleryButtons = new ArrayList<StringValuePair<List<JCommandToggleButton>>>();
		List<JCommandToggleButton> transitionGalleryButtonsList = new ArrayList<JCommandToggleButton>();

		UIManager.installLookAndFeel("Autumn",
				"org.jvnet.substance.skin.SubstanceAutumnLookAndFeel");
		UIManager
				.installLookAndFeel("BusinessBlack",
						"org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
		UIManager
				.installLookAndFeel("BusinessBlue",
						"org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel");
		UIManager.installLookAndFeel("Business",
				"org.jvnet.substance.skin.SubstanceBusinessLookAndFeel");
		UIManager.installLookAndFeel("Challenger",
				"org.jvnet.substance.skin.SubstanceChallengerDeepLookAndFeel");
		UIManager.installLookAndFeel("CremeCoffee",
				"org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel");
		UIManager.installLookAndFeel("EmeraldDusk",
				"org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel");
		UIManager.installLookAndFeel("MistAqua",
				"org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel");
		UIManager.installLookAndFeel("MistSilver",
				"org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel");
		UIManager.installLookAndFeel("Nebula",
				"org.jvnet.substance.skin.SubstanceNebulaLookAndFeel");
		UIManager.installLookAndFeel("OfficeBlue",
				"org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");
		UIManager
				.installLookAndFeel("OfficeSilver",
						"org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");
		UIManager
				.installLookAndFeel("RavenGlass",
						"org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel");
		UIManager.installLookAndFeel("Raven",
				"org.jvnet.substance.skin.SubstanceRavenLookAndFeel");
		UIManager.installLookAndFeel("Sahara",
				"org.jvnet.substance.skin.SubstanceSaharaLookAndFeel");
		UIManager
				.installLookAndFeel("WheatField",
						"org.jvnet.substance.skinpack.SubstanceFieldOfWheatLookAndFeel");
		UIManager.installLookAndFeel("FindingNemo",
				"org.jvnet.substance.skinpack.SubstanceFindingNemoLookAndFeel");
		UIManager.installLookAndFeel("GreenMagic",
				"org.jvnet.substance.skinpack.SubstanceGreenMagicLookAndFeel");
		UIManager.installLookAndFeel("Mango",
				"org.jvnet.substance.skinpack.SubstanceMangoLookAndFeel");
		UIManager
				.installLookAndFeel("StreetLight",
						"org.jvnet.substance.skinpack.SubstanceStreetlightsLookAndFeel");

		StyleChangeActionListener myStyleChangeActionListener = new StyleChangeActionListener(
				this);

		LookAndFeelInfo[] allStyles = UIManager.getInstalledLookAndFeels();
		allLookAndFeelMap = new HashMap<String, LookAndFeelInfo>(
				allStyles.length);

		int index = 0;
		while (!allStyles[index].getName().equals("Autumn")) {
			index++;
		}

		while (index < allStyles.length) {
			LookAndFeelInfo style = allStyles[index];
			allLookAndFeelMap.put(style.getName(), style);

			JCommandToggleButton additionalButton = new JCommandToggleButton(
					style.getName(), ImageWrapperResizableIcon.getIcon(ImageIO
							.read(this.getClass().getResource(
									"/icon/Style.png")), new Dimension(100,
							80)));

			additionalButton.addActionListener(myStyleChangeActionListener);
			transitionGalleryButtonsList.add(additionalButton);
			index++;
		}

		transitionGalleryButtons
				.add(new StringValuePair<List<JCommandToggleButton>>(null,
						transitionGalleryButtonsList));
		styleManagerBand.addRibbonGallery("Styles", transitionGalleryButtons,
				transitionGalleryVisibleButtonCounts, 6, 3,
				RibbonElementPriority.TOP);

		return styleManagerBand;
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
