package org.codepanda.application.googlecontactsyn;

import com.google.gdata.client.calendar.*;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;
import com.google.gdata.data.contacts.*;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import java.io.IOException;

import java.net.*;
import java.util.ArrayList;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;

public class GContactOper {

	public static void getAllContacts() throws ServiceException, IOException {
		ContactsService myService = new ContactsService("TEST");
		try {
			myService.setUserCredentials("code.pandas@gmail.com", "qwerfdsa");
		} catch (AuthenticationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// Request the feed
		URL feedUrl = new URL(
				"http://www.google.com/m8/feeds/contacts/code.pandas@gmail.com/full");
		ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
		// Print the results
		System.out.println(resultFeed.getTitle().getPlainText());
		for (int i = 0; i < resultFeed.getEntries().size(); i++) {
			PersonalContact pc = new PersonalContact();
			ContactEntry entry = resultFeed.getEntries().get(i);

			pc.setContactName(entry.getTitle().getPlainText());
			System.out.println("\t" + entry.getTitle().getPlainText());

			ArrayList<String> emailAddressList = new ArrayList<String>();
			System.out.println("Email addresses:");
			for (Email email : entry.getEmailAddresses()) {
				emailAddressList.add(email.getAddress());
				System.out.print(" " + email.getAddress());
				System.out.print("\n");
			}
			pc.setEmailAddresseList(emailAddressList);

			ArrayList<String> imContactInformationList = new ArrayList<String>();
			System.out.println("IM addresses:");
			for (Im im : entry.getImAddresses()) {
				imContactInformationList.add(im.getAddress());
				System.out.print(" " + im.getAddress());
				System.out.print("\n");
			}

			pc.setImContactInformationList(imContactInformationList);

			DataPool.getInstance().newContact(pc);
			// System.out.println("Groups:");
			// for (GroupMembershipInfo group : entry.getGroupMembershipInfos())
			// {
			// String groupHref = group.getHref();
			// System.out.println("  Id: " + groupHref);
			// }
			//
			// System.out.println("Extended Properties:");
			// for (ExtendedProperty property : entry.getExtendedProperties()) {
			// if (property.getValue() != null) {
			// System.out.println("  " + property.getName() + "(value) = "
			// + property.getValue());
			// } else if (property.getXmlBlob() != null) {
			// System.out.println("  " + property.getName()
			// + "(xmlBlob)= " + property.getXmlBlob().getBlob());
			// }
			// }
			//
			// String photoLink = entry.getContactPhotoLink().getHref();
			// System.out.println("Photo Link: " + photoLink);
			//
			// if (photoLink.getEtag() != null) {
			// System.out.println("Contact Photo's ETag: "
			// + photoLink.getEtag());
			// }
			//
			// System.out.println("Contact's ETag: " + entry.getEtag());
		}
	}

	public static void createContact() throws ServiceException, IOException {
		ContactsService myService = new ContactsService("TEST");
		try {
			myService.setUserCredentials("code.pandas@gmail.com", "qwerfdsa");
		} catch (AuthenticationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		ArrayList<ContactOperations> contactList = new ArrayList<ContactOperations>();
		DataPool.getInstance().getDb().getContactData(
				DataPool.getInstance().getCurrentUser().getUserName(),
				contactList);

		for (ContactOperations ite : contactList) {
			// Create the entry to insert
			ContactEntry contact = new ContactEntry();
			contact.setTitle(new PlainTextConstruct(ite.getContactName()));
			// contact.setContent(new PlainTextConstruct(notes));

			Email primaryMail = new Email();
			primaryMail.setAddress(ite.getEmailAddresseList().get(0));
			// primaryMail.setRel("http://schemas.google.com/g/2005#home");
			// primaryMail.setPrimary(true);
			contact.addEmailAddress(primaryMail);

			// Email secondaryMail = new Email();
			// secondaryMail.setAddress("liz@example.com");
			// secondaryMail.setRel("http://schemas.google.com/g/2005#work");
			// secondaryMail.setPrimary(false);
			// contact.addEmailAddress(secondaryMail);
			//
			// ExtendedProperty favouriteFlower = new ExtendedProperty();
			// favouriteFlower.setName("favourite flower");
			// favouriteFlower.setValue("daisy");
			// contact.addExtendedProperty(favouriteFlower);

			// ExtendedProperty sportsProperty = new ExtendedProperty();
			// sportsProperty.setName("sports");
			// XmlBlob sportKinds = new XmlBlob();
			// sportKinds.setBlob(new
			// String("<dance><salsa/><ballroom dancing/><dance/>"));
			// // spsportsPropertyorts.setXmlBlob(sportKinds);
			// contact.addExtendedProperty(sportsProperty);

			// Ask the service to insert the new entry
			URL postUrl = new URL(
					"http://www.google.com/m8/feeds/contacts/code.pandas@gmail.com/full");
			myService.insert(postUrl, contact);
		}
	}
}
