package org.codepanda.userinterface.listener;

import org.codepanda.userinterface.PhoneMeField;
import org.codepanda.userinterface.PhoneMeFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class FieldActionListener implements ActionListener{
	private PhoneMeFrame localFrame;
	private JComboBox manageBox;
	private PhoneMeField field;
	private int localType;
	public static final int EMAIL_FIELD = 1;
	public static final int OTHER_FIELD = 2;

	public FieldActionListener(
			PhoneMeFrame frame, JComboBox Box, PhoneMeField PMF){
		localFrame = frame;
		manageBox = Box;
		field = PMF;
		localType = OTHER_FIELD;
	}
	
	public FieldActionListener(
			PhoneMeFrame frame ,JComboBox Box, PhoneMeField PMF, int type){
		localFrame = frame;
		manageBox = Box;
		field = PMF;
		localType = type;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(localType == EMAIL_FIELD){
			if(!field.getText().contains("@")){
				if(localFrame != null){
					localFrame.getMyPhoneMeStatusBar().setStatus
					("email不符合格式，至少包含@");
				}
				return;
			}
		}
		if(field.getState() == PhoneMeField.ADD_STATE){
			if(field.getText().length() != 0){
				manageBox.addItem(field.getText());
				manageBox.setSelectedIndex(manageBox.getItemCount()-1);
			}
			field.setVisible(false);
			field.setText("");
		}
		if(field.getState() == PhoneMeField.EDIT_STATE){
			if(field.getText().length() != 0){
				manageBox.setEditable(true);
				int index = manageBox.getSelectedIndex();
				manageBox.removeItem(manageBox.getSelectedItem());
				manageBox.insertItemAt(field.getText(), index);
				manageBox.setSelectedIndex(index);
			//manageBox.addItem(field.getText());
			//(String)manageBox.getSelectedItem()
			//manageBox.setSelectedItem(field.getText());
			//manageBox.updateUI();
				manageBox.setEditable(false);
			}
			field.setVisible(false);
			field.setText("");
		}
	}

}
