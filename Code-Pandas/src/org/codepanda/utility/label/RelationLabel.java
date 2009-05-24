/**
 * 
 */
package org.codepanda.utility.label;

import java.io.Serializable;

/**
 * 
 * @author hszcg
 * @version 4.16.01
 *
 */
public class RelationLabel implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -3619647312177809215L;

	/**
	 * 
	 */
	private String labelName;
	
	/**
	 * 
	 */
	private Integer relationObjectISN;
	
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
	 * @param relationObjectISN the relationObjectISN to set
	 */
	public void setRelationObjectISN(Integer relationObjectISN) {
		this.relationObjectISN = relationObjectISN;
	}

	/**
	 * @return the relationObjectISN
	 */
	public Integer getRelationObjectISN() {
		return relationObjectISN;
	}
	
	
}
