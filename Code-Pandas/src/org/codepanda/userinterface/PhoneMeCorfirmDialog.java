package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.codepanda.userinterface.xml.MyXMLMaker;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class PhoneMeCorfirmDialog extends JDialog implements ActionListener{
	private JTextField userNameField;
	private JPasswordField userPasswordField;
	
	private JButton confirm;
	private JButton cancel;
	
	private JLabel errorMessageLabel;
	
	public PhoneMeCorfirmDialog(PhoneMeFrame frame){
		super(frame, "Confirm", true);
		FormLayout layout = new FormLayout(
			"1dlu, pref, 50dlu, pref, 1dlu", // columns
			"p, 10dlu, p, 10dlu, p, 10dlu, p"); // rows
		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		builder.addLabel("需要进行密码确认", cc.xy(1, 1));
		
		builder.addLabel("用户名", cc.xy(1, 3));
		userNameField = new JTextField(20);
		builder.add(userNameField, cc.xy(3, 3));
		
		builder.addLabel("密码", cc.xy(1, 5));
		userPasswordField = new JPasswordField(20);
		builder.add(userPasswordField, cc.xy(3, 5));
		
		builder.add(errorMessageLabel, cc.xy(1, 5));
		
		setLayout(new BorderLayout());
		add(builder.getPanel(), "Center");
		
		FormLayout downlayout = new FormLayout("170dlu, pref, 50dlu, pref", // columns
		"p"); // rows

		PanelBuilder downbuilder = new PanelBuilder(downlayout);
		downbuilder.setDefaultDialogBorder();

		CellConstraints downcc = new CellConstraints();

		confirm = new JButton("确认删除用户");
		downbuilder.add(confirm, downcc.xy(2, 1));
		confirm.addActionListener(this);

		cancel = new JButton("取消删除用户");
		downbuilder.add(cancel, downcc.xy(4, 1));
		cancel.addActionListener(this);
		
		add(downbuilder.getPanel(), "South");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel)
			dispose();
		
		if(e.getSource() == confirm){
			String userName = userNameField.getText();
			String userPassword = String.valueOf(this.userPasswordField
					.getPassword());
			
			String xml = MyXMLMaker.getLoginUserXML
			(userName, userPassword);
			
		// TODO check password is right or wrong
		
		// TODO make delete user command actor and act 
		}
	}
}
