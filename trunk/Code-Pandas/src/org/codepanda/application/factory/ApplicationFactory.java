/**
 * 
 */
package org.codepanda.application.factory;

import org.codepanda.application.CommandActor;

/**
 * @author hszcg
 * @version 4.17.01
 * 
 * Factory Pattern
 * 
 */
public abstract class ApplicationFactory {
	public abstract CommandActor creator( int commandType, String commandDetail);
}
