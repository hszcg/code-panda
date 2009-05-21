package org.codepanda.application.contact;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;

public class EditContactActor implements CommandActor {
	private PersonalContact myContact;
	public static final int NULL_CONTACT = -1;
	public static final int FAILED= -2;
	public static final int SUCCEED = 0;
	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		if(this.getContact()==null)
		{
			System.out.println("Edit Contact NULL!!!!");
			return EditContactActor.NULL_CONTACT;
		}
		int result=DataPool.getInstance().editContact(myContact);
		if(result==-2)
		{
			System.out.println("Edit Contact Failed!!!");
			return EditContactActor.FAILED;
		}
		return EditContactActor.SUCCEED;
	}
	public void setContact(PersonalContact contact)
	{
		this.myContact=contact;
	}
	public PersonalContact getContact()
	{
		return this.myContact;
	}
}
