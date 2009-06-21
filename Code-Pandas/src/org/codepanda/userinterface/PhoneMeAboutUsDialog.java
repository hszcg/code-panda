package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PhoneMeAboutUsDialog extends JDialog{
	JTextArea info;
	JButton confirm;
	JPanel southPanel;
	public PhoneMeAboutUsDialog(PhoneMeFrame mainFrame, 
			String title, boolean mark){
		super(mainFrame, title, mark);
		
		setLayout(new BorderLayout());
		
		//TODO add logo at north
		
		info = new JTextArea();
		info.setText("主要开发人员：\n" +
				"许丹青\t\t张晨光\n" +
				"李慧岷\t\t张昕宇\n\n" +
				"完成时间：2009年6月22日\n" +
				"Google code网址：\n" +
				"http://code.google.com/p/code-panda/ \n");
		info.setEditable(false);
		add(info,"Center");
		
		southPanel = new JPanel();
		confirm = new JButton("确定");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		southPanel.add(confirm);
		add(southPanel, "South");
		
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		setResizable(false);
		setVisible(true);
	}
}
