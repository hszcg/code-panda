package org.codepanda.userinterface;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.group.ContactGroup;
import org.jdesktop.swingx.*;

public class PhoneMeTaskPane extends JXTaskPaneContainer implements
		TreeSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2688085485464738955L;
	private PhoneMeFrame parentFrame;
	private JXTaskPane searchTaskPane;
	private JTextField searchField;

	private JXTaskPane contactListTaskPane;
	private JScrollPane treeViewPane;
	private JTree contactListTree;

	public PhoneMeTaskPane(PhoneMeFrame phoneMeFrame) {
		super();
		this.parentFrame = phoneMeFrame;

		// ����
		searchTaskPane = new JXTaskPane();
		searchTaskPane.setTitle("����������    ");
		searchField = new JTextField("");
		searchTaskPane.add(searchField);
		this.add(searchTaskPane);
	}

	public void configureContactList() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("���з���");
		createTree(root);
		contactListTree = new JTree(root);
		contactListTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		contactListTree.addTreeSelectionListener(this);
		treeViewPane = new JScrollPane(contactListTree);

		contactListTaskPane = new JXTaskPane();
		contactListTaskPane.setTitle("��ϵ�˷����б� ");
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
			if (name == null)
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

		System.out.println("Create Tree!");

		final HashMap<String, ContactGroup> allContactGroup = DataPool
				.getInstance().getAllContactGroupMap();

		System.out.println("GROUP" + allContactGroup.size());

		final HashMap<Integer, ContactOperations> allContactISN = DataPool
				.getInstance().getAllContactISNMap();

		System.out.println("ALL CONTACT" + allContactISN.size());

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
		configureContactList();
	}

	/**
	 * @param updateISN
	 */
	public void updateGroupList(int updateISN) {
		// TODO Auto-generated method stub
		ContactOperations p = DataPool.getInstance().getAllContactISNMap().get(
				updateISN);

		if (p == null) {
			// ɾ����ϵ��֮��ĸ���
			System.out.println("DELETE");
			deleteISNNode(updateISN);
		} else {
			// �޸�/�½� ��ϵ��֮��ĸ���
			System.out.println("UPDATE");
			updateNodes(p);
		}

		// model.insertNodeInto(cNode, pNode, 0);
	}

	/**
	 * @param p
	 */
	private void updateNodes(ContactOperations p) {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.contactListTree
				.getModel().getRoot();
		DefaultTreeModel model = (DefaultTreeModel) this.contactListTree
				.getModel();
		visitAndUpdateAllNodes(root, model, p);
	}

	/**
	 * @param node
	 * @param model
	 * @param p
	 */
	private boolean visitAndUpdateAllNodes(DefaultMutableTreeNode node,
			DefaultTreeModel model, ContactOperations p) {
		// TODO Auto-generated method stub
		boolean hasFindISN = false;
		Object nodeInfo = ((DefaultMutableTreeNode) node).getUserObject();
		
		System.out.println("UPDATE CON" + p.getContactName());

		for (Enumeration e = node.children(); e.hasMoreElements();) {
			DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();
			Object nInfo = ((DefaultMutableTreeNode) n).getUserObject();
			if (!n.isLeaf()) {
				if (visitAndUpdateAllNodes(n, model, p) == false) {
					// ԭ��û�У�������Ҫ�¼�
					if (nInfo instanceof String) {
						// ���ܵ��¼ӽڵ㴦��
						String groupName = (String) nodeInfo;

						for (String str : p.getGroupList()) {
							if (str.equals(groupName)) {
								model.insertNodeInto(
										new DefaultMutableTreeNode(
												new TreeNodeItem(p
														.getContactName(), p
														.getISN())), node, node
												.getChildCount() + 1);
								break;
							}
						}
					}
				}
			} else if (nInfo instanceof TreeNodeItem
					&& ((TreeNodeItem) nInfo).iSN == p.getISN()) {
				// ԭ���Ľڵ㴦��
				hasFindISN = true;

				boolean isStillInList = false;
				String groupName = (String) nodeInfo;
				for (String str : p.getGroupList()) {
					if (str.equals(groupName))
						isStillInList = true;
				}

				if (isStillInList == false) {
					model.removeNodeFromParent(n);
				}
			}

		}
		return hasFindISN;

	}

	/**
	 * @param deleteISN
	 */
	private void deleteISNNode(int deleteISN) {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.contactListTree
				.getModel().getRoot();
		DefaultTreeModel model = (DefaultTreeModel) this.contactListTree
				.getModel();
		visitAndDeleteAllNodes(root, model, deleteISN);
	}

	/**
	 * @param node
	 * @param model
	 * @param deleteISN
	 */
	private void visitAndDeleteAllNodes(DefaultMutableTreeNode node,
			DefaultTreeModel model, int deleteISN) {
		Object nodeInfo = ((DefaultMutableTreeNode) node).getUserObject();

		if (node.isLeaf() && nodeInfo instanceof TreeNodeItem) {
			if (((TreeNodeItem) nodeInfo).iSN == deleteISN) {
				model.removeNodeFromParent(node);
			}
		} else {

			for (Enumeration e = node.children(); e.hasMoreElements();) {
				DefaultMutableTreeNode n = (DefaultMutableTreeNode) e
						.nextElement();
				visitAndDeleteAllNodes(n, model, deleteISN);
			}

		}

	}

}
