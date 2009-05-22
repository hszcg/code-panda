package org.codepanda.application.xml;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.codepanda.utility.contact.Birthday;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.label.RelationLabel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
	String infoStr=null;
	boolean blur=false;
	boolean continueFlag=false;
	int loopFlag=0;
	/**
	 * @param match1   //<SearchContact>
	 * @param match2   //</SearchContact>
	 * @param commandDetail  //传入的字符串
	 */
	public void SearchContact(ArrayList<Integer> iSNList,PersonalContact contactData,String match1,String match2,String commandDetail)
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
			SearchContactIterator(iSNList,contactData,root);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		public  void SearchContactIterator(ArrayList<Integer>iSNList,PersonalContact contactData,Element element)
		{
			if(iSNList.isEmpty())
			{
				iSNList=new ArrayList<Integer>();
			}
			NodeList nodelist=element.getChildNodes();
//			System.out.println(element.getNodeName());
			for(int i=0;i<nodelist.getLength();i++)
			{
				
				Node node=nodelist.item(i);
				String str=node.getNodeName();
				if(str.equalsIgnoreCase("BlurSearch"))
				{
					blur=true;
				}
					if(str.equalsIgnoreCase("ContactName"))
					{
						String value=node.getTextContent();
						if(blur)
						{
							if(contactData.getContactName().contains(value))
								iSNList.add(contactData.getISN());
						}
						else
						{
								if(contactData.getContactName().equalsIgnoreCase(value))
									iSNList.add(contactData.getISN());
						}
					}				
					else if(str.equalsIgnoreCase("Telephone"))
					{
						String value=node.getTextContent();
						if(blur)
						{
							for(int j=0;j<contactData.getPhoneNumberList().size();j++)
							if(contactData.getPhoneNumberList().get(j).contains(value))
								iSNList.add(contactData.getISN());
						}
						else
						{
							for(int j=0;j<contactData.getPhoneNumberList().size();j++)
								if(contactData.getPhoneNumberList().get(j).equalsIgnoreCase(value))
									iSNList.add(contactData.getISN());
						}
						
					}
					else if(str.equalsIgnoreCase("Email"))
					{
						String value=node.getTextContent();
						if(blur)
						{
							for(int j=0;j<contactData.getEmailAddresseList().size();j++)
							if(contactData.getEmailAddresseList().get(j).contains(value))
								iSNList.add(contactData.getISN());
						}
						else
						{
							for(int j=0;j<contactData.getEmailAddresseList().size();j++)
								if(contactData.getEmailAddresseList().get(j).equalsIgnoreCase(value))
									iSNList.add(contactData.getISN());
						}	
					}
					else if(str.equalsIgnoreCase("Address"))
					{
						String value=node.getTextContent();
						if(blur)
						{
							for(int j=0;j<contactData.getContactAddressList().size();j++)
							if(contactData.getContactAddressList().get(j).contains(value))
								iSNList.add(contactData.getISN());
						}
						else
						{
							for(int j=0;j<contactData.getContactAddressList().size();j++)
								if(contactData.getContactAddressList().get(j).equalsIgnoreCase(value))
									iSNList.add(contactData.getISN());
						}	
					}
					else if(str.equalsIgnoreCase("Office"))
					{
						String value=node.getTextContent();
						if(blur)
						{
							for(int j=0;j<contactData.getWorkingDepartmentList().size();j++)
							if(contactData.getWorkingDepartmentList().get(j).contains(value))
								iSNList.add(contactData.getISN());
						}
						else
						{
							for(int j=0;j<contactData.getWorkingDepartmentList().size();j++)
								if(contactData.getWorkingDepartmentList().get(j).equalsIgnoreCase(value))
									iSNList.add(contactData.getISN());
						}	
					}
					else if(str.equalsIgnoreCase("IMContact"))
					{
						String value=node.getTextContent();
						if(blur)
						{
							for(int j=0;j<contactData.getImContactInformationList().size();j++)
							if(contactData.getImContactInformationList().get(j).contains(value))
								iSNList.add(contactData.getISN());
						}
						else
						{
							for(int j=0;j<contactData.getImContactInformationList().size();j++)
								if(contactData.getImContactInformationList().get(j).equalsIgnoreCase(value))
									iSNList.add(contactData.getISN());
						}	
					}
					//生日查询有问题
					else if(str.equalsIgnoreCase("Birthday"))
					{
						String value=node.getTextContent();
						if(blur)
						{
							for(int j=0;j<contactData.getImContactInformationList().size();j++)
							if(contactData.getImContactInformationList().get(j).contains(value))
								iSNList.add(contactData.getISN());
						}
						else
						{
							for(int j=0;j<contactData.getImContactInformationList().size();j++)
								if(contactData.getImContactInformationList().get(j).equalsIgnoreCase(value))
									iSNList.add(contactData.getISN());
						}	
					}
					else if(str.equalsIgnoreCase("url"))
					{
						String value=node.getTextContent();
						if(blur)
						{
							for(int j=0;j<contactData.getUrlList().size();j++)
							if(contactData.getUrlList().get(j).contains(value))
								iSNList.add(contactData.getISN());
						}
						else
						{
							for(int j=0;j<contactData.getUrlList().size();j++)
								if(contactData.getUrlList().get(j).equalsIgnoreCase(value))
									iSNList.add(contactData.getISN());
						}	
					}
					else if(str.equalsIgnoreCase("CommonLabel"))
					{
						String value=node.getTextContent();
						if(blur)
						{
							for(int j=0;j<contactData.getCommonLabelList().size();j++)
							if(contactData.getCommonLabelList().get(j).contains(value))
								iSNList.add(contactData.getISN());
						}
						else
						{
							for(int j=0;j<contactData.getCommonLabelList().size();j++)
								if(contactData.getCommonLabelList().get(j).equalsIgnoreCase(value))
									iSNList.add(contactData.getISN());
						}	
					}
					else if(str.equalsIgnoreCase("Group"))
					{
						String value=node.getTextContent();
						if(blur)
						{
							for(int j=0;j<contactData.getGroupList().size();j++)
							if(contactData.getGroupList().get(j).contains(value))
								iSNList.add(contactData.getISN());
						}
						else
						{
							for(int j=0;j<contactData.getGroupList().size();j++)
								if(contactData.getGroupList().get(j).equalsIgnoreCase(value))
									iSNList.add(contactData.getISN());
						}
					}
					//关于由于关系标签的表示方法，所以这里可能有问题
					//关系标签的相关处理
					if(node instanceof Element && node.getNodeName().equalsIgnoreCase("RelationLabel"))
					{
						RelationLabelIterator(iSNList,contactData,(Element)node);
					}	
					if(node instanceof Element && !node.getNodeName().equalsIgnoreCase("RelationLabel"))
					{
					
						SearchContactIterator(iSNList,contactData,(Element)node);
					}
			}
		}
		public void  RelationLabelIterator(ArrayList<Integer>iSNList,PersonalContact contactData,Element  element)
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
						
						for (loopFlag=0;loopFlag<contactData.getRelationLabelList().size();loopFlag++)
						{
							if(contactData.getRelationLabelList().get(loopFlag).getLabelName().equalsIgnoreCase(value))
							{
								continueFlag=true;
							}
						}
					}
					else if(str.equalsIgnoreCase("DestName"))
					{
						if(continueFlag)
						{
							String value=node.getTextContent();
							
							int temp=contactData.getRelationLabelList().get(loopFlag).getRelationObjectISN();
							PersonalContact tempData=(PersonalContact)DataPool.getInstance().getAllContactISNMap().get(temp);
							if(blur)
							{
								if(tempData.getContactName().contains(value))
								iSNList.add(tempData.getISN());
							}
							else
							{
								if(tempData.getContactName().equalsIgnoreCase(value))
									iSNList.add(tempData.getISN());
							}	
						}
					}
			}
			}

	}
