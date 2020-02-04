/**
 * @(#)CrcReviewDetailsController.java
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
import org.springframework.web.bind.annotation.RequestParam;

import ca.bc.gov.ag.ecrc.exceptions.ECRCException;
import ca.bc.gov.ag.ecrc.utils.SessionVarHelpers;
import ca.bc.gov.ag.ecrc.utils.SessionVars;
import ca.bc.gov.ag.ecrc.utils.VCRCLogger;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class CrcReviewDetailsController {
	
	@Autowired
	private SessionVars sessionVars;
	
	@Autowired
	private SessionVarHelpers sessionVarHelpers;
	
	@Autowired
	private Properties ecrc_props;
	
	@Autowired
	private VCRCLogger vcrcLogger;
	
	@RequestMapping(value="/secure/crcReviewDetails.htm", method = RequestMethod.GET)
	public String showApplicantDetails(HttpServletRequest request, Map<String, Object> model) throws ECRCException {
		
		model.put("currentPage", "crcReviewDetails");
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.crc");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.review.details");
		sessionVars.addBreadcrumbNode(pageTitle, 4);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		request.setAttribute("scopeLevelDesc", sessionVarHelpers.getScopeLevelDesc());
		request.setAttribute("scheduleTypeDesc", sessionVarHelpers.getScheduleTypeDesc());
		
		request.setAttribute("si_n", sessionVars.getEcrc_props().getProperty("wt.si_n.crc"));
		request.setAttribute("si_p", sessionVars.getEcrc_props().getProperty("wt.si_p.crc.review.details"));
		
		return "crcReviewDetails";
	}
	
	// Process the form.
	@RequestMapping(value="/secure/crcReviewDetails.htm", method = RequestMethod.POST)
	public String processForm(HttpServletRequest request, @RequestParam String action) throws Exception {

		if (action.equals("Back")) {
			return "redirectCrcRecordCheckForm";
		} else {
			return "redirectCrcEivForm";
		}
		
	}
	
	@RequestMapping(value="/secure/crcReviewDetailsCancel.htm", method = RequestMethod.POST)
	public String processform(HttpServletRequest request) throws Exception {
		
		return "redirectHome";
	}
	
}
