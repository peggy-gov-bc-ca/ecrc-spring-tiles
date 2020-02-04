/**
 * @(#)LandingPageController.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.bc.gov.open.pssg.ecrc.exceptions.RestServiceException;
import ca.bc.gov.open.pssg.ecrc.exceptions.ECRCException;
import ca.bc.gov.open.pssg.ecrc.form.AccessForm;
import ca.bc.gov.open.pssg.ecrc.services.FigaroServices;
import ca.bc.gov.open.pssg.ecrc.utils.Consts;
import ca.bc.gov.open.pssg.ecrc.utils.SessionVars;
import ca.bc.gov.open.pssg.ecrc.utils.VCRCLogger;

import com.google.code.kaptcha;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
public class LandingPageController {
	
	@Autowired
	private FigaroServices fs; 

	@Autowired
	private Properties val_props;
	
	@Autowired
	private SessionVars sessionVars;
	
	//@Autowired 
	//private EquifaxTransactionManager etm; 
	
	@Autowired
	private Producer captchaProducer;
	
	@Autowired
	private VCRCLogger vcrcLogger;
	
	
	@RequestMapping(value="/captcha.htm", method = RequestMethod.GET)
	public String loadImage(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws RestServiceException, IOException
	{
		
		// Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
          
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        
        // store the text for validation
        sessionVars.setCaptchaText(capText);

        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try
        {
                out.flush();
        }
        finally
        {
                out.close();
        }
		
		return null;
	}
	
	@RequestMapping(value="/home.htm", method = RequestMethod.GET)
	public String doLanding(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws RestServiceException, IOException, ECRCException
	{
		
		// clear session variables 
		sessionVars.clearSessionVars();
		
		// initialize the logger with the session ID
		vcrcLogger.initialize(request);
		
		AccessForm accessForm = new AccessForm();
		model.put("accessForm", accessForm);
		
		String pageTitle = sessionVars.getEcrc_props().getProperty("title.landing.page");
		sessionVars.addBreadcrumbNode(pageTitle, 0);
		request.setAttribute("pageTitle", pageTitle);
		
		return "home";
	}
	
	// Process the form.
	@RequestMapping(value="/home.htm", method = RequestMethod.POST)
	public String processLandingForm(@Valid AccessForm accessForm,
			BindingResult result, @RequestParam String action, HttpServletRequest request) throws Exception {
		
		//Validation fail?
		if (result.hasErrors()) {
			accessForm.setCaptchaTextEntered(null);
			return "home";
		}
		
		// validate the captcha
		if (!accessForm.getCaptchaTextEntered().equalsIgnoreCase(sessionVars.getCaptchaText())) {
			result.rejectValue("captchaTextEntered", null, val_props.getProperty("captchaTextEntered.invalid"));
			accessForm.setCaptchaTextEntered(null);
			return "home";
		}
		
		// Invoke Figaro Services to validate accessCode here.
		// If valid, send on to either crc or share consent form
		// Available path is dependant access code type. For example: One time, volunteers and employee tickets may access 
		// CRC however only volunteer and employee tickets may access SHARE.  
		sessionVars.setAuthenticateUser(fs.doAuthenticateUser(accessForm.getAccessCode().toUpperCase()));
		
		if (sessionVars.isAllowAccess()) {
			
			sessionVars.setAccessForm(accessForm);
			
			if (action.equals("Request a New Criminal Record Check")) {	
				 
				// Allowed with ANY org appl relationship 
				sessionVars.setCRCRequestType(); 
				return "redirectCrcOrgDetails";
			} else {
				
				// ONLY allowed with org appl relationship value of 'EMPLOYEE' or 'VOLUNTEER'
				if ( sessionVars.getOrgApplRelationship().equals(Consts.ORG_APPL_RELATION_EMPLOYEE) ||
						sessionVars.getOrgApplRelationship().equals(Consts.ORG_APPL_RELATION_VOLUNTEER) ) {			
					sessionVars.setShareRequestType(); 
					return "redirectShareOrgDetails";
				} else {
					
					// add error message and return home. 
					result.rejectValue("accessCode", null, val_props.getProperty("landingPage.accessCode.NotPermittedForSharing"));
					accessForm.setCaptchaTextEntered(null);
					return "home";
				}
			}
			
		} else {
			
			result.rejectValue("accessCode", null, val_props.getProperty("landingPage.accessCode.invalid"));
			accessForm.setCaptchaTextEntered(null);
			return "home";
		}
	}
	
}
