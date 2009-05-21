
package org.codepanda.application.contact;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.contact.PersonalContact;

public class NewContactActor implements CommandActor {
	private PersonalContact myContact;
	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		return 0;
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
