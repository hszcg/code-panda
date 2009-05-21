package org.codepanda.application.xml;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.*;

import org.codepanda.utility.contact.Birthday;
import org.codepanda.utility.contact.ContactData;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.codepanda.utility.user.User;
/**
 * @author xdq
 *
 */
public class UserXML {
	boolean comStart=false;
	boolean  comEnd=false;
	boolean funcStart=false;
	boolean funcEnd=false;
	String  funSubStr=null;
	String comSubStr=null;
	/**
	 * @param currentUser   //当前的用户
	 * @param match1       //表示某种用户功能，如新建用户，删除用户等
	 * @param match2
	 * @param commandDetail  //传入的字符串
	 */
	public void userParserXML(User currentUser,String match1,String match2,String commandDetail)
	{
	//从commandDetail中解析出用户信息
	try
	{
		if(commandDetail.contains("<com>")&&commandDetail.contains("</com"))
		{
			comStart=true;
			comEnd=true;
			int i=commandDetail.indexOf("<com>");
			int j=commandDetail.indexOf("</com>");
			comSubStr=commandDetail.substring(i+5, j);
		}
		if(!comStart||!comEnd)
		{
			System.out.println("Wrong Format!!!");
		}
			if(comStart&&comEnd&&comSubStr.contains(match1)&&comSubStr.contains(match2))
			{
				funcStart=true;
				funcEnd=true;
			}
			if(!funcStart||!funcEnd)
			{
				System.out.println("LLLLLLLLL");
				System.out.println("Wrong Function!!!");
			}
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document document=db.parse(new InputSource(new StringReader(commandDetail)));
		//System.out.println("File Path"+document.getDocumentURI());
		Element root=document.getDocumentElement();
		UserIterator(currentUser,root);
	//	System.out.println("root------"+root.getTextContent());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
/**
 * @param currentUser
 * @param element
 */
public  void UserIterator(User currentUser,Element element)
{
	NodeList nodelist=element.getChildNodes();
//	System.out.println(element.getNodeName());
	for(int i=0;i<nodelist.getLength();i++)
	{
		
		Node node=nodelist.item(i);
		String str=node.getNodeName();
		
		if(i==0)
		{
			if(str.equalsIgnoreCase("UserName"))
			{
			System.out.println(i);
			String value=node.getTextContent();
			currentUser.setUserName(value);
			System.out.println("UserName--"+value);
			}
			else
			{
			//	System.out.println("Please input UserName!!!");
			}
		}
		else if(i==1)
		{
			if(str.equalsIgnoreCase("UserPassword"))
			{
			System.out.println(i);
			String value=node.getTextContent();
			currentUser.setPassword(value);
			System.out.println("UserPassword--"+value);
			}
			else
			{
				System.out.println("Please input UserPassword!!!");
			}
		}
		else if(str.equalsIgnoreCase("Telephone"))
		{
			System.out.println(i);
			String value=node.getTextContent();
			System.out.println(value);
			ArrayList<String> myphoneNumberList=currentUser.getPhoneNumberList();
			if(myphoneNumberList==null)
			{
				myphoneNumberList=new ArrayList<String>();
			}
			myphoneNumberList.add(value);
			currentUser.setPhoneNumberList(myphoneNumberList);				
			System.out.println("Telephone--"+value);
		}
		else if(str.equalsIgnoreCase("Email"))
		{
			System.out.println(i);
			String value=node.getTextContent();
			ArrayList<String> myEmailList=currentUser.getEmailAddresseList();
			if(myEmailList==null)
			{
				myEmailList=new ArrayList<String>();
			}
		
			myEmailList.add(value);
			currentUser.setEmailAddresseList(myEmailList);
			System.out.println("Email--"+value);
		}
		else if(str.equalsIgnoreCase("Address"))
		{
			String value=node.getTextContent();
			ArrayList<String> myAddressList=currentUser.getContactAddressList();
			if(myAddressList==null)
			{
				myAddressList=new ArrayList<String>();
			}
		
			myAddressList.add(value);
			currentUser.setContactAddressList(myAddressList);
			System.out.println("Address--"+value);
		}
		else if(str.equalsIgnoreCase("Office"))
		{
			String value=node.getTextContent();
			ArrayList<String> myOfficeList=currentUser.getWorkingDepartmentList();
			if(myOfficeList==null)
			{
				myOfficeList=new ArrayList<String>();
			}
		
			myOfficeList.add(value);
			currentUser.setWorkingDepartmentList(myOfficeList);
			System.out.println("Office--"+value);
		}
		else if(str.equalsIgnoreCase("IMContact"))
		{
			String value=node.getTextContent();
			ArrayList<String> myimContactList=currentUser.getImContactInformationList();
			if(myimContactList==null)
			{
				myimContactList=new ArrayList<String>();
			}
		
			myimContactList.add(value);
			currentUser.setImContactInformationList(myimContactList);
			System.out.println("IMContact--"+value);
		}
		else if(str.equalsIgnoreCase("Birthday"))
		{
			String value=node.getTextContent();
			String str1[]= new String[3];
			str1=value.split("-");
			Birthday  birth=new Birthday();
			birth.setYear(Integer.parseInt(str1[0]));
			birth.setMonth(Integer.parseInt(str1[1]));
			birth.setDay(Integer.parseInt(str1[2]));
			currentUser.setContactBirthday(birth);
			System.out.println("Birthday--"+value);
		}
		else if(str.equalsIgnoreCase("url"))
		{
			String value=node.getTextContent();
			ArrayList<String> myurlList=currentUser.getUrlList();
			if(myurlList==null)
			{
				myurlList=new ArrayList<String>();
			}			
			myurlList.add(value);
			currentUser.setUrlList(myurlList);
			System.out.println("Url--"+value);
		}
		if(node instanceof Element)
		{
			
			UserIterator(currentUser,(Element)node);
		}
		
	}
}
}

