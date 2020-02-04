/**
 * @(#)ShareConsentFormController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.ag.ecrc.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bc.gov.ag.ecrc.form.ConsentForm;
import ca.bc.gov.ag.ecrc.utils.SessionVars;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class ShareConsentFormController {

	@Autowired
	private SessionVars sessionVars;
	
	@RequestMapping(value="/secure/shareConsentForm.htm", method = RequestMethod.GET)
	public String showConsentDetails(HttpServletRequest request, Map<String, Object> model) {
		
		ConsentForm form = new ConsentForm();
		model.put("consentForm", form);
		
		model.put("currentPage", "shareConsentForm");
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.share");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.share.consent");
		sessionVars.addBreadcrumbNode(pageTitle, 2);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		request.setAttribute("si_n", sessionVars.getEcrc_props().getProperty("wt.si_n.share"));
		request.setAttribute("si_p", sessionVars.getEcrc_props().getProperty("wt.si_p.share.consent"));
		
		return "shareConsentForm";
	}
	
	// Process the form.
	@RequestMapping(value="/secure/shareConsentForm.htm", method = RequestMethod.POST)
	public String processForm(@Validated({Default.class}) ConsentForm consentForm, BindingResult result, Map<String, Object> model) throws Exception {
		if (result.hasErrors()) {
			
			model.put("currentPage", "shareConsentForm");
			
			return "shareConsentForm";
			
		}

		sessionVars.setConsentForm(consentForm);
		
		return "redirectShareRecordCheckForm";
	}
	
	@RequestMapping(value="/secure/shareConsentFormCancel.htm", method = RequestMethod.POST)
	public String processform(HttpServletRequest request) throws Exception {
			
		return "redirectHome";
	}
	
}

