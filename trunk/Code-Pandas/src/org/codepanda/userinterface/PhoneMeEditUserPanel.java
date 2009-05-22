package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.user.User;
import org.jdesktop.swingx.JXPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;

public class PhoneMeEditUserPanel{
	private PhoneMeFrame localParentFrame;
	private JDialog editUserDialog;
	private User localUser;
	private JTextField userNameField;
	private JPasswordField userNewPasswordField;
	private JPasswordField userNewComfirmPasswordField;
	private ContactInfoPanel mainPanel;
	private JButton confirm;
	private JButton cancel;
	
	public PhoneMeEditUserPanel(PhoneMeFrame parentFrame){
		super();
		localParentFrame = parentFrame;
		
		editUserDialog = new JDialog(parentFrame, "当前用户" +
				(DataPool.getInstance().getCurrentUser().getUserName()) , true);
		FormLayout upperlayout = new FormLayout(
				"86dlu, pref, 50dlu, pref", // columns
				"p, 8dlu, p, 5dlu, p"); // rows
			
			PanelBuilder builder = new PanelBuilder(upperlayout);
			builder.setDefaultDialogBorder();

			CellConstraints cc = new CellConstraints();
			
			//builder.addLabel("当前用户", cc.xy(2, 1));
			//userNameField = new JTextField();
			builder.addLabel("新密码", cc.xy(2, 3));
			builder.addLabel("确认新密码", cc.xy(2, 5));
			//userNameField.setText
			//(DataPool.getInstance().getCurrentUser().getUserName());
			//userNameField.setEditable(false);
			
			builder.addLabel("如果需要修改密码，请在此填写", cc.xy(4, 1));
			userNewPasswordField = new JPasswordField();
			builder.add(userNewPasswordField, cc.xy(4, 3));
			
			userNewComfirmPasswordField = new JPasswordField();
			builder.add(userNewComfirmPasswordField, cc.xy(4, 5));
			
			editUserDialog.setLayout(new BorderLayout());
			editUserDialog.add(builder.getPanel(), "North");
			
			mainPanel = new ContactInfoPanel(parentFrame, 
					DataPool.getInstance().getCurrentUser(),
					true, ContactInfoPanel.USER_INFO_PANEL);
			editUserDialog.add(mainPanel, "Center");
			
			FormLayout downlayout = new FormLayout(
					"170dlu, pref, 50dlu, pref", // columns
					"p"); // rows
			
			PanelBuilder downbuilder = new PanelBuilder(downlayout);
			downbuilder.setDefaultDialogBorder();

			CellConstraints downcc = new CellConstraints();
			
			confirm = new JButton("确认信息修改");
			downbuilder.add(confirm, downcc.xy(2, 1));
			
			cancel = new JButton("取消信息修改");
			downbuilder.add(cancel, downcc.xy(4, 1));
		
			editUserDialog.add(downbuilder.getPanel(), "South");
			cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editUserDialog.dispose();
			}
		});
	}
	
	public JDialog getDialog(){
		return editUserDialog;
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
