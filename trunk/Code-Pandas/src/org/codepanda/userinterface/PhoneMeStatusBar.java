package org.codepanda.userinterface;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.jdesktop.swingx.JXStatusBar;

public class PhoneMeStatusBar extends JXStatusBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4735323763736018217L;

	private PhoneMeFrame parentFrame;
	private JLabel statusLabel;
	private JProgressBar progressBar;

	public PhoneMeStatusBar(PhoneMeFrame phoneMeFrame) {
		// TODO Auto-generated constructor stub
		super();
		this.parentFrame = phoneMeFrame;

		// statusLabel
		statusLabel = new JLabel("Ready");

		JXStatusBar.Constraint c1 = new Constraint();
		c1.setFixedWidth(200);
		this.add(statusLabel, c1);

		JXStatusBar.Constraint c2 = new Constraint(
				JXStatusBar.Constraint.ResizeBehavior.FILL);

		// JProgressBar
		progressBar = new JProgressBar();
		this.add(progressBar, c2);
	}

	/**
	 * @param String
	 *            status
	 */
	public void setStatus(String status) {
		if (status == null)
			return;

		this.statusLabel.setText(status);
	}

	/**
	 * @return the parentFrame
	 */
	public PhoneMeFrame getParentFrame() {
		return parentFrame;
	}

}
