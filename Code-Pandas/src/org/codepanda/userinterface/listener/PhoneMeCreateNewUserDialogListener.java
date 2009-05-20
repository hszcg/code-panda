package org.codepanda.userinterface.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.codepanda.userinterface.PhoneMeCreateNewUserDialog;

public class PhoneMeCreateNewUserDialogListener implements ActionListener {
	PhoneMeCreateNewUserDialog myPhoneMeCreateNewUserDialog;

	public PhoneMeCreateNewUserDialogListener(
			PhoneMeCreateNewUserDialog phoneMeCreateNewUserDialog) {
		// TODO Auto-generated constructor stub
		this.myPhoneMeCreateNewUserDialog = phoneMeCreateNewUserDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Return")) {
			System.out.println("PhoneMeCreateNewUserDialog Action Return.");
			
			this.myPhoneMeCreateNewUserDialog.setVisible(false);
			this.myPhoneMeCreateNewUserDialog.getMyPhoneMeLoginDialog().setVisible(true);
			
		} else if (e.getActionCommand().equals("OK")) {
			System.out.println("PhoneMeCreateNewUserDialog Action Comfirmed.");
			this.myPhoneMeCreateNewUserDialog.setVisible(false);
			this.myPhoneMeCreateNewUserDialog.getMyPhoneMeLoginDialog().setVisible(true);
			
		} else if (e.getActionCommand().equals("Cancel")) {
			System.out.println("PhoneMeCreateNewUserDialog Action Canceled.");
			System.exit(0);
		};
	}

}
