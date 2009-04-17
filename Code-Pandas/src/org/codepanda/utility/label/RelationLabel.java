/**
 * 
 */
package org.codepanda.utility.label;

import org.codepanda.utility.contact.ContactOperations;

/**
 * 
 * @author hszcg
 * @version 4.16.01
 *
 */
public class RelationLabel {
	/**
	 * 
	 */
	private String labelName;
	
	/**
	 * 
	 */
	private ContactOperations relationObject;
	
	/**
	 * @param labelName the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}
	
	/**
	 * @param relationObject the relationObject to set
	 */
	public void setRelationObject(ContactOperations relationObject) {
		this.relationObject = relationObject;
	}
	
	/**
	 * @return the relationObject
	 */
	public ContactOperations getRelationObject() {
		return relationObject;
	}
	
}
