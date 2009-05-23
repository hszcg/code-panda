package org.codepanda.userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.SearchContactMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.data.PhoneMeConstants;
import org.jdesktop.swingx.JXDatePicker;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class SearchPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9032156600157133157L;

	private final PhoneMeFrame parentFrame;
	private JTextField nameField;
	private JTextField teleField;

	private JTextField emailField;
	private JTextField addressField;

	private JTextField workField;
	private JTextField imField;

	private JXDatePicker birthdayField;
	private static SimpleDateFormat birthdayDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	private JTextField webField;

	private JTextField commonLabelField;
	private JComboBox groupBox;

	private JComboBox relationBox;
	private JComboBox contactBox;
	private ArrayList<String> allContactName;

	private ButtonGroup selectionGroup;
	private JRadioButton muohu;
	private JRadioButton jingque;
	private JButton action;

	public SearchPanel(final PhoneMeFrame mainFrame) {
		super();
		parentFrame = mainFrame;
		FormLayout mainLayout = new FormLayout(
				"pref, 20dlu, pref, 10dlu, pref, 30dlu, pref, 10dlu, pref", // columns
				"p, 15dlu, p, 10dlu, p, 10dlu, p, 10dlu, p, 10dlu, p, 10dlu, p, "
						+ "10dlu, p, 10dlu, p, 10dlu, p"); // rows

		PanelBuilder builder = new PanelBuilder(mainLayout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();
		builder.addSeparator("高级搜索", cc.xyw(1, 1, 9));

		builder.addLabel("姓名", cc.xy(3, 3));
		nameField = new JTextField(15);
		builder.add(nameField, cc.xy(5, 3));
		builder.addLabel("联系电话", cc.xy(7, 3));
		teleField = new JTextField(15);
		builder.add(teleField, cc.xy(9, 3));

		builder.addLabel("电子邮箱", cc.xy(3, 5));
		emailField = new JTextField(15);
		builder.add(emailField, cc.xy(5, 5));
		builder.addLabel("家庭住址", cc.xy(7, 5));
		addressField = new JTextField(15);
		builder.add(addressField, cc.xy(9, 5));

		builder.addLabel("工作单位", cc.xy(3, 7));
		workField = new JTextField(15);
		builder.add(workField, cc.xy(5, 7));
		builder.addLabel("即时联系方式", cc.xy(7, 7));
		imField = new JTextField(15);
		builder.add(imField, cc.xy(9, 7));

		builder.addLabel("联系人生日", cc.xy(3, 9));
		birthdayField = new JXDatePicker();
		birthdayField.setFormats(birthdayDateFormat);
		builder.add(birthdayField, cc.xy(5, 9));
		builder.addLabel("Web地址", cc.xy(7, 9));
		webField = new JTextField(15);
		builder.add(webField, cc.xy(9, 9));

		builder.addLabel("普通标签", cc.xy(3, 11));
		commonLabelField = new JTextField();
		builder.add(commonLabelField, cc.xy(5, 11));
		builder.addLabel("所属分组", cc.xy(7, 11));
		groupBox = new JComboBox((String[]) PhoneMeConstants.getInstance()
				.getAllGroupList().toArray(new String[0]));
		groupBox.insertItemAt("(任意)", 0);
		groupBox.setSelectedIndex(0);
		builder.add(groupBox, cc.xy(9, 11));

		builder.addLabel("关系标签", cc.xy(3, 13));
		relationBox = new JComboBox((String[]) PhoneMeConstants.getInstance()
				.getAllRelationLabelName().toArray(new String[0]));

		allContactName = new ArrayList<String>();

		Iterator<Entry<Integer, ContactOperations>> it = DataPool.getInstance()
				.getAllContactISNMap().entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, ContactOperations> entry = (Entry<Integer, ContactOperations>) it
					.next();
			allContactName.add(entry.getValue().getContactName());
		}
		contactBox = new JComboBox((String[]) allContactName
				.toArray(new String[0]));

		builder.add(relationBox, cc.xy(5, 13));
		builder.add(contactBox, cc.xy(7, 13));

		muohu = new JRadioButton("模糊匹配");
		jingque = new JRadioButton("精确匹配");
		jingque.setSelected(true);
		selectionGroup = new ButtonGroup();
		selectionGroup.add(muohu);
		selectionGroup.add(jingque);
		builder.addLabel("匹配规则", cc.xy(3, 15));
		builder.add(muohu, cc.xy(5, 15));
		builder.add(jingque, cc.xy(5, 17));

		action = new JButton("搜索");
		builder.add(action, cc.xy(3, 19));
		action.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				// TODO make message Handler
				// 搜索联系人
				String xml = makeSearchMessageXML().toString();
				xml = MyXMLMaker.addTag("SearchContact", xml);
				xml = MyXMLMaker.addTag("com", xml);

				System.out.println("SEARCH_CONTACT\n" + xml);

				CommandVisitor searchContactCommandVisitor = new CommandVisitor(
						CommandType.SEARCH_CONTACT, xml);
				SearchContactMessageHandler searchContactMessageHandler = new SearchContactMessageHandler();
				ArrayList<Integer> resultContactList = (ArrayList<Integer>) searchContactMessageHandler
						.executeCommand(searchContactCommandVisitor);
				parentFrame.getMyPhoneMeMajorPanel().addNewTab(
						"Search Result",
						new SearchResult(parentFrame, resultContactList)
								.getMainPanel());
			}
		});

		add(builder.getPanel());
	}

	public StringBuffer makeSearchMessageXML() {
		StringBuffer message = new StringBuffer();
		if (muohu.isSelected())
			message.append(MyXMLMaker.addTag("BlurSearch", "0"));
		else
			message.append(MyXMLMaker.addTag("BlurSearch", "1"));
		if (nameField.getText().length() != 0)
			message.append(MyXMLMaker
					.addTag("ContactName", nameField.getText()));
		if (teleField.getText().length() != 0)
			message.append(MyXMLMaker.addTag("Telephone", teleField.getText()));
		if (emailField.getText().length() != 0)
			message.append(MyXMLMaker.addTag("Email", emailField.getText()));
		if (addressField.getText().length() != 0)
			message
					.append(MyXMLMaker
							.addTag("Address", addressField.getText()));
		if (workField.getText().length() != 0)
			message.append(MyXMLMaker.addTag("Office", workField.getText()));
		if (imField.getText().length() != 0)
			message.append(MyXMLMaker.addTag("IMContact", imField.getText()));

		// TODO birthday message
		if (birthdayField.getDate() != null)
			message.append(MyXMLMaker.addTag("Birthday", birthdayDateFormat
					.format(birthdayField.getDate())));

		if (workField.getText().length() != 0)
			message.append(MyXMLMaker.addTag("Office", workField.getText()));

		if (webField.getText().length() != 0)
			message.append(MyXMLMaker.addTag("URL", webField.getText()));

		if (commonLabelField.getText().length() != 0)
			message.append(MyXMLMaker.addTag("CommonLabel", commonLabelField
					.getText()));

		if (!groupBox.getSelectedItem().toString().equals("(任意)"))
			message.append(MyXMLMaker.addTag("Group", groupBox
					.getSelectedItem().toString()));

		if (!relationBox.getSelectedItem().toString().equals("(任意)")
				&& !contactBox.getSelectedItem().toString().equals("(任意)")) {
			StringBuffer relation = new StringBuffer();
			relation.append(MyXMLMaker.addTag("LabelName", relationBox
					.getSelectedItem().toString()));
			relation.append(MyXMLMaker.addTag("DestName", contactBox
					.getSelectedItem().toString()));
			message.append(MyXMLMaker.addTag("RelationLabel", relation
					.toString()));
		}

		return message;
	}
}
