package org.codepanda.userinterface;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.plaf.FontUIResource;

import org.codepanda.utility.data.DataPool;
import org.jvnet.flamingo.ribbon.*;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;

public class PhoneMeFrame extends JRibbonFrame{
	PhoneMeRibbon ribbon;
	PhoneMeMenu menu;
	PhoneMeMajorPanel majorPanel;
	
	public void configureRibbon(){
		ribbon = new PhoneMeRibbon(this);
		this.getRibbon().addTask(ribbon.getBasicTask());
		this.getRibbon().addTask(ribbon.getAdvancedTask());
	}
	
	public void configureApplicationMenu(){
		menu = new PhoneMeMenu(this);
		this.getRibbon().setApplicationMenu(menu);
	}
	
	public void configureMajorPanel() {
		majorPanel = new PhoneMeMajorPanel(this);
		this.add(majorPanel, BorderLayout.CENTER);
	}
	
	public void setLocation(){
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment()
		.getMaximumWindowBounds();
		this.setPreferredSize(new Dimension(r.width, r.height));
		this.pack();
		this.setLocation(r.x, r.y);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public PhoneMeFrame(){
		super("PhoneMe test");
		try {
			this.setIconImages(Arrays
					.asList(ImageIO.read(this.getClass().getResource(
							"/ribbon-main-icon-16.png")), ImageIO.read(this
							.getClass().getResource("/ribbon-main-icon.png"))));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		configureApplicationMenu();
		configureRibbon();
		configureMajorPanel();
		setLocation();
	}
	
	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys
				.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}
	
	public static void main(String args[]){
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				// datapool-init
				// DataPool.getInstance()
				// DataPool.getInstance();

				// MyMessageHandler mmh = new MyMessageHandler();
				// mmh.test();

				// 外观设置
				try {
					UIManager
							.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
					UIManager
							.put(
									SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY,
									Boolean.TRUE);
					UIManager.put("JXLoginPane.banner.darkBackground",
							Color.ORANGE);
					UIManager.put("JXLoginPane.banner.lightBackground",
							Color.ORANGE.brighter());
					UIManager.put("JXLoginPane.banner.font", new Font("Arial",
							Font.ITALIC, 35));
					UIManager.put("JXLoginPane.banner.foreground", Color.WHITE);

					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

				Locale.setDefault(new Locale("USA"));
				// **************************************************

				try {
					InitGlobalFont(new Font("宋体", Font.PLAIN, 12));
				} catch (Exception e) {
					InitGlobalFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
				}

				PhoneMeFrame phoneMeFrame = new PhoneMeFrame();
			}
		});
	}
}
