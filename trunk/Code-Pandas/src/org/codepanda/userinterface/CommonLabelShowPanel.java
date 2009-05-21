package org.codepanda.userinterface;

import org.jdesktop.swingx.JXPanel;

public class CommonLabelShowPanel extends JXPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2617574101499696345L;
	private PhoneMeFrame parentFrame;
	
	/**
	 * @param mainFrame
	 */
	public CommonLabelShowPanel(PhoneMeFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super();
		this.parentFrame = mainFrame;
	}

	/**
	 * @return the parentFrame
	 */
	public PhoneMeFrame getParentFrame() {
		return parentFrame;
	}

}
