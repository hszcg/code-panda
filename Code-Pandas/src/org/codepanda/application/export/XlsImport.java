package org.codepanda.application.export;

import java.io.*; 
import java.util.ArrayList;
import java.util.Date; 
import jxl.*; 
import jxl.write.*; 

import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;

public class XlsImport {
	public void convert(String url){
		try {
			InputStream is = new FileInputStream("url");
			Workbook workbook = Workbook.getWorkbook(is);
			Sheet sheet = workbook.getSheet(0); 
			
			for(int index=1;index < sheet.getRows(); index ++){
				PersonalContact pc = new PersonalContact();
				for(int jindex = 0; jindex< sheet.getColumns(); jindex ++){
					if(jindex == 0){
						if(sheet.getCell(jindex, index).
								getContents().trim().length() != 0
								&& !sheet.getCell(jindex, index).
								getContents().trim().equals("[]")){
							pc.setContactName(sheet.getCell(jindex, index).
								getContents().trim());
						}
					}
					
					if(jindex == 1){
						ArrayList<String> phoneNumberList = 
							new ArrayList<String>();
						if(sheet.getCell(jindex, index).
								getContents().trim().length() != 0
								&& !sheet.getCell(jindex, index).
								getContents().trim().equals("[]")){
							phoneNumberList.add(sheet.getCell(jindex, index).
								getContents().trim());
							pc.setPhoneNumberList(phoneNumberList);
						}
					}
					
					if(jindex == 2){
						ArrayList<String> emailAddressList = 
							new ArrayList<String>();
						if(sheet.getCell(jindex, index).
								getContents().trim().length() != 0
								&& !sheet.getCell(jindex, index).
								getContents().trim().equals("[]")){
							emailAddressList.add(sheet.getCell(jindex, index).
								getContents().trim());
							pc.setEmailAddresseList(emailAddressList);
						}
					}
					
					if(jindex == 3){
						ArrayList<String> contactaddressList = 
							new ArrayList<String>();
						if(sheet.getCell(jindex, index).
								getContents().trim().length() != 0
								&& !sheet.getCell(jindex, index).
								getContents().trim().equals("[]")){
							contactaddressList.add(sheet.getCell(jindex, index).
								getContents().trim());
							pc.setContactAddressList(contactaddressList);
						}
					}
					
					if(jindex == 4){
						ArrayList<String> workingDepartmentList = 
							new ArrayList<String>();
						if(sheet.getCell(jindex, index).
								getContents().trim().length() != 0
								&& !sheet.getCell(jindex, index).
								getContents().trim().equals("[]")){
							workingDepartmentList.add(sheet.getCell(jindex, index).
								getContents().trim());
							pc.setWorkingDepartmentList(workingDepartmentList);
						}
					}
					
					if(jindex == 5){
						ArrayList<String> imContactInformationList = 
							new ArrayList<String>();
						if(sheet.getCell(jindex, index).
								getContents().trim().length() != 0
								&& !sheet.getCell(jindex, index).
								getContents().trim().equals("[]")){
							imContactInformationList.add(sheet.getCell(jindex, index).
								getContents().trim());
							pc.setImContactInformationList(imContactInformationList);
						}
					}
					
					if(jindex == 6){
						if(sheet.getCell(jindex, index).
								getContents().trim().length() != 0
								&& !sheet.getCell(jindex, index).
								getContents().trim().equals("[]")){
							pc.setContactBirthday(sheet.getCell(jindex, index).
								getContents().trim());
						}
					}
					
					if(jindex == 7){
						ArrayList<String> urlList = 
							new ArrayList<String>();
						if(sheet.getCell(jindex, index).
								getContents().trim().length() != 0
								&& !sheet.getCell(jindex, index).
								getContents().trim().equals("[]")){
							urlList.add(sheet.getCell(jindex, index).
								getContents().trim());
							pc.setUrlList(urlList);
						}
					}
					
					if(jindex == 8){
						ArrayList<String> commonLabelList = 
							new ArrayList<String>();
						if(sheet.getCell(jindex, index).
								getContents().trim().length() != 0
								&& !sheet.getCell(jindex, index).
								getContents().trim().equals("[]")){
							commonLabelList.add(sheet.getCell(jindex, index).
								getContents().trim());
							pc.setCommonLabelList(commonLabelList);
						}
					}
					
					if(jindex == 9){
						ArrayList<String> groupList = 
							new ArrayList<String>();
						if(sheet.getCell(jindex, index).
								getContents().trim().length() != 0
								&& !sheet.getCell(jindex, index).
								getContents().trim().equals("[]")){
							groupList.add(sheet.getCell(jindex, index).
								getContents().trim());
							pc.setGroupList(groupList);
						}
					}
					
					if(jindex == 10){
						// TODO Relation Label import
					}
				}
			}
			
			workbook.close();
		}catch (Exception e) {   
            e.printStackTrace();   
        } 
	}
}
