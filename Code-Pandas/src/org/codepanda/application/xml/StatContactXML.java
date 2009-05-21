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
	boolean comStart=false;
	boolean  comEnd=false;
	boolean funcStart=false;
	boolean funcEnd=false;
	String  funcSubStr=null;
	String comSubStr=null;
	boolean secondFlag=false;//表示<birthday>tag是是否已经出现一次，false表示未出现
	public boolean contactParserXML(PersonalContact currentContact,String match1,String match2,String commandDetail)
	{
		Integer startYear=0;
		Integer startMonth=0;
		Integer startDay=0;
		Integer endYear=0;
		Integer endMonth=0;
		Integer endDay=0;
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
			NodeList nodeList=root.getChildNodes();
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
						//secondFlag=true;
					}
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
							if(currentContact.getContactBirthday().getDay()==startDay)
							{
								return true;
							}
						}
						if(startDay<endDay)
						{
							if(currentContact.getContactBirthday().getDay()>=startDay&&currentContact.getContactBirthday().getDay()<=endDay)
								return true;
						}
					}
					if(startMonth<endMonth)
					{
						boolean startTemp1=(currentContact.getContactBirthday().getMonth()>startMonth);
						boolean startTemp2=(currentContact.getContactBirthday().getMonth()==startMonth)&&(currentContact.getContactBirthday().getDay()>=startDay);
						boolean endTemp1=(currentContact.getContactBirthday().getMonth()<endMonth);
						boolean endTemp2=(currentContact.getContactBirthday().getMonth()==endMonth)&&(currentContact.getContactBirthday().getDay()<=endDay);
						//上述情况的任意两种情况进行组合，即可
						if((startTemp1||startTemp2)&&(endTemp1||endTemp2))
							return true;
					}
				}
				if(startYear<endYear)
				{
					boolean startTemp1=(currentContact.getContactBirthday().getYear()>startYear);
					boolean startTemp2=(currentContact.getContactBirthday().getYear()==startYear)&&(currentContact.getContactBirthday().getMonth()>startMonth);
					boolean startTemp3=(currentContact.getContactBirthday().getYear()==startYear)&&(currentContact.getContactBirthday().getMonth()==startMonth)&&(currentContact.getContactBirthday().getDay()>=startDay);
					boolean endTemp1=(currentContact.getContactBirthday().getYear()<endYear);
					boolean endTemp2=(currentContact.getContactBirthday().getYear()==endYear)&&(currentContact.getContactBirthday().getMonth()<endMonth);
					boolean endTemp3=(currentContact.getContactBirthday().getYear()==endYear)&&(currentContact.getContactBirthday().getMonth()==endMonth)&&(currentContact.getContactBirthday().getDay()<=endDay);
					if((startTemp1||startTemp2||startTemp3)&&(endTemp1||endTemp2||endTemp3))
						return true;
					
				}
		//		return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
