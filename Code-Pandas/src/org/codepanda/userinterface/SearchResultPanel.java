package org.codepanda.userinterface;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;

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
		
		HashMap<Integer, ContactOperations> temp = DataPool.getInstance().getAllContactISNMap();
		
		for(int t: myISNList){
			this.add(new SingleResultPanel(this.parentFrame, temp.get(t)));
		}
	}

	/**
	 * @return the parentFrame
	 */
	public final PhoneMeFrame getParentFrame() {
		return parentFrame;
	}
}
