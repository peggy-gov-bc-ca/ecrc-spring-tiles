/**
 * @(#)ShareNoCrcFoundController.java
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

import ca.bc.gov.ag.ecrc.exceptions.ECRCException;
import ca.bc.gov.ag.ecrc.utils.SessionVarHelpers;
import ca.bc.gov.ag.ecrc.utils.SessionVars;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class ShareNoCrcFoundController {

	@Autowired
	private SessionVars sessionVars;
	
	@Autowired
	private SessionVarHelpers sessionVarHelpers;
	
	// Display the form on the get request
		@RequestMapping(value="/secure/shareNoCrcFound.htm", method = RequestMethod.GET)
		public String showNoCrcFoundDetails(HttpServletRequest request, Map<String, Object> model) throws ECRCException {
			
			String titlePrefix = sessionVars.getEcrc_props().getProperty("title.share");
			String pageTitle = sessionVars.getEcrc_props().getProperty("title.share.no.crc");
			sessionVars.addBreadcrumbNode(pageTitle, 8);
			request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
			
			request.setAttribute("scopeLevelDesc", sessionVarHelpers.getScopeLevelDesc());
			request.setAttribute("scheduleTypeDesc", sessionVarHelpers.getScheduleTypeDesc());
			
			request.setAttribute("scenario_result", sessionVars.getEcrc_props().getProperty("dcsext.scenario_result.share.no.crc"));
			
			return "shareNoCrcFound";
		}
	
}
