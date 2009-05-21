package org.codepanda.userinterface;

import java.util.Vector;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import org.jdesktop.swingx.*;

public class PhoneMeTaskPane extends JXTaskPaneContainer
								implements TreeSelectionListener {
	//JXTaskPane currentUserPane;
	//JXLabel usernameLabel;
	//JTextField usernameField;
	
	private PhoneMeFrame mainFrame;
	private JXTaskPane details;
	private JTextField searchField;

	//JXTaskPane contactListPane;
	JScrollPane treeView;
	JTree contactListTree;
	
	public PhoneMeTaskPane(PhoneMeFrame phoneMeFrame){
		super();
		/*currentUserPane = new JXTaskPane();
		currentUserPane.setTitle("用户信息");
		usernameLabel = new JXLabel("当前用户");
		usernameField = new JTextField("");
		currentUserPane.add(usernameLabel);
		currentUserPane.add(usernameField);
		add(currentUserPane);*/
		// create another taskPane, it will show details of the selected file
		this.mainFrame = phoneMeFrame;
		details = new JXTaskPane();
		details.setTitle("按姓名搜索    ");

		// add standard components to the details taskPane
		//JXLabel searchLabel = new JXLabel("Search:");
		searchField = new JTextField("");
		//details.add(searchLabel);
		details.add(searchField);
		add(details);
		
		configureContactList();
	}
	
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
	
	public void configureContactList(){
		DefaultMutableTreeNode root =
            new DefaultMutableTreeNode("联系人分组列表");
		createTree(root);
		contactListTree = new JTree(root);
		contactListTree.getSelectionModel().setSelectionMode
        (TreeSelectionModel.SINGLE_TREE_SELECTION);
		contactListTree.addTreeSelectionListener(this);
		treeView = new JScrollPane(contactListTree);
		add(treeView);
	}
	
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
        contactListTree.getLastSelectedPathComponent();
		if (node == null)
			//Nothing is selected.	
			return;
		Object nodeInfo = node.getUserObject();
		if (node.isLeaf()) {
			System.out.println(((TreeNodeItem)nodeInfo).getISN());
		} 
		else {
			//displayURL(helpURL);
		}
	}

	/**
	 * @return the mainFrame
	 */
	public PhoneMeFrame getMainFrame() {
		return mainFrame;
	}

	private class TreeNodeItem{
		private String name;
		private Integer iSN;

		TreeNodeItem(String name, Integer iSN){
			this.name = name;
			this.iSN = iSN;
		}
		
		public String toString(){
			return name;
		}
		
		public Integer getISN() {
			return iSN;
		}
		
	}
}
