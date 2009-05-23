package org.codepanda.userinterface;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.PhoneMeConstants;

public class SingleResultPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3302839879471641065L;
	private PhoneMeFrame parentFrame;
	private ContactOperations myContact;
	private static Image defaultImage;

	public SingleResultPanel(PhoneMeFrame phoneMeFrame,
			final ContactOperations c) {
		// TODO Auto-generated constructor stub
		super();
		this.parentFrame = phoneMeFrame;
		this.myContact = c;

		try {
			if (SingleResultPanel.defaultImage == null)
				SingleResultPanel.defaultImage = ImageIO
						.read(getClass()
								.getResource(
										PhoneMeConstants.getInstance().DEFAULT_IMAGE_URL));

			configurePanel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void configurePanel() throws IOException {
		// TODO Auto-generated method stub
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(new BevelBorder(BevelBorder.RAISED));

		//
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		String name = myContact.getContactName();
		if (name == null)
			name = "N/A";
		JLabel nameLabel = new JLabel(name);
		nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD,
				(float) 15.0));

		String phoneNumber = "N/A";
		ArrayList<String> phoneNumberList = myContact.getPhoneNumberList();
		if (phoneNumberList != null && phoneNumberList.size() > 0)
			phoneNumber = myContact.getPhoneNumberList().get(0);

		JLabel phoneLabel = new JLabel(phoneNumber);
		phoneLabel.setFont(phoneLabel.getFont().deriveFont(Font.BOLD,
				(float) 15.0));

		// String emailAddress = "N/A";
		// ArrayList<String> emailList = myContact.getEmailAddresseList();
		// if (emailList != null && emailList.size() > 0)
		// emailAddress = myContact.getEmailAddresseList().get(0);
		// JLabel emailLabel = new JLabel("E-mail Address £º" + emailAddress);

		infoPanel.add(Box.createGlue());
		infoPanel.add(nameLabel);
		infoPanel.add(Box.createGlue());
		infoPanel.add(phoneLabel);
		infoPanel.add(Box.createGlue());
		// infoPanel.add(emailLabel);
		// infoPanel.add(Box.createGlue());

		JButton contactHeadImage = new JButton();

		ImageIcon imageIcon = new ImageIcon(SingleResultPanel.defaultImage
				.getScaledInstance(64, 64, Image.SCALE_DEFAULT));

		if (myContact.getHeadImage() != null
				&& myContact.getHeadImage().getMyImageIcon() != null) {
			imageIcon = new ImageIcon(myContact.getHeadImage().getMyImageIcon()
					.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		}
		contactHeadImage.setIcon(imageIcon);

		contactHeadImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = myContact.getContactName();
				if (name == null)
					name = "N/A";

				parentFrame.getMyPhoneMeMajorPanel().addNewTab(
						name,
						new ContactInfoPanel(parentFrame, myContact, false,
								ContactInfoPanel.CONTACT_INFO_PANEL));
			}

		});

		this.add(contactHeadImage);
		this.add(Box.createHorizontalStrut(8));
		this.add(infoPanel);
	}
}
