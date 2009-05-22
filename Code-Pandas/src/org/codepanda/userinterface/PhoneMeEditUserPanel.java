package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.user.User;
import org.jdesktop.swingx.JXPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;

public class PhoneMeEditUserPanel extends JXPanel {
	private PhoneMeFrame localParentFrame;
	private User localUser;
	private JTextField userNameField;
	private JPasswordField userNewPasswordField;
	private JPasswordField userNewComfirmPasswordField;
	private ContactInfoPanel mainPanel;
	private JButton confirm;
	private JButton cancel;
	
	public PhoneMeEditUserPanel(PhoneMeFrame parentFrame){
		localParentFrame = parentFrame;
		
		setUserData(parentFrame);
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setUserData(localParentFrame);
			}
		});
	}
	
	public void setUserData(PhoneMeFrame parentFrame){
		FormLayout upperlayout = new FormLayout(
				"pref, 50dlu, pref", // columns
				"p, 8dlu, p, 5dlu, p"); // rows
			
			PanelBuilder builder = new PanelBuilder(upperlayout);
			builder.setDefaultDialogBorder();

			CellConstraints cc = new CellConstraints();
			
			builder.addLabel("当前用户", cc.xy(1, 1));
			userNameField = new JTextField();
			builder.add(userNameField, cc.xy(1, 3));
			//userNameField.setText
			//(DataPool.getInstance().get
			userNameField.setEditable(false);
			
			builder.addLabel("如果需要修改密码，请在此填写", cc.xy(3, 1));
			userNewPasswordField = new JPasswordField();
			builder.add(userNewPasswordField, cc.xy(3, 3));
			
			userNewComfirmPasswordField = new JPasswordField();
			builder.add(userNewComfirmPasswordField, cc.xy(3, 5));
			
			setLayout(new BorderLayout());
			add(builder.getPanel(), "North");
			
			//mainPanel = new ContactInfoPanel(parentFrame, currentUser,
			//		true, ContactInfoPanel.USER_INFO_PANEL);
			add(mainPanel, "Center");
			
			FormLayout downlayout = new FormLayout(
					"pref, 50dlu, pref", // columns
					"p"); // rows
			
			PanelBuilder downbuilder = new PanelBuilder(upperlayout);
			downbuilder.setDefaultDialogBorder();

			CellConstraints downcc = new CellConstraints();
			
			confirm = new JButton("确认信息修改");
			downbuilder.add(confirm, downcc.xy(1, 1));
			
			cancel = new JButton("取消信息修改");
			downbuilder.add(cancel, downcc.xy(3, 1));
			
	}
	
	public StringBuffer makeEditUserMessageXML(){
		StringBuffer message = new StringBuffer();
		message.append(MyXMLMaker.
			addTag("UserName", userNameField.getText()));
		if(String.valueOf(this.userNewPasswordField
				.getPassword()).length() != 0)
			message.append(MyXMLMaker.
					addTag("UserPassword", String.valueOf
							(this.userNewPasswordField
							.getPassword())));
		message.append(mainPanel.makeMainMessageXML());
		return message;
	}
	
}
