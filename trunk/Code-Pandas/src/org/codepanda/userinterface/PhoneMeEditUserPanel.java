package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.EditUserMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.data.DataPool;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;

public class PhoneMeEditUserPanel implements ActionListener {
	private JDialog editUserDialog;
	private JPasswordField userNewPasswordField;
	private JPasswordField userNewComfirmPasswordField;
	private JLabel errorMessageLabel;
	private ContactInfoPanel mainPanel;
	private JButton confirm;
	private JButton cancel;

	public PhoneMeEditUserPanel(PhoneMeFrame parentFrame) {
		super();

		editUserDialog = new JDialog(parentFrame, "Current User: "
				+ (DataPool.getInstance().getCurrentUser().getUserName()), true);
		FormLayout upperlayout = new FormLayout(
				"86dlu, pref, 50dlu, pref, 100dlu, pref", // columns
				"p, 8dlu, p, 5dlu, p"); // rows

		PanelBuilder builder = new PanelBuilder(upperlayout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

		builder.addLabel("新密码", cc.xy(2, 3));
		builder.addLabel("确认新密码", cc.xy(2, 5));

		builder.addLabel("如果需要修改密码，请在此填写", cc.xy(4, 1));
		userNewPasswordField = new JPasswordField();
		builder.add(userNewPasswordField, cc.xy(4, 3));

		userNewComfirmPasswordField = new JPasswordField();
		builder.add(userNewComfirmPasswordField, cc.xy(4, 5));

		errorMessageLabel = new JLabel();
		builder.add(errorMessageLabel, cc.xy(6, 5));

		editUserDialog.setLayout(new BorderLayout());
		editUserDialog.add(builder.getPanel(), "North");

		mainPanel = new ContactInfoPanel(parentFrame, DataPool.getInstance()
				.getCurrentUser(), true, ContactInfoPanel.USER_INFO_PANEL);
		editUserDialog.add(mainPanel, "Center");

		FormLayout downlayout = new FormLayout("170dlu, pref, 50dlu, pref", // columns
				"p"); // rows

		PanelBuilder downbuilder = new PanelBuilder(downlayout);
		downbuilder.setDefaultDialogBorder();

		CellConstraints downcc = new CellConstraints();

		confirm = new JButton("确认信息修改");
		downbuilder.add(confirm, downcc.xy(2, 1));

		cancel = new JButton("取消信息修改");
		downbuilder.add(cancel, downcc.xy(4, 1));

		editUserDialog.add(downbuilder.getPanel(), "South");

		confirm.addActionListener(this);
		cancel.addActionListener(this);
	}

	public JDialog getDialog() {
		return editUserDialog;
	}

	public void updateErrorMessageLabel(String errorMessage) {
		this.errorMessageLabel.setText(errorMessage);
		this.errorMessageLabel.setForeground(Color.RED);
	}

	public String getPassword() {
		String passwordInput = String.valueOf(this.userNewPasswordField
				.getPassword());
		String passwordComfirm = String
				.valueOf(this.userNewComfirmPasswordField.getPassword());

		if (passwordInput.equals(passwordComfirm))
			return passwordInput;
		else
			return null;
	}

	public StringBuffer makeEditUserMessageXML() {
		StringBuffer message = new StringBuffer();
		if (String.valueOf(this.userNewPasswordField.getPassword()).length() != 0)
			message.append(MyXMLMaker.addTag("UserPassword", String
					.valueOf(this.userNewPasswordField.getPassword())));
		message.append(mainPanel.makeMainMessageXML());
		return message;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			editUserDialog.dispose();
		}

		if (e.getSource() == confirm) {
			if (getPassword() == null) {
				updateErrorMessageLabel("新密码输入不正确");
			}
		}

		// TODO make commandActor
		String xml = makeEditUserMessageXML().toString();

		xml = MyXMLMaker.addTag("EditUser", xml);
		xml = MyXMLMaker.addTag("com", xml);

		System.out.println("EDIT_USER\n" + xml);

		CommandVisitor editUserCommandVisitor = new CommandVisitor(
				CommandType.EDIT_USER, xml);
		EditUserMessageHandler editUserMessageHandler = new EditUserMessageHandler();
		editUserMessageHandler.executeCommand(editUserCommandVisitor);
	}

}
