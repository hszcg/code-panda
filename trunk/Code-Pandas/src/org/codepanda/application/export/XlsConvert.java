package org.codepanda.application.export;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.ContactSectionType;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.data.PhoneMeConstants;

public class XlsConvert {
	public void convert(String url){
		try {
			
			// TODO delete  [ and ]
			ContactOperations[] contactList = 
				(ContactOperations[]) DataPool.getInstance()
			.getAllContactISNMap().values().toArray(new ContactOperations[0]);
			String[] labelList = (String[]) PhoneMeConstants.getInstance()
			.getContactSectionList().values().toArray(new String[0]);
		
			File tempFile = new File(url);
			if(tempFile.exists())
				tempFile.delete();
			WritableWorkbook workbook = 
				Workbook.createWorkbook(tempFile);
			
			WritableSheet sheet = 
        		workbook.createSheet("First Sheet", 0);
			
			Label label;
			int firstLineIndex = 0;
			
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.CONTACT_NAME)); 
        	sheet.addCell(label);
        	firstLineIndex++;
        	
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.PHONE_NUMBER)); 
        	sheet.addCell(label);
        	firstLineIndex++;
        	
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.EMAIL_ADDR)); 
        	sheet.addCell(label);
        	firstLineIndex++;
        	
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.HOME_ADDR)); 
        	sheet.addCell(label);
        	firstLineIndex++;
        	
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.WORK_OFFICE)); 
        	sheet.addCell(label);
        	firstLineIndex++;
        	
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.IM)); 
        	sheet.addCell(label);
        	firstLineIndex++;
        	
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.BIRTHDAY)); 
        	sheet.addCell(label);
        	firstLineIndex++;
        	
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.WEB_URL)); 
        	sheet.addCell(label);
        	firstLineIndex++;
        	
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.COMMON_LABEL)); 
        	sheet.addCell(label);
        	firstLineIndex++;
        	
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.GROUP_LABEL)); 
        	sheet.addCell(label);
        	firstLineIndex++;
        	
        	label = new Label
        	(firstLineIndex, 0, 
        			PhoneMeConstants.getInstance().getContactSectionList().
        			get(ContactSectionType.RELATION_LABEL)); 
        	sheet.addCell(label);
        	firstLineIndex++;
			
        	int column = 0;
			for(int index=0;index<contactList.length;index++){
				Label tempLabel = new Label
				(column, index + 1, contactList[index].getContactName()); 
				sheet.addCell(tempLabel);
			}
			column ++;
			
			for(int index=0;index<contactList.length;index++){
				Label tempLabel = new Label
				(column, index + 1, 
						contactList[index].getPhoneNumberList().toString());
				sheet.addCell(tempLabel);
			}
			column ++;
			
			for(int index=0;index<contactList.length;index++){
				Label tempLabel = new Label
				(column, index + 1, 
						contactList[index].getEmailAddresseList().toString());
				sheet.addCell(tempLabel);
			}
			column ++;
			
			for(int index=0;index<contactList.length;index++){
				Label tempLabel = new Label
				(column, index + 1, 
						contactList[index].getContactAddressList().toString());
				sheet.addCell(tempLabel);
			}
			column ++;
			
			for(int index=0;index<contactList.length;index++){
				Label tempLabel = new Label
				(column, index + 1, 
				contactList[index].getWorkingDepartmentList().toString());
				sheet.addCell(tempLabel);
			}
			column ++;
			
			for(int index=0;index<contactList.length;index++){
				Label tempLabel = new Label
				(column, index + 1, 
				contactList[index].getImContactInformationList().toString());
				sheet.addCell(tempLabel);
			}
			column ++;
			
			for(int index=0;index<contactList.length;index++){
				Label tempLabel = new Label
				(column, index + 1, 
				contactList[index].getContactBirthday().toString());
				sheet.addCell(tempLabel);
			}
			column ++;
			
			for(int index=0;index<contactList.length;index++){
				Label tempLabel = new Label
				(column, index + 1, 
				contactList[index].getUrlList().toString());
				sheet.addCell(tempLabel);
			}
			column ++;
			
			for(int index=0;index<contactList.length;index++){
				Label tempLabel = new Label
				(column, index + 1, 
				contactList[index].getCommonLabelList().toString());
				sheet.addCell(tempLabel);
			}
			column ++;
			
			for(int index=0;index<contactList.length;index++){
				Label tempLabel = new Label
				(column, index + 1, 
				contactList[index].getGroupList().toString());
				sheet.addCell(tempLabel);
			}
			column ++;
			
			for(int index=0;index<contactList.length;index++){
				StringBuffer relationLabel = new StringBuffer();
				for(int jindex = 0; jindex<contactList[index].
					getRelationLabelList().size();jindex ++){
					relationLabel.append(contactList[index].
						getRelationLabelList().get(jindex).getLabelName());
					relationLabel.append(", ");
					relationLabel.append(DataPool.getInstance().
						getAllContactISNMap().get(contactList[index].
						getRelationLabelList().get(jindex).
						getRelationObjectISN()).getContactName());
					if(index != contactList.length -1)
						relationLabel.append(";");
					else
						relationLabel.append(".");
				}
				Label tempLabel = new Label
				(column, index + 1, relationLabel.toString());
				sheet.addCell(tempLabel);
			}
			
			workbook.write(); 
        	workbook.close();
		}catch (Exception e) {   
            e.printStackTrace();   
        }
	}
}
