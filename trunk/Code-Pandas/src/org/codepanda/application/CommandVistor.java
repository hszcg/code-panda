/**
 * 
 */
package org.codepanda.application;

/**
 * @author hszcg
 *
 */
public abstract class CommandVistor {
	//Define Command Type
	
	//User Operations
	public static final int NEW_USER = 1;
	public static final int DELETE_USER = 2;
	public static final int EDIT_USER = 3;
	public static final int LOGIN_USER = 4;
	
	//Contact Operations
	public static final int NEW_CONTACT = 5;
	public static final int DELETE_CONTACT = 6;
	public static final int EDIT_CONTACT = 7;
	public static final int SEARCH_CONTACT = 8;
	public static final int STAT_CONTACT = 9;
	public static final int IMPORT_CONTACT = 10;
	public static final int EXPORT_CONTACT = 11;
	
	//Contact Label Operations
	public static final int NEW_CONTACT_COMMON_LABEL = 12;
	public static final int EDIT_CONTACT_COMMON_LABEL = 13;
	public static final int DELETE_CONTACT_COMMON_LABEL = 14;
	
	public static final int NEW_CONTACT_RELATION_LABEL = 15;
	public static final int EDIT_CONTACT_RELATION_LABEL = 16;
	public static final int DELETE_CONTACT_RELATION_LABEL = 17;
	
	//Label Operations
	public static final int NEW_COMMON_LABEL = 18;
	public static final int EDIT_COMMON_LABEL = 19;
	public static final int DELETE_COMMON_LABEL = 20;
	
	//Extra
	public static final int FILT_BIRTHDAYS = 21;
	public static final int GET_SAMENAME_CONTACT = 22;
	public static final int MERGE_CONTACT = 23;
	public static final int SYNCHRONOUS_CONTACT = 24;
	public static final int GET_RELATION_NET = 25;
	
	/**
	 * One of Commands Given Above
	 * 
	 */
	private int commandType;
	
	
	/**
	 * To Execute Command
	 * 
	 */
	private int CommandActor;

	/**
	 * @param commandType the commandType to set
	 */
	public void setCommandType(int commandType) {
		this.commandType = commandType;
	}

	/**
	 * @return the commandType
	 */
	public int getCommandType() {
		return commandType;
	}

	/**
	 * @param commandActor the commandActor to set
	 */
	public void setCommandActor(int commandActor) {
		CommandActor = commandActor;
	}

	/**
	 * @return the commandActor
	 */
	public int getCommandActor() {
		return CommandActor;
	}
}
