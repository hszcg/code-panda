package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;

public class SearchResult {
	private final PhoneMeFrame parentFrame;
	private final ArrayList<Integer> myISNList;
	private JScrollPane mainPanel;

	public SearchResult(final PhoneMeFrame mainFrame,
			final ArrayList<Integer> myISNList) {
		this.parentFrame = mainFrame;
		this.myISNList = myISNList;

		JPanel p = configureResultPanels();
		this.mainPanel = new JScrollPane(p,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	/**
	 * 
	 */
	private JPanel configureResultPanels() {
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());

		// 结果提示信息
		JLabel searchResultLabel = new JLabel(
				myISNList.size() + " Items Found", SwingConstants.CENTER);
		searchResultLabel.setForeground(Color.BLUE);
		searchResultLabel.setFont(searchResultLabel.getFont().deriveFont(Font.BOLD,
				(float) 14.0));
		searchResultLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

		content.add(searchResultLabel, BorderLayout.NORTH);

		// 结果显示
		HashMap<Integer, ContactOperations> temp = DataPool.getInstance()
				.getAllContactISNMap();
		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());

		JPanel resultPanel = new JPanel();

		GridLayout layout = new GridLayout();
		int rows = (int) Math.sqrt(myISNList.size()) / 5;
		if( rows == 0 )
			layout.setColumns(myISNList.size());
		else
			layout.setColumns(5);
		
		layout.setHgap(5);
		layout.setVgap(5);
		resultPanel.setLayout(layout);
		

		for (int t : myISNList) {
			resultPanel
					.add(new SingleResultPanel(this.parentFrame, temp.get(t)));
		}
		
		center.add(resultPanel);

		content.add(center, BorderLayout.CENTER);

		return content;
	}

	/**
	 * @return the parentFrame
	 */
	public final PhoneMeFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @return the mainPanel
	 */
	public final JScrollPane getMainPanel() {
		return mainPanel;
	}
}
