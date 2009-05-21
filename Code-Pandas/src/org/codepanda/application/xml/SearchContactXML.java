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
public class SearchContactXML {
	boolean comStart=false;
	boolean  comEnd=false;
	boolean funcStart=false;
	boolean funcEnd=false;
	String  funcSubStr=null;
	String comSubStr=null;
	/**
	 * @param match1   //<SearchContact>
	 * @param match2   //</SearchContact>
	 * @param commandDetail  //传入的字符串
	 */
	public void SearchContact(String match1,String match2,String commandDetail)
	{
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
			NodeList nodelist=root.getChildNodes();
//			System.out.println(element.getNodeName());
			for(int i=0;i<nodelist.getLength();i++)
			{
				
				Node node=nodelist.item(i);
				String str=node.getNodeName();
					if(str.equalsIgnoreCase("SearchInput"))
					{
						String value=node.getTextContent();
					}
					else if(str.equalsIgnoreCase("BlurSearch"))
					{
						String value=node.getTextContent();
						int blur=Integer.parseInt(value);
						if(blur==0)//不模糊匹配
						{
							
						}
						if(blur==1)  //模糊匹配
						{
							
						}
					}
					else if(str.equalsIgnoreCase("SearchType"))
					{
						String value=node.getTextContent();
						
					}
					}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
