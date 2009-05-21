package org.codepanda.utility.user;

import java.io.Serializable;
import java.util.ArrayList;
import org.codepanda.database.DatabaseMagager;
import org.codepanda.database.DatabaseManagerFacade;

import org.codepanda.utility.contact.Birthday;
import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.HeadImage;
import org.codepanda.utility.label.RelationLabel;
public class User implements UserOperations,ContactOperations,Serializable {
	private ContactData userContactData=new ContactData();
	private String userName;
	private String password;
	private  DatabaseManagerFacade db;
	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public int varifyUserPassword(String inputPassword) {
		// TODO Auto-generated method stub
		//验证用户输入密码是否正确，正确返回1，错误返回0
		if(this.getPassword().equalsIgnoreCase(inputPassword))
		{
			return 1;
		}
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
		
		return userContactData.commonLabelList;
	}

	@Override
	public ArrayList<String> getContactAddressList() {
		// TODO Auto-generated method stub
		return userContactData.contactAddressList;
	}

	@Override
	public Birthday getContactBirthday() {
		// TODO Auto-generated method stub
		return userContactData.contactBirthday;
	}

	@Override
	public String getContactName() {
		// TODO Auto-generated method stub
		return userContactData.contactName;
	}

	@Override
	public ArrayList<String> getEmailAddresseList() {
		// TODO Auto-generated method stub
		return userContactData.emailAddresseList;
	}

	@Override
	public ArrayList<String> getGroupList() {
		// TODO Auto-generated method stub
		return userContactData.groupList;
	}

	@Override
	public HeadImage getHeadImage() {
		// TODO Auto-generated method stub
		return userContactData.headImage;
	}

	@Override
	public Integer getISN() {
		// TODO Auto-generated method stub
		return userContactData.ISN;
	}

	@Override
	public ArrayList<String> getImContactInformationList() {
		// TODO Auto-generated method stub
		return userContactData.imContactInformationList;
	}

	@Override
	public ArrayList<String> getPhoneNumberList() {
		// TODO Auto-generated method stub
		return userContactData.phoneNumberList;
	}

	@Override
	public ArrayList<RelationLabel> getRelationLabelList() {
		// TODO Auto-generated method stub
		return userContactData.relationLabelList;
	}

	@Override
	public ArrayList<String> getUrlList() {
		// TODO Auto-generated method stub
		return userContactData.urlList;
	}

	@Override
	public ArrayList<String> getWorkingDepartmentList() {
		// TODO Auto-generated method stub
		return userContactData.workingDepartmentList;
	}

	@Override
	public void setCommonLabelList(ArrayList<String> commonLabelList) {
		// TODO Auto-generated method stub
		userContactData.commonLabelList=commonLabelList;	   
	}

	@Override
	public void setContactAddressList(ArrayList<String> contactAddressList) {
		// TODO Auto-generated method stub
		userContactData.contactAddressList=contactAddressList;
	}

	@Override
	public void setContactBirthday(Birthday contactBirthday) {
		// TODO Auto-generated method stub
		userContactData.contactBirthday=contactBirthday;
	}

	@Override
	public void setContactName(String contactName) {
		// TODO Auto-generated method stub
		userContactData.contactName=contactName;
	}

	@Override
	public void setEmailAddresseList(ArrayList<String> emailAddresseList) {
		// TODO Auto-generated method stub
		userContactData.emailAddresseList=emailAddresseList;
	}

	@Override
	public void setGroupList(ArrayList<String> groupList) {
		// TODO Auto-generated method stub
		userContactData.groupList=groupList;
	}

	@Override
	public void setHeadImage(HeadImage headImage) {
		// TODO Auto-generated method stub
		userContactData.headImage=headImage;
	}

	@Override
	public void setISN(Integer isn) {
		// TODO Auto-generated method stub
		userContactData.ISN=isn;
	}

	@Override
	public void setImContactInformationList(
			ArrayList<String> imContactInformationList) {
		// TODO Auto-generated method stub
		userContactData.imContactInformationList=imContactInformationList;
	}

	@Override
	public void setPhoneNumberList(ArrayList<String> phoneNumberList) {
		// TODO Auto-generated method stub
		userContactData.phoneNumberList=phoneNumberList;
	}

	@Override
	public void setRelationLabelList(ArrayList<RelationLabel> relationLabelList) {
		// TODO Auto-generated method stub
		userContactData.relationLabelList=relationLabelList;
	}

	@Override
	public void setUrlList(ArrayList<String> urlList) {
		// TODO Auto-generated method stub
		userContactData.urlList=urlList;
	}

	@Override
	public void setWorkingDepartmentList(ArrayList<String> workingDepartmentList) {
		// TODO Auto-generated method stub
		userContactData.workingDepartmentList=workingDepartmentList;
	}
	
/*	public void createNewUser(User UserToBeAdded)
	{
		db=new DatabaseMagager();
		if(db.checkExistUser(UserToBeAdded.getUserName())!=1)
		{
			db.newUser(UserToBeAdded);
		}
		else
		{
			System.out.println("This User Has  Already Exist ");
			return ;
		}
	}
	public  void loginUser()
	{
		db=new DatabaseMagager();
		boolean flag1=false;
		boolean flag2=false;
		//首先检查是否存在
		if(db.checkExistUser(this.getUserName())==1)
		{
			flag1=true;
		}
		System.out.println("Already Login!!!");
		//数据库应该提供检查密码的操作
	//	if(db.checkPassword()==1)
		//{
		//	flag2=true;
	//	}
		//如果检查全部合格，则进行登录操作,显示所有的联系人列表
	}
	public void deleteUser()
	{
		db=new DatabaseMagager();
		//检查密码是否匹配，如果匹配则可以进行删除操作
	}
	public void changeUser()
	{
		db=new DatabaseMagager();
		//对数据进行修改
	}*/
}
