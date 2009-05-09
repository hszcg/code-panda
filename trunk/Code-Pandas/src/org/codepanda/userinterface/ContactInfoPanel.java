package org.codepanda.userinterface;

import java.awt.*;
import javax.swing.*;
import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.JXPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;

public class ContactInfoPanel extends JXPanel{
	JPanel upperPanel;
	JPanel upperLeftPanel;
	JXMultiSplitPane upperRightPanel;
	JPanel mainPanel;
	
	//图片部分
	JLabel headImageLabel;
	JButton editHeadImageButton;
	JButton deleteHeadImageButton;
	
	//联系人姓名部分
	//JXMultiSplitPane namePanel;
	//JLabel nameLabel;
	JTextField nameField;
	JButton nameEditButton;
	
	//联系人电话号码部分
	//JXMultiSplitPane phoneNumberPanel;
	//JLabel phoneNumberLabel;
	JComboBox phoneNumberBox;
	JButton addPhoneNumberButton;
	JButton editPhoneNumberButton;
	JButton deletePhoneNumberButton;
	
	//联系人e-mail部分
	//JXMultiSplitPane emailAddressPanel;
	//JLabel emailAddressLabel;
	JComboBox emailAddressBox;
	JButton addEmailAddressButton;
	JButton editEmailAddressButton;
	JButton deleteEmailAddressButton;
	
	//联系人住址部分
	//JXMultiSplitPane contactAddressPanel;
	//JLabel contactAddressLabel;
	JComboBox contactAddressBox;
	JButton addContactAddressButton;
	JButton editContactAddressButton;
	JButton deleteContactAddressButton;
	
	//联系人工作／学习单位
	JComboBox workingDepartmentBox;
	JButton addWorkingDepartmentButton;
	JButton editWorkingDepartmentButton;
	JButton deleteWorkingDepartmentButton;
	
	//联系人即时联系方式部分
	JComboBox imContactInformationBox;
	JButton addImContactInformationButton;
	JButton editImContactInformationButton;
	JButton deleteImContactInformationButton;
	
	//联系人生日部分
	JTextField contactBirthdayField;
	JButton editcontactBirthdayButton;
	
	//联系人Web地址
	JComboBox urlListBox;
	JButton addUrlListButton;
	JButton editUrlListButton;
	JButton deleteUrlListButton;
	
	//联系人普通标签
	JComboBox commonLabelListBox;
	JButton addCommonLabelListButton;
	JButton editCommonLabelListButton;
	JButton deleteCommonLabelListButton;
	
	//联系人所属分组
	JComboBox groupListBox;
	JButton addGroupListButton;
	JButton editGroupListButton;
	JButton deleteGroupListButton;
	
	//联系人关系标签
	JComboBox relationLabelListBox;
	JComboBox objectContactListBox;
	JButton addRelationLabelListButton;
	JButton editRelationLabelListButton;
	JButton deleteRelationLabelListButton;
	
	public ContactInfoPanel(){
		super();
		setUpperPanel();
		setLayout(new BorderLayout());
		add(upperPanel, "North");
		setMainPanel();
		add(mainPanel, "Center");
	}
	/*public static void main(String args[]){
		JXPanel_test test = new JXPanel_test();
		test.setVisible(true);
	}*/
	
	public void setUpperPanel(){
		upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		//upperLeftPanel = new JPanel();
		/*upperRightPanel = new JXMultiSplitPane();
		//upperRightPanel.setLayout(new GridLayout(2,1));
		String upperRightlayoutDef =
			"(COLUMN (LEAF name=up weight=0.5) (LEAF name=down weight=0.5))";
		MultiSplitLayout.Node upperRightmodelRoot = MultiSplitLayout.
		parseModel( upperRightlayoutDef );
		upperRightPanel.getMultiSplitLayout().setModel(upperRightmodelRoot);
		
		//no use
		JButton temp = new JButton("姓名");
		temp.setVisible(false);
		
		//MultiSplitPane
		String layoutDef =
		"(ROW (LEAF name=label weight=0.35) (LEAF name=field weight=0.5) " +
		"(LEAF name=leftButton weight=0.05) " +
		"(LEAF name=middleButton weight=0.05)) " +
		"(LEAF name=rightButton weight=0.05))";
		MultiSplitLayout.Node modelRoot = MultiSplitLayout.
			parseModel( layoutDef );
		
		//添加联系人姓名部分相关组件
		namePanel = new JXMultiSplitPane();
		namePanel.getMultiSplitLayout().setModel(modelRoot);
		
		nameLabel = new JLabel("姓名/Name");
		//nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameField = new JTextField();
		nameField.setText("");
		nameEditButton = new JButton("编辑");
		
		namePanel.add(nameLabel, "label");
		namePanel.add(nameField, "field");
		namePanel.add(nameEditButton, "leftButton");
		namePanel.add(temp, "middleButton");
		namePanel.add(temp, "rightButton");
		
		//添加联系人电话部分相关组件
		phoneNumberPanel = new JXMultiSplitPane();
		phoneNumberPanel.getMultiSplitLayout().setModel(modelRoot);
		
		phoneNumberLabel = new JLabel("联系电话");
		//phoneNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneNumberBox = new JComboBox();
		addPhoneNumberButton = new JButton("添加");
		editPhoneNumberButton = new JButton("编辑");
		deletePhoneNumberButton = new JButton("删除");
		
		phoneNumberPanel.add(phoneNumberLabel, "label");
		phoneNumberPanel.add(phoneNumberBox, "field");
		phoneNumberPanel.add(addPhoneNumberButton, "middleButton");
		phoneNumberPanel.add(editPhoneNumberButton, "leftButton");
		phoneNumberPanel.add(deletePhoneNumberButton, "rightButton");
		
		upperRightPanel.add(namePanel, "up");
		upperRightPanel.add(phoneNumberPanel, "down");*/
		
		nameField = new JTextField(30);
		//nameField.setText("请输入联系人的姓名");
		nameEditButton = new JButton("编辑");
		
		//phoneNumberLabel = new JLabel("联系电话");
		String temp[] = {"010-51534419", "13810013188", "029-85367800"};
		phoneNumberBox = new JComboBox(temp);
		addPhoneNumberButton = new JButton("添加");
		editPhoneNumberButton = new JButton("编辑");
		deletePhoneNumberButton = new JButton("删除");
		
		FormLayout upperRightlayout = new FormLayout(
	    "pref, 3dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref", // columns
	    "p, 8dlu, p, 5dlu, p");      // rows
		upperRightlayout.setColumnGroups(new int[][]{{5, 7, 9}});
		
		PanelBuilder builder = new PanelBuilder(upperRightlayout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		builder.addSeparator("基本信息", cc.xyw(1, 1, 9));
		builder.addLabel("姓名/Name", cc.xy(1, 3));
		builder.add(nameField, cc.xy(3, 3));
		builder.add(nameEditButton, cc.xy(5, 3));
		
		builder.addLabel("联系电话/Phone Number", cc.xy(1, 5));
		builder.add(phoneNumberBox, cc.xy(3, 5));
		builder.add(addPhoneNumberButton, cc.xy(5, 5));
		builder.add(editPhoneNumberButton, cc.xy(7, 5));
		builder.add(deletePhoneNumberButton, cc.xy(9, 5));
		
		//upperLeftPanel.add(new JLabel("test1"));
		FormLayout upperLeftlayout = new FormLayout(
		"pref, 9dlu, pref", // columns
		"p, 7dlu, p");      // rows
		upperLeftlayout.setColumnGroups(new int[][]{{1, 3}});
				
		PanelBuilder leftBuilder = new PanelBuilder(upperLeftlayout);
		leftBuilder.setDefaultDialogBorder();
		
		CellConstraints leftcc = new CellConstraints();
		
		headImageLabel = new JLabel();//联系人图片
		editHeadImageButton = new JButton("编辑");
		deleteHeadImageButton = new JButton("删除");
		
		editHeadImageButton.setPreferredSize(new Dimension(50, 20));
		deleteHeadImageButton.setPreferredSize(new Dimension(50, 20));
		
		ImageIcon imageIconSource = new ImageIcon("./resource/userpic/user1.jpg");
		Image image = imageIconSource.getImage();
		Image tempImage = image.getScaledInstance(115, 115, Image.SCALE_DEFAULT);
		ImageIcon imageIcon = new ImageIcon(tempImage);
		headImageLabel.setIcon(imageIcon);
		
		leftBuilder.add(headImageLabel, leftcc.xyw(1, 1, 3));
		leftBuilder.add(editHeadImageButton, leftcc.xy(1, 3));
		leftBuilder.add(deleteHeadImageButton, leftcc.xy(3, 3));
		
		upperPanel.add(leftBuilder.getPanel(), "West");
		upperPanel.add(builder.getPanel(), "Center");
	}
	public void setMainPanel(){
		mainPanel = new JPanel();
		FormLayout mainAreaLayout = new FormLayout(
	//"pref, 3dlu, pref, 2dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref",//column
	"pref, 3dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref",//column
	"p, 8dlu, p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p," +
	" 5dlu, p, 5dlu, p");//row
		//mainAreaLayout.setColumnGroups(new int[][]{{7, 9, 11}});
		mainAreaLayout.setColumnGroups(new int[][]{{5, 7, 9}});
		
		PanelBuilder builder = new PanelBuilder(mainAreaLayout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		String temp[] = {
				"中华人民共和国北京市海淀区清华大学紫荆公寓1#419B"};
		
		builder.addSeparator("个人信息", cc.xyw(1, 1, 9));
		//e-mail
		emailAddressBox = new JComboBox(temp);
		//emailAddressBox.setPreferredSize(new   Dimension(200,20));
		emailAddressBox.setEditable(true);
		addEmailAddressButton = new JButton("添加");
		editEmailAddressButton = new JButton("编辑");
		deleteEmailAddressButton = new JButton("删除");
		
		builder.addLabel("电子邮箱/E-mail", cc.xy(1, 3));
		//builder.add(emailAddressBox, cc.xyw(3, 1, 3));
		builder.add(emailAddressBox, cc.xy(3, 3));
		builder.add(addEmailAddressButton, cc.xy(5, 3));
		builder.add(editEmailAddressButton, cc.xy(7, 3));
		builder.add(deleteEmailAddressButton, cc.xy(9, 3));
		//builder.add(addEmailAddressButton, cc.xy(7, 1));
		//builder.add(editEmailAddressButton, cc.xy(9, 1));
		//builder.add(deleteEmailAddressButton, cc.xy(11, 1));
		
		//联系人住址
		contactAddressBox = new JComboBox();
		addContactAddressButton = new JButton("添加");
		editContactAddressButton = new JButton("编辑");
		deleteContactAddressButton = new JButton("删除");
		
		builder.addLabel("家庭住址/Address", cc.xy(1, 5));
		builder.add(contactAddressBox, cc.xy(3, 5));
		builder.add(addContactAddressButton, cc.xy(5, 5));
		builder.add(editContactAddressButton, cc.xy(7, 5));
		builder.add(deleteContactAddressButton, cc.xy(9, 5));
		
		//联系人工作／学习单位
		workingDepartmentBox = new JComboBox();
		addWorkingDepartmentButton = new JButton("添加");
		editWorkingDepartmentButton = new JButton("编辑");
		deleteWorkingDepartmentButton = new JButton("删除");
		
		builder.addLabel("工作单位/Working Department", cc.xy(1, 7));
		builder.add(workingDepartmentBox, cc.xy(3, 7));
		builder.add(addWorkingDepartmentButton, cc.xy(5, 7));
		builder.add(editWorkingDepartmentButton, cc.xy(7, 7));
		builder.add(deleteWorkingDepartmentButton, cc.xy(9, 7));
		
		//联系人即时联系方式
		imContactInformationBox = new JComboBox();
		addImContactInformationButton = new JButton("添加");
		editImContactInformationButton = new JButton("编辑");
		deleteImContactInformationButton = new JButton("删除");
		
		builder.addLabel("即时联系方式/Im Contact", cc.xy(1, 9));
		builder.add(imContactInformationBox, cc.xy(3, 9));
		builder.add(addImContactInformationButton, cc.xy(5, 9));
		builder.add(editImContactInformationButton, cc.xy(7, 9));
		builder.add(deleteImContactInformationButton, cc.xy(9, 9));
		
		//联系人生日部分
		contactBirthdayField = new JTextField(30);
		editcontactBirthdayButton = new JButton("编辑");
		
		builder.addLabel("联系人生日/Birthday", cc.xy(1, 11));
		builder.add(contactBirthdayField, cc.xy(3, 11));
		builder.add(editcontactBirthdayButton, cc.xy(5, 11));
		
		//联系人Web地址
		urlListBox = new JComboBox();
		addUrlListButton = new JButton("添加");
		editUrlListButton = new JButton("编辑");
		deleteUrlListButton = new JButton("删除");
		
		builder.addLabel("联系人Web地址/URL Address", cc.xy(1, 13));
		builder.add(urlListBox, cc.xy(3, 13));
		builder.add(addUrlListButton, cc.xy(5, 13));
		builder.add(editUrlListButton, cc.xy(7, 13));
		builder.add(deleteUrlListButton, cc.xy(9, 13));
		
		//标签以及分组的显示区域
		FormLayout mainSouthAreaLayout = new FormLayout(
	"pref, 3dlu, pref, 2dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref",//column
	"p, 8dlu, p, 5dlu, p, 5dlu, p, 5dlu, p");//row
		
		mainSouthAreaLayout.setColumnGroups(new int[][]{{7, 9, 11}});
		
		PanelBuilder downbuilder = new PanelBuilder(mainSouthAreaLayout);
		downbuilder.setDefaultDialogBorder();
		
		CellConstraints downcc = new CellConstraints();
		
		downbuilder.addSeparator("标签及分组信息", downcc.xyw(1, 1, 11));
		
		//联系人普通标签
		commonLabelListBox = new JComboBox();
		addCommonLabelListButton = new JButton("添加");
		editCommonLabelListButton = new JButton("编辑");
		deleteCommonLabelListButton = new JButton("删除");
		
		downbuilder.addLabel("普通标签/Common Label", downcc.xy(1, 3));
		downbuilder.add(commonLabelListBox, downcc.xyw(3, 3, 3));
		downbuilder.add(addCommonLabelListButton, downcc.xy(7, 3));
		downbuilder.add(editCommonLabelListButton, downcc.xy(9, 3));
		downbuilder.add(deleteCommonLabelListButton, downcc.xy(11, 3));
		
		//联系人所属分组
		groupListBox = new JComboBox();
		addGroupListButton = new JButton("添加");
		editGroupListButton = new JButton("编辑");
		deleteGroupListButton = new JButton("删除");
		
		downbuilder.addLabel("所属分组/Group", downcc.xy(1, 5));
		downbuilder.add(groupListBox, downcc.xyw(3, 5, 3));
		downbuilder.add(addGroupListButton, downcc.xy(7, 5));
		//downbuilder.add(editGroupListButton, downcc.xy(9, 5));
		downbuilder.add(deleteGroupListButton, downcc.xy(9, 5));
		
		
		//关系标签
		relationLabelListBox = new JComboBox();
		objectContactListBox = new JComboBox();
		relationLabelListBox.setPreferredSize(new   Dimension(100,20));
		objectContactListBox.setPreferredSize(new   Dimension(100,20));
		addRelationLabelListButton = new JButton("添加");
		editRelationLabelListButton = new JButton("编辑");
		deleteRelationLabelListButton = new JButton("删除");
		
		downbuilder.addLabel("关系标签/Relation Label", downcc.xy(1, 7));
		downbuilder.add(relationLabelListBox, downcc.xy(3, 7));
		downbuilder.add(objectContactListBox, downcc.xy(5, 7));
		downbuilder.add(addRelationLabelListButton, downcc.xy(7, 7));
		//downbuilder.add(editRelationLabelListButton, downcc.xy(9, 7));
		downbuilder.add(deleteRelationLabelListButton, downcc.xy(9, 7));
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(builder.getPanel(), "Center");
		mainPanel.add(downbuilder.getPanel(), "South");
	}
}
