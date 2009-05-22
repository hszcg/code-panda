package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;
import org.jdesktop.swingx.JXPanel;

public class SearchResultPanel extends JXPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6855596910770372772L;
	private final PhoneMeFrame parentFrame;
	private final ArrayList<Integer> myISNList;

	public SearchResultPanel(final PhoneMeFrame mainFrame,
			final ArrayList<Integer> myISNList) {
		// TODO Auto-generated constructor stub
		super();
		this.parentFrame = mainFrame;
		this.myISNList = myISNList;

		configureResultPanels();
	}

	/**
	 * 
	 */
	private void configureResultPanels() {
		// TODO Auto-generated method stub
		this.setLayout(new BorderLayout());

		// 结果提示信息
		JLabel searchResultLabel = new JLabel(myISNList.size()
				+ " Items Found", SwingConstants.CENTER);
		searchResultLabel.setForeground(Color.BLUE);
		searchResultLabel.setFont(searchResultLabel.getFont().deriveFont(
				(float) 14.0));

		this.add(searchResultLabel, BorderLayout.NORTH);

		// 结果显示
		HashMap<Integer, ContactOperations> temp = DataPool.getInstance()
				.getAllContactISNMap();
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new FlowLayout());
		for (int t : myISNList) {
			resultPanel
					.add(new SingleResultPanel(this.parentFrame, temp.get(t)));
		}
		this.add(resultPanel, BorderLayout.CENTER);
	}

	/**
	 * @return the parentFrame
	 */
	public final PhoneMeFrame getParentFrame() {
		return parentFrame;
	}
}
