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
	 * 请参考以下代码
	 * 
	public class CatalogDAOFactory { 

　　//本方法制定一个特别的子类来实现DAO模式。
　　//具体子类定义是在J2EE的部署描述器中。
　　	public static CatalogDAO getDAO() throws CatalogDAOSysException {

　　　　CatalogDAO catDao = null;

　　　　try {

　　　　　　InitialContext ic = new InitialContext();
　　　　　　//动态装入CATALOG_DAO_CLASS
　　　　　　//可以定义自己的CATALOG_DAO_CLASS，从而在无需变更太多代码
　　　　　　//的前提下，完成系统的巨大变更。

　　　　　　String className =(String) ic.lookup(JNDINames.CATALOG_DAO_CLASS);

　　　　　　catDao = (CatalogDAO) Class.forName(className).newInstance();

　　　　} catch (NamingException ne) {

　　　　　　throw new CatalogDAOSysException("
　　　　　　　　CatalogDAOFactory.getDAO: NamingException while 
　　　　　　　　　　getting DAO type : \n" + ne.getMessage());

　　　　} catch (Exception se) {

　　　　　　throw new CatalogDAOSysException("
　　　　　　　　CatalogDAOFactory.getDAO: Exception while getting 
　　　　　　　　　　DAO type : \n" + se.getMessage());

　　　　}

　　　　return catDao;

　　}}

	 * 
	 * */
}
