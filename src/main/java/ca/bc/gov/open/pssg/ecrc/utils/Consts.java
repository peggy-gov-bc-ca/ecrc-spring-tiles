/**
 * @(#)Consts.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.utils;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public final class Consts  {

  /** RESTful service constants **/
  public static final int REST_SUCCESS = 0;
  public static final int REST_FAIL = 1;
  public static final int REST_INTERNAL_ERROR = -1; // no db connection or something. 	
  
  public static final String ORG_APPL_RELATION_VOLUNTEER = "VOLUNTEER"; 
  public static final String ORG_APPL_RELATION_EMPLOYEE = "EMPLOYEE";
  public static final String ORG_APPL_RELATION_ONETIMETIK = "ONETIME"; 
  
  public static final String  ORG_TO_PAY = "O";
  public static final String  APPL_TO_PAY = "A";
  
  public static final String REQUEST_TYPE_SHARE = "share";
  public static final String REQUEST_TYPE_CHECK = "crc";
  
  public static final String CONF_TYPE_PAY = "PAY";
  public static final String CONF_TYPE_NOPAY = "NOPAY";
  public static final String CONF_TYPE_SHARE = "SHARE";
  
  public static final String COUNTRY_CANADA = "CANADA";
  public static final String PROVINCE_BC = "BRITISH COLUMBIA";
  
  // Employee Schedule Types 
  public static final String SCHEDULE_A = "WBSA";  	
  public static final String SCHEDULE_B = "WBSB"; 	
  public static final String SCHEDULE_C = "WBSC";	 
  public static final String SCHEDULE_D = "WBSD";
  public static final String SCHEDULE_E = "WBSE";
  
  //Volunteer Schedule Type
  public static final String SCHEDULE_V = "WBSV";
  
  //Sharing Request Schedule Type 
  public static final String SCHEDULE_S = "WBSS";
  
  // One-time Schedule Types
  public static final String SCHEDULE_OT_A = "BBCA";
  public static final String SCHEDULE_OT_B = "BBCB";
  public static final String SCHEDULE_OT_C = "BBCC";
  public static final String SCHEDULE_OT_D = "BBCD";
  public static final String SCHEDULE_OT_E = "BBCE";
  
  //BCEP constants
  public static final String APKEY_BCEP_ITEMID = "bcep.item.name";
  public static final String APKEY_BCEP_ACCOUNT = "bcep.account";
  public static final String APKEY_BCEP_STATEMENT = "bcep.statement";
  public static final String APKEY_BCEP_KEY = "bcep.key";
  public static final String APKEY_BCEP_IVKEY = "bcep.ivkey";
  public static final String APKEY_BCEP_APPID = "bcep.appid";
  public static final String APKEY_BCEP_USER = "bcep.user";
  public static final String APKEY_BCEP_PASSWORD = "bcep.password";
  public static final String APKEY_BCEP_HOST = "bcep.host.url";
  public static final String APKEY_BCEP_RETURN_HOST = "bcep.return.host";
  
  // status approved from bcep
  public static final String STATUS_DECLINED = "D";
  // status declined from bcep
  public static final String STATUS_APPROVED = "A";

  /** System properties **/ 
  public static final String NEW_LINE = System.getProperty("line.separator");
  public static final String FILE_SEPARATOR = System.getProperty("file.separator");
  public static final String PATH_SEPARATOR = System.getProperty("path.separator");
  
  /** Equifax Service constants **/
  public final static int ES_SUCCESS_CD = 1;
  public final static int ES_FAILURE_CD = 0;
  
  public static final String ADDRESS_TYPE_CURRENT = "Current";
  public static final String ADDRESS_TYPE_FORMER = "Former";
  public static final String ADDRESS_TYPE_OTHER = "Other";
  
  public static final String PHONE_TYPE_HOME = "Home";
  public static final String PHONE_TYPE_WORK = "Work";
  public static final String PHONE_TYPE_MOBILE = "Mobile";
  public static final String PHONE_TYPE_OTHER = "Other";
  
  
 /** 
  * Tri-state variable used for response from CheckApplicantForPrevCRC.
  * @see ca.bc.gov.ag.ecrc.services.FigaroServices 
  */
  public static enum PrevCRCResponseType {
		TRUE,
		FALSE, 
		REVIEW;
	}

  /**
   The caller references the constants using <tt>Constants.EMPTY_STRING</tt>, 
   and so on. Thus, the caller should be prevented from costructing objects of 
   this class, by declaring this private constructor. 
  */
  private Consts(){
    //this prevents even the native class from 
    //calling this constructor as well :
    throw new AssertionError();
  }
  
}