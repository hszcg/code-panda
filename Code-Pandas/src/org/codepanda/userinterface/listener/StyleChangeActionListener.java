package org.codepanda.userinterface.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.jvnet.flamingo.common.JCommandToggleButton;

import org.codepanda.userinterface.PhoneMeRibbon;

public class StyleChangeActionListener implements ActionListener {
	private PhoneMeRibbon myPhoneMeRibbon;

	public StyleChangeActionListener(final PhoneMeRibbon phoneMeRibbon) {
		// TODO Auto-generated constructor stub
		myPhoneMeRibbon = phoneMeRibbon;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JCommandToggleButton src = (JCommandToggleButton) arg0.getSource();
		final String styleName = src.getText();
		System.out.println("Select Style: " + styleName);

		SwingUtilities.invokeLater(new Runnable() {
			// TODO
			public void run() {
				JFrame frame = myPhoneMeRibbon.getMainFrame();

				boolean wasDecoratedByOS = !frame.isUndecorated();
				try {
					LookAndFeelInfo selected = myPhoneMeRibbon.allLookAndFeelMap
							.get(styleName);
					UIManager.setLookAndFeel(selected.getClassName());
					frame.paintAll(frame.getGraphics());
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				boolean canBeDecoratedByLAF = UIManager.getLookAndFeel()
						.getSupportsWindowDecorations();
				if (canBeDecoratedByLAF == wasDecoratedByOS) {
					boolean wasVisible = frame.isVisible();

					frame.setVisible(false);
					frame.dispose();
					if (!canBeDecoratedByLAF) {
						frame.setUndecorated(false);
						frame.getRootPane().setWindowDecorationStyle(
								JRootPane.NONE);

					} else {
						frame.setUndecorated(true);
						frame.getRootPane().setWindowDecorationStyle(
								JRootPane.FRAME);
					}
					frame.setVisible(wasVisible);
					wasDecoratedByOS = !frame.isUndecorated();
				}
			}
		});
	}

}
