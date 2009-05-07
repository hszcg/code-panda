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
import org.jvnet.flamingo.ribbon.*;
import org.jvnet.substance.SubstanceLegacyDefaultLookAndFeel;
import org.jvnet.substance.skin.*;
import org.jvnet.substance.skinpack.SubstanceGreenMagicLookAndFeel;

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
				new document_save_as(), "�û�����", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("�û�����");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
		// userManager.setActionKeyTip("A");
		// userManager.setPopupKeyTip("F");

		RibbonApplicationMenuEntrySecondary newUser = new RibbonApplicationMenuEntrySecondary(
				new x_office_document(), "�½��û�", null,
				CommandButtonKind.ACTION_ONLY);
		newUser.setDescriptionText("�½��û��˻���" + "�������½����˻�ʹ��PhoneMe��������й���");
		// newUser.setActionKeyTip("W");
		RibbonApplicationMenuEntrySecondary deleteUser = new RibbonApplicationMenuEntrySecondary(
				new text_html(), "ɾ���û�", null, CommandButtonKind.ACTION_ONLY);
		deleteUser.setDescriptionText("���û��б���ɾ��ĳ���û�����Ҫ����������֤");
		// deleteUser.setEnabled(false);
		// deleteUser.setActionKeyTip("H");
		RibbonApplicationMenuEntrySecondary editUser = new RibbonApplicationMenuEntrySecondary(
				new document_save_as(), "�޸��û���Ϣ", null,
				CommandButtonKind.ACTION_ONLY);
		editUser.setDescriptionText("�޸ĵ�ǰ�û��˻��ĸ�����Ϣ");
		// editUser.setActionKeyTip("O");

		userManager.addSecondaryMenuGroup("�û���Ϣ����������ӡ�ɾ�����޸��û��˻�", newUser,
				deleteUser, editUser);

		RibbonApplicationMenuEntryPrimary contactManager = new RibbonApplicationMenuEntryPrimary(
				new document_save_as(), "��ϵ�˹���", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("��ϵ�˹���");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
		// userManager.setActionKeyTip("A");
		// userManager.setPopupKeyTip("F");

		RibbonApplicationMenuEntrySecondary newContact = new RibbonApplicationMenuEntrySecondary(
				new x_office_document(), "�½���ϵ��", null,
				CommandButtonKind.ACTION_ONLY);
		newContact.setDescriptionText("�½���ϵ�ˣ�����д����ϵ�˵������Ϣ");
		// newUser.setActionKeyTip("W");
		RibbonApplicationMenuEntrySecondary deleteContact = new RibbonApplicationMenuEntrySecondary(
				new text_html(), "ɾ����ϵ��", null, CommandButtonKind.ACTION_ONLY);
		deleteContact.setDescriptionText("����ϵ���б���ɾ��һ��������ϵ��");
		// deleteUser.setEnabled(false);
		// deleteUser.setActionKeyTip("H");
		RibbonApplicationMenuEntrySecondary editContact = new RibbonApplicationMenuEntrySecondary(
				new document_save_as(), "�޸���ϵ����Ϣ", null,
				CommandButtonKind.ACTION_ONLY);
		editContact.setDescriptionText("�޸ĵ�ǰ�û��˻��µ�ĳ����ϵ����Ϣ");
		// editUser.setActionKeyTip("O");

		RibbonApplicationMenuEntrySecondary arrangeContact = new RibbonApplicationMenuEntrySecondary(
				new document_save_as(), "��ϵ��ͳ��", null,
				CommandButtonKind.ACTION_ONLY);
		arrangeContact.setDescriptionText("ͳ�Ƶ�ǰ�û��˻��µ���ϵ����Ϣ");
		// editUser.setActionKeyTip("O");

		RibbonApplicationMenuEntrySecondary searchContact = new RibbonApplicationMenuEntrySecondary(
				new text_html(), "��ϵ������", null, CommandButtonKind.ACTION_ONLY);
		searchContact.setDescriptionText("�û���������������" + "ȷ�Ϻ�᷵�ط�����������ϵ���б�");
		// deleteUser.setActionKeyTip("H");

		contactManager.addSecondaryMenuGroup("��ϵ�˹���" + "������ӡ�ɾ�����޸ġ�ͳ�ƺ�������ϵ��",
				newContact, deleteContact, editContact, arrangeContact,
				searchContact);

		RibbonApplicationMenuEntryPrimary contactExchange = new RibbonApplicationMenuEntryPrimary(
				new document_save_as(), "���ݵ��뵼��", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("���ݵ��뵼��");
					}
				}, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
		// userManager.setActionKeyTip("A");
		// userManager.setPopupKeyTip("F");

		RibbonApplicationMenuEntrySecondary importContact = new RibbonApplicationMenuEntrySecondary(
				new x_office_document(), "��ϵ�˵���", null,
				CommandButtonKind.ACTION_ONLY);
		importContact.setDescriptionText("���ļ��е�����ϵ�����ݣ�"
				+ "����֧�ִ�.xls��.csv��ʽ������ϵ�˵���ǰ�û��˻���");
		// importContact.setActionKeyTip("W");
		RibbonApplicationMenuEntrySecondary exportContact = new RibbonApplicationMenuEntrySecondary(
				new text_html(), "��ϵ�˵���", null, CommandButtonKind.ACTION_ONLY);
		exportContact.setDescriptionText("����ǰ�û��˻��µ���ϵ����Ϣ�������ļ���"
				+ "����֧�ֵ�����.xls��.csv��ʽ���ļ���");
		// exportContact.setActionKeyTip("H");

		contactExchange.addSecondaryMenuGroup("��ϵ�����ݵ��뵼����"
				+ "����֧��.xls��.csv��ʽ�ļ�", importContact, exportContact);

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

	private JRibbonBand getUserManagerBand(){
		JRibbonBand userManagerBand = new JRibbonBand("�û�����",
				new edit_paste(), new ExpandActionListener());
		
		JCommandButton newUserButton = new JCommandButton("�½��û�",
				new edit_paste());
		
		newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�½��û�");
			}
		});
		
		userManagerBand.addCommandButton
		(newUserButton, RibbonElementPriority.MEDIUM);
		
		JCommandButton deleteUserButton = new JCommandButton("ɾ���û�",
				new edit_paste());
		
		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ɾ���û�");
			}
		});
		
		userManagerBand.addCommandButton
		(deleteUserButton, RibbonElementPriority.MEDIUM);
		
		JCommandButton editUserButton = new JCommandButton("�޸��û���Ϣ",
				new edit_paste());
		
		editUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�޸��û���Ϣ");
			}
		});
		
		userManagerBand.addCommandButton
		(editUserButton, RibbonElementPriority.MEDIUM);
		
		//userManagerBand.setResizePolicies(CoreRibbonResizePolicies
		//		.getCorePoliciesRestrictive(userManagerBand));
		
		return userManagerBand;
	}
	private JRibbonBand getContactManagerBand(){
		JRibbonBand contactManagerBand = new JRibbonBand("��ϵ�˹���",
				new edit_paste(), new ExpandActionListener());
		

		JCommandButton newContactButton = new JCommandButton("�½���ϵ��",
				new edit_paste());
		
		newContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�½���ϵ��");
			}
		});
		
		contactManagerBand.addCommandButton
		(newContactButton, RibbonElementPriority.MEDIUM);
		
		JCommandButton deleteContactButton = new JCommandButton("ɾ����ϵ��",
				new edit_paste());
		
		deleteContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ɾ����ϵ��");
			}
		});
		
		contactManagerBand.addCommandButton
		(deleteContactButton, RibbonElementPriority.MEDIUM);
		
		JCommandButton editContactButton = new JCommandButton("�޸���ϵ����Ϣ",
				new edit_paste());
		
		editContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�޸���ϵ��");
			}
		});
		
		contactManagerBand.addCommandButton
		(editContactButton, RibbonElementPriority.MEDIUM);
		
		JCommandButton statContactButton = new JCommandButton("��ϵ��ͳ��",
				new edit_paste());
		
		statContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ��ͳ��");
			}
		});
		
		contactManagerBand.addCommandButton
		(statContactButton, RibbonElementPriority.MEDIUM);
		
		JCommandButton searchContactButton = new JCommandButton("��ϵ������",
				new edit_paste());
		
		searchContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ������");
			}
		});
		
		contactManagerBand.addCommandButton
		(searchContactButton, RibbonElementPriority.MEDIUM);
		
		//contactManagerBand.setResizePolicies(CoreRibbonResizePolicies
		//		.getCorePoliciesRestrictive(contactManagerBand));
		
		return contactManagerBand;
	}
	
	JRibbonBand getContactExchangeBand(){
		JRibbonBand contactExchangeBand = new JRibbonBand("���ݵ��뵼��",
				new edit_paste(), new ExpandActionListener());
		
		JCommandButton importContactButton = new JCommandButton("��ϵ�˵���",
				new edit_paste());
		
		importContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ�˵���");
			}
		});
		
		contactExchangeBand.addCommandButton
		(importContactButton, RibbonElementPriority.MEDIUM);
		
		JCommandButton exportContactButton = new JCommandButton("��ϵ�˵���",
				new edit_paste());
		
		exportContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ�˵���");
			}
		});
		
		contactExchangeBand.addCommandButton
		(exportContactButton, RibbonElementPriority.MEDIUM);
		
		return contactExchangeBand;
	}
	
	JRibbonBand getContactSyncBand(){
		JRibbonBand contactSyncBand = new JRibbonBand("��ϵ��ͬ��",
				new edit_paste(), new ExpandActionListener());
		
		JCommandButton googleContactButton = new JCommandButton
		("��Google Contactͬ��", new edit_paste());
		
		googleContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��Google Contactͬ��");
			}
		});
		
		contactSyncBand.addCommandButton
		(googleContactButton, RibbonElementPriority.MEDIUM);
		
		JCommandButton bluetoothContactButton = new JCommandButton("���ֻ�����ͬ��",
				new edit_paste());
		
		bluetoothContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("���ֻ�����ͬ��");
			}
		});
		
		contactSyncBand.addCommandButton
		(bluetoothContactButton, RibbonElementPriority.MEDIUM);
		
		return contactSyncBand;
	}
	
	JRibbonBand getOtherFunctionBand(){
		JRibbonBand otherFunctionBand = new JRibbonBand("�����߼�����",
				new edit_paste(), new ExpandActionListener());
		
		JCommandButton remindBirthdayButton = new JCommandButton("��������",
				new edit_paste());
		
		remindBirthdayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��������");
			}
		});
		
		otherFunctionBand.addCommandButton
		(remindBirthdayButton, RibbonElementPriority.MEDIUM);
		
		JCommandButton arrangeContactButton = new JCommandButton
		("��ϵ������", new edit_paste());
		
		arrangeContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ������");
			}
		});
		
		otherFunctionBand.addCommandButton
		(arrangeContactButton, RibbonElementPriority.MEDIUM);
		
		JCommandButton relationNetButton = new JCommandButton("������",
				new edit_paste());
		
		relationNetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("������");
			}
		});
		
		otherFunctionBand.addCommandButton
		(relationNetButton, RibbonElementPriority.MEDIUM);
		
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
		RibbonTask basicTask = new RibbonTask("��������", userManagerBand,
				newContactBand);
		basicTask.setKeyTip("B");
		this.getRibbon().addTask(basicTask);

		JRibbonBand contactExchangeBand = this.getContactExchangeBand();
		JRibbonBand contactSyncBand = this.getContactSyncBand();
		JRibbonBand otherFunctionBand = this.getOtherFunctionBand();
		RibbonTask advancedTask = new RibbonTask("�߼�����",
				contactExchangeBand, contactSyncBand, otherFunctionBand);
		advancedTask.setKeyTip("A");
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
				// �������
				try {
					UIManager
							.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
					JFrame.setDefaultLookAndFeelDecorated(false);
					JDialog.setDefaultLookAndFeelDecorated(false);
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

				// Locale.setDefault(new Locale("USA"));
				// **************************************************

				try {
					InitGlobalFont(new Font("����", Font.PLAIN, 12));
				} catch (Exception e) {
					InitGlobalFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
				}

				EmptyRibbon er = new EmptyRibbon();
				er.configureRibbon();
				er.configureApplicationMenu();
				Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getMaximumWindowBounds();
				er.setPreferredSize(new Dimension(r.width, r.height / 2));
				er.pack();
				er.setLocation(r.x, r.y);
				er.setVisible(true);
				er.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}
