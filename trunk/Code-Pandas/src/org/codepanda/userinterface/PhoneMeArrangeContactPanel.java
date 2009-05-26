package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.codepanda.utility.data.DataPool;

import com.google.common.collect.HashMultimap;

public class PhoneMeArrangeContactPanel extends JPanel
					implements ListSelectionListener, ActionListener{
	final private PhoneMeFrame localFrame;
	final private HashMultimap<String, Integer> localNameMap;
	private HashMap<String, Vector<Integer>> displayContent;
	
	private JPanel leftPanel;
	private Vector<String> nameSet;
	private JList nameList;
	private JLabel title1;
	
	private Vector<JCheckBox> secondBox;
	private JPanel middlePanel;
	private JPanel mainPanel;
	private JLabel title2;
	private JButton action;
	
	private JPanel rightPanel;
	
	public PhoneMeArrangeContactPanel(PhoneMeFrame frame){
		localFrame = frame;
		localNameMap = DataPool.getInstance().getAllContactNameMultimap();
		if(!getData())
		{
			localFrame.getMyPhoneMeStatusBar().setStatus
			("当前没有重名的联系人");
			return;
		}
		setLabel();
		
		setLayout(new GridLayout(1, 3));
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(title1, "North");
		
		nameList = new JList(nameSet);
		nameList.addListSelectionListener(this);
		leftPanel.add(nameList, "Center");
		add(leftPanel);
		
		middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());
		//middlePanel.add(title2, "North");
		
		mainPanel = new JPanel();
		middlePanel.add(mainPanel, "North");
		add(middlePanel);
		secondBox = new Vector<JCheckBox>();
		action = new JButton("对选中的联系人进行整理");
		middlePanel.add(action, "South");
		action.addActionListener(this);
		
		rightPanel = new JPanel();
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
			title2.setText("有同样姓名为" + 
				nameList.getSelectedValue().toString() + "的所有联系人");
		localFrame.getMyPhoneMeStatusBar().setStatus("正在读取数据，请稍候");
		Vector<Integer> displayISN = displayContent.get
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
	
	public void setLabel(){
		title1 = new JLabel("所有同名的联系人列表");
		title2 = new JLabel();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
