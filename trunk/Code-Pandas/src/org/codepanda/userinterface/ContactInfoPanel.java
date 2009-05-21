package org.codepanda.userinterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.codepanda.userinterface.listener.ContactHeadImageEditListener;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXMultiSplitPane;
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
	private JPanel upperPanel;
	private JPanel upperLeftPanel;
	private JXMultiSplitPane upperRightPanel;
	private JPanel mainPanel;

	// ͼƬ����
	private JLabel headImageLabel;
	private JButton editHeadImageButton;
	private JButton deleteHeadImageButton;

	// ��ϵ����������
	// JXMultiSplitPane namePanel;
	// JLabel nameLabel;
	private JTextField nameField;
	private JButton nameEditButton;

	// ��ϵ�˵绰���벿��
	// JXMultiSplitPane phoneNumberPanel;
	// JLabel phoneNumberLabel;
	private JComboBox phoneNumberBox;
	private JButton addPhoneNumberButton;
	private JButton editPhoneNumberButton;
	private JButton deletePhoneNumberButton;

	// ��ϵ��e-mail����
	// JXMultiSplitPane emailAddressPanel;
	// JLabel emailAddressLabel;
	private JComboBox emailAddressBox;
	private JButton addEmailAddressButton;
	private JButton editEmailAddressButton;
	private JButton deleteEmailAddressButton;

	// ��ϵ��סַ����
	// JXMultiSplitPane contactAddressPanel;
	// JLabel contactAddressLabel;
	private JComboBox contactAddressBox;
	private JButton addContactAddressButton;
	private JButton editContactAddressButton;
	private JButton deleteContactAddressButton;

	// ��ϵ�˹�����ѧϰ��λ
	private JComboBox workingDepartmentBox;
	private JButton addWorkingDepartmentButton;
	private JButton editWorkingDepartmentButton;
	private JButton deleteWorkingDepartmentButton;

	// ��ϵ�˼�ʱ��ϵ��ʽ����
	private JComboBox imContactInformationBox;
	private JButton addImContactInformationButton;
	private JButton editImContactInformationButton;
	private JButton deleteImContactInformationButton;

	// ��ϵ�����ղ���
	private JTextField contactBirthdayField;
	private JButton editcontactBirthdayButton;
	private JXDatePicker contactBirthdayPicker;
	private static SimpleDateFormat birthdayDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	// ��ϵ��Web��ַ
	private JComboBox urlListBox;
	private JButton addUrlListButton;
	private JButton editUrlListButton;
	private JButton deleteUrlListButton;

	// ��ϵ����ͨ��ǩ
	private JComboBox commonLabelListBox;
	private JButton addCommonLabelListButton;
	private JButton editCommonLabelListButton;
	private JButton deleteCommonLabelListButton;

	// ��ϵ����������
	private JComboBox groupListBox;
	private JButton addGroupListButton;
	private JButton editGroupListButton;
	private JButton deleteGroupListButton;

	// ��ϵ�˹�ϵ��ǩ
	private JComboBox relationLabelListBox;
	private JComboBox objectContactListBox;
	private JButton addRelationLabelListButton;
	private JButton editRelationLabelListButton;
	private JButton deleteRelationLabelListButton;

	public ContactInfoPanel(PhoneMeFrame parenetFrame) {
		super();
		this.parentFrame = parenetFrame;
		this.parentDialog = null;
		setUpperPanel();
		setLayout(new BorderLayout());
		add(upperPanel, "North");
		setMainPanel();
		add(mainPanel, "Center");
	}

	public ContactInfoPanel(JDialog parenetDialog) {
		super();
		this.parentFrame = null;
		this.parentDialog = parenetDialog;
		setUpperPanel();
		setLayout(new BorderLayout());
		add(upperPanel, "North");
		setMainPanel();
		add(mainPanel, "Center");
	}

	/*
	 * public static void main(String args[]){ JXPanel_test test = new
	 * JXPanel_test(); test.setVisible(true); }
	 */

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
		 * //no use JButton temp = new JButton("����"); temp.setVisible(false);
		 * 
		 * //MultiSplitPane String layoutDef =
		 * "(ROW (LEAF name=label weight=0.35) (LEAF name=field weight=0.5) " +
		 * "(LEAF name=leftButton weight=0.05) " +
		 * "(LEAF name=middleButton weight=0.05)) " +
		 * "(LEAF name=rightButton weight=0.05))"; MultiSplitLayout.Node
		 * modelRoot = MultiSplitLayout. parseModel( layoutDef );
		 * 
		 * //�����ϵ���������������� namePanel = new JXMultiSplitPane();
		 * namePanel.getMultiSplitLayout().setModel(modelRoot);
		 * 
		 * nameLabel = new JLabel("����/Name");
		 * //nameLabel.setHorizontalAlignment(SwingConstants.RIGHT); nameField =
		 * new JTextField(); nameField.setText(""); nameEditButton = new
		 * JButton("�༭");
		 * 
		 * namePanel.add(nameLabel, "label"); namePanel.add(nameField, "field");
		 * namePanel.add(nameEditButton, "leftButton"); namePanel.add(temp,
		 * "middleButton"); namePanel.add(temp, "rightButton");
		 * 
		 * //�����ϵ�˵绰���������� phoneNumberPanel = new JXMultiSplitPane();
		 * phoneNumberPanel.getMultiSplitLayout().setModel(modelRoot);
		 * 
		 * phoneNumberLabel = new JLabel("��ϵ�绰");
		 * //phoneNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		 * phoneNumberBox = new JComboBox(); addPhoneNumberButton = new
		 * JButton("���"); editPhoneNumberButton = new JButton("�༭");
		 * deletePhoneNumberButton = new JButton("ɾ��");
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

		nameField = new JTextField(30);
		// nameField.setText("��������ϵ�˵�����");
		nameEditButton = new JButton("�༭");

		// phoneNumberLabel = new JLabel("��ϵ�绰");
		String temp[] = { "010-51534419", "13810013188", "029-85367800" };
		phoneNumberBox = new JComboBox(temp);
		addPhoneNumberButton = new JButton("���");
		editPhoneNumberButton = new JButton("�༭");
		deletePhoneNumberButton = new JButton("ɾ��");

		FormLayout upperRightlayout = new FormLayout(
				"pref, 3dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref", // columns
				"p, 8dlu, p, 5dlu, p"); // rows
		upperRightlayout.setColumnGroups(new int[][] { { 5, 7, 9 } });

		PanelBuilder builder = new PanelBuilder(upperRightlayout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

		builder.addSeparator("������Ϣ", cc.xyw(1, 1, 9));
		builder.addLabel("����/Name", cc.xy(1, 3));
		builder.add(nameField, cc.xy(3, 3));
		builder.add(nameEditButton, cc.xy(5, 3));

		builder.addLabel("��ϵ�绰/Phone Number", cc.xy(1, 5));
		builder.add(phoneNumberBox, cc.xy(3, 5));
		builder.add(addPhoneNumberButton, cc.xy(5, 5));
		builder.add(editPhoneNumberButton, cc.xy(7, 5));
		builder.add(deletePhoneNumberButton, cc.xy(9, 5));

		// upperLeftPanel.add(new JLabel("test1"));
		FormLayout upperLeftlayout = new FormLayout("pref, 9dlu, pref", // columns
				"p, 7dlu, p"); // rows
		upperLeftlayout.setColumnGroups(new int[][] { { 1, 3 } });

		PanelBuilder leftBuilder = new PanelBuilder(upperLeftlayout);
		leftBuilder.setDefaultDialogBorder();

		CellConstraints leftcc = new CellConstraints();

		headImageLabel = new JLabel();// ��ϵ��ͼƬ
		editHeadImageButton = new JButton("�༭");
		deleteHeadImageButton = new JButton("ɾ��");

		editHeadImageButton.addActionListener(new ContactHeadImageEditListener(
				this));
		deleteHeadImageButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteContactHeadImage();
			}
			
		});

		editHeadImageButton.setPreferredSize(new Dimension(50, 20));
		deleteHeadImageButton.setPreferredSize(new Dimension(50, 20));

		this.setContactHeadImage("/userpic/user1.jpg");

		leftBuilder.add(headImageLabel, leftcc.xyw(1, 1, 3));
		leftBuilder.add(editHeadImageButton, leftcc.xy(1, 3));
		leftBuilder.add(deleteHeadImageButton, leftcc.xy(3, 3));

		upperPanel.add(leftBuilder.getPanel(), "West");
		upperPanel.add(builder.getPanel(), "Center");
	}

	private void setMainPanel() {
		mainPanel = new JPanel();
		FormLayout mainAreaLayout = new FormLayout(
		// "pref, 3dlu, pref, 2dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref",//column
				"pref, 3dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref",// column
				"p, 8dlu, p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p,"
						+ " 5dlu, p, 5dlu, p");// row
		// mainAreaLayout.setColumnGroups(new int[][]{{7, 9, 11}});
		mainAreaLayout.setColumnGroups(new int[][] { { 5, 7, 9 } });

		PanelBuilder builder = new PanelBuilder(mainAreaLayout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

		String temp[] = { "�л����񹲺͹������к������廪��ѧ�Ͼ���Ԣ1#419B" };

		builder.addSeparator("������Ϣ", cc.xyw(1, 1, 9));
		// e-mail
		emailAddressBox = new JComboBox(temp);
		// emailAddressBox.setPreferredSize(new Dimension(200,20));
		emailAddressBox.setEditable(true);
		addEmailAddressButton = new JButton("���");
		editEmailAddressButton = new JButton("�༭");
		deleteEmailAddressButton = new JButton("ɾ��");

		builder.addLabel("��������/E-mail", cc.xy(1, 3));
		// builder.add(emailAddressBox, cc.xyw(3, 1, 3));
		builder.add(emailAddressBox, cc.xy(3, 3));
		builder.add(addEmailAddressButton, cc.xy(5, 3));
		builder.add(editEmailAddressButton, cc.xy(7, 3));
		builder.add(deleteEmailAddressButton, cc.xy(9, 3));
		// builder.add(addEmailAddressButton, cc.xy(7, 1));
		// builder.add(editEmailAddressButton, cc.xy(9, 1));
		// builder.add(deleteEmailAddressButton, cc.xy(11, 1));

		// ��ϵ��סַ
		contactAddressBox = new JComboBox();
		addContactAddressButton = new JButton("���");
		editContactAddressButton = new JButton("�༭");
		deleteContactAddressButton = new JButton("ɾ��");

		builder.addLabel("��ͥסַ/Address", cc.xy(1, 5));
		builder.add(contactAddressBox, cc.xy(3, 5));
		builder.add(addContactAddressButton, cc.xy(5, 5));
		builder.add(editContactAddressButton, cc.xy(7, 5));
		builder.add(deleteContactAddressButton, cc.xy(9, 5));

		// ��ϵ�˹�����ѧϰ��λ
		workingDepartmentBox = new JComboBox();
		addWorkingDepartmentButton = new JButton("���");
		editWorkingDepartmentButton = new JButton("�༭");
		deleteWorkingDepartmentButton = new JButton("ɾ��");

		builder.addLabel("������λ/Working Department", cc.xy(1, 7));
		builder.add(workingDepartmentBox, cc.xy(3, 7));
		builder.add(addWorkingDepartmentButton, cc.xy(5, 7));
		builder.add(editWorkingDepartmentButton, cc.xy(7, 7));
		builder.add(deleteWorkingDepartmentButton, cc.xy(9, 7));

		// ��ϵ�˼�ʱ��ϵ��ʽ
		imContactInformationBox = new JComboBox();
		addImContactInformationButton = new JButton("���");
		editImContactInformationButton = new JButton("�༭");
		deleteImContactInformationButton = new JButton("ɾ��");

		builder.addLabel("��ʱ��ϵ��ʽ/Im Contact", cc.xy(1, 9));
		builder.add(imContactInformationBox, cc.xy(3, 9));
		builder.add(addImContactInformationButton, cc.xy(5, 9));
		builder.add(editImContactInformationButton, cc.xy(7, 9));
		builder.add(deleteImContactInformationButton, cc.xy(9, 9));

		// ��ϵ�����ղ���
		contactBirthdayField = new JTextField(30);
		contactBirthdayPicker = new JXDatePicker(this.getContactBirthday());
		contactBirthdayPicker.setFormats(birthdayDateFormat);
		contactBirthdayPicker.setVisible(false);
		contactBirthdayPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContactBirthday(contactBirthdayPicker.getDate());
			}
		});
		editcontactBirthdayButton = new JButton("�༭");
		editcontactBirthdayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				contactBirthdayPicker.setVisible(!contactBirthdayPicker
						.isVisible());
			}
		});

		builder.addLabel("��ϵ������/Birthday", cc.xy(1, 11));
		builder.add(contactBirthdayField, cc.xy(3, 11));
		builder.add(editcontactBirthdayButton, cc.xy(5, 11));
		builder.add(contactBirthdayPicker, cc.xy(7, 11));

		// ��ϵ��Web��ַ
		urlListBox = new JComboBox();
		addUrlListButton = new JButton("���");
		editUrlListButton = new JButton("�༭");
		deleteUrlListButton = new JButton("ɾ��");

		builder.addLabel("��ϵ��Web��ַ/URL Address", cc.xy(1, 13));
		builder.add(urlListBox, cc.xy(3, 13));
		builder.add(addUrlListButton, cc.xy(5, 13));
		builder.add(editUrlListButton, cc.xy(7, 13));
		builder.add(deleteUrlListButton, cc.xy(9, 13));

		// ��ǩ�Լ��������ʾ����
		FormLayout mainSouthAreaLayout = new FormLayout(
				"pref, 3dlu, pref, 2dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref",// column
				"p, 8dlu, p, 5dlu, p, 5dlu, p, 5dlu, p");// row

		mainSouthAreaLayout.setColumnGroups(new int[][] { { 7, 9, 11 } });

		PanelBuilder downbuilder = new PanelBuilder(mainSouthAreaLayout);
		downbuilder.setDefaultDialogBorder();

		CellConstraints downcc = new CellConstraints();

		downbuilder.addSeparator("��ǩ��������Ϣ", downcc.xyw(1, 1, 11));

		// ��ϵ����ͨ��ǩ
		commonLabelListBox = new JComboBox();
		addCommonLabelListButton = new JButton("���");
		editCommonLabelListButton = new JButton("�༭");
		deleteCommonLabelListButton = new JButton("ɾ��");

		downbuilder.addLabel("��ͨ��ǩ/Common Label", downcc.xy(1, 3));
		downbuilder.add(commonLabelListBox, downcc.xyw(3, 3, 3));
		downbuilder.add(addCommonLabelListButton, downcc.xy(7, 3));
		downbuilder.add(editCommonLabelListButton, downcc.xy(9, 3));
		downbuilder.add(deleteCommonLabelListButton, downcc.xy(11, 3));

		// ��ϵ����������
		groupListBox = new JComboBox();
		addGroupListButton = new JButton("���");
		editGroupListButton = new JButton("�༭");
		deleteGroupListButton = new JButton("ɾ��");

		downbuilder.addLabel("��������/Group", downcc.xy(1, 5));
		downbuilder.add(groupListBox, downcc.xyw(3, 5, 3));
		downbuilder.add(addGroupListButton, downcc.xy(7, 5));
		// downbuilder.add(editGroupListButton, downcc.xy(9, 5));
		downbuilder.add(deleteGroupListButton, downcc.xy(9, 5));

		// ��ϵ��ǩ
		relationLabelListBox = new JComboBox();
		objectContactListBox = new JComboBox();
		relationLabelListBox.setPreferredSize(new Dimension(100, 20));
		objectContactListBox.setPreferredSize(new Dimension(100, 20));
		addRelationLabelListButton = new JButton("���");
		editRelationLabelListButton = new JButton("�༭");
		deleteRelationLabelListButton = new JButton("ɾ��");

		downbuilder.addLabel("��ϵ��ǩ/Relation Label", downcc.xy(1, 7));
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

		Image tempImage = pic.getScaledInstance(115, 115, Image.SCALE_DEFAULT);
		ImageIcon imageIcon = new ImageIcon(tempImage);
		headImageLabel.setIcon(imageIcon);
	}

	/**
	 * @param url
	 */
	public void setContactHeadImage(String url) {
		if(url == null)
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
	public Window getParenetWindow() {
		if (this.parentFrame != null)
			return this.parentFrame;
		else
			return this.parentDialog;
	}
}
