package org.codepanda.application.xml;

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

public class DeleteContactXML {
	boolean comStart = false;
	boolean comEnd = false;
	boolean funcStart = false;
	boolean funcEnd = false;
	String funcSubStr = null;
	String comSubStr = null;

	// �ú����ĺ����ǣ���ǰ����ϵ�˷���XML��ʽ��ϵ�ˣ�������ϣ�����true��Ϊ���õķ��㣩

	/**
	 * @param currentContact
	 * @param match1
	 * @param match2
	 * @param commandDetail
	 * @return
	 */
	public int DeleteConXML(String match1,
			String match2, String commandDetail) {
		// ��commandDetail�н������û���Ϣ
		int tempISN;
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

			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document document=db.parse(new InputSource(new StringReader(commandDetail)));
			//System.out.println("File Path"+document.getDocumentURI());
			Element root=document.getDocumentElement();
			return DeleteContactIterator(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Wrong Format!!!");
		return Integer.MIN_VALUE;
	}
	public int DeleteContactIterator(Element element)
	{
		NodeList nodelist=element.getChildNodes();
//		System.out.println(element.getNodeName());
		for(int i=0;i<nodelist.getLength();i++)
		{
			
			Node node=nodelist.item(i);
			String str=node.getNodeName();
				if(str.equalsIgnoreCase("ISN"))
				{
				//System.out.println(i);
					String value=node.getTextContent();
					int tempISN=Integer.parseInt(value);
					return tempISN;
				}
			if(node instanceof Element)
			{
				return DeleteContactIterator((Element)node);
			}
	}
		return Integer.MIN_VALUE;
	}
}
