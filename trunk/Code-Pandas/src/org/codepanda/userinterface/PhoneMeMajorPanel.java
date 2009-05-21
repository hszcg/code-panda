package org.codepanda.userinterface;

import java.awt.*;
import java.awt.event.*;
import java.util.Set;

import javax.swing.*;

import org.jdesktop.swingx.*;
import org.jdesktop.swingx.decorator.*;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.renderer.*;
import org.jdesktop.swingx.treetable.*;
import org.jvnet.lafwidget.LafWidget;
import org.jvnet.lafwidget.tabbed.DefaultTabPreviewPainter;
import org.jvnet.substance.*;
import org.jvnet.substance.api.SubstanceConstants.TabCloseKind;
import org.jvnet.substance.api.tabbed.*;

public class PhoneMeMajorPanel extends JPanel {
	private PhoneMeFrame mainFrame;
	private JTabbedPane centerPanel;

	public PhoneMeMajorPanel(PhoneMeFrame mainFrame) {
		super();
		setLayout(new BorderLayout());

		this.mainFrame = mainFrame;

		this.centerPanel = configureCenterPanel();
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

	private JTabbedPane configureCenterPanel() {

		// ****************************************************************************
		JTabbedPane centerPanel = new JTabbedPane();

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
		tree.updateUI();
		
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

		centerPanel.addTab("Contact Info", new ContactInfoPanel(this.mainFrame, null, false));
		
		centerPanel.addTab("Search Result", new SearchResultPanel(this.mainFrame));
		
		centerPanel.addTab("CommonLabel Show", new CommonLabelShowPanel(this.mainFrame));

		return centerPanel;
	}
	
	/**
	 * @return Component CurrentTab
	 */
	public Component getCurrentTab() {
		return centerPanel.getSelectedComponent();
	}

}