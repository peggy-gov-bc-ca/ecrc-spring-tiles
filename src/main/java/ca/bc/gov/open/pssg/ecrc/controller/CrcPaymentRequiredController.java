/**
 * @(#)CrcPaymentRequiredController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import ca.bc.gov.ag.ecrc.exceptions.ECRCException;
import ca.bc.gov.ag.ecrc.services.BCEPServices;
import ca.bc.gov.ag.ecrc.services.FigaroServices;
import ca.bc.gov.ag.ecrc.utils.Consts;
import ca.bc.gov.ag.ecrc.utils.PaymentInformation;
import ca.bc.gov.ag.ecrc.utils.SessionVarHelpers;
import ca.bc.gov.ag.ecrc.utils.SessionVars;
import ca.bc.gov.ag.ecrc.utils.VCRCLogger;
import ca.bc.gov.fin.bcep.BCEPException;
import ca.bc.gov.fin.bcep.RedirectingResponse;

/**
 * 
 * Prepares for BCEP payment.
 * 
 *  
 * 
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2013-09-10 17:18:27 -0700
 *          (Tue, 10 Sep 2013) $)
 */
@Controller
public class CrcPaymentRequiredController {

	@Autowired
	private SessionVars sessionVars;
	
	@Autowired
	private SessionVarHelpers sessionVarHelpers;
	
	@Autowired
	private FigaroServices fs; 
	
	@Autowired
	private BCEPServices bcep;
	
	@Autowired
	private VCRCLogger vcrcLogger;
	
	// Process the BCEP request on post.
	@RequestMapping(value="/secure/crcPaymentRequired.htm", method = RequestMethod.GET)
	public RedirectView processForm(HttpSession session, 
			HttpServletRequest request, Map<String, Object> model) throws Exception {
		
		//Update the bread crumb trail so we can see that we've passed through the payment section when 
		//we arrive on the confirmation page. 
		//String titlePrefix = sessionVars.getEcrc_props().getProperty("title.crc");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.crc.payment.required");
		sessionVars.addBreadcrumbNode(pageTitle, 7);
		
		//Find the return path for BCEP. 
		String redirect = sessionVars.getEcrc_props().getProperty("bcep.return.host");
		
		//Fetch Schedule Level Cd
		String scheduleType = sessionVarHelpers.getScheduleTypeCd();
		vcrcLogger.log(this, Level.DEBUG, "Schedule type calculated : "+ scheduleType);
		
		//Fetch  ScopeLevel CD. 
		String scopeLevelCd = sessionVarHelpers.getScopeLevelCd();
		vcrcLogger.log(this, Level.DEBUG, "CRC Scope Level calculated : "+ scopeLevelCd);
		
		// Get payment Information from session and update payor type. 
		PaymentInformation pi = sessionVars.getPaymentInformation();
		if (null != sessionVars.getIdentifyPayorForm()) { 
			pi.setPayorType(sessionVars.getIdentifyPayorForm().getPayor()); // one time ticket case where this may be 'O' or 'A'.
		} else {
			pi.setPayorType(Consts.APPL_TO_PAY); // employee case
		}
		
		vcrcLogger.log(this, Level.DEBUG, "Appl or Org to pay : "+ pi.getPayorType());
		
		//Set PI Session Id.
		pi.setSessionId(Integer.toString(sessionVars.getSessionId()));
		
		//Set PI Invoice Id.
        pi.setInvoiceNumber(Integer.toString(sessionVars.getInvoiceId()));
        
        //Transfer a copy of the service fee to the PI. 
        pi.setChargeAmount(sessionVars.getServiceFeeAmount()); 
		
		//Wrap the bank response in the event something goes wrong with the call. 
		RedirectingResponse redirect_response;
        try {
        	bcep.setSessionId(sessionVars.getSessionId()); 
        	redirect_response = bcep.doCharge(pi.getInvoiceNumber(),
					Integer.toString(sessionVars.getServiceId()),
					Integer.toString(sessionVars.getAuthenticateUser().getAccessCodeResponse().getOrgPartyId()),   
					sessionVarHelpers.getApplicantDisplayname(),  //echoData
					redirect,
					pi.getChargeAmount());
        	vcrcLogger.log(this, Level.INFO, "BCEP Transaction Id : "+ redirect_response.getTransactionId());
        	pi.setTransactionId(new Integer(redirect_response.getTransactionId())); 
		} catch (IOException e) {
			e.printStackTrace();
			throw new ECRCException(e.getMessage()); 
		} catch (BCEPException e) {
			e.printStackTrace();
			throw new ECRCException(e.getMessage()); 
		}
        
        return new RedirectView(redirect_response.getRedirectURL());
		
	}

	@RequestMapping(value = "/secure/crcPaymentRequiredCancel.htm", method = RequestMethod.POST)
	public String processform(HttpServletRequest request) throws Exception {

		return "redirectHome";
	}

}
