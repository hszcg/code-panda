package org.codepanda.userinterface.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.codepanda.userinterface.ContactInfoPanel;
import org.codepanda.userinterface.utility.ExtensionFileFilter;

/**
 * @author hszcg
 * 
 */
public class ContactHeadImageEditListener implements ActionListener {
	private final ContactInfoPanel myContactInfoPanel;

	public ContactHeadImageEditListener(ContactInfoPanel contactInfoPanel) {
		// TODO Auto-generated constructor stub
		this.myContactInfoPanel = contactInfoPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser playerHeadChooser = new JFileChooser();
		playerHeadChooser.setDialogTitle("Contact Head Image Chooser");

		File dir = new File("./resource/userpic");
		if (dir.isDirectory())
			playerHeadChooser.setCurrentDirectory(dir);

		FileFilter playerHeadFileFilter = new ExtensionFileFilter(
				"Contact Head Image Files (*.png, *.gif, *.jpg)", new String[] {
						".png", ".gif", ".jpg" });
		playerHeadChooser.addChoosableFileFilter(playerHeadFileFilter);

		int result = playerHeadChooser.showOpenDialog(myContactInfoPanel
				.getParentWindow());

		if (result == JFileChooser.CANCEL_OPTION)
			return;

		File selectedFile = playerHeadChooser.getSelectedFile();
		try {
			URL url = selectedFile.toURI().toURL();
			myContactInfoPanel.setContactHeadImage(ImageIO.read(url), url
					.toString());
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

}
