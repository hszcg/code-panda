package org.codepanda.utility.user;

import java.util.ArrayList;

import org.codepanda.utility.contact.Birthday;
import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.HeadImage;
import org.codepanda.utility.label.RelationLabel;

public class User implements UserOperations,ContactOperations {
	private ContactData userContactData;
	
	private String userName;
	private String password;

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int varifyUserPassword(String inputPassword) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public ArrayList<String> getCommonLabelList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getContactAddressList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Birthday getContactBirthday() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContactName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getEmailAddresseList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getGroupList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HeadImage getHeadImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getISN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getImContactInformationList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getPhoneNumberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RelationLabel> getRelationLabelList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getUrlList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getWorkingDepartmentList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCommonLabelList(ArrayList<String> commonLabelList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContactAddressList(ArrayList<String> contactAddressList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContactBirthday(Birthday contactBirthday) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContactName(String contactName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEmailAddresseList(ArrayList<String> emailAddresseList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGroupList(ArrayList<String> groupList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeadImage(HeadImage headImage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setISN(String isn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setImContactInformationList(
			ArrayList<String> imContactInformationList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPhoneNumberList(ArrayList<String> phoneNumberList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRelationLabelList(ArrayList<RelationLabel> relationLabelList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUrlList(ArrayList<String> urlList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWorkingDepartmentList(ArrayList<String> workingDepartmentList) {
		// TODO Auto-generated method stub
		
	}

}
