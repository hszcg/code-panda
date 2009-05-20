/**
 * 
 */
package org.codepanda.userinterface.messagehandler;

import org.codepanda.application.CommandVisitor;



/**
 * @author hszcg
 * @version 4.16.01
 * 
 */
public interface MessageHandler {
	/**
	 * @param commandVistor
	 * @return
	 */
	public Object executeCommand(CommandVisitor commandVistor);  // ? visitor ?
}