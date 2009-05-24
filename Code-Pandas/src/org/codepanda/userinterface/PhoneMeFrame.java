package org.codepanda.userinterface;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		this.getContentPane().add(myPhoneMeStatusBar, BorderLayout.SOUTH);
	}

	/**
	 * 
	 */
	public void configureMajorPanel() {

		myPhoneMeMajorPanel = new PhoneMeMajorPanel(this);
		this.getContentPane().add(myPhoneMeMajorPanel, BorderLayout.CENTER);

		myPhoneMeTaskPane = new PhoneMeTaskPane(this);
		this.getContentPane().add(myPhoneMeTaskPane, BorderLayout.WEST);
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
		this.setResizable(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("PhoneMeFrame Closed.");
				exitProgram();
			}
		});
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
				/* ADD BY SA */
				DataPool.getInstance();

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

	/**
	 * 
	 */
	private void configureLogin() {
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
	 * After Login to Initialize Data
	 */
	public void initializeData() {
		// GContactOper gco = new GContactOper();
		// try {
		// gco.createContact();
		// // gco.getAllContacts();
		// } catch (ServiceException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		this.myPhoneMeTaskPane.initializeData();
		this.myPhoneMeMajorPanel.initializeData();

		// //test of CSV export and import
		// /* CsvExport ce = new CsvExport();
		// ce.convert("D:\\javawork\\four pandas\\abc.csv");
		//		
		// <<<<<<< .mine
		// // CsvImport ci = new CsvImport();
		// // ci.convert("D:\\javawork\\four pandas\\abc.csv");
		// =======
		// //CsvImport ci = new CsvImport();
		// //ci.convert("abc.csv");
		// >>>>>>> .r291
		//		
		// CsvExport cee = new CsvExport();
		// <<<<<<< .mine
		// cee.convert("D:\\javawork\\four pandas\\cba.csv");
		// =======
		// cee.convert("cba.csv");*/
		// >>>>>>> .r291
	}

	/**
	 * @return
	 */
	public final PhoneMeStatusBar getMyPhoneMeStatusBar() {
		return myPhoneMeStatusBar;
	}

	/**
	 * 退出程序的相关处理函数
	 * 
	 */
	public void exitProgram() {
		try {
			DataPool.getInstance().fl.release();
			DataPool.getInstance().raf.close();
			DataPool.getInstance().getDb().close();
			// Thread.sleep(1000);
			System.out.println("Normal Exit!");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed Exit!");
			System.exit(-1);
		}
	}

	/**
	 * 
	 */
	public void updateTaskPane(int updateISN) {
		this.myPhoneMeTaskPane.updateGroupList(updateISN);
	}
}