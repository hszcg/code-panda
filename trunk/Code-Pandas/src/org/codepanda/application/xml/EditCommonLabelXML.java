package org.codepanda.application.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class EditCommonLabelXML {
	boolean comStart=false;
	boolean  comEnd=false;
	boolean funcStart=false;
	boolean funcEnd=false;
	String  funSubStr=null;
	String comSubStr=null;
	public String labelParserXML(String match1,String match2,String commandDetail)
	{
		//从commandDetail中解析出用户信息
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
			return CommonLabelParser(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String CommonLabelParser(Element element)
	{
		String tempstr=null;
		NodeList nodeList=element.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++)
		{
			Node node=nodeList.item(i);
			String str=node.getNodeName();
			//首先保证进行一对标签的修改时正确的，可能多对标签时会有bug
			if(str.equals("OldName"))
			{
				String value=node.getTextContent();
				tempstr=tempstr+"--"+value;
			}
			else if(str.equals("NewName"))
			{
				String value=node.getTextContent();
				tempstr=tempstr+"--"+value;
			}
			if(node instanceof Element)
			{
				CommonLabelParser((Element)node);
			}
		}
		return tempstr;
		
	}
}
