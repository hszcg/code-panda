package org.codepanda.application.factory;

import java.util.ArrayList;

import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;
import org.codepanda.application.contact.DeleteContactActor;
import org.codepanda.application.contact.EditContactActor;
import org.codepanda.application.contact.ImportContactActor;
import org.codepanda.application.contact.NewContactActor;
import org.codepanda.application.contact.SearchContactActor;
import org.codepanda.application.contact.StatContactActor;
import org.codepanda.application.xml.ContactXML;
import org.codepanda.application.xml.DeleteContactXML;
import org.codepanda.application.xml.SearchContactXML;
import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;

/**
 * @author xdq
 * 
 */
public class ContactActorFactory extends CommandActorFactory {

	private PersonalContact currentContact;

	public CommandActor creator(CommandType commandType, String commandDetail) {
		// TODO Auto-generated method stub
		//新建联系人
		if (commandType == CommandType.NEW_CONTACT) {
			NewContactActor newContactActor = new NewContactActor();
			currentContact = new PersonalContact();
			ContactXML myContactXML = new ContactXML();
			myContactXML.contactParserXML(currentContact, "<NewContact>",
					"</NewContact>", commandDetail);
			newContactActor.setContact(currentContact);
			return newContactActor;
		}
		//编辑联系人
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
		 //导出联系人
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
		 //删除联系人
		 if(commandType==CommandType.DELETE_CONTACT)
		 {
			 DeleteContactActor deleteContactActor=new DeleteContactActor();
			 currentContact=new PersonalContact();
			 DeleteContactXML deleteContactXML=new DeleteContactXML();
			 deleteContactXML.DeleteConXML(currentContact, "<DeleteContact>", "</DeletaContact>", commandDetail);
			 deleteContactActor.setContact(currentContact);
			 return deleteContactActor;
			 
		 }
		 //进行联系人的生日统计
		 if(commandType==CommandType.STAT_CONTACT)
		 {
			 StatContactActor StatContactActor=new StatContactActor();
			//TODO  
			 
		 }
		 //进行联系人的查询
		 if(commandType==CommandType.SEARCH_CONTACT)
		 {
			 ArrayList<Integer> ISNList=new ArrayList<Integer>();
			 SearchContactActor searchContactActor=new SearchContactActor();
			 SearchContactXML searchContactXML=new SearchContactXML();
			 for (ContactOperations t : DataPool.getInstance().getAllContactList()) {
				 searchContactXML.SearchContact(ISNList,(PersonalContact)t, "SearchContact", "/SearchContact", commandDetail);
			 }
			 searchContactActor.setISNList(ISNList);
			 return searchContactActor;
		 }
		return null;
	}

}
