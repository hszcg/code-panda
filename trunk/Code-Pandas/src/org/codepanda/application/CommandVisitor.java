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

}
