package org.codepanda.userinterface;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXPanel;

public class SearchResultPanel extends JXPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6855596910770372772L;
	private PhoneMeFrame parentFrame;
	private JTable searchResultTable;

	public SearchResultPanel(PhoneMeFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super();
		this.parentFrame = mainFrame;
		configureSearchResultTable();
	}

	private void configureSearchResultTable() {
		// TODO Auto-generated method stub
		String[] headers = { "Name", "Value" };
		String[][] cellData = new String[11][2];
		cellData[0][0] = "  Name";
		cellData[1][0] = "  Number";
		cellData[2][0] = "  State";
		cellData[3][0] = "  Role";
		cellData[4][0] = "  Defense";
		cellData[5][0] = "  Dribble";
		cellData[6][0] = "  Energy";
		cellData[7][0] = "  GK";
		cellData[8][0] = "  Pass";
		cellData[9][0] = "  Shot";
		cellData[10][0] = "  Speed";

		cellData[0][1] = "  Sa";
		cellData[1][1] = "  0";
		cellData[2][1] = "  1";
		cellData[3][1] = "  1";
		cellData[4][1] = "  1";
		cellData[5][1] = "  1";
		cellData[6][1] = "  1";
		cellData[7][1] = "  1";
		cellData[8][1] = "  1";
		cellData[9][1] = "  1";
		cellData[10][1] = "  1";

		DefaultTableModel model = new DefaultTableModel(cellData, headers) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8009739084051963944L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		searchResultTable = new JTable(model);
		searchResultTable
		.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

		this.add(searchResultTable, BorderLayout.CENTER);
	}

	/**
	 * @return the parentFrame
	 */
	public PhoneMeFrame getParentFrame() {
		return parentFrame;
	}
}
