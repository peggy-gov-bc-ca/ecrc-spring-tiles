/**
 * @(#)BreadcrumbNode.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.breadcrumb;
import java.io.Serializable;
/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class BreadcrumbNode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3814465473481108884L;
	
	private String label;
	private int level;
	
	public BreadcrumbNode(String label, int level) {
		this.label = label;
		this.level = level;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
