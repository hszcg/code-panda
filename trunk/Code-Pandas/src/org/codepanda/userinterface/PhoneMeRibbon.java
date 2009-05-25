package org.codepanda.userinterface;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.application.googlecontactsyn.GContactOper;
import org.codepanda.userinterface.messagehandler.DeleteContactMessageHandler;
import org.codepanda.userinterface.messagehandler.ExportContactMessageHandler;
import org.codepanda.userinterface.messagehandler.ImportContactMessageHandler;
import org.codepanda.userinterface.utility.ExtensionFileFilter;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.ContactSectionType;
import org.codepanda.utility.data.DataPool;
import org.jvnet.flamingo.common.JCommandButton;
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

	public PhoneMeRibbon(PhoneMeFrame mainFrame) {
		this.mainFrame = mainFrame;

		try {
			JRibbonBand userManagerBand = this.getUserManagerBand();
			JRibbonBand contactManagerBand = this.getContactManagerBand();
			JRibbonBand commonLabelManagerBand = this
					.getCommonLabelManagerBand();
			//userManagerBand.setTitle("User Manager");
			//contactManagerBand.setTitle("User Manager");
			//commonLabelManagerBand.setTitle("User Manager");
			basicTask = new RibbonTask("��������", userManagerBand,
					contactManagerBand, commonLabelManagerBand);
			basicTask.setKeyTip("B");

			JRibbonBand contactExchangeBand = this.getContactExchangeBand();
			JRibbonBand contactSyncBand = this.getContactSyncBand();
			JRibbonBand otherFunctionBand = this.getOtherFunctionBand();
			//contactExchangeBand.setTitle("User Manager");
			//contactSyncBand.setTitle("User Manager");
			//otherFunctionBand.setTitle("User Manager");
			advancedTask = new RibbonTask("�߼�����", contactExchangeBand,
					contactSyncBand, otherFunctionBand);
			advancedTask.setKeyTip("A");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	private class ExpandActionListener implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			JOptionPane.showMessageDialog(mainFrame, "Expand button clicked");
//		}
//	}

	private JRibbonBand getUserManagerBand() throws IOException {
		JRibbonBand userManagerBand = new JRibbonBand("�û�����",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user network.png")),
						new Dimension(32, 32)), null);
		userManagerBand.startGroup();

		JCommandButton newUserButton;

		newUserButton = new JCommandButton("�½��û�", ImageWrapperResizableIcon
				.getIcon(ImageIO.read(this.getClass().getResource(
						"/icon/plateIcon/user.png")), new Dimension(32, 32)));

		newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�½��û�");

				PhoneMeCreateNewUserDialog dialog = new PhoneMeCreateNewUserDialog(
						mainFrame, null);
				dialog.setVisible(true);
			}
		});

		userManagerBand.addCommandButton(newUserButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteUserButton = new JCommandButton("ɾ���û�",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user root.png")),
						new Dimension(32, 32)));

		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ɾ���û�");
				// TODO delete current user

				PhoneMeCorfirmDialog deleteUserDialog = new PhoneMeCorfirmDialog(
						mainFrame);
				deleteUserDialog.setVisible(true);
			}
		});

		userManagerBand.addCommandButton(deleteUserButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editUserButton = new JCommandButton("�޸��û���Ϣ",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/user network.png")),
						new Dimension(32, 32)));

		editUserButton.addActionListener(new ActionListener() {
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
		});

		userManagerBand.addCommandButton(editUserButton,
				RibbonElementPriority.MEDIUM);

		// userManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(userManagerBand));

		return userManagerBand;
	}

	private JRibbonBand getContactManagerBand() throws IOException {
		JRibbonBand contactManagerBand = new JRibbonBand("��ϵ�˹���",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)), null);

		contactManagerBand.startGroup();
		
		JCommandButton newContactButton = new JCommandButton("�½���ϵ��",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook.png")),
						new Dimension(32, 32)));

		newContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�½���ϵ��");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab(
						"�½���ϵ��",
						new ContactInfoPanel(mainFrame, null, true,
								ContactInfoPanel.CONTACT_INFO_PANEL));
			}
		});

		contactManagerBand.addCommandButton(newContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteContactButton = new JCommandButton("ɾ����ϵ��",
				ImageWrapperResizableIcon.getIcon(ImageIO
						.read(this.getClass().getResource(
								"/icon/plateIcon/addressbook manager.png")),
						new Dimension(32, 32)));

		deleteContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ɾ����ϵ��");
				Component currentTab = mainFrame.getMyPhoneMeMajorPanel()
						.getCurrentTab();
				if (currentTab instanceof ContactInfoPanel) {
					ContactOperations p = ((ContactInfoPanel) currentTab)
							.getMyContact();
					if (p != null) {
						// ���½���ϵ��
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

		JCommandButton editContactButton = new JCommandButton("�޸���ϵ����Ϣ",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 1.png")),
						new Dimension(32, 32)));

		editContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�޸���ϵ��");
				Component currentTab = mainFrame.getMyPhoneMeMajorPanel()
						.getCurrentTab();
				if (currentTab instanceof ContactInfoPanel) {
					((ContactInfoPanel) currentTab).setEditable(true);
				}
			}
		});

		contactManagerBand.addCommandButton(editContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton allContactButton = new JCommandButton("��ʾ������ϵ��",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/chat 2.png")),
						new Dimension(32, 32)));

		allContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ʾ������ϵ��");
				ArrayList<Integer> myISNList = new ArrayList<Integer>();
				myISNList.addAll(DataPool.getInstance().getAllContactISNMap()
						.keySet());
				mainFrame.getMyPhoneMeMajorPanel().addNewTab("All Contacts",
						new SearchResultPanel(mainFrame, myISNList, ContactSectionType.PHONE_NUMBER).getMainPanel());
			}
		});

		contactManagerBand.addCommandButton(allContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton statContactButton = new JCommandButton("��ϵ��ͳ��",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/addressbook 2.png")),
						new Dimension(32, 32)));

		statContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ��ͳ��");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab("��ϵ��ͳ��",
						new StatContactPanel(mainFrame));
			}
		});

		contactManagerBand.addCommandButton(statContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton searchContactButton = new JCommandButton("��ϵ������",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/search.png")),
						new Dimension(32, 32)));

		searchContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ������");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab("�߼�����",
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
		JRibbonBand commonLabelManagerBand = new JRibbonBand("��ͨ��ǩ����",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/clipboard.png")),
						new Dimension(32, 32)), null);
		
		commonLabelManagerBand.startGroup();

		JCommandButton newCommonLabelButton = new JCommandButton("��ͨ��ǩ����",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/clipboard.png")),
						new Dimension(32, 32)));

		newCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ͨ��ǩ����");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab(
						"Common Label Show",
						new CommonLabelShowPanel(mainFrame));
			}
		});

		commonLabelManagerBand.addCommandButton(newCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteCommonLabelButton = new JCommandButton("ɾ����ͨ��ǩ",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/trash full.png")),
						new Dimension(32, 32)));

		deleteCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ɾ����ͨ��ǩ");
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

		JCommandButton editCommonLabelButton = new JCommandButton("�޸���ͨ��ǩ",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/document rtf.png")),
						new Dimension(32, 32)));

		editCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�޸���ͨ��ǩ");
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
		JRibbonBand contactExchangeBand = new JRibbonBand("���ݵ��뵼��",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/harddrive floppy.png")),
						new Dimension(32, 32)), null);
		contactExchangeBand.startGroup();

		JCommandButton importContactButton = new JCommandButton("��ϵ�˵���",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/folder downloads.png")),
						new Dimension(32, 32)));

		importContactButton.addActionListener(new ActionListener() {
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

				mainFrame.updateAllPanel(0);
			}
		});

		contactExchangeBand.addCommandButton(importContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton exportContactButton = new JCommandButton("��ϵ�˵���",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/save to floppy.png")),
						new Dimension(32, 32)));

		exportContactButton.addActionListener(new ActionListener() {
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
		});

		contactExchangeBand.addCommandButton(exportContactButton,
				RibbonElementPriority.MEDIUM);

		return contactExchangeBand;
	}

	JRibbonBand getContactSyncBand() throws IOException {
		JRibbonBand contactSyncBand = new JRibbonBand("��ϵ��ͬ��",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/regional settings.png")),
						new Dimension(32, 32)), null);
		contactSyncBand.startGroup();

		JCommandButton googleContactButton = new JCommandButton(
				"��Google Contactͬ��", ImageWrapperResizableIcon.getIcon(ImageIO
						.read(this.getClass().getResource(
								"/icon/plateIcon/regional settings.png")),
						new Dimension(32, 32)));

		googleContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��Google Contactͬ��");
				final JDialog dialog = new JDialog(mainFrame,
						"Sync With Gmail", true);
				final JTextField userNameField = new JTextField(15);
				final JPasswordField userPasswordField = new JPasswordField(15);
				final JLabel errorLabel = new JLabel();
				JButton upload = new JButton("�ϴ�");
				upload.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(
							GContactOper.createContact
							(userNameField.getText(), 
							String.valueOf(userPasswordField.getPassword())))
							dialog.dispose();
							else
								errorLabel.setText("��¼ʧ��");
						} catch (ServiceException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				JButton download = new JButton("����");
				download.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(
							GContactOper.getAllContacts
							(userNameField.getText(), 
									String.valueOf(userPasswordField.getPassword())))
							dialog.dispose();
							else
								errorLabel.setText("��¼ʧ��");
						} catch (ServiceException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						mainFrame.updateAllPanel(0);
					}
				});
				JButton cancel = new JButton("ȡ��ͬ������");
				cancel.addActionListener(new ActionListener(){

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
				
				builder.addLabel("������gmail�ʺ�", cc.xy(2, 2));
				builder.addLabel("�û���", cc.xy(2, 4));
				builder.add(userNameField, cc.xy(4, 4));
				
				builder.addLabel("����", cc.xy(2, 6));
				builder.add(userPasswordField, cc.xy(4, 6));
				
				builder.add(upload, cc.xy(2, 8));
				builder.add(download, cc.xy(4, 8));
				builder.add(errorLabel, cc.xy(2, 10));
				builder.add(cancel, cc.xy(4, 10));
				//dialog.add(upload);
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

		/*JCommandButton bluetoothContactButton = new JCommandButton("���ֻ�����ͬ��",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/computer network.png")),
						new Dimension(32, 32)));

		bluetoothContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("���ֻ�����ͬ��");
			}
		});

		contactSyncBand.addCommandButton(bluetoothContactButton,
				RibbonElementPriority.MEDIUM);*/

		return contactSyncBand;
	}

	JRibbonBand getOtherFunctionBand() throws IOException {
		JRibbonBand otherFunctionBand = new JRibbonBand("�����߼�����",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/tool.png")),
						new Dimension(32, 32)), null);
		otherFunctionBand.startGroup();

		JCommandButton remindBirthdayButton = new JCommandButton("��������",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/calendar.png")),
						new Dimension(32, 32)));

		remindBirthdayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��������");
				mainFrame.getMyPhoneMeMajorPanel().addNewTab("��������",
						new BirthdayRemindPanel(mainFrame));
			}
		});

		otherFunctionBand.addCommandButton(remindBirthdayButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton arrangeContactButton = new JCommandButton("��ϵ������",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/folder user.png")),
						new Dimension(32, 32)));

		arrangeContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ������");
			}
		});

		otherFunctionBand.addCommandButton(arrangeContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton relationNetButton = new JCommandButton("������",
				ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass()
						.getResource("/icon/plateIcon/windows.png")),
						new Dimension(32, 32)));

		relationNetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("������");
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
