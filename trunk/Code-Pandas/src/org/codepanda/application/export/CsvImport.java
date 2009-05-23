package org.codepanda.application.export;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;

public class CsvImport {
	public void convert(String filename){
		InputStreamReader fr = null;
		BufferedReader br = null;
		try {
			fr = new InputStreamReader(new FileInputStream(filename));
			br = new BufferedReader(fr);
			String line =null;
			String[] argsArr = null;
			System.out.println(line=br.readLine());
			while((line=br.readLine())!=null){
				int col = 0;
				PersonalContact pc = new PersonalContact();
				System.out.println(line);
				argsArr = line.split(",");
				for(int i=0;i<argsArr.length;i++){
					System.out.println(i+1+" : "+argsArr[i]);
				}
				/* argsArr.length */
				
				argsArr = line.split(",", 2);
				// contact name
				pc.setContactName(argsArr[0]);
				
				argsArr = line.split(",", 2);
				// phone
				ArrayList<String> phoneNumberList = new ArrayList<String>();
				phoneNumberList.add(argsArr[0]);
				pc.setPhoneNumberList(phoneNumberList);
				
				argsArr = line.split(",", 2);
				// email addr
				ArrayList<String> emailAddressList = new ArrayList<String>();
				emailAddressList.add(argsArr[0]);
				pc.setEmailAddresseList(emailAddressList);
				
				argsArr = line.split(",", 2);
				// home addr
				ArrayList<String> contactaddressList = new ArrayList<String>();
				contactaddressList.add(argsArr[0]);
				pc.setContactAddressList(contactaddressList);
				
				argsArr = line.split(",", 2);
				// work addr
				ArrayList<String> workingDepartmentList = new ArrayList<String>();
				workingDepartmentList.add(argsArr[0]);
				pc.setWorkingDepartmentList(workingDepartmentList);
				
				argsArr = line.split(",", 2);
				// IM
				ArrayList<String> imContactInformationList = new ArrayList<String>();
				imContactInformationList.add(argsArr[0]);
				pc.setImContactInformationList(imContactInformationList);
				
				argsArr = line.split(",", 2);
				// birthday
				pc.setContactBirthday(argsArr[0]);
				
				argsArr = line.split(",", 2);
				// URL
				ArrayList<String> urlList = new ArrayList<String>();
				urlList.add(argsArr[0]);
				pc.setUrlList(urlList);
				 
				argsArr = line.split(",", 2);
				// common label
				ArrayList<String> commonLabelList = new ArrayList<String>();
				commonLabelList.add(argsArr[0]);
				pc.setCommonLabelList(commonLabelList);
				
				argsArr = line.split(",", 2);
				// group
				ArrayList<String> groupList = new ArrayList<String>();
				groupList.add(argsArr[0]);
				pc.setGroupList(groupList);
				
				// relation label
				
				/* add this contact ...*/
				DataPool.getInstance().newContact(pc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
