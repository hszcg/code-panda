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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.MergeContactMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;

import com.google.common.collect.HashMultimap;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class PhoneMeArrangeContactPanel extends JPanel
					implements ListSelectionListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4426399520001378902L;
	final private PhoneMeFrame localFrame;
	private HashMultimap<String, Integer> localNameMap;
	private HashMap<String, Vector<Integer>> displayContent;
	private Vector<Integer> displayISN;
	private boolean first = true;
	
	private JPanel leftPanel;
	private JLabel title1;
	private Vector<String> nameSet;
	private DefaultListModel nameListModel;
	private JList nameList;
	private boolean getList;
	
	private Vector<JCheckBox> secondBox;
	private JPanel middlePanel;
	private JPanel mainPanel;
	private JLabel title2;
	
	private JPanel rightPanel;
	private ContactOperations displayContact;
	private JTextField nameField;
	private JComboBox phoneNumberBox;
	private JComboBox workingDepartmentBox;
	private JTextField contactBirthdayField;
	private JComboBox groupListBox;
	private JButton seeAll;
	private JButton action;
	
	public PhoneMeArrangeContactPanel(PhoneMeFrame frame){
		localFrame = frame;
		setThisLayout();
		initialComponent();
		first = false;
	}
	
	public void setThisLayout(){
		setLayout(new GridLayout(1, 3));
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		title1 = new JLabel("所有同名联系人的列表");
		leftPanel.setBorder(new 
				SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		add(leftPanel);
		
		middlePanel = new JPanel();
		add(middlePanel);
		
		mainPanel = new JPanel();
		title2 = new JLabel();
		
		rightPanel = new JPanel();
		setRightComponent();
		
		action = new JButton("合并所有选中的联系人");
		action.addActionListener(this);
		
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
		
		rightPanel = rightBuilder.getPanel();
		rightPanel.setBorder(new 
				SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		rightPanel.setVisible(false);
		add(rightPanel);
	}
	
	public void initialComponent(){
		localNameMap = DataPool.getInstance().getAllContactNameMultimap();
		if(!getData())
		{
			//localFrame.getMyPhoneMeStatusBar().setStatus
			//("当前没有重名的联系人");
			if(first){
				JOptionPane.showMessageDialog
				(localFrame, "当前没有重名的联系人");
				localFrame.getMyPhoneMeMajorPanel().closeTab(this);
				//localFrame.getMyPhoneMeStatusBar().setStatus
				//("当前没有重名的联系人");
				return;
			}
			else{
				localFrame.getMyPhoneMeMajorPanel().closeTab(this);
				JOptionPane.showMessageDialog
				(localFrame, "当前没有重名的联系人");
			}
		}
		
		nameListModel = new DefaultListModel();
		for(int i=0;i<nameSet.size();i++){
			nameListModel.addElement(nameSet.get(i));
		}
		nameList = new JList(nameListModel);
		nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nameList.addListSelectionListener(this);
		
		leftPanel.removeAll();
		leftPanel.add(title1, "North");
		leftPanel.add(nameList, "Center");
		leftPanel.paintComponents(leftPanel.getGraphics());
		getList = false;
		
		middlePanel.setLayout(new BorderLayout());
		
		middlePanel.add(mainPanel, "North");
		middlePanel.add(title2, "Center");
		secondBox = new Vector<JCheckBox>();
	}
	
	public boolean getData(){
		boolean moreThanOne = false;
		nameSet = new Vector<String>();
		displayContent = new HashMap<String, Vector<Integer>>();
		for (String str : localNameMap.keySet()) {
			if(localNameMap.get(str).size() > 1){
				nameSet.add(str);
				Vector<Integer> ISNSet = new Vector<Integer>();
				ISNSet.addAll(localNameMap.get(str));
				displayContent.put(str, ISNSet);
				moreThanOne = true;
			}
		}
		return moreThanOne;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(!arg0.getValueIsAdjusting() && !getList){
		localFrame.getMyPhoneMeStatusBar().setStatus("正在读取数据，请稍候");
		displayISN = displayContent.get
		(nameList.getSelectedValue().toString());
		System.out.println("www");
		System.out.println(nameList.getSelectedValue().toString());
		System.out.println(displayISN.size());
		secondBox.clear();
		mainPanel.removeAll();
		
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
			localFrame.getMyPhoneMeStatusBar().
			setStatus("选择要整理的联系人");
			title2.setText("共有" + 
					displayISN.size()+"个姓名为" + 
					nameList.getSelectedValue().toString() + "的联系人");
			title2.setVisible(true);
		}
		updateRightPanel(Integer.MIN_VALUE);
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
		if(ISN.equals(Integer.MIN_VALUE)){
			nameField.setText("");
			phoneNumberBox.removeAllItems();
			workingDepartmentBox.removeAllItems();
			contactBirthdayField.setText("");
			groupListBox.removeAllItems();
			return;
		}
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == action){
			System.out.println("action merge");
			ArrayList<Integer> mergeISN = new ArrayList<Integer>();
			for(int i=0;i<secondBox.size();i++){
				if(secondBox.get(i).isSelected()){
					mergeISN.add(displayISN.get(i));
				}
			}
			for(int i=0;i<mergeISN.size();i++){
				System.out.println(mergeISN.get(i));
			}
			if(mergeISN.size() <= 1){
				localFrame.getMyPhoneMeStatusBar().
				setStatus("选择的联系人太少，不能进行合并");
				return;
			}
			
			StringBuffer message = new StringBuffer();
			for(int i=0;i<mergeISN.size();i++)
				message.append(MyXMLMaker.addTag
						("ISN", String.valueOf(mergeISN.get(i))));
			
			String xml = new String();
			xml = MyXMLMaker.addTag("MergeContact", message.toString());
			xml = MyXMLMaker.addTag("com", xml);
			System.out.println(xml);
			
			CommandVisitor statContactCommandVisitor = new CommandVisitor(
					CommandType.MERGE_CONTACT, xml);
			MergeContactMessageHandler mergeContactMessageHandler = 
				new MergeContactMessageHandler();
			mergeContactMessageHandler
					.executeCommand(statContactCommandVisitor); 
			mainPanel.removeAll();
			title2.setVisible(false);
			initialComponent();
			updateRightPanel(Integer.MIN_VALUE);
			localFrame.updateAllPanel(mergeISN);
			this.paintComponents(this.getGraphics());
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
		{
			updateRightPanel(displayISN.get
				(Integer.valueOf(e.getActionCommand())));
		}
	}
}
