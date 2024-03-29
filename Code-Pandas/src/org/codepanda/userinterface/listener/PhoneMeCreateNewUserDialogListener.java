package org.codepanda.userinterface.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.PhoneMeCreateNewUserDialog;
import org.codepanda.userinterface.messagehandler.NewUserMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;

/**
 * @author hszcg
 * 
 */
public class PhoneMeCreateNewUserDialogListener implements ActionListener {
	PhoneMeCreateNewUserDialog myPhoneMeCreateNewUserDialog;

	/**
	 * @param phoneMeCreateNewUserDialog
	 */
	public PhoneMeCreateNewUserDialogListener(
			PhoneMeCreateNewUserDialog phoneMeCreateNewUserDialog) {
		this.myPhoneMeCreateNewUserDialog = phoneMeCreateNewUserDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Return")) {
			System.out.println("PhoneMeCreateNewUserDialog Action Return.");

			this.myPhoneMeCreateNewUserDialog.setVisible(false);
			this.myPhoneMeCreateNewUserDialog.getMyPhoneMeLoginDialog()
					.setVisible(true);

		} else if (e.getActionCommand().equals("OK")) {
			System.out.println("PhoneMeCreateNewUserDialog Action Comfirmed.");

			if (myPhoneMeCreateNewUserDialog.getNewUserName().length() == 0) {
				this.myPhoneMeCreateNewUserDialog
						.updateErrorMessageLabel("User Name is Empty!");
				return;
			}

			if (myPhoneMeCreateNewUserDialog.getPassword() == null) {
				this.myPhoneMeCreateNewUserDialog
						.updateErrorMessageLabel("Password is not Comfirmed Right!");
				return;
			}

			if (myPhoneMeCreateNewUserDialog.getPassword().length() == 0) {
				this.myPhoneMeCreateNewUserDialog
						.updateErrorMessageLabel("Password is Empty!");
				return;
			}

			// TODO New User
			// makeUserOnlyMessageXML
			String headxml = myPhoneMeCreateNewUserDialog
					.makeUserOnlyMessageXML().toString();
			String backxml = myPhoneMeCreateNewUserDialog
					.getUserContactInfoPanel().makeMainMessageXML().toString();
			String xml = MyXMLMaker.addTag("NewUser", headxml + backxml);
			xml = MyXMLMaker.addTag("com", xml);

			System.out.println("NEW_USER\n" + xml);

			CommandVisitor newUserCommandVisitor = new CommandVisitor(
					CommandType.NEW_USER, xml);
			NewUserMessageHandler newUserMessageHandler = new NewUserMessageHandler();
			int result = (Integer) newUserMessageHandler
					.executeCommand(newUserCommandVisitor);

			if (result == 0) {
				// �ɹ�����

				this.myPhoneMeCreateNewUserDialog.setVisible(false);
				
				if (this.myPhoneMeCreateNewUserDialog.getMyPhoneMeLoginDialog() != null) {
					this.myPhoneMeCreateNewUserDialog.getMyPhoneMeLoginDialog()
							.setVisible(true);
				}
			} else {
				// ʧ�ܳ���
				this.myPhoneMeCreateNewUserDialog
						.updateErrorMessageLabel("Fail to Create New User!");
			}

		} else if (e.getActionCommand().equals("Cancel")) {
			if (this.myPhoneMeCreateNewUserDialog.getMyPhoneMeLoginDialog() != null) {
				System.out.println("PhoneMeCreateNewUserDialog Action Canceled.");
				myPhoneMeCreateNewUserDialog.getParentFrame().exitProgram();
			} else {
				this.myPhoneMeCreateNewUserDialog.setVisible(false);
			}
		}
	}
}
