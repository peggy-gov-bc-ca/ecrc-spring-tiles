/**
 * @(#)BankResponseController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.ag.ecrc.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bc.gov.ag.ecrc.exceptions.RestServiceException;
import ca.bc.gov.ag.ecrc.exceptions.ECRCException;
import ca.bc.gov.ag.ecrc.services.BCEPServices;
import ca.bc.gov.ag.ecrc.services.FigaroServices;
import ca.bc.gov.ag.ecrc.utils.Consts;
import ca.bc.gov.ag.ecrc.utils.PaymentInformation;
import ca.bc.gov.ag.ecrc.utils.SessionVars;
import ca.bc.gov.ag.ecrc.utils.VCRCLogger;
import ca.bc.gov.fin.bcep.BCEPException;
import ca.bc.gov.fin.bcep.BankResponse;

/**
 * Handles the response from BCEP. 
 * 
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106021 $, ($LastChangedDate: 2014-03-31 15:08:26 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
@RequestMapping("/secure/bankResponse.htm")
public class BankResponseController {
	
	@Autowired 
	private SessionVars sessionVars;
	
	@Autowired
	private BCEPServices bcep;
	
	@Autowired
	private FigaroServices fs;
	
	@Autowired
	private VCRCLogger vcrcLogger;
	
	@RequestMapping(method = RequestMethod.GET)
	public String renderPage(HttpServletRequest request,	Map<String, Object> model) throws ECRCException, RestServiceException {
		
		request.setAttribute("pageTitle", "bcep test page bank response");
		
		// if we are logging while testing BCEP, the logger id needs to be set. 
		if( null == vcrcLogger.getId()) {
			vcrcLogger.initialize(request);
		}
		
		vcrcLogger.log(this, Level.INFO, sessionVars.getSessionId() + " loading response from bcep transaction.");
		
		PaymentInformation pi = sessionVars.getPaymentInformation();
		
        // Wrap the bank response in the event something goes wrong with the call. 
		BankResponse spbr = null;
        try {
        	bcep.setSessionId(sessionVars.getSessionId());
			spbr = bcep.doResponse(pi.getTransactionId());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ECRCException(e.getMessage()); 
		} catch (BCEPException e) {
			e.printStackTrace();
			throw new ECRCException(e.getMessage()); 
		}
        
        // If a specific error is being returned from BCEP, 
        // send to user to the generic error page.  
        if (spbr.isError()) {
        	vcrcLogger.log(this, Level.ERROR, "ERROR: BCEP Response message " + spbr.getResponseMessage());
            pi.setPaymentStatus(Consts.STATUS_DECLINED);
            throw new ECRCException("Error received from BCEP Banking Response. Code:" + spbr.getResponseCode() + " Message:" + spbr.getResponseMessage());
        }

        vcrcLogger.log(this, Level.INFO, sessionVars.getSessionId() + " auth number: " +spbr.getAuthorizationNumber());
        vcrcLogger.log(this, Level.INFO, sessionVars.getSessionId() + " resp msg: " + spbr.getBankResponseMessage());
        vcrcLogger.log(this, Level.INFO, sessionVars.getSessionId() + " srv msg: " + spbr.getServiceResponseMessage());
        vcrcLogger.log(this, Level.INFO, sessionVars.getSessionId() + " pay dt: " + spbr.getPaymentDate());
        
        // we are approved or declined ... copy the information
        // to the payment bean
        pi.setAuthorizationNumber(spbr.getAuthorizationNumber());
        pi.setPaymentDate(spbr.getPaymentDate());
        pi.setBankISOResponseCode(spbr.getISOResponseCode());
        pi.setBankResponseCode(spbr.getBankResponseCode());
        pi.setBankResponseMessage(spbr.getBankResponseMessage());
        pi.setCardNumber(spbr.getCardNumber());
        pi.setCardType(spbr.getCardType());
        pi.setSequenceNumber(spbr.getSequenceNumber());
        pi.setTerminalNumber(spbr.getTerminalId());
        pi.setCardHolderName(spbr.getEchoData()); 
        
        pi.setPaymentStatus((spbr.isDeclined()) ? Consts.STATUS_DECLINED
                : Consts.STATUS_APPROVED);
       
        // Post BCEP communication w/FIGARO.  
        if (!spbr.isDeclined()) { // BCEP success
        	
        	//If we are performing a Bank Test, do not report back to Figaro. 
            if ( !sessionVars.isBankTest() ) {
        	
	        	fs.updateServiceFinancialTxn(
	        			sessionVars.getAccessForm().getAccessCode(), //orgTicketNumber,
	        			Integer.toString(sessionVars.getPartyId()), //appl_Party_Id,
	        			Integer.toString(sessionVars.getServiceId()), //service_Id,
	        			pi.getAuthorizationNumber(), //cc_auth. Not sure what this should be yet. 
	        			pi.getPaymentDate(), //payment date yyyy/mm/dd 
	        			pi.getPayorType(), // payor_Type_Cd,   should always be 'A'pplicant 
	        			pi.getPaymentStatus(), //payment_Status_Cd,  should always be 'A'ccepted
	        			pi.getSessionId(), //session_Id,
	        			pi.getInvoiceNumber(), //invoice_Id,
	        			Integer.toString(pi.getTransactionId()), //transaction_Id,
	        			Double.toString(pi.getChargeAmount()) //transaction_Amount
	        			); 
            }
	        	
	        return "redirectCrcPayConfirmation";
	        	        	
        } else {  // BCEP failure
        	
        	//If we are performing a Bank Test, do not report back to Figaro. 
            if ( !sessionVars.isBankTest() ) {
        	
	        	fs.logPaymentFailure(
	        			sessionVars.getAccessForm().getAccessCode(), //orgTicketNumber,
	        			Integer.toString(sessionVars.getServiceId()), //service_Id,
	        			Integer.toString(sessionVars.getPartyId()), //appl_Party_Id,
	        			pi.getSessionId(), //session_Id,
	        			pi.getInvoiceNumber(), //invoice_Id,
	        			Double.toString(pi.getChargeAmount()), //service_Fee_Amount,
	        			pi.getBankResponseMessage() //BCEP_Error_Msg
	        			);
            }

        	// set charge amount so displays properly under 'Paid' of failure page.   
        	pi.setChargeAmount(0);
        	
        	return "redirectCrcPaymentIncomplete";
        }
        
	}

}
