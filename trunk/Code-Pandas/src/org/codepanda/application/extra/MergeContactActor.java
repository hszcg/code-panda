package org.codepanda.application.extra;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;

public class MergeContactActor implements CommandActor {
	private String Info;
	private PersonalContact currentContact;
	@Override
	public Object executeCommand() {
		// TODO Auto-generated method stub
		DataPool.getInstance().MergeContact(getInfo(), currentContact);
		return 0;
	}
	public void setContact(PersonalContact contact)
	{
		this.currentContact=contact;
	}
	public PersonalContact getContact()
	{
		return this.currentContact;
	}
	public void setInfo(String str)
	{
		this.Info=str;
	}
	public String getInfo()
	{
		return this.Info;
	}
}
