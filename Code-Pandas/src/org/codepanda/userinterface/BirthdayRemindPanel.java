package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.StatContactMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.data.ContactSectionType;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class BirthdayRemindPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2951986593695307900L;
	private PhoneMeFrame localFrame;
	private JButton prevWeek;
	private JButton postWeek;
	private SearchResultPanel resultPanel;

	private ArrayList<Integer> resultContactList;
	
	private static final int UN_CLICKED = 0;
	private static final int PREV_WEEK = -1;
	private static final int POST_WEEK = -2;
	
	private int currentStatus = UN_CLICKED;

	private static SimpleDateFormat birthdayDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static final int PREV_DAY = 1;
	public static final int POST_DAY = 2;

	public BirthdayRemindPanel(PhoneMeFrame frame) {
		localFrame = frame;

		FormLayout layout = new FormLayout("170dlu, pref, 30dlu, pref, 50dlu", // columns
				"10dlu, p, 10dlu"); // rows

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

		prevWeek = new JButton("前一周过生日的联系人");
		prevWeek.addActionListener(this);
		postWeek = new JButton("下一周过生日的联系人");
		postWeek.addActionListener(this);

		builder.add(prevWeek, cc.xy(2, 2));
		builder.add(postWeek, cc.xy(4, 2));

		setLayout(new BorderLayout());
		add(builder.getPanel(), "North");

	}

	public String getDate(Date d, int day, int type) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		if (type == PREV_DAY)
			now.set(Calendar.DATE, now.get(Calendar.DATE) - day);

		if (type == POST_DAY)
			now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return birthdayDateFormat.format(now.getTime());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == prevWeek) {
			currentStatus = PREV_WEEK;
			showPrevWeek();
		}
		if (e.getSource() == postWeek) {
			currentStatus = POST_WEEK;
			showPostWeek();
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void showPostWeek() {
		// TODO Auto-generated method stub
		StringBuffer message = new StringBuffer();
		message.append(MyXMLMaker.addTag("StartBirthday", getDate(
				new Date(), 0, POST_DAY)));
		message.append(MyXMLMaker.addTag("EndBirthday", getDate(new Date(),
				7, POST_DAY)));
		String xml = MyXMLMaker.addTag("StatContact", message.toString());
		xml = MyXMLMaker.addTag("com", xml);

		CommandVisitor statContactCommandVisitor = new CommandVisitor(
				CommandType.STAT_CONTACT, xml);
		StatContactMessageHandler statContactMessageHandler = new StatContactMessageHandler();
		resultContactList = (ArrayList<Integer>) statContactMessageHandler
				.executeCommand(statContactCommandVisitor);

		resultPanel = new SearchResultPanel(localFrame, resultContactList,
				ContactSectionType.BIRTHDAY).getMainPanel();
		add(resultPanel, "Center");
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void showPrevWeek() {
		// TODO Auto-generated method stub
		StringBuffer message = new StringBuffer();
		message.append(MyXMLMaker.addTag("StartBirthday", getDate(
				new Date(), 7, PREV_DAY)));
		message.append(MyXMLMaker.addTag("EndBirthday", getDate(new Date(),
				0, PREV_DAY)));
		String xml = MyXMLMaker.addTag("StatContact", message.toString());
		xml = MyXMLMaker.addTag("com", xml);

		CommandVisitor statContactCommandVisitor = new CommandVisitor(
				CommandType.STAT_CONTACT, xml);
		StatContactMessageHandler statContactMessageHandler = new StatContactMessageHandler();
		resultContactList = (ArrayList<Integer>) statContactMessageHandler
				.executeCommand(statContactCommandVisitor);

		resultPanel = new SearchResultPanel(localFrame, resultContactList,
				ContactSectionType.BIRTHDAY).getMainPanel();

		add(resultPanel, BorderLayout.CENTER);
	}

	/**
	 * @param updateISN
	 */
	public void updateAllResult(int updateISN) {
		// TODO Auto-generated method stub
		if(this.currentStatus == PREV_WEEK){
			showPrevWeek();
		} else if(this.currentStatus == POST_WEEK){
			showPostWeek();
		}
		
	}

	/**
	 * @param updateISNList
	 */
	public void updateAllResult(ArrayList<Integer> updateISNList) {
		// TODO Auto-generated method stub
		if(this.currentStatus == PREV_WEEK){
			showPrevWeek();
		} else if(this.currentStatus == POST_WEEK){
			showPostWeek();
		}
	}

}
