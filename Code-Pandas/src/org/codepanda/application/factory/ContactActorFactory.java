package org.codepanda.application.factory;

import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;
import org.codepanda.application.contact.NewContactActor;
import org.codepanda.application.xml.ContactXML;
import org.codepanda.utility.contact.PersonalContact;

/**
 * @author xdq
 *
 */
public class ContactActorFactory extends CommandActorFactory {
	
	private PersonalContact currentContact;
	public CommandActor creator(CommandType commandType, String commandDetail)
	{
		// TODO Auto-generated method stub
		if(commandType==CommandType.NEW_CONTACT)
		{
			NewContactActor newContactActor=new NewContactActor();
			currentContact=new PersonalContact();
			ContactXML myContactXML=new  ContactXML();
			myContactXML.contactParserXML(currentContact, "<NewContact>", "</NewContact>", commandDetail);
			newContactActor.setContact(currentContact);
			return newContactActor;
		}
		return null;
	}

}
