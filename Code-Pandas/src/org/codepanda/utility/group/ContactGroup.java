package org.codepanda.utility.group;

import java.util.ArrayList;

/**
 * @author hszcg
 * @version 4.17.01
 */
public class ContactGroup {
	private ArrayList<Integer> groupMembers;
	
	private GroupType groupType;

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
