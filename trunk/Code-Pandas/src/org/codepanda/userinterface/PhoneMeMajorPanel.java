package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXLoginPane;
import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.JXTree;
import org.jdesktop.swingx.JXStatusBar.Constraint;
import org.jdesktop.swingx.auth.LoginService;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.renderer.IconValues;
import org.jdesktop.swingx.renderer.StringValues;
import org.jdesktop.swingx.treetable.FileSystemModel;
import org.jvnet.lafwidget.LafWidget;
import org.jvnet.lafwidget.tabbed.DefaultTabPreviewPainter;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceConstants.TabCloseKind;
import org.jvnet.substance.api.tabbed.TabCloseCallback;
import org.jvnet.substance.api.tabbed.TabCloseListener;
import org.jvnet.substance.api.tabbed.VetoableMultipleTabCloseListener;
import org.jvnet.substance.api.tabbed.VetoableTabCloseListener;

public class PhoneMeMajorPanel extends JPanel{
	private PhoneMeFrame mainFrame;
	private JXTaskPaneContainer taskPaneContainer;
	private JXStatusBar statusBar;
	private JTabbedPane centerPanel;
	
	public PhoneMeMajorPanel(PhoneMeFrame mainFrame) {
		super();
		setLayout(new BorderLayout());
		
		this.mainFrame = mainFrame;
		
		this.taskPaneContainer = getTaskPaneContainer();
		add(taskPaneContainer, BorderLayout.WEST);
		
		this.statusBar = getStatusBar();
		add(statusBar, BorderLayout.SOUTH);
		
		this.centerPanel = getCenterPanel();
		add(centerPanel, BorderLayout.CENTER);
		
		this.configureListener();
	}

	private void configureListener() {
		SubstanceLookAndFeel.registerTabCloseChangeListener(centerPanel,
				new VetoableTabCloseListener() {
					public void tabClosed(JTabbedPane tabbedPane,
							Component tabComponent) {
						System.out.println("Closed tab - specific");
					}

					public void tabClosing(JTabbedPane tabbedPane,
							Component tabComponent) {
						System.out.println("Closing tab - specific");
					}

					public boolean vetoTabClosing(JTabbedPane tabbedPane,
							Component tabComponent) {
						int userCloseAnswer = JOptionPane
								.showConfirmDialog(
										mainFrame,
										"Are you sure you want to close '"
												+ tabbedPane
														.getTitleAt(tabbedPane
																.indexOfComponent(tabComponent))
												+ "' tab?", "Confirm dialog",
										JOptionPane.YES_NO_OPTION);
						return (userCloseAnswer == JOptionPane.NO_OPTION);
					}
				});

		SubstanceLookAndFeel.registerTabCloseChangeListener(centerPanel,
				new VetoableMultipleTabCloseListener() {
					public void tabsClosed(JTabbedPane tabbedPane,
							Set<Component> tabComponents) {
						System.out.println("Closed " + tabComponents.size()
								+ " tabs - specific");
					}

					public void tabsClosing(JTabbedPane tabbedPane,
							Set<Component> tabComponents) {
						System.out.println("Closing " + tabComponents.size()
								+ " tabs - specific");
					}

					public boolean vetoTabsClosing(JTabbedPane tabbedPane,
							Set<Component> tabComponents) {
						int userCloseAnswer = JOptionPane.showConfirmDialog(
								mainFrame, "Are you sure you want to close "
										+ tabComponents.size() + " tabs?",
								"Confirm dialog", JOptionPane.YES_NO_OPTION);
						return (userCloseAnswer == JOptionPane.NO_OPTION);
					}
				});
	}

	private JTabbedPane getCenterPanel() {
		
		// ****************************************************************************
		JTabbedPane centerPanel = new JTabbedPane();

		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new SpringLayout());

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

		final JFrame frame = JXLoginPane.showLoginFrame(panel);

		JXButton loginButton = new JXButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.getStatus() == JXLoginPane.Status.SUCCEEDED)
					return;

				System.out.println("Login");
				frame.setVisible(!frame.isVisible());
			}
		});

		loginPanel.add(loginButton);
		centerPanel.addTab("Login Tab", loginPanel);

		JPanel monthPanel = new JPanel();
		monthPanel.add(new JXMonthView());
		centerPanel.addTab("Month Tab", monthPanel);

		JPanel treePanel = new JPanel();
		treePanel.setLayout(new SpringLayout());
		JXTree tree = new JXTree(new FileSystemModel());
		// use system file icons and name to render
		tree.setCellRenderer(new DefaultTreeRenderer(IconValues.FILE_ICON,
				StringValues.FILE_NAME));
		// highlight condition: file modified after a date
		HighlightPredicate predicate = new HighlightPredicate() {
			public boolean isHighlighted(Component renderer,
					ComponentAdapter adapter) {
				// File file = getUserObject(adapter.getValue());
				// return file != null ? lastWeek < file.lastModified : false;
				return false;
			}
		};
		// highlight with foreground color
		tree.addHighlighter(new ColorHighlighter(predicate, null, Color.RED));

		treePanel.add(tree);
		centerPanel.addTab("Tree Tab", treePanel);

		TabCloseCallback closeCallbackMain = new TabCloseCallback() {
			public TabCloseKind onAreaClick(JTabbedPane tabbedPane,
					int tabIndex, MouseEvent mouseEvent) {
				if (mouseEvent.getButton() != MouseEvent.BUTTON2)
					return TabCloseKind.NONE;
				if (mouseEvent.isShiftDown()) {
					return TabCloseKind.ALL;
				}
				return TabCloseKind.THIS;
			}

			public TabCloseKind onCloseButtonClick(JTabbedPane tabbedPane,
					int tabIndex, MouseEvent mouseEvent) {
				if (mouseEvent.isAltDown()) {
					return TabCloseKind.ALL_BUT_THIS;
				}
				if (mouseEvent.isShiftDown()) {
					return TabCloseKind.ALL;
				}
				return TabCloseKind.THIS;
			}

			public String getAreaTooltip(JTabbedPane tabbedPane, int tabIndex) {
				return null;
			}

			public String getCloseButtonTooltip(JTabbedPane tabbedPane,
					int tabIndex) {
				StringBuffer result = new StringBuffer();
				result.append("<html><body>");
				result.append("Mouse click closes <b>"
						+ tabbedPane.getTitleAt(tabIndex) + "</b> tab");
				result
						.append("<br><b>Alt</b>-Mouse click closes all tabs but <b>"
								+ tabbedPane.getTitleAt(tabIndex) + "</b> tab");
				result.append("<br><b>Shift</b>-Mouse click closes all tabs");
				result.append("</body></html>");
				return result.toString();
			}
		};

		centerPanel.putClientProperty(
				SubstanceLookAndFeel.TABBED_PANE_CLOSE_CALLBACK,
				closeCallbackMain);
		centerPanel.putClientProperty(LafWidget.TABBED_PANE_PREVIEW_PAINTER,
				new DefaultTabPreviewPainter());

		SubstanceLookAndFeel
				.registerTabCloseChangeListener(new TabCloseListener() {
					public void tabClosed(JTabbedPane tabbedPane,
							Component tabComponent) {
						System.out.println("Closed tab");
					}

					public void tabClosing(JTabbedPane tabbedPane,
							Component tabComponent) {
						System.out.println("Closing tab");
					}
				});

		
		// ****************************************************************************

		// JScrollPane contactInfoScrollPane = new JScrollPane(new
		// ContactInfoPanel());
		// contactInfoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// contactInfoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		centerPanel.add("Contact Info", new ContactInfoPanel());
		
		return centerPanel;
	}

	private JXStatusBar getStatusBar() {
		JXStatusBar bar = new JXStatusBar();
		JXLabel statusLabel = new JXLabel("Ready");
		JXStatusBar.Constraint c1 = new Constraint();
		c1.setFixedWidth(100);
		bar.add(statusLabel, c1); // Fixed width of 100 with no inserts
		JXStatusBar.Constraint c2 = new Constraint(
				JXStatusBar.Constraint.ResizeBehavior.FILL); // Fill with no
		// inserts
		JProgressBar pbar = new JProgressBar();
		bar.add(pbar, c2); // Fill with no inserts - will use remaining space
		
		return bar;
	}


	private JXTaskPaneContainer getTaskPaneContainer() {
		// ****************************************************************************
		// a container to put all JXTaskPane together
		JXTaskPaneContainer taskPaneContainer = new JXTaskPaneContainer();
		
		JXTaskPane details = new JXTaskPane();
		details.setTitle("Details");

		JXLabel searchLabel = new JXLabel("Search:");
		JTextField searchField = new JTextField("");
		details.add(searchLabel);
		details.add(searchField);

		taskPaneContainer.add(details);

		JXTaskPane actionPane = new JXTaskPane();
		actionPane.setTitle("Files and Folders");
		actionPane.setSpecial(true);

		taskPaneContainer.add(actionPane);
		
		return taskPaneContainer;
	}

}
