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
public interface ContactOperations {
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName);
	
	/**
	 * @return the contactName
	 */
	public String getContactName();
	
	/**
	 * @param phoneNumberList the phoneNumberList to set
	 */
	public void setPhoneNumberList(ArrayList<String> phoneNumberList);
	
	/**
	 * @return the phoneNumberList
	 */
	public ArrayList<String> getPhoneNumberList();
	
	/**
	 * @param emailAddresseList the emailAddresseList to set
	 */
	public void setEmailAddresseList(ArrayList<String> emailAddresseList);
	
	/**
	 * @return the emailAddresseList
	 */
	public ArrayList<String> getEmailAddresseList();
	
	/**
	 * @param contactAddressList the contactAddressList to set
	 */
	public void setContactAddressList(ArrayList<String> contactAddressList);
	
	/**
	 * @return the contactAddressList
	 */
	public ArrayList<String> getContactAddressList();
	
	/**
	 * @param workingDepartmentList the workingDepartmentList to set
	 */
	public void setWorkingDepartmentList(ArrayList<String> workingDepartmentList);
	
	/**
	 * @return the workingDepartmentList
	 */
	public ArrayList<String> getWorkingDepartmentList();
	
	/**
	 * @param imContactInformationList the imContactInformationList to set
	 */
	public void setImContactInformationList(ArrayList<String> imContactInformationList);
	
	/**
	 * @return the imContactInformationList
	 */
	public ArrayList<String> getImContactInformationList();
	
	/**
	 * @param contactBirthday the contactBirthday to set
	 */
	public void setContactBirthday(Birthday contactBirthday);
	
	/**
	 * @return the contactBirthday
	 */
	public Birthday getContactBirthday();
	
	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(HeadImage headImage);
	
	/**
	 * @return the headImage
	 */
	public HeadImage getHeadImage();
	
	/**
	 * @param urlList the urlList to set
	 */
	public void setUrlList(ArrayList<String> urlList);
	
	/**
	 * @return the urlList
	 */
	public ArrayList<String> getUrlList();
	
	/**
	 * @param relationLabelList the relationLabelList to set
	 */
	public void setRelationLabelList(ArrayList<RelationLabel> relationLabelList);
	
	/**
	 * @return the relationLabelList
	 */
	public ArrayList<RelationLabel> getRelationLabelList();

	/**
	 * @param commonLabelList the commonLabelList to set
	 */
	public void setCommonLabelList(ArrayList<String> commonLabelList);

	/**
	 * @return the commonLabelList
	 */
	public ArrayList<String> getCommonLabelList();

	/**
	 * @param groupList the groupList to set
	 */
	public void setGroupList(ArrayList<String> groupList);

	/**
	 * @return the groupList
	 */
	public ArrayList<String> getGroupList();

	/**
	 * @param iSN the iSN to set
	 */
	public void setISN(String iSN);

	/**
	 * @return the iSN
	 */
	public String getISN();
}
