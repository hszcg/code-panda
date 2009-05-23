package org.codepanda.userinterface;

import javax.swing.JDialog;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.FormLayout;

public class PhoneMeCorfirmDialog extends JDialog{
	JTextField userNameField;
	JTextField userPasswordField;
	
	public PhoneMeCorfirmDialog(){
		super();
		FormLayout upperlayout = new FormLayout(
			"1dlu, pref, 50dlu, pref, 1dlu", // columns
			"p, 10dlu, p, 10dlu, p, 10dlu, p"); // rows
	}
}
