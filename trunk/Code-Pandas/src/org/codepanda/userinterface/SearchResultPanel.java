package org.codepanda.userinterface;

import javax.swing.JTable;

import org.jdesktop.swingx.JXPanel;

public class SearchResultPanel extends JXPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6855596910770372772L;
	private PhoneMeFrame parentFrame;
	private JTable searchResultTable;
	
	public SearchResultPanel(PhoneMeFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.parentFrame = mainFrame;
		searchResultTable = new JTable();
	}
}
