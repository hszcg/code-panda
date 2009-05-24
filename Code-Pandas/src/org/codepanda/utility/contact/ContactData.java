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
	/**
	 * 
	 */
	private static final long serialVersionUID = -8100955859773567957L;
	public String contactName;
	public ArrayList<String> phoneNumberList;
	public ArrayList<String> emailAddresseList;
	public ArrayList<String> contactAddressList;
	public ArrayList<String> workingDepartmentList;
	public ArrayList<String> imContactInformationList;
	public String contactBirthday;
	public HeadImage headImage;
	public ArrayList<String> urlList;
	public ArrayList<String> commonLabelList;
	public ArrayList<String> groupList;
	public ArrayList<RelationLabel> relationLabelList;
	public Integer ISN;
	public ContactData()
	{
		contactName=new String();
		phoneNumberList=new ArrayList<String>();
		emailAddresseList=new ArrayList<String>();
		contactAddressList=new ArrayList<String>();
		workingDepartmentList=new ArrayList<String>();
		imContactInformationList=new ArrayList<String>();
		contactBirthday=new String();
		headImage=null;
		urlList=new ArrayList<String>();
		commonLabelList=new ArrayList<String>();
		groupList=new ArrayList<String>();
		relationLabelList=new ArrayList<RelationLabel>();
		 
		
	}
}
