/**
 * 
 */
package org.codepanda.application;

import org.codepanda.application.factory.CommandActorFactory;

/**
 * @author hszcg
 *
 */
public class CommandVisitor {
	
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

	public CommandVisitor(CommandType commandType, String commandDetail) {
		this.commandType = commandType;
		this.commandDetail = commandDetail;
		
		String className = DynamicFactoryLinker.getDynamicFactoryLinker(commandType);
		
		try {
			this.setCommandActor(((CommandActorFactory)(Class.forName(className).newInstance())).creator(commandType, commandDetail));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	
}
