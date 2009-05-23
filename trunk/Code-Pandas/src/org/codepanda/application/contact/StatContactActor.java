package org.codepanda.application.contact;

import java.util.ArrayList;

import org.codepanda.application.CommandActor;
import org.codepanda.application.xml.SearchContactXML;
import org.codepanda.application.xml.StatContactXML;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;

public class StatContactActor implements CommandActor {
	private String StatInfo;
	public void setStatInfo(String statInfo) {
		this.StatInfo= statInfo;

	}
	@Override
	public Object executeCommand() {
		// TODO Auto-generated method stub
		ArrayList<Integer> resultList = (ArrayList<Integer>) statContact();
		return resultList;
		
	}
	private Object statContact() {
		ArrayList<Integer> ISNList = new ArrayList<Integer>();
		StatContactXML statContactXML = new StatContactXML();
		for (ContactOperations t : DataPool.getInstance().getAllContactISNMap()
				.values()) {
		//	System.out.println("SearchInfo____"+SearchInfo);
			System.out.println("DataPool----Name" + t.getContactName());
			System.out.println("DataPool___Birthday"+t.getContactBirthday());
		//	System.out.print("email1"+t.getEmailAddresseList().get(0));
			if(statContactXML.contactParserXML((PersonalContact)t, "<StatContact>", "</StatContact>",StatInfo))
			{			
				ISNList.add(t.getISN());
				System.out.println("OKContact____");
				System.out.println("Name____"+t.getContactName());
			}
		}
		System.out.println("SIZE_____"+ISNList.size());
		return ISNList;
	}

}
