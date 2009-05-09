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
	
	//ͼƬ����
	JLabel headImageLabel;
	JButton editHeadImageButton;
	JButton deleteHeadImageButton;
	
	//��ϵ����������
	//JXMultiSplitPane namePanel;
	//JLabel nameLabel;
	JTextField nameField;
	JButton nameEditButton;
	
	//��ϵ�˵绰���벿��
	//JXMultiSplitPane phoneNumberPanel;
	//JLabel phoneNumberLabel;
	JComboBox phoneNumberBox;
	JButton addPhoneNumberButton;
	JButton editPhoneNumberButton;
	JButton deletePhoneNumberButton;
	
	//��ϵ��e-mail����
	//JXMultiSplitPane emailAddressPanel;
	//JLabel emailAddressLabel;
	JComboBox emailAddressBox;
	JButton addEmailAddressButton;
	JButton editEmailAddressButton;
	JButton deleteEmailAddressButton;
	
	//��ϵ��סַ����
	//JXMultiSplitPane contactAddressPanel;
	//JLabel contactAddressLabel;
	JComboBox contactAddressBox;
	JButton addContactAddressButton;
	JButton editContactAddressButton;
	JButton deleteContactAddressButton;
	
	//��ϵ�˹�����ѧϰ��λ
	JComboBox workingDepartmentBox;
	JButton addWorkingDepartmentButton;
	JButton editWorkingDepartmentButton;
	JButton deleteWorkingDepartmentButton;
	
	//��ϵ�˼�ʱ��ϵ��ʽ����
	JComboBox imContactInformationBox;
	JButton addImContactInformationButton;
	JButton editImContactInformationButton;
	JButton deleteImContactInformationButton;
	
	//��ϵ�����ղ���
	JTextField contactBirthdayField;
	JButton editcontactBirthdayButton;
	
	//��ϵ��Web��ַ
	JComboBox urlListBox;
	JButton addUrlListButton;
	JButton editUrlListButton;
	JButton deleteUrlListButton;
	
	//��ϵ����ͨ��ǩ
	JComboBox commonLabelListBox;
	JButton addCommonLabelListButton;
	JButton editCommonLabelListButton;
	JButton deleteCommonLabelListButton;
	
	//��ϵ����������
	JComboBox groupListBox;
	JButton addGroupListButton;
	JButton editGroupListButton;
	JButton deleteGroupListButton;
	
	//��ϵ�˹�ϵ��ǩ
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
		JButton temp = new JButton("����");
		temp.setVisible(false);
		
		//MultiSplitPane
		String layoutDef =
		"(ROW (LEAF name=label weight=0.35) (LEAF name=field weight=0.5) " +
		"(LEAF name=leftButton weight=0.05) " +
		"(LEAF name=middleButton weight=0.05)) " +
		"(LEAF name=rightButton weight=0.05))";
		MultiSplitLayout.Node modelRoot = MultiSplitLayout.
			parseModel( layoutDef );
		
		//�����ϵ����������������
		namePanel = new JXMultiSplitPane();
		namePanel.getMultiSplitLayout().setModel(modelRoot);
		
		nameLabel = new JLabel("����/Name");
		//nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameField = new JTextField();
		nameField.setText("");
		nameEditButton = new JButton("�༭");
		
		namePanel.add(nameLabel, "label");
		namePanel.add(nameField, "field");
		namePanel.add(nameEditButton, "leftButton");
		namePanel.add(temp, "middleButton");
		namePanel.add(temp, "rightButton");
		
		//�����ϵ�˵绰����������
		phoneNumberPanel = new JXMultiSplitPane();
		phoneNumberPanel.getMultiSplitLayout().setModel(modelRoot);
		
		phoneNumberLabel = new JLabel("��ϵ�绰");
		//phoneNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneNumberBox = new JComboBox();
		addPhoneNumberButton = new JButton("���");
		editPhoneNumberButton = new JButton("�༭");
		deletePhoneNumberButton = new JButton("ɾ��");
		
		phoneNumberPanel.add(phoneNumberLabel, "label");
		phoneNumberPanel.add(phoneNumberBox, "field");
		phoneNumberPanel.add(addPhoneNumberButton, "middleButton");
		phoneNumberPanel.add(editPhoneNumberButton, "leftButton");
		phoneNumberPanel.add(deletePhoneNumberButton, "rightButton");
		
		upperRightPanel.add(namePanel, "up");
		upperRightPanel.add(phoneNumberPanel, "down");*/
		
		nameField = new JTextField(30);
		//nameField.setText("��������ϵ�˵�����");
		nameEditButton = new JButton("�༭");
		
		//phoneNumberLabel = new JLabel("��ϵ�绰");
		String temp[] = {"010-51534419", "13810013188", "029-85367800"};
		phoneNumberBox = new JComboBox(temp);
		addPhoneNumberButton = new JButton("���");
		editPhoneNumberButton = new JButton("�༭");
		deletePhoneNumberButton = new JButton("ɾ��");
		
		FormLayout upperRightlayout = new FormLayout(
	    "pref, 3dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref", // columns
	    "p, 8dlu, p, 5dlu, p");      // rows
		upperRightlayout.setColumnGroups(new int[][]{{5, 7, 9}});
		
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
		
		//upperLeftPanel.add(new JLabel("test1"));
		FormLayout upperLeftlayout = new FormLayout(
		"pref, 9dlu, pref", // columns
		"p, 7dlu, p");      // rows
		upperLeftlayout.setColumnGroups(new int[][]{{1, 3}});
				
		PanelBuilder leftBuilder = new PanelBuilder(upperLeftlayout);
		leftBuilder.setDefaultDialogBorder();
		
		CellConstraints leftcc = new CellConstraints();
		
		headImageLabel = new JLabel();//��ϵ��ͼƬ
		editHeadImageButton = new JButton("�༭");
		deleteHeadImageButton = new JButton("ɾ��");
		
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
				"�л����񹲺͹������к������廪��ѧ�Ͼ���Ԣ1#419B"};
		
		builder.addSeparator("������Ϣ", cc.xyw(1, 1, 9));
		//e-mail
		emailAddressBox = new JComboBox(temp);
		//emailAddressBox.setPreferredSize(new   Dimension(200,20));
		emailAddressBox.setEditable(true);
		addEmailAddressButton = new JButton("���");
		editEmailAddressButton = new JButton("�༭");
		deleteEmailAddressButton = new JButton("ɾ��");
		
		builder.addLabel("��������/E-mail", cc.xy(1, 3));
		//builder.add(emailAddressBox, cc.xyw(3, 1, 3));
		builder.add(emailAddressBox, cc.xy(3, 3));
		builder.add(addEmailAddressButton, cc.xy(5, 3));
		builder.add(editEmailAddressButton, cc.xy(7, 3));
		builder.add(deleteEmailAddressButton, cc.xy(9, 3));
		//builder.add(addEmailAddressButton, cc.xy(7, 1));
		//builder.add(editEmailAddressButton, cc.xy(9, 1));
		//builder.add(deleteEmailAddressButton, cc.xy(11, 1));
		
		//��ϵ��סַ
		contactAddressBox = new JComboBox();
		addContactAddressButton = new JButton("���");
		editContactAddressButton = new JButton("�༭");
		deleteContactAddressButton = new JButton("ɾ��");
		
		builder.addLabel("��ͥסַ/Address", cc.xy(1, 5));
		builder.add(contactAddressBox, cc.xy(3, 5));
		builder.add(addContactAddressButton, cc.xy(5, 5));
		builder.add(editContactAddressButton, cc.xy(7, 5));
		builder.add(deleteContactAddressButton, cc.xy(9, 5));
		
		//��ϵ�˹�����ѧϰ��λ
		workingDepartmentBox = new JComboBox();
		addWorkingDepartmentButton = new JButton("���");
		editWorkingDepartmentButton = new JButton("�༭");
		deleteWorkingDepartmentButton = new JButton("ɾ��");
		
		builder.addLabel("������λ/Working Department", cc.xy(1, 7));
		builder.add(workingDepartmentBox, cc.xy(3, 7));
		builder.add(addWorkingDepartmentButton, cc.xy(5, 7));
		builder.add(editWorkingDepartmentButton, cc.xy(7, 7));
		builder.add(deleteWorkingDepartmentButton, cc.xy(9, 7));
		
		//��ϵ�˼�ʱ��ϵ��ʽ
		imContactInformationBox = new JComboBox();
		addImContactInformationButton = new JButton("���");
		editImContactInformationButton = new JButton("�༭");
		deleteImContactInformationButton = new JButton("ɾ��");
		
		builder.addLabel("��ʱ��ϵ��ʽ/Im Contact", cc.xy(1, 9));
		builder.add(imContactInformationBox, cc.xy(3, 9));
		builder.add(addImContactInformationButton, cc.xy(5, 9));
		builder.add(editImContactInformationButton, cc.xy(7, 9));
		builder.add(deleteImContactInformationButton, cc.xy(9, 9));
		
		//��ϵ�����ղ���
		contactBirthdayField = new JTextField(30);
		editcontactBirthdayButton = new JButton("�༭");
		
		builder.addLabel("��ϵ������/Birthday", cc.xy(1, 11));
		builder.add(contactBirthdayField, cc.xy(3, 11));
		builder.add(editcontactBirthdayButton, cc.xy(5, 11));
		
		//��ϵ��Web��ַ
		urlListBox = new JComboBox();
		addUrlListButton = new JButton("���");
		editUrlListButton = new JButton("�༭");
		deleteUrlListButton = new JButton("ɾ��");
		
		builder.addLabel("��ϵ��Web��ַ/URL Address", cc.xy(1, 13));
		builder.add(urlListBox, cc.xy(3, 13));
		builder.add(addUrlListButton, cc.xy(5, 13));
		builder.add(editUrlListButton, cc.xy(7, 13));
		builder.add(deleteUrlListButton, cc.xy(9, 13));
		
		//��ǩ�Լ��������ʾ����
		FormLayout mainSouthAreaLayout = new FormLayout(
	"pref, 3dlu, pref, 2dlu, pref, 15dlu, pref, 3dlu, pref, 3dlu, pref",//column
	"p, 8dlu, p, 5dlu, p, 5dlu, p, 5dlu, p");//row
		
		mainSouthAreaLayout.setColumnGroups(new int[][]{{7, 9, 11}});
		
		PanelBuilder downbuilder = new PanelBuilder(mainSouthAreaLayout);
		downbuilder.setDefaultDialogBorder();
		
		CellConstraints downcc = new CellConstraints();
		
		downbuilder.addSeparator("��ǩ��������Ϣ", downcc.xyw(1, 1, 11));
		
		//��ϵ����ͨ��ǩ
		commonLabelListBox = new JComboBox();
		addCommonLabelListButton = new JButton("���");
		editCommonLabelListButton = new JButton("�༭");
		deleteCommonLabelListButton = new JButton("ɾ��");
		
		downbuilder.addLabel("��ͨ��ǩ/Common Label", downcc.xy(1, 3));
		downbuilder.add(commonLabelListBox, downcc.xyw(3, 3, 3));
		downbuilder.add(addCommonLabelListButton, downcc.xy(7, 3));
		downbuilder.add(editCommonLabelListButton, downcc.xy(9, 3));
		downbuilder.add(deleteCommonLabelListButton, downcc.xy(11, 3));
		
		//��ϵ����������
		groupListBox = new JComboBox();
		addGroupListButton = new JButton("���");
		editGroupListButton = new JButton("�༭");
		deleteGroupListButton = new JButton("ɾ��");
		
		downbuilder.addLabel("��������/Group", downcc.xy(1, 5));
		downbuilder.add(groupListBox, downcc.xyw(3, 5, 3));
		downbuilder.add(addGroupListButton, downcc.xy(7, 5));
		//downbuilder.add(editGroupListButton, downcc.xy(9, 5));
		downbuilder.add(deleteGroupListButton, downcc.xy(9, 5));
		
		
		//��ϵ��ǩ
		relationLabelListBox = new JComboBox();
		objectContactListBox = new JComboBox();
		relationLabelListBox.setPreferredSize(new   Dimension(100,20));
		objectContactListBox.setPreferredSize(new   Dimension(100,20));
		addRelationLabelListButton = new JButton("���");
		editRelationLabelListButton = new JButton("�༭");
		deleteRelationLabelListButton = new JButton("ɾ��");
		
		downbuilder.addLabel("��ϵ��ǩ/Relation Label", downcc.xy(1, 7));
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
