package org.codepanda.userinterface.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.ContactInfoPanel;
import org.codepanda.userinterface.messagehandler.EditContactMessageHandler;
import org.codepanda.userinterface.messagehandler.NewContactMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.data.DataPool;

public class ConfirmActionListener implements ActionListener {
	private ContactInfoPanel myContactInfoPanel;

	public ConfirmActionListener(ContactInfoPanel contactInfoPanel) {
		// TODO Auto-generated constructor stub
		myContactInfoPanel = contactInfoPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// makeMainMessageXML
		String name = myContactInfoPanel.getNameField().getText().trim();
		if (name.length() == 0)
			return;
		if (myContactInfoPanel.getMyContact() == null) {
			// 新建联系人
			String xml = myContactInfoPanel.makeMainMessageXML().toString();
			xml = MyXMLMaker.addTag("NewContact", xml);
			xml = MyXMLMaker.addTag("com", xml);

			System.out.println("NEW_CONTACT\n" + xml);

			CommandVisitor newContactCommandVisitor = new CommandVisitor(
					CommandType.NEW_CONTACT, xml);
			NewContactMessageHandler newContactMessageHandler = new NewContactMessageHandler();
			int iSN = (Integer) newContactMessageHandler
					.executeCommand(newContactCommandVisitor);

			myContactInfoPanel.setMyContact(DataPool.getInstance()
					.getAllContactISNMap().get(iSN));

			myContactInfoPanel.setEditable(false);
			myContactInfoPanel.getNameField().setEditable(false);
			myContactInfoPanel.setTextFieldInVisible();
			myContactInfoPanel.setContactBirthdayPickerInVisible();

			myContactInfoPanel.getParentFrame().updateTaskPane(iSN);
			myContactInfoPanel.getParentFrame().getMyPhoneMeMajorPanel()
					.setTitle(name, myContactInfoPanel);
		} else {
			// 修改联系人
			String xml = myContactInfoPanel.makeMainMessageXML().toString();
			xml = MyXMLMaker.addTag("EditContact", xml);
			xml = MyXMLMaker.addTag("com", xml);

			System.out.println("EDIT_CONTACT\n" + xml);

			CommandVisitor editContactCommandVisitor = new CommandVisitor(
					CommandType.EDIT_CONTACT, xml);
			EditContactMessageHandler editContactMessageHandler = new EditContactMessageHandler();
			editContactMessageHandler.executeCommand(editContactCommandVisitor);

			myContactInfoPanel.setEditable(false);
			myContactInfoPanel.getNameField().setEditable(false);
			myContactInfoPanel.setTextFieldInVisible();
			myContactInfoPanel.setContactBirthdayPickerInVisible();

			myContactInfoPanel.getParentFrame().updateTaskPane(
					myContactInfoPanel.getMyContact().getISN());
			myContactInfoPanel.getParentFrame().getMyPhoneMeMajorPanel()
					.setTitle(name, myContactInfoPanel);

		}
	}

}
