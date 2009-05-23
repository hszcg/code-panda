package org.codepanda.application.contact;

import java.util.ArrayList;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;

public class SearchContactActor implements CommandActor {
	private ArrayList<Integer> ISNList;
	private String SearchInfo;
	@Override
	public Object executeCommand() {
		// TODO Auto-generated method stub
		ArrayList<Integer> resultList=(ArrayList<Integer>) DataPool.getInstance().searchContact(SearchInfo);
		for(int i=0;i<resultList.size();i++)
			System.out.println(resultList.get(i));
		return resultList;
		
	}

	public void setISNList(ArrayList<Integer> isn) {
		this.ISNList = isn;
	}
	public void setSearchInfo(String searchInfo)
	{
		this.SearchInfo=searchInfo;
	
	}
}
