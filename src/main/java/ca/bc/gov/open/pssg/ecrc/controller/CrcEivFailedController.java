/**
 * @(#)CrcEivFailedController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.controller;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bc.gov.ag.ecrc.exceptions.RestServiceException;
import ca.bc.gov.ag.ecrc.exceptions.ECRCException;
import ca.bc.gov.ag.ecrc.services.FigaroServices;
import ca.bc.gov.ag.ecrc.utils.DateUtils;
import ca.bc.gov.ag.ecrc.utils.SessionVarHelpers;
import ca.bc.gov.ag.ecrc.utils.SessionVars;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class CrcEivFailedController {

	@Autowired
	private SessionVars sessionVars;
	
	@Autowired
	private SessionVarHelpers sessionVarHelpers;
	
	@Autowired
	private FigaroServices fs;
	
	@Autowired
	private DateUtils du;
	
	@Autowired
	private Properties ecrc_props;
	
	// Display the form on the get request
	@RequestMapping(value="/secure/crcEivFailed.htm", method = RequestMethod.GET)
	public String showEivFailedDetails(HttpServletRequest request, Map<String, Object> model) throws ECRCException, RestServiceException {
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.crc");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.eiv.failed");
		sessionVars.addBreadcrumbNode(pageTitle, 8);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		request.setAttribute("scopeLevelDesc", sessionVarHelpers.getScopeLevelDesc());
		request.setAttribute("scheduleTypeDesc", sessionVarHelpers.getScheduleTypeDesc());
		
		request.setAttribute("scenario_result", sessionVars.getEcrc_props().getProperty("dcsext.scenario_result.crc.eiv.failed"));
		
		return "crcEivFailed";
	}
	
}
