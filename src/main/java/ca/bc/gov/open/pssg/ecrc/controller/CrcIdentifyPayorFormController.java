/**
 * @(#)CrcIdentifyPayorFormController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bc.gov.ag.ecrc.form.IdentifyPayorForm;
import ca.bc.gov.ag.ecrc.utils.SessionVars;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class CrcIdentifyPayorFormController {

	@Autowired
	private SessionVars sessionVars;
	
	@RequestMapping(value="/secure/crcIdentifyPayorForm.htm", method = RequestMethod.GET)
	public String showPayorTypeChoice(HttpServletRequest request, Map<String, Object> model) {
		
		IdentifyPayorForm form = new IdentifyPayorForm();
		model.put("identifyPayorForm", form);
		
		model.put("currentPage", "crcIdentifyPayorForm");
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.crc");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.identify.payor");
		sessionVars.addBreadcrumbNode(pageTitle, 6);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		return "crcIdentifyPayorForm";
	}
	
	// Process the form.
	@RequestMapping(value="/secure/crcIdentifyPayorForm.htm", method = RequestMethod.POST)
	public String processForm(@Valid IdentifyPayorForm identifyPayorForm, BindingResult result, Map<String, Object> model) throws Exception {
		
		if (result.hasErrors()) {
			
			model.put("currentPage", "crcIdentifyPayorForm");
			
			return "crcIdentifyPayorForm";
			
		}
		
		sessionVars.setIdentifyPayorForm(identifyPayorForm);
	
		return "redirectPrepaymentController";
		
	}
	
	@RequestMapping(value="/secure/crcIdentifyPayorFormCancel.htm", method = RequestMethod.POST)
	public String processform(HttpServletRequest request) throws Exception {
				
		return "redirectHome";
	}
	
}
