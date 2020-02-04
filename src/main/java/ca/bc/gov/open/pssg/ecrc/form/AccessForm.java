/**
 * @(#)AccessForm.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
 
/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class AccessForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7261398074362651211L;

	@NotEmpty (message = "{landingPage.accessCode.required}")
    private String accessCode;
	
	@NotEmpty (message="{captchaTextEntered.required}")
	private String captchaTextEntered;

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getCaptchaTextEntered() {
		return captchaTextEntered;
	}

	public void setCaptchaTextEntered(String captchaTextEntered) {
		this.captchaTextEntered = captchaTextEntered;
	} 
     
}