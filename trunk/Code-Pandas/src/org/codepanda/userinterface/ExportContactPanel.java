package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileFilter;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.ExportContactMessageHandler;
import org.codepanda.userinterface.utility.ExtensionFileFilter;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.data.PhoneMeConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class ExportContactPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3772702803606122407L;
	final private PhoneMeFrame localFrame;
	private JLabel daochu;
	private JComboBox groupBox;
	
	private JLabel xuanxiang;
	private ButtonGroup selectionGroup;
	private JRadioButton csv;
	private JRadioButton xls;
	
	private JButton action;
	
	public ExportContactPanel(PhoneMeFrame frame){
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
		
		xuanxiang = new JLabel("导出选项");
		xuanxiang.setFont(daochu.getFont().deriveFont(
				Font.BOLD, (float) 12.0));
		builder.add(xuanxiang, cc.xy(2, 2));
		
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
		System.out.println("联系人导出");

		JFileChooser playerHeadChooser = new JFileChooser();
		playerHeadChooser.setDialogTitle("Contact Export Chooser");

		File dir = new File("./");
		StringBuffer url = null;
		if (dir.isDirectory())
			playerHeadChooser.setCurrentDirectory(dir);

		FileFilter playerHeadFileFilter;
		if(csv.isSelected()){
			playerHeadFileFilter = new ExtensionFileFilter(
				"Support Files (*.csv)", new String[] { ".csv"});
			playerHeadChooser.addChoosableFileFilter
			(playerHeadFileFilter);
		}
		
		if(xls.isSelected()){
			playerHeadFileFilter = new ExtensionFileFilter(
				"Support Files (*.xls)", new String[] {".xls" });
			playerHeadChooser.addChoosableFileFilter
			(playerHeadFileFilter);
		}

		int result = playerHeadChooser.showOpenDialog(localFrame);

		if (result == JFileChooser.CANCEL_OPTION)
			return;
		
		File selectedFile = playerHeadChooser.getSelectedFile();
		try {
			url = new StringBuffer(selectedFile.getAbsolutePath());
			if(url.length() == 0)
				return;
			System.out.println("EXPORT\n" + url);
		} catch (Exception error) {
			error.printStackTrace();
		}

		// TODO url == null
		StringBuffer tempMessage = new StringBuffer();
		if (csv.isSelected())
		{
			tempMessage.append(MyXMLMaker.addTag("Type", "csv"));
			if(!url.toString().endsWith(".csv"))
				url = url.append(".csv");
		}
		if (xls.isSelected())
		{
			tempMessage.append(MyXMLMaker.addTag("Type", "xls"));
			if(!url.toString().endsWith(".xls"))
				url = url.append(".xls");
		}

		tempMessage.append(MyXMLMaker.addTag("Url", url.toString()));

		String xml = MyXMLMaker.addTag("ExportContact", tempMessage
				.toString());
		xml = MyXMLMaker.addTag("com", xml);

		System.out.println("EXPORT_CONTACT\n" + xml);

		CommandVisitor exportContactCommandVisitor = new CommandVisitor(
				CommandType.EXPORT_CONTACT, xml);
		ExportContactMessageHandler exportContactMessageHandler = new ExportContactMessageHandler();
		exportContactMessageHandler
				.executeCommand(exportContactCommandVisitor);
	}
}
