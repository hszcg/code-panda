/**
 * 
 */
package org.codepanda.userinterface;

import java.awt.event.*;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.Locale;

import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.JXStatusBar.Constraint;
import org.jvnet.flamingo.common.JCommandButton;
import org.jvnet.flamingo.ribbon.JRibbonBand;
import org.jvnet.flamingo.ribbon.JRibbonFrame;
import org.jvnet.flamingo.ribbon.RibbonElementPriority;
import org.jvnet.flamingo.ribbon.RibbonTask;
import org.jvnet.substance.skin.*;

//import test.ribbon.BasicCheckRibbon.ExpandActionListener;
public class EmptyRibbon extends JRibbonFrame {
	public EmptyRibbon() {
		super("PhoneMe test");
		// try {
		// this.setIconImages(Arrays.asList(ImageIO
		// .read(BasicCheckRibbon.class
		// .getResource("ribbon-main-icon-16.png")), ImageIO
		// .read(BasicCheckRibbon.class
		// .getResource("ribbon-main-icon.png"))));
		// } catch (IOException ioe) {
		// ioe.printStackTrace();
		// }
	}

	private class ExpandActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(EmptyRibbon.this,
					"Expand button clicked");
		}
	}

	private JRibbonBand getUserManagerBand() {
		JRibbonBand userManagerBand = new JRibbonBand("User Management",
				new edit_paste(), new ExpandActionListener());

		JCommandButton newUserButton = new JCommandButton("New User",
				new edit_paste());

		newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("New User");
			}
		});

		userManagerBand.addCommandButton(newUserButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteUserButton = new JCommandButton("Delete User",
				new edit_paste());

		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete User");
			}
		});

		userManagerBand.addCommandButton(deleteUserButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editUserButton = new JCommandButton("Edit User Info",
				new edit_paste());

		editUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Edit User Info");
			}
		});

		userManagerBand.addCommandButton(editUserButton,
				RibbonElementPriority.MEDIUM);

		// userManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(userManagerBand));

		return userManagerBand;
	}

	private JRibbonBand getContactManagerBand() {
		JRibbonBand contactManagerBand = new JRibbonBand("Contact Management",
				new edit_paste(), new ExpandActionListener());

		JCommandButton newContactButton = new JCommandButton("New Contact",
				new edit_paste());

		newContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Contact");
			}
		});

		contactManagerBand.addCommandButton(newContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteContactButton = new JCommandButton(
				"Delete Contact", new edit_paste());

		deleteContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete Contact");
			}
		});

		contactManagerBand.addCommandButton(deleteContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editContactButton = new JCommandButton("Edit Contact",
				new edit_paste());

		editContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Edit Contact");
			}
		});

		contactManagerBand.addCommandButton(editContactButton,
				RibbonElementPriority.MEDIUM);

		// contactManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(contactManagerBand));

		return contactManagerBand;
	}

	JRibbonBand getContactExchangeBand() {
		JRibbonBand contactExchangeBand = new JRibbonBand("Contact Exchange",
				new edit_paste(), new ExpandActionListener());

		JCommandButton importContactButton = new JCommandButton(
				"Import Contact", new edit_paste());

		importContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Import Contact");
			}
		});

		contactExchangeBand.addCommandButton(importContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton exportContactButton = new JCommandButton(
				"Export Contact", new edit_paste());

		exportContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Export Contact");
			}
		});

		contactExchangeBand.addCommandButton(exportContactButton,
				RibbonElementPriority.MEDIUM);

		return contactExchangeBand;
	}

	JRibbonBand getContactSyncBand() {
		JRibbonBand contactSyncBand = new JRibbonBand("Sync Contact",
				new edit_paste(), new ExpandActionListener());

		JCommandButton googleContactButton = new JCommandButton(
				"Sync With Google Contact", new edit_paste());

		googleContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sync With Google Contact");
			}
		});

		contactSyncBand.addCommandButton(googleContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton bluetoothContactButton = new JCommandButton(
				"Sync With Bluetooth", new edit_paste());

		bluetoothContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sync With Bluetooth");
			}
		});

		contactSyncBand.addCommandButton(bluetoothContactButton,
				RibbonElementPriority.MEDIUM);

		return contactSyncBand;
	}

	JRibbonBand getOtherFunctionBand() {
		JRibbonBand otherFunctionBand = new JRibbonBand("Others",
				new edit_paste(), new ExpandActionListener());

		JCommandButton remindBirthdayButton = new JCommandButton(
				"Birthday Tips", new edit_paste());

		remindBirthdayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Birthday Tips");
			}
		});

		otherFunctionBand.addCommandButton(remindBirthdayButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton arrangeContactButton = new JCommandButton(
				"Contact Arrangement", new edit_paste());

		arrangeContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Contact Arrangement");
			}
		});

		otherFunctionBand.addCommandButton(arrangeContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton relationNetButton = new JCommandButton("Human Cube",
				new edit_paste());

		relationNetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Human Cube");
			}
		});

		otherFunctionBand.addCommandButton(relationNetButton,
				RibbonElementPriority.MEDIUM);

		return otherFunctionBand;
	}

	public void configureRibbon() {
		JRibbonBand userManagerBand = this.getUserManagerBand();
		JRibbonBand newContactBand = this.getContactManagerBand();
		RibbonTask basicTask = new RibbonTask("Basic", userManagerBand,
				newContactBand);
		basicTask.setKeyTip("P");
		this.getRibbon().addTask(basicTask);

		JRibbonBand contactExchangeBand = this.getContactExchangeBand();
		JRibbonBand contactSyncBand = this.getContactSyncBand();
		JRibbonBand otherFunctionBand = this.getOtherFunctionBand();
		RibbonTask advancedTask = new RibbonTask("Advanced",
				contactExchangeBand, contactSyncBand, otherFunctionBand);
		this.getRibbon().addTask(advancedTask);

		// ****************************************************************************
		JPanel majorPanel = new JPanel();
		majorPanel.setLayout(new BorderLayout());
		// a container to put all JXTaskPane together
		JXTaskPaneContainer taskPaneContainer = new JXTaskPaneContainer();

		// create a first taskPane with common actions
		JXTaskPane actionPane = new JXTaskPane();
		actionPane.setTitle("Files and Folders");
		actionPane.setSpecial(true);

		// add this taskPane to the taskPaneContainer
		taskPaneContainer.add(actionPane);

		// create another taskPane, it will show details of the selected file
		JXTaskPane details = new JXTaskPane();
		details.setTitle("Details");

		// add standard components to the details taskPane
		JLabel searchLabel = new JLabel("Search:");
		JTextField searchField = new JTextField("");
		details.add(searchLabel);
		details.add(searchField);

		taskPaneContainer.add(details);

		majorPanel.add(taskPaneContainer, BorderLayout.WEST);

		JXStatusBar bar = new JXStatusBar();
		JLabel statusLabel = new JLabel("Ready");
		JXStatusBar.Constraint c1 = new Constraint();
		c1.setFixedWidth(100);
		bar.add(statusLabel, c1); // Fixed width of 100 with no inserts
		JXStatusBar.Constraint c2 = new Constraint(
				JXStatusBar.Constraint.ResizeBehavior.FILL); // Fill with no
																// inserts
		JProgressBar pbar = new JProgressBar();
		bar.add(pbar, c2); // Fill with no inserts - will use remaining space
		
		majorPanel.add(bar, BorderLayout.SOUTH);
		// ****************************************************************************

		this.add(majorPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		// Õ‚π€…Ë÷√
		try {
			UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		Locale.setDefault(new Locale("USA"));
		// **************************************************

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				EmptyRibbon er = new EmptyRibbon();
				er.configureRibbon();
				Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getMaximumWindowBounds();
				er.setPreferredSize(new Dimension(r.width, r.height / 2));
				er.pack();
				er.setLocation(r.x, r.y);
				er.setVisible(true);
				er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
	}
}
