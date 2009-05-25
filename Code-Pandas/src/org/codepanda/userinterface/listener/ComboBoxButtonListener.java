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
		if(e.getActionCommand() == "���"){
			System.out.println("���");
			field.setVisible(true);
			field.setText("");
			field.setState(PhoneMeField.ADD_STATE);
			if(localPanel.getParentFrame() != null)
			localPanel.getParentFrame().
			getMyPhoneMeStatusBar().
			setStatus("���Ҳ��ı������������ݣ��س��������");
			localPanel.paintComponents(localPanel.getGraphics());
		}
		if(e.getActionCommand() == "�༭"){
			if(manageBox.getItemCount() == 0)
			{
				if(localPanel.getParentFrame() != null)
				localPanel.getParentFrame().
				getMyPhoneMeStatusBar().
				setStatus("��ǰ����Ϊ�գ�ֻ�ܽ�����Ӳ���");
				return;
			}
			field.setVisible(true);
			field.setText(manageBox.getSelectedItem().toString());
			System.out.println("STEP 1");
			field.setState(PhoneMeField.EDIT_STATE);
			System.out.println("STEP 2");
			localPanel.paintComponents(localPanel.getGraphics());
		}
		if(e.getActionCommand() == "ɾ��"){
			if(manageBox.getItemCount() == 0)
			{
				if(localPanel.getParentFrame() != null)
				localPanel.getParentFrame().
				getMyPhoneMeStatusBar().
				setStatus("��ǰ����Ϊ�գ�ֻ�ܽ�����Ӳ���");
				return;
			}
			manageBox.removeItem(manageBox.getSelectedItem());
		}
	}
	
}
