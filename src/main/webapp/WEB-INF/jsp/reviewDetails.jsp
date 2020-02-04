<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="ca.bc.gov.ag.ecrc.utils.Consts" %>

<c:set var="REQUEST_TYPE_CHECK" value="<%=Consts.REQUEST_TYPE_CHECK%>" />
<c:set var="REQUEST_TYPE_SHARE" value="<%=Consts.REQUEST_TYPE_SHARE%>" />

<c:set var="SCHEDULE_D" value="<%=Consts.SCHEDULE_D%>" />
<c:set var="SCHEDULE_OT_D" value="<%=Consts.SCHEDULE_OT_D%>" />

<c:set var="ONETIME" value="<%=Consts.ORG_APPL_RELATION_ONETIMETIK%>" />

<c:set var="COUNTRY_CANADA" value="<%=Consts.COUNTRY_CANADA%>" />

<div id="MainContent">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
				<p>If any corrections are required to the information presented above, select the Back button below. Select 
				Next to proceed with identity verification.</p>
			</td>
		</tr>
	</table>
	<br/>
	<form:form action="${ sessionVars.requestType }ReviewDetails.htm" method="post" id="detailsForm">
		<div class="vcrcBox">	
		<table width="100% border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left"><input type="submit" name="action" value="Back" class="vcrcButton"/></td>
				<td align="center"><input id="cancelButton" type="button" name="action" value="Cancel" class="vcrcButton"/></td>
				<%-- 
				<td align="right"><input type="submit" name="action" value="Next" class="vcrcButton"/></td>
				--%>
				<td align="right"><input type="button" id="eivNext" value="Next" class="vcrcButton"/></td>
			</tr>	
		</table>
		</div>
	</form:form>
	
</div>