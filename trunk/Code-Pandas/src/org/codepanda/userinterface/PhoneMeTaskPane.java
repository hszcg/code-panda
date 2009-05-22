package org.codepanda.userinterface;

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

		if (node.isLeaf()) {
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
		// TODO
		configureContactList();
	}
}