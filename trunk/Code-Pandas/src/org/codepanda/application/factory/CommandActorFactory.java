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
	 * Ϊ��ʵ�ֶ�̬����������Ҫ��CommandActor
	 * ��ο����´��룺
	 * 
	 * 
	 * ��ȡ��̬���ӵ�����
	 * String className = DynamicFactoryLinker.getDynamicFactoryLinker(commandType);
	 * 
	 * ͨ����̬��������������Ҫ��CommandActor
	 * CommandActor actor = (CommandActorFactory)(Class.forName(className)).creator();
	 * 
	 * */
}
