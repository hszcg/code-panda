/**
 * 
 */
package org.codepanda.userinterfacer;

import org.codepanda.application.CommandVisitor;



/**
 * @author hszcg
 * @version 4.16.01
 * 
 */
public interface MessageHandler {
	/**
	 * @param commandVistor
	 */
	public void executeCommand(CommandVisitor commandVistor);
}
