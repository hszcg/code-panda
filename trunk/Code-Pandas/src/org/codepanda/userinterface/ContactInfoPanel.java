package org.codepanda.userinterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.codepanda.userinterface.listener.*;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.label.RelationLabel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;

public class ContactInfoPanel extends JXPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7578999178358931661L;
	public static final int USER_INFO_PANEL = 1;
	public static final int CONTACT_INFO_PANEL = 2;

	private PhoneMeFrame parentFrame;
	private JDialog parentDialog;
	private ContactOperations myContact;
	private boolean isEditable;

	// All the buttons
	private ArrayList<JButton> myButtonList;
	// All the textfields
	private ArrayList<JTextField> myTextFieldList;

	//
	private JPanel upperPanel;
	private JPanel upperLeftPanel;
	private JPanel mainPanel;

	// 图片部分
	private JLabel headImageLabel;
	private JButton editHeadImageButton;
	private JButton deleteHeadImageButton;

	// 联系人姓名部分
	private JTextField nameField;
	private JButton nameEditButton;

	// 联系人电话号码部分
	private JComboBox phoneNumberBox;
	private JButton addPhoneNumberButton;
	private JButton editPhoneNumberButton;
	private JButton deletePhoneNumberButton;
	private PhoneMeField phoneNumberField;

	// 联系人e-mail部分
	private JComboBox emailAddressBox;
	private JButton addEmailAddressButton;
	private JButton editEmailAddressButton;
	private JButton deleteEmailAddressButton;
	private PhoneMeField emailField;

	// 联系人住址部分
	private JComboBox contactAddressBox;
	private JButton addContactAddressButton;
	private JButton editContactAddressButton;
	private JButton deleteContactAddressButton;

	// 联系人工作／学习单位
	private JComboBox workingDepartmentBox;
	private JButton addWorkingDepartmentButton;
	private JButton editWorkingDepartmentButton;
	private JButton deleteWorkingDepartmentButton;

	// 联系人即时联系方式部分
	private JComboBox imContactInformationBox;
	private JButton addImContactInformationButton;
	private JButton editImContactInformationButton;
	private JButton deleteImContactInformationButton;

	// 联系人生日部分
	private JTextField contactBirthdayField;
	private JButton editcontactBirthdayButton;
	private JXDatePicker contactBirthdayPicker;
	private static SimpleDateFormat birthdayDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	// 联系人Web地址
	private JComboBox urlListBox;
	private JButton addUrlListButton;
	private JButton editUrlListButton;
	private JButton deleteUrlListButton;

	// 联系人普通标签
	private JComboBox commonLabelListBox;
	private JButton addCommonLabelListButton;
	private JButton editCommonLabelListButton;
	private JButton deleteCommonLabelListButton;
	private PhoneMeField commonLabelField;

	// 联系人所属分组
	private JComboBox groupListBox;
	private JButton addGroupListButton;
	private JButton editGroupListButton;
	private JButton deleteGroupListButton;

	// 联系人关系标签
	private JComboBox relationLabelListBox;
	private JComboBox objectContactListBox;
	private JButton addRelationLabelListButton;
	private JButton editRelationLabelListButton;
	private JButton deleteRelationLabelListButton;
	private ArrayList<RelationLabel> localRelationLabelList;
	private HashMap<String, ArrayList<Integer>> relationLabelList;
	private HashMap<String, ArrayList<String>> relationLabelNameList;

	private JButton confirmButton;
	private JButton cancelButton;

	// private StringBuffer messageXML;

	/**
	 * @param parenetFrame
	 * @param myContact
	 * @param isEditable
	 */
	public ContactInfoPanel(PhoneMeFrame parenetFrame,
			ContactOperations myContact, boolean isEditable, int panelType) {
		super();
		this.parentFrame = parenetFrame;
		this.parentDialog = null;
		this.isEditable = true;
		this.myContact = null;
		this.myButtonList = new ArrayList<JButton>();
		this.myTextFieldList = new ArrayList<JTextField>();
		setUpperPanel();
		setLayout(new BorderLayout());
		add(upperPanel, "North");
		setMainPanel();
		add(mainPanel, "Center");

		this.setMyContact(myContact);

		if (isEditable == false) {
			this.setEditable(isEditable);
		} else {
			if (panelType == USER_INFO_PANEL) {
				confirmButton.setVisible(false);
				cancelButton.setVisible(false);
			}
		}
	}

	/**
	 * @param parenetDialog
	 * @param myContact
	 * @param isEditable
	 */
	public ContactInfoPanel(JDialog parenetDialog, ContactOperations myContact,
			boolean isEditable, int panelType) {
		super();
		this.parentFrame = null;
		this.parentDialog = parenetDialog;
		this.myContact = null;
		this.isEditable = true;
		this.myButtonList = new ArrayList<JButton>();
		this.myTextFieldList = new ArrayList<JTextField>();
		setUpperPanel();
		setLayout(new BorderLayout());
		add(upperPanel, "North");
		setMainPanel();
		add(mainPanel, "Center");

		this.setMyContact(myContact);

		if (isEditable == false) {
			this.setEditable(isEditable);
		} else {
			confirmButton.setVisible(false);
			cancelButton.setVisible(false);
		}
	}

	private void setUpperPanel() {
		upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());

		nameField = new JTextField(15);
		nameField.setEditable(false);
		nameField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nameField.setEditable(false);
			}
		});
		nameEditButton = new JButton("编辑");
		nameEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nameField.setEditable(true);
			}
		});
		this.myButtonList.add(nameEditButton);

		String temp[] = { "010-51534419", "13810013188", "029-85367800" };
		phoneNumberBox = new JComboBox(temp);
		addPhoneNumberButton = new JButton("添加");
		addPhoneNumberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				phoneNumberField.setVisible(true);
				phoneNumberField.setState(0);
				getParentWindow().repaint();
			}
		});
		editPhoneNumberButton = new JButton("编辑");
		editPhoneNumberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				phoneNumberField.setVisible(true);
				phoneNumberField.setState(1);
				getParentWindow().repaint();
			}
		});
		deletePhoneNumberButton = new JButton("删除");
		deletePhoneNumberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				phoneNumberBox.removeItem(phoneNumberBox.getSelectedItem());
			}
		});
		phoneNumberField = new PhoneMeField(20);
		phoneNumberField.addActionListener(new FieldActionListener(
				phoneNumberBox, phoneNumberField));

		// ActionListener phoneNumberListener = new
		// ComboBoxButtonListener(phoneNumberBox, phoneNumberField);
		// addPhoneNumberButton.addActionListener(phoneNumberListener);
		// editPhoneNumberButton.addActionListener(phoneNumberListener);
		// deletePhoneNumberButton.addActionListener(phoneNumberListener);
		this.myButtonList.add(addPhoneNumberButton);
		this.myButtonList.add(editPhoneNumberButton);
		this.myButtonList.add(deletePhoneNumberButton);
		this.myTextFieldList.add(phoneNumberField);

		FormLayout upperRightlayout = new FormLayout(
				"pref, 3dlu, pref, 15dlu, pref, 3dlu, pref, "
						+ "3dlu, pref, 3dlu, pref", // columns
				"p, 8dlu, p, 5dlu, p"); // rows
		upperRightlayout.setColumnGroups(new int[][] { { 5, 7, 9 } });

		PanelBuilder builder = new PanelBuilder(upperRightlayout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

		builder.addSeparator("基本信息", cc.xyw(1, 1, 9));
		builder.addLabel("姓名", cc.xy(1, 3));
		builder.add(nameField, cc.xy(3, 3));
		builder.add(nameEditButton, cc.xy(5, 3));

		builder.addLabel("联系电话", cc.xy(1, 5));
		builder.add(phoneNumberBox, cc.xy(3, 5));
		builder.add(addPhoneNumberButton, cc.xy(5, 5));
		builder.add(editPhoneNumberButton, cc.xy(7, 5));
		builder.add(deletePhoneNumberButton, cc.xy(9, 5));
		builder.add(phoneNumberField, cc.xy(11, 5));

		// upperLeftPanel.add(new JLabel("test1"));
		FormLayout upperLeftlayout = new FormLayout("pref, 9dlu, pref", // columns
				"p, 7dlu, p"); // rows
		upperLeftlayout.setColumnGroups(new int[][] { { 1, 3 } });

		PanelBuilder leftBuilder = new PanelBuilder(upperLeftlayout);
		leftBuilder.setDefaultDialogBorder();

		// CellConstraints leftcc = new CellConstraints();

		headImageLabel = new JLabel();// 联系人图片
		this.setContactHeadImage("/userpic/user1.jpg");
		headImageLabel.setPreferredSize(new Dimension(130, 115));
		headImageLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		editHeadImageButton = new JButton("编辑");
		deleteHeadImageButton = new JButton("删除");
		this.myButtonList.add(editHeadImageButton);
		this.myButtonList.add(deleteHeadImageButton);

		editHeadImageButton.addActionListener(new ContactHeadImageEditListener(
				this));
		deleteHeadImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteContactHeadImage();
			}

		});

		editHeadImageButton.setPreferredSize(new Dimension(50, 20));
		deleteHeadImageButton.setPreferredSize(new Dimension(50, 20));

		// leftBuilder.add(headImageLabel, leftcc.xyw(1, 1, 3));
		// leftBuilder.add(editHeadImageButton, leftcc.xy(1, 3));
		// leftBuilder.add(deleteHeadImageButton, leftcc.xy(3, 3));

		FormLayout upperLeftDownlayout = new FormLayout("pref, 3dlu, pref", // columns
				"p"); // rows
		upperLeftDownlayout.setColumnGroups(new int[][] { { 1, 3 } });

		PanelBuilder leftDownBuilder = new PanelBuilder(upperLeftDownlayout);
		leftDownBuilder.setDefaultDialogBorder();

		CellConstraints leftDowncc = new CellConstraints();
		leftDownBuilder.add(editHeadImageButton, leftDowncc.xy(1, 1));
		leftDownBuilder.add(deleteHeadImageButton, leftDowncc.xy(3, 1));

		upperLeftPanel = new JPanel();
		upperLeftPanel.setLayout(new BorderLayout());
		upperLeftPanel.add(headImageLabel, "Center");
		upperLeftPanel.add(leftDownBuilder.getPanel(), "South");
		// upperLeftPanel.add(editHeadImageButton, "South");
		// upperLeftPanel.add(deleteHeadImageButton, "South");
		upperLeftPanel.setVisible(true);
		// upperPanel.add(leftBuilder.getPanel(), "West");
		upperPanel.add(upperLeftPanel, "West");
		upperPanel.add(builder.getPanel(), "Center");
	}

	private void setMainPanel() {
		mainPanel = new JPanel();
		FormLayout mainAreaLayout = new FormLayout(
				"pref, 3dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref",// column
				"p, 8dlu, p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p,"
						+ " 5dlu, p, 5dlu, p");// row
		// mainAreaLayout.setColumnGroups(new int[][]{{7, 9, 11}});
		mainAreaLayout.setColumnGroups(new int[][] { { 5, 7, 9 } });

		PanelBuilder builder = new PanelBuilder(mainAreaLayout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

		String temp[] = { "中华人民共和国北京市海淀区清华大学紫荆公寓1#419B" };

		builder.addSeparator("个人信息", cc.xyw(1, 1, 9));
		builder.addLabel("           ", cc.xy(11, 1));
		// e-mail
		emailAddressBox = new JComboBox(temp);
		addEmailAddressButton = new JButton("添加");
		addEmailAddressButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				emailField.setVisible(true);
				emailField.setState(0);
				getParentWindow().repaint();
			}
		});
		editEmailAddressButton = new JButton("编辑");
		editEmailAddressButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				emailField.setVisible(true);
				emailField.setState(1);
				getParentWindow().repaint();
			}
		});
		deleteEmailAddressButton = new JButton("删除");
		deleteEmailAddressButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				emailAddressBox.removeItem(emailAddressBox.getSelectedItem());
			}
		});
		emailField = new PhoneMeField(20);
		emailField.addActionListener(new FieldActionListener(emailAddressBox,
				emailField));
		this.myButtonList.add(addEmailAddressButton);
		this.myButtonList.add(editEmailAddressButton);
		this.myButtonList.add(deleteEmailAddressButton);
		this.myTextFieldList.add(emailField);

		builder.addLabel("电子邮箱", cc.xy(1, 3));
		builder.add(emailAddressBox, cc.xy(3, 3));
		builder.add(addEmailAddressButton, cc.xy(5, 3));
		builder.add(editEmailAddressButton, cc.xy(7, 3));
		builder.add(deleteEmailAddressButton, cc.xy(9, 3));
		builder.add(emailField, cc.xy(11, 3));

		// 联系人住址
		contactAddressBox = new JComboBox();
		addContactAddressButton = new JButton("添加");
		editContactAddressButton = new JButton("编辑");
		deleteContactAddressButton = new JButton("删除");
		this.myButtonList.add(addContactAddressButton);
		this.myButtonList.add(editContactAddressButton);
		this.myButtonList.add(deleteContactAddressButton);

		builder.addLabel("家庭住址", cc.xy(1, 5));
		builder.add(contactAddressBox, cc.xy(3, 5));
		builder.add(addContactAddressButton, cc.xy(5, 5));
		builder.add(editContactAddressButton, cc.xy(7, 5));
		builder.add(deleteContactAddressButton, cc.xy(9, 5));

		// 联系人工作／学习单位
		workingDepartmentBox = new JComboBox();
		addWorkingDepartmentButton = new JButton("添加");
		editWorkingDepartmentButton = new JButton("编辑");
		deleteWorkingDepartmentButton = new JButton("删除");

		this.myButtonList.add(addWorkingDepartmentButton);
		this.myButtonList.add(editWorkingDepartmentButton);
		this.myButtonList.add(deleteWorkingDepartmentButton);

		builder.addLabel("工作单位", cc.xy(1, 7));
		builder.add(workingDepartmentBox, cc.xy(3, 7));
		builder.add(addWorkingDepartmentButton, cc.xy(5, 7));
		builder.add(editWorkingDepartmentButton, cc.xy(7, 7));
		builder.add(deleteWorkingDepartmentButton, cc.xy(9, 7));

		// 联系人即时联系方式
		imContactInformationBox = new JComboBox();
		addImContactInformationButton = new JButton("添加");
		editImContactInformationButton = new JButton("编辑");
		deleteImContactInformationButton = new JButton("删除");

		this.myButtonList.add(addImContactInformationButton);
		this.myButtonList.add(editImContactInformationButton);
		this.myButtonList.add(deleteImContactInformationButton);

		builder.addLabel("即时联系方式", cc.xy(1, 9));
		builder.add(imContactInformationBox, cc.xy(3, 9));
		builder.add(addImContactInformationButton, cc.xy(5, 9));
		builder.add(editImContactInformationButton, cc.xy(7, 9));
		builder.add(deleteImContactInformationButton, cc.xy(9, 9));

		// 联系人生日部分
		contactBirthdayField = new JTextField(30);
		contactBirthdayPicker = new JXDatePicker(this.getContactBirthday());
		contactBirthdayPicker.setFormats(birthdayDateFormat);
		contactBirthdayPicker.setVisible(false);
		contactBirthdayPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContactBirthday(contactBirthdayPicker.getDate());
			}
		});

		editcontactBirthdayButton = new JButton("编辑");
		this.myButtonList.add(editcontactBirthdayButton);
		editcontactBirthdayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contactBirthdayPicker.setVisible(!contactBirthdayPicker
						.isVisible());
			}
		});

		builder.addLabel("联系人生日", cc.xy(1, 11));
		builder.add(contactBirthdayField, cc.xy(3, 11));
		builder.add(editcontactBirthdayButton, cc.xy(5, 11));
		builder.add(contactBirthdayPicker, cc.xy(11, 11));

		// 联系人Web地址
		urlListBox = new JComboBox();
		addUrlListButton = new JButton("添加");
		editUrlListButton = new JButton("编辑");
		deleteUrlListButton = new JButton("删除");

		this.myButtonList.add(addUrlListButton);
		this.myButtonList.add(editUrlListButton);
		this.myButtonList.add(deleteUrlListButton);

		builder.addLabel("Web地址", cc.xy(1, 13));
		builder.add(urlListBox, cc.xy(3, 13));
		builder.add(addUrlListButton, cc.xy(5, 13));
		builder.add(editUrlListButton, cc.xy(7, 13));
		builder.add(deleteUrlListButton, cc.xy(9, 13));

		// 标签以及分组的显示区域
		FormLayout mainSouthAreaLayout = new FormLayout(
				"pref, 3dlu, pref, 2dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref, "
						+ "5dlu, pref, 10dlu, pref, 5dlu, pref",// column
				"p, 8dlu, p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p");// row

		mainSouthAreaLayout.setColumnGroups(new int[][] { { 7, 9, 11 } });

		PanelBuilder downbuilder = new PanelBuilder(mainSouthAreaLayout);
		downbuilder.setDefaultDialogBorder();

		CellConstraints downcc = new CellConstraints();

		downbuilder.addSeparator("标签及分组信息", downcc.xyw(1, 1, 11));
		downbuilder.addLabel("                        ", downcc.xy(13, 1));

		// 联系人普通标签
		commonLabelListBox = new JComboBox();
		addCommonLabelListButton = new JButton("添加");
		editCommonLabelListButton = new JButton("编辑");
		deleteCommonLabelListButton = new JButton("删除");
		commonLabelField = new PhoneMeField(20);
		commonLabelField.setVisible(false);

		this.myButtonList.add(addCommonLabelListButton);
		this.myButtonList.add(editCommonLabelListButton);
		this.myButtonList.add(deleteCommonLabelListButton);

		downbuilder.addLabel("普通标签", downcc.xy(1, 3));
		downbuilder.add(commonLabelListBox, downcc.xyw(3, 3, 3));
		downbuilder.add(addCommonLabelListButton, downcc.xy(7, 3));
		downbuilder.add(editCommonLabelListButton, downcc.xy(9, 3));
		downbuilder.add(deleteCommonLabelListButton, downcc.xy(11, 3));
		downbuilder.add(commonLabelField, downcc.xy(13, 3));

		// 联系人所属分组
		groupListBox = new JComboBox();
		addGroupListButton = new JButton("添加");
		editGroupListButton = new JButton("编辑");
		deleteGroupListButton = new JButton("删除");

		this.myButtonList.add(addGroupListButton);
		this.myButtonList.add(editGroupListButton);
		this.myButtonList.add(deleteGroupListButton);

		downbuilder.addLabel("所属分组", downcc.xy(1, 5));
		downbuilder.add(groupListBox, downcc.xyw(3, 5, 3));
		downbuilder.add(addGroupListButton, downcc.xy(7, 5));
		downbuilder.add(deleteGroupListButton, downcc.xy(9, 5));

		// 关系标签
		relationLabelListBox = new JComboBox();
		objectContactListBox = new JComboBox();
		relationLabelListBox.setPreferredSize(new Dimension(100, 20));
		objectContactListBox.setPreferredSize(new Dimension(100, 20));
		addRelationLabelListButton = new JButton("添加");
		editRelationLabelListButton = new JButton("编辑");
		deleteRelationLabelListButton = new JButton("删除");
		localRelationLabelList = new ArrayList<RelationLabel>();

		this.myButtonList.add(addRelationLabelListButton);
		this.myButtonList.add(editRelationLabelListButton);
		this.myButtonList.add(deleteRelationLabelListButton);

		downbuilder.addLabel("关系标签", downcc.xy(1, 7));
		downbuilder.add(relationLabelListBox, downcc.xy(3, 7));
		downbuilder.add(objectContactListBox, downcc.xy(5, 7));
		downbuilder.add(addRelationLabelListButton, downcc.xy(7, 7));
		downbuilder.add(deleteRelationLabelListButton, downcc.xy(9, 7));

		confirmButton = new JButton("确认");
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO
				// makeMainMessageXML
			}
		});
		cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setMyContact(myContact);
			}
		});
		this.myButtonList.add(confirmButton);
		this.myButtonList.add(cancelButton);
		downbuilder.add(confirmButton, downcc.xy(15, 9));
		downbuilder.add(cancelButton, downcc.xy(17, 9));

		setTextFieldInVisible();

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(builder.getPanel(), "Center");
		mainPanel.add(downbuilder.getPanel(), "South");
	}

	/**
	 * @return
	 */
	public Date getContactBirthday() {
		String birthdayString = this.contactBirthdayField.getText();

		Date birthday;
		try {
			birthday = birthdayDateFormat.parse(birthdayString);
		} catch (ParseException e) {
			birthday = new Date();
		}
		return birthday;
	}

	/**
	 * @param birthday
	 */
	public void setContactBirthday(Date birthday) {
		this.contactBirthdayField.setText(birthdayDateFormat.format(birthday));
	}

	/**
	 * @param pic
	 */
	public void setContactHeadImage(Image pic) {
		if (pic == null)
			return;

		Image tempImage = pic.getScaledInstance(130, 115, Image.SCALE_DEFAULT);
		ImageIcon imageIcon = new ImageIcon(tempImage);
		headImageLabel.setIcon(imageIcon);
	}

	/**
	 * @param url
	 */
	public void setContactHeadImage(String url) {
		if (url == null)
			return;

		try {
			this.setContactHeadImage(ImageIO.read(this.getClass().getResource(
					url)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void deleteContactHeadImage() {
		try {
			this.setContactHeadImage(ImageIO.read(this.getClass().getResource(
					"/icon/Logo Square.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the parenetWindow
	 */
	public Window getParentWindow() {
		if (this.parentFrame != null)
			return this.parentFrame;
		else
			return this.parentDialog;
	}

	/**
	 * @param isEditable
	 *            the isEditable to set
	 */
	public void setEditable(boolean isEditable) {
		if (this.isEditable == isEditable)
			return;

		this.isEditable = isEditable;

		for (JButton p : myButtonList) {
			p.setVisible(isEditable);
		}
	}

	/**
	 * Set all of the text fields invisible
	 */
	public void setTextFieldInVisible() {
		for (JTextField p : myTextFieldList) {
			p.setVisible(false);
		}
	}

	/**
	 * @return the isEditable
	 */
	public boolean isEditable() {
		return isEditable;
	}

	/**
	 * @return the XML format user/contact message
	 */
	public StringBuffer makeMainMessageXML() {
		StringBuffer message = new StringBuffer();
		message.append(MyXMLMaker.addTag("ContactName", nameField.getText()));
		if (myContact != null)
			message.append(MyXMLMaker.addTag("ISN", myContact.getISN()
					.toString()));
		for (int index = 0; index < phoneNumberBox.getItemCount(); index++) {
			message.append(MyXMLMaker.addTag("TelePhone", phoneNumberBox
					.getItemAt(index).toString()));
		}
		for (int index = 0; index < emailAddressBox.getItemCount(); index++) {
			message.append(MyXMLMaker.addTag("Email", emailAddressBox
					.getItemAt(index).toString()));
		}
		for (int index = 0; index < contactAddressBox.getItemCount(); index++) {
			message.append(MyXMLMaker.addTag("Address", contactAddressBox
					.getItemAt(index).toString()));
		}
		for (int index = 0; index < workingDepartmentBox.getItemCount(); index++) {
			message.append(MyXMLMaker.addTag("Office", workingDepartmentBox
					.getItemAt(index).toString()));
		}
		for (int index = 0; index < imContactInformationBox.getItemCount(); index++) {
			message.append(MyXMLMaker.addTag("IMContact",
					imContactInformationBox.getItemAt(index).toString()));
		}
		message.append(MyXMLMaker.addTag("Birthday", contactBirthdayField
				.getText()));
		for (int index = 0; index < urlListBox.getItemCount(); index++) {
			message.append(MyXMLMaker.addTag("URL", urlListBox.getItemAt(index)
					.toString()));
		}
		for (int index = 0; index < commonLabelListBox.getItemCount(); index++) {
			message.append(MyXMLMaker.addTag("CommonLabel", commonLabelListBox
					.getItemAt(index).toString()));
		}
		for (int index = 0; index < groupListBox.getItemCount(); index++) {
			message.append(MyXMLMaker.addTag("Group", groupListBox.getItemAt(
					index).toString()));
		}
		return message;
	}

	/**
	 * @param myContact
	 *            the myContact to set
	 */
	public void setMyContact(ContactOperations myContact) {
		if (myContact == null)
			return;

		ArrayList<String> temp = new ArrayList<String>();
		temp.add("asd");
		temp.add("asdasd");
		temp.add("asdasdasd");
		phoneNumberBox.setModel(new DefaultComboBoxModel((String[]) (temp
				.toArray(new String[0]))));

		// TODO set Contact Information

		if (myContact.getContactName() != null) {
			nameField.setText(myContact.getContactName());
		}

		if (myContact.getPhoneNumberList() != null) {
			phoneNumberBox.setModel(new DefaultComboBoxModel(
					(String[]) (myContact.getPhoneNumberList())
							.toArray(new String[0])));
		}

		if (myContact.getGroupList() != null) {
			groupListBox.setModel(new DefaultComboBoxModel(
					(String[]) (myContact.getGroupList())
							.toArray(new String[0])));
		}

		localRelationLabelList = myContact.getRelationLabelList();
		HashMap<Integer, ContactOperations> std = DataPool.getInstance()
				.getAllContactISNMap();

		if (localRelationLabelList != null) {
			// TODO init relationLabelList / relationLabelNameList
			relationLabelList = new HashMap<String, ArrayList<Integer>>();
			relationLabelNameList = new HashMap<String, ArrayList<String>>();

			for (RelationLabel t : localRelationLabelList) {
				String name = t.getLabelName();
				int iSN = t.getRelationObjectISN();
				if (relationLabelList.containsKey(name)) {
					relationLabelList.get(name).add(iSN);
					relationLabelNameList.get(name).add(
							std.get(iSN).getContactName());
				} else {
					ArrayList<Integer> toBeAddISN = new ArrayList<Integer>();
					toBeAddISN.add(iSN);
					relationLabelList.put(name, toBeAddISN);

					ArrayList<String> toBeAddName = new ArrayList<String>();
					toBeAddName.add(std.get(iSN).getContactName());
					relationLabelNameList.put(name, toBeAddName);
				}
			}

			if (relationLabelList.size() != 0
					&& relationLabelNameList.size() != 0) {
				// TODO init relationLabelListBox / objectContactListBox;
				relationLabelListBox.setModel(new DefaultComboBoxModel(
						(String[]) (relationLabelList.keySet())
								.toArray(new String[0])));

				objectContactListBox.setModel(new DefaultComboBoxModel(
						(String[]) (relationLabelNameList
								.get(relationLabelListBox.getItemAt(0)))
								.toArray(new String[0])));
			}
		}

		this.myContact = myContact;
	}

	/**
	 * @return the myContact
	 */
	public final ContactOperations getMyContact() {
		return myContact;
	}

}
