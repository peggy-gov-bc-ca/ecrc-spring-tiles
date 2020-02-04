/**
 * @(#)CrcPaymentIncompleteController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bc.gov.ag.ecrc.utils.SessionVars;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class CrcPaymentIncompleteController {

	@Autowired
	private SessionVars sessionVars;
	
	// Display the form on the get request
	@RequestMapping(value="/secure/crcPaymentIncomplete.htm", method = RequestMethod.GET)
	public String showPaymentIncompleteDetails(HttpServletRequest request, Map<String, Object> model) {
		
		model.put("currentPage", "crcPaymentIncomplete");
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.crc");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.payment.incomplete");
		sessionVars.addBreadcrumbNode(pageTitle, 8);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		request.setAttribute("scenario_result", sessionVars.getEcrc_props().getProperty("dcsext.scenario_result.crc.payment.incomplete"));
		
		return "crcPaymentIncomplete";
	}
	
	// Process the form.
	@RequestMapping(value="/secure/crcPaymentIncomplete.htm", method = RequestMethod.POST)
	public String processForm(HttpServletRequest request) throws Exception {

		return "redirectCrcPaymentRequired";
	}
	
	@RequestMapping(value="/secure/crcPaymentIncompleteCancel.htm", method = RequestMethod.POST)
	public String processform(HttpServletRequest request) throws Exception {
		
		return "redirectHome";
	}
	
}
