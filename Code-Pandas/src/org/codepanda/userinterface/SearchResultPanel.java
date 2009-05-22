package org.codepanda.userinterface;

import java.awt.FlowLayout;
import java.util.ArrayList;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;
import org.jdesktop.swingx.JXPanel;

public class SearchResultPanel extends JXPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6855596910770372772L;
	private PhoneMeFrame parentFrame;
	private ArrayList<Integer> myISNList;

	public SearchResultPanel(PhoneMeFrame mainFrame, ArrayList<Integer> myISNList) {
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
		this.setLayout(new FlowLayout());
		
		// ContactOperations temp = DataPool.getInstance().getAllContactISNMap().get(myISNList.get(0));
		SingleResultPanel p1 = new SingleResultPanel(this.parentFrame, null);
		SingleResultPanel p2 = new SingleResultPanel(this.parentFrame, null);
		SingleResultPanel p3 = new SingleResultPanel(this.parentFrame, null);
		
		this.add(p1);
		this.add(p2);
		this.add(p3);
	}

	/**
	 * @return the parentFrame
	 */
	public PhoneMeFrame getParentFrame() {
		return parentFrame;
	}
}
