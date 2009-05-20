package org.codepanda.userinterface.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import org.codepanda.userinterface.ContactInfoPanel;
import org.codepanda.userinterface.PhoneMeCreateNewUserDialog;
import org.codepanda.userinterface.PhoneMeLoginDialog;

/**
 * @author hszcg
 * 
 */
public class PhoneMeLoginDialogListener implements ActionListener {
	PhoneMeLoginDialog myPhoneMeLoginDialog;

	/**
	 * @param phoneMeLoginDialog
	 */
	public PhoneMeLoginDialogListener(PhoneMeLoginDialog phoneMeLoginDialog) {
		// TODO Auto-generated constructor stub
		this.myPhoneMeLoginDialog = phoneMeLoginDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("New User")) {
			System.out.println("PhoneMeLoginDialog New User Action Comfirmed.");
			
			myPhoneMeLoginDialog.setVisible(false);
			
			PhoneMeCreateNewUserDialog myCreateNewUserDialog = new PhoneMeCreateNewUserDialog(myPhoneMeLoginDialog
					.getParentFrame(), myPhoneMeLoginDialog);
			
			myCreateNewUserDialog.setVisible(true);
			
		} else if (e.getActionCommand().equals("Login User")) {
			System.out.println("PhoneMeLoginDialog Login Action Comfirmed.");

			LoginResultType loginResult = loginUser(myPhoneMeLoginDialog
					.getUserNameInput(), myPhoneMeLoginDialog
					.getUserPasswordInput());
			if (loginResult == LoginResultType.SUCCEED) {
				myPhoneMeLoginDialog.setVisible(false);
			} else {
				if (loginResult == LoginResultType.INVAILD_PASSWORD)
					myPhoneMeLoginDialog
							.updateErrorMessageLabel("Login Failed: INVAILD_PASSWORD");

				if (loginResult == LoginResultType.USERNAME_NOT_EXIST)
					myPhoneMeLoginDialog
							.updateErrorMessageLabel("Login Failed: USERNAME_NOT_EXIST");

			}

		} else if (e.getActionCommand().equals("Cancel Option")) {
			System.out.println("PhoneMeLoginDialog Login Action Canceled.");
			System.exit(0);
		}

	}

	/**
	 * @param String
	 *            userNameInput
	 * @param char[] userPasswordInput
	 * @return LoginResultType
	 */
	private LoginResultType loginUser(String userNameInput,
			char[] userPasswordInput) {
		// TODO Auto-generated method stub
		String password = String.valueOf(userPasswordInput);
		System.out.println("UserName:\t" + userNameInput);
		System.out.println("Password:\t" + password);

		if (userNameInput.equals("Sa")) {
			if (password.equals("Sa"))
				return LoginResultType.SUCCEED;
			else
				return LoginResultType.INVAILD_PASSWORD;
		} else {
			return LoginResultType.USERNAME_NOT_EXIST;
		}

	}

}

/**
 * @author hszcg
 * 
 */
enum LoginResultType {
	SUCCEED, USERNAME_NOT_EXIST, INVAILD_PASSWORD;
}