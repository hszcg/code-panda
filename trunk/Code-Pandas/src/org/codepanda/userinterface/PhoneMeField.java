package org.codepanda.userinterface;

import javax.swing.JTextField;

public class PhoneMeField extends JTextField{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7905237871318907598L;
	public static final int ADD_STATE = 0;
	public static final int EDIT_STATE = 1;
	int state =ADD_STATE;
	
	public PhoneMeField(){
		super();
	}
	public PhoneMeField(int size){
		super(size);
	}
	public void setState(int newstate){
		state = newstate;
		if(state == ADD_STATE)
			setToolTipText("�ڴ����������س�����ȷ��");
		if(state == EDIT_STATE)
			setToolTipText("�ڴ�������б༭���س�����ȷ��");
	}
	public int getState(){
		return state;
	}
}
