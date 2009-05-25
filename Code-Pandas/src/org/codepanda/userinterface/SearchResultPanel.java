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
import org.codepanda.utility.data.ContactSectionType;
import org.codepanda.utility.data.DataPool;

public class SearchResultPanel extends JScrollPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2387803767963350937L;
	protected final PhoneMeFrame parentFrame;
	protected ArrayList<Integer> myISNList;
	private final JPanel center;
	protected final JPanel resultPanel;
	protected final JLabel searchResultLabel;
	protected final ContactSectionType secondType;

	public SearchResultPanel(final PhoneMeFrame mainFrame,
			final ArrayList<Integer> myISNList, ContactSectionType secondType) {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.parentFrame = mainFrame;
		this.myISNList = myISNList;
		this.center = new JPanel();
		this.resultPanel = new JPanel();
		this.searchResultLabel = new JLabel(myISNList.size() + " Items Found",
				SwingConstants.CENTER);
		this.secondType = secondType;

		JPanel p = configureResultPanels();

		this.setViewportView(p);
	}

	/**
	 * 
	 */
	private JPanel configureResultPanels() {
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());

		// 结果提示信息
		searchResultLabel.setForeground(Color.BLUE);
		searchResultLabel.setFont(searchResultLabel.getFont().deriveFont(
				Font.BOLD, (float) 14.0));
		searchResultLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

		content.add(searchResultLabel, BorderLayout.NORTH);

		// 结果显示
		HashMap<Integer, ContactOperations> temp = DataPool.getInstance()
				.getAllContactISNMap();
		center.setLayout(new FlowLayout());

		GridLayout layout = null;
		int numberInOneRow = 5;
		if (myISNList.size() < numberInOneRow)
			layout = new GridLayout(1, myISNList.size());
		else
			layout = new GridLayout((int) Math.ceil(myISNList.size()
					/ (double) numberInOneRow), numberInOneRow);

		System.out.println("ALL DATA:" + myISNList.size());

		layout.setHgap(5);
		layout.setVgap(5);
		resultPanel.setLayout(layout);

		for (int t : myISNList) {
			resultPanel.add(new SingleResultPanel(this.parentFrame,
					temp.get(t), secondType));
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
	public final SearchResultPanel getMainPanel() {
		return this;
	}

	/**
	 * @param updateISN
	 * 
	 * 只能更新和删除，不能新加
	 * 
	 */
	public void updateAllResult(int updateISN) {
		ContactOperations c = DataPool.getInstance().getAllContactISNMap().get(
				updateISN);
		for (int i = 0; i < this.myISNList.size(); i++) {
			if (myISNList.get(i) == updateISN) {			
				if (c == null) {
					myISNList.remove(i);
					resultPanel.remove(i);
				} else {
					((SingleResultPanel) resultPanel.getComponent(i))
							.updateDisplay(c, secondType);
				}
			}
		}
		
		searchResultLabel.setText(myISNList.size() + " Items Found");
	}

	/**
	 * @param updateISNList
	 * 
	 * 只能更新和删除，不能新加
	 * 
	 */
	public void updateAllResult(ArrayList<Integer> updateISNList) {
		for (int updateISN : updateISNList) {
			updateAllResult(updateISN);
		}
	}
}
