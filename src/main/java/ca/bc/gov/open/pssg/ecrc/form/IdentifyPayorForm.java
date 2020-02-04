/**
 * @(#)IdentifyPayorForm.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class IdentifyPayorForm implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4765913037318120939L;
	
	@NotNull(message="{payor.required}")
	private String payor;

	public String getPayor() {
		return payor;
	}

	public void setPayor(String payor) {
		this.payor = payor;
	}
	
}
