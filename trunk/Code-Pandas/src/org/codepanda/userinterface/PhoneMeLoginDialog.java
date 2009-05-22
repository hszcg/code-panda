package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.codepanda.userinterface.listener.PhoneMeLoginDialogListener;

public class PhoneMeLoginDialog {
	private PhoneMeFrame parentFrame;
	private JDialog myLoginDialog;
	private PhoneMeLoginDialogListener myPhoneMeLoginDialogListener;
	private JTextField userNameField;
	private JPasswordField userPasswordField;
	private JLabel errorMessageLabel;

	public PhoneMeLoginDialog(PhoneMeFrame parent) {
		this.parentFrame = parent;
		this.myLoginDialog = new JDialog(parent, "Welcome To PhoneMe!", true);
		this.myLoginDialog.setLayout(new BorderLayout());
		this.myLoginDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("PhoneMeLoginDialog Closed.");
				parentFrame.exitProgram();
			}
		});
		
		this.myPhoneMeLoginDialogListener = new PhoneMeLoginDialogListener(this);
		// **************************************************

		// 顶部Logo
		try {
			JLabel topLogo = new JLabel(new ImageIcon(ImageIO.read(this
					.getClass().getResource("/icon/Logo.png"))));
			this.myLoginDialog.add(topLogo, BorderLayout.NORTH);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		this.userPasswordField.setActionCommand("Login User");
		this.userPasswordField.addActionListener(myPhoneMeLoginDialogListener);

		userPasswordPanel.add(userPasswordLabel);
		userPasswordPanel.add(userPasswordField);
		// **************************************************

		// 信息提示
		this.errorMessageLabel = new JLabel(
				"Welcome To PhoneMe, Please Login to Procceed.");
		this.errorMessageLabel.setForeground(Color.BLUE);

		// **************************************************

		// 主界面设置
		JPanel major = new JPanel();
		major.setLayout(new BoxLayout(major, BoxLayout.Y_AXIS));
		major.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

		major.add(Box.createGlue());
		major.add(userNamePanel);
		major.add(Box.createGlue());
		major.add(userPasswordPanel);
		major.add(Box.createGlue());
		major.add(errorMessageLabel);
		major.add(Box.createGlue());

		this.myLoginDialog.add(major, BorderLayout.CENTER);
		// **************************************************

		// 底部按钮设置
		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));

		JButton newUserButton = new JButton("New User");
		newUserButton.setActionCommand("New User");
		newUserButton.setMnemonic('n');
		newUserButton.addActionListener(myPhoneMeLoginDialogListener);

		JButton loginUserButton = new JButton("Login");
		loginUserButton.setActionCommand("Login User");
		loginUserButton.setMnemonic('l');
		loginUserButton.addActionListener(myPhoneMeLoginDialogListener);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel Option");
		cancelButton.setMnemonic('c');
		cancelButton.addActionListener(myPhoneMeLoginDialogListener);

		bottom.add(Box.createGlue());
		bottom.add(newUserButton);
		bottom.add(Box.createGlue());
		bottom.add(loginUserButton);
		bottom.add(Box.createGlue());
		bottom.add(cancelButton);
		bottom.add(Box.createGlue());

		this.myLoginDialog.add(bottom, BorderLayout.SOUTH);
		// **************************************************

		// 对话框位置和大小设置
		this.myLoginDialog.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.myLoginDialog.setLocation((screenSize.width - myLoginDialog
				.getWidth()) / 2, (screenSize.height - myLoginDialog
				.getHeight()) / 2);
		this.myLoginDialog.setResizable(false);
		this.myLoginDialog.setVisible(false);
		// **************************************************
	}

	/**
	 * @param isVisible
	 */
	public void setVisible(boolean isVisible) {
		this.myLoginDialog.setVisible(isVisible);
	}

	/**
	 * @return String UserNameInput
	 */
	public String getUserNameInput() {
		return userNameField.getText().trim();
	}

	/**
	 * @return String UserPasswordInput
	 */
	public char[] getUserPasswordInput() {
		return userPasswordField.getPassword();
	}

	/**
	 * @param errorMessage
	 */
	public void updateErrorMessageLabel(String errorMessage) {
		this.errorMessageLabel.setText(errorMessage);
		this.errorMessageLabel.setForeground(Color.RED);
	}

	/**
	 * @return the parentFrame
	 */
	public PhoneMeFrame getParentFrame() {
		return this.parentFrame;
	}

}
