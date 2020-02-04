/**
 * @(#)ECRCUtils.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.utils;

import java.util.Properties;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Various VCRC generic Utils.  
 * 
 * @author $Author: smillar $
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class ECRCUtils {
	
	@Autowired
	private Properties ecrc_props;
	
	private static final Logger logger = Logger.getLogger(ECRCUtils.class.toString());
	
	public ECRCUtils() {
		 logger.log(Level.INFO, "Initializing VCRC Utils.");
		 
	}; 

	/**
	 * Boolean value used to determine if test pages may be accessed. 
	 * Set using Contect Param in WEB.XML
	 * 
	 * @param request
	 * @return
	 */
	public boolean getTestConfig()  {
		boolean retVal = false;
		
		// return value if present. 
		String value = ecrc_props.getProperty("app.test.pages");
		if (null != value) {
			retVal = Boolean.parseBoolean(value); 
		} 
		logger.log(Level.INFO, "getTestConfig says " + retVal);
		return  retVal;
	}

}
