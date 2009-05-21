/**
 * 
 */
package org.codepanda.utility.contact;

import java.io.Serializable;
import java.util.ArrayList;

import org.codepanda.utility.label.RelationLabel;


/**
 *
 * @author hszcg
 * @version 4.16.01
 *
 */
public class ContactData implements Serializable {
	public String contactName;
	public ArrayList<String> phoneNumberList;
	public ArrayList<String> emailAddresseList;
	public ArrayList<String> contactAddressList;
	public ArrayList<String> workingDepartmentList;
	public ArrayList<String> imContactInformationList;
	public Birthday contactBirthday;
	public HeadImage headImage;
	public ArrayList<String> urlList;
	public ArrayList<String> commonLabelList;
	public ArrayList<String> groupList;
	public ArrayList<RelationLabel> relationLabelList;
	public Integer ISN;
}
