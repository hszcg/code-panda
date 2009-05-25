package org.codepanda.userinterface.listener;

import org.codepanda.userinterface.ContactInfoPanel;
import org.codepanda.userinterface.PhoneMeField;

import java.awt.event.*;
import javax.swing.*;

public class ComboBoxButtonListener implements ActionListener{
	ContactInfoPanel localPanel;
	JComboBox manageBox;
	PhoneMeField field;
	
	public ComboBoxButtonListener(ContactInfoPanel panel, 
			JComboBox Box, PhoneMeField jf){
		localPanel = panel;
		manageBox = Box;
		field = jf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "添加"){
			System.out.println("添加");
			field.setVisible(true);
			field.setText("");
			field.setState(PhoneMeField.ADD_STATE);
			if(localPanel.getParentFrame() != null)
			localPanel.getParentFrame().
			getMyPhoneMeStatusBar().
			setStatus("在右侧文本框中输入内容，回车进行添加");
			localPanel.paintComponents(localPanel.getGraphics());
		}
		if(e.getActionCommand() == "编辑"){
			if(manageBox.getItemCount() == 0)
			{
				if(localPanel.getParentFrame() != null)
				localPanel.getParentFrame().
				getMyPhoneMeStatusBar().
				setStatus("当前内容为空，只能进行添加操作");
				return;
			}
			field.setVisible(true);
			field.setText(manageBox.getSelectedItem().toString());
			System.out.println("STEP 1");
			field.setState(PhoneMeField.EDIT_STATE);
			System.out.println("STEP 2");
			localPanel.paintComponents(localPanel.getGraphics());
		}
		if(e.getActionCommand() == "删除"){
			if(manageBox.getItemCount() == 0)
			{
				if(localPanel.getParentFrame() != null)
				localPanel.getParentFrame().
				getMyPhoneMeStatusBar().
				setStatus("当前内容为空，只能进行添加操作");
				return;
			}
			manageBox.removeItem(manageBox.getSelectedItem());
		}
	}
	
}
