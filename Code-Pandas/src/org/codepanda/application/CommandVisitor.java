/**
 * 
 */
package org.codepanda.application;

/**
 * @author hszcg
 *
 */
public abstract class CommandVisitor {
	
	/**
	 * One of Commands Given Above
	 * 
	 */
	private CommandType commandType;
	
	/**
	 * 
	 */
	private String commandDetail;
	
	/**
	 * To Execute Command
	 * 
	 */
	private CommandActor commandActor;

	/**
	 * @param commandActor the commandActor to set
	 */
	public void setCommandActor(CommandActor commandActor) {
		this.commandActor = commandActor;
	}

	/**
	 * @return the commandActor
	 */
	public CommandActor getCommandActor() {
		return commandActor;
	}

	/**
	 * @param commandType the commandType to set
	 */
	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	/**
	 * @return the commandType
	 */
	public CommandType getCommandType() {
		return commandType;
	}

	/**
	 * @param commandDetail the commandDetail to set
	 */
	public void setCommandDetail(String commandDetail) {
		this.commandDetail = commandDetail;
	}

	/**
	 * @return the commandDetail
	 */
	public String getCommandDetail() {
		return commandDetail;
	}

}
