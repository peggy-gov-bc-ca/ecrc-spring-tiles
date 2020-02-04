/**
 * @(#)CrcOrgDetailsController.java
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

import ca.bc.gov.ag.ecrc.exceptions.RestServiceException;
import ca.bc.gov.ag.ecrc.exceptions.ECRCException;
import ca.bc.gov.ag.ecrc.services.FigaroServices;
import ca.bc.gov.ag.ecrc.utils.SessionVars;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class CrcOrgDetailsController {
	
	@Autowired
	private FigaroServices fs; 
	
	@Autowired
	private SessionVars sessionVars;
	
	// Display the form on the get request
	@RequestMapping(value="/secure/crcOrgDetails.htm", method = RequestMethod.GET)
	public String showOrganizationDetails(HttpServletRequest request, Map<String, Object> model) throws RestServiceException, ECRCException {
		
		// Invoke Figaro Services to get session ID here.
		sessionVars.setSessionId(fs.getNextSessionId(sessionVars.getAccessForm().getAccessCode().toUpperCase()));
		
		model.put("currentPage", "crcOrgDetails");
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.crc");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.org.details");
		sessionVars.addBreadcrumbNode(pageTitle, 1);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		request.setAttribute("si_n", sessionVars.getEcrc_props().getProperty("wt.si_n.crc"));
		request.setAttribute("si_p", sessionVars.getEcrc_props().getProperty("wt.si_p.crc.org.details"));
		
		return "crcOrgDetails";
	}
	
	// Process the form.
	@RequestMapping(value="/secure/crcOrgDetails.htm", method = RequestMethod.POST)
	public String processForm(HttpServletRequest request) throws Exception {

		return "redirectCrcConsentForm";
	}
	
	@RequestMapping(value="/secure/crcOrgDetailsCancel.htm", method = RequestMethod.POST)
	public String processform(HttpServletRequest request) throws Exception {
		
		return "redirectHome";
	}
	
}
