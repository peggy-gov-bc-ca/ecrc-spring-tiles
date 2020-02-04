/**
 * @(#)ShareEivController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.ag.ecrc.controllers;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import ca.bc.gov.ag.ecrc.equifax.EquifaxConsts;
import ca.bc.gov.ag.ecrc.equifax.EquifaxUtils;
import ca.bc.gov.ag.ecrc.exceptions.ECRCException;
import ca.bc.gov.ag.ecrc.exceptions.EquifaxException;
import ca.bc.gov.ag.ecrc.exceptions.RestServiceException;
import ca.bc.gov.ag.ecrc.rest.initialRequest.InitialRequest;
import ca.bc.gov.ag.ecrc.rest.initialResponse.InitialResponse;
import ca.bc.gov.ag.ecrc.rest.resultRequest.ResultRequest;
import ca.bc.gov.ag.ecrc.rest.resultResponse.ResultResponse;
import ca.bc.gov.ag.ecrc.services.EquifaxServices;
import ca.bc.gov.ag.ecrc.services.FigaroServices;
import ca.bc.gov.ag.ecrc.utils.DateUtils;
import ca.bc.gov.ag.ecrc.utils.SessionVars;
import ca.bc.gov.ag.ecrc.utils.VCRCLogger;


/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106059 $, ($LastChangedDate: 2014-04-02 09:48:51 -0700 (Wed, 02 Apr 2014) $)
 */
@Controller
public class ShareEivController {

	@Autowired
	private SessionVars sessionVars;
	
	@Autowired
	private Properties ecrc_props;
	
	@Autowired 
	private EquifaxServices es; 
	
	@Autowired
	private FigaroServices fs;
	
	@Autowired 
	private EquifaxUtils eUtils; 
	
	@Autowired
	private DateUtils du;
	
	@Autowired
	private VCRCLogger vcrcLogger;
	
	
	@RequestMapping(value="/secure/shareEivForm.htm", method = RequestMethod.GET)
	public RedirectView showEivQuestions(HttpServletRequest request, Map<String, Object> model) throws ECRCException, EquifaxException, RestServiceException {
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.share");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.share.eiv");
		sessionVars.addBreadcrumbNode(pageTitle, 5);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		vcrcLogger.log(this, Level.INFO, "Starting initiate request to Equifax ...");
		
		// create initial Request to ES.
		InitialRequest ireq = eUtils.CreateInitialRequest(sessionVars.getRecordCheckForm(), sessionVars.getRequestType(), sessionVars.isEivTest());
				
		InitialResponse iresp = es.processInitialRequest(ireq);
				
		// redirect to the ES. 
		return new RedirectView(iresp.getRedirectUrl());

	}
	
	/**
	 * Handles return froprocess. 
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/secure/shareEivReturn.htm", method = RequestMethod.GET)
	public String eivReturn(@RequestParam("key") String key, HttpServletRequest request) throws Exception {
		
		vcrcLogger.log(this, Level.INFO, "Share EivController - Return from EIV Process");
		
		//create the result request for ES.
		ResultRequest rr = eUtils.CreateResultRequest(key);
		
		ResultResponse rresp = es.processResultRequest(rr);
		sessionVars.setEivReasonCodeDescriptions(rresp.getReasonCodeDescriptions());
		
		vcrcLogger.log(this, Level.INFO, "SHARE eIV controller:ProcessResponse - AssesmentComplete. Inidividual: " + sessionVars.getRecordCheckForm().getLastName() + ", " + sessionVars.getRecordCheckForm().getFirstName());
		vcrcLogger.log(this, Level.INFO, "SHARE eIV controller:ProcessResponse - AssesmentComplete. ReasonCodes: " + rresp.getReasonCodes());
		vcrcLogger.log(this, Level.INFO, "SHARE eIV controller:ProcessResponse - AssesmentComplete. Risk Strategy Decision from Equifax : " + rresp.getRiskStrategyDecision());
		vcrcLogger.log(this, Level.INFO, "SHARE eIV controller:ProcessResponse - AssesmentComplete. Score : " + rresp.getScore() + " Interactive Scrore: " + rresp.getInteractiveScore());
		
		// tiles name based on RiskStrategyDecision. Y = PASS, N = FAIL   
		if ( rresp.getRiskStrategyDecision().equals(EquifaxConsts.ASSESSMENT_PASSED)) {										
			return "redirectPrepaymentController";
		} else {
			logEiVFailure(); 
			return "redirectShareEivFailed"; 
			
		} 
	}
	
	/**
	 * Handles user requested cancel from EIV process.
	 *  
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/secure/shareEivCancel.htm", method = RequestMethod.GET)
	public String eivCancel(HttpServletRequest request) throws Exception {
		
		vcrcLogger.log(this, Level.INFO, "User cancelled from EIV Process");
		
		return "redirectShareRecordCheckForm";
	}
	
	/**
	 * Send EIV failure messages to Figaro. 
	 * 
	 * @throws RestServiceException
	 * @throws ECRCException
	 */
	private void logEiVFailure() throws RestServiceException, ECRCException {

		fs.logEivFailure(
			sessionVars.getAccessForm().getAccessCode(), //orgTicketNumber
			Integer.toString(sessionVars.getSessionId()), //session_Id
				sessionVars.getRecordCheckForm().getLastName(), //legal_Surname_Nm
				sessionVars.getRecordCheckForm().getFirstName(), //legal_First_Nm
				sessionVars.getRecordCheckForm().getMiddleName(), //legal_Second_Nm
				// message date from screen format to Figaro format before sending birth date.  
				du.formatDateString(sessionVars.getRecordCheckForm().getBirthDate(), ecrc_props.getProperty("screenDateFormat"),  ecrc_props.getProperty("figaroDateFormat")), //birth_Dt
				sessionVars.getRecordCheckForm().getGender(), //gender_Txt
				sessionVars.getEivReasonCodeDescriptions().replace(",", "||") //EIV_Vendor_Error_Msg (added pipe char for FIGARO API). 
		);
	}
}
