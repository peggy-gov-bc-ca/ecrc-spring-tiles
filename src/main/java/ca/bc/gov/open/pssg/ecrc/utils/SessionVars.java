/**
 * @(#)SessionVars.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Properties;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;

import ca.bc.gov.open.pssg.ecrc.breadcrumb.BreadcrumbNode;
import ca.bc.gov.open.pssg.ecrc.breadcrumb.BreadcrumbTree;
import ca.bc.gov.open.pssg.ecrc.exceptions.ECRCException;
import ca.bc.gov.open.pssg.ecrc.form.AccessForm;
import ca.bc.gov.open.pssg.ecrc.form.ConsentForm;
import ca.bc.gov.open.pssg.ecrc.form.EivForm;
import ca.bc.gov.open.pssg.ecrc.form.IdentifyPayorForm;
import ca.bc.gov.open.pssg.ecrc.form.RecordCheckForm;
import ca.bc.gov.open.pssg.ecrc.rest.doAuthenticateUser.DoAuthenticateUser;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2013-09-05 17:02:54 -0700
 *          (Thu, 05 Sep 2013) $)
 */
public class SessionVars implements Serializable {

	/**
	 * Required for load balanced system
	 */
	private static final long serialVersionUID = 7775431072792185312L;

	/** Logger. */
	private static final Logger logger = Logger.getLogger(SessionVars.class.toString());

	@Autowired
	private Properties ecrc_props;

	private BreadcrumbTree breadcrumbTree;

	private String captchaText;
	private String requestType;
	private int sessionId;
	private int partyId;
	private int invoiceId;
	private int serviceId;
	private double serviceFeeAmount;
	private String confirmationType;

	private EivForm eivForm;
	private AccessForm accessForm;
	private ConsentForm consentForm;
	private RecordCheckForm recordCheckForm;
	private IdentifyPayorForm identifyPayorForm;

	private DoAuthenticateUser authenticateUser;
	
	private PaymentInformation pi;
	
	private String eivReasonCodeDescriptions; 

	private boolean bankTest = false;
	private boolean eivTest = false;

	/** Session variable for VCRC request type. */
	public static final String REQUESTTYPE = "requestType";

	public SessionVars() {
		// can't use the vcrcLogger bean while instantiating the SessionVars bean
		logger.log(Level.INFO, "Initializing Session Vars");

		// initialize breadcrumb tree here
		breadcrumbTree = new BreadcrumbTree();
	}

	/**
	 * Clear all objects
	 */
	public void clearSessionVars() {
		
		logger.log(Level.INFO, "Clearing Session Vars");

		breadcrumbTree = new BreadcrumbTree();

		captchaText = null;
		requestType = null;
		confirmationType = null;

		sessionId = 0;
		partyId = 0;
		invoiceId = 0;
		serviceId = 0;
		serviceFeeAmount = 0;

		accessForm = null;
		consentForm = null;
		recordCheckForm = null;
		identifyPayorForm = null;

		authenticateUser = null;
		
		pi = null;
		
		//bcep testing only
		bankTest = false;
		
		//eiv testing only 
		eivTest = false;
		
	}

	public Properties getEcrc_props() {
		return ecrc_props;
	}

	public BreadcrumbTree getBreadcrumbTree() {
		return breadcrumbTree;
	}

	/**
	 * Append a breadcrumb node to the end of the list.
	 * 
	 * @param name
	 * @param level
	 */
	public void addBreadcrumbNode(String name, int level) {
		BreadcrumbNode node = new BreadcrumbNode(name, level);

		breadcrumbTree.addNode(node);

	}

	public String getCaptchaText() {
		return captchaText;
	}

	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}
	
	/**
	 * Returns VCRC request type.
	 * 
	 * @return
	 */
	public String getRequestType() {
		return this.requestType;
	}

	/**
	 * Sets the CRC request type for the session.
	 * 
	 * @param request
	 * @param requestType
	 */
	public void setCRCRequestType() {
		this.requestType = Consts.REQUEST_TYPE_CHECK;
	}

	/**
	 * Sets the Share request type for the session.
	 * 
	 * @param request
	 * @param requestType
	 */
	public void setShareRequestType() {
		this.requestType = Consts.REQUEST_TYPE_SHARE;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}

	public int getInvoiceId() throws ECRCException {
		String orgApplRelationship = this.getOrgApplRelationship();
		// volunteers and one-time ticket holders payed for by the organization do not have invoice IDs
		if (orgApplRelationship.equals(Consts.ORG_APPL_RELATION_VOLUNTEER) 
				|| (orgApplRelationship.equals(Consts.ORG_APPL_RELATION_ONETIMETIK) 
						&& this.identifyPayorForm.getPayor().equals(Consts.ORG_TO_PAY))) {
			throw new ECRCException("Attempt to access Invoice ID for invalid role.");
		}
		
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getServiceIdAsString() {
		return String.valueOf(serviceId);
	}

	public double getServiceFeeAmount() {
		return serviceFeeAmount;
	}

	public void setServiceFeeAmount(double serviceFeeAmount) {
		this.serviceFeeAmount = serviceFeeAmount;
	}

	public String getConfirmationType() {
		return confirmationType;
	}

	/**
	 * Sets the PAY confirmation type for the session.
	 * 
	 */
	public void setPAYConfirmationType() {
		this.confirmationType = Consts.CONF_TYPE_PAY;
	}
	
	/** Sets the NOPAY confirmation type for the session.
	 * 
	 */
	public void setNOPAYConfirmationType() {
		this.confirmationType = Consts.CONF_TYPE_NOPAY;
	}
	
	/**
	 * Sets the SHARE confirmation type for the session.
	 * 
	 */
	public void setSHAREConfirmationType() {
		this.confirmationType = Consts.CONF_TYPE_SHARE;
	}

	/**
	 * getter for authorized access.
	 * 
	 * @return
	 */
	public boolean isAllowAccess() {
		if (null != this.authenticateUser) {
			return this.authenticateUser.getResponseCode() == Consts.REST_SUCCESS;
		} else
			return false;
	}

	public DoAuthenticateUser getAuthenticateUser() {
		return authenticateUser;
	}

	public AccessForm getAccessForm() {
		return accessForm;
	}

	public void setAccessForm(AccessForm accessForm) {
		this.accessForm = accessForm;
	}

	public ConsentForm getConsentForm() {
		return consentForm;
	}

	public void setConsentForm(ConsentForm consentForm) {
		this.consentForm = consentForm;
	}

	public RecordCheckForm getRecordCheckForm() {
		return recordCheckForm;
	}

	public void setRecordCheckForm(RecordCheckForm recordCheckForm) {
		this.recordCheckForm = recordCheckForm;
	}

	public IdentifyPayorForm getIdentifyPayorForm() {
		return identifyPayorForm;
	}

	public void setIdentifyPayorForm(IdentifyPayorForm identifyPayorForm) {
		this.identifyPayorForm = identifyPayorForm;
	}
	
	public EivForm getEivForm() {
		if (null == this.eivForm) {
			return new EivForm();
		} else {
			return this.eivForm;
		}
	}

	public void setAuthenticateUser(DoAuthenticateUser doAuthenticateUser) {
		this.authenticateUser = doAuthenticateUser;
	}

	public String getOrgApplRelationship() {
		if (null != this.authenticateUser && isAllowAccess()) {
			return this.authenticateUser.getAccessCodeResponse()
					.getOrgApplicantRelationship();
		} else
			return "";
	}
	
	//public void setPaymentInformation(PaymentInformation pi) {
	//	this.pi = pi; 
	//}
	
	public PaymentInformation getPaymentInformation() {
		if (null != this.pi) {
			return this.pi;
		} else {
			this.pi = new PaymentInformation();
			return this.pi;
		}
	}

	public boolean isBankTest() {
		return bankTest;
	}

	public void setBankTest(boolean bankTest) {
		this.bankTest = bankTest;
	}

	public boolean isEivTest() {
		return eivTest;
	}

	public void setEivTest(boolean eivTest) {
		this.eivTest = eivTest;
	}

	public String getEivReasonCodeDescriptions() {
		return eivReasonCodeDescriptions;
	}

	public void setEivReasonCodeDescriptions(String eivReasonCodeDescriptions) {
		this.eivReasonCodeDescriptions = eivReasonCodeDescriptions;
	}

	/**
	 * Intended only for debugging.
	 * 
	 * <P>
	 * Here, a generic implementation uses reflection to print names and values
	 * of all fields <em>declared in this class</em>. Note that superclass
	 * fields are left out of this implementation.
	 * 
	 * <p>
	 * The format of the presentation could be standardized by using a
	 * MessageFormat object with a standard pattern.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		String newLine = "<BR/>";

		result.append(this.getClass().getName());
		result.append(newLine);
		result.append(" Object {");
		result.append(newLine);

		// determine fields declared in this class only (no fields of
		// superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			result.append("  ");
			try {
				result.append("<b>" + field.getName() + "</b>");
				result.append(": ");
				// requires access to private field:
				result.append(field.get(this));
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
			result.append(newLine);
		}
		result.append("}");

		return result.toString();
	}	

}
