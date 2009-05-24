/**
 * 
 */
package org.codepanda.utility.contact;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * 
 * @author hszcg
 * @version 4.16.01
 *
 */
public class HeadImage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1574864907186977562L;
	private ImageIcon myImageIcon;

	/**
	 * @param myImageIcon the myImageIcon to set
	 */
	public void setMyImageIcon(ImageIcon myImageIcon) {
		this.myImageIcon = myImageIcon;
	}

	/**
	 * @return the myImageIcon
	 */
	public ImageIcon getMyImageIcon() {
		return myImageIcon;
	}
}
