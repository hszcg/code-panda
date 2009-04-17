/**
 * 
 */
package org.codepanda.utility.contact;

import java.util.ArrayList;

import org.codepanda.utility.label.RelationLabel;


/**
 *
 * @author hszcg
 * @version 4.16.01
 *
 */
public abstract class ContactData {
	private String contactName;
	private ArrayList<String> phoneNumberList;
	private ArrayList<String> emailAddresseList;
	private ArrayList<String> contactAddressList;
	private ArrayList<String> workingDepartmentList;
	private ArrayList<String> imContactInformationList;
	private Birthday contactBirthday;
	private HeadImage headImage;
	private ArrayList<String> urlList;
	private ArrayList<String> commonLabelList;
	private ArrayList<String> groupList;
	private ArrayList<RelationLabel> relationLabelList;
	
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	
	/**
	 * @param phoneNumberList the phoneNumberList to set
	 */
	public void setPhoneNumberList(ArrayList<String> phoneNumberList) {
		this.phoneNumberList = phoneNumberList;
	}
	
	/**
	 * @return the phoneNumberList
	 */
	public ArrayList<String> getPhoneNumberList() {
		return phoneNumberList;
	}
	
	/**
	 * @param emailAddresseList the emailAddresseList to set
	 */
	public void setEmailAddresseList(ArrayList<String> emailAddresseList) {
		this.emailAddresseList = emailAddresseList;
	}
	
	/**
	 * @return the emailAddresseList
	 */
	public ArrayList<String> getEmailAddresseList() {
		return emailAddresseList;
	}
	
	/**
	 * @param contactAddressList the contactAddressList to set
	 */
	public void setContactAddressList(ArrayList<String> contactAddressList) {
		this.contactAddressList = contactAddressList;
	}
	
	/**
	 * @return the contactAddressList
	 */
	public ArrayList<String> getContactAddressList() {
		return contactAddressList;
	}
	
	/**
	 * @param workingDepartmentList the workingDepartmentList to set
	 */
	public void setWorkingDepartmentList(ArrayList<String> workingDepartmentList) {
		this.workingDepartmentList = workingDepartmentList;
	}
	
	/**
	 * @return the workingDepartmentList
	 */
	public ArrayList<String> getWorkingDepartmentList() {
		return workingDepartmentList;
	}
	
	/**
	 * @param imContactInformationList the imContactInformationList to set
	 */
	public void setImContactInformationList(ArrayList<String> imContactInformationList) {
		this.imContactInformationList = imContactInformationList;
	}
	
	/**
	 * @return the imContactInformationList
	 */
	public ArrayList<String> getImContactInformationList() {
		return imContactInformationList;
	}
	
	/**
	 * @param contactBirthday the contactBirthday to set
	 */
	public void setContactBirthday(Birthday contactBirthday) {
		this.contactBirthday = contactBirthday;
	}
	
	/**
	 * @return the contactBirthday
	 */
	public Birthday getContactBirthday() {
		return contactBirthday;
	}
	
	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(HeadImage headImage) {
		this.headImage = headImage;
	}
	
	/**
	 * @return the headImage
	 */
	public HeadImage getHeadImage() {
		return headImage;
	}
	
	/**
	 * @param urlList the urlList to set
	 */
	public void setUrlList(ArrayList<String> urlList) {
		this.urlList = urlList;
	}
	
	/**
	 * @return the urlList
	 */
	public ArrayList<String> getUrlList() {
		return urlList;
	}
	
	/**
	 * @param relationLabelList the relationLabelList to set
	 */
	public void setRelationLabelList(ArrayList<RelationLabel> relationLabelList) {
		this.relationLabelList = relationLabelList;
	}
	
	/**
	 * @return the relationLabelList
	 */
	public ArrayList<RelationLabel> getRelationLabelList() {
		return relationLabelList;
	}

	/**
	 * @param commonLabelList the commonLabelList to set
	 */
	public void setCommonLabelList(ArrayList<String> commonLabelList) {
		this.commonLabelList = commonLabelList;
	}

	/**
	 * @return the commonLabelList
	 */
	public ArrayList<String> getCommonLabelList() {
		return commonLabelList;
	}

	/**
	 * @param groupList the groupList to set
	 */
	public void setGroupList(ArrayList<String> groupList) {
		this.groupList = groupList;
	}

	/**
	 * @return the groupList
	 */
	public ArrayList<String> getGroupList() {
		return groupList;
	}
	
}
