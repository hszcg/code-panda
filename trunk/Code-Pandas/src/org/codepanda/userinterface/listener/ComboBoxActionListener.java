package org.codepanda.userinterface.listener;

import java.awt.event.*;

import javax.swing.*;

public class ComboBoxActionListener implements ItemListener{
	JComboBox manageBox;
	
	public ComboBoxActionListener(JComboBox Box){
		manageBox = Box;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(manageBox.isEditable()){
	        int state = e.getStateChange();
	        if(state == ItemEvent.DESELECTED){
	        	
	        }
		}
	}
	
	
}
