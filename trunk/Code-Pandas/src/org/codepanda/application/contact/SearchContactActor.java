package org.codepanda.application.contact;

import java.util.ArrayList;

import org.codepanda.application.CommandActor;
import org.codepanda.application.xml.SearchContactXML;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;

public class SearchContactActor implements CommandActor {
	private String SearchInfo;
	private boolean Blur=false;
	@Override
	public Object executeCommand() {
		// TODO Auto-generated method stub
		ArrayList<Integer> resultList = (ArrayList<Integer>) searchContact();
		for (int i = 0; i < resultList.size(); i++)
			System.out.println(resultList.get(i));
		return resultList;

	}

	public void setSearchInfo(String searchInfo) {
		this.SearchInfo = searchInfo;

	}

	private Object searchContact() {
		ArrayList<Integer> ISNList = new ArrayList<Integer>();
		SearchContactXML searchContactXML = new SearchContactXML();
		for (ContactOperations t : DataPool.getInstance().getAllContactISNMap()
				.values()) {
			System.out.println("SearchInfo____"+SearchInfo);
			System.out.println("DataPool----Name" + t.getContactName());
			if (searchContactXML.SearchContact(t, "<SearchContact>",
					"</SearchContact>", SearchInfo))
				ISNList.add(t.getISN());
		}
		return ISNList;
	}
	public void setBlur(Boolean b)
	{
		this.Blur=b;
	}
	public Boolean getBlur()
	{
		 return this.Blur;
	}
	
}
