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
		if(e.getActionCommand() == "Ìí¼Ó"){
			System.out.println("Ìí¼Ó");
			//manageBox.addItem(new StringBuffer(""));
			//manageBox.setSelectedIndex(manageBox.getItemCount()-1);
			//manageBox.setEditable(true);
			field.setVisible(true);
			field.setText("");
			field.setState(PhoneMeField.ADD_STATE);
			localPanel.paintComponents(localPanel.getGraphics());
		}
		if(e.getActionCommand() == "±à¼­"){
			if(manageBox.getItemCount() == 0)
				return;
			field.setVisible(true);
			field.setText(manageBox.getSelectedItem().toString());
			System.out.println("STEP 1");
			field.setState(PhoneMeField.EDIT_STATE);
			System.out.println("STEP 2");
			localPanel.paintComponents(localPanel.getGraphics());
		}
		if(e.getActionCommand() == "É¾³ý"){
			manageBox.removeItem(manageBox.getSelectedItem());
		}
	}
	
}
