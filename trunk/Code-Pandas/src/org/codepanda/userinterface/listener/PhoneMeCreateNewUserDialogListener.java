package org.codepanda.userinterface.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.codepanda.userinterface.PhoneMeCreateNewUserDialog;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.user.User;

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

			User myUser = myPhoneMeCreateNewUserDialog.getMyUser();
			if (myUser.getUserName().length() == 0) {
				this.myPhoneMeCreateNewUserDialog
						.updateErrorMessageLabel("User Name is Empty!");
				return;
			}

			if (myUser.getPassword() == null) {
				this.myPhoneMeCreateNewUserDialog
						.updateErrorMessageLabel("Password is not Comfirmed Right!");
				return;
			}

			if (myUser.getPassword().length() == 0) {
				this.myPhoneMeCreateNewUserDialog
						.updateErrorMessageLabel("Password is Empty!");
				return;
			}

			String xml = MyXMLMaker.getNewUserXML(myUser);
			System.out.println(xml);
			
			// TODO New User

			this.myPhoneMeCreateNewUserDialog.setVisible(false);
			this.myPhoneMeCreateNewUserDialog.getMyPhoneMeLoginDialog()
					.setVisible(true);

		} else if (e.getActionCommand().equals("Cancel")) {
			System.out.println("PhoneMeCreateNewUserDialog Action Canceled.");
			System.exit(0);
		}
		;
	}
}
