package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.codepanda.utility.contact.ContactOperations;

public class SingleResultPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3302839879471641065L;
	private PhoneMeFrame parentFrame;
	private ContactOperations myContact;

	public SingleResultPanel(PhoneMeFrame phoneMeFrame,
			final ContactOperations c) {
		// TODO Auto-generated constructor stub
		super();
		this.parentFrame = phoneMeFrame;
		this.myContact = c;

		try {
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

		JButton contactHeadImage = new JButton();

		String url = "/userpic/user0.jpg";

		Image pic = ImageIO.read(this.getClass().getResource(url));
		Image tempImage = pic.getScaledInstance(64, 64, Image.SCALE_DEFAULT);
		ImageIcon imageIcon = new ImageIcon(tempImage);
		contactHeadImage.setIcon(imageIcon);
		contactHeadImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				parentFrame.getMyPhoneMeMajorPanel().addNewTab("test",
						new ContactInfoPanel(parentFrame, myContact, false));
			}

		});

		//
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		JLabel nameLabel = new JLabel("Name Test");
		JLabel phoneLabel = new JLabel("Phone Number Test");
		JLabel emailLabel = new JLabel("E-mail Test");

		infoPanel.add(Box.createGlue());
		infoPanel.add(nameLabel);
		infoPanel.add(Box.createGlue());
		infoPanel.add(phoneLabel);
		infoPanel.add(Box.createGlue());
		infoPanel.add(emailLabel);
		infoPanel.add(Box.createGlue());

		this.add(contactHeadImage);
		this.add(Box.createHorizontalStrut(8));
		this.add(infoPanel);
	}

}
