/**
 * @(#)RestServiceException.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.exceptions;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class RestServiceException extends Exception {

	private static final long serialVersionUID = -5577583580596777345L;

	public RestServiceException(String message) {
		super(message);
	}

}
