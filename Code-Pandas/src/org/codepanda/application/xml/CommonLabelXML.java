package org.codepanda.application.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.codepanda.utility.label.CommonLabel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class CommonLabelXML {
	boolean comStart=false;
	boolean  comEnd=false;
	boolean funcStart=false;
	boolean funcEnd=false;
	String  funSubStr=null;
	String comSubStr=null;
	public void labelParserXML(CommonLabel commonLabel,String match1,String match2,String commandDetail)
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
			Node node = root.getFirstChild();
			String str = node.getNodeName();
			if (str.equalsIgnoreCase("LabelName")) {
				String value = node.getTextContent();
				commonLabel.setLabelName(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
