package org.codepanda.utility.group;

import java.util.ArrayList;

/**
 * @author hszcg
 * @version 4.17.01
 */
public class ContactGroup {
	public static final int NORMAL_GROUP = 1;
	public static final int LABEL_GROUP = 2;
	
	private ArrayList<Integer> groupMembers;
	
	private int groupType;

	/**
	 * @param groupMembers the groupMembers to set
	 */
	public void setGroupMembers(ArrayList<Integer> groupMembers) {
		this.groupMembers = groupMembers;
	}

	/**
	 * @return the groupMembers
	 */
	public ArrayList<Integer> getGroupMembers() {
		return groupMembers;
	}

}
