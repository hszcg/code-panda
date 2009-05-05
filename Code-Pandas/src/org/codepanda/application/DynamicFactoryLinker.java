package org.codepanda.application;

import java.util.HashMap;

/**
 * @author hszcg
 *
 */
public class DynamicFactoryLinker {
	/**
	 * 
	 */
	private static HashMap<CommandType, String> linkerMap = null;
	
	
	/**
	 * @param key
	 * @return
	 */
	public static String getDynamicFactoryLinker(CommandType key) {
		if( linkerMap == null )
			DynamicFactoryLinker.initDynamicFactoryLinker();
		
		return linkerMap.get(key);
	}
	
	/**
	 * 
	 */
	private static void initDynamicFactoryLinker(){
		linkerMap = new HashMap<CommandType, String>(CommandType.values().length);
		
		linkerMap.put(CommandType.NEW_USER, "org.codepanda.application.factory.UserActorFactory");
		linkerMap.put(CommandType.DELETE_USER, "org.codepanda.application.factory.UserActorFactory");
		linkerMap.put(CommandType.EDIT_USER, "org.codepanda.application.factory.UserActorFactory");
		linkerMap.put(CommandType.LOGIN_USER, "org.codepanda.application.factory.UserActorFactory");

		linkerMap.put(CommandType.NEW_CONTACT, "org.codepanda.application.factory.ContactActorFactory");
		linkerMap.put(CommandType.DELETE_CONTACT, "org.codepanda.application.factory.ContactActorFactory");
		linkerMap.put(CommandType.EDIT_CONTACT, "org.codepanda.application.factory.ContactActorFactory");
		linkerMap.put(CommandType.SEARCH_CONTACT, "org.codepanda.application.factory.ContactActorFactory");
		linkerMap.put(CommandType.STAT_CONTACT, "org.codepanda.application.factory.ContactActorFactory");
		linkerMap.put(CommandType.IMPORT_CONTACT, "org.codepanda.application.factory.ContactActorFactory");
		linkerMap.put(CommandType.EXPORT_CONTACT, "org.codepanda.application.factory.ContactActorFactory");
		
		linkerMap.put(CommandType.NEW_CONTACT_COMMON_LABEL, "org.codepanda.application.factory.ContactLabelActorFactory");
		linkerMap.put(CommandType.EDIT_CONTACT_COMMON_LABEL, "org.codepanda.application.factory.ContactLabelActorFactory");
		linkerMap.put(CommandType.DELETE_CONTACT_COMMON_LABEL, "org.codepanda.application.factory.ContactLabelActorFactory");
		linkerMap.put(CommandType.NEW_CONTACT_RELATION_LABEL, "org.codepanda.application.factory.ContactLabelActorFactory");
		linkerMap.put(CommandType.EDIT_CONTACT_RELATION_LABEL, "org.codepanda.application.factory.ContactLabelActorFactory");
		linkerMap.put(CommandType.DELETE_CONTACT_RELATION_LABEL, "org.codepanda.application.factory.ContactLabelActorFactory");
		
		linkerMap.put(CommandType.NEW_COMMON_LABEL, "org.codepanda.application.factory.LabelActorFactory");
		linkerMap.put(CommandType.EDIT_COMMON_LABEL, "org.codepanda.application.factory.LabelActorFactory");
		linkerMap.put(CommandType.DELETE_COMMON_LABEL, "org.codepanda.application.factory.LabelActorFactory");
		
		
		linkerMap.put(CommandType.FILT_BIRTHDAYS, "org.codepanda.application.factory.ExtraActorFactory");
		linkerMap.put(CommandType.GET_SAMENAME_CONTACT, "org.codepanda.application.factory.ExtraActorFactory");
		linkerMap.put(CommandType.MERGE_CONTACT, "org.codepanda.application.factory.ExtraActorFactory");
		linkerMap.put(CommandType.SYNCHRONOUS_CONTACT, "org.codepanda.application.factory.ExtraActorFactory");
		linkerMap.put(CommandType.GET_RELATION_NET, "org.codepanda.application.factory.ExtraActorFactory");
	}
}
