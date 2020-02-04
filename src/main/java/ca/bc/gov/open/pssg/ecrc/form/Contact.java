/**
 * @(#)Contact.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.form;
 
/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
public class Contact {
	
    private String firstname;
	private String lastname;
    private String email;
    private String telephone;
     
    //.. getter and setter for all above fields.
    
    public String getFirstname() {
  		return firstname;
  	}
  	public void setFirstname(String firstname) {
  		this.firstname = firstname;
  	}
  	public String getLastname() {
  		return lastname;
  	}
  	public void setLastname(String lastname) {
  		this.lastname = lastname;
  	}
  	public String getEmail() {
  		return email;
  	}
  	public void setEmail(String email) {
  		this.email = email;
  	}
  	public String getTelephone() {
  		return telephone;
  	}
  	public void setTelephone(String telephone) {
  		this.telephone = telephone;
  	}
     
}