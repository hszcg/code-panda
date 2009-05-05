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
	 * ��ο����´���
	 * 
	public class CatalogDAOFactory { 

����//�������ƶ�һ���ر��������ʵ��DAOģʽ��
����//�������ඨ������J2EE�Ĳ����������С�
����	public static CatalogDAO getDAO() throws CatalogDAOSysException {

��������CatalogDAO catDao = null;

��������try {

������������InitialContext ic = new InitialContext();
������������//��̬װ��CATALOG_DAO_CLASS
������������//���Զ����Լ���CATALOG_DAO_CLASS���Ӷ���������̫�����
������������//��ǰ���£����ϵͳ�ľ޴�����

������������String className =(String) ic.lookup(JNDINames.CATALOG_DAO_CLASS);

������������catDao = (CatalogDAO) Class.forName(className).newInstance();

��������} catch (NamingException ne) {

������������throw new CatalogDAOSysException("
����������������CatalogDAOFactory.getDAO: NamingException while 
��������������������getting DAO type : \n" + ne.getMessage());

��������} catch (Exception se) {

������������throw new CatalogDAOSysException("
����������������CatalogDAOFactory.getDAO: Exception while getting 
��������������������DAO type : \n" + se.getMessage());

��������}

��������return catDao;

����}}

	 * 
	 * */
}
