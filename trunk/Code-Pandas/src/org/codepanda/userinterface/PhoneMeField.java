package org.codepanda.userinterface;

import javax.swing.JTextField;

public class PhoneMeField extends JTextField{
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
