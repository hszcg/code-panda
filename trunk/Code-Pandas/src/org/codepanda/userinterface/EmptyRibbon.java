/**
 * 
 */
package org.codepanda.userinterface;

import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import org.codepanda.userinterface.utility.*;

import org.jdesktop.swingx.*;
import org.jdesktop.swingx.JXStatusBar.Constraint;
import org.jdesktop.swingx.auth.LoginService;
import org.jdesktop.swingx.decorator.*;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.renderer.*;
import org.jdesktop.swingx.treetable.FileSystemModel;
import org.jvnet.flamingo.common.JCommandButton;
import org.jvnet.flamingo.common.JCommandButton.CommandButtonKind;
import org.jvnet.flamingo.ribbon.JRibbonBand;
import org.jvnet.flamingo.ribbon.JRibbonFrame;
import org.jvnet.flamingo.ribbon.RibbonApplicationMenu;
import org.jvnet.flamingo.ribbon.RibbonApplicationMenuEntryPrimary;
import org.jvnet.flamingo.ribbon.RibbonApplicationMenuEntrySecondary;
import org.jvnet.flamingo.ribbon.RibbonElementPriority;
import org.jvnet.flamingo.ribbon.RibbonTask;
import org.jvnet.substance.skin.*;

//import test.ribbon.BasicCheckRibbon.ExpandActionListener;
public class EmptyRibbon extends JRibbonFrame {
	public EmptyRibbon() {
		super("PhoneMe test");
		try {
			this.setIconImages(Arrays.asList(ImageIO.read(this.getClass()
					.getResource("/ribbon-main-icon-16.png")), ImageIO.read(this
					.getClass().getResource("/ribbon-main-icon.png"))));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private class ExpandActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(EmptyRibbon.this,
					"Expand button clicked");
		}
	}

	protected void configureApplicationMenu() {
		RibbonApplicationMenuEntryPrimary userManager = new RibbonApplicationMenuEntryPrimary(
				new document_save_as(), "用户管理", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("用户管理");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
		// userManager.setActionKeyTip("A");
		// userManager.setPopupKeyTip("F");

		RibbonApplicationMenuEntrySecondary newUser = new RibbonApplicationMenuEntrySecondary(
				new x_office_document(), "新建用户", null,
				CommandButtonKind.ACTION_ONLY);
		newUser.setDescriptionText("新建用户账户，" + "可以以新建的账户使用PhoneMe软件的所有功能");
		// newUser.setActionKeyTip("W");
		RibbonApplicationMenuEntrySecondary deleteUser = new RibbonApplicationMenuEntrySecondary(
				new text_html(), "删除用户", null, CommandButtonKind.ACTION_ONLY);
		deleteUser.setDescriptionText("从用户列表中删除某个用户，需要进行密码认证");
		// deleteUser.setEnabled(false);
		// deleteUser.setActionKeyTip("H");
		RibbonApplicationMenuEntrySecondary editUser = new RibbonApplicationMenuEntrySecondary(
				new document_save_as(), "修改用户信息", null,
				CommandButtonKind.ACTION_ONLY);
		editUser.setDescriptionText("修改当前用户账户的个人信息");
		// editUser.setActionKeyTip("O");

		userManager.addSecondaryMenuGroup("用户信息管理，包括添加、删除和修改用户账户", newUser,
				deleteUser, editUser);

		RibbonApplicationMenuEntryPrimary contactManager = new RibbonApplicationMenuEntryPrimary(
				new document_save_as(), "联系人管理", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("联系人管理");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
		// userManager.setActionKeyTip("A");
		// userManager.setPopupKeyTip("F");

		RibbonApplicationMenuEntrySecondary newContact = new RibbonApplicationMenuEntrySecondary(
				new x_office_document(), "新建联系人", null,
				CommandButtonKind.ACTION_ONLY);
		newContact.setDescriptionText("新建联系人，并填写该联系人的相关信息");
		// newUser.setActionKeyTip("W");
		RibbonApplicationMenuEntrySecondary deleteContact = new RibbonApplicationMenuEntrySecondary(
				new text_html(), "删除联系人", null, CommandButtonKind.ACTION_ONLY);
		deleteContact.setDescriptionText("从联系人列表中删除一个或多个联系人");
		// deleteUser.setEnabled(false);
		// deleteUser.setActionKeyTip("H");
		RibbonApplicationMenuEntrySecondary editContact = new RibbonApplicationMenuEntrySecondary(
				new document_save_as(), "修改联系人信息", null,
				CommandButtonKind.ACTION_ONLY);
		editContact.setDescriptionText("修改当前用户账户下的某个联系人信息");
		// editUser.setActionKeyTip("O");

		RibbonApplicationMenuEntrySecondary arrangeContact = new RibbonApplicationMenuEntrySecondary(
				new document_save_as(), "联系人统计", null,
				CommandButtonKind.ACTION_ONLY);
		arrangeContact.setDescriptionText("统计当前用户账户下的联系人信息");
		// editUser.setActionKeyTip("O");

		RibbonApplicationMenuEntrySecondary searchContact = new RibbonApplicationMenuEntrySecondary(
				new text_html(), "联系人搜索", null, CommandButtonKind.ACTION_ONLY);
		searchContact.setDescriptionText("用户输入搜索条件，" + "确认后会返回符合条件的联系人列表");
		// deleteUser.setActionKeyTip("H");

		contactManager.addSecondaryMenuGroup("联系人管理，" + "包括添加、删除、修改、统计和搜索联系人",
				newContact, deleteContact, editContact, arrangeContact,
				searchContact);

		RibbonApplicationMenuEntryPrimary contactExchange = new RibbonApplicationMenuEntryPrimary(
				new document_save_as(), "数据导入导出", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("数据导入导出");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
		// userManager.setActionKeyTip("A");
		// userManager.setPopupKeyTip("F");

		RibbonApplicationMenuEntrySecondary importContact = new RibbonApplicationMenuEntrySecondary(
				new x_office_document(), "联系人导入", null,
				CommandButtonKind.ACTION_ONLY);
		importContact.setDescriptionText("从文件中导入联系人数据，"
				+ "可以支持从.xls和.csv格式导入联系人到当前用户账户下");
		// importContact.setActionKeyTip("W");
		RibbonApplicationMenuEntrySecondary exportContact = new RibbonApplicationMenuEntrySecondary(
				new text_html(), "联系人导出", null, CommandButtonKind.ACTION_ONLY);
		exportContact.setDescriptionText("将当前用户账户下的联系人信息导出至文件中"
				+ "可以支持导出到.xls或.csv格式的文件中");
		// exportContact.setActionKeyTip("H");

		contactExchange.addSecondaryMenuGroup("联系人数据导入导出，"
				+ "可以支持.xls和.csv格式文件", importContact, exportContact);

		RibbonApplicationMenuEntryPrimary amEntryExit = new RibbonApplicationMenuEntryPrimary(
				new system_log_out(), "Exit", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				}, CommandButtonKind.ACTION_ONLY);

		RibbonApplicationMenu applicationMenu = new RibbonApplicationMenu();
		applicationMenu.addMenuEntry(userManager);
		applicationMenu.addMenuEntry(contactManager);
		applicationMenu.addMenuEntry(contactExchange);
		applicationMenu.addMenuEntry(amEntryExit);

		this.getRibbon().setApplicationMenu(applicationMenu);
	}

	private JRibbonBand getUserManagerBand() {
		JRibbonBand userManagerBand = new JRibbonBand("User Management",
				new edit_paste(), new ExpandActionListener());

		JCommandButton newUserButton = new JCommandButton("New User",
				new edit_paste());

		newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("New User");
			}
		});

		userManagerBand.addCommandButton(newUserButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteUserButton = new JCommandButton("Delete User",
				new edit_paste());

		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete User");
			}
		});

		userManagerBand.addCommandButton(deleteUserButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editUserButton = new JCommandButton("Edit User Info",
				new edit_paste());

		editUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Edit User Info");
			}
		});

		userManagerBand.addCommandButton(editUserButton,
				RibbonElementPriority.MEDIUM);

		// userManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(userManagerBand));

		return userManagerBand;
	}

	private JRibbonBand getContactManagerBand() {
		JRibbonBand contactManagerBand = new JRibbonBand("Contact Management",
				new edit_paste(), new ExpandActionListener());

		JCommandButton newContactButton = new JCommandButton("New Contact",
				new edit_paste());

		newContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Contact");
			}
		});

		contactManagerBand.addCommandButton(newContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteContactButton = new JCommandButton(
				"Delete Contact", new edit_paste());

		deleteContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete Contact");
			}
		});

		contactManagerBand.addCommandButton(deleteContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editContactButton = new JCommandButton("Edit Contact",
				new edit_paste());

		editContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Edit Contact");
			}
		});

		contactManagerBand.addCommandButton(editContactButton,
				RibbonElementPriority.MEDIUM);

		// contactManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(contactManagerBand));

		return contactManagerBand;
	}

	JRibbonBand getContactExchangeBand() {
		JRibbonBand contactExchangeBand = new JRibbonBand("Contact Exchange",
				new edit_paste(), new ExpandActionListener());

		JCommandButton importContactButton = new JCommandButton(
				"Import Contact", new edit_paste());

		importContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Import Contact");
			}
		});

		contactExchangeBand.addCommandButton(importContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton exportContactButton = new JCommandButton(
				"Export Contact", new edit_paste());

		exportContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Export Contact");
			}
		});

		contactExchangeBand.addCommandButton(exportContactButton,
				RibbonElementPriority.MEDIUM);

		return contactExchangeBand;
	}

	JRibbonBand getContactSyncBand() {
		JRibbonBand contactSyncBand = new JRibbonBand("Sync Contact",
				new edit_paste(), new ExpandActionListener());

		JCommandButton googleContactButton = new JCommandButton(
				"Sync With Google Contact", new edit_paste());

		googleContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sync With Google Contact");
			}
		});

		contactSyncBand.addCommandButton(googleContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton bluetoothContactButton = new JCommandButton(
				"Sync With Bluetooth", new edit_paste());

		bluetoothContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sync With Bluetooth");
			}
		});

		contactSyncBand.addCommandButton(bluetoothContactButton,
				RibbonElementPriority.MEDIUM);

		return contactSyncBand;
	}

	JRibbonBand getOtherFunctionBand() {
		JRibbonBand otherFunctionBand = new JRibbonBand("Others",
				new edit_paste(), new ExpandActionListener());

		JCommandButton remindBirthdayButton = new JCommandButton(
				"Birthday Tips", new edit_paste());

		remindBirthdayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Birthday Tips");
			}
		});

		otherFunctionBand.addCommandButton(remindBirthdayButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton arrangeContactButton = new JCommandButton(
				"Contact Arrangement", new edit_paste());

		arrangeContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Contact Arrangement");
			}
		});

		otherFunctionBand.addCommandButton(arrangeContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton relationNetButton = new JCommandButton("Human Cube",
				new edit_paste());

		relationNetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Human Cube");
			}
		});

		otherFunctionBand.addCommandButton(relationNetButton,
				RibbonElementPriority.MEDIUM);

		return otherFunctionBand;
	}

	JXPanel getMajorPanel() {
		JXPanel majorPanel = new JXPanel();
		majorPanel.setLayout(new BorderLayout());

		// ****************************************************************************
		// a container to put all JXTaskPane together
		JXTaskPaneContainer taskPaneContainer = new JXTaskPaneContainer();

		// create a first taskPane with common actions
		JXTaskPane actionPane = new JXTaskPane();
		actionPane.setTitle("Files and Folders");
		actionPane.setSpecial(true);

		// add this taskPane to the taskPaneContainer
		taskPaneContainer.add(actionPane);

		// create another taskPane, it will show details of the selected file
		JXTaskPane details = new JXTaskPane();
		details.setTitle("Details");

		// add standard components to the details taskPane
		JXLabel searchLabel = new JXLabel("Search:");
		JTextField searchField = new JTextField("");
		details.add(searchLabel);
		details.add(searchField);

		taskPaneContainer.add(details);

		majorPanel.add(taskPaneContainer, BorderLayout.WEST);
		//
		// JXTaskPane
		// ****************************************************************************

		// ****************************************************************************
		JXStatusBar bar = new JXStatusBar();
		JXLabel statusLabel = new JXLabel("Ready");
		JXStatusBar.Constraint c1 = new Constraint();
		c1.setFixedWidth(100);
		bar.add(statusLabel, c1); // Fixed width of 100 with no inserts
		JXStatusBar.Constraint c2 = new Constraint(
				JXStatusBar.Constraint.ResizeBehavior.FILL); // Fill with no
		// inserts
		JProgressBar pbar = new JProgressBar();
		bar.add(pbar, c2); // Fill with no inserts - will use remaining space

		majorPanel.add(bar, BorderLayout.SOUTH);
		//
		// JXStatusBar
		// ****************************************************************************

		// ****************************************************************************
		JTabbedPane centerPanel = new JTabbedPane();

		JXPanel loginPanel = new JXPanel();
		loginPanel.setLayout(new SpringLayout());

		final JXLoginPane panel = new JXLoginPane(new LoginService() {
			public boolean authenticate(String name, char[] password,
					String server) throws Exception {
				// perform authentication and return true on success.
				String str = String.valueOf(password);

				System.out.println("NAME:\t" + name);
				System.out.println("PASSWORD:\t" + str);

				if (name.equals("Sa") && str.equals("Sa")) {
					return true;
				}

				return false;
			}
		});

		final JFrame frame = JXLoginPane.showLoginFrame(panel);

		JXButton loginButton = new JXButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.getStatus() == JXLoginPane.Status.SUCCEEDED)
					return;

				System.out.println("Login");
				frame.setVisible(!frame.isVisible());
			}
		});

		loginPanel.add(loginButton);
		centerPanel.addTab("Login Tab", loginPanel);

		JXPanel monthPanel = new JXPanel();
		monthPanel.add(new JXMonthView());
		centerPanel.addTab("Month Tab", monthPanel);

		JXPanel treePanel = new JXPanel();
		treePanel.setLayout(new SpringLayout());
		JXTree tree = new JXTree(new FileSystemModel());
		// use system file icons and name to render
		tree.setCellRenderer(new DefaultTreeRenderer(IconValues.FILE_ICON,
				StringValues.FILE_NAME));
		// highlight condition: file modified after a date
		HighlightPredicate predicate = new HighlightPredicate() {
			public boolean isHighlighted(Component renderer,
					ComponentAdapter adapter) {
				// File file = getUserObject(adapter.getValue());
				// return file != null ? lastWeek < file.lastModified : false;
				return false;
			}
		};
		// highlight with foreground color
		tree.addHighlighter(new ColorHighlighter(predicate, null, Color.RED));

		treePanel.add(tree);
		centerPanel.addTab("Tree Tab", treePanel);
		// ****************************************************************************

		majorPanel.add(centerPanel, BorderLayout.CENTER);

		return majorPanel;
	}

	private void configureRibbon() {
		JRibbonBand userManagerBand = this.getUserManagerBand();
		JRibbonBand newContactBand = this.getContactManagerBand();
		RibbonTask basicTask = new RibbonTask("Basic", userManagerBand,
				newContactBand);
		basicTask.setKeyTip("P");
		this.getRibbon().addTask(basicTask);

		JRibbonBand contactExchangeBand = this.getContactExchangeBand();
		JRibbonBand contactSyncBand = this.getContactSyncBand();
		JRibbonBand otherFunctionBand = this.getOtherFunctionBand();
		RibbonTask advancedTask = new RibbonTask("Advanced",
				contactExchangeBand, contactSyncBand, otherFunctionBand);
		this.getRibbon().addTask(advancedTask);

		JXPanel majorPanel = this.getMajorPanel();

		this.add(majorPanel, BorderLayout.CENTER);
	}

	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys
				.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// 外观设置
				try {
					UIManager
							.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
					JFrame.setDefaultLookAndFeelDecorated(false);
					JDialog.setDefaultLookAndFeelDecorated(false);
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

				// Locale.setDefault(new Locale("USA"));
				// **************************************************

				InitGlobalFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

				EmptyRibbon er = new EmptyRibbon();
				er.configureRibbon();
				er.configureApplicationMenu();
				Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getMaximumWindowBounds();
				er.setPreferredSize(new Dimension(r.width, r.height / 2));
				er.pack();
				er.setLocation(r.x, r.y);
				er.setVisible(true);
				er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
	}
}
