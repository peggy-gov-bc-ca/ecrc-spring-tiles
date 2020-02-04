/**
 * @(#)LoggingExceptionResolver.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.exceptions;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import ca.bc.gov.ag.ecrc.utils.VCRCLogger;


/**
 * Extends SPRING3 Exception resolver to provide logging.  
 * 
 * @author $Author: smillar $
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class LoggingExceptionResolver extends SimpleMappingExceptionResolver {
	
	private static final Logger logger = Logger.getLogger(LoggingExceptionResolver.class.toString());
	
	@Autowired
	private VCRCLogger vcrcLogger;
	
	@Override
	protected void logException(Exception ex, HttpServletRequest request) {
		
		vcrcLogger.initialize(request);
		vcrcLogger.log(this, Level.ERROR, "AN EXCEPTION HAS OCCURRED: " + ex.getMessage());
	}
}