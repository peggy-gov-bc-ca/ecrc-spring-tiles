/**
 * @(#)ShareRecordCheckController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.ag.ecrc.controllers;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.apache.commons.validator.routines.IntegerValidator;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bc.gov.ag.ecrc.exceptions.RestServiceException;
import ca.bc.gov.ag.ecrc.exceptions.ECRCException;
import ca.bc.gov.ag.ecrc.form.RecordCheckForm;
import ca.bc.gov.ag.ecrc.rest.getProvinceList.GetProvinceList.Provinces.Province;
import ca.bc.gov.ag.ecrc.services.FigaroServices;
import ca.bc.gov.ag.ecrc.utils.Consts;
import ca.bc.gov.ag.ecrc.utils.SessionVars;
import ca.bc.gov.ag.ecrc.utils.VCRCLogger;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class ShareRecordCheckController {

	@Autowired
	private SessionVars sessionVars;
	
	@Autowired
	private Properties val_props;
	
	@Autowired
	private VCRCLogger vcrcLogger;
	
	@Autowired
	private FigaroServices fs; 
	
	// Display the form on the get request
	@RequestMapping(value="/secure/shareRecordCheckForm.htm", method = RequestMethod.GET)
	public String showForm(HttpServletRequest request, Map<String, Object> model) throws RestServiceException, ECRCException {
		
		List<Province> provinces = fs.getProvincesList();
		model.put("provinces", provinces);
		
		if (sessionVars.getRecordCheckForm() == null) {
			RecordCheckForm recordCheckForm = new RecordCheckForm();
			// set defaults
			recordCheckForm.setProvince(Consts.PROVINCE_BC);
			recordCheckForm.setPrevProvince1(Consts.PROVINCE_BC);
			recordCheckForm.setPrevProvince2(Consts.PROVINCE_BC);
			
			// PT 22762 set default scope level (OffenseCategory) value from service. 
			if (StringUtils.isEmpty(recordCheckForm.getOffenseCategory())) {
				recordCheckForm.setOffenseCategory(sessionVars.getAuthenticateUser().getAccessCodeResponse().getDefaultCrcScopeLevelCd());
			}
			
			// PT 22780 set default schedule type from service.
			if (StringUtils.isEmpty(recordCheckForm.getScheduleType())) {
				recordCheckForm.setScheduleType(sessionVars.getAuthenticateUser().getAccessCodeResponse().getDefaultScheduleTypeCd());
			}
			
			sessionVars.setRecordCheckForm(recordCheckForm);
		
		} else {
			// check if previous addresses are set
			String prevMailingAddress1 = sessionVars.getRecordCheckForm().getPrevMailingAddress1();
			String prevMailingAddress2 = sessionVars.getRecordCheckForm().getPrevMailingAddress2();
			
			if (prevMailingAddress1 == null || prevMailingAddress1.isEmpty()) {
				// set default country and province values
				sessionVars.getRecordCheckForm().setPrevProvince1(Consts.PROVINCE_BC);
				sessionVars.getRecordCheckForm().setPrevCountry1(Consts.COUNTRY_CANADA);
			}
			
			if (prevMailingAddress2 == null || prevMailingAddress2.isEmpty()) {
				// set default country and province values
				sessionVars.getRecordCheckForm().setPrevProvince2(Consts.PROVINCE_BC);
				sessionVars.getRecordCheckForm().setPrevCountry2(Consts.COUNTRY_CANADA);
			}
		}
		
		model.put("recordCheckForm", sessionVars.getRecordCheckForm());
		model.put("currentPage", "shareRecordCheckForm");
		
		String titlePrefix = sessionVars.getEcrc_props().getProperty("title.share");
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.share.data.entry");
		sessionVars.addBreadcrumbNode(pageTitle, 3);
		request.setAttribute("pageTitle", titlePrefix + " - " + pageTitle);
		
		request.setAttribute("si_n", sessionVars.getEcrc_props().getProperty("wt.si_n.share"));
		request.setAttribute("si_p", sessionVars.getEcrc_props().getProperty("wt.si_p.share.data.entry"));
		
		return "shareRecordCheckForm";
	}

	// Process the form.
	@RequestMapping(value="/secure/shareRecordCheckForm.htm", method = RequestMethod.POST)
	public String processForm(@Validated({Default.class, RecordCheckForm.ShareChecks.class}) RecordCheckForm recordCheckForm, BindingResult result, Map<String, Object> model) throws Exception {
		
		//if time at current address is < 24 months, we require a previous address for Equifax.
		if ( !recordCheckForm.getMonths().isEmpty() && IntegerValidator.getInstance().isValid(recordCheckForm.getMonths()) 
				&& Integer.parseInt(recordCheckForm.getMonths()) < 24 && !recordCheckForm.isPrevAddress1() ) {
			result.rejectValue("monthsAtPrevious", null, val_props.getProperty("previous.address.required"));
		}
		
		if (result.hasErrors()) {
			
			List<Province> provinces = fs.getProvincesList();
			model.put("provinces", provinces);
			
			model.put("currentPage", "shareRecordCheckForm");
			
			return "shareRecordCheckForm";
			
		}

		// check if previous addresses are set
		String prevMailingAddress1 = recordCheckForm.getPrevMailingAddress1();
		String prevMailingAddress2 = recordCheckForm.getPrevMailingAddress2();
		
		if (prevMailingAddress1 == null || prevMailingAddress1.isEmpty()) {
			// clear country and province values
			recordCheckForm.setPrevProvince1("");
			recordCheckForm.setPrevCountry1("");
		}
		
		if (prevMailingAddress2 == null || prevMailingAddress2.isEmpty()) {
			// clear country and province values
			recordCheckForm.setPrevProvince2("");
			recordCheckForm.setPrevCountry2("");
		}
		
		// clear licence province if licence number not entered
		String licenceNo = recordCheckForm.getLicenceNo();
		if (licenceNo == null || licenceNo.isEmpty()) {
			recordCheckForm.setLicenceProvince("");
		}
		
		//Log the user name for debug purposed. 
		vcrcLogger.log(this, Level.INFO, "Applicant Name : " + recordCheckForm.getLastName() + ", " + recordCheckForm.getFirstName());

		sessionVars.setRecordCheckForm(recordCheckForm);
		
		return "redirectShareReviewDetails";
	}

	@RequestMapping(value="/secure/shareRecordCheckFormCancel.htm", method = RequestMethod.POST)
	public String processform(HttpServletRequest request) throws Exception {
		
		return "redirectHome";
	}
	
}
