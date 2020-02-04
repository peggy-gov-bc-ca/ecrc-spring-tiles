/**
 * @(#)DateUtils.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import ca.bc.gov.open.pssg.ecrc.exceptions.ECRCException;


/**
 * Various Date Utils.  
 * 
 * @author $Author: smillar $
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class DateUtils {

	@Autowired
	private Properties ecrc_props;
	
	public DateUtils() {}; 

	public String formatDateString(String inputDate, String inputMask, String outputMask) throws ECRCException {
		
		SimpleDateFormat inputFormat = new SimpleDateFormat(inputMask);
		
		SimpleDateFormat outputFormat = new SimpleDateFormat(outputMask);
		
		String returnVal = null;
		try {
			returnVal = outputFormat.format(inputFormat.parse(inputDate));
		} catch (ParseException e) {
			throw new ECRCException(e.getMessage());
		}
		return returnVal;
	}

}
