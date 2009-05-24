package org.codepanda.application.xml;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.codepanda.utility.contact.PersonalContact;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class StatContactXML {
	int startYear;
	int startMonth;
	int startDay;
	int endYear;
	int endMonth;
	int endDay;
	boolean comStart=false;
	boolean  comEnd=false;
	boolean funcStart=false;
	boolean funcEnd=false;
	String  funcSubStr=null;
	String comSubStr=null;
	boolean secondFlag=false;//表示<birthday>tag是是否已经出现一次，false表示未出现
	public boolean contactParserXML(PersonalContact currentContact,String match1,String match2,String commandDetail)
	{
		try {
			if (commandDetail.contains("<com>")
					&& commandDetail.contains("</com")) {
				comStart = true;
				comEnd = true;
				int i = commandDetail.indexOf("<com>");
				int j = commandDetail.indexOf("</com>");
				comSubStr = commandDetail.substring(i + 5, j);
			}
			if (!comStart || !comEnd) {
				System.out.println("Wrong Format!!!");
			}
			if (comStart && comEnd && comSubStr.contains(match1)
					&& comSubStr.contains(match2)) {
				funcStart = true;
				funcEnd = true;
			}
			if (!funcStart || !funcEnd) {
				System.out.println("LLLLLLLLL");
				System.out.println("Wrong Function!!!");
			}

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new InputSource(new StringReader(
					commandDetail)));
			// System.out.println("File Path"+document.getDocumentURI());
			Element root = document.getDocumentElement();
			return StatIterator(root,currentContact);
		//		return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean StatIterator(Element element,PersonalContact currentContact)
	{
		System.out.println(currentContact.getContactBirthday());
		NodeList nodeList=element.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++)
		{
			Node node=nodeList.item(i);
			String str=node.getNodeName();
		//第一次出现
			if (str.equalsIgnoreCase("Birthday")) {
				if(!secondFlag)
				{
				String value = node.getTextContent();
				//对生日条目进行解析
				String []tempstr=new String[3];
				tempstr=value.split("-");
				startYear=Integer.parseInt(tempstr[0]);
				startMonth=Integer.parseInt(tempstr[1]);
				startDay=Integer.parseInt(tempstr[2]);
				System.out.println(startYear);
				System.out.println(startMonth);
				System.out.println(startDay);
				secondFlag=true;
				}
				else
				{
					String value = node.getTextContent();
					//对生日条目进行解析
					String []tempstr=new String[3];
					tempstr=value.split("-");
					endYear=Integer.parseInt(tempstr[0]);
					endMonth=Integer.parseInt(tempstr[1]);
					endDay=Integer.parseInt(tempstr[2]);
					System.out.println(endYear);
					System.out.println(endMonth);
					System.out.println(endDay);
					//secondFlag=true;
				}
			}
			if(node instanceof Element)
			{
				StatIterator((Element)node,currentContact);
			}
		}
		//分情况进行分析
			if(startYear>endYear)
			{
				System.out.println("Start Year>>>>>>>>>>end Year!!!!");
				return false;
			}
			if(startYear==endYear)
			{
				if(startMonth>endMonth)
				{
					System.out.print("Start Month>>>>>>>>>>end Month!!!!");
					return false;
				}
				if(startMonth==endMonth)
				{
					if(startDay>endDay)
					{
						System.out.print("Start Day>>>>>>>>>>end Day!!!!");
						return false;
					}
					if(startDay==endDay)
					{
						if(!currentContact.getContactBirthday().isEmpty())
						{
							String temp=currentContact.getContactBirthday().substring(8,9);
							int tempDay=Integer.parseInt(temp);
							if(tempDay==startDay)
							{
								return true;
							}
						}
					}
					if(startDay<endDay)
					{
						if(!currentContact.getContactBirthday().isEmpty())
						{
							String temp=currentContact.getContactBirthday().substring(8,9);
							int tempDay=Integer.parseInt(temp);
							if(tempDay>=startDay&&tempDay<=endDay)
							{
								return true;
							}
						}
					}
				}
				if(startMonth<endMonth)
				{
					if(!currentContact.getContactBirthday().isEmpty()){
					String temp=currentContact.getContactBirthday().substring(5,6);
					int tempMonth=Integer.parseInt(temp);
					boolean startTemp1=(tempMonth>startMonth);
					boolean startTemp2=(tempMonth==startMonth)&&(tempMonth>=startDay);
					boolean endTemp1=(tempMonth<endMonth);
					boolean endTemp2=(tempMonth==endMonth)&&(tempMonth<=endDay);
					//上述情况的任意两种情况进行组合，即可
					if((startTemp1||startTemp2)&&(endTemp1||endTemp2))
						return true;
					}
				}
			}
			if(startYear<endYear)
			{
				if(!currentContact.getContactBirthday().isEmpty()){
				String temp=currentContact.getContactBirthday().substring(0,3);
				int tempYear=Integer.parseInt(temp);
				 temp=currentContact.getContactBirthday().substring(5,6);
				int tempMonth=Integer.parseInt(temp);
				 temp=currentContact.getContactBirthday().substring(8,9);
				int tempDay=Integer.parseInt(temp);
				
				
				boolean startTemp1=(tempYear>startYear);
				boolean startTemp2=(tempYear==startYear)&&(tempMonth>startMonth);
				boolean startTemp3=(tempYear==startYear)&&(tempMonth==startMonth)&&(tempDay>=startDay);
				boolean endTemp1=(tempYear<endYear);
				boolean endTemp2=(tempYear==endYear)&&(tempMonth<endMonth);
				boolean endTemp3=(tempYear==endYear)&&(tempMonth==endMonth)&&(tempDay<=endDay);
				if((startTemp1||startTemp2||startTemp3)&&(endTemp1||endTemp2||endTemp3))
					return true;
			}
			}
			return false;
	}
			
	}
