package ca.bc.gov.open.pssg.ecrc.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;

import org.springframework.beans.factory.annotation.Autowired;

import ca.bc.gov.open.pssg.ecrc.exceptions.ECRCException;
import ca.bc.gov.open.pssg.ecrc.rest.doAuthenticateUser.DoAuthenticateUser.ScheduleTypes.ScheduleType;
import ca.bc.gov.open.pssg.ecrc.rest.doAuthenticateUser.DoAuthenticateUser.ScopeLevels.ScopeLevel;

public class SessionVarHelpers {

	@Autowired
	private SessionVars sessionVars;
	
	@Autowired
	private DateUtils du;
	
	public SessionVarHelpers() {};
	
	/**
	 * Returns a formatted string representing the double.
	 * 
	 * @param serviceFeeAmount
	 * @throws ECRCException 
	 */
	public String getServiceFeeAmountString() throws ECRCException {
		if (sessionVars == null) {
			throw new ECRCException("SessionVars has not been initialized.");
		}
		
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMinimumIntegerDigits(1);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		formatter.setRoundingMode(RoundingMode.HALF_UP);
		
//		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(sessionVars.getServiceFeeAmount());
	}
	
	/**
	 * Return the String description of the selected scope level
	 * @throws ECRCException 
	 * 
	 */
	public String getScopeLevelDesc() throws ECRCException {
		if (sessionVars == null) {
			throw new ECRCException("SessionVars has not been initialized.");
		}
		
		try { 
			sessionVars.getAuthenticateUser();
		} catch (NullPointerException npe) {
			throw new ECRCException("SessionVars.getAuthenticateUser object is null.");
		}
		
		try {
			sessionVars.getAuthenticateUser().getScopeLevels().getScopeLevel(); 
		}  catch (NullPointerException npe) {
			throw new ECRCException("SessionVars.getAuthenticateUser.getScopeLevel list is null.");
		}
		
		String scopeLevelDesc = "";
		if (null != sessionVars.getAuthenticateUser() && sessionVars.isAllowAccess() && null != sessionVars.getRecordCheckForm()) {
			for (ScopeLevel scopeLevel : sessionVars.getAuthenticateUser().getScopeLevels().getScopeLevel()) {
				if (scopeLevel.getCrcScopeLevelCd().equalsIgnoreCase(this.getScopeLevelCd())) {
					scopeLevelDesc = scopeLevel.getCrcScopeLevelDsc();
					break;
				}
			}
		}
		return scopeLevelDesc;
	}
	
	/**
	 * Return the String description of the selected schedule type
	 * @throws ECRCException 
	 */
	public String getScheduleTypeDesc() throws ECRCException {
		if (null == sessionVars) {
			throw new ECRCException("SessionVars has not been initialized.");
		}
		
		try { 
			sessionVars.getAuthenticateUser();
		} catch (NullPointerException npe) {
			throw new ECRCException("SessionVars.getAuthenticateUser object is null.");
		}
		
		try {
			sessionVars.getAuthenticateUser().getScheduleTypes().getScheduleType(); 
		}  catch (NullPointerException npe) {
			throw new ECRCException("SessionVars.getAuthenticateUser.getScheduleTypes list is null.");
		}
		
		String scheduleTypeDesc = "";
		if (null != sessionVars.getAuthenticateUser() && sessionVars.isAllowAccess() && null != sessionVars.getRecordCheckForm()) {
			for (ScheduleType scheduleType : sessionVars.getAuthenticateUser().getScheduleTypes().getScheduleType()) {
				if (scheduleType.getCrcScheduleTypeCd().equalsIgnoreCase(this.getScheduleTypeCd())) {
					scheduleTypeDesc = scheduleType.getCrcScheduleTypeDsc();
					break;
				}
			}
		}
		return scheduleTypeDesc;
	}
	
	/**
	 * Return the Schedule Type Cd.
	 * 
	 * Derive schedule Type depending on ticket type. If one time, use the value
	 * set in the form else use the default value for the ticket arriving in the
	 * doAuthenticate user call.
	 * 
	 * @throws ECRCException
	 */
	public String getScheduleTypeCd() throws ECRCException {
		if (sessionVars == null || sessionVars.getRecordCheckForm() == null
				|| sessionVars.getAuthenticateUser() == null) {
			throw new ECRCException(
					"SessionVars or a required property has not been initialized.");
		}
		String scheduleType;
		if (sessionVars.getOrgApplRelationship().equals(
				Consts.ORG_APPL_RELATION_ONETIMETIK)) {
			scheduleType = sessionVars.getRecordCheckForm().getScheduleType();
		} else {
			scheduleType = sessionVars.getAuthenticateUser()
					.getAccessCodeResponse().getDefaultScheduleTypeCd();
		}
		return scheduleType;
	}
	
	/**
	 * Return the Scope Level Cd.
	 * 
	 * Derive scope level depending on ticket type. If value found set in the
	 * form, use it, else use the default value for the ticket arriving in the
	 * doAuthenticate user call.
	 * 
	 * @throws ECRCException
	 */
	public String getScopeLevelCd() throws ECRCException {
		if (sessionVars == null || sessionVars.getRecordCheckForm() == null
				|| sessionVars.getAuthenticateUser() == null) {
			throw new ECRCException(
					"SessionVars or a required property has not been initialized.");
		}
		String scopeLevelCd;
		if (null != sessionVars.getRecordCheckForm().getOffenseCategory()) {
			scopeLevelCd = sessionVars.getRecordCheckForm()
					.getOffenseCategory();
		} else {
			scopeLevelCd = sessionVars.getAuthenticateUser()
					.getAccessCodeResponse().getDefaultCrcScopeLevelCd();
		}
		return scopeLevelCd;

	}
	
	/**
	 * Returns a user friendly display name for the applicant.  
	 * @return String
	 */
	public String getApplicantDisplayname() {
		if (null != sessionVars.getAuthenticateUser()) {
			return sessionVars.getAuthenticateUser().getAccessCodeResponse()
					.getContactFirstNm() + " " + sessionVars.getAuthenticateUser().getAccessCodeResponse()
					.getContactSurnameNm();
		} else
			return "";
	}
	
	/**
	 * Returns birthDate as Jan 01 1990 format. 
	 * Assumes incoming date has 'screen date format' and 
	 * outgoing date format will have 'pretty format'. 
	 * (See vcrc.properties for format masks) 
	 * 
	 * @return  String 
	 * @throws ECRCException
	 */
	public String getPrettyFormatBirthDate() throws ECRCException {
		if (null != sessionVars.getRecordCheckForm().getBirthDate()) {
			String inputMask = sessionVars.getEcrc_props().getProperty("screenDateFormat"); 
			String outputMask = sessionVars.getEcrc_props().getProperty("prettyScreenFormat");
			return du.formatDateString(sessionVars.getRecordCheckForm().getBirthDate(), inputMask, outputMask);
		} else return "";
	}
	
}
