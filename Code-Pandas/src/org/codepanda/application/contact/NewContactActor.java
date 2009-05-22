
package org.codepanda.application.contact;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;

public class NewContactActor implements CommandActor {
	private PersonalContact myContact;
	public static final int NULL_CONTACT = -1;
	public static final int FAILED= -2;
	public static final int SUCCEED = 0;
	@Override
	public Object executeCommand() {
		// TODO Auto-generated method stub
		if(this.getContact()==null)
		{
			System.out.println("New Contact Null!!!!");
			return NewContactActor.NULL_CONTACT;
		}
		//·µ»ØµÄÊÇISN
		int result=DataPool.getInstance().newContact(myContact);
		return result;
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
