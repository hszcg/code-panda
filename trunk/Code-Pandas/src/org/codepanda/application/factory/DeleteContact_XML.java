package org.codepanda.application.factory;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.*;

import org.codepanda.utility.contact.Birthday;
import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.label.RelationLabel;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.codepanda.utility.user.User;
public class DeleteContact_XML {
	boolean start1=false;
	boolean  end1=false;
	boolean start2=false;
	boolean end2=false;
	String  substr2=null;
	String substr=null;
	public void ContactXML(PersonalContact currentContact,String match1,String match2,String commandDetail)
	{
	//从commandDetail中解析出用户信息
	try
	{
		if(commandDetail.contains("<com>")&&commandDetail.contains("</com"))
		{
			start1=true;
			end1=true;
			int i=commandDetail.indexOf("<com>");
			int j=commandDetail.indexOf("</com>");
			substr=commandDetail.substring(i+5, j);
		}
		if(!start1||!end1)
		{
			System.out.println("Wrong Format!!!");
		}
			if(start1&&end1&&substr.contains(match1)&&substr.contains(match2))
			{
				start2=true;
				end2=true;
			}
			if(!start2||!end2)
			{
				System.out.println("LLLLLLLLL");
				System.out.println("Wrong Function!!!");
			}
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document document=db.parse(new InputSource(new StringReader(commandDetail)));
		//System.out.println("File Path"+document.getDocumentURI());
		Element root=document.getDocumentElement();
		Node node=root.getFirstChild();
		String str=node.getNodeName();
		if(str.equalsIgnoreCase("ISN"))
		{
			//怎么获得整个的ISN列表
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
}
