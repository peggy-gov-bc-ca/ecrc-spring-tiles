/**
 * @(#)ShareReviewDetailsController.java
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
import org.springframework.web.bind.annotation.RequestParam;

import ca.bc.gov.ag.ecrc.exceptions.ECRCException;
import ca.bc.gov.ag.ecrc.services.FigaroServices;
import ca.bc.gov.ag.ecrc.utils.Consts;
import ca.bc.gov.ag.ecrc.utils.DateUtils;
import ca.bc.gov.ag.ecrc.utils.SessionVarHelpers;
import ca.bc.gov.ag.ecrc.utils.SessionVars;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class ShareReviewDetailsController {
	
	@Autowired
	private SessionVars sessionVars;
	
	@Autowired
	private SessionVarHelpers sessionVarHelpers;
	
	@Autowired
	private FigaroServices fs;
	
	@Autowired
	private DateUtils du;
	
	@RequestMapping(value="/secure/shareReviewDetails.htm", method = RequestMethod.GET)
	public String showApplicantDetails(HttpServletRequest request, Map<String, Object> model) throws ECRCException {
		
		model.put("currentPage", "shareReviewDetails");
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.share");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.share.review.details");
		sessionVars.addBreadcrumbNode(pageTitle, 4);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		request.setAttribute("scopeLevelDesc", sessionVarHelpers.getScopeLevelDesc());
		request.setAttribute("scheduleTypeDesc", sessionVarHelpers.getScheduleTypeDesc());
		
		request.setAttribute("si_n", sessionVars.getEcrc_props().getProperty("wt.si_n.share"));
		request.setAttribute("si_p", sessionVars.getEcrc_props().getProperty("wt.si_p.share.review.details"));
		
		return "shareReviewDetails";
	}
	
	// Process the form.
	@RequestMapping(value="/secure/shareReviewDetails.htm", method = RequestMethod.POST)
	public String processForm(HttpServletRequest request, @RequestParam String action) throws Exception {

		if (action.equals("Back")) {
			return "redirectShareRecordCheckForm";
		} else {
			
			// make sure a previous service exists for sharing
			Consts.PrevCRCResponseType response = fs.checkApplicantForPrevCRC(
					sessionVars.getAccessForm().getAccessCode(), //orgTicketNumber
					sessionVars.getRecordCheckForm().getLastName(), //legal_Surname_Nm
					sessionVars.getRecordCheckForm().getFirstName(), //legal_First_Nm
					du.formatDateString(sessionVars.getRecordCheckForm().getBirthDate(), 
							sessionVars.getEcrc_props().getProperty("screenDateFormat"),
							sessionVars.getEcrc_props().getProperty("figaroDateFormat")), //birth_Dt
					sessionVars.getRecordCheckForm().getGender(), //gender_Txt
					sessionVars.getRecordCheckForm().getPostalCode(), //postal_Code_Txt
					sessionVars.getRecordCheckForm().getLicenceNo(), //drivers_Lic_No
					sessionVars.getRecordCheckForm().getOffenseCategory(), //scope_Level_Cd
					sessionVars.getRecordCheckForm().getServiceNumber() //previous_Service_Id
					);
			
			if (Consts.PrevCRCResponseType.TRUE.equals(response)) { // true case
				return "redirectShareEivForm";
			} else { // false and review cases (see 5.50.2 "Next" section near end) 
				return "redirectShareNoCrcFound";
			}
			
		}
		
	}
	
	@RequestMapping(value="/secure/shareReviewDetailsCancel.htm", method = RequestMethod.POST)
	public String processform(HttpServletRequest request) throws Exception {
		
		return "redirectHome";
	}
	
}
