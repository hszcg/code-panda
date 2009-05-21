package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.codepanda.userinterface.listener.PhoneMeCreateNewUserDialogListener;
import org.codepanda.utility.user.User;

public class PhoneMeCreateNewUserDialog {
	private PhoneMeFrame parentFrame;
	private PhoneMeLoginDialog myPhoneMeLoginDialog;
	private JDialog newUserDialog;
	private PhoneMeCreateNewUserDialogListener myPhoneMeCreateNewUserDialogListener;
	private JTextField userNameField;
	private JPasswordField userPasswordField;
	private JPasswordField userComfirmPasswordField;
	private JLabel errorMessageLabel;
	private User myUser;

	public PhoneMeCreateNewUserDialog(PhoneMeFrame parentFrame,
			PhoneMeLoginDialog myPhoneMeLoginDialog) {
		// TODO Auto-generated constructor stub
		this.parentFrame = parentFrame;
		this.myPhoneMeLoginDialog = myPhoneMeLoginDialog;
		this.setMyUser(new User());

		this.newUserDialog = new JDialog(parentFrame, "Create New User Dialog",
				true);
		newUserDialog.setLayout(new BorderLayout());
		this.newUserDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("PhoneMeCreateNewUserDialog Closed.");
				System.exit(0);
			}
		});
		// **************************************************
		// 用户名
		JPanel userNamePanel = new JPanel();
		userNamePanel.setLayout(new GridLayout());
		JLabel userNameLabel = new JLabel("User Name");
		this.userNameField = new JTextField();

		userNamePanel.add(userNameLabel);
		userNamePanel.add(userNameField);
		// **************************************************

		// 用户密码
		JPanel userPasswordPanel = new JPanel();
		userPasswordPanel.setLayout(new GridLayout());
		JLabel userPasswordLabel = new JLabel("Password");
		this.userPasswordField = new JPasswordField();

		userPasswordPanel.add(userPasswordLabel);
		userPasswordPanel.add(userPasswordField);
		// **************************************************

		// 用户密码确认
		JPanel userComfirmPasswordPanel = new JPanel();
		userComfirmPasswordPanel.setLayout(new GridLayout());
		JLabel userComfirmPasswordLabel = new JLabel("Comfirm Password");
		this.userComfirmPasswordField = new JPasswordField();

		userComfirmPasswordPanel.add(userComfirmPasswordLabel);
		userComfirmPasswordPanel.add(userComfirmPasswordField);
		// **************************************************

		// 信息提示
		this.errorMessageLabel = new JLabel(
				"Welcome To PhoneMe, Please Login to Procceed.");
		this.errorMessageLabel.setForeground(Color.BLUE);
		// **************************************************

		// 顶部界面设置
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		top.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

		top.add(Box.createGlue());
		top.add(userNamePanel);
		top.add(Box.createGlue());
		top.add(userPasswordPanel);
		top.add(Box.createGlue());
		top.add(userComfirmPasswordPanel);
		top.add(Box.createGlue());
		top.add(errorMessageLabel);
		top.add(Box.createGlue());

		this.newUserDialog.add(top, BorderLayout.NORTH);

		// **************************************************

		JPanel major = new ContactInfoPanel(this.parentFrame);
		this.newUserDialog.add(major, BorderLayout.CENTER);

		// **************************************************
		myPhoneMeCreateNewUserDialogListener = new PhoneMeCreateNewUserDialogListener(
				this);

		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));

		JButton newUserButton = new JButton("Return To Login");
		newUserButton.setActionCommand("Return");
		newUserButton.setMnemonic('r');
		newUserButton.addActionListener(myPhoneMeCreateNewUserDialogListener);

		JButton loginUserButton = new JButton("OK");
		loginUserButton.setActionCommand("OK");
		loginUserButton.setMnemonic('o');
		loginUserButton.addActionListener(myPhoneMeCreateNewUserDialogListener);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setMnemonic('c');
		cancelButton.addActionListener(myPhoneMeCreateNewUserDialogListener);

		bottom.add(Box.createGlue());
		bottom.add(newUserButton);
		bottom.add(Box.createGlue());
		bottom.add(loginUserButton);
		bottom.add(Box.createGlue());
		bottom.add(cancelButton);
		bottom.add(Box.createGlue());

		this.newUserDialog.add(bottom, BorderLayout.SOUTH);

		// **************************************************
		this.newUserDialog.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.newUserDialog.setLocation((screenSize.width - newUserDialog
				.getWidth()) / 2, (screenSize.height - newUserDialog
				.getHeight()) / 2);
		this.newUserDialog.setResizable(false);
		this.newUserDialog.setVisible(false);
		// **************************************************
	}

	/**
	 * @param isVisible
	 */
	public void setVisible(boolean isVisible) {
		this.newUserDialog.setVisible(isVisible);
	}

	/**
	 * @return the parentFrame
	 */
	public PhoneMeFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @return the myPhoneMeLoginDialog
	 */
	public PhoneMeLoginDialog getMyPhoneMeLoginDialog() {
		return myPhoneMeLoginDialog;
	}

	/**
	 * @param myUser
	 *            the myUser to set
	 */
	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}

	/**
	 * @param errorMessage
	 */
	public void updateErrorMessageLabel(String errorMessage) {
		this.errorMessageLabel.setText(errorMessage);
		this.errorMessageLabel.setForeground(Color.RED);
	}

	/**
	 * @return the myUser
	 */
	public User getMyUser() {
		myUser.setUserName(this.userNameField.getText().trim());
		String passwordInput = String.valueOf(this.userPasswordField
				.getPassword());
		String passwordComfirm = String.valueOf(this.userComfirmPasswordField
				.getPassword());

		if (passwordInput.equals(passwordComfirm))
			myUser.setPassword(passwordInput);
		else
			myUser.setPassword(null);

		return myUser;
	}
}
