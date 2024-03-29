package org.codepanda.application.xml;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.xml.parsers.*;

import org.codepanda.utility.contact.HeadImage;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.label.RelationLabel;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

/**
 * @author xdq
 *
 */
public class ContactXML {
	
	boolean comStart=false;
	boolean  comEnd=false;
	boolean funcStart=false;
	boolean funcEnd=false;
	String  funcSubStr=null;
	String comSubStr=null;
	ArrayList<RelationLabel> relationLabelList;
	//为了修改的方便，需要得到之前的currentContact
	public void contactParserXML(PersonalContact currentContact,String match1,String match2,String commandDetail)
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
		ContactIterator(currentContact,root);
	//	System.out.println("root------"+root.getTextContent());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
public  void ContactIterator(PersonalContact currentContact,Element element) throws IOException
{
	NodeList nodelist=element.getChildNodes();
//	System.out.println(element.getNodeName());
	for(int i=0;i<nodelist.getLength();i++)
	{
		
		Node node=nodelist.item(i);
		String str=node.getNodeName();
			if(str.equalsIgnoreCase("ContactName"))
			{
			//System.out.println(i);
			String value=node.getTextContent();
			currentContact.setContactName(value);
			System.out.println("ContactName--"+value);
			}
			else if(str.equalsIgnoreCase("ISN"))
			{
				String value=node.getTextContent();
				currentContact.setISN(Integer.parseInt(value));
				System.out.println("ISN----"+value);
			}
			//头像的解析，头像是通过url得到的
			else if(str.equals("HeadImage"))
			{
				String value=node.getTextContent();
				//BufferedImage headBufferedImage=ImageIO.read(this.getClass().getResource(value));
				BufferedImage headBufferedImage=ImageIO.read(new URL(value));
				HeadImage currentImage=new HeadImage();
				Image tempImage = headBufferedImage.getScaledInstance(130, 115, Image.SCALE_DEFAULT);
				ImageIcon imageIcon = new ImageIcon(tempImage);
				currentImage.setMyImageIcon(imageIcon);
				currentContact.setHeadImage(currentImage);
				System.out.println(tempImage.toString());
				System.out.println("HeadImage"+currentImage.toString());
			}
			else if(str.equalsIgnoreCase("Telephone"))
			{
				String value=node.getTextContent();
				ArrayList<String> myphoneNumberList=currentContact.getPhoneNumberList();
				if(myphoneNumberList==null)
				{
					myphoneNumberList=new ArrayList<String>();
				}
				myphoneNumberList.add(value);
				currentContact.setPhoneNumberList(myphoneNumberList);
				for(int l=0;l<myphoneNumberList.size();l++)
				{
					System.out.println("Telephone"+l+myphoneNumberList.get(l));
				}
			}
			else if(str.equalsIgnoreCase("Email"))
			{
				String value=node.getTextContent();
				ArrayList<String> myEmailList=currentContact.getEmailAddresseList();
				if(myEmailList==null)
				{
					myEmailList=new ArrayList<String>();
				}
			
				myEmailList.add(value);
				currentContact.setEmailAddresseList(myEmailList);
				for(int l=0;l<myEmailList.size();l++)
				{
					System.out.println("Email"+l+":"+myEmailList.get(l));
				}
			}
			else if(str.equalsIgnoreCase("Address"))
			{
				String value=node.getTextContent();
				ArrayList<String> myAddressList=currentContact.getContactAddressList();
				if(myAddressList==null)
				{
					myAddressList=new ArrayList<String>();
				}
			
				myAddressList.add(value);
				currentContact.setContactAddressList(myAddressList);
				for(int l=0;l<myAddressList.size();l++)
				{
					System.out.println("Address"+l+myAddressList.get(l));
				}
			}
			else if(str.equalsIgnoreCase("Office"))
			{
				String value=node.getTextContent();
				ArrayList<String> myOfficeList=currentContact.getWorkingDepartmentList();
				if(myOfficeList==null)
				{
					myOfficeList=new ArrayList<String>();
				}
			
				myOfficeList.add(value);
				currentContact.setWorkingDepartmentList(myOfficeList);
				for(int l=0;l<myOfficeList.size();l++)
				{
					System.out.println("Office"+l+myOfficeList.get(l));
				}
			}
			else if(str.equalsIgnoreCase("IMContact"))
			{
				String value=node.getTextContent();
				ArrayList<String> myimContactList=currentContact.getImContactInformationList();
				if(myimContactList==null)
				{
					myimContactList=new ArrayList<String>();
				}
			
				myimContactList.add(value);
				currentContact.setImContactInformationList(myimContactList);
				for(int l=0;l<myimContactList.size();l++)
				{
					System.out.println("IMContact"+l+myimContactList.get(l));
				}
			}
			else if(str.equalsIgnoreCase("Birthday"))
			{
				String value=node.getTextContent();
				currentContact.setContactBirthday(value);
				System.out.println("Birthday--"+value);
			}
			else if(str.equalsIgnoreCase("url"))
			{
				String value=node.getTextContent();
				ArrayList<String> myurlList=currentContact.getUrlList();
				if(myurlList==null)
				{
					myurlList=new ArrayList<String>();
				}			
				myurlList.add(value);
				currentContact.setUrlList(myurlList);
				for(int l=0;l<currentContact.getUrlList().size();l++)
				{
					System.out.println("URL--"+l+currentContact.getUrlList().get(l));
				}
			}
			else if(str.equalsIgnoreCase("CommonLabel"))
			{
				String value=node.getTextContent();
				ArrayList<String> myCommonLabelList=currentContact.getCommonLabelList();
				if(myCommonLabelList==null)
				{
					myCommonLabelList=new ArrayList<String>();
				}			
				myCommonLabelList.add(value);
				currentContact.setCommonLabelList(myCommonLabelList);
				for(int l=0;l<currentContact.getCommonLabelList().size();l++)
				{
					System.out.println("CommonLabel--"+l+currentContact.getCommonLabelList().get(l));
				}
			}
			else if(str.equalsIgnoreCase("Group"))
			{
				String value=node.getTextContent();
				ArrayList<String> myGroupList=currentContact.getGroupList();
				if(myGroupList==null)
				{
					myGroupList=new ArrayList<String>();
				}			
				myGroupList.add(value);
				currentContact.setGroupList(myGroupList);
				for(int l=0;l<currentContact.getGroupList().size();l++)
				{
					System.out.println("CommonLabel--"+l+currentContact.getGroupList().get(l));
				}
			}
			//关于由于关系标签的表示方法，所以这里可能有问题
			//关系标签的相关处理
			if(node instanceof Element && node.getNodeName().equalsIgnoreCase("RelationLabel"))
			{
				ArrayList<RelationLabel>relationLabelList = currentContact.getRelationLabelList();
				RelationLabel relationLabel=new RelationLabel();
				RelationLabelIterator(relationLabel,(Element)node);
				relationLabelList.add(relationLabel);
				System.out.println("LabelName----"+relationLabel.getLabelName());
				currentContact.setRelationLabelList(relationLabelList);
				System.out.println("Size----"+currentContact.getRelationLabelList().size());
			}
			if(node instanceof Element && !node.getNodeName().equalsIgnoreCase("RelationLabel"))
			{
			
				ContactIterator(currentContact,(Element)node);
			}
	}
	
}
	public void  RelationLabelIterator(RelationLabel  relationLabel,Element  element)
	{
		
		NodeList nodelist=element.getChildNodes();
	//	relationLabel=new RelationLabel();
		for(int i=0;i<nodelist.getLength();i++)
		{
			
			Node node=nodelist.item(i);
			String str=node.getNodeName();
			if(str.equalsIgnoreCase("LabelName"))
			{
				String value=node.getTextContent();
				relationLabel.setLabelName(value);
				//System.out.println("fjsdkl---"+value);
			}
			else if(str.equalsIgnoreCase("DestISN"))
			{
				String value=node.getTextContent();
				int DestISN=Integer.parseInt(value);
				relationLabel.setRelationObjectISN(DestISN);
			}
	}
		
}

}