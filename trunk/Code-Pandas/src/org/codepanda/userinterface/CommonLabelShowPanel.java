package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import org.codepanda.utility.data.DataPool;

public class CommonLabelShowPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1084995150969470877L;
	private PhoneMeFrame parentFrame;
	private ArrayList<SingleCommonLabelPanel> allCommonLabelPanel;

	/**
	 * @param mainFrame
	 */
	public CommonLabelShowPanel(PhoneMeFrame mainFrame) {
		super();
		this.parentFrame = mainFrame;
		configureResultPanels();
	}

	/**
	 * 
	 */
	private void configureResultPanels() {
		this.setLayout(new BorderLayout());

		Set<String> allCommonLabel = DataPool.getInstance()
				.getAllCommonLabelDataMap().keySet();

		// 结果提示信息
		JLabel searchResultLabel = new JLabel(allCommonLabel.size()
				+ " Items Found", SwingConstants.CENTER);
		searchResultLabel.setForeground(Color.BLUE);
		searchResultLabel.setFont(searchResultLabel.getFont().deriveFont(
				Font.BOLD, (float) 14.0));
		searchResultLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

		this.add(searchResultLabel, BorderLayout.NORTH);

		// 结果显示
		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());

		JPanel resultPanel = new JPanel();

		GridLayout layout = null;
		int numberInOneRow = 10;
		if (allCommonLabel.size() < numberInOneRow)
			layout = new GridLayout(1, allCommonLabel.size());
		else
			layout = new GridLayout((int) Math.ceil(allCommonLabel.size()
					/ (double) numberInOneRow), numberInOneRow);

		layout.setHgap(5);
		layout.setVgap(5);
		resultPanel.setLayout(layout);

		allCommonLabelPanel = new ArrayList<SingleCommonLabelPanel>(
				allCommonLabel.size());

		for (String labelName : allCommonLabel) {
			SingleCommonLabelPanel temp = new SingleCommonLabelPanel(
					this.parentFrame, labelName);
			allCommonLabelPanel.add(temp);
			resultPanel.add(temp);
		}

		center.add(resultPanel);

		this.add(center, BorderLayout.CENTER);
	}

	/**
	 * @return the parentFrame
	 */
	public PhoneMeFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * 
	 */
	public void deleteAllSelectedCommonLabel() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param isEditable
	 */
	public void setEditable(boolean isEditable) {
		// TODO Auto-generated method stub
		for (SingleCommonLabelPanel t : allCommonLabelPanel) {
			t.setEditable(isEditable);
		}
	}

}
