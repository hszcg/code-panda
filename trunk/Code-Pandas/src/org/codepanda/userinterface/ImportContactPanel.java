package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.codepanda.utility.data.PhoneMeConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class ImportContactPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 897652141435910105L;
	final private PhoneMeFrame localFrame;
	private JLabel daochu;
	private JComboBox groupBox;
	
	private ButtonGroup selectionGroup;
	private JRadioButton csv;
	private JRadioButton xls;
	
	private JButton action;
	
	public ImportContactPanel(PhoneMeFrame frame){
		super();
		localFrame = frame;
		
		setLayout(new BorderLayout());
		
		FormLayout upperlayout = new FormLayout(
				"1dlu, pref", // columns
				"1dlu, p, 8dlu, p, 20dlu"); // rows

		PanelBuilder upbuilder = new PanelBuilder(upperlayout);
		upbuilder.setDefaultDialogBorder();

		CellConstraints upcc = new CellConstraints(); 
		daochu = new JLabel("导出");
		daochu.setFont(daochu.getFont().deriveFont(
				Font.BOLD, (float) 12.0));
		upbuilder.add(daochu, upcc.xy(2, 2));
		upbuilder.addLabel
		("导出联系人以便您可以将其传送到其他帐户或离线保存", 
				upcc.xy(2, 4));
		add(upbuilder.getPanel(), "North");
		
		FormLayout layout = new FormLayout(
			"10dlu, pref, 3dlu, pref", // columns
			"15dlu, p, 6dlu, p, 15dlu, p, 6dlu, p, 3dlu, p, 20dlu, p ,1dlu"); // rows

		PanelBuilder builder = new PanelBuilder(layout);
		upbuilder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints(); 
		builder.addLabel("导出选项",cc.xy(2, 2));
		
		builder.addLabel("选择要导出分组", cc.xy(2, 4));
		groupBox = new JComboBox((String[]) PhoneMeConstants.getInstance()
				.getAllGroupList().toArray(new String[0]));
		groupBox.insertItemAt("所有分组", 0);
		groupBox.setSelectedIndex(0);
		builder.add(groupBox, cc.xy(4, 4));
		
		builder.addLabel("选择导出的文件格式", cc.xy(2, 6));
		
		selectionGroup = new ButtonGroup();
		csv = new JRadioButton
		("CSV 格式");
		csv.setSelected(true);
		xls = new JRadioButton
		("EXCEL 的XLS格式");
		selectionGroup.add(csv);
		selectionGroup.add(xls);
		builder.add(csv, cc.xy(2, 8));
		builder.add(xls, cc.xy(2, 10));
		
		action = new JButton("进行导出操作");
		builder.add(action, cc.xy(2, 12));
		action.addActionListener(this);
		
		add(builder.getPanel(), "Center");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
