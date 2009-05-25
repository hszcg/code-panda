package org.codepanda.userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.SearchContactMessageHandler;
import org.codepanda.userinterface.xml.MyXMLMaker;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.ContactSectionType;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.group.ContactGroup;
import org.jdesktop.swingx.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class PhoneMeTaskPane extends JXTaskPaneContainer implements
		TreeSelectionListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2688085485464738955L;
	private PhoneMeFrame parentFrame;
	private JXTaskPane searchTaskPane;
	private JTextField searchField;
	private ArrayList<String> autoCompleteList;

	private JXTaskPane contactListTaskPane;
	private JScrollPane treeViewPane;
	private JTree contactListTree;

	public PhoneMeTaskPane(PhoneMeFrame phoneMeFrame) {
		super();
		this.parentFrame = phoneMeFrame;
	}

	public void configureContactList() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("所有分组");
		createTree(root);
		contactListTree = new JTree(root);
		contactListTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		contactListTree.addTreeSelectionListener(this);
		treeViewPane = new JScrollPane(contactListTree);

		contactListTaskPane = new JXTaskPane();
		contactListTaskPane.setTitle("联系人分组列表 ");
		contactListTaskPane.add(treeViewPane);
		this.add(contactListTaskPane);
	}

	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) contactListTree
				.getLastSelectedPathComponent();
		if (node == null)
			// Nothing is selected.
			return;
		Object nodeInfo = node.getUserObject();

		if (node.isLeaf() && nodeInfo instanceof TreeNodeItem) {
			int iSN = ((TreeNodeItem) nodeInfo).getISN();
			ContactOperations myContact = DataPool.getInstance()
					.getAllContactISNMap().get(iSN);
			String name = myContact.getContactName();
			if (name == null || name.length() == 0)
				name = "N/A";

			parentFrame.getMyPhoneMeMajorPanel().addNewTab(
					name,
					new ContactInfoPanel(parentFrame, myContact, false,
							ContactInfoPanel.CONTACT_INFO_PANEL));
		} else {
			// displayURL(helpURL);
		}
	}

	/**
	 * @param root
	 */
	private void createTree(DefaultMutableTreeNode root) {
		DefaultMutableTreeNode group = null;
		DefaultMutableTreeNode contact = null;

		//System.out.println("Create Tree!");

		final HashMap<String, ContactGroup> allContactGroup = DataPool
				.getInstance().getAllContactGroupMap();

		//System.out.println("GROUP" + allContactGroup.size());

		final HashMap<Integer, ContactOperations> allContactISN = DataPool
				.getInstance().getAllContactISNMap();

		//System.out.println("ALL CONTACT" + allContactISN.size());

		Iterator<Entry<String, ContactGroup>> it = allContactGroup.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, ContactGroup> entry = (Entry<String, ContactGroup>) it
					.next();
			group = new DefaultMutableTreeNode(entry.getKey());
			root.add(group);

			final HashSet<Integer> groupMember = entry.getValue()
					.getGroupMembers();
			Iterator<Integer> newIt = groupMember.iterator();

			while (newIt.hasNext()) {
				ContactOperations c = allContactISN.get(newIt.next());
				System.out.println(c.getContactName());
				contact = new DefaultMutableTreeNode(new TreeNodeItem(c
						.getContactName(), c.getISN()));
				group.add(contact);
			}
		}

		// 未分组
		group = new DefaultMutableTreeNode("未分组");
		root.add(group);

		Iterator<Entry<Integer, ContactOperations>> itNew = allContactISN
				.entrySet().iterator();
		while (itNew.hasNext()) {
			Entry<Integer, ContactOperations> entry = (Entry<Integer, ContactOperations>) itNew
					.next();

			ContactOperations c = allContactISN.get(entry.getKey());

			if (c.getGroupList() == null || c.getGroupList().size() == 0) {
				contact = new DefaultMutableTreeNode(new TreeNodeItem(c
						.getContactName(), c.getISN()));

				group.add(contact);
			}
		}
	}

	/**
	 * @return the mainFrame
	 */
	public PhoneMeFrame getMainFrame() {
		return parentFrame;
	}

	/**
	 * @author hszcg
	 * 
	 */
	private class TreeNodeItem {
		private String name;
		private Integer iSN;

		TreeNodeItem(String name, Integer iSN) {
			this.name = name;
			this.iSN = iSN;
		}

		public String toString() {
			return name;
		}

		public Integer getISN() {
			return iSN;
		}

	}

	/**
	 * 
	 */
	public void initializeData() {
		configureSearchField();
		configureContactList();
	}

	private void configureSearchField() {
		// TODO Auto-generated method stub
		// 搜索
		searchTaskPane = new JXTaskPane();
		searchTaskPane.setTitle("按姓名搜索    ");
		searchField = new JTextField("");
		autoCompleteList = new ArrayList<String>();
		autoCompleteList.addAll(DataPool.getInstance().getAllContactNameMultimap().keySet());
		AutoCompleteDecorator.decorate(searchField, autoCompleteList, false);
		searchTaskPane.add(searchField);
		searchField.addActionListener(this);
		this.add(searchTaskPane);
	}

	/**
	 * @param updateISN
	 */
	public void updateGroupList() {
		// TODO Auto-generated method stub
		this.remove(contactListTaskPane);
		configureContactList();
		this.repaint();
		autoCompleteList = new ArrayList<String>();
		autoCompleteList.addAll(DataPool.getInstance().getAllContactNameMultimap().keySet());
		AutoCompleteDecorator.decorate(searchField, autoCompleteList, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == searchField) {
			StringBuffer message = new StringBuffer();
			message.append(MyXMLMaker.addTag("BlurSearch", "1"));
			message.append(MyXMLMaker.addTag("ContactName", searchField
					.getText()));
			String xml = message.toString();
			xml = MyXMLMaker.addTag("SearchContact", xml);
			xml = MyXMLMaker.addTag("com", xml);
			CommandVisitor searchContactCommandVisitor = new CommandVisitor(
					CommandType.SEARCH_CONTACT, xml);
			SearchContactMessageHandler searchContactMessageHandler = new SearchContactMessageHandler();
			ArrayList<Integer> resultContactList = (ArrayList<Integer>) searchContactMessageHandler
					.executeCommand(searchContactCommandVisitor);
			parentFrame.getMyPhoneMeMajorPanel().addNewTab(
					"Search Name Result",
					new SearchResultPanel(parentFrame, resultContactList,
							ContactSectionType.PHONE_NUMBER).getMainPanel());
		}
	}

	// ContactOperations p = DataPool.getInstance().getAllContactISNMap().get(
	// updateISN);
	//
	// if (p == null) {
	// // 删除联系人之后的更新
	// System.out.println("DELETE");
	// deleteISNNode(updateISN);
	// } else {
	// // 修改/新建 联系人之后的更新
	// System.out.println("UPDATE");
	// updateNodes(p);
	// }

	// model.insertNodeInto(cNode, pNode, 0);

	// /**
	// * @param p
	// */
	// private void updateNodes(ContactOperations p) {
	// DefaultMutableTreeNode root = (DefaultMutableTreeNode)
	// this.contactListTree
	// .getModel().getRoot();
	// DefaultTreeModel model = (DefaultTreeModel) this.contactListTree
	// .getModel();
	// visitAndUpdateAllNodes(root, model, p);
	// // TODO update
	// // model.nodeStructureChanged(root);
	// }
	//
	// /**
	// * @param node
	// * @param model
	// * @param p
	// */
	// private boolean visitAndUpdateAllNodes(DefaultMutableTreeNode node,
	// DefaultTreeModel model, ContactOperations p) {
	// // TODO Auto-generated method stub
	// boolean hasFindISN = false;
	// Object nodeInfo = ((DefaultMutableTreeNode) node).getUserObject();
	//
	// System.out.println("UPDATE CON++" + p.getContactName());
	//
	// for (Enumeration e = node.children(); e.hasMoreElements();) {
	// DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();
	// Object nInfo = ((DefaultMutableTreeNode) n).getUserObject();
	//
	// System.out.println("UPDATE NODE++" + nInfo.toString());
	//
	// if (nInfo instanceof TreeNodeItem
	// && ((TreeNodeItem) nInfo).iSN == p.getISN()) {
	// // 原来的节点处理
	// hasFindISN = true;
	//
	// boolean isStillInList = false;
	// String groupName = (String) nodeInfo;
	// for (String str : p.getGroupList()) {
	// if (str.equals(groupName)) {
	// isStillInList = true;
	// ((TreeNodeItem) nInfo).name = p.getContactName();
	// }
	// }
	//
	// if (isStillInList == false) {
	// System.out.println("Remove++" + n.toString());
	// model.removeNodeFromParent(n);
	// // model.nodesWereRemoved(node, childIndices,
	// // removedChildren)
	// }
	// } else if (n.isLeaf()
	// || visitAndUpdateAllNodes(n, model, p) == false) {
	// // 原来没有，可能需要新加
	// if (nInfo instanceof String) {
	// // 可能的新加节点处理
	// String groupName = (String) nInfo;
	// System.out.println("CURRENT GROUP+++" + groupName);
	//
	// for (String str : p.getGroupList()) {
	// System.out.println("GET NAME+++" + str);
	// if (str.equals(groupName)) {
	// System.out.println("ADD TO GROUP+++" + groupName);
	//
	// DefaultMutableTreeNode temp = new DefaultMutableTreeNode(
	// new TreeNodeItem(p.getContactName(), p
	// .getISN()));
	//
	// model.insertNodeInto(temp, node, node
	// .getChildCount());
	// int[] t = new int[] { node.getChildCount() - 1 };
	//
	// model.nodesWereInserted(node, t);
	// break;
	// }
	// }
	// }
	// }
	// }
	// return hasFindISN;
	// }
	//
	// /**
	// * @param deleteISN
	// */
	// private void deleteISNNode(int deleteISN) {
	// DefaultMutableTreeNode root = (DefaultMutableTreeNode)
	// this.contactListTree
	// .getModel().getRoot();
	// DefaultTreeModel model = (DefaultTreeModel) this.contactListTree
	// .getModel();
	// visitAndDeleteAllNodes(root, model, deleteISN);
	// }
	//
	// /**
	// * @param node
	// * @param model
	// * @param deleteISN
	// */
	// private void visitAndDeleteAllNodes(DefaultMutableTreeNode node,
	// DefaultTreeModel model, int deleteISN) {
	// Object nodeInfo = ((DefaultMutableTreeNode) node).getUserObject();
	//
	// if (node.isLeaf() && nodeInfo instanceof TreeNodeItem) {
	// if (((TreeNodeItem) nodeInfo).iSN == deleteISN) {
	// model.removeNodeFromParent(node);
	// }
	// } else {
	//
	// for (Enumeration e = node.children(); e.hasMoreElements();) {
	// DefaultMutableTreeNode n = (DefaultMutableTreeNode) e
	// .nextElement();
	// visitAndDeleteAllNodes(n, model, deleteISN);
	// }
	//
	// }
	//
	// }

}
