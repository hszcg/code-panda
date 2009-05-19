package org.codepanda.userinterface;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.plaf.FontUIResource;

import org.codepanda.utility.data.DataPool;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXLoginPane;
import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.JXStatusBar.Constraint;
import org.jdesktop.swingx.auth.LoginService;
import org.jvnet.flamingo.ribbon.*;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;

public class PhoneMeFrame extends JRibbonFrame {
	private PhoneMeRibbon ribbon;
	private PhoneMeMenu menu;
	private PhoneMeMajorPanel majorPanel;
	private PhoneMeTaskPane taskPane;
	private JXStatusBar statusBar;

	public void configureRibbon() {
		ribbon = new PhoneMeRibbon(this);
		this.getRibbon().addTask(ribbon.getBasicTask());
		this.getRibbon().addTask(ribbon.getAdvancedTask());
	}

	public void configureApplicationMenu() {
		try {
			menu = new PhoneMeMenu(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getRibbon().setApplicationMenu(menu);
	}

	public void configureStatusBar() {
		this.statusBar = new JXStatusBar();
		JXLabel statusLabel = new JXLabel("Ready");
		
		JXStatusBar.Constraint c1 = new Constraint();
		c1.setFixedWidth(100);
		statusBar.add(statusLabel, c1); // Fixed width of 100 with no inserts
		JXStatusBar.Constraint c2 = new Constraint(
				JXStatusBar.Constraint.ResizeBehavior.FILL); // Fill with no
		
		// inserts
		JProgressBar pbar = new JProgressBar();
		statusBar.add(pbar, c2);

		add(statusBar, BorderLayout.SOUTH);
	}

	public void configureMajorPanel() {

		majorPanel = new PhoneMeMajorPanel(this);
		this.add(majorPanel, BorderLayout.CENTER);
		
		taskPane = new PhoneMeTaskPane(this);
		add(taskPane, BorderLayout.WEST);
	}

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

	public static void main(String args[]) {

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

				phoneMeFrame.configureLogin();

				phoneMeFrame.setVisible(true);
			}
		});
	}

	private void configureLogin() {
		// TODO Auto-generated method stub

		final JXLoginPane panel = new JXLoginPane(new LoginService() {
			public boolean authenticate(String name, char[] password,
					String server) throws Exception {
				// perform authentication and return true on success.
				String str = String.valueOf(password);

				System.out.println("NAME:\t" + name);
				System.out.println("PASSWORD:\t" + str);

				if (name.equals("Sa") && str.equals("Sa")) {
					return true;
				}

				return false;
			}
		});

		// UIManager.put("JXLoginPane.banner.darkBackground",
		// Color.ORANGE);
		// UIManager.put("JXLoginPane.banner.lightBackground",
		// Color.ORANGE.brighter());
		// UIManager.put("JXLoginPane.banner.font", new Font("Arial",
		// Font.ITALIC, 45));
		// UIManager.put("JXLoginPane.banner.foreground", Color.BLUE);

		// Image p =
		// Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/userpic/user0.jpg"));
		// panel.setBanner(p);
		panel.setBannerText("Welcome To PhoneMe!");

		JXLoginPane.Status loginStatus = JXLoginPane.showLoginDialog(this,
				panel);

		if (loginStatus != JXLoginPane.Status.SUCCEEDED) {
			System.exit(-1);
		}

	}
}
