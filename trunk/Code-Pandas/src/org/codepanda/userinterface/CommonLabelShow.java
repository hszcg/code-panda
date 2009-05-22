package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.group.ContactGroup;
import org.jdesktop.swingx.JXPanel;

public class CommonLabelShow{
	private PhoneMeFrame parentFrame;
	private JScrollPane mainPanel;
	
	/**
	 * @param mainFrame
	 */
	public CommonLabelShow(PhoneMeFrame mainFrame) {
		this.parentFrame = mainFrame;
		
		JPanel p = configureResultPanels();
		this.mainPanel = new JScrollPane(p,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	private JPanel configureResultPanels() {
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());

		// 结果提示信息
		JLabel searchResultLabel = new JLabel(" Items Found", SwingConstants.CENTER);
		searchResultLabel.setForeground(Color.BLUE);
		searchResultLabel.setFont(searchResultLabel.getFont().deriveFont(Font.BOLD,
				(float) 14.0));
		searchResultLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

		content.add(searchResultLabel, BorderLayout.NORTH);

		// 结果显示
		HashMap<String, ContactGroup> temp = DataPool.getInstance()
				.getAllCommonLabelDataMap();
		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());

		JPanel resultPanel = new JPanel();

		GridLayout layout = new GridLayout();
		int rows = 10 / 5;
		if( rows == 0 )
			layout.setColumns(2);
		else
			layout.setColumns(5);
		
		layout.setHgap(5);
		layout.setVgap(5);
		resultPanel.setLayout(layout);


		center.add(resultPanel);

		content.add(center, BorderLayout.CENTER);

		return content;
	}

	/**
	 * @return the parentFrame
	 */
	public PhoneMeFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @return the mainPanel
	 */
	public final JScrollPane getMainPanel() {
		return mainPanel;
	}
}
