package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.SearchContactMessageHandler;
import org.codepanda.userinterface.messagehandler.StatContactMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.data.ContactSectionType;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.data.PhoneMeConstants;
import org.codepanda.utility.group.ContactGroup;
import org.jdesktop.swingx.JXDatePicker;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class StatContactPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5567407877191130269L;

	private final PhoneMeFrame parentFrame;
	
	private JComboBox group;
	private JButton groupConfirm;
	private JLabel groupResult;
	private JButton displayGroup;
	
	private JComboBox commonLabel;
	private JButton commonLabelConfirm;
	private JLabel commonLabelResult;
	private JButton displaycommonLabel;
	private Vector<String> allCommonLabel;
	
	private JXDatePicker startBirthdayField;
	private JXDatePicker endBirthdayField;
	private static SimpleDateFormat birthdayDateFormat = new SimpleDateFormat(
	"yyyy-MM-dd");
	private JButton birthdayConfirm;
	private JLabel birthdayResult;
	private JButton displayBirthday;
	
	private ArrayList<Integer> resultContactList;
	
	public StatContactPanel(final PhoneMeFrame mainFrame){
		parentFrame = mainFrame;
		
		FormLayout upperLayout = new FormLayout(
		"20dlu, pref, 30dlu, pref, 50dlu, pref, 50dlu, pref, 50dlu, pref, 1dlu", // columns
		"1dlu, p, 30dlu, p, 10dlu, p, 1dlu"); // rows
		
		PanelBuilder upperBuilder = new PanelBuilder(upperLayout);
		upperBuilder.setDefaultDialogBorder();

		CellConstraints upcc = new CellConstraints();
		upperBuilder.addSeparator
		("根据特定字段信息进行统计", upcc.xyw(2, 2, 9));
		upperBuilder.addLabel("根据分组", upcc.xy(2, 4));
		
		group = new JComboBox((String[]) PhoneMeConstants.
				getInstance().getAllGroupList().toArray(new String[0]));
		upperBuilder.add(group, upcc.xy(4, 4));
		
		groupConfirm = new JButton("开始统计");
		upperBuilder.add(groupConfirm, upcc.xy(6, 4));
		groupConfirm.addActionListener(this);
		
		groupResult = new JLabel();
		upperBuilder.add(groupResult, upcc.xy(8, 4));
		
		displayGroup = new JButton("显示相关联系人");
		upperBuilder.add(displayGroup, upcc.xy(10, 4));
		displayGroup.addActionListener(this);
		
		upperBuilder.addLabel("根据普通标签", upcc.xy(2, 6));
		
		allCommonLabel = new Vector<String>();
		Iterator<Entry<String, ContactGroup>> it = DataPool.
		getInstance().getAllCommonLabelDataMap().entrySet().iterator();
		while(it.hasNext()){
			Entry<String, ContactGroup> entry = 
				(Entry<String, ContactGroup>) it.next();
			allCommonLabel.add(entry.getKey());
		}
		commonLabel = new JComboBox(allCommonLabel);
		upperBuilder.add(commonLabel, upcc.xy(4, 6));
		
		commonLabelConfirm = new JButton("开始统计");
		upperBuilder.add(commonLabelConfirm, upcc.xy(6, 6));
		commonLabelConfirm.addActionListener(this);
		
		commonLabelResult = new JLabel();
		upperBuilder.add(commonLabelResult, upcc.xy(8, 6));
		
		displaycommonLabel = new JButton("显示相关联系人");
		upperBuilder.add(displaycommonLabel, upcc.xy(10, 6));
		displaycommonLabel.addActionListener(this);
		
		setLayout(new BorderLayout());
		add(upperBuilder.getPanel(), "North");
		
		FormLayout layout = new FormLayout(
			"20dlu, pref, 10dlu, pref, 50dlu, pref, 10dlu, pref, 5dlu", // columns
			"1dlu, p, 30dlu, p, 10dlu, p, 1dlu"); // rows
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		builder.addSeparator("根据生日区间统计", cc.xyw(2, 2, 7));
		
		builder.addLabel("起始生日", cc.xy(2, 4));
		startBirthdayField = new JXDatePicker();
		startBirthdayField.setFormats(birthdayDateFormat);
		builder.add(startBirthdayField, cc.xy(4, 4));
		
		builder.addLabel("终止生日", cc.xy(6, 4));
		endBirthdayField = new JXDatePicker();
		endBirthdayField.setFormats(birthdayDateFormat);
		builder.add(endBirthdayField, cc.xy(8, 4));
		
		birthdayConfirm = new JButton("开始统计");
		builder.add(birthdayConfirm, cc.xy(4, 6));
		birthdayConfirm.addActionListener(this);
		
		birthdayResult = new JLabel();
		builder.add(birthdayResult, cc.xy(6, 6));
		
		displayBirthday = new JButton("显示相关联系人");
		builder.add(displayBirthday, cc.xy(8, 6));
		
		add(builder.getPanel(), "Center");
		
		localSetVisible(false);
	}
	
	public void localSetVisible(boolean isVisible){
		displayGroup.setVisible(isVisible);
		displaycommonLabel.setVisible(isVisible);
		displayBirthday.setVisible(isVisible);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == groupConfirm){
			displayGroup.setVisible(true);
			// TODO Stat
			String xml = groupStat().toString();
			xml = MyXMLMaker.addTag("SearchContact", xml);
			xml = MyXMLMaker.addTag("com", xml);
			System.out.println("SEARCH_CONTACT group in stat action\n" + xml);

			CommandVisitor searchContactCommandVisitor = new CommandVisitor(
					CommandType.SEARCH_CONTACT, xml);
			SearchContactMessageHandler searchContactMessageHandler = new SearchContactMessageHandler();
			resultContactList = (ArrayList<Integer>) searchContactMessageHandler
					.executeCommand(searchContactCommandVisitor);
			groupResult.setText("结果个数: "+resultContactList.size());
		}
		
		if(e.getSource() == displayGroup){
			parentFrame.getMyPhoneMeMajorPanel().addNewTab(
					"Stat Result",
					new SearchResultPanel(parentFrame, resultContactList, ContactSectionType.GROUP_LABEL)
							.getMainPanel());
		}
		
		if(e.getSource() == commonLabelConfirm){
			localSetVisible(false);
			displaycommonLabel.setVisible(true);
			String xml = commonLabelStat().toString();
			xml = MyXMLMaker.addTag("SearchContact", xml);
			xml = MyXMLMaker.addTag("com", xml);
			System.out.println("SEARCH_CONTACT commonLabel in stat action\n" + xml);
			
			CommandVisitor searchContactCommandVisitor = new CommandVisitor(
					CommandType.SEARCH_CONTACT, xml);
			SearchContactMessageHandler searchContactMessageHandler = new SearchContactMessageHandler();
			resultContactList = (ArrayList<Integer>) searchContactMessageHandler
					.executeCommand(searchContactCommandVisitor);
			commonLabelResult.setText("结果个数: "+resultContactList.size());
		}
		
		if(e.getSource() == displaycommonLabel){
			parentFrame.getMyPhoneMeMajorPanel().addNewTab(
					"Stat Result",
					new SearchResultPanel(parentFrame, resultContactList, ContactSectionType.COMMON_LABEL)
							.getMainPanel());
		}
		
		if(e.getSource() == birthdayConfirm){
			displayBirthday.setVisible(true);
			StringBuffer message = new StringBuffer();
			if (startBirthdayField.getDate() != null)
				message.append(MyXMLMaker.addTag("StartBirthday", birthdayDateFormat
				.format(startBirthdayField.getDate())));
			else
				return;
			
			if(endBirthdayField.getDate() != null){
				
				message.append(MyXMLMaker.addTag("EndBirthday", 
						birthdayDateFormat.format(endBirthdayField.getDate())));
				}
			else
				return;
			String xml = MyXMLMaker.addTag("StatContact", message.toString());
			xml = MyXMLMaker.addTag("com", xml);
			
			System.out.println("birthday xml");
			System.out.println(xml);
//			TODO stat by birthday
			CommandVisitor statContactCommandVisitor = new CommandVisitor(
					CommandType.STAT_CONTACT, xml);
			StatContactMessageHandler statContactMessageHandler = new StatContactMessageHandler();
			resultContactList = (ArrayList<Integer>) statContactMessageHandler
			.executeCommand(statContactCommandVisitor);
			birthdayResult.setText("结果个数: "+resultContactList.size());
			parentFrame.getMyPhoneMeMajorPanel().addNewTab(
					"Stat Result",
					new SearchResultPanel(parentFrame, resultContactList, ContactSectionType.BIRTHDAY)
							.getMainPanel());
		}
	}
	
	public StringBuffer groupStat(){
		StringBuffer message = new StringBuffer();
		message.append(MyXMLMaker.addTag("BlurSearch", "1"));
		message.append(MyXMLMaker.addTag("Group", group
				.getSelectedItem().toString()));
		return message;
	}
	
	public StringBuffer commonLabelStat(){
		StringBuffer message = new StringBuffer();
		message.append(MyXMLMaker.addTag("BlurSearch", "1"));
		message.append(MyXMLMaker.addTag("CommonLabel", commonLabel
				.getSelectedItem().toString()));
		return message;
	}
}
