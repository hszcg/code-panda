/**
 * 
 */
package org.codepanda.userinterface;

import javax.swing.*;
import org.jvnet.flamingo.ribbon.*;
import org.jvnet.flamingo.ribbon.ui.appmenu.JRibbonApplicationMenuButton;
import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;

/**
 * 
 * @author hszcg
 * @version 4.16.01
 *
 */
public class EmptyRibbon {
	public JRibbonFrame mainMenu; // 主界面
	
	public static void main(String[] args) {
		
		EmptyRibbon p = new EmptyRibbon();
		p.intiGUI();
	}

	private void intiGUI() {
		mainMenu = new JRibbonFrame("Test GUI");
		
		// 外观设置
		try {
			UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// **************************************************
		
		mainMenu.add(new JRibbonApplicationMenuButton());
		
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setSize(400, 300);		
		mainMenu.setVisible(true);
	}
}
