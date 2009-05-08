package org.codepanda.application.factory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.codepanda.utility.contact.*;
import org.codepanda.utility.data.DataPool;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;
import org.codepanda.application.user.NewUserActor;
import org.codepanda.utility.user.User;

/**
 * @author hszcg
 *
 */
public class UserActorFactory extends CommandActorFactory {
	 User currentUser;
	@Override
	public CommandActor creator(CommandType commandType, String commandDetail) {
		// TODO Auto-generated method stub
		commandType=CommandType.NEW_USER;
		commandDetail=
			"<com>"+
			"<NewUser>"+"</NewUser>"+
			"<UserName>"+"leilei"+"</UserName>"+
			"<Telephone>"+"13699252256"+"</Telephone>"+
			"<Telephone>"+"51531174"+"</Telephone>"+
			"</com>";
		if( commandType == CommandType.NEW_USER ){
			NewUserActor newUserActor = new NewUserActor();			
			currentUser = new User();
			newUserActor.setNewUser(currentUser);
		//从commandDetail中解析出用户信息
		try
		{
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			//Document document=db.parse(commandDetail);
			Document document=db.parse(new InputSource(new StringReader(commandDetail)));
			
			//System.out.println("File Path"+document.getDocumentURI());
			Element root=document.getDocumentElement();
			Iterator(root);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return newUserActor;
		}
		return null;
	}
	public  void Iterator(Element element)
	{
		NodeList nodelist=element.getChildNodes();
	//	System.out.println(element.getNodeName());
		for(int i=0;i<nodelist.getLength();i++)
		{
			System.out.println(i);
			Node node=nodelist.item(i);
			String str=node.getNodeName();
			/* if(i==0)
			{
				if(str.equalsIgnoreCase("NewUser"))
				{
					System.out.println("New User!!!");
					continue;
				}
				else
				{
					System.out.println("Wrong Function!!!");
					break;
				}
			}*/
			//else
			//{
			if(str.equalsIgnoreCase("UserName"))
			{
				String value=node.getTextContent();
				currentUser.setUserName(value);
				System.out.println("UserName--"+value);
			}
			else if(str.equalsIgnoreCase("UserPassword"))
			{
				String value=node.getTextContent();
				currentUser.setPassword(value);
				System.out.println("UserPassword--"+value);
			}
			else if(str.equalsIgnoreCase("Telephone"))
			{
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
				str1=value.split(" ");
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
				//System.out.println("asdf");
				Iterator((Element)node);
			}
			
		//	} 
		}
	}
	public static void main(String args[])
	{
		DataPool.getInstance();
		UserActorFactory  uac=new UserActorFactory();
		uac.creator(CommandType.NEW_USER,"<com>"+"<NewUser>"+"</NewUser>"+"<UserName>"+"leilei"+"</UserName>"+"<Telephone>"+"13699252256"+"</Telephone>"+"<Telephone>"+"51531174"+"</Telephone>"+"</com>");
		
	}

}
