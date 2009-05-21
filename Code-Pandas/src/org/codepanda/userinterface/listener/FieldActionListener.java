package org.codepanda.userinterface.listener;

import org.codepanda.userinterface.PhoneMeField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class FieldActionListener implements ActionListener{
	JComboBox manageBox;
	PhoneMeField field;

	public FieldActionListener(JComboBox Box, PhoneMeField PMF){
		manageBox = Box;
		field = PMF;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(field.getState() == 0){
			if(field.getText().length() != 0){
				manageBox.addItem(field.getText());
				manageBox.setSelectedIndex(manageBox.getItemCount()-1);
			}
			field.setVisible(false);
			field.setText("");
		}
		if(field.getState() == 1){
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
