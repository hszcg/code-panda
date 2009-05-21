package org.codepanda.userinterface;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
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
	// JXTaskPane currentUserPane;
	// JXLabel usernameLabel;
	// JTextField usernameField;

	private PhoneMeFrame mainFrame;
	private JXTaskPane searchTaskPane;
	private JTextField searchField;

	private JXTaskPane contactListTaskPane;
	private JScrollPane treeViewPane;
	private JTree contactListTree;

	public PhoneMeTaskPane(PhoneMeFrame phoneMeFrame) {
		super();
		this.mainFrame = phoneMeFrame;

		// 搜索
		searchTaskPane = new JXTaskPane();
		searchTaskPane.setTitle("按姓名搜索    ");
		searchField = new JTextField("");
		searchTaskPane.add(searchField);
		this.add(searchTaskPane);
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
		if (node.isLeaf()) {
			// System.out.println(((TreeNodeItem) nodeInfo).getISN());
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
				root.add(contact);
			}
		}
	}

	/**
	 * @return the mainFrame
	 */
	public PhoneMeFrame getMainFrame() {
		return mainFrame;
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
		// TODO
		configureContactList();
	}
}
