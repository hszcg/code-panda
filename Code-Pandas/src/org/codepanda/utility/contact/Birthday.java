/**
 * 
 */
package org.codepanda.utility.contact;

import java.io.Serializable;

/**
 * 
 * @author hszcg
 * @version 4.16.01
 * 
 */
public class Birthday implements Serializable{
	private Integer year;
	private Integer month;
	private Integer day;
	
	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}
}
