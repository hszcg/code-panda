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
		info.setText("��Ҫ������Ա��\n" +
				"����\t\t�ų���\n" +
				"����\t\t�����\n\n" +
				"���ʱ�䣺2009��6��22��\n" +
				"Google code��ַ��\n" +
				"http://code.google.com/p/code-panda/ \n");
		info.setEditable(false);
		add(info,"Center");
		
		southPanel = new JPanel();
		confirm = new JButton("ȷ��");
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
