package org.codepanda.application.contact;

import java.util.ArrayList;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;

public class SearchContactActor implements CommandActor {
	private ArrayList <Integer> ISNList;
	@Override
	public Object executeCommand() {
		// TODO Auto-generated method stub
		
		return 0;
	}
	public void setISNList(ArrayList <Integer> isn)
	{
		this.ISNList=isn;
	}
	
}
