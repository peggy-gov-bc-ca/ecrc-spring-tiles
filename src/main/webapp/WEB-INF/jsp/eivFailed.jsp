<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="ca.bc.gov.ag.ecrc.utils.Consts" %>

<c:set var="REQUEST_TYPE_CHECK" value="<%=Consts.REQUEST_TYPE_CHECK%>" />
<c:set var="REQUEST_TYPE_SHARE" value="<%=Consts.REQUEST_TYPE_SHARE%>" />

<c:set var="SCHEDULE_D" value="<%=Consts.SCHEDULE_D%>" />
<c:set var="SCHEDULE_OT_D" value="<%=Consts.SCHEDULE_OT_D%>" />

<c:set var="ONETIME" value="<%=Consts.ORG_APPL_RELATION_ONETIMETIK%>" />

<c:set var="COUNTRY_CANADA" value="<%=Consts.COUNTRY_CANADA%>" />

<div id="MainContent">
	<div class="BodyContent sizable">
	
		<c:choose>
			<c:when test="${ sessionVars.requestType == REQUEST_TYPE_CHECK }">
				<div class="vcrcBoxFilled">
				<p><img src="${pageContext.request.contextPath}/assets/img/exclamation-icon.png"/>&nbsp;The system could not complete your request to initiate a criminal record check online.</p>
				</div>
				<p>A copy of this form can be printed using the "Print" button below. A signed and dated version of this form 
				can be submitted to the Criminal Records Review Program to initiate a criminal record check:</p>
			</c:when>
			<c:when test="${ sessionVars.requestType == REQUEST_TYPE_SHARE }">
				<div class="vcrcBoxFilled">
				<p><img src="${pageContext.request.contextPath}/assets/img/exclamation-icon.png"/>&nbsp;The system could not find a completed criminal record check to share.</p>
				</div>
				<p>A copy of this form can be printed using the "Print" button below. A signed and dated version of this form 
				can be submitted to the Criminal Records Review Program to initiate a request to share the result of a completed 
				criminal record check:</p>
			</c:when>
		</c:choose>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td>By Mail:</td><td>${ sessionVars.ecrc_props['securities.contact.address1'] }</td>
						</tr>
						<tr>
							<td></td><td>${ sessionVars.ecrc_props['securities.contact.address2'] }</td>
						</tr>
						<tr>
							<td>By Fax:</td><td>${ sessionVars.ecrc_props['securities.contact.fax'] }</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<hr />
				</td>
			</tr> 
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td colspan="2">
								<b>Applicant Information</b><br/> 
							</td>
						</tr>
						<tr>
							<td width="50%">
							<label>Surname: </label>
							</td>
							<td width="50%">
							${ sessionVars.recordCheckForm.lastName }
							</td>
			
						</tr>
						<tr>
							<td>
							<label>First Name: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.firstName }
							</td>
						</tr>
						<tr>
							<td>
							<label>Middle Name: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.middleName }
							</td>
						</tr>
						<tr>
							<td>
							<label>Date of Birth: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.birthDate }
							</td>
						</tr>
						<tr>
							<td>
							<label>Gender: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.gender }
							</td>
						</tr>
						<c:if test="${ sessionVars.requestType == REQUEST_TYPE_CHECK }">
							<tr>
								<td>
								<label>Birth Place: </label> <span class="style2">(City,
								Province/State, Country) </span>
								</td>
								<td>
								${ sessionVars.recordCheckForm.birthPlace }
								</td>
							</tr>
						</c:if>
						<tr>
							<td>
							<label>Driver's Licence #: </label> <span class="style2">(Current or Expired)</span>
							</td>
							<td>
							${ sessionVars.recordCheckForm.licenceNo }
							</td>
						</tr>
						<tr>
							<td>
							<label>Driver's Licence Province of Issue: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.licenceProvince }
							</td>
						</tr>
						<tr>
							<td>
							<label>Applicant's Position/Job Title: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.applicantPosition }
							</td>
						</tr>
						<tr>
							<td>
							<label>Category of Offenses: </label>
							</td>
							<td>
							${ scopeLevelDesc }
							</td>
						</tr>
						
						<%-- schedule type selection only visible when user is 'ONETIME' ticket type --%>
						<c:if test="${ sessionVars.requestType == REQUEST_TYPE_CHECK && sessionVars.authenticateUser.accessCodeResponse.orgApplicantRelationship == ONETIME }">
							<tr>
								<td>
								<label>Schedule Type: </label>
								</td>
								<td>
								${ scheduleTypeDesc }
								</td>
							</tr>
						</c:if>
						<c:if test="${ sessionVars.requestType == REQUEST_TYPE_CHECK  && (sessionVars.authenticateUser.accessCodeResponse.defaultScheduleTypeCd == SCHEDULE_D || sessionVars.recordCheckForm.scheduleType == SCHEDULE_OT_D) }">
							<tr>
								<td>
								<label>Licensed Child Care or Adult Care Facility Name: </label>
								</td>
								<td>
								${ sessionVars.recordCheckForm.careFacility }
								</td>
							</tr>
						</c:if>
						<c:if test="${ sessionVars.requestType == REQUEST_TYPE_SHARE }">
							<tr>
								<td>
									<label>Service Number: </label><span class="style2">(Of completed criminal record check, if known)</span>
								</td>
								<td>
								${ sessionVars.recordCheckForm.serviceNumber }
								</td>
							</tr>
						</c:if>
						<tr>
							<td colspan="2">
							<hr />
							</td>
						</tr> 
						<tr>
							<td colspan="2">
							<b>Other Names Used</b><br/>
							<span style="font-size: xx-small">(e.g. maiden name, birth name, or previous married name(s)) </span><br/>
							</td>
						</tr>
						<tr>
							<td>
							<label>Surname: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.lastName1 }
							</td>
						</tr>
						<tr>
							<td>
							<label>First Name: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.firstName1 }
							</td>
						</tr>
						<tr>
							<td>
							<label>Middle Name: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.middleName1 }
							</td>
						</tr>
						<tr>
							<td colspan="2">
							</td>
						</tr>
						<tr>
							<td>
							<label>Surname: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.lastName2 }
							</td>
						</tr>
						<tr>
							<td>
							<label>First Name: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.firstName2 }
							</td>
						</tr>
						<tr>
							<td>
							<label>Middle Name: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.middleName2 }
							</td>
						</tr>
						<tr>
							<td colspan="2">
							</td>
						</tr>
						<tr>
							<td>
							<label>Surname: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.lastName3 }
							</td>
						</tr>
						<tr>
							<td>
							<label>First Name: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.firstName3 }
							</td>
						</tr>
						<tr>
							<td>
							<label>Middle Name: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.middleName3 }
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<hr />
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<b>Contact Information</b><br/>
							</td>
						</tr>
						<tr>
							<td class="content">
							<label>Mailing Address: </label>
							</td>
							<td class="content">
							${ sessionVars.recordCheckForm.mailingAddress }
							</td>
						</tr>
						<tr>
							<td>
							<label>City: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.city }
							</td>
						</tr>
						<tr>
							<td>
							<label>Country: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.country }
							</td>
						</tr>
						<tr>
							<td>
							<label>Province: </label>
							</td>
							<td>
							${ sessionVars.recordCheckForm.province }
							</td>
						</tr>
						<tr>
							<td>
							<label>Postal Code: </label><span class="style2">(X#X #X#)</span>
							</td>
							<td>
							${ sessionVars.recordCheckForm.postalCode }
							</td>
						</tr>
						<tr>
							<td>
								<label>Time at Address: (Months) </label>
							</td>
							<td>
								${ sessionVars.recordCheckForm.months }
							</td>
						</tr>
						<tr>
							<td>
							<label>Contact Phone #: </label><span class="style2">(###-###-####)</span>
							</td>
							<td>
							${ sessionVars.recordCheckForm.contactPhone }
							</td>
						</tr>
					<tr>
						<td colspan="2">
						<hr />
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<b>Previous Address Information</b><br/>
						</td>
					</tr>
					<tr>
						<td class="content">
						<label>Mailing Address: </label>
						</td>
						<td class="content">
						${ sessionVars.recordCheckForm.prevMailingAddress1 }
						</td>
					</tr>
					<tr>
						<td>
						<label>City: </label>
						</td>
						<td>
						${ sessionVars.recordCheckForm.prevCity1 }
						</td>
					</tr>
					<tr>
						<td>
						<label>Country: </label>
						</td>
						<td>
						${ sessionVars.recordCheckForm.prevCountry1 }
						</td>
					</tr>
					<tr>
						<td>
						<label>Province: </label>
						</td>
						<td>
						${ sessionVars.recordCheckForm.prevProvince1 }
						</td>
					</tr>
					<tr>
						<td>
						<label>Postal Code: </label><span class="style2">(X#X #X#)</span>
						</td>
						<td>
						${ sessionVars.recordCheckForm.prevPostalCode1 }
						</td>
					</tr>
					<tr>
						<td>
							<label>Time at Address: (Months) </label>
						</td>
						<td>
							${ sessionVars.recordCheckForm.prevMonths1 }
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<br />
						</td>
					</tr>
					<tr>
						<td class="content">
						<label>Mailing Address: </label>
						</td>
						<td class="content">
						${ sessionVars.recordCheckForm.prevMailingAddress2 }
						</td>
					</tr>
					<tr>
						<td>
						<label>City: </label>
						</td>
						<td>
						${ sessionVars.recordCheckForm.prevCity2 }
						</td>
					</tr>
					<tr>
						<td>
						<label>Country: </label>
						</td>
						<td>
						${ sessionVars.recordCheckForm.prevCountry2 }
						</td>
					</tr>
					<tr>
						<td>
						<label>Province: </label>
						</td>
						<td>
						${ sessionVars.recordCheckForm.prevProvince2 }
						</td>
					</tr>
					<tr>
						<td>
						<label>Postal Code: </label><span class="style2">(X#X #X#)</span>
						</td>
						<td>
						${ sessionVars.recordCheckForm.prevPostalCode2 }
						</td>
					</tr>
					<tr>
						<td>
							<label>Time at Address: (Months) </label>
						</td>
						<td>
							${ sessionVars.recordCheckForm.prevMonths2 }
						</td>
					</tr>
						<tr>
							<td colspan="2">
							<hr />
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<b>Organization Information</b><br/>
							</td>
						</tr>
						<tr>
							<td width="50%">
							Organization Name:
							</td>
							<td width="50%">
							${ sessionVars.authenticateUser.accessCodeResponse.orgNm }
							</td>
						</tr>
						<tr>
							<td>
							Address Line 1:
							</td>
							<td>
							${ sessionVars.authenticateUser.accessCodeResponse.addressLine1 }
							</td>
						</tr>
						<tr>
							<td>
							Address Line 2:
							</td>
							<td>
							${ sessionVars.authenticateUser.accessCodeResponse.addressLine2 }
							</td>
						</tr>
						<tr>
							<td>
							City:
							</td>
							<td>
							${ sessionVars.authenticateUser.accessCodeResponse.cityNm }
							</td>
						</tr>
						<tr>
							<td>
							Province:
							</td>
							<td>
							${ sessionVars.authenticateUser.accessCodeResponse.provinceNm }
							</td>
						</tr>
						<tr>
							<td>
							Country:
							</td>
							<td>
							${ sessionVars.authenticateUser.accessCodeResponse.countryNm }
							</td>
						</tr>
						<tr>
							<td>
							Postal Code:
							</td>
							<td>
							${ sessionVars.authenticateUser.accessCodeResponse.postalCodeTxt }
							</td>
						</tr>
						<tr>
							<td>
							Role:
							</td>
							<td>
							${ sessionVars.authenticateUser.accessCodeResponse.orgApplicantRelationship }
							</td>
						</tr>
			
					</table>
				</td>
			</tr>
			<tr>
				<td>
				<hr />
				</td>
			</tr>
			<tr>
				<td>
				<b>ID Verification - To be completed by the requesting Organization</b><br/><br/>
				</td>
			</tr>
			<tr>
				<td>
					<p>I certify that I _______________________________ have verified the applicant's Primary and Secondary ID as outlined 
					in the CRRP ID Verification Requirements (for a complete list of acceptable ID and organization responsibilities, please 
					visit <a href="${ sessionVars.ecrc_props['url.responsibilities'] }">${ sessionVars.ecrc_props['url.responsibilities'] }</a>)
					</p>
					<p>Signature: _______________________________		Date: _______________________________</p>
				</td>
			</tr> 
			<tr>
				<td>
				<hr />
				</td>
			</tr>
			<c:choose>
				<c:when test="${ sessionVars.requestType == REQUEST_TYPE_CHECK }">
					<tr>
						<td>
							<b>Payment Information</b><br/>
						</td>
					</tr>
					<tr>
						<td>
							<p>For volunteers completing a request for a criminal record check, no payment is required. For all other 
							applicants, a $28 fee payable by credit card (Visa or MasterCard) is required. If payment is required, please 
							submit an Application for Pre-Authorized Credit Card Usage with this form. The Application for Pre-Authorized Credit 
							Card usage is available online at <a href="${ sessionVars.ecrc_props['url.credit.card.auth'] }">${ sessionVars.ecrc_props['url.credit.card.auth'] }</a></p>
						</td>
					</tr> 
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${ sessionVars.requestType == REQUEST_TYPE_CHECK }">
					<tr>
						<td>
							<b>Consent to a Criminal Record Check</b><br/>
						</td>
					</tr>
					<tr>
						<td>
							<ul>
								<li>
								I hereby consent to a check for records of criminal convictions to determine whether I have a conviction 
								or outstanding charge for any relevant or specified offences under the Criminal Records Review Act;
								</li>
								<li>
								I hereby authorize the release to the Deputy Registrar any documents in the custody of the police, the court 
								and crown counsel relating to an outstanding charge or conviction of any relevant or specified offence 
								as defined under the Criminal Records Review Act.
								</li>
								<li>
								Where the results of this check indicate that a criminal record or outstanding charge for a relevant or specified 
								offence may exist, I agree to provide my fingerprints to verify any such criminal record.
								</li>
								<li>
								The Deputy Registrar will notify me and my organization that I have an outstanding charge or conviction for any 
								relevant or specified offence(s) and the matter has been referred to the Deputy Registrar;
								</li>
								<li>
								The Deputy Registrar will determine whether or not I present a risk of physical or sexual abuse to children 
								and/or physical, sexual or financial abuse to vulnerable adults as applicable.
								</li>
								<li>
								The Deputy Registrar's determination will be disclosed to my organization and it will include consideration 
								of any relevant or specified offence for which I have received a pardon;
								</li>
								<li>
								If I am charged with or convicted of a relevant or specified offence at any time subsequent to the criminal 
								record check authorized herein, I further agree to report the charge or conviction to my organization and 
								provide my organization, in a timely manner, with consent to conduct a Criminal Record Check form.
								</li>
							</ul>
						</td>
					</tr>
				</c:when>
				<c:when test="${ sessionVars.requestType == REQUEST_TYPE_SHARE }">
					<tr>
						<td>
							<b>Consent</b><br/>
						</td>
					</tr>
					<tr>
						<td>
							<p>I hereby consent to share the result of a completed criminal record check with the organization 
							indicated in this request.</p>
						</td>
					</tr> 
				</c:when>
			</c:choose>
			<tr>
				<td>
					<p>
						FREEDOM OF INFORMATION AND PROTECTION OF PRIVACY ACT (FOIPPA): The information requested on this form is collected 
						under the authority of the Criminal Records Review Act section 4(1) and section 26(c) of the <i>Freedom of 
						Information and Protection of Privacy Act</i> (FOIPPA). The information provided will be used to fulfil the 
						requirements of the Criminal Records Review Act for the release of criminal records information and is in compliance 
						with the FOIPPA. If you have any questions about the collection of your personal information, please contact 
						${ sessionVars.ecrc_props['securities.contact.name'] }, ${ sessionVars.ecrc_props['securities.contact.address1'] }, ${ sessionVars.ecrc_props['securities.contact.address2'] } or by phone at ${ sessionVars.ecrc_props['securities.contact.phone'] }.
					</p>
					<p>
					Applicant Signature: _______________________________
					</p>
					<p>
					Date: _______________________________
					</p>
				</td>
			</tr> 
		</table>
		<br/>
		<div class="vcrcBox">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="right" width="90%"><input type="button" value="Print" onClick="window.print()" class="vcrcButton"></td>
			</tr>	
		</table>
		</div>	
		
		
	</div>
</div>