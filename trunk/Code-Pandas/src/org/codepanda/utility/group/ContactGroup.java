package org.codepanda.utility.group;

import java.util.HashSet;

/**
 * @author hszcg
 * @version 4.17.01
 */
public class ContactGroup {
	private HashSet<Integer> groupMembers;
	private String groupName;
	private GroupType groupType;
	

	/**
	 * @param type
	 * @param name
	 */
	public ContactGroup( GroupType type, String name){
		this.groupMembers = new HashSet<Integer>();
		this.setGroupType(type);
		this.setGroupName(name);
	}
	
	
	/**
	 * @param ISN
	 */
	public void addGroupMember(Integer ISN){
		groupMembers.add(ISN);
	}

	/**
	 * @return the groupMembers
	 */
	public final HashSet<Integer> getGroupMembers() {
		return groupMembers;
	}


	/**
	 * @param groupType the groupType to set
	 */
	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
	}


	/**
	 * @return the groupType
	 */
	public GroupType getGroupType() {
		return groupType;
	}


	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

}
