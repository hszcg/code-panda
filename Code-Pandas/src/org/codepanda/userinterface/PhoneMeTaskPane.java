package org.codepanda.userinterface;

import java.util.Vector;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

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

		configureContactList();
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
			System.out.println(((TreeNodeItem) nodeInfo).getISN());
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

		group = new DefaultMutableTreeNode("同学");
		root.add(group);

		contact = new DefaultMutableTreeNode(new TreeNodeItem("张三", 1));
		group.add(contact);
		contact = new DefaultMutableTreeNode(new TreeNodeItem("李四", 2));
		group.add(contact);

		group = new DefaultMutableTreeNode(new TreeNodeItem("朋友", 3));
		root.add(group);

		contact = new DefaultMutableTreeNode(new TreeNodeItem("王五", 4));
		group.add(contact);
		contact = new DefaultMutableTreeNode(new TreeNodeItem("赵六", 5));
		group.add(contact);
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
}
