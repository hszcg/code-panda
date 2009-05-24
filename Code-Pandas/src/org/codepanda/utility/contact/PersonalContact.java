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
public class PersonalContact implements ContactOperations,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8192855369818866696L;
	private ContactData personalContactData;
	
	public PersonalContact()
	{
		personalContactData=new ContactData();
	}
	public  void setContactName(String Name)
	{
		personalContactData.contactName=Name;
	}
	public String getContactName()
	{
		return personalContactData.contactName;
	}
	public void setISN(Integer iSN)
	{
		personalContactData.ISN=iSN;
	}
	public Integer getISN()
	{
			return personalContactData.ISN;
	}
	public ArrayList<String> getPhoneNumberList()
	{
		return personalContactData.phoneNumberList;
	}
	public void setPhoneNumberList(ArrayList<String> phoneNumberList)
	{
		personalContactData.phoneNumberList=phoneNumberList;
	}
	public ArrayList<String> getEmailAddresseList()
	{
		return personalContactData.emailAddresseList;
	}
	public void setEmailAddresseList(ArrayList<String> emailAddressList)
	{
		personalContactData.emailAddresseList=emailAddressList;
	}
	public void setContactAddressList(ArrayList<String> contactaddressList)
	{
		personalContactData.contactAddressList=contactaddressList;
	}
	public  ArrayList<String> getContactAddressList()
	{
		return personalContactData.contactAddressList;
	}
	public void setHeadImage(HeadImage headImage)
	{
		personalContactData.headImage=headImage;
	}
	public  HeadImage getHeadImage()
	{
		return personalContactData.headImage;
	}
	public void setImContactInformationList(ArrayList<String> imContactInformationList)
	{
		personalContactData.imContactInformationList=imContactInformationList;
	}
	public  ArrayList<String> getImContactInformationList()
	{
		return personalContactData.imContactInformationList;
	}
	public void setContactBirthday(String contactBirthday)
	{
		personalContactData.contactBirthday=contactBirthday;
		System.out.println("Contact____Set"+contactBirthday.toString());
	}
	public  String getContactBirthday()
	{
		return personalContactData.contactBirthday;
	}
	public void setWorkingDepartmentList(ArrayList<String> workingDepartmentList)
	{
		personalContactData.workingDepartmentList=workingDepartmentList;
	}
	public  ArrayList<String> getWorkingDepartmentList()
	{
		return personalContactData.workingDepartmentList;
	}
	public void setUrlList(ArrayList<String> urlList)
	{
		personalContactData.urlList=urlList;
	}
	public  ArrayList<String> getUrlList()
	{
		return personalContactData.urlList;
	}
	public void setCommonLabelList(ArrayList<String> commonLabelList)
	{
		personalContactData.commonLabelList=commonLabelList;
	}
	public  ArrayList<String> getCommonLabelList()
	{
		return personalContactData.commonLabelList;
	}
	public void setGroupList(ArrayList<String> groupList)
	{
		personalContactData.groupList=groupList;
	}
	public  ArrayList<String> getGroupList()
	{
		return personalContactData.groupList;
	}
	public void setRelationLabelList(ArrayList<RelationLabel> relationLabelList )
	{
		personalContactData.relationLabelList=relationLabelList;
	}
	public  ArrayList<RelationLabel> getRelationLabelList()
	{
		return personalContactData.relationLabelList;
	}
}
