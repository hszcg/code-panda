package org.codepanda.userinterface.listener;

import org.codepanda.userinterface.PhoneMeField;

import java.awt.event.*;
import javax.swing.*;

public class ComboBoxButtonListener implements ActionListener{
	
	JComboBox manageBox;
	PhoneMeField field;
	
	public ComboBoxButtonListener(JComboBox Box, PhoneMeField jf){
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
			field.setState(0);
		}
		if(e.getActionCommand() == "±à¼­"){
			field.setVisible(true);
			System.out.println("STEP 1");
			field.setState(1);
			System.out.println("STEP 2");
			//manageBox.setEditable(true);
		}
		if(e.getActionCommand() == "É¾³ý"){
			manageBox.removeItem(manageBox.getSelectedItem());
		}
	}
	
}
