/**
 * @(#)EivQuestion.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.form;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class EivQuestion {

	private String label;
	
	private String answer;

	public EivQuestion() {
		super();
	}
	
	public EivQuestion(String label) {
		this();
		this.label = label;
		this.answer = "";
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
