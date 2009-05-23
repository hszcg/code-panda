package org.codepanda.application.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author xdq
 *
 */
public class ExportContactXML { 
	boolean comStart=false;
	boolean  comEnd=false;
	boolean funcStart=false;
	boolean funcEnd=false;
	String  funSubStr=null;
	String comSubStr=null;
	String tempStr=null;
	public String ContactParserXML(String match1,String match2,String commandDetail)
	{
		String tempStr=null;
		try
		{
			//System.out.println("MMMMMMMMMMM");
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
			return ExportIterator(root);}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public String ExportIterator(Element element)
	{
		
		NodeList nodelist=element.getChildNodes();
		for(int i=0;i<nodelist.getLength();i++)
		{
			
			Node node=nodelist.item(i);
			String str=node.getNodeName();
			if(str.equals("Type"))
			{
				String value=node.getTextContent();
				tempStr=value+"--";
			}
			else if(str.equals("Url"))
			{
				String value=node.getTextContent();
				tempStr=tempStr+value;
			}
		  if(node instanceof Element)
		  {
			  ExportIterator((Element)node);
		  }
		}
		return tempStr;
	}
}
