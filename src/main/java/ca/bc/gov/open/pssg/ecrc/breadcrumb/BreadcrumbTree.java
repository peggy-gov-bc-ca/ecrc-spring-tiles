/**
 * @(#)BreadcrumbTree.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.breadcrumb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class BreadcrumbTree implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606827858322499978L;
	
	private List<BreadcrumbNode> breadcrumbs;
	
	public BreadcrumbTree() {
		breadcrumbs = new ArrayList<BreadcrumbNode>();
	}
	
	public List<BreadcrumbNode> getBreadcrumbs() {
		return breadcrumbs;
	}
	
	public void addNode(BreadcrumbNode node) {
		BreadcrumbNode existing = findNode(node);
		
		if (existing != null) {
			int position = breadcrumbs.indexOf(existing);
			for (int i = breadcrumbs.size() - 1; i >= position; i--) {
				breadcrumbs.remove(i);
			}
		} 
		
		breadcrumbs.add(node);
		
	}
	
	private BreadcrumbNode findNode(BreadcrumbNode node) {
		for (BreadcrumbNode n : breadcrumbs) {
			if (node.getLevel() == n.getLevel()) {
				return n;
			}
		}
		
		return null;
	}
}
