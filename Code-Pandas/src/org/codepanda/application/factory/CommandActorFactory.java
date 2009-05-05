/**
 * 
 */
package org.codepanda.application.factory;

import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;

/**
 * @author hszcg
 * @version 4.17.01
 * 
 * Factory Pattern
 * 
 */
public abstract class CommandActorFactory {
	public abstract CommandActor creator( CommandType commandType, String commandDetail);
}
