package org.codepanda.application.export;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.ContactSectionType;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.data.PhoneMeConstants;

public class CsvExport {
	
	public void convert(String filename){
		FileWriter fw;
		try {
			fw = new FileWriter(filename);
			
			// write the column-title-name of CSV
			fw.write(
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.CONTACT_NAME)+","+
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.PHONE_NUMBER)+","+
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.EMAIL_ADDR)+","+
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.HOME_ADDR)+","+
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.WORK_OFFICE)+","+
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.IM)+","+
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.BIRTHDAY)+","+
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.WEB_URL)+","+
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.COMMON_LABEL)+","+
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.GROUP_LABEL)+","+
					PhoneMeConstants.getInstance().getContactSectionList().get(ContactSectionType.RELATION_LABEL)+
					"\r\n");
			
			// get the contact list of current user
			ArrayList<ContactOperations> contactList = new ArrayList<ContactOperations>();
			String userName = DataPool.getInstance().getCurrentUser().getUserName();
			DataPool.getInstance().getDb().getContactData(userName, contactList);
			
			// write every contactData into CSV
			for(ContactOperations ite : contactList){
//				fw.write(
//						ite.getContactName()+","+
//						ite.getPhoneNumberList().get(0)+","+
//						ite.getEmailAddresseList().get(0)+","+
//						ite.getContactAddressList().get(0)+","+
//						ite.getWorkingDepartmentList().get(0)+","+
//						ite.getImContactInformationList().get(0)+","+
//						ite.getContactBirthday()+","+
//						ite.getUrlList().get(0)+","+
//						ite.getCommonLabelList().get(0)+","+
//						ite.getGroupList().get(0)+","+
//						ite.getRelationLabelList().get(0).getLabelName()+
//						"\r\n");
				if(ite.getContactName() != null)
					fw.write(ite.getContactName());
				fw.write(",");
				if(!ite.getPhoneNumberList().isEmpty())
					fw.write(ite.getPhoneNumberList().get(0));
				fw.write(",");
				if(!ite.getEmailAddresseList().isEmpty())
					fw.write(ite.getEmailAddresseList().get(0));
				fw.write(",");
				if(!ite.getContactAddressList().isEmpty())
					fw.write(ite.getContactAddressList().get(0));
				fw.write(",");
				if(!ite.getWorkingDepartmentList().isEmpty())
					fw.write(ite.getWorkingDepartmentList().get(0));
				fw.write(",");
				if(!ite.getImContactInformationList().isEmpty())
					fw.write(ite.getImContactInformationList().get(0));
				fw.write(",");
				if(ite.getContactBirthday() != null)
					fw.write(ite.getContactBirthday());
				fw.write(",");
				if(!ite.getUrlList().isEmpty())
					fw.write(ite.getUrlList().get(0));
				fw.write(",");
				if(!ite.getCommonLabelList().isEmpty())
					fw.write(ite.getCommonLabelList().get(0));
				fw.write(",");
				if(!ite.getGroupList().isEmpty())
					fw.write(ite.getGroupList().get(0));
				fw.write(",");
				if(!ite.getRelationLabelList().isEmpty())
					fw.write(ite.getRelationLabelList().get(0).getLabelName());
				fw.write("\r\n");
			}
			
			// close the file writer
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}