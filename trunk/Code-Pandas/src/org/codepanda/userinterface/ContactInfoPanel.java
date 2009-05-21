package org.codepanda.userinterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.codepanda.userinterface.listener.*;
import org.codepanda.utility.contact.ContactOperations;

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
	private PhoneMeFrame parentFrame;
	private JDialog parentDialog;
	private ContactOperations myContact;
	private boolean isEditable;

	// All the buttons
	private ArrayList<JButton> myButtonList;

	//
	private JPanel upperPanel;
	private JPanel upperLeftPanel;
	private JPanel mainPanel;

	// 图片部分
	private JLabel headImageLabel;
	private JButton editHeadImageButton;
	private JButton deleteHeadImageButton;

	// 联系人姓名部分
	// JXMultiSplitPane namePanel;
	// JLabel nameLabel;
	private JTextField nameField;
	private JButton nameEditButton;

	// 联系人电话号码部分
	// JXMultiSplitPane phoneNumberPanel;
	// JLabel phoneNumberLabel;
	private JComboBox phoneNumberBox;
	private JButton addPhoneNumberButton;
	private JButton editPhoneNumberButton;
	private JButton deletePhoneNumberButton;
	private PhoneMeField phoneNumberField;

	// 联系人e-mail部分
	// JXMultiSplitPane emailAddressPanel;
	// JLabel emailAddressLabel;
	private JComboBox emailAddressBox;
	private JButton addEmailAddressButton;
	private JButton editEmailAddressButton;
	private JButton deleteEmailAddressButton;

	// 联系人住址部分
	// JXMultiSplitPane contactAddressPanel;
	// JLabel contactAddressLabel;
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

	/**
	 * @param parenetFrame
	 * @param myContact
	 * @param isEditable
	 */
	public ContactInfoPanel(PhoneMeFrame parenetFrame,
			ContactOperations myContact, boolean isEditable) {
		super();
		this.parentFrame = parenetFrame;
		this.parentDialog = null;
		this.isEditable = true;
		this.myContact = myContact;
		this.myButtonList = new ArrayList<JButton>();
		setUpperPanel();
		setLayout(new BorderLayout());
		add(upperPanel, "North");
		setMainPanel();
		add(mainPanel, "Center");

		if (myContact != null) {
			// TODO initialize Display
			this.setMyContact(myContact);
		} else {
			// TODO new Contact
		}

		if (isEditable == false) {
			// TODO initialize Editable
			this.setEditable(isEditable);
		}
	}

	/**
	 * @param parenetDialog
	 * @param myContact
	 * @param isEditable
	 */
	public ContactInfoPanel(JDialog parenetDialog, ContactOperations myContact,
			boolean isEditable) {
		super();
		this.parentFrame = null;
		this.parentDialog = parenetDialog;
		this.myContact = myContact;
		this.isEditable = true;
		this.myButtonList = new ArrayList<JButton>();
		setUpperPanel();
		setLayout(new BorderLayout());
		add(upperPanel, "North");
		setMainPanel();
		add(mainPanel, "Center");

		if (myContact != null) {
			// TODO initialize Display
			this.setMyContact(myContact);
		} else {
			// TODO new Contact
		}

		if (isEditable == false) {
			// TODO initialize Editable
			this.setEditable(isEditable);
		}
	}

	private void setUpperPanel() {
		upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		// upperLeftPanel = new JPanel();
		/*
		 * upperRightPanel = new JXMultiSplitPane();
		 * //upperRightPanel.setLayout(new GridLayout(2,1)); String
		 * upperRightlayoutDef =
		 * "(COLUMN (LEAF name=up weight=0.5) (LEAF name=down weight=0.5))";
		 * MultiSplitLayout.Node upperRightmodelRoot = MultiSplitLayout.
		 * parseModel( upperRightlayoutDef );
		 * upperRightPanel.getMultiSplitLayout().setModel(upperRightmodelRoot);
		 * 
		 * //no use JButton temp = new JButton("姓名"); temp.setVisible(false);
		 * 
		 * //MultiSplitPane String layoutDef =
		 * "(ROW (LEAF name=label weight=0.35) (LEAF name=field weight=0.5) " +
		 * "(LEAF name=leftButton weight=0.05) " +
		 * "(LEAF name=middleButton weight=0.05)) " +
		 * "(LEAF name=rightButton weight=0.05))"; MultiSplitLayout.Node
		 * modelRoot = MultiSplitLayout. parseModel( layoutDef );
		 * 
		 * //添加联系人姓名部分相关组件 namePanel = new JXMultiSplitPane();
		 * namePanel.getMultiSplitLayout().setModel(modelRoot);
		 * 
		 * nameLabel = new JLabel("姓名/Name");
		 * //nameLabel.setHorizontalAlignment(SwingConstants.RIGHT); nameField =
		 * new JTextField(); nameField.setText(""); nameEditButton = new
		 * JButton("编辑");
		 * 
		 * namePanel.add(nameLabel, "label"); namePanel.add(nameField, "field");
		 * namePanel.add(nameEditButton, "leftButton"); namePanel.add(temp,
		 * "middleButton"); namePanel.add(temp, "rightButton");
		 * 
		 * //添加联系人电话部分相关组件 phoneNumberPanel = new JXMultiSplitPane();
		 * phoneNumberPanel.getMultiSplitLayout().setModel(modelRoot);
		 * 
		 * phoneNumberLabel = new JLabel("联系电话");
		 * //phoneNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		 * phoneNumberBox = new JComboBox(); addPhoneNumberButton = new
		 * JButton("添加"); editPhoneNumberButton = new JButton("编辑");
		 * deletePhoneNumberButton = new JButton("删除");
		 * 
		 * phoneNumberPanel.add(phoneNumberLabel, "label");
		 * phoneNumberPanel.add(phoneNumberBox, "field");
		 * phoneNumberPanel.add(addPhoneNumberButton, "middleButton");
		 * phoneNumberPanel.add(editPhoneNumberButton, "leftButton");
		 * phoneNumberPanel.add(deletePhoneNumberButton, "rightButton");
		 * 
		 * upperRightPanel.add(namePanel, "up");
		 * upperRightPanel.add(phoneNumberPanel, "down");
		 */

		nameField = new JTextField(15);
		// nameField.setText("请输入联系人的姓名");
		nameEditButton = new JButton("编辑");
		this.myButtonList.add(nameEditButton);

		// phoneNumberLabel = new JLabel("联系电话");
		String temp[] = { "010-51534419", "13810013188", "029-85367800" };
		phoneNumberBox = new JComboBox(temp);
		addPhoneNumberButton = new JButton("添加");
		editPhoneNumberButton = new JButton("编辑");
		deletePhoneNumberButton = new JButton("删除");
		phoneNumberField = new PhoneMeField(20);
		phoneNumberField.addActionListener(new FieldActionListener(
				phoneNumberBox, phoneNumberField));
		phoneNumberField.setVisible(false);

		ActionListener phoneNumberListener = new ComboBoxButtonListener(
				phoneNumberBox, phoneNumberField);
		// addPhoneNumberButton.addActionListener(phoneNumberListener);
		// editPhoneNumberButton.addActionListener(phoneNumberListener);
		deletePhoneNumberButton.addActionListener(phoneNumberListener);
		this.myButtonList.add(addPhoneNumberButton);
		this.myButtonList.add(editPhoneNumberButton);
		this.myButtonList.add(deletePhoneNumberButton);

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
		addPhoneNumberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				phoneNumberField.setVisible(true);
				phoneNumberField.setState(0);
				paintComponents(getGraphics());
			}
		});
		editPhoneNumberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				phoneNumberField.setVisible(true);
				phoneNumberField.setState(1);
				paintComponents(getGraphics());
			}
		});
		builder.add(editPhoneNumberButton, cc.xy(7, 5));
		builder.add(deletePhoneNumberButton, cc.xy(9, 5));
		builder.add(phoneNumberField, cc.xy(11, 5));

		// upperLeftPanel.add(new JLabel("test1"));
		FormLayout upperLeftlayout = new FormLayout("pref, 9dlu, pref", // columns
				"p, 7dlu, p"); // rows
		upperLeftlayout.setColumnGroups(new int[][] { { 1, 3 } });

		PanelBuilder leftBuilder = new PanelBuilder(upperLeftlayout);
		leftBuilder.setDefaultDialogBorder();

		CellConstraints leftcc = new CellConstraints();

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
				// TODO Auto-generated method stub
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
		// "pref, 3dlu, pref, 2dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref",//column
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
		builder.addLabel("                      ", cc.xy(11, 1));
		// e-mail
		emailAddressBox = new JComboBox(temp);
		// emailAddressBox.setPreferredSize(new Dimension(200,20));
		emailAddressBox.setEditable(true);
		addEmailAddressButton = new JButton("添加");
		editEmailAddressButton = new JButton("编辑");
		deleteEmailAddressButton = new JButton("删除");
		this.myButtonList.add(addEmailAddressButton);
		this.myButtonList.add(editEmailAddressButton);
		this.myButtonList.add(deleteEmailAddressButton);

		builder.addLabel("电子邮箱", cc.xy(1, 3));
		// builder.add(emailAddressBox, cc.xyw(3, 1, 3));
		builder.add(emailAddressBox, cc.xy(3, 3));
		builder.add(addEmailAddressButton, cc.xy(5, 3));
		builder.add(editEmailAddressButton, cc.xy(7, 3));
		builder.add(deleteEmailAddressButton, cc.xy(9, 3));
		// builder.add(addEmailAddressButton, cc.xy(7, 1));
		// builder.add(editEmailAddressButton, cc.xy(9, 1));
		// builder.add(deleteEmailAddressButton, cc.xy(11, 1));

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
				// TODO Auto-generated method stub
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
				"pref, 3dlu, pref, 2dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref",// column
				"p, 8dlu, p, 5dlu, p, 5dlu, p, 5dlu, p");// row

		mainSouthAreaLayout.setColumnGroups(new int[][] { { 7, 9, 11 } });

		PanelBuilder downbuilder = new PanelBuilder(mainSouthAreaLayout);
		downbuilder.setDefaultDialogBorder();

		CellConstraints downcc = new CellConstraints();

		downbuilder.addSeparator("标签及分组信息", downcc.xyw(1, 1, 11));

		// 联系人普通标签
		commonLabelListBox = new JComboBox();
		addCommonLabelListButton = new JButton("添加");
		editCommonLabelListButton = new JButton("编辑");
		deleteCommonLabelListButton = new JButton("删除");

		this.myButtonList.add(addCommonLabelListButton);
		this.myButtonList.add(editCommonLabelListButton);
		this.myButtonList.add(deleteCommonLabelListButton);

		downbuilder.addLabel("普通标签", downcc.xy(1, 3));
		downbuilder.add(commonLabelListBox, downcc.xyw(3, 3, 3));
		downbuilder.add(addCommonLabelListButton, downcc.xy(7, 3));
		downbuilder.add(editCommonLabelListButton, downcc.xy(9, 3));
		downbuilder.add(deleteCommonLabelListButton, downcc.xy(11, 3));

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
		// downbuilder.add(editGroupListButton, downcc.xy(9, 5));
		downbuilder.add(deleteGroupListButton, downcc.xy(9, 5));

		// 关系标签
		relationLabelListBox = new JComboBox();
		objectContactListBox = new JComboBox();
		relationLabelListBox.setPreferredSize(new Dimension(100, 20));
		objectContactListBox.setPreferredSize(new Dimension(100, 20));
		addRelationLabelListButton = new JButton("添加");
		editRelationLabelListButton = new JButton("编辑");
		deleteRelationLabelListButton = new JButton("删除");

		this.myButtonList.add(addRelationLabelListButton);
		this.myButtonList.add(editRelationLabelListButton);
		this.myButtonList.add(deleteRelationLabelListButton);

		downbuilder.addLabel("关系标签", downcc.xy(1, 7));
		downbuilder.add(relationLabelListBox, downcc.xy(3, 7));
		downbuilder.add(objectContactListBox, downcc.xy(5, 7));
		downbuilder.add(addRelationLabelListButton, downcc.xy(7, 7));
		// downbuilder.add(editRelationLabelListButton, downcc.xy(9, 7));
		downbuilder.add(deleteRelationLabelListButton, downcc.xy(9, 7));

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
		// TODO setContactBirthday
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
	 * @return the isEditable
	 */
	public boolean isEditable() {
		return isEditable;
	}

	/**
	 * @param myContact
	 *            the myContact to set
	 */
	public void setMyContact(ContactOperations myContact) {
		if (this.myContact == myContact)
			return;

		this.myContact = myContact;
	}

	/**
	 * @return the myContact
	 */
	public ContactOperations getMyContact() {
		return myContact;
	}

}
