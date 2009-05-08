/**
 * 
 */
package org.codepanda.application;

/**
 * @author hszcg
 * 
 */
public enum CommandType {
	// Define Command Type
	
	//User Operations
	NEW_USER, 
	DELETE_USER, 
	EDIT_USER, 
	LOGIN_USER,
	
	//Contact Operations
	NEW_CONTACT, 
	DELETE_CONTACT, 
	EDIT_CONTACT, 
	SEARCH_CONTACT,
	STAT_CONTACT, 
	IMPORT_CONTACT, 
	EXPORT_CONTACT,
	
	//Contact Label Operations
	NEW_CONTACT_COMMON_LABEL, 
	EDIT_CONTACT_COMMON_LABEL, 
	DELETE_CONTACT_COMMON_LABEL,
	NEW_CONTACT_RELATION_LABEL, 
	EDIT_CONTACT_RELATION_LABEL, 
	DELETE_CONTACT_RELATION_LABEL,
	
	//Label Operations
	NEW_COMMON_LABEL, 
	EDIT_COMMON_LABEL, 
	DELETE_COMMON_LABEL,
	
	//Extra
	FILT_BIRTHDAYS, 
	GET_SAMENAME_CONTACT, 
	MERGE_CONTACT,
	SYNCHRONOUS_CONTACT,
	GET_RELATION_NET;

	//Initialize Operations
}
