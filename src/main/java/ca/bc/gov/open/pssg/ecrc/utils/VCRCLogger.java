/**
 * @(#)VCRCLogger.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.utils;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class VCRCLogger implements Serializable {
	
	private static final Logger logger = Logger.getLogger(VCRCLogger.class.toString());

	/**
	 * 
	 */
	private static final long serialVersionUID = 4187419218393519058L;
	
	private String id;
	
	private static final String NO_ID_ASSIGNED = "--";
	
	public VCRCLogger() {
		logger.log(Level.INFO, "Initializing VCRC Logger service.");
	}
	
	public void initialize(HttpServletRequest request) {
		if (id == null || id.equals(NO_ID_ASSIGNED)) {
			String sessionId = request.getSession().getId();
			//id = sessionId.substring(sessionId.length() - 5).toUpperCase();
			id = sessionId.toUpperCase();
		} 
	}
	
	public void log(Object clazz, Level level, Object message) {
		if (id == null) {
			id = NO_ID_ASSIGNED ; 
		}
		
		Logger logger = Logger.getLogger(clazz.getClass().toString());
		logger.log(level, id + " : " + message);
	}

	public String getId() {
		return id;
	}
	
	
}
