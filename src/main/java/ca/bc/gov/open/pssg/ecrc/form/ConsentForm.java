/**
 * @(#)ConsentForm.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.form;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class ConsentForm implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4027126424636202273L;

	@AssertTrue(groups = CrcChecks.class, message="{recordCheck.consent.required}")
	@NotNull(groups = CrcChecks.class, message="{recordCheck.consent.required}")
	private Boolean crcConsent;
	
	@AssertTrue(message="{personalInfo.consent.required}")
	@NotNull(message="{personalInfo.consent.required}")
	private Boolean personalInfoConsent;

	public Boolean getCrcConsent() {
		return crcConsent;
	}

	public void setCrcConsent(Boolean crcConsent) {
		this.crcConsent = crcConsent;
	}

	public Boolean getPersonalInfoConsent() {
		return personalInfoConsent;
	}

	public void setPersonalInfoConsent(Boolean personalInfoConsent) {
		this.personalInfoConsent = personalInfoConsent;
	}
	
	public interface CrcChecks {}
	
}
