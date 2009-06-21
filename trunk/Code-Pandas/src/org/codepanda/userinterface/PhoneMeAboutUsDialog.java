package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PhoneMeAboutUsDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4431678750159996379L;

	private JLabel picShow;
	private JTextArea info;
	private JPanel centerPanel;
	private JButton confirm;
	private JPanel southPanel;

	public PhoneMeAboutUsDialog(PhoneMeFrame mainFrame, String title,
			boolean mark) {
		super(mainFrame, title, mark);

		setLayout(new BorderLayout());

		// TODO add logo at north
		centerPanel = new JPanel();

		try {
			picShow = new JLabel(new ImageIcon(PhoneMeAboutUsDialog.class
					.getResource("Panda.gif")));
			centerPanel.add(picShow, BorderLayout.EAST);
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		info = new JTextArea();
		info.setText("Code Pandas!\n\n" + "Developers：\n" + "许丹青\tleilei\n"
				+ "李慧岷\tAN\n" + "张昕宇\tZhangxy\n" + "张晨光\thszcg\n\n"
				+ "Build v2009.06.22\n\n" + "Project Website：\n"
				+ "http://code.google.com/p/code-panda/ \n");
		info.setEditable(false);
		centerPanel.add(info, BorderLayout.CENTER);
		add(centerPanel, BorderLayout.CENTER);

		//
		southPanel = new JPanel();
		confirm = new JButton("确定");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		southPanel.add(confirm);
		add(southPanel, BorderLayout.SOUTH);

		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		setResizable(false);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
}
