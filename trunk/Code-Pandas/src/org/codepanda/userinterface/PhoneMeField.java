package org.codepanda.userinterface;

import javax.swing.JTextField;

public class PhoneMeField extends JTextField{
	public static final int ADD_STATE = 0;
	public static final int EDIT_STATE = 1;
	int state;
	
	public PhoneMeField(){
		super();
	}
	public PhoneMeField(int size){
		super(size);
	}
	public void setState(int newstate){
		state = newstate;
	}
	public int getState(){
		return state;
	}
}
