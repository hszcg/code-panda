package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;
import org.jvnet.substance.colorschemepack.BelizeColorScheme;

import com.google.common.collect.HashMultimap;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;

public class PhoneMeArrangeContactPanel extends JPanel
					implements ListSelectionListener, ActionListener{
	final private PhoneMeFrame localFrame;
	final private HashMultimap<String, Integer> localNameMap;
	private HashMap<String, Vector<Integer>> displayContent;
	private Vector<Integer> displayISN;
	
	private JPanel leftPanel;
	private Vector<String> nameSet;
	private JList nameList;
	
	private Vector<JCheckBox> secondBox;
	private JPanel middlePanel;
	private JPanel mainPanel;
	private JButton action;
	
	private JPanel rightPanel;
	private ContactOperations displayContact;
	private JTextField nameField;
	private JComboBox phoneNumberBox;
	private JComboBox workingDepartmentBox;
	private JTextField contactBirthdayField;
	private JComboBox groupListBox;
	private JButton seeAll;
	
	public PhoneMeArrangeContactPanel(PhoneMeFrame frame){
		localFrame = frame;
		localNameMap = DataPool.getInstance().getAllContactNameMultimap();
		if(!getData())
		{
			localFrame.getMyPhoneMeStatusBar().setStatus
			("当前没有重名的联系人");
			return;
		}
		
		setLayout(new GridLayout(1, 3));
		
		FormLayout leftLayout = 
			new FormLayout("1dlu, pref, 1dlu", // columns
							"1dlu, p, 10dlu, p, 50dlu, p"); // rows
		
		PanelBuilder leftBuilder = new PanelBuilder(leftLayout);
		leftBuilder.setDefaultDialogBorder();
		CellConstraints leftcc = new CellConstraints();
		
		leftBuilder.addLabel("同名的联系人列表", leftcc.xy(2, 2));
		
		
		nameList = new JList(nameSet);
		nameList.addListSelectionListener(this);
		
		leftBuilder.add(nameList, leftcc.xy(2, 4));
		
		action = new JButton("合并所有选中的联系人");
		action.addActionListener(this);
		
		leftPanel = leftBuilder.getPanel();
		leftPanel.setBorder(new 
				SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		add(leftPanel);
		
		middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());
		
		mainPanel = new JPanel();
		middlePanel.add(mainPanel, "North");
		add(middlePanel);
		secondBox = new Vector<JCheckBox>();
		//middlePanel.add(action, "South");
		
		rightPanel = new JPanel();
		setRightComponent();
		FormLayout rightLayout = 
			new FormLayout("1dlu, pref, 4dlu, pref, 1dlu", // columns
		"5dlu, p, 10dlu, p, 8dlu, p, 8dlu, p, 8dlu, " +
		"p, 8dlu, p, 30dlu, p, 90dlu, p"); // rows
		
		PanelBuilder rightBuilder = new PanelBuilder(rightLayout);
		rightBuilder.setDefaultDialogBorder();
		CellConstraints rightcc = new CellConstraints();
		
		rightBuilder.addLabel("摘要信息", rightcc.xy(2, 2));
		rightBuilder.addLabel("姓名", rightcc.xy(2, 4));
		rightBuilder.add(nameField, rightcc.xy(4, 4));
		rightBuilder.addLabel("电话", rightcc.xy(2, 6));
		rightBuilder.add(phoneNumberBox, rightcc.xy(4, 6));
		rightBuilder.addLabel("工作单位", rightcc.xy(2, 8));
		rightBuilder.add(workingDepartmentBox, rightcc.xy(4, 8));
		rightBuilder.addLabel("生日", rightcc.xy(2, 10));
		rightBuilder.add(contactBirthdayField, rightcc.xy(4, 10));
		rightBuilder.addLabel("分组", rightcc.xy(2, 12));
		rightBuilder.add(groupListBox, rightcc.xy(4, 12));
		rightBuilder.add(seeAll, rightcc.xy(2, 14));
		rightBuilder.add(action, rightcc.xy(4, 16));
		
		System.out.println("in right");
		rightPanel = rightBuilder.getPanel();
		rightPanel.setBorder(new 
				SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		rightPanel.setVisible(false);
		add(rightPanel);
	}
	
	public boolean getData(){
		System.out.println("getDataing");
		boolean moreThanOne = false;
		nameSet = new Vector<String>();
		displayContent = new HashMap<String, Vector<Integer>>();
		for (String str : localNameMap.keySet()) {
			if(localNameMap.get(str).size() > 1){
				System.out.println("[][][][][][][][]");
				System.out.println(str);
				System.out.println(localNameMap.get(str).size());
				nameSet.add(str);
				Vector<Integer> ISNSet = new Vector<Integer>();
				ISNSet.addAll(localNameMap.get(str));
				displayContent.put(str, ISNSet);
				System.out.println(ISNSet.size());
				moreThanOne = true;
			}
		}
		return moreThanOne;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		if(!arg0.getValueIsAdjusting()){
			//title2.setText("有同样姓名为" + 
				//nameList.getSelectedValue().toString() + "的所有联系人");
		localFrame.getMyPhoneMeStatusBar().setStatus("正在读取数据，请稍候");
		displayISN = displayContent.get
		(nameList.getSelectedValue().toString());
		secondBox.clear();
		mainPanel.removeAll();
		
		System.out.println("valueChanged");
		System.out.println(displayISN.size());
		for(int index = 0; index <displayISN.size(); index ++){
			JCheckBox temp = new JCheckBox
			(DataPool.getInstance().getAllContactISNMap().
				get(displayISN.get(index)).getContactName() + "     (ID:"
				+ displayISN.get(index) + ")");
			temp.setActionCommand(String.valueOf(index));
			temp.addActionListener(this);
			secondBox.add(temp);
		}
		mainPanel.setLayout
		(new GridLayout(displayISN.size() + displayISN.size()/2, 1));
		
		for(int index = 0;index < secondBox.size(); index ++){
			mainPanel.add(secondBox.get(index));
		}
		localFrame.getMyPhoneMeStatusBar().setStatus("选择要整理的联系人");
		}
	}
	
	public void setRightComponent(){
		nameField = new JTextField(23);
		nameField.setEditable(false);
		phoneNumberBox = new JComboBox();
		workingDepartmentBox = new JComboBox();
		contactBirthdayField = new JTextField();
		groupListBox = new JComboBox();
		seeAll = new JButton("查看完整");
		seeAll.addActionListener(this);
		seeAll.setToolTipText("点击查看该联系人完整信息");
	}
	
	public void updateRightPanel(Integer ISN){
		displayContact = 
			DataPool.getInstance().getAllContactISNMap().get(ISN);
		
		nameField.setText(displayContact.getContactName());
		if (displayContact.getPhoneNumberList() != null) {
			phoneNumberBox.setModel(new DefaultComboBoxModel(
				(String[]) (displayContact.getPhoneNumberList())
				.toArray(new String[0])));
		}
		
		if(displayContact.getWorkingDepartmentList() != null){
			workingDepartmentBox.setModel(new DefaultComboBoxModel(
					(String[]) (displayContact.getWorkingDepartmentList())
					.toArray(new String[0])));
		}
		
		if(displayContact.getContactBirthday() != null){
			contactBirthdayField.setText
			(displayContact.getContactBirthday());
		}
		
		if (displayContact.getGroupList() != null) {
			groupListBox.setModel(new DefaultComboBoxModel(
					(String[]) (displayContact.getGroupList())
					.toArray(new String[0])));
		}
		
		rightPanel.setVisible(true);
		System.out.println("in right");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == action){
			System.out.println("action merge");
			Vector<Integer> mergeISN = new Vector<Integer>();
			for(int i=0;i<secondBox.size();i++){
				if(secondBox.get(i).isSelected()){
					mergeISN.add(displayISN.get(i));
				}
			}
			for(int i=0;i<mergeISN.size();i++){
				System.out.println(mergeISN.get(i));
			}
			return;
		}
		if(e.getSource() == seeAll){
			localFrame.getMyPhoneMeMajorPanel().addNewTab(
					displayContact.getContactName(),
					new ContactInfoPanel(localFrame, displayContact, false,
							ContactInfoPanel.CONTACT_INFO_PANEL));
		}
		AbstractButton abstractButton = (AbstractButton) e.getSource();
		boolean selected = abstractButton.getModel().isSelected();
		if(selected)
		{	System.out.println(displayISN.get
				(Integer.valueOf(e.getActionCommand())));
			updateRightPanel(displayISN.get
				(Integer.valueOf(e.getActionCommand())));
		}
	}
}
