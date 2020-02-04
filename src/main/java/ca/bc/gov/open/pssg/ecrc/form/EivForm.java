/**
 * @(#)EivForm.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * EIV form questions
 * 
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class EivForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -631237370269070920L;

	@NotEmpty (message = "{eivform.selection.required}")
	private String questionId1;
	
	@NotEmpty (message = "{eivform.selection.required}")
	private String questionId2;
	
	@NotEmpty (message = "{eivform.selection.required}")
	private String questionId3;
	
	@NotEmpty (message = "{eivform.selection.required}")
	private String questionId4;
	
	public String getQuestionId1() {
		return questionId1;
	}
	public void setQuestionId1(String questionId1) {
		this.questionId1 = questionId1;
	}
	public String getQuestionId2() {
		return questionId2;
	}
	public void setQuestionId2(String questionId2) {
		this.questionId2 = questionId2;
	}
	public String getQuestionId3() {
		return questionId3;
	}
	public void setQuestionId3(String questionId3) {
		this.questionId3 = questionId3;
	}
	public String getQuestionId4() {
		return questionId4;
	}
	public void setQuestionId4(String questionId4) {
		this.questionId4 = questionId4;
	}

}
