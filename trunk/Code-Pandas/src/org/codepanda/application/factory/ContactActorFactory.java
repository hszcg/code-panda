package org.codepanda.application.factory;

import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;
import org.codepanda.application.contact.DeleteContactActor;
import org.codepanda.application.contact.EditContactActor;
import org.codepanda.application.contact.ImportContactActor;
import org.codepanda.application.contact.NewContactActor;
import org.codepanda.application.xml.ContactXML;
import org.codepanda.application.xml.DeleteContactXML;
import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.contact.PersonalContact;

/**
 * @author xdq
 * 
 */
public class ContactActorFactory extends CommandActorFactory {

	private PersonalContact currentContact;

	public CommandActor creator(CommandType commandType, String commandDetail) {
		// TODO Auto-generated method stub
		//�½���ϵ��
		if (commandType == CommandType.NEW_CONTACT) {
			NewContactActor newContactActor = new NewContactActor();
			currentContact = new PersonalContact();
			ContactXML myContactXML = new ContactXML();
			myContactXML.contactParserXML(currentContact, "<NewContact>",
					"</NewContact>", commandDetail);
			newContactActor.setContact(currentContact);
			return newContactActor;
		}
		//�༭��ϵ��
		 if(commandType==CommandType.EDIT_CONTACT)
		 {
			 EditContactActor editContactActor=new EditContactActor();
			 currentContact=new PersonalContact();
			 ContactXML myContactXML = new ContactXML();
			 myContactXML.contactParserXML(currentContact, "<EditContact>",
						"</EditContact>", commandDetail);
			 editContactActor.setContact(currentContact);
			 return  editContactActor;
		 }
		 //������ϵ��
		 if(commandType==CommandType.IMPORT_CONTACT)
		 {
			 ImportContactActor importContactActor=new  ImportContactActor();
			 currentContact=new PersonalContact();
			 ContactXML myContactXML = new ContactXML();
			 myContactXML.contactParserXML(currentContact, "<ImportContact>",
						"</ImportContact>", commandDetail);
			 importContactActor.setContact(currentContact);
			 return  importContactActor;
		 }
		 //ɾ����ϵ��
		 if(commandType==CommandType.DELETE_CONTACT)
		 {
			 DeleteContactActor deleteContactActor=new DeleteContactActor();
			 currentContact=new PersonalContact();
			 DeleteContactXML deleteContactXML=new DeleteContactXML();
			 deleteContactXML.DeleteConXML(currentContact, "<DeleteContact>", "</DeletaContact>", commandDetail);
			 deleteContactActor.setContact(currentContact);
			 return deleteContactActor;
			 
		 }
		return null;
	}

}