package org.codepanda.application.xml;

import java.io.StringReader;
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
	String temp=new String();
	boolean comStart=false;
	boolean  comEnd=false;
	boolean funcStart=false;
	boolean funcEnd=false;
	String  funcSubStr=null;
	String comSubStr=null;
	boolean secondFlag=false;//��ʾ<birthday>tag���Ƿ��Ѿ�����һ�Σ�false��ʾδ����
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
		//��һ�γ���
			if (str.equalsIgnoreCase("StartBirthday")) {
				String value = node.getTextContent();
				//��������Ŀ���н���
				String []tempstr=new String[3];
				tempstr=value.split("-");
				startYear=Integer.parseInt(tempstr[0]);
				startMonth=Integer.parseInt(tempstr[1]);
				startDay=Integer.parseInt(tempstr[2]);
				System.out.println("startYear"+startYear);
				System.out.println("startMonth"+startMonth);
				System.out.println(startDay);
				secondFlag=true;
				continue;
				}
				else if(str.equalsIgnoreCase("EndBirthday"))
				{
					String value = node.getTextContent();
					//��������Ŀ���н���
					String []tempstr=new String[3];
					tempstr=value.split("-");
					endYear=Integer.parseInt(tempstr[0]);
					endMonth=Integer.parseInt(tempstr[1]);
					endDay=Integer.parseInt(tempstr[2]);
					System.out.println("End"+endYear);
					System.out.println(endMonth);
					System.out.println(endDay);
					//secondFlag=true;
				}
			if(node instanceof Element)
			{
				StatIterator((Element)node,currentContact);
			}
		}
		//��������з���
		int tempYear,tempMonth,tempDay;
		System.out.println(currentContact.getContactBirthday().length());
		temp=currentContact.getContactBirthday().substring(0,4);
		tempYear=Integer.parseInt(temp);
		temp=currentContact.getContactBirthday().substring(5,7);
		tempMonth=Integer.parseInt(temp);
		temp=currentContact.getContactBirthday().substring(8,10);
		tempDay=Integer.parseInt(temp);
		System.out.println("tempYear"+tempYear);
		System.out.println("tempMonth"+tempMonth);
		System.out.println("tempDay"+tempDay);
			boolean temp1=(tempYear>startYear)||(tempYear==startYear&&tempMonth>startMonth)||(tempYear==startYear&&tempMonth==startMonth&&tempDay>=startDay);
			boolean temp2=(tempYear<endYear)||(tempYear==endYear&&tempMonth<endMonth)||(tempYear==endYear&&tempMonth==endMonth&&tempDay<=endDay);
			if(temp1&&temp2)
			{
				return true;
			}
			return false;
}
}
			
			