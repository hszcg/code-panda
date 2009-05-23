package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.DeleteContactMessageHandler;
import org.codepanda.userinterface.messagehandler.DeleteUserMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.data.DataPool;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class PhoneMeCorfirmDialog extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2524826142754032472L;
	private PhoneMeFrame parentFrame;
	private JTextField userNameField;
	private JPasswordField userPasswordField;

	private JButton confirm;
	private JButton cancel;

	private JLabel errorMessageLabel;

	public PhoneMeCorfirmDialog(PhoneMeFrame frame) {
		super(frame, "Confirm", true);
		this.parentFrame = frame;
		FormLayout layout = new FormLayout
		("1dlu, pref, 5dlu, pref, 1dlu", // columns
				"p, 10dlu, p, 10dlu, p, 10dlu, p"); // rows

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

		//builder.addLabel("需要进行密码确认", cc.xy(1, 1));

		builder.addLabel("用户名", cc.xy(2, 3));
		userNameField = new JTextField(20);
		builder.add(userNameField, cc.xy(4, 3));

		builder.addLabel("密码", cc.xy(2, 5));
		userPasswordField = new JPasswordField(20);
		builder.add(userPasswordField, cc.xy(4, 5));

		errorMessageLabel = new JLabel();
		builder.add(errorMessageLabel, cc.xy(2, 7));

		setLayout(new BorderLayout());
		add(builder.getPanel(), "Center");

		FormLayout downlayout = new FormLayout("1dlu, pref, 10dlu, pref", // columns
				"p"); // rows

		PanelBuilder downbuilder = new PanelBuilder(downlayout);
		downbuilder.setDefaultDialogBorder();

		CellConstraints downcc = new CellConstraints();

		confirm = new JButton("确认删除");
		downbuilder.add(confirm, downcc.xy(2, 1));
		confirm.addActionListener(this);

		cancel = new JButton("取消删除");
		downbuilder.add(cancel, downcc.xy(4, 1));
		cancel.addActionListener(this);

		add(downbuilder.getPanel(), "South");
		
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, 
			(screenSize.height - getHeight()) / 2);
		setResizable(false);
		setVisible(true);
		
		System.out.println("rrrrr");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel)
			dispose();

		if (e.getSource() == confirm) {
			String userName = userNameField.getText();
			String userPassword = String.valueOf(this.userPasswordField
					.getPassword());
	
			// check password is right or wrong
			if( !userName.equals(DataPool.getInstance().getCurrentUser().getUserName()) ){
				updateErrorMessageLabel("User Name not Exist!");
				return;
			}
			
			if(userPassword.equals(DataPool.getInstance().getCurrentUser().getPassword())) {
				updateErrorMessageLabel("User Password Error!");
				return;
			}

			// make delete user command actor and act
			String xml = MyXMLMaker.addTag("DeleteUser", "");
			xml = MyXMLMaker.addTag("com", xml);

			System.out.println("DELETE_USER\n" + xml);

			CommandVisitor deleteUserCommandVisitor = new CommandVisitor(
					CommandType.DELETE_USER, xml);
			DeleteUserMessageHandler deleteUserMessageHandler = new DeleteUserMessageHandler();
			deleteUserMessageHandler
					.executeCommand(deleteUserCommandVisitor);
			
			parentFrame.exitProgram();
		}
	}
	
	/**
	 * @param errorMessage
	 */
	public void updateErrorMessageLabel(String errorMessage) {
		this.errorMessageLabel.setText(errorMessage);
		this.errorMessageLabel.setForeground(Color.RED);
	}
}
