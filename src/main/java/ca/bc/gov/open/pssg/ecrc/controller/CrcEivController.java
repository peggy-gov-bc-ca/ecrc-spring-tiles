/**
 * @(#)CrcEivController.java
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
import ca.bc.gov.ag.ecrc.utils.Consts;
import ca.bc.gov.ag.ecrc.utils.DateUtils;
import ca.bc.gov.ag.ecrc.utils.SessionVars;
import ca.bc.gov.ag.ecrc.utils.VCRCLogger;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106058 $, ($LastChangedDate: 2014-04-02 09:30:43 -0700 (Wed, 02 Apr 2014) $)
 */
@Controller
public class CrcEivController {
	
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
	
	
	@RequestMapping(value="/secure/crcEivForm.htm", method = RequestMethod.GET)
	public RedirectView commenceEIV(HttpServletRequest request, Map<String, Object> model) throws ECRCException, EquifaxException, RestServiceException {
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.crc");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.eiv");
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
	 * Handles return from EIV process. 
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/secure/crcEivReturn.htm", method = RequestMethod.GET)
	public String eivReturn(@RequestParam("key") String key, HttpServletRequest request) throws Exception {
		
		vcrcLogger.log(this, Level.INFO, "CRC EivController - Return from EIV Process");
		
		//create the result request for ES.
		ResultRequest rr = eUtils.CreateResultRequest(key);
		
		ResultResponse rresp = es.processResultRequest(rr);
		sessionVars.setEivReasonCodeDescriptions(rresp.getReasonCodeDescriptions());
		
		vcrcLogger.log(this, Level.INFO, "CRC eIV controller:ProcessResponse - AssesmentComplete. Inidividual: " + sessionVars.getRecordCheckForm().getLastName() + ", " + sessionVars.getRecordCheckForm().getFirstName());
		vcrcLogger.log(this, Level.INFO, "CRC eIV controller:ProcessResponse - AssesmentComplete. ReasonCodes: " + rresp.getReasonCodes());
		vcrcLogger.log(this, Level.INFO, "CRC eIV controller:ProcessResponse - AssesmentComplete. Risk Strategy Decision from Equifax : " + rresp.getRiskStrategyDecision());
		vcrcLogger.log(this, Level.INFO, "CRC eIV controller:ProcessResponse - AssesmentComplete. Score : " + rresp.getScore() + " Interactive Scrore: " + rresp.getInteractiveScore());
		
		// tiles name based on RiskStrategyDecision. Y = PASS, N = FAIL   
		if ( rresp.getRiskStrategyDecision().equals(EquifaxConsts.ASSESSMENT_PASSED)) {
										
			// If one time ticket, determine payor. 
			if (sessionVars.getOrgApplRelationship().equals(Consts.ORG_APPL_RELATION_ONETIMETIK)) {
				vcrcLogger.log(this, Level.INFO, "Redirecting to Identify Payor form...");
				return "redirectCrcIdentifyPayorForm";	
			} else {
				return "redirectPrepaymentController";
			}
			
		} 
		
		// had an eiv failure
		logEiVFailure(); 
		
		// send to failed screen. 
		return "redirectCrcEivFailed"; 
	
	}
	
	/**
	 * Handles user requested cancel from EIV process.
	 *  
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/secure/crcEivCancel.htm", method = RequestMethod.GET)
	public String eivCancel(HttpServletRequest request) throws Exception {
		
		vcrcLogger.log(this, Level.INFO, "User cancelled from EIV Process");
		
		return "redirectCrcRecordCheckForm";
	}
	
//	/**
//	 * Returns tiles name based on EquifaxTransactionManager output. 
//	 * 
//	 * @param request
//	 * @param model
//	 * @return
//	 * @throws ECRCException 
//	 * @throws EquifaxException 
//	 * @throws RestServiceException 
//	 */
//	private String processCRCResponse(HttpServletRequest request,
//			Map<String, Object> model) throws ECRCException, EquifaxException, RestServiceException {
//		
//		String tilesName = null;
//				
//		if ( sessionVars.getEivState().isLastCallStatus() ) {
//			
//			switch ( sessionVars.getEivState().getResponseType() ) {
//			
//				case FieldChecksFailed: // works. 
//						
//						throw new EquifaxException("Equifax Field Checks Failed : " + Arrays.asList( sessionVars.getEivState().getXpathErrorList()));
//						
//				case ApplicationVerification: 
//						vcrcLogger.log(this, Level.INFO, "CRC eIV form controller: ProcessResponse - ApplicationVerification. ReasonCodes: " + sessionVars.getEivState().getReasonCodeList());
//					
//						// User has 1 attempt to get it right, otherwise eIV failure occurs. 
//						if ( sessionVars.getEivState().isRetryPossible()) {
//							
//							SimpleDialogType sdt = new SimpleDialogType("Application Verification", "Verification Message(s) have been returned from Equifax. Fields highlighted in yellow may require corrections for your Electronic Identity Verification (EIV) to succeed. Please make updates as required and click 'Next'.", null);
//							model.put("simpleDialog", sdt);
//						
//							model.put("recordCheckForm", sessionVars.getRecordCheckForm());
//							
//							List<Province> provinces = fs.getProvincesList();
//							model.put("provinces", provinces);
//							
//							// Fields will be auto highlighted along with Application Verification messages in forwarding page.
//							vcrcLogger.log(this, Level.INFO, "Forwarding to CRC RecordCheck Form");
//							tilesName = "crcRecordCheckForm";
//							
//						} else {
//							
//							//AV Failure - Retries exhausted. Redirect to eIV failure page.
//							vcrcLogger.log(this, Level.INFO, "Number of Application Verification Retries are exhausted. Forwarding to CRC EIV failure page");
//							logEiVFailure();
//							tilesName = "redirectCrcEivFailed";
//						}
//						break; 
//						
//				case AssesmentComplete:
//						vcrcLogger.log(this, Level.INFO, "CRC eIV form controller:ProcessResponse - AssesmentComplete. ReasonCodes: " + sessionVars.getEivState().getReasonCodeList());
//						vcrcLogger.log(this, Level.INFO, "CRC eIV form controller:ProcessResponse - AssesmentComplete. Risk Strategy Decision from Equifax : " + sessionVars.getEivState().getriskStrategyDecision());
//						vcrcLogger.log(this, Level.INFO, "CRC eIV form controller:ProcessResponse - AssesmentComplete. Score : " + sessionVars.getEivState().getScore() + " Interactive Scrore: " + sessionVars.getEivState().getSimpleInteractiveQueryScore());
//						
//						// tiles name based on RiskStrategyDecision. Y = PASS, N = FAIL   
//						if ( sessionVars.getEivState().getriskStrategyDecision().equals(EquifaxConsts.ASSESSMENT_PASSED)) {
//														
//							// If one time ticket, determine payor. 
//							if (sessionVars.getOrgApplRelationship().equals(Consts.ORG_APPL_RELATION_ONETIMETIK)) {
//								vcrcLogger.log(this, Level.INFO, "Redirecting to Identify Payor form...");
//								tilesName = "redirectCrcIdentifyPayorForm";	
//							} else {
//								tilesName = "redirectPrepaymentController";
//							}
//							
//						} else {
//							logEiVFailure(); 
//							tilesName = "redirectCrcEivFailed"; 
//						}
//						break;
//						
//				case InteractiveQuery:
//						vcrcLogger.log(this, Level.INFO, "CRC eIV form controller: ProcessResponse - InteractiveQuery");
//						
//						model.put("eivForm", sessionVars.getEivForm());
//						tilesName = "crcEivForm";
//						break;
//						
//				case FraudCheckFailed:
//						vcrcLogger.log(this, Level.INFO, "CRC eIV form controller: ProcessResponse - FraudCheckFailed");
//						
//						logEiVFailure(); 
//						tilesName = "redirectCrcEivFailed";
//						break;
//						
//				case SystemProblem:
//						vcrcLogger.log(this, Level.INFO, "CRC eIV form controller: ProcessResponse - SystemProblem");
//						throw new EquifaxException( sessionVars.getEivState().getLastSystemProblemMessage());
//				
//				default:
//						throw new EquifaxException("Reached default switch statement. We shouldn't be here. Something went wrong..");
//
//			}
//						
//		} else { // if bad status, a system problem occurred. 
//			String message = "Equifax Process Initial Response - Equifax system problem indicated. Last system message " + sessionVars.getEivState().getLastSystemProblemMessage();
//			vcrcLogger.log(this, Level.INFO, message);
//			throw new EquifaxException(message);
//		}
//		
//		return tilesName;
//	}

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
