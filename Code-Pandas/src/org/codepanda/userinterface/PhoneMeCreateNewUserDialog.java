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
import org.codepanda.userinterface.xml.MyXMLMaker;

public class PhoneMeCreateNewUserDialog {
	private PhoneMeFrame parentFrame;
	// 如果myPhoneMeLoginDialog不是null，代表是Login操作之前
	// 如果myPhoneMeLoginDialog是null，代表是Login操作之后
	private PhoneMeLoginDialog myPhoneMeLoginDialog;
	private JDialog newUserDialog;
	private ContactInfoPanel userContactInfoPanel;
	private PhoneMeCreateNewUserDialogListener myPhoneMeCreateNewUserDialogListener;
	private JTextField userNameField;
	private JPasswordField userPasswordField;
	private JPasswordField userComfirmPasswordField;
	private JButton backButton;
	private JButton createNewUserButton;
	private JButton cancelButton;
	private JLabel errorMessageLabel;

	public PhoneMeCreateNewUserDialog(PhoneMeFrame parentFrame,
			PhoneMeLoginDialog myPhoneMeLoginDialog) {
		this.parentFrame = parentFrame;
		this.myPhoneMeLoginDialog = myPhoneMeLoginDialog;

		this.newUserDialog = new JDialog(parentFrame, "Create New User Dialog",
				true);
		newUserDialog.setLayout(new BorderLayout());
		
		if (this.myPhoneMeLoginDialog != null) {
			this.newUserDialog.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.out.println("PhoneMeCreateNewUserDialog Closed.");
					System.exit(0);
				}
			});
		}
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

		// TODO 如何让头像显示正常
		userContactInfoPanel = new ContactInfoPanel(this.newUserDialog, null,
				true, ContactInfoPanel.USER_INFO_PANEL);
		this.newUserDialog.add(userContactInfoPanel, BorderLayout.CENTER);

		// **************************************************
		myPhoneMeCreateNewUserDialogListener = new PhoneMeCreateNewUserDialogListener(
				this);

		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		
		if (this.myPhoneMeLoginDialog != null) {
			backButton = new JButton("Return To Login");
			backButton.setActionCommand("Return");
			backButton.setMnemonic('r');
			backButton.addActionListener(myPhoneMeCreateNewUserDialogListener);

			bottom.add(Box.createGlue());
			bottom.add(backButton);
		}
		
		createNewUserButton = new JButton("OK");
		createNewUserButton.setActionCommand("OK");
		createNewUserButton.setMnemonic('o');
		createNewUserButton
				.addActionListener(myPhoneMeCreateNewUserDialogListener);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setMnemonic('c');
		cancelButton
				.addActionListener(myPhoneMeCreateNewUserDialogListener);
		
		bottom.add(Box.createGlue());
		bottom.add(createNewUserButton);
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
	 * @return
	 */
	public StringBuffer makeUserOnlyMessageXML() {
		StringBuffer message = new StringBuffer();
		message.append(MyXMLMaker.addTag("UserName", userNameField.getText()));
		message.append(MyXMLMaker.addTag("UserPassword", String
				.valueOf(this.userPasswordField.getPassword())));
		return message;
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
	 * @param errorMessage
	 */
	public void updateErrorMessageLabel(String errorMessage) {
		this.errorMessageLabel.setText(errorMessage);
		this.errorMessageLabel.setForeground(Color.RED);
	}

	/**
	 * @return
	 */
	public String getNewUserName() {
		return this.userNameField.getText().trim();
	}

	/**
	 * @return
	 */
	public String getPassword() {
		String passwordInput = String.valueOf(this.userPasswordField
				.getPassword());
		String passwordComfirm = String.valueOf(this.userComfirmPasswordField
				.getPassword());

		if (passwordInput.equals(passwordComfirm))
			return passwordInput;
		else
			return null;
	}

	/**
	 * @return
	 */
	public JDialog getNewUserDialog() {
		return newUserDialog;
	}

	/**
	 * @return the userContactInfoPanel
	 */
	public ContactInfoPanel getUserContactInfoPanel() {
		return userContactInfoPanel;
	}
}
