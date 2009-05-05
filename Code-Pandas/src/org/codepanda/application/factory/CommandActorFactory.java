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
	/**
	 * @param commandType
	 * @param commandDetail
	 * @return
	 */
	public abstract CommandActor creator( CommandType commandType, String commandDetail);
	
	/**
	 * 为了实现动态加载生成需要的CommandActor
	 * 请参考以下代码：
	 * 
	 * 
	 * 获取动态链接的类名
	 * String className = DynamicFactoryLinker.getDynamicFactoryLinker(commandType);
	 * 
	 * 通过动态加载类来生成需要的CommandActor
	 * CommandActor actor = (CommandActorFactory)(Class.forName(className)).creator();
	 * 
	 * */
}
