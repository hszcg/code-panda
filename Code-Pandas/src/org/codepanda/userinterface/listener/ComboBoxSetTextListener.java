package org.codepanda.userinterface.listener;

import org.codepanda.userinterface.PhoneMeField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class ComboBoxSetTextListener implements ItemListener{
	PhoneMeField localField;
	JComboBox localBox;

	public ComboBoxSetTextListener(PhoneMeField field, JComboBox Box){
		localField = field;
		localBox = Box;
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(!localBox.isEditable()){
			System.out.println("UnEditable");
			if(localField.isVisible() && 
				localField.getState() == PhoneMeField.EDIT_STATE){
					System.out.println("SucceddIn");
					localField.setText
					(localBox.getSelectedItem().toString());
			}
		}
	}

}
