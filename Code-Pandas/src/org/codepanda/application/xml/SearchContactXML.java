package org.codepanda.application.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;
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
	boolean comStart = false;
	boolean comEnd = false;
	boolean funcStart = false;
	boolean funcEnd = false;
	String funcSubStr = null;
	String comSubStr = null;
	String infoStr = null;
	boolean continueFlag = false;
	boolean goFlag = false; // 继续向下进行匹配的标志
	int loopFlag = 0;
	boolean blur=false;
	/**
	 * @param match1
	 *            //<SearchContact>
	 * @param match2
	 *            //</SearchContact>
	 * @param commandDetail
	 *            //传入的字符串
	 */
	public boolean SearchContact(ContactOperations contactData, String match1,
			String match2, String commandDetail) {
		goFlag = false;
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
			System.out.print("root___"+root.toString());
			return SearchContactIterator(contactData, root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goFlag;
	}

	public boolean SearchContactIterator(ContactOperations contactData,
			Element element) {
		System.out.println("Element____"+element.toString());
		NodeList nodelist = element.getChildNodes();
		// System.out.println(element.getNodeName());
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
			String str = node.getNodeName();
			if (str.equalsIgnoreCase("BlurSearch")) {
				String value = node.getTextContent();
				if (value.equals("0"))
					blur = true;
			}
		}
		if (blur) {
			// 模糊匹配
			System.out.println(element.toString());
			System.out.println("Blur________");
			//System.out.println("Length____"+nodelist.getLength());
			for (int i = 0; i < nodelist.getLength(); i++) {
				System.out.println("LLLLLLLLL");
				Node node = nodelist.item(i);
				System.out.println(node.toString());
				String str = node.getNodeName();
				System.out.println(str);
				if (str.equalsIgnoreCase("ContactName")) {
					String value = node.getTextContent();
					System.out.println("SearchText____" + value);
					System.out.println("Name____"
							+ contactData.getContactName());
					if (contactData.getContactName().contains(value)) {
						goFlag = true;
						System.out.println("BlurSearch___MM"
								+ contactData.getContactName() + "MMMMMMMM"
								+ value);
						System.out.println(goFlag);

					} else {
						System.out.println("Pos____"
								+ contactData.getContactName().indexOf(value));
						System.out.println("Error Here!!!");
						goFlag = false;
						return false;
					}
				} else if (str.equalsIgnoreCase("Telephone")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getPhoneNumberList().size(); j++) {
						if (contactData.getPhoneNumberList().get(j).contains(
								value)) {
							goFlag = true;

							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("Email")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getEmailAddresseList()
							.size(); j++) {
						if (contactData.getEmailAddresseList().get(j).contains(
								value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("Address")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getContactAddressList()
							.size(); j++) {
						if (contactData.getContactAddressList().get(j)
								.contains(value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("Office")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getWorkingDepartmentList()
							.size(); j++) {

						if (contactData.getWorkingDepartmentList().get(j)
								.contains(value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("IMContact")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData
							.getImContactInformationList().size(); j++) {
						if (contactData.getImContactInformationList().get(j)
								.contains(value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("Birthday")) {
					String value = node.getTextContent();
					if (contactData.getContactBirthday().toString().contains(
							value)) {
						goFlag = true;
						break;
					} else {
						goFlag = false;
					}
				} else if (str.equalsIgnoreCase("url")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getUrlList().size(); j++) {
						if (contactData.getUrlList().get(j).contains(value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("CommonLabel")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getCommonLabelList().size(); j++) {
						if (contactData.getCommonLabelList().get(j).contains(
								value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("Group")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getGroupList().size(); j++) {
						if (contactData.getGroupList().get(j).contains(value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				}
				if (node instanceof Element
						&& !node.getNodeName()
								.equalsIgnoreCase("RelationLabel")) {

					SearchContactIterator(contactData, (Element) node);
				} else {
					// 现在不能对关系标签进行搜索
				}
			}
			return goFlag;
		} else {
			// 精确搜索
			for (int i = 0; i < nodelist.getLength(); i++) {
				Node node = nodelist.item(i);
				String str = node.getNodeName();
				if (str.equalsIgnoreCase("ContactName")) {
					String value = node.getTextContent();
					System.out.println("SearchText____" + value);
					if (contactData.getContactName().equalsIgnoreCase(value))
						goFlag = true;
					else {
						goFlag = false;
						return false;
					}
				} else if (str.equalsIgnoreCase("Telephone")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getPhoneNumberList().size(); j++) {
						if (contactData.getPhoneNumberList().get(j)
								.equalsIgnoreCase(value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("Email")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getEmailAddresseList()
							.size(); j++) {
						if (contactData.getEmailAddresseList().get(j).equals(
								value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("Address")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getContactAddressList()
							.size(); j++) {
						if (contactData.getContactAddressList().get(j)
								.equalsIgnoreCase(value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("Office")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getWorkingDepartmentList()
							.size(); j++) {

						if (contactData.getWorkingDepartmentList().get(j)
								.equalsIgnoreCase(value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("IMContact")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData
							.getImContactInformationList().size(); j++) {
						if (contactData.getImContactInformationList().get(j)
								.equalsIgnoreCase(value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("Birthday")) {
					String value = node.getTextContent();
					System.out.println("Contact----"+contactData.getContactName().toString());
					System.out.println("value---"+value);
					if (contactData.getContactBirthday().toString()
							.equalsIgnoreCase(value)) {
						goFlag = true;
						break;
					} else {
						goFlag = false;
						return false;
					}
				} else if (str.equalsIgnoreCase("url")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getUrlList().size(); j++) {
						if (contactData.getUrlList().get(j).equalsIgnoreCase(
								value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("CommonLabel")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getCommonLabelList().size(); j++) {
						if (contactData.getCommonLabelList().get(j)
								.equalsIgnoreCase(value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				} else if (str.equalsIgnoreCase("Group")) {
					String value = node.getTextContent();
					for (int j = 0; j < contactData.getGroupList().size(); j++) {
						if (contactData.getGroupList().get(j).equalsIgnoreCase(
								value)) {
							goFlag = true;
							break;
						} else {
							goFlag = false;
							continue;
						}
					}
					if (!goFlag) {
						return false;
					}
				}
				// 关于由于关系标签的表示方法，所以这里可能有问题
				// 关系标签的相关处理
				// if(node instanceof Element &&
				// node.getNodeName().equalsIgnoreCase("RelationLabel"))
				// {
				// RelationLabelIterator(iSNList,contactData,(Element)node);
				// }
				if (node instanceof Element
						&& !node.getNodeName()
								.equalsIgnoreCase("RelationLabel")) {

					SearchContactIterator(contactData, (Element) node);
				} 
				
				if (node instanceof Element
						&& node.getNodeName()
								.equalsIgnoreCase("RelationLabel"))	
				{
					System.out.println("RelationLabel Search");
					return RelationLabel(contactData,(Element)node);
				}
			}
		}
		return goFlag;
	}
	//关系标签
	public boolean RelationLabel(ContactOperations contactData,Element element)
	{
		String labelName=null;
		String contactName=null;
		NodeList nodeList=element.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++)
		{
			Node node=nodeList.item(i);
			String str=node.getNodeName();
			if(str.equals("LabelName"))
			{
				labelName=node.getTextContent();
				System.out.println("SearchContact____LabelName"+labelName);
			}
			else if(str.equals("DestName"))
			{
				contactName=node.getTextContent();
				System.out.println("SearchContact____DestName"+labelName);
			}
		}
		for(int i=0;i<contactData.getRelationLabelList().size();i++)
		{
			if(contactData.getRelationLabelList().get(i).getLabelName().equals(labelName))
			{
				//这里如果仅仅按照名字进行查询
					int tempISN=contactData.getRelationLabelList().get(i).getRelationObjectISN();
					if(DataPool.getInstance().getAllContactISNMap().get(tempISN).getContactName().equals(contactName))
					{
						goFlag=true;
					}
					else
					{
						goFlag=false;
						continue;
					}
				}
			}
				
		return goFlag;
	}
}