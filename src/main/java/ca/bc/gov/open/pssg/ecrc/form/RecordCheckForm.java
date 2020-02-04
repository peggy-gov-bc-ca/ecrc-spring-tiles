/**
 * @(#)RecordCheckForm.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.form;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;

import ca.bc.gov.open.pssg.ecrc.constraints.ConditionalAddressRequire;
import ca.bc.gov.open.pssg.ecrc.constraints.ConditionalRequire;
import ca.bc.gov.open.pssg.ecrc.constraints.ValidDate;
import ca.bc.gov.open.pssg.ecrc.utils.Consts;

/**
 * @author Ministry of Attorney General
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $, ($LastChangedDate: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $)
 */
@Controller
@ConditionalRequire.List({
	@ConditionalRequire(field1="lastName1", field2="firstName1", message="{firstName.required}"), 
	@ConditionalRequire(field1="firstName1", field2="lastName1", message="{surname.required}"),
	@ConditionalRequire(field1="lastName2", field2="firstName2", message="{firstName.required}"), 
	@ConditionalRequire(field1="firstName2", field2="lastName2", message="{surname.required}"),
	@ConditionalRequire(field1="lastName3", field2="firstName3", message="{firstName.required}"), 
	@ConditionalRequire(field1="firstName3", field2="lastName3", message="{surname.required}")
	
})
@ConditionalAddressRequire.List({
	@ConditionalAddressRequire(mailingAddress="prevMailingAddress1", city="prevCity1", postalCode="prevPostalCode1", months="prevMonths1"),
	@ConditionalAddressRequire(mailingAddress="prevMailingAddress2", city="prevCity2", postalCode="prevPostalCode2", months="prevMonths2")
})
public class RecordCheckForm implements Serializable  { 


	/**
	 * 
	 */
	private static final long serialVersionUID = 7790925540473093294L;

	@NotEmpty(message = "{surname.required}")
	@Size(min=0, max=40, message="{surname.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{surname.invalid.characters}")
	private String lastName;
	
	@NotEmpty(message = "{firstName.required}")
	@Size(min = 0, max = 25, message="{firstName.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{firstName.invalid.characters}")
	private String firstName;
	
	@Size(min = 0, max = 25, message="{middleName.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{middleName.invalid.characters}")
	private String middleName;
	
	@ValidDate
	@NotEmpty(message = "{birthDate.required}")
	private String birthDate;
	
	@NotEmpty(message = "{gender.required}")
	private String gender;
	
	@NotEmpty(groups = CrcChecks.class, message = "{birthPlace.required}")
	@Size(groups = CrcChecks.class, min = 0, max = 40, message="{birthPlace.size}")
	private String birthPlace;
	
	@Size(min = 0, max = 25)
	@Pattern(regexp="^[0-9A-Za-z -]*$", message="{licenceNo.invalid.characters}")
	private String licenceNo;
	
	private String licenceProvince;
	
	@NotEmpty(message = "{applicantPosition.required}")
	@Size(min = 0, max = 80, message="{applicantPosition.size}")
	private String applicantPosition;
	
	@NotEmpty(message = "{offenseCategory.required}")
	private String offenseCategory;
	
	private String scheduleType;
	
	@Size(groups = CrcChecks.class, min = 0, max = 100, message="{careFacility.size}")
	private String careFacility;
	
	@Size(groups = ShareChecks.class, min = 0, max = 15, message="{serviceNumber.size}")
	private String serviceNumber;
	
	@Size(min = 0, max = 40, message="{surname.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{surname.invalid.characters}")
	private String lastName1;
	
	@Size(min = 0, max = 25, message="{firstName.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{firstName.invalid.characters}")
	private String firstName1;
	
	@Size(min = 0, max = 25, message="{middleName.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{middleName.invalid.characters}")
	private String middleName1;
	
	@Size(min = 0, max = 40, message="{surname.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{surname.invalid.characters}")
	private String lastName2;
	
	@Size(min = 0, max = 25, message="{firstName.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{firstName.invalid.characters}")
	private String firstName2;
	
	@Size(min = 0, max = 25, message="{middleName.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{middleName.invalid.characters}")
	private String middleName2;
	
	@Size(min = 0, max = 40, message="{surname.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{surname.invalid.characters}")
	private String lastName3;
	
	@Size(min = 0, max = 25, message="{firstName.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{firstName.invalid.characters}")
	private String firstName3;
	
	@Size(min = 0, max = 25, message="{middleName.size}")
	@Pattern(regexp="^[A-Za-z -]*$", message="{middleName.invalid.characters}")
	private String middleName3;
	
	@NotEmpty(message = "{mailingAddress.required}")
	@Size(min = 0, max = 40, message="{mailingAddress.size}")
	@Pattern(regexp="[a-zA-Z0-9#/,\\- .��������������������������]*", message="{mailAddress.invalid.characters}")
	private String mailingAddress;
	
	@NotEmpty(message = "{city.required}")
	@Size(min = 0, max = 25, message="{city.size}")
	@Pattern(regexp="[a-zA-Z0-9 .'\\-��������������������������]*", message="{city.invalid.characters}")
	private String city;
	
	@NotEmpty(message = "{province.required}")
	private String province;
	
	@NotEmpty(message = "{country.required}")
	private String country = Consts.COUNTRY_CANADA;
	
	@NotEmpty(message = "{postalCode.required}")
	@Size(min = 0, max = 7, message="{postalCode.size}")
	@Pattern(regexp="^([a-zA-Z][0-9][a-zA-Z] [0-9][a-zA-Z][0-9])?$", message="{postalCode.invalid.format}")
	private String postalCode;
	
	@NotEmpty(message = "{contactPhone.required}")
	@Size(min = 0, max = 12, message="{contactPhone.size}")
	@Pattern(regexp="^([0-9]{3}-[0-9]{3}-[0-9]{4})?$", message="{contactPhone.invalid.format}")
	private String contactPhone;
	
	@NotEmpty(message = "{months.required}")
	@Pattern(regexp="^[0-9]*$", message="{months.invalid.characters}")
	private String months;
	
	// bogus field. Only here to support error tag. 
	// see crcRecordCheckController. 
	private String monthsAtPrevious;
	
	@Size(min = 0, max = 40, message="{mailingAddress.size}")
	@Pattern(regexp="[a-zA-Z0-9#/,\\- .��������������������������]*", message="{mailAddress.invalid.characters}")
	private String prevMailingAddress1;
	
	@Size(min = 0, max = 25, message="{city.size}")
	@Pattern(regexp="[a-zA-Z0-9 .'\\-��������������������������]*", message="{city.invalid.characters}")
	private String prevCity1;
	
	private String prevProvince1;
	
	private String prevCountry1 = Consts.COUNTRY_CANADA;
	
	@Size(min = 0, max = 7, message="{postalCode.size}")
	@Pattern(regexp="^([a-zA-Z][0-9][a-zA-Z] [0-9][a-zA-Z][0-9])?$", message="{postalCode.invalid.format}")
	private String prevPostalCode1;
	
	@Pattern(regexp="^[0-9]*$", message="{months.invalid.characters}")
	private String prevMonths1;

	@Size(min = 0, max = 40, message="{mailingAddress.size}")
	@Pattern(regexp="[a-zA-Z0-9#/,\\- .��������������������������]*", message="{mailAddress.invalid.characters}")
	private String prevMailingAddress2;
	
	@Size(min = 0, max = 25, message="{city.size}")
	@Pattern(regexp="[a-zA-Z0-9 .'\\-��������������������������]*", message="{city.invalid.characters}")
	private String prevCity2;
	
	private String prevProvince2;
	
	private String prevCountry2 = Consts.COUNTRY_CANADA;
	
	@Size(min = 0, max = 7, message="{postalCode.size}")
	@Pattern(regexp="^([a-zA-Z][0-9][a-zA-Z] [0-9][a-zA-Z][0-9])?$", message="{postalCode.invalid.format}")
	private String prevPostalCode2;
	
	@Pattern(regexp="^[0-9]*$", message="{months.invalid.characters}")
	private String prevMonths2;
	
//	private boolean prevAddress1 = false;
//	private boolean prevAddress2 = false;
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getLicenceProvince() {
		return licenceProvince;
	}

	public void setLicenceProvince(String licenceProvince) {
		this.licenceProvince = licenceProvince;
	}

	public String getApplicantPosition() {
		return applicantPosition;
	}

	public void setApplicantPosition(String applicantPosition) {
		this.applicantPosition = applicantPosition;
	}

	public String getOffenseCategory() {
		return offenseCategory;
	}

	public void setOffenseCategory(String offenseCategory) {
		this.offenseCategory = offenseCategory;
	}
	
	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getCareFacility() {
		return careFacility;
	}

	public void setCareFacility(String careFacility) {
		this.careFacility = careFacility;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getLastName1() {
		return lastName1;
	}

	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	public String getFirstName1() {
		return firstName1;
	}

	public void setFirstName1(String firstName1) {
		this.firstName1 = firstName1;
	}

	public String getMiddleName1() {
		return middleName1;
	}

	public void setMiddleName1(String middleName1) {
		this.middleName1 = middleName1;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	public String getFirstName2() {
		return firstName2;
	}

	public void setFirstName2(String firstName2) {
		this.firstName2 = firstName2;
	}

	public String getMiddleName2() {
		return middleName2;
	}

	public void setMiddleName2(String middleName2) {
		this.middleName2 = middleName2;
	}

	public String getLastName3() {
		return lastName3;
	}

	public void setLastName3(String lastName3) {
		this.lastName3 = lastName3;
	}

	public String getFirstName3() {
		return firstName3;
	}

	public void setFirstName3(String firstName3) {
		this.firstName3 = firstName3;
	}

	public String getMiddleName3() {
		return middleName3;
	}

	public void setMiddleName3(String middleName3) {
		this.middleName3 = middleName3;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getMonths() {
		if (null != months) {
			return months.trim();
		} else return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getMonthsAtPrevious() {
		return monthsAtPrevious;
	}

	public void setMonthsAtPrevious(String monthsAtPrevious) {
		this.monthsAtPrevious = monthsAtPrevious;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	public String getPrevMailingAddress1() {
		return prevMailingAddress1;
	}

	public void setPrevMailingAddress1(String prevMailingAddress1) {
		this.prevMailingAddress1 = prevMailingAddress1;
	}

	public String getPrevCity1() {
		return prevCity1;
	}

	public void setPrevCity1(String prevCity1) {
		this.prevCity1 = prevCity1;
	}

	public String getPrevProvince1() {
		return prevProvince1;
	}

	public void setPrevProvince1(String prevProvince1) {
		this.prevProvince1 = prevProvince1;
	}

	public String getPrevCountry1() {
		return prevCountry1;
	}

	public void setPrevCountry1(String prevCountry1) {
		this.prevCountry1 = prevCountry1;
	}

	public String getPrevPostalCode1() {
		return prevPostalCode1;
	}

	public void setPrevPostalCode1(String prevPostalCode1) {
		this.prevPostalCode1 = prevPostalCode1;
	}

	public String getPrevMonths1() {
		return prevMonths1;
	}

	public void setPrevMonths1(String prevMonths1) {
		this.prevMonths1 = prevMonths1;
	}

	public String getPrevMailingAddress2() {
		return prevMailingAddress2;
	}

	public void setPrevMailingAddress2(String prevMailingAddress2) {
		this.prevMailingAddress2 = prevMailingAddress2;
	}

	public String getPrevCity2() {
		return prevCity2;
	}

	public void setPrevCity2(String prevCity2) {
		this.prevCity2 = prevCity2;
	}

	public String getPrevProvince2() {
		return prevProvince2;
	}

	public void setPrevProvince2(String prevProvince2) {
		this.prevProvince2 = prevProvince2;
	}

	public String getPrevCountry2() {
		return prevCountry2;
	}

	public void setPrevCountry2(String prevCountry2) {
		this.prevCountry2 = prevCountry2;
	}

	public String getPrevPostalCode2() {
		return prevPostalCode2;
	}

	public void setPrevPostalCode2(String prevPostalCode2) {
		this.prevPostalCode2 = prevPostalCode2;
	}

	public String getPrevMonths2() {
		return prevMonths2;
	}

	public void setPrevMonths2(String prevMonths2) {
		this.prevMonths2 = prevMonths2;
	}
	
	

	// validation groups
	public interface CrcChecks {}
	
	public interface ShareChecks {}

	// Utils methods 
	public boolean isPrevAddress1() {
		return ( !StringUtils.isEmpty(this.prevMailingAddress1) 
				&& !StringUtils.isEmpty(this.prevCity1)  
				&& !StringUtils.isEmpty(this.prevProvince1)
				&& !StringUtils.isEmpty(this.prevCountry1)
				&& !StringUtils.isEmpty(this.prevPostalCode1) 
				&& !StringUtils.isEmpty(this.prevMonths1) 
				);	
	}

	public boolean isPrevAddress2() {
		return ( !StringUtils.isEmpty(this.prevMailingAddress2)
				&& !StringUtils.isEmpty(this.prevCity2)  
				&& !StringUtils.isEmpty(this.prevProvince2)
				&& !StringUtils.isEmpty(this.prevCountry2)
				&& !StringUtils.isEmpty(this.prevPostalCode2) 
				&& !StringUtils.isEmpty(this.prevMonths2) 
				);	
	}
	
	
}
