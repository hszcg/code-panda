package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.EditCommonLabelMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.data.DataPool;

public class SingleCommonLabelPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -489177675135631883L;
	private PhoneMeFrame parentFrame;
	private String oldLabelName;
	private String newLabelName;

	private JCheckBox selectBox;
	private JTextField labelNameField;
	private JButton comfirmButton;

	/**
	 * @param parentFrame
	 * @param labelName
	 */
	public SingleCommonLabelPanel(PhoneMeFrame parentFrame, String labelName) {
		// TODO Auto-generated constructor stub
		super();
		this.parentFrame = parentFrame;
		this.oldLabelName = labelName;
		this.newLabelName = labelName;

		configurePanel();
	}

	/**
	 * 
	 */
	private void configurePanel() {
		// TODO Auto-generated method stub
		selectBox = new JCheckBox();
		selectBox.setSelected(false);

		labelNameField = new JTextField(this.oldLabelName);

		comfirmButton = new JButton("х╥хо");
		comfirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String input = labelNameField.getText().trim();
				if (DataPool.getInstance().getAllCommonLabelDataMap()
						.containsKey(input) == false) {
					// EDIT Contact
					StringBuffer buffer = new StringBuffer(50);
					buffer.append(MyXMLMaker.addTag("OldName", oldLabelName));
					buffer.append(MyXMLMaker.addTag("NewName", input));
					String xml = MyXMLMaker.addTag("Label", buffer.toString());
					xml = MyXMLMaker.addTag("EditCommonLabel", xml);
					xml = MyXMLMaker.addTag("com", xml);

					System.out.println("EDIT_COMMON_LABEL\n" + xml);

					CommandVisitor editCommonLabelCommandVisitor = new CommandVisitor(
							CommandType.EDIT_COMMON_LABEL, xml);
					EditCommonLabelMessageHandler editCommonLabelMessageHandler = new EditCommonLabelMessageHandler();
					editCommonLabelMessageHandler
							.executeCommand(editCommonLabelCommandVisitor);
					
					setEditable(false);
					selectBox.setSelected(false);
				}
			}

		});

		this.setLayout(new BorderLayout());
		JPanel major = new JPanel(new FlowLayout());
		major.add(selectBox);
		major.add(labelNameField);
		this.add(major, BorderLayout.CENTER);
		this.add(comfirmButton, BorderLayout.SOUTH);
		this.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

		setEditable(false);
	}

	/**
	 * @param isEditable
	 */
	public void setEditable(boolean isEditable) {
		selectBox.setVisible(isEditable);
		labelNameField.setEditable(isEditable);
		comfirmButton.setVisible(isEditable);
	}
	
	/**
	 * @return
	 */
	public boolean isSelected(){
		return selectBox.isSelected();
	}

	/**
	 * @return
	 */
	public String getLabelName() {
		// TODO Auto-generated method stub
		return oldLabelName;
	}

}
