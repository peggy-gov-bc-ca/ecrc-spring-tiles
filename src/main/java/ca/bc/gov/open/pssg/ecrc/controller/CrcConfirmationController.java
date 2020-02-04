/**
 * @(#)CrcConfirmationController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.ag.ecrc.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bc.gov.ag.ecrc.utils.Consts;
import ca.bc.gov.ag.ecrc.utils.SessionVars;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class CrcConfirmationController {

	@Autowired
	private SessionVars sessionVars;
	
	// Display the form on the get request
	@RequestMapping(value="/secure/crcPayConfirmation.htm", method = RequestMethod.GET)
	public String showEmpConfirmationDetails(HttpServletRequest request, Map<String, Object> model) {
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.crc");
		String pageTitle = "";
		if (sessionVars.getOrgApplRelationship().equals(Consts.ORG_APPL_RELATION_EMPLOYEE)) {
			pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.emp.confirmation");
		} else {
			// one-time ticket
			pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.confirmation");
		}
		sessionVars.addBreadcrumbNode(pageTitle, 8);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		sessionVars.setPAYConfirmationType();
		
		request.setAttribute("scenario_result", sessionVars.getEcrc_props().getProperty("dcsext.scenario_result.crc.pay.confirmation"));
		
		return "crcPayConfirmation";
	}
	
	// Display the form on the get request
	@RequestMapping(value="/secure/crcNoPayConfirmation.htm", method = RequestMethod.GET)
	public String showVolConfirmationDetails(HttpServletRequest request, Map<String, Object> model) {
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.crc");
		String pageTitle = "";
		if (sessionVars.getOrgApplRelationship().equals(Consts.ORG_APPL_RELATION_VOLUNTEER)) {
			pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.vol.confirmation");
		} else {
			// one-time ticket
			pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.confirmation");
		}
		sessionVars.addBreadcrumbNode(pageTitle, 8);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		sessionVars.setNOPAYConfirmationType();
		
		request.setAttribute("scenario_result", sessionVars.getEcrc_props().getProperty("dcsext.scenario_result.crc.nopay.confirmation"));
		
		return "crcNoPayConfirmation";
	}
	
}
