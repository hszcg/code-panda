/**
 * 
 */
package org.codepanda.userInterface;

import org.codepanda.application.CommandVistor;



/**
 * @author hszcg
 * @version 4.16.01
 * 
 */
public interface MessageHandler {
	/**
	 * @param commandVistor
	 */
	public void executeCommand(CommandVistor commandVistor);
}
