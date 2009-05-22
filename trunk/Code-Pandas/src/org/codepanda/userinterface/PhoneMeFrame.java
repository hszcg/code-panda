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

/**
 * @author hszcg
 *
 */
public class PhoneMeFrame extends JRibbonFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2654797980693943612L;
	private PhoneMeRibbon myPhoneMeRibbon;
	private PhoneMeMenu myPhoneMeMenu;
	private PhoneMeMajorPanel myPhoneMeMajorPanel;
	private PhoneMeTaskPane myPhoneMeTaskPane;
	private PhoneMeLoginDialog myPhoneMeLoginDialog;
	private PhoneMeStatusBar myPhoneMeStatusBar;

	/**
	 * 
	 */
	public void configureRibbon() {
		myPhoneMeRibbon = new PhoneMeRibbon(this);
		this.getRibbon().addTask(myPhoneMeRibbon.getBasicTask());
		this.getRibbon().addTask(myPhoneMeRibbon.getAdvancedTask());
	}

	/**
	 * 
	 */
	public void configureApplicationMenu() {
		try {
			myPhoneMeMenu = new PhoneMeMenu(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getRibbon().setApplicationMenu(myPhoneMeMenu);
	}

	/**
	 * 
	 */
	public void configureStatusBar() {
		this.myPhoneMeStatusBar = new PhoneMeStatusBar(this);
		add(myPhoneMeStatusBar, BorderLayout.SOUTH);
	}

	/**
	 * 
	 */
	public void configureMajorPanel() {

		myPhoneMeMajorPanel = new PhoneMeMajorPanel(this);
		this.add(myPhoneMeMajorPanel, BorderLayout.CENTER);

		myPhoneMeTaskPane = new PhoneMeTaskPane(this);
		add(myPhoneMeTaskPane, BorderLayout.WEST);
	}

	/**
	 * 
	 */
	public void setLocation() {
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();
		this.setPreferredSize(new Dimension(r.width, r.height));
		this.pack();
		this.setLocation(r.x, r.y);
		this.setVisible(false);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 
	 */
	public PhoneMeFrame() {
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
		configureStatusBar();
		setLocation();
	}

	/**
	 * @param font
	 */
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

	/**
	 * @param args
	 */
	public static void main(String args[]) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				// datapool-init
				/* ADD BY SA*/
				DataPool.getInstance();


				// �������
				try {
					UIManager
							.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
					UIManager
							.put(
									SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY,
									Boolean.TRUE);

					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

				Locale.setDefault(new Locale("USA"));
				// **************************************************

				try {
					InitGlobalFont(new Font("����", Font.PLAIN, 12));
				} catch (Exception e) {
					InitGlobalFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
				}

				PhoneMeFrame phoneMeFrame = new PhoneMeFrame();

				phoneMeFrame.configureLogin();

				phoneMeFrame.setVisible(true);
				phoneMeFrame.setResizable(false);
			}
		});
	}

	/**
	 * 
	 */
	private void configureLogin() {
		// TODO Auto-generated method stub
		myPhoneMeLoginDialog = new PhoneMeLoginDialog(this);
		myPhoneMeLoginDialog.setVisible(true);
	}
	
	/**
	 * @return
	 */
	public final PhoneMeMajorPanel getMyPhoneMeMajorPanel() {
		return myPhoneMeMajorPanel;
	}
	
	
	/**
	 * 
	 */
	public void initializeData() {
		this.myPhoneMeTaskPane.initializeData();
		this.myPhoneMeMajorPanel.initializeData();
	}

	/**
	 * @return
	 */
	public final PhoneMeStatusBar getMyPhoneMeStatusBar() {
		return myPhoneMeStatusBar;
	}
}