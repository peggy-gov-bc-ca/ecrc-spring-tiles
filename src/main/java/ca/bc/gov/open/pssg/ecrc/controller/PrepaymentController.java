/**
 * @(#)PrepaymentController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.ag.ecrc.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bc.gov.ag.ecrc.services.FigaroServices;
import ca.bc.gov.ag.ecrc.utils.Consts;
import ca.bc.gov.ag.ecrc.utils.DateUtils;
import ca.bc.gov.ag.ecrc.utils.SessionVarHelpers;
import ca.bc.gov.ag.ecrc.utils.SessionVars;
import ca.bc.gov.ag.ecrc.utils.VCRCLogger;

/**
 * 
 * Pre-payment Controller. 
 * 
 * All request types (CRC and SHARING) pass through this controller so we may call: 
 *  
 * 	1.) CreateApplicant
 * 	2.) GetNextInvoice  
 *  3.) GetFee 
 * 	4.) CreateNewCRCService or CreateShareService 
 * 
 *  after EIV and Prior to BCEP (If required).  
 * 
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class PrepaymentController {

	@Autowired
	private SessionVars sessionVars;
	
	@Autowired
	private SessionVarHelpers sessionVarHelpers;
	
	@Autowired
	private FigaroServices fs; 
	
	@Autowired
	private DateUtils du; 
	
	//@Autowired 
	//private EquifaxTransactionManager etm;
	
	@Autowired
	private VCRCLogger vcrcLogger;
	
	// Display the form on the get request
	@RequestMapping(value="/secure/prepayment.htm", method = RequestMethod.GET)
	public String prepayment(HttpServletRequest request, Map<String, Object> model) throws Exception {
		
		vcrcLogger.log(this, Level.INFO, "Prepayment controller...");
		
		String callPurpose = "";
		if (sessionVars.getRequestType().equals(Consts.REQUEST_TYPE_SHARE)) {
			callPurpose = "SHARING";
		} else {
			callPurpose = sessionVars.getRequestType().toUpperCase();
		}
		
		// createApplicant
		sessionVars.setPartyId(fs.createApplicant(
				sessionVars.getAccessForm().getAccessCode(), // access code
				callPurpose, // Call Purpose (e.g. crc or sharing)
				sessionVars.getRecordCheckForm().getLastName(), // surname
				sessionVars.getRecordCheckForm().getFirstName(), // first name 
				sessionVars.getRecordCheckForm().getMiddleName(), // second name
				du.formatDateString(sessionVars.getRecordCheckForm().getBirthDate(),
						sessionVars.getEcrc_props().getProperty("screenDateFormat"),
						sessionVars.getEcrc_props().getProperty("figaroDateFormat")), // birth date
				sessionVars.getRecordCheckForm().getGender(),	// gender_txt
				sessionVars.getRecordCheckForm().getBirthPlace(), //birth_Place,
				sessionVars.getRecordCheckForm().getLastName1(), //alias1_Surname_Nm
				sessionVars.getRecordCheckForm().getFirstName1(), //alias1 first name
				sessionVars.getRecordCheckForm().getMiddleName1(), //alias1 second name
				sessionVars.getRecordCheckForm().getLastName2(), //alias2_Surname_Nm
				sessionVars.getRecordCheckForm().getFirstName2(), //alias2 first name
				sessionVars.getRecordCheckForm().getMiddleName2(), //alias2 second name
				sessionVars.getRecordCheckForm().getLastName3(), //alias3_Surname_Nm
				sessionVars.getRecordCheckForm().getFirstName3(), //alias3 first name
				sessionVars.getRecordCheckForm().getMiddleName3(), //alias3 second name
				sessionVars.getRecordCheckForm().getContactPhone(), //phone
				sessionVars.getRecordCheckForm().getMailingAddress(), // mail 1
				null, // mail 2
				sessionVars.getRecordCheckForm().getCity(), // city
				sessionVars.getRecordCheckForm().getProvince(), // province
				sessionVars.getRecordCheckForm().getCountry(), // country
				sessionVars.getRecordCheckForm().getPostalCode(), // postal
				sessionVars.getRecordCheckForm().getLicenceNo() // driver's lic
		)); 
				
		// CreateNewCRCService or CreateShareService call made based on request type 
		if (sessionVars.getRequestType().equals(Consts.REQUEST_TYPE_CHECK)) {
			
			// getNextInvoice (required by the following service calls - sessionId already present in sessionVars).
			// only if the user is an employee or a one-time ticket holder and is paying for the record check
			String orgApplRelationship = sessionVars.getOrgApplRelationship();
			String invoiceId = null;
			if (orgApplRelationship.equals(Consts.ORG_APPL_RELATION_EMPLOYEE)
					|| (orgApplRelationship.equals(Consts.ORG_APPL_RELATION_ONETIMETIK) 
							&& sessionVars.getIdentifyPayorForm().getPayor().equals(Consts.APPL_TO_PAY))) {
				int id = fs.getNextInvoiceId(sessionVars.getAccessForm().getAccessCode());
				sessionVars.setInvoiceId(id); 
				invoiceId = Integer.toString(id);
			}
			
			String scheduleTypeCd = sessionVarHelpers.getScheduleTypeCd(); 
			vcrcLogger.log(this, Level.INFO, "CRC Schecule Type : "+ scheduleTypeCd);
			
			String scopeLevelCd = sessionVarHelpers.getScopeLevelCd(); 
			vcrcLogger.log(this, Level.INFO, "CRC Scope Level : "+ scopeLevelCd);
				
			// Note: Org or Applicant to pay will be chose by the applicant if the ticket type is ONE TIME ONLY. Else this is driven by the ticket type.
			String org_appl_to_pay;
			if (sessionVars.getAuthenticateUser().getAccessCodeResponse().getOrgApplicantRelationship().equals(Consts.ORG_APPL_RELATION_ONETIMETIK)) {
				org_appl_to_pay = sessionVars.getIdentifyPayorForm().getPayor();
			} else {
				if (sessionVars.getAuthenticateUser().getAccessCodeResponse().getOrgApplicantRelationship().equals(Consts.ORG_APPL_RELATION_EMPLOYEE)) {
					org_appl_to_pay = Consts.APPL_TO_PAY;
				} else {
					// volunteer
					org_appl_to_pay = "";
				}
			}
			
			//Calculate the cost of this service. This will get transfered to 
			//PI object at CRCPaymentRequiredController. 
			Double fee = 0.00; 
			if (org_appl_to_pay.equals(Consts.APPL_TO_PAY)) {
				fee = fs.getServiceFeeAmount(
				sessionVars.getAccessForm().getAccessCode(), // OrgTicketNumber
				scheduleTypeCd,    //scheduleTypeCd,
				scopeLevelCd); //scopeLevelCd)
			}
			vcrcLogger.log(this, Level.INFO, "Service Fee calculated : "+ fee);
			sessionVars.setServiceFeeAmount(fee);  
			
			// Stuff governing body name for createNewCRCSErvice. 
			String gov_body_nm = "";
			if (sessionVarHelpers.getScheduleTypeCd().equals(Consts.SCHEDULE_D)) {
				gov_body_nm = sessionVars.getRecordCheckForm().getCareFacility(); 
			}; 
			
			//Create NEW service 
			sessionVars.setServiceId(fs.createNewCRCService(
					sessionVars.getAccessForm().getAccessCode(), //orgTicketNumber,
					scheduleTypeCd, //schedule_Type_Cd,
					sessionVars.getRecordCheckForm().getOffenseCategory(), //scope_Level_Cd,
					Integer.toString(sessionVars.getPartyId()),  //appl_Party_Id,
					org_appl_to_pay, //org_Appl_To_Pay,
					sessionVars.getRecordCheckForm().getApplicantPosition(), //applicant_Posn,
					sessionVars.getRecordCheckForm().getCareFacility(), //child_Care_Fac_Nm, 
					gov_body_nm, // governing_Body_Nm,
					Integer.toString(sessionVars.getSessionId()), //sessionId
					invoiceId, //invoice_Id,
					"Y", //auth_Release_EIV_Vendor_YN: this comes from the consent information screen. see func design, pp 22
					"Y", //auth_Conduct_CRC_Check_YN: this comes from the consent information screen. see func design pp 22
					"Y", //auth_Release_To_Org_YN: this comes from selecting next on Org Details screen.
					"Y",  //appl_Identity_Verified_EIV_YN: this happens if eIV passed. Assume Y if we get here. 
					sessionVars.getEivReasonCodeDescriptions().replace(",", "||") 
					));
		} else {
			
			//Create Sharing service 
			sessionVars.setServiceId(fs.createSharingService(
					sessionVars.getAccessForm().getAccessCode(), //orgTicketNumber
					Integer.toString(sessionVars.getPartyId()), //appl_Party_Id
					sessionVars.getRecordCheckForm().getOffenseCategory(), //scope_Level_Cd
					sessionVars.getRecordCheckForm().getApplicantPosition(), //applicant_Posn
					"Y", //auth_Release_EIV_Vendor_YN: this comes from the consent information screen. see func design, pp 22
					"Y", //auth_Release_To_Org_YN: this comes from selecting next on Org Details screen. 
					"Y", //appl_Identity_Verified_EIV_YN: this happens if eIV passed. Assume Y if we get here.
					sessionVars.getRecordCheckForm().getServiceNumber(), //previous_Service_Id
					sessionVars.getEivReasonCodeDescriptions().replace(",", "||")  
					));
			
		}

		
		// CRC ROUTING
		if (sessionVars.getRequestType().equals(Consts.REQUEST_TYPE_CHECK)) {
			
			if (sessionVars.getOrgApplRelationship().equals(Consts.ORG_APPL_RELATION_ONETIMETIK)) {
				
				// redirection to payment required if applicant has chosen to pay for this service, 
				// else payment is by org and we go to the final confirmation page
				if (sessionVars.getIdentifyPayorForm().getPayor().equals(Consts.APPL_TO_PAY)) {
					vcrcLogger.log(this, Level.INFO, "Applicant to pay so redirecting to payment required page..");
					return "redirectCrcPaymentRequired";
				} else {
					vcrcLogger.log(this, Level.INFO, "Organization to pay so redirecting to onetime completion page..");
					return "redirectCrcNoPayConfirmation";
				}
				
			} else if (sessionVars.getOrgApplRelationship().equals(Consts.ORG_APPL_RELATION_EMPLOYEE)){
				vcrcLogger.log(this, Level.INFO, "Redirecting to CRC Payment Required controller...");
				return "redirectCrcPaymentRequired";
			} else {
				// volunteer
				vcrcLogger.log(this, Level.INFO, "Redirecting to Volunteer confirmation controller...");
				return "redirectCrcNoPayConfirmation";
			}
		
		// SHARING ROUTING
		} else {
			
			return "redirectShareConfirmation";
			
		}
		
	}
	
}
